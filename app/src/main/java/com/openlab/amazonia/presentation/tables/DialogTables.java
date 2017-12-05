package com.openlab.amazonia.presentation.tables;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.openlab.amazonia.R;
import com.openlab.amazonia.data.entities.ListEntity;
import com.openlab.amazonia.data.entities.VisitedEntity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by kath on 5/12/17.
 */

public class DialogTables extends AlertDialog {
    @BindView(R.id.tv_foreign)
    TextView tvForeign;
    @BindView(R.id.tv_national)
    TextView tvNational;
    @BindView(R.id.tv_exonerated)
    TextView tvExonerated;
    @BindView(R.id.tv_last_foreign)
    TextView tvLastForeign;
    @BindView(R.id.tv_last_national)
    TextView tvLastNational;
    @BindView(R.id.tv_last_exonerated)
    TextView tvLastExonerated;
    @BindView(R.id.tv_foreign_percent)
    TextView tvForeignPercent;
    @BindView(R.id.tv_national_percent)
    TextView tvNationalPercent;
    @BindView(R.id.tv_exonerated_percent)
    TextView tvExoneratedPercent;
    private int id;
    private VisitedEntity visitedEntity;

    protected DialogTables(Context context, Bundle bundle) {
        super(context);
        LayoutInflater inflater = LayoutInflater.from(getContext());
        final View view = inflater.inflate(R.layout.dialog_detail_table, null);
        ButterKnife.bind(this, view);
        setView(view);
        visitedEntity = (VisitedEntity) bundle.getSerializable("visitedEntity");
        updateUi(visitedEntity);

    }

    public void updateUi(VisitedEntity visitedEntity) {
        if (visitedEntity != null) {
            tvExonerated.setText(String.valueOf(visitedEntity.getThis_year_exonerated()));
            tvNational.setText(String.valueOf(visitedEntity.getThis_year_national()));
            tvForeign.setText(String.valueOf(visitedEntity.getThis_year_foreign()));


            tvLastExonerated.setText(String.valueOf(visitedEntity.getLast_year_exonerated()));
            tvLastNational.setText(String.valueOf(visitedEntity.getLast_year_national()));
            tvLastForeign.setText(String.valueOf(visitedEntity.getLast_year_foreign()));


            tvExoneratedPercent.setText(String.valueOf(visitedEntity.getExonerated_percent())+ "%");
            tvNationalPercent.setText(String.valueOf(visitedEntity.getNational_percent())+"%");
            tvForeignPercent.setText(String.valueOf(visitedEntity.getForeign_percent())+"%");

        } else {
            tvExonerated.setText(String.valueOf("Sin data"));
            tvNational.setText(String.valueOf("Sin data"));
            tvForeign.setText(String.valueOf("Sin data"));


            tvLastExonerated.setText("Sin data");
            tvLastNational.setText("Sin data");
            tvLastForeign.setText("Sin data");


            tvExoneratedPercent.setText("Sin data");
            tvNationalPercent.setText("Sin data");
            tvForeignPercent.setText("Sin data");
        }

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
