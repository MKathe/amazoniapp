package com.openlab.amazonia.presentation.tables.acumulativo;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.openlab.amazonia.R;
import com.openlab.amazonia.data.entities.ProductEntity;
import com.openlab.amazonia.data.entities.VisitedEntity;
import com.openlab.amazonia.utils.OnClickListListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by katherine on 26/06/17.
 */

public class AcumulativoAdapter extends RecyclerView.Adapter<AcumulativoAdapter.ViewHolder> implements OnClickListListener {
    private static final String TAG = "ELIMINAR";

    private ArrayList<VisitedEntity> list;
    private ProductEntity item;
    private ArrayList<Boolean> status;
    private Context context;
    private AcumulativoItem orderItem;


    public AcumulativoAdapter(ArrayList<VisitedEntity> list, Context context, AcumulativoItem orderItem) {
        this.list = list;
        //setStatus();
        this.context = context;
        this.orderItem = orderItem;
    }

  /*  public RecipeAdapter(SchedulesEntity item, Context context, SchedulesItem schedulesItem) {
        this.list = new ArrayList<>();
        this.item = item;
        this.context = context;
        this.schedulesItem = schedulesItem;
    }*/

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_visited, parent, false);
        return new ViewHolder(root, this);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final VisitedEntity productEntity = list.get(position);

        if (productEntity == null) {
            return;
        }
        holder.tvName.setText(productEntity.getAnp());
        holder.ammount2016.setText(String.valueOf(productEntity.getLast_year_exonerated()+productEntity.getLast_year_foreign()+productEntity.getLast_year_national()));
        holder.ammount2017.setText(String.valueOf(productEntity.getThis_year_exonerated()+productEntity.getThis_year_foreign()+productEntity.getThis_year_national()));
        /*holder.tvPrice.setText(schedulesEntity.getPriceNormal());
        holder.tvQuantity.setText(schedulesEntity.getMaxUser());
        holder.imEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                schedulesItem.clickItem(schedulesEntity);
            }
        });
        holder.imDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                schedulesItem.deleteItem(schedulesEntity, position);
            }
        });
        holder.tvLocality.setText(schedulesEntity.getLocality());
        holder.tvHour.setText(schedulesEntity.getHour());

        if (schedulesEntity.getDestiny().getImage_1()!=null){
            Glide.with(context)
                    .load(schedulesEntity.getDestiny().getImage_1())
                    .transform(new CircleTransform(context))
                    .into(holder.ivPlaces);
        }else{
            (holder).ivPlaces.setImageDrawable(context.getDrawable(R.drawable.circular_symbol));
        }*/


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setPlaceItem(VisitedEntity productEntity) {
        this.list.clear();
        if (productEntity != null) {
            this.list.add(productEntity);
        }
        notifyDataSetChanged();
    }

    public void setItems(ArrayList<VisitedEntity> items) {
        list = items;
        notifyDataSetChanged();
    }

    public void deleteItem(int position) {
        String pos = String.valueOf(position);
        list.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, list.size());
        Log.e(TAG, pos);
    }

    @Override
    public void onClick(int position) {
        final VisitedEntity productEntity = list.get(position);
        orderItem.clickItem(productEntity);

    }

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private OnClickListListener onClickListListener;

        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.ammount_2016)
        TextView ammount2016;
        @BindView(R.id.ammount_2017)
        TextView ammount2017;

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
