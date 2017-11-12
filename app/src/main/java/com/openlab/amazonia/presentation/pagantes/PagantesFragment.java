package com.openlab.amazonia.presentation.pagantes;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.openlab.amazonia.R;
import com.openlab.amazonia.core.BaseActivity;
import com.openlab.amazonia.core.BaseFragment;
import com.openlab.amazonia.data.entities.ChartEntity;
import com.openlab.amazonia.data.entities.PayChartEntity;
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

public class PagantesFragment extends BaseFragment implements PagantesContract.View {

    @BindView(R.id.sp_mont)
    Spinner spMont;
    @BindView(R.id.sp_year)
    Spinner spYear;
    @BindView(R.id.iv_search)
    ImageView ivSearch;
    @BindView(R.id.piechart)
    PieChart piechart;
    @BindView(R.id.piechart02)
    PieChart piechart02;
    Unbinder unbinder;
    @BindView(R.id.subtitle)
    TextView subtitle;
    @BindView(R.id.btn_table)
    LinearLayout btnTable;
    private PagantesContract.Presenter mPresenter;
    private ProgressDialogCustom mProgressDialogCustom;

    List<PieEntry> entries;
    ArrayList<String> PieEntryLabels;
    PieDataSet pieDataSet;
    PieData pieData;

    private int idMonth;

    public PagantesFragment() {
        // Requires empty public constructor
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    public static PagantesFragment newInstance() {
        return new PagantesFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new PagantesPresenter(this, getContext());
        mPresenter.loadChart();
        mPresenter.loadPay();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_pagantes, container, false);
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

        entries.add(new PieEntry(chartEntity.getForeign_percent(), 0));
        entries.add(new PieEntry(chartEntity.getExonerated_percent(), 1));
        entries.add(new PieEntry(chartEntity.getNational_percent(), 2));

    }

    public void AddValuesToPieEntryLabels(ChartEntity chartEntity) {
        PieEntryLabels.add("Extranjeros");
        PieEntryLabels.add("Exonerados");
        PieEntryLabels.add("Nacionales");

    }

    public void AddValuesToPayChart(PayChartEntity chartEntity) {
        entries.add(new PieEntry(chartEntity.getPayers_percent(), 0));
        entries.add(new PieEntry(chartEntity.getNon_paying_percent(), 1));

    }

    public void AddValuesToPayChartLabels(PayChartEntity chartEntity) {
        PieEntryLabels.add("Pagados");
        PieEntryLabels.add("NoPagados");

    }


    @Override
    public void getChart(ChartEntity chartEntity) {
        entries = new ArrayList<>();

        PieEntryLabels = new ArrayList<String>();

        AddValuesToPIEENTRY(chartEntity);

        AddValuesToPieEntryLabels(chartEntity);

        pieDataSet = new PieDataSet(entries, "Datos");
        pieData = new PieData(pieDataSet);
        //pieData = new PieData(PieEntryLabels, pieDataSet);
        pieDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        piechart.setData(pieData);
        piechart.setCenterTextRadiusPercent(20);
        piechart.setUsePercentValues(true);
        piechart.animateY(3000);
    }

    @Override
    public void getPayChart(PayChartEntity chartEntity) {

        entries = new ArrayList<>();

        PieEntryLabels = new ArrayList<String>();

        AddValuesToPayChart(chartEntity);

        AddValuesToPayChartLabels(chartEntity);

        pieDataSet = new PieDataSet(entries, "");
        pieData = new PieData(pieDataSet);
        //pieData = new PieData(PieEntryLabels, pieDataSet);
        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        piechart02.setData(pieData);
        piechart02.animateY(3000);
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


        spMont.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                idMonth = position + 1;
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
    public void setPresenter(PagantesContract.Presenter mPresenter) {
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

    @OnClick({R.id.iv_search, R.id.btn_table})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_search:
                mPresenter.loadMonthChart(idMonth);
                mPresenter.loadPayMonthChart(idMonth);
                break;
            case R.id.btn_table:
                next(getActivity(),null, TablePagantesActivity.class, false);
                break;
        }
    }
}
