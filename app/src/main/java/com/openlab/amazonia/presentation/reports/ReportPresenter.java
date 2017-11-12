package com.openlab.amazonia.presentation.reports;

import android.content.Context;

import com.openlab.amazonia.data.entities.ChartEntity;
import com.openlab.amazonia.data.entities.PayChartEntity;
import com.openlab.amazonia.data.local.SessionManager;
import com.openlab.amazonia.data.remote.ServiceFactory;
import com.openlab.amazonia.data.remote.request.ListRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by katherine on 31/05/17.
 */

public class ReportPresenter implements ReportContract.Presenter {

    private final ReportContract.View mView;
    private final SessionManager mSessionManager;
    private Context context;
    private boolean firstLoad = false;
    private int currentPage = 1;

    public ReportPresenter(ReportContract.View mView, Context context) {
        this.mView = mView;
        this.mSessionManager = new SessionManager(context);
        this.mView.setPresenter(this);

    }

    @Override
    public void start() {
    }


}
