package com.openlab.amazonia.presentation.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.openlab.amazonia.R;
import com.openlab.amazonia.core.BaseActivity;
import com.openlab.amazonia.data.entities.UserEntity;
import com.openlab.amazonia.data.local.SessionManager;
import com.openlab.amazonia.presentation.auth.LoginActivity;
import com.openlab.amazonia.presentation.orders.OrdersActivity;
import com.openlab.amazonia.presentation.recipes.RecipeActivity;
import com.openlab.amazonia.utils.ActivityUtils;
import com.openlab.amazonia.utils.CircleTransform;

/**
 * Created by katherine on 3/05/17.
 */

public class VisitedActivity extends BaseActivity {

    DrawerLayout mDrawer;
    NavigationView navigationView;
    SessionManager mSessionManager;
    private Toolbar toolbar;
    private ActionBarDrawerToggle mDrawerToggle;

    public TextView tv_username;
    public TextView tv_email;
    public UserEntity mUser;
    public ImageView imageView;
    private VisitedFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSessionManager = new SessionManager(this);

        /**
         *Setup the DrawerLayout and NavigationView
         */
        mDrawer = (DrawerLayout) findViewById(R.id.drawerLayout);
        navigationView = (NavigationView) findViewById(R.id.navigation);


        /**
         * Setup click events on the Navigation View Items.
         */

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setTitle("Dashboard");

        setupDrawerContent(navigationView);

        mDrawerToggle = new ActionBarDrawerToggle(
                this,                    /* host Activity */
                mDrawer,                    /* DrawerLayout object */
                toolbar,
                R.string.app_name,  /* "open drawer" description for accessibility */
                R.string.app_name  /* "close drawer" description for accessibility */
        );
        mDrawerToggle.syncState();
        mDrawer.setDrawerListener(mDrawerToggle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        mDrawerToggle.syncState();
        View header = navigationView.getHeaderView(0);

        tv_username = (TextView) header.findViewById(R.id.tv_fullnanme);
        tv_email = (TextView) header.findViewById(R.id.tv_email);
        imageView = (ImageView) header.findViewById(R.id.imageView);
        //EventBus.getDefault().register(this);
        initHeader();

        fragment = (VisitedFragment) getSupportFragmentManager()
                .findFragmentById(R.id.body);

        if (fragment == null) {
            fragment = VisitedFragment.newInstance();

            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                    fragment, R.id.body);
        }
        new VisitedPresenter(fragment, this);
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {

                        menuItem.setChecked(false);
                        menuItem.setCheckable(false);

                        switch (menuItem.getItemId()) {
                            case R.id.action_begin_order:
                                //next(RecipeActivity.this,null, AboutUsActivity.class, false);
                                break;
                            case R.id.action_orders:
                                //Intent intent = new Intent(RecipeActivity.this , ProfileActivity.class);
                                //startActivityForResult(intent, 7);
                                break;
                            case R.id.action_recipe:
                                next(VisitedActivity.this,null, RecipeActivity.class, false);
                                break;
                            case R.id.action_recipe_mine:
                                //next(RecipeActivity.this, null, SlideActivity.class, false);
                                break;
                            case R.id.action_purchase:
                                next(VisitedActivity.this,null, OrdersActivity.class, false);
                                //next(RecipeActivity.this,null, AboutUsActivity.class, false);
                                break;
                            case R.id.action_signout:
                                CloseSession();
                                break;
                            default:
                                break;
                        }
                        menuItem.setChecked(false);
                        //  mDrawer.closeDrawers();
                        return true;
                    }
                });
    }

    private void CloseSession() {
        mSessionManager.closeSession();
        //AccessToken.setCurrentAccessToken(null);
        newActivityClearPreview(this, null, LoginActivity.class);

    }


    public void initHeader() {

        mUser = mSessionManager.getUserEntity();
        if (mUser != null) {
            tv_username.setText(mUser.getFullName());
            tv_username.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //next(RecipeActivity.this, null, ProfileActivity.class, false);
                }
            });
            tv_email.setText(mUser.getEmail());
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //next(RecipeActivity.this, null, ProfileActivity.class, false);
                }
            });

            if (mUser.getPicture() != null) {
                Glide.with(this)
                        .load(mSessionManager.getUserEntity().getPicture())
                        .transform(new CircleTransform(this))
                        .into(imageView);
            }

        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case 200:
                    initHeader();
                    break;
                case 7:
                    Glide.with(this)
                            .load(mSessionManager.getUserEntity().getPicture())
                            .transform(new CircleTransform(this))
                            .into(imageView);
                    break;


            }
        }
    }

    @Override
    public void onBackPressed() {
        if (this.mDrawer.isDrawerOpen(GravityCompat.START)) {
            this.mDrawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
       // EventBus.getDefault().unregister(this);
    }
}
