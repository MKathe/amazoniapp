package com.openlab.amazonia.presentation.tables.acumulativo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.format.Time;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.openlab.amazonia.R;
import com.openlab.amazonia.core.BaseActivity;
import com.openlab.amazonia.core.BaseFragment;
import com.openlab.amazonia.data.entities.VisitedEntity;
import com.openlab.amazonia.utils.ProgressDialogCustom;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by katherine on 31/05/17.
 */

public class AcumulativoFragment extends BaseFragment implements AcumulativoContract.View {

    @BindView(R.id.rv_list)
    RecyclerView rvList;
    @BindView(R.id.noListIcon)
    ImageView noListIcon;
    @BindView(R.id.noListMain)
    TextView noListMain;
    @BindView(R.id.noList)
    LinearLayout noList;
    Unbinder unbinder;
    @BindView(R.id.spinner)
    Spinner spinner;

    private AcumulativoAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;
    private AcumulativoContract.Presenter mPresenter;
    private ProgressDialogCustom mProgressDialogCustom;

    private DialogTables dialogTables;

    private int idMonth;

    public AcumulativoFragment() {
        // Requires empty public constructor
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    public static AcumulativoFragment newInstance() {
        return new AcumulativoFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_list, container, false);
        unbinder = ButterKnife.bind(this, root);
        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mProgressDialogCustom = new ProgressDialogCustom(getContext(), "Obteniendo datos...");
        mLayoutManager = new LinearLayoutManager(getContext());
        rvList.setLayoutManager(mLayoutManager);
        mAdapter = new AcumulativoAdapter(new ArrayList<VisitedEntity>(), getContext(), (AcumulativoItem) mPresenter);
        rvList.setAdapter(mAdapter);
        getListMonth();
    }

    public void getListMonth(){
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

        spinner.setAdapter(adapter);

        Time today = new Time(Time.getCurrentTimezone());
        today.setToNow();
        int monthDay = today.monthDay;
        int month = today.month;

        spinner.setSelection(month);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                idMonth = position + 1;
                mPresenter.loadList(idMonth);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void getList(ArrayList<VisitedEntity> list) {
        mAdapter.setItems(list);

        if (list != null) {
            noList.setVisibility((list.size() > 0) ? View.GONE : View.VISIBLE);
        }

    }

    @Override
    public void showDetailsVisited(VisitedEntity visitedEntity) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("visitedEntity", visitedEntity);
        dialogTables = new DialogTables(getContext(), bundle);
        dialogTables.show();


    }

    @Override
    public boolean isActive() {
        return isAdded();
    }

    @Override
    public void setPresenter(AcumulativoContract.Presenter mPresenter) {
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
}
