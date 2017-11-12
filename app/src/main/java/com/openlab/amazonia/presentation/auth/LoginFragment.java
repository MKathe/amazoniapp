package com.openlab.amazonia.presentation.auth;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.openlab.amazonia.R;
import com.openlab.amazonia.core.BaseActivity;
import com.openlab.amazonia.core.BaseFragment;
import com.openlab.amazonia.utils.ProgressDialogCustom;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Password;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by junior on 27/08/16.
 */
public class LoginFragment extends BaseFragment implements LoginContract.View, Validator.ValidationListener {

    private static final String TAG = LoginActivity.class.getSimpleName();


    @NotEmpty(message = " ")
    @BindView(R.id.et_email)
    EditText etEmail;

    @NotEmpty(message = "Contraseña inválida")
    @Password(message = "Debe contener mínimo 6 dígitos")
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.btn_login)
    Button btnLogin;
    Unbinder unbinder;
    @BindView(R.id.et_register)
    TextView etRegister;
    @BindView(R.id.tv_forgot_pass)
    TextView tvForgotPass;


    private LoginContract.Presenter mPresenter;
    private ProgressDialogCustom mProgressDialogCustom;
    //private DialogForgotPassword dialogForgotPassword;
    private Validator validator;
    private boolean isLoading = false;


    public LoginFragment() {
        // Requires empty public constructor
    }

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        //mPresenter.start();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_login, container, false);
        unbinder = ButterKnife.bind(this, root);
       // dialogForgotPassword = new DialogForgotPassword(getContext(), this);
        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mProgressDialogCustom = new ProgressDialogCustom(getContext(), "Ingresando...");
        validator = new Validator(this);
        validator.setValidationListener(this);
    }


    @Override
    public void setPresenter(LoginContract.Presenter mPresenter) {
        this.mPresenter = mPresenter;
    }

    @Override
    public void setLoadingIndicator(boolean active) {
        if (getView() == null) {
            return;
        }
        if (active) {
            mProgressDialogCustom.show();
        } else {
            if (mProgressDialogCustom.isShowing()) {
                mProgressDialogCustom.dismiss();
            }
        }
    }

    @Override
    public void showMessage(String msg) {
        ((BaseActivity) getActivity()).showMessage(msg);
    }

    @Override
    public void showErrorMessage(String message) {
        ((BaseActivity) getActivity()).showMessageError(message);
    }

    @Override
    public void loginSuccessful(int id) {

        Toast.makeText(getContext(), "Login exitoso", Toast.LENGTH_SHORT).show();
        /*Bundle bundle = new Bundle();
        bundle.putInt("id", id);
        newActivityClearPreview(getActivity(), bundle, VisitedActivity.class);
        showMessage("Login exitoso");*/
    }

    @Override
    public void errorLogin(String msg) {
        showErrorMessage(msg);
    }

  /*  @Override
    public void showDialogForgotPassword() {
        //dialogForgotPassword.show();
    }*/

    /*@Override
    public void showSendEmail(String email) {
        //mPresenter.sendEmail(email);
        //dialogForgotPassword.dismiss();
    }*/

    @Override
    public boolean isActive() {
        return isAdded();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    @OnClick({R.id.btn_login, R.id.et_register, R.id.tv_forgot_pass})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                validator.validate();

                break;
            case R.id.et_register:
               // nextActivity(getActivity(), null, RegisterActivity.class, false);
                break;
            case R.id.tv_forgot_pass:
                //showDialogForgotPassword();
                break;
        }
    }


    @Override
    public void onValidationSucceeded() {
        mPresenter.loginUser(etEmail.getText().toString(), etPassword.getText().toString());
        isLoading = true;

    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        for (ValidationError error : errors) {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(getContext());
            // Display error messages ;)
            if (view instanceof EditText) {
                ((EditText) view).setError(message);
            } else {
                Toast.makeText(getContext(), "Por favor ingrese lo campos correctamente", Toast.LENGTH_SHORT).show();
            }
        }
    }
}