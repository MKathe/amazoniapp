package com.openlab.amazonia.presentation.pagantes;

import com.openlab.amazonia.core.BasePresenter;
import com.openlab.amazonia.core.BaseView;
import com.openlab.amazonia.data.entities.VisitedEntity;

import java.util.ArrayList;

/**
 * Created by katherine on 31/05/17.
 */

public interface TablePagantesContract {
    interface View extends BaseView<Presenter> {

        void getList(ArrayList<VisitedEntity> list);

        boolean isActive();



    }

    interface Presenter extends BasePresenter {

        void loadList();

    }
}
