package com.openlab.amazonia.presentation.datalist;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.openlab.amazonia.R;
import com.openlab.amazonia.core.LoaderAdapter;
import com.openlab.amazonia.data.entities.ListEntity;
import com.openlab.amazonia.utils.OnClickListListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by katherine on 15/05/17.
 */

public class ListAdapter extends LoaderAdapter<ListEntity> implements OnClickListListener {

    private Context context;
    private ListItem listItem;

    public ListAdapter(ArrayList<ListEntity> listEntities, Context context, ListItem listItem) {
        super(context);
        setItems(listEntities);
        this.context = context;
        this.listItem = listItem;
    }

    public ListAdapter(ArrayList<ListEntity> listEntities, Context context) {
        super(context);
        setItems(listEntities);
        this.context = context;

    }

    public ArrayList<ListEntity> getItems() {
        return (ArrayList<ListEntity>) getmItems();
    }

    @Override
    public long getYourItemId(int position) {
        return getmItems().get(position).getId();
    }

    @Override
    public RecyclerView.ViewHolder getYourItemViewHolder(ViewGroup parent) {
        View root = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        return new ViewHolder(root, this);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void bindYourViewHolder(RecyclerView.ViewHolder holder, int position) {
        ListEntity listEntity = getItems().get(position);
        ((ViewHolder) holder).tvNamePlace.setText(listEntity.getDay());
       /* if (cityEntity.getImage_1()!=null){
            Glide.with(context)
                    .load(cityEntity.getImage_1())
                    .transform(new CircleTransform(context))
                    .into(((ViewHolder) holder).ivPlaces);
        }else{
            ((ViewHolder) holder).ivPlaces.setImageDrawable(context.getDrawable(R.drawable.ic_no_pais));
        }*/
    }

    @Override
    public void onClick(int position) {

        ListEntity listEntity = getItems().get(position);
        listItem.clickItem(listEntity);
    }

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.iv_places)
        ImageView ivPlaces;
        @BindView(R.id.tv_name_place)
        TextView tvNamePlace;

        private OnClickListListener onClickListListener;

        ViewHolder(View itemView, OnClickListListener onClickListListener) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.onClickListListener = onClickListListener;
            this.itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onClickListListener.onClick(getAdapterPosition());
        }
    }
}
