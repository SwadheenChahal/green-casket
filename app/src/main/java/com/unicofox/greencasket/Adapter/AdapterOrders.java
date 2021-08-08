package com.unicofox.greencasket.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.unicofox.greencasket.Model.ModelOrder;
import com.unicofox.greencasket.R;
import com.unicofox.greencasket.Utility.Config;
import com.unicofox.greencasket.databinding.ItemOrderBinding;

import java.util.List;

public class AdapterOrders extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Activity activity;
    List<ModelOrder> orderList;


    public AdapterOrders(Activity activity,List<ModelOrder> orderList){
        this.activity=activity;
        this.orderList=orderList;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order, parent, false);
        return new OrderHolder(row);

    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        OrderHolder orderHolder = (OrderHolder) holder;
        orderHolder.binding.orderStatus.setText(orderList.get(position).getOrderStatus());
        orderHolder.binding.orderId.setText("order id: #"+Config.USER_EMAIL.hashCode()+"OCD"+orderList.get(position).getOrderIId());
        orderHolder.binding.orderPrice.setText("₹"+(orderList.get(position).getOrderPrice()+orderList.get(position).getOrderPrice()*0.15));
        orderHolder.binding.orderTitleTv.setText(orderList.get(position).getOrderTitle());
        Glide.with(activity)
                .load(Config.API_KEY + orderList.get(position).getOrderImage())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .thumbnail(0.2f)
                .into(orderHolder.binding.orderItemImage);


        switch (orderList.get(position).getOrderStatus()) {
            case "Shipped":
                orderHolder.binding.orderStatus.setBackgroundResource(R.drawable.bg_curved_shipping);
                break;
            case "On the Way":
                orderHolder.binding.orderStatus.setBackgroundResource(R.drawable.bg_curved_on_the_way);
                break;
            case "Delivery Tomorrow":
                orderHolder.binding.orderStatus.setBackgroundResource(R.drawable.bg_curved_delivery_tomorrow);
                break;
            case "Delivery Today":
                orderHolder.binding.orderStatus.setBackgroundResource(R.drawable.bg_curved_delivery_today);
                break;
            case "Delivered":
                orderHolder.binding.orderStatus.setBackgroundResource(R.drawable.bg_curved_delivered);
                break;
            case "Cancelled":
                orderHolder.binding.orderStatus.setBackgroundResource(R.drawable.bg_curved_cancelled);
                break;
            case "Processing":
                orderHolder.binding.orderStatus.setBackgroundResource(R.drawable.bg_curved_normal);
                break;
        }


    }


    @Override
    public int getItemCount() {
        return orderList.size();
    }

    static class OrderHolder extends RecyclerView.ViewHolder{
        ItemOrderBinding binding;
        public OrderHolder(View view){
            super(view);
            binding=ItemOrderBinding.bind(view);
        }
    }
}
