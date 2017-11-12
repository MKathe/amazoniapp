package com.openlab.amazonia.presentation.auth;

import android.content.Context;
import android.support.annotation.NonNull;

import com.openlab.amazonia.data.entities.AccessTokenEntity;
import com.openlab.amazonia.data.entities.UserEntity;
import com.openlab.amazonia.data.local.SessionManager;
import com.openlab.amazonia.data.remote.ServiceFactory;
import com.openlab.amazonia.data.remote.request.LoginRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by katherine on 10/05/17.
 */

public class LoginPresenter implements LoginContract.Presenter {

    private final LoginContract.View mView;
    private final SessionManager mSessionManager;
    private Context context;

    public LoginPresenter(@NonNull LoginContract.View mView, @NonNull Context context) {
        this.context = context;
        this.mView = mView;
        this.mView.setPresenter(this);
        mSessionManager = new SessionManager(context);

    }

    @Override
    public void loginUser(String username, String password) {
/*
        UserEntity user = new UserEntity(username, password,
                "Kath", "Caillahua","992406360","Femenino");

       mSessionManager.setUser(user);
       mView.loginSuccessful(user);*/

        LoginRequest loginService =
                ServiceFactory.createService(LoginRequest.class);
        Call<AccessTokenEntity> call = loginService.login(username,password);
        mView.setLoadingIndicator(true);
        call.enqueue(new Callback<AccessTokenEntity>() {
            @Override
            public void onResponse(Call<AccessTokenEntity> call, Response<AccessTokenEntity> response) {
                if(!mView.isActive()){
                    return;
                }
                if (response.isSuccessful()) {
                    mView.setLoadingIndicator(false);
                    mView.loginSuccessful(response.body().getAccount_id());
                    //AccessToken.setCurrentAccessToken(null);
                    //getProfile(response.body());

                } else {
                    mView.setLoadingIndicator(false);
                    mView.showMessage("login fallido");
                }
            }

            @Override
            public void onFailure(Call<AccessTokenEntity> call, Throwable t) {
                if(!mView.isActive()){
                    return;
                }
                mView.setLoadingIndicator(false);
                mView.showErrorMessage("No se puede conectar al servidor");
            }
        });
    }

    /*@Override
    public void getProfile(final AccessTokenEntity token) {
        LoginRequest loginService =
                ServiceFactory.createService(LoginRequest.class);
        Call<UserEntity> call = loginService.getUser("Token "+ token.getAccessToken());
        call.enqueue(new Callback<UserEntity>() {
            @Override
            public void onResponse(Call<UserEntity> call, Response<UserEntity> response) {
                if (response.isSuccessful()) {
                    if (!mView.isActive()) {
                        return;
                    }
                    openSession(token, response.body());

                } else {
                    if (!mView.isActive()) {
                        return;
                    }
                    mView.setLoadingIndicator(false);
                    mView.errorLogin("Ocurrió un error al cargar su perfil");
                }
            }

            @Override
            public void onFailure(Call<UserEntity> call, Throwable t) {
                if (!mView.isActive()) {
                    return;
                }
                mView.setLoadingIndicator(false);
                mView.errorLogin("Fallo al traer datos, comunicarse con su administrador");
            }
        });
    }*/

  /*

    @Override
    public void openSession(AccessTokenEntity token, UserEntity userEntity) {
        mSessionManager.openSession(token);
        mSessionManager.setUser(userEntity);
        mView.setLoadingIndicator(false);
        mView.loginSuccessful(userEntity);
    }*/

    /*@Override
    public void sendEmail(String email) {
        LoginRequest loginRequest =
                ServiceFactory.createService(LoginRequest.class);
        Call<UserEntity> call = loginRequest.recovery(email);
        call.enqueue(new Callback<UserEntity>() {
            @Override
            public void onResponse(Call<UserEntity> call, Response<UserEntity> response) {
                if (response.isSuccessful()) {
                    if (!mView.isActive()) {
                        return;
                    }
                    mView.showMessage("Se envió un correo con instrucciones");
                } else {
                    if (!mView.isActive()) {
                        return;
                    }
                    mView.setLoadingIndicator(false);
                    mView.showErrorMessage("Ocurrió un error al enviar el correo a su dirección");
                }
            }

            @Override
            public void onFailure(Call<UserEntity> call, Throwable t) {
                if (!mView.isActive()) {
                    return;
                }
                mView.setLoadingIndicator(false);
                mView.showErrorMessage("Fallo al traer datos, comunicarse con su administrador");
            }


        });
    }*/

    @Override
    public void start() {

    }
}
