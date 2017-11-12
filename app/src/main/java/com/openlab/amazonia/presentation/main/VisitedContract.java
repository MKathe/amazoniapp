package com.openlab.amazonia.presentation.main;

import com.openlab.amazonia.core.BasePresenter;
import com.openlab.amazonia.core.BaseView;

import java.util.ArrayList;

/**
 * Created by katherine on 31/05/17.
 */

public interface VisitedContract {
    interface View extends BaseView<Presenter> {

        void getProducts(ArrayList<ProductEntity> list);

        void showDetailsProducts(ProductEntity ordersEntity);

        boolean isActive();



    }

    interface Presenter extends BasePresenter {

        void loadList();

    }
}
