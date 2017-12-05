package com.openlab.amazonia.presentation.visited;

import android.content.Context;

import com.openlab.amazonia.data.entities.ChartEntity;
import com.openlab.amazonia.data.local.SessionManager;
import com.openlab.amazonia.data.remote.ServiceFactory;
import com.openlab.amazonia.data.remote.request.ListRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by katherine on 31/05/17.
 */

public class TotalVisitedPresenter implements TotalVisitedContract.Presenter {

    private final TotalVisitedContract.View mView;
    private final SessionManager mSessionManager;
    private Context context;
    private boolean firstLoad = false;
    private int currentPage = 1;

    public TotalVisitedPresenter(TotalVisitedContract.View mView, Context context) {
        this.mView = mView;
        this.mSessionManager = new SessionManager(context);
        this.mView.setPresenter(this);

    }

    @Override
    public void start() {
    }

    @Override
    public void loadChart() {
        mView.setLoadingIndicator(true);

        ListRequest listRequest = ServiceFactory.createService(ListRequest.class);
        Call<ChartEntity> reservation = listRequest.getChart("Token " + mSessionManager.getUserToken());
        reservation.enqueue(new Callback<ChartEntity>() {
            @Override
            public void onResponse(Call<ChartEntity> call, Response<ChartEntity> response) {
                mView.setLoadingIndicator(false);
                if (!mView.isActive()) {
                    return;
                }
                if (response.isSuccessful()) {
                    mView.getChart(response.body());

                } else {
                    mView.showErrorMessage("Error al obtener la lista");
                }
            }

            @Override
            public void onFailure(Call<ChartEntity> call, Throwable t) {
                if (!mView.isActive()) {
                    return;
                }
                mView.setLoadingIndicator(false);
                mView.showErrorMessage("Error al conectar con el servidor");
            }
        });

    }

    @Override
    public void loadMonthChart(int id) {
        mView.setLoadingIndicator(true);

        ListRequest listRequest = ServiceFactory.createService(ListRequest.class);
        Call<ChartEntity> reservation = listRequest.getChartByMonth("Token " + mSessionManager.getUserToken(), id);
        reservation.enqueue(new Callback<ChartEntity>() {
            @Override
            public void onResponse(Call<ChartEntity> call, Response<ChartEntity> response) {
                mView.setLoadingIndicator(false);
                if (!mView.isActive()) {
                    return;
                }
                if (response.isSuccessful()) {
                    mView.getChart(response.body());

                } else {
                    mView.showErrorMessage("Error al obtener la lista");
                }
            }

            @Override
            public void onFailure(Call<ChartEntity> call, Throwable t) {
                if (!mView.isActive()) {
                    return;
                }
                mView.setLoadingIndicator(false);
                mView.showErrorMessage("Error al conectar con el servidor");
            }
        });
    }


}
