package com.unicofox.greencasket.Activity;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.unicofox.greencasket.Adapter.AdapterOrders;
import com.unicofox.greencasket.App;
import com.unicofox.greencasket.Model.ModelCart;
import com.unicofox.greencasket.Model.ModelOrder;
import com.unicofox.greencasket.Utility.Config;
import com.unicofox.greencasket.Utility.Tools;
import com.unicofox.greencasket.databinding.ActivityOrdersBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrdersActivity extends AppCompatActivity {

    ActivityOrdersBinding binding;
    AdapterOrders adapterOrders;
    List<ModelOrder> orderList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityOrdersBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        adapterOrders=new AdapterOrders(this,orderList);
        binding.ordersRv.setAdapter(adapterOrders);

        binding.goBack.setOnClickListener(v -> finish());
        loadOrders();
    }


    void loadOrders(){

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.API_ORDERS,
                response -> {
                    try {
                        JSONObject obj = new JSONObject(response);
                        JSONArray ordersArray = obj.getJSONArray("OrdersData");

                        for (int i = 0; i < ordersArray.length(); i++) {
                            JSONObject dataObj = ordersArray.getJSONObject(i);
                           ModelOrder modelOrder = new ModelOrder();
                           modelOrder.setOrderIId(dataObj.getInt("order_id"));
                           modelOrder.setOrderImage(dataObj.getString("product_image"));
                           modelOrder.setOrderPrice(dataObj.getInt("product_current_price"));
                           modelOrder.setOrderTitle(dataObj.getString("product_title"));
                           modelOrder.setOrderStatus(dataObj.getString("order_status"));
                           orderList.add(modelOrder);
                        }
                        adapterOrders.notifyDataSetChanged();
                        checkVisible();
                    } catch (JSONException e) {
                        e.printStackTrace();
                        adapterOrders.notifyDataSetChanged();
                        checkVisible();
                    }
                },
                error -> {
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
        if (orderList.size() < 1) binding.notFoundContainer.setVisibility(View.VISIBLE);

    }
}
