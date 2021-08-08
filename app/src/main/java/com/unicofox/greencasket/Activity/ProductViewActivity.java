package com.unicofox.greencasket.Activity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import static com.unicofox.greencasket.Utility.Config.PRODUCTS_LIST;
import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.unicofox.greencasket.App;
import com.unicofox.greencasket.R;
import com.unicofox.greencasket.Utility.Config;
import com.unicofox.greencasket.Utility.Tools;
import com.unicofox.greencasket.databinding.ActivityProductViewBinding;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ProductViewActivity extends AppCompatActivity {

    ActivityProductViewBinding binding;
    int position;
    ProgressDialog dialog;
    float rating = 0;


    @SuppressLint({"SetTextI18n", "DefaultLocale"})
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProductViewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        position = getIntent().getIntExtra("position", 0);
        dialog = new ProgressDialog(this);
        dialog.setCancelable(false);
        dialog.setMessage("Please Wait !");

        Glide.with(this)
                .load(Config.API_KEY + PRODUCTS_LIST.get(position).getProductImageUrl())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .thumbnail(0.2f)
                .into(binding.productImage);

        binding.productTitle.setText("" + PRODUCTS_LIST.get(position).getProductTitle());
        binding.productBrandName.setText("" + PRODUCTS_LIST.get(position).getProductBrandName());
        binding.productDescription.setText("" + PRODUCTS_LIST.get(position).getProductDescription());
        binding.realPriceTextView.setPaintFlags(binding.realPriceTextView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        binding.currentPriceTextView.setText("" + PRODUCTS_LIST.get(position).getProductCurrentPrice());
        binding.realPriceTextView.setText("" + PRODUCTS_LIST.get(position).getProductRealPrice());
        binding.priceOffTextView.setText("" + (PRODUCTS_LIST.get(position).getProductRealPrice() - PRODUCTS_LIST.get(position).getProductCurrentPrice()) / (PRODUCTS_LIST.get(position).getProductRealPrice() / 100) + " % OFF");
        binding.ratingBar.setRating((float) (PRODUCTS_LIST.get(position).getProductRatings() / PRODUCTS_LIST.get(position).getProductRaters()));
        binding.ratingTextView.setText(String.format("%.2f",(float) (PRODUCTS_LIST.get(position).getProductRatings() / PRODUCTS_LIST.get(position).getProductRaters())) + " out of 5");
        binding.totalRatesTextView.setText("" + PRODUCTS_LIST.get(position).getProductRaters() + " indian ratings");
        binding.goBack.setOnClickListener(v -> finish());
        binding.editReview.setOnClickListener(v -> showBottomSheet());

        binding.btnAddToCart.setOnClickListener(v -> {
            dialog.show();
            StringRequest stringReq = new StringRequest(Request.Method.POST, Config.API_CART,
                    response -> {
                        Tools.showSnackBar(binding.parentLayout, response);
                        dialog.dismiss();
                    },
                    error -> {
                        Tools.showSnackBar(binding.parentLayout, "Something went wrong !");
                        dialog.dismiss();
                    }) {
                protected Map<String, String> getParams() {
                    Map<String, String> serverData = new HashMap<>();
                    serverData.put("for", Config.FOR_SET);
                    serverData.put("product_id", ""+PRODUCTS_LIST.get(position).getProductId());
                    serverData.put("email", Config.USER_EMAIL);
                    return serverData;
                }
            };

            App.getInstance().addToRequestQueue(stringReq);
        });


        binding.btnBuyNow.setOnClickListener(v -> {
            dialog.show();
            StringRequest stringReq = new StringRequest(Request.Method.POST, Config.API_ORDERS,
                    response -> {
                        Tools.showSnackBar(binding.parentLayout, response);
                        dialog.dismiss();
                    },
                    error -> {
                        Tools.showSnackBar(binding.parentLayout, "Something went wrong !");
                        dialog.dismiss();
                    }) {
                protected Map<String, String> getParams() {
                    Map<String, String> serverData = new HashMap<>();
                    serverData.put("for", Config.PLACE_ORDER);
                    serverData.put("email", Config.USER_EMAIL);
                    serverData.put("product_id",""+PRODUCTS_LIST.get(position).getProductId());
                    return serverData;
                }
            };

            App.getInstance().addToRequestQueue(stringReq);
        });
    }





    private void showBottomSheet() {

       final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        bottomSheetDialog.setContentView(R.layout.dialog_review);

        ImageView productImage = bottomSheetDialog.findViewById(R.id.product_image);

        assert productImage != null;
        Glide.with(this)
                .load(Config.API_KEY + PRODUCTS_LIST.get(position).getProductImageUrl())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .fitCenter()
                .thumbnail(0.2f)
                .into(productImage);

        Button btnCancel = bottomSheetDialog.findViewById(R.id.btn_cancel);
        Button btnRate = bottomSheetDialog.findViewById(R.id.btn_rate);
        RatingBar ratingBar = bottomSheetDialog.findViewById(R.id.rating_bar);

        assert btnCancel != null;
        btnCancel.setOnClickListener(v -> {
            bottomSheetDialog.dismiss();
        });

        assert btnRate != null;
        btnRate.setOnClickListener(v -> {
        });

        LinearLayout loading = bottomSheetDialog.findViewById(R.id.container_loading);
        LinearLayout main = bottomSheetDialog.findViewById(R.id.main_container);


        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.API_RATING,
                response -> {
                    try {
                        if (!response.equals("404")) {
                            assert ratingBar != null;
                            rating = ((Number) Objects.requireNonNull(NumberFormat.getInstance().parse(response))).floatValue();
                            ratingBar.setRating(rating);

                        }

                        assert loading != null;
                        loading.setVisibility(View.GONE);
                        assert main != null;
                        main.setVisibility(View.VISIBLE);

                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                },

                error -> {
                    assert loading != null;
                    loading.setVisibility(View.GONE);
                    assert main != null;
                    main.setVisibility(View.VISIBLE);
                }) {
            protected Map<String, String> getParams() {
                Map<String, String> serverData = new HashMap<>();
                serverData.put("for", Config.FOR_GET);
                serverData.put("email", Config.USER_EMAIL);
                serverData.put("product_id", "" + PRODUCTS_LIST.get(position).getProductId());
                return serverData;
            }

        };

        App.getInstance().addToRequestQueue(stringRequest);


        btnRate.setOnClickListener(v -> {
            assert ratingBar != null;
            bottomSheetDialog.dismiss();
            if (ratingBar.getRating() != rating) {
                Tools.showSnackBar(binding.parentLayout, "Submitting...");

                StringRequest stringReq = new StringRequest(Request.Method.POST, Config.API_RATING,
                        response -> {
                            rating = ratingBar.getRating();
                            Tools.showSnackBar(binding.parentLayout, response);
                        },
                        error -> Tools.showSnackBar(binding.parentLayout, "Something went wrong !")) {
                    protected Map<String, String> getParams() {
                        Map<String, String> serverData = new HashMap<>();
                        serverData.put("for", Config.FOR_SET);
                        serverData.put("email", Config.USER_EMAIL);
                        serverData.put("rating", "" + ratingBar.getRating());
                        serverData.put("product_id", "" + PRODUCTS_LIST.get(position).getProductId());
                        return serverData;
                    }
                };

                App.getInstance().addToRequestQueue(stringReq);
            }
        });

        bottomSheetDialog.show();
    }

}
