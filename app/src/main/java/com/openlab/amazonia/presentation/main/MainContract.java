package com.openlab.amazonia.presentation.main;

import com.openlab.amazonia.core.BasePresenter;
import com.openlab.amazonia.core.BaseView;
import com.openlab.amazonia.data.entities.ChartEntity;
import com.openlab.amazonia.data.entities.PayChartEntity;
import com.openlab.amazonia.data.entities.ProductEntity;

import java.util.ArrayList;

/**
 * Created by katherine on 31/05/17.
 */

public interface MainContract {
    interface View extends BaseView<Presenter> {

        void getChart(ChartEntity chartEntity);
        void getPayChart(PayChartEntity chartEntity);
        boolean isActive();

    }

    interface Presenter extends BasePresenter {
        void loadChart();
        void loadMonthChart(int id);
        void loadPay();
        void loadPayMonthChart(int id);


    }
}
