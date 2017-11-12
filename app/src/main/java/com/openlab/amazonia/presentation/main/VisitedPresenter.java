package com.openlab.amazonia.presentation.main;

import android.content.Context;

import com.openlab.amazonia.data.local.SessionManager;

import java.util.ArrayList;

/**
 * Created by katherine on 31/05/17.
 */

public class VisitedPresenter implements VisitedContract.Presenter, ProductItem {

    private final VisitedContract.View mView;
    private final SessionManager mSessionManager;
    private Context context;
    private boolean firstLoad = false;
    private int currentPage = 1;

    public VisitedPresenter(VisitedContract.View mView, Context context) {
        this.mView = mView;
        this.mSessionManager = new SessionManager(context);
        this.mView.setPresenter(this);

    }
    @Override
    public void clickItem(ProductEntity ordersEntity) {
        mView.showDetailsProducts(ordersEntity);

    }


    @Override
    public void loadList() {
        ArrayList<ProductEntity> list = new ArrayList<>();
        list.add(new ProductEntity(1, "Producto 01"));
        list.add(new ProductEntity(2, "Producto 02"));
        list.add(new ProductEntity(3, "Producto 03"));

        mView.getProducts(list);

       /* ListRequest listRequest = ServiceFactory.createService(ListRequest.class);
        Call<TrackHolderEntity<ReservationEntity>> reservation = listRequest.getReservation("Token " + token, page);
        reservation.enqueue(new Callback<TrackHolderEntity<ReservationEntity>>() {
            @Override
            public void onResponse(Call<TrackHolderEntity<ReservationEntity>> call, Response<TrackHolderEntity<ReservationEntity>> response) {
                mView.setLoadingIndicator(false);
                if (!mView.isActive()) {
                    return;
                }
                if (response.isSuccessful()) {

                    if (response.body().getNext() != null) {
                        currentPage = page +1;
                    } else {
                        currentPage = -1;
                    }
                    mView.getTickets(response.body().getResults());

                } else {
                    mView.showErrorMessage("Error al obtener la lista");
                }
            }

            @Override
            public void onFailure(Call<TrackHolderEntity<ReservationEntity>> call, Throwable t) {
                if (!mView.isActive()) {
                    return;
                }
                mView.setLoadingIndicator(false);
                mView.showErrorMessage("Error al conectar con el servidor");
            }
        });*/
    }

    @Override
    public void start() {
    }
}
