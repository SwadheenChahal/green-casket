package com.unicofox.greencasket.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.unicofox.greencasket.Activity.ProductViewActivity;
import com.unicofox.greencasket.Model.ModelMain;
import com.unicofox.greencasket.R;
import com.unicofox.greencasket.Utility.Config;
import com.unicofox.greencasket.databinding.ItemMainRvBinding;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final Activity activity;
    private final List<ModelMain> productsList;


    public ProductAdapter(Activity activity, List<ModelMain> productsList) {
        this.activity = activity;
        this.productsList = productsList;
        setHasStableIds(true);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main_rv, parent, false);
        return new ProductHolder(row);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ProductHolder productHolder = (ProductHolder) holder;
        productHolder.binding.realPriceTextView.setPaintFlags(productHolder.binding.realPriceTextView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        productHolder.binding.textViewTitle.setText(productsList.get(position).getProductTitle());
        productHolder.binding.brandName.setText(productsList.get(position).getProductBrandName());
        productHolder.binding.ratingBar.setRating((float) (productsList.get(position).getProductRatings() / productsList.get(position).getProductRaters()));
        productHolder.binding.ratingRaters.setText("" + productsList.get(position).getProductRaters());
        productHolder.binding.currentPriceTextView.setText("" + productsList.get(position).getProductCurrentPrice());
        productHolder.binding.realPriceTextView.setText("" + productsList.get(position).getProductRealPrice());
        productHolder.binding.priceOffTextView.setText("" + (productsList.get(position).getProductRealPrice() - productsList.get(position).getProductCurrentPrice()) / (productsList.get(position).getProductRealPrice() / 100) + " % OFF");
        Glide.with(activity)
                .load(Config.API_KEY + productsList.get(position).getProductImageUrl())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .thumbnail(0.2f)
                .into(productHolder.binding.itemImage);
        productHolder.itemView.setOnClickListener(v -> {
            Intent i = new Intent(activity,ProductViewActivity.class);
            i.putExtra("position", position);
            activity.startActivity(i);
        });
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return productsList.size();
    }


    static class ProductHolder extends RecyclerView.ViewHolder {
        ItemMainRvBinding binding;

        ProductHolder(View view) {
            super(view);
            binding = ItemMainRvBinding.bind(view);
        }
    }


}
