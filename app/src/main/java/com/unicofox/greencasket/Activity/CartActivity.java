package com.unicofox.greencasket.Activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.unicofox.greencasket.Adapter.AdapterCart;
import com.unicofox.greencasket.App;
import com.unicofox.greencasket.Interface.CartAdapterInterface;
import com.unicofox.greencasket.Model.ModelCart;
import com.unicofox.greencasket.Utility.Config;
import com.unicofox.greencasket.Utility.Tools;
import com.unicofox.greencasket.databinding.ActivityCartBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CartActivity extends AppCompatActivity {


    ActivityCartBinding binding;
    List<ModelCart> cartList = new ArrayList<>();
    ProgressDialog dialog;
    boolean canLoadAgain = true;
    AdapterCart adapterCart;
    CartAdapterInterface cartAdapterInterface;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCartBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        dialog = new ProgressDialog(this);
        dialog.setCancelable(false);
        dialog.setMessage("Please Wait !");

        cartAdapterInterface = (action, itemId) -> {
            dialog.show();
            StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.API_CART,
                    response -> {
                        Tools.showSnackBar(binding.parentView, response);
                        cartList.clear();
                        loadCartItems();
                    },
                    error -> {
                        Tools.showSnackBar(binding.parentView, "Something went wrong !");
                        dialog.dismiss();
                    })
            {
                protected Map<String, String> getParams() {
                    Map<String, String> serverData = new HashMap<>();
                    serverData.put("for", action);
                    serverData.put("email", Config.USER_EMAIL);
                    serverData.put("product_id", "" + itemId);
                    return serverData;
                }

            };
            App.getInstance().addToRequestQueue(stringRequest);
        };


        adapterCart = new AdapterCart(this, cartList, cartAdapterInterface);
        binding.cartRv.setAdapter(adapterCart);
        loadCartItems();

        binding.goBack.setOnClickListener(v -> finish());
    }


    void loadCartItems() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.API_CART,
                response -> {
                    try {
                        JSONObject obj = new JSONObject(response);
                        JSONArray productArray = obj.getJSONArray("CartData");

                        for (int i = 0; i < productArray.length(); i++) {
                            JSONObject dataObj = productArray.getJSONObject(i);
                            ModelCart modelCart = new ModelCart();
                            modelCart.setCartItems(dataObj.getInt("cart_items"));
                            modelCart.setProductTitle(dataObj.getString("product_title"));
                            modelCart.setProductImageUrl(dataObj.getString("product_image"));
                            modelCart.setProductId(dataObj.getInt("product_id"));
                            modelCart.setProductCurrentPrice(dataObj.getInt("product_current_price"));
                            modelCart.setProductRealPrice(dataObj.getInt("product_real_price"));
                            cartList.add(modelCart);
                        }
                        adapterCart.notifyDataSetChanged();
                        canLoadAgain = true;
                        dialog.dismiss();
                        checkVisible();
                    } catch (JSONException e) {
                        e.printStackTrace();
                        adapterCart.notifyDataSetChanged();
                        dialog.dismiss();
                        canLoadAgain = false;
                        checkVisible();
                    }
                },
                error -> {
                    canLoadAgain = false;
                    checkVisible();
                }) {
            protected Map<String, String> getParams() {
                Map<String, String> serverData = new HashMap<>();
                serverData.put("for", Config.FOR_GET);
                serverData.put("email", Config.USER_EMAIL);
                return serverData;
            }

        };
        App.getInstance().addToRequestQueue(stringRequest);
    }


    void checkVisible() {
        if (binding.skeletonContainer.getVisibility() == View.VISIBLE)
            binding.skeletonContainer.setVisibility(View.GONE);
        if (cartList.size() < 1) binding.notFoundContainer.setVisibility(View.VISIBLE);

    }

}
