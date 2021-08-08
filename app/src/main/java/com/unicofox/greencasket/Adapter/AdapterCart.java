package com.unicofox.greencasket.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.unicofox.greencasket.Interface.CartAdapterInterface;
import com.unicofox.greencasket.Model.ModelCart;
import com.unicofox.greencasket.R;
import com.unicofox.greencasket.Utility.Config;
import com.unicofox.greencasket.Utility.Tools;
import com.unicofox.greencasket.databinding.ItemCartBinding;
import com.unicofox.greencasket.databinding.ItemTotalAmountBinding;

import java.util.List;


public class AdapterCart extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<ModelCart> cartList;
    Activity activity;
    CartAdapterInterface cartAdapterInterface;

    public AdapterCart(Activity activity, List<ModelCart> cartList, CartAdapterInterface cartAdapterInterface) {
        this.activity = activity;
        this.cartList = cartList;
        this.cartAdapterInterface = cartAdapterInterface;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == 1) {
            View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_total_amount, parent, false);
            return new TotalHolder(row);
        }
        View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart, parent, false);
        return new CartHolder(row);
    }

    @SuppressLint({"SetTextI18n", "DefaultLocale"})
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == 0) {
            CartHolder cartHolder = (CartHolder) holder;
            cartHolder.binding.textViewTitle.setText(cartList.get(position).getProductTitle());
            cartHolder.binding.currentPriceTextView.setText("" + cartList.get(position).getProductCurrentPrice());
            cartHolder.binding.realPriceTextView.setText("" + cartList.get(position).getProductRealPrice());
            cartHolder.binding.priceOffTextView.setText("" + (cartList.get(position).getProductRealPrice() - cartList.get(position).getProductCurrentPrice()) / (cartList.get(position).getProductRealPrice() / 100) + " % OFF");
            cartHolder.binding.cartItems.setText("" + cartList.get(position).getCartItems());
            Glide.with(activity)
                    .load(Config.API_KEY + cartList.get(position).getProductImageUrl())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .centerCrop()
                    .thumbnail(0.2f)
                    .into(cartHolder.binding.itemImage);
            cartHolder.binding.btnRemoveItem.setOnClickListener(v -> cartAdapterInterface.onCartItemChange(Config.REMOVE_CART_ITEM, cartList.get(position).getProductId()));
            cartHolder.binding.btnMinus.setOnClickListener(v -> {
                if (!cartHolder.binding.cartItems.getText().toString().equals("1"))
                    cartAdapterInterface.onCartItemChange(Config.MINUS_CART_ITEM, cartList.get(position).getProductId());
            });
            cartHolder.binding.btnPlush.setOnClickListener(v -> cartAdapterInterface.onCartItemChange(Config.PLUSH_CART_ITEM, cartList.get(position).getProductId()));
        } else if (getItemViewType(position) == 1) {
            TotalHolder totalHolder = (TotalHolder) holder;
            int totalAmount = 0;
            for (int item = 0; cartList.size() - 1 >= item; item++) {
                totalAmount += cartList.get(item).getProductCurrentPrice() * cartList.get(item).getCartItems();
            }
            double taxGst = totalAmount * 0.15;
            totalHolder.binding.tvSubTotal.setText("₹" + totalAmount);
            totalHolder.binding.tvTax.setText("₹" + String.format("%.2f",taxGst));
            totalHolder.binding.tvTotal.setText("₹" + String.format("%.2f",(totalAmount + taxGst)));
            totalHolder.binding.btnPlaceOrder.setOnClickListener(v -> cartAdapterInterface.onCartItemChange(Config.PLACE_CART_ORDERS, 0));
        }
    }

    @Override
    public int getItemCount() {
        return cartList.size() + 1;
    }


    @Override
    public int getItemViewType(int position) {
        if (position == cartList.size())
            return 1;
        return 0;
    }


    static class CartHolder extends RecyclerView.ViewHolder {
        ItemCartBinding binding;

        CartHolder(View view) {
            super(view);
            binding = ItemCartBinding.bind(view);
        }
    }

    static class TotalHolder extends RecyclerView.ViewHolder {
        ItemTotalAmountBinding binding;

        TotalHolder(View view) {
            super(view);
            binding = ItemTotalAmountBinding.bind(view);
        }
    }
}
