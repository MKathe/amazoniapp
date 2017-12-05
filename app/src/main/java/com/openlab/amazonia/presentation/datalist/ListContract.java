package com.openlab.amazonia.presentation.datalist;


import com.openlab.amazonia.core.BasePresenter;
import com.openlab.amazonia.core.BaseView;
import com.openlab.amazonia.data.entities.ApprovedResponse;
import com.openlab.amazonia.data.entities.ListEntity;

import java.util.ArrayList;

/**
 * Created by katherine on 12/05/17.
 */

public interface ListContract {
    interface View extends BaseView<Presenter> {

        void getList(ArrayList<ListEntity> list);

        void clickItemList(ListEntity listEntity);

        void updateResponse(ApprovedResponse approvedResponse);

        void sendUpdate(int id);


        boolean isActive();
    }

    interface Presenter extends BasePresenter {


        void loadOrdersFromPage(int id, int page);

        void loadfromNextPage(int id);

        void startLoad(int id);

        void updateData(int id);


        void getList();

    }
}
