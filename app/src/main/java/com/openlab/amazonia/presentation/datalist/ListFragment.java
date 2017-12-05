package com.openlab.amazonia.presentation.datalist;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.openlab.amazonia.R;
import com.openlab.amazonia.core.BaseActivity;
import com.openlab.amazonia.core.BaseFragment;
import com.openlab.amazonia.data.entities.ApprovedResponse;
import com.openlab.amazonia.data.entities.ListEntity;
import com.openlab.amazonia.utils.ProgressDialogCustom;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by katherine on 28/06/17.
 */

public class ListFragment extends BaseFragment implements ListContract.View {

    @BindView(R.id.rv_list)
    RecyclerView rvList;
    @BindView(R.id.noListIcon)
    ImageView noListIcon;
    @BindView(R.id.noListMain)
    TextView noListMain;
    @BindView(R.id.noList)
    LinearLayout noList;
    //@BindView(R.id.refresh_layout)
    //ScrollChildSwipeRefreshLayout refreshLayout;
    Unbinder unbinder;
    @BindView(R.id.container_spinner)
    LinearLayout containerSpinner;
    //private CountryEntity countryEntity;
    //private CityEntity cityEntity;
    //private String daySelected;
    private ListAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;
    private ListContract.Presenter mPresenter;
    private ProgressDialogCustom mProgressDialogCustom;
    private DialogApproved dialogApproved;

    public ListFragment() {
        // Requires empty public constructor
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.getList();

    }

    public static ListFragment newInstance() {
        //CitiesFragment fragment = new CitiesFragment();
        //fragment.setArguments(bundle);
        return new ListFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ///countryEntity = (CountryEntity) getArguments().getSerializable("countryEntity");
        //cityEntity = (CityEntity) getArguments().getSerializable("cityEntity");
        //idCountry =  (int) getArguments().getSerializable("id_country");
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
        containerSpinner.setVisibility(View.GONE);
        mProgressDialogCustom = new ProgressDialogCustom(getContext(), "Obteniendo datos...");
        mLayoutManager = new LinearLayoutManager(getContext());
        rvList.setLayoutManager(mLayoutManager);
        mAdapter = new ListAdapter(new ArrayList<ListEntity>(), getContext(), (ListItem) mPresenter);
        rvList.setAdapter(mAdapter);
    }

    @Override
    public void getList(final ArrayList<ListEntity> list) {
        mAdapter.setItems(list);
        if (list != null) {
            noList.setVisibility((list.size() > 0) ? View.GONE : View.VISIBLE);
            noListMain.setText("No hay data por validar");
        }

    }

    @Override
    public void clickItemList(ListEntity listEntity) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("listEntity", listEntity);
        dialogApproved = new DialogApproved(getContext(), this, bundle);
        dialogApproved.show();
        //Toast.makeText(getContext(), "ListEntity", Toast.LENGTH_SHORT).show();
        //Bundle bundle = new Bundle();
        //bundle.putSerializable("cityEntity", cityEntity);
        //next(getActivity(), bundle, DestinyActivity.class, false);
        //getActivity().finish();

    }

    @Override
    public void updateResponse(ApprovedResponse approvedResponse) {
        showMessage("Datos validados con éxito");
        dialogApproved.dismiss();
        mPresenter.getList();
    }

    @Override
    public void sendUpdate(int id) {
        mPresenter.updateData(id);
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }


    @Override
    public void setPresenter(ListContract.Presenter mPresenter) {
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
