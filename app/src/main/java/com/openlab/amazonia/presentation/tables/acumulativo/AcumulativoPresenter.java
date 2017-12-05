package com.openlab.amazonia.presentation.tables.acumulativo;

import android.content.Context;

import com.openlab.amazonia.data.entities.ResponseVisited;
import com.openlab.amazonia.data.entities.VisitedEntity;
import com.openlab.amazonia.data.local.SessionManager;
import com.openlab.amazonia.data.remote.ServiceFactory;
import com.openlab.amazonia.data.remote.request.ListRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by katherine on 31/05/17.
 */

public class AcumulativoPresenter implements AcumulativoContract.Presenter, AcumulativoItem {

    private final AcumulativoContract.View mView;
    private final SessionManager mSessionManager;
    private Context context;
    private boolean firstLoad = false;
    private int currentPage = 1;

    public AcumulativoPresenter(AcumulativoContract.View mView, Context context) {
        this.mView = mView;
        this.mSessionManager = new SessionManager(context);
        this.mView.setPresenter(this);

    }


    @Override
    public void loadList(int id) {
        mView.setLoadingIndicator(true);
        ListRequest listRequest = ServiceFactory.createService(ListRequest.class);
        Call<ResponseVisited> reservation = listRequest.getListAcumulado("Token " + mSessionManager.getUserToken(), id);
        reservation.enqueue(new Callback<ResponseVisited>() {
            @Override
            public void onResponse(Call<ResponseVisited> call, Response<ResponseVisited> response) {
                mView.setLoadingIndicator(false);
                if (!mView.isActive()) {
                    return;
                }
                if (response.isSuccessful()) {

                    mView.getList(response.body().getList_anp());

                } else {
                    mView.showErrorMessage("Error al obtener la lista");
                }
            }

            @Override
            public void onFailure(Call<ResponseVisited> call, Throwable t) {
                if (!mView.isActive()) {
                    return;
                }
                mView.setLoadingIndicator(false);
                mView.showErrorMessage("Error al conectar con el servidor");
            }
        });
    }

    @Override
    public void start() {
    }

    @Override
    public void clickItem(VisitedEntity visitedEntity) {
        mView.showDetailsVisited(visitedEntity);
    }
}
