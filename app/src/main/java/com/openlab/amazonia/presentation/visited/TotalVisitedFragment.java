package com.openlab.amazonia.presentation.visited;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.format.Time;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.openlab.amazonia.R;
import com.openlab.amazonia.core.BaseActivity;
import com.openlab.amazonia.core.BaseFragment;
import com.openlab.amazonia.data.entities.ChartEntity;
import com.openlab.amazonia.presentation.tables.VisitedActivity;
import com.openlab.amazonia.utils.ProgressDialogCustom;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by katherine on 31/05/17.
 */

public class TotalVisitedFragment extends BaseFragment implements TotalVisitedContract.View {

    @BindView(R.id.sp_mont)
    Spinner spMont;
    @BindView(R.id.piechart)
    PieChart piechart;
    Unbinder unbinder;
    @BindView(R.id.btn_table)
    LinearLayout btnTable;
    @BindView(R.id.tv_quantity)
    TextView tvQuantity;
    private TotalVisitedContract.Presenter mPresenter;
    private ProgressDialogCustom mProgressDialogCustom;

    List<PieEntry> entries;
    ArrayList<String> PieEntryLabels;
    PieDataSet pieDataSet;
    PieData pieData;

    private int idMonth;

    public TotalVisitedFragment() {
        // Requires empty public constructor
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    public static TotalVisitedFragment newInstance() {
        return new TotalVisitedFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new TotalVisitedPresenter(this, getContext());
        mPresenter.loadChart();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_visited, container, false);
        unbinder = ButterKnife.bind(this, root);
        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getMonth();
        mProgressDialogCustom = new ProgressDialogCustom(getContext(), "Obteniendo datos...");


    }

    public void AddValuesToPIEENTRY(ChartEntity chartEntity) {

        entries.add(new PieEntry(chartEntity.getForeign(), "Extranjeros"));
        entries.add(new PieEntry(chartEntity.getExonerated(), "Exonerados"));
        entries.add(new PieEntry(chartEntity.getNational(), "Nacionales"));

    }

    public void AddValuesToPieEntryLabels(ChartEntity chartEntity) {
        PieEntryLabels.add("Extranjeros");
        PieEntryLabels.add("Exonerados");
        PieEntryLabels.add("Nacionales");

    }


    @Override
    public void getChart(ChartEntity chartEntity) {

        tvQuantity.setText(String.valueOf(chartEntity.getExonerated()+chartEntity.getForeign()+chartEntity.getNational()));

        entries = new ArrayList<>();

        PieEntryLabels = new ArrayList<String>();

        AddValuesToPIEENTRY(chartEntity);

        //AddValuesToPieEntryLabels(chartEntity);

        pieDataSet = new PieDataSet(entries, " ");
        pieData = new PieData(pieDataSet);
        //pieData = new PieData(PieEntryLabels, pieDataSet);
        pieDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        piechart.setData(pieData);
        piechart.animateY(3000);
        piechart.getDescription().setEnabled(false);
        piechart.setCenterTextSize(12f);
        pieData.setValueTextSize(20f);
        Legend l = piechart.getLegend();
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);

    }


    public void getMonth() {

        ArrayList<String> names = new ArrayList<>();
        names.add("Enero");
        names.add("Febrero");
        names.add("Marzo");
        names.add("Abril");
        names.add("Mayo");
        names.add("Junio");
        names.add("Julio");
        names.add("Agosto");
        names.add("Setiembre");
        names.add("Octubre");
        names.add("Noviembre");
        names.add("Diciembre");

        ArrayAdapter adapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_dropdown_item, names);

        spMont.setAdapter(adapter);

        Time today = new Time(Time.getCurrentTimezone());
        today.setToNow();
        int monthDay = today.monthDay;
        int month = today.month;

        spMont.setSelection(month);


        spMont.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                idMonth = position + 1;
                mPresenter.loadMonthChart(idMonth);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    @Override
    public boolean isActive() {
        return isAdded();
    }

    @Override
    public void setPresenter(TotalVisitedContract.Presenter mPresenter) {
        this.mPresenter = mPresenter;
    }

    @Override
    public void setLoadingIndicator(final boolean active) {
        if (getView() == null) {
            return;
        }

        if (active) {
            mProgressDialogCustom.show();
        } else {
            if (mProgressDialogCustom.isShowing()) {
                mProgressDialogCustom.dismiss();
            }
        }
    }

    @Override
    public void showMessage(String message) {
        ((BaseActivity) getActivity()).showMessage(message);
    }

    @Override
    public void showErrorMessage(String message) {
        ((BaseActivity) getActivity()).showMessageError(message);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.btn_table})
    public void onViewClicked(View view) {
        switch (view.getId()) {/*
            case R.id.iv_search:
                mPresenter.loadMonthChart(idMonth);
                break;*/
            case R.id.btn_table:
                next(getActivity(), null, VisitedActivity.class, false);
                break;
        }
    }
}
