package com.openlab.amazonia.presentation.datalist;

import android.content.Context;

import com.openlab.amazonia.data.entities.ApprovedResponse;
import com.openlab.amazonia.data.entities.ListEntity;
import com.openlab.amazonia.data.local.SessionManager;
import com.openlab.amazonia.data.remote.ServiceFactory;
import com.openlab.amazonia.data.remote.request.ListRequest;
import com.openlab.amazonia.data.remote.request.PostRequest;

import java.util.ArrayDeque;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.support.v4.util.Preconditions.checkNotNull;

/**
 * Created by katherine on 28/06/17.
 */

public class ListPresenter implements ListContract.Presenter, ListItem {

    private ListContract.View mView;
    private Context context;
    private SessionManager mSessionManager;
    private boolean firstLoad = false;
    private int currentPage = 1;

    public ListPresenter(ListContract.View mView, Context context) {
        //this.context = checkNotNull(context, "context cannot be null!");
        this.mView = mView;
        this.mView.setPresenter(this);
        //this.mSessionManager = new SessionManager(this.context);
    }


    @Override
    public void start() {

    }


    @Override
    public void clickItem(ListEntity listEntity) {
        mView.clickItemList(listEntity);
    }

    @Override
    public void deleteItem(ListEntity listEntity, int position) {

    }

    @Override
    public void loadOrdersFromPage(int id, int page) {
        //getCities(id, page);

    }

    @Override
    public void loadfromNextPage(int id) {

        if (currentPage > 0){

        }
            //getCities(id, currentPage);

    }

    @Override
    public void startLoad(int id) {
        if (!firstLoad) {
            firstLoad = true;
            loadOrdersFromPage(id, 1);
        }
    }

    @Override
    public void updateData(int id) {
        mView.setLoadingIndicator(true);
        PostRequest postRequest = ServiceFactory.createService(PostRequest.class);
        Call<ApprovedResponse> list = postRequest.updateApproved(id, true);
        list.enqueue(new Callback<ApprovedResponse>() {
            @Override
            public void onResponse(Call<ApprovedResponse> call, Response<ApprovedResponse> response) {

                if (!mView.isActive()) {
                    return;
                }
                mView.setLoadingIndicator(false);
                if (response.isSuccessful()) {

                    mView.updateResponse(response.body());

                } else {
                    mView.showErrorMessage("Error al obtener las órdenes");
                }
            }

            @Override
            public void onFailure(Call<ApprovedResponse> call, Throwable t) {
                if (!mView.isActive()) {
                    return;
                }
                mView.setLoadingIndicator(false);
                mView.showErrorMessage("Error al conectar con el servidor");
            }
        });
    }

    @Override
    public void getList() {
        mView.setLoadingIndicator(true);
        ListRequest listRequest = ServiceFactory.createService(ListRequest.class);
        Call<ArrayList<ListEntity>> list = listRequest.getListData(false);
        list.enqueue(new Callback<ArrayList<ListEntity>>() {
            @Override
            public void onResponse(Call<ArrayList<ListEntity>> call, Response<ArrayList<ListEntity>> response) {

                if (!mView.isActive()) {
                    return;
                }
                mView.setLoadingIndicator(false);
                if (response.isSuccessful()) {

                    mView.getList(response.body());

                } else {
                    mView.showErrorMessage("Error al obtener las órdenes");
                }
            }

            @Override
            public void onFailure(Call<ArrayList<ListEntity>> call, Throwable t) {
                if (!mView.isActive()) {
                    return;
                }
                mView.setLoadingIndicator(false);
                mView.showErrorMessage("Error al conectar con el servidor");
            }
        });
    }
}
