package com.openlab.amazonia.presentation.main;

import android.os.Bundle;

import com.openlab.amazonia.R;
import com.openlab.amazonia.core.BaseActivity;
import com.openlab.amazonia.presentation.auth.LoginFragment;
import com.openlab.amazonia.presentation.auth.LoginPresenter;
import com.openlab.amazonia.utils.ActivityUtils;


public class VisitedActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clear);

        VisitedFragment fragment = (VisitedFragment) getSupportFragmentManager()
                .findFragmentById(R.id.body);

        if (fragment == null) {
            fragment = VisitedFragment.newInstance();

            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                    fragment, R.id.body);
        }

        // Create the presenter
        new VisitedPresenter(fragment,this);
    }


}
