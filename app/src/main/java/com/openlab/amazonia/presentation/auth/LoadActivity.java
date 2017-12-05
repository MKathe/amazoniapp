package com.openlab.amazonia.presentation.auth;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.openlab.amazonia.R;
import com.openlab.amazonia.core.BaseActivity;
import com.openlab.amazonia.data.local.SessionManager;
import com.openlab.amazonia.presentation.datalist.MainActivity;


/**
 * Created by katherine on 10/05/17.
 */

public class LoadActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loader);
        if (savedInstanceState == null)
            initialProcess();
    }

    private void initialProcess() {
        SessionManager mSessionManager = new SessionManager(getApplicationContext());
        if(mSessionManager.isLogin()){

            next(this,null, MainActivity.class, true);
        }else{
            next(this,null, LoginActivity.class, true);
        }
    }
}
