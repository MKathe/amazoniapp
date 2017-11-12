package com.openlab.amazonia.presentation.recaudation;

import com.openlab.amazonia.core.BasePresenter;
import com.openlab.amazonia.core.BaseView;
import com.openlab.amazonia.data.entities.VisitedEntity;

import java.util.ArrayList;

/**
 * Created by katherine on 31/05/17.
 */

public interface TableRecaudationContract {
    interface View extends BaseView<Presenter> {

        void getList(ArrayList<VisitedEntity> list);

        boolean isActive();



    }

    interface Presenter extends BasePresenter {

        void loadList();

    }
}
