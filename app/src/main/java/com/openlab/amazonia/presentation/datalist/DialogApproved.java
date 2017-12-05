package com.openlab.amazonia.presentation.datalist;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.openlab.amazonia.R;
import com.openlab.amazonia.data.entities.ListEntity;
import com.openlab.amazonia.presentation.datalist.ListContract;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by kath on 5/12/17.
 */

public class DialogApproved extends AlertDialog {
    @BindView(R.id.tv_day)
    TextView tvDay;
    @BindView(R.id.tv_foreign)
    TextView tvForeign;
    @BindView(R.id.tv_national)
    TextView tvNational;
    @BindView(R.id.tv_exonerated)
    TextView tvExonerated;
    @BindView(R.id.btn_validar)
    Button btnValidar;
    private ListContract.View mView;
    private int id;
    private ListEntity listEntity;

    protected DialogApproved(Context context, final ListContract.View mView, Bundle bundle) {
        super(context);
        this.mView = mView;
        LayoutInflater inflater = LayoutInflater.from(getContext());
        final View view = inflater.inflate(R.layout.dialog_approved, null);
        ButterKnife.bind(this, view);
        setView(view);
        listEntity = (ListEntity) bundle.getSerializable("listEntity");
        updateUi(listEntity);

    }
    public void updateUi(ListEntity listEntity){
        if (listEntity!= null){
            id = listEntity.getId();
            tvDay.setText("Día: "+ listEntity.getDay());

            tvExonerated.setText(String.valueOf("Exonerados: " +listEntity.getExonerated()));
            tvNational.setText(String.valueOf("Nacionales: " +listEntity.getNational()));
            tvForeign.setText(String.valueOf("Extranjeros: " +listEntity.getForeign()));


        }else {
            tvDay.setText("Sin data");
            tvExonerated.setText(String.valueOf("Sin data"));
            tvNational.setText(String.valueOf("Sin data"));
            tvForeign.setText(String.valueOf("Sin data"));
        }

    }

    @OnClick(R.id.btn_validar)
    public void onViewClicked() {
        mView.sendUpdate(id);
    }
}

/* @NotEmpty
    @Email
    private EditText edEmail;
    private Button btnSendEmail;
    private LoginContract.View viewContract;
    private Validator validator;

    public DialogForgotPassword(Context context, final LoginContract.View viewContract) {
        super(context);


        edEmail = (EditText) view.findViewById(R.id.et_email);
        btnSendEmail = (Button) view.findViewById(R.id.btn_send_email);
        validator = new Validator(this);
        validator.setValidationListener(this);

        btnSendEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validator.validate();
            }
        });
    }

    @Override
    public void onValidationSucceeded() {
        viewContract.showSendEmail(edEmail.getText().toString());
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
                Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
            }
        }
    }*/
