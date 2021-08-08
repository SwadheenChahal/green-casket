package com.unicofox.greencasket.Activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView.Adapter;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.unicofox.greencasket.Adapter.ProductAdapter;
import com.unicofox.greencasket.App;
import com.unicofox.greencasket.Model.ModelMain;
import com.unicofox.greencasket.Utility.Config;
import com.unicofox.greencasket.Utility.Tools;
import com.unicofox.greencasket.databinding.ActivitySearchBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.unicofox.greencasket.Utility.Config.PRODUCTS_LIST;


public class SearchActivity extends AppCompatActivity {

    ActivitySearchBinding binding;
    ProductAdapter productAdapter;
    List<ModelMain> searchList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable  Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivitySearchBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        productAdapter=new ProductAdapter(this,searchList);
        binding.searchRv.setAdapter(productAdapter);

        searchProducts(getIntent().getStringExtra("query"));

        binding.goBack.setOnClickListener(v -> finish());

    }

    void searchProducts(String search) {
        AsyncTask.execute(() -> {
            StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.API_PRODUCTS,
                    response -> {
                        try {
                            JSONObject obj = new JSONObject(response);
                            JSONArray productArray = obj.getJSONArray("ProductsData");

                            for (int i = 0; i < productArray.length(); i++) {
                                JSONObject dataObj = productArray.getJSONObject(i);
                                ModelMain modelMain = new ModelMain(dataObj.getInt("product_id"), dataObj.getString("product_title"), dataObj.getString("product_brand"), dataObj.getString("product_image"), dataObj.getDouble("product_ratings"), dataObj.getInt("product_raters"), dataObj.getInt("product_current_price"), dataObj.getInt("product_real_price"), dataObj.getString("product_description"));
                                searchList.add(modelMain);
                            }
                            productAdapter.notifyDataSetChanged();
                            checkVisible();
                        } catch (JSONException e) {
                            e.printStackTrace();
                            checkVisible();
                        }
                    },
                    error -> {
                checkVisible();
                        Log.e("Search",error.getMessage());
                    }) {
                protected Map<String, String> getParams() {
                    Map<String, String> serverData = new HashMap<>();
                    serverData.put("for", Config.FOR_SEARCH);
                    serverData.put("text", search);
                    return serverData;
                }

            };

            App.getInstance().addToRequestQueue(stringRequest);

        });
    }


    void checkVisible() {
        if (binding.skeletonContainer.getVisibility() == View.VISIBLE)
            binding.skeletonContainer.setVisibility(View.GONE);
        if (searchList.size() < 1) binding.notFoundContainer.setVisibility(View.VISIBLE);

    }
}
