package com.openlab.amazonia.presentation.visited;

import com.openlab.amazonia.core.BasePresenter;
import com.openlab.amazonia.core.BaseView;
import com.openlab.amazonia.data.entities.ChartEntity;

/**
 * Created by katherine on 31/05/17.
 */

public interface TotalVisitedContract {
    interface View extends BaseView<Presenter> {

        void getChart(ChartEntity chartEntity);
        boolean isActive();

    }

    interface Presenter extends BasePresenter {
        void loadChart();
        void loadMonthChart(int id);

    }
}
