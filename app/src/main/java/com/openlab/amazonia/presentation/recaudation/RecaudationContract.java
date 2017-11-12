package com.openlab.amazonia.presentation.recaudation;

import com.openlab.amazonia.core.BasePresenter;
import com.openlab.amazonia.core.BaseView;
import com.openlab.amazonia.data.entities.ChartEntity;
import com.openlab.amazonia.data.entities.PayChartEntity;

/**
 * Created by katherine on 31/05/17.
 */

public interface RecaudationContract {
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
