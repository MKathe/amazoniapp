package com.openlab.amazonia.presentation.auth;


import com.openlab.amazonia.core.BasePresenter;
import com.openlab.amazonia.core.BaseView;
import com.openlab.amazonia.data.entities.UserEntity;

/**
 * Created by katherine on 3/05/17.
 */

public interface LoginContract {
    interface View extends BaseView<Presenter> {
        void loginSuccessful(UserEntity userEntity);
        void errorLogin(String msg);
       // void showDialogForgotPassword();
        //void showSendEmail(String email);
        boolean isActive();
    }

    interface Presenter extends BasePresenter {
        void loginUser(String username, String password);
        void getProfile(int id);
        //void openSession(AccessTokenEntity token, UserEntity userEntity);
       // void sendEmail(String email);


    }
}
