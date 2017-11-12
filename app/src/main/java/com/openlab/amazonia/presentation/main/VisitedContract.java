package com.openlab.amazonia.presentation.main;

import com.openlab.amazonia.core.BasePresenter;
import com.openlab.amazonia.core.BaseView;
import com.openlab.amazonia.data.entities.ProductEntity;
import com.openlab.amazonia.data.entities.VisitedEntity;

import java.util.ArrayList;

/**
 * Created by katherine on 31/05/17.
 */

public interface VisitedContract {
    interface View extends BaseView<Presenter> {

        void getList(ArrayList<VisitedEntity> list);

        boolean isActive();



    }

    interface Presenter extends BasePresenter {

        void loadList();

    }
}
