package com.unicofox.greencasket.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.annotation.SuppressLint;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.unicofox.greencasket.Adapter.ProductAdapter;
import com.unicofox.greencasket.App;
import com.unicofox.greencasket.Model.ModelMain;
import com.unicofox.greencasket.R;
import com.unicofox.greencasket.Utility.Config;
import com.unicofox.greencasket.Utility.Tools;
import com.unicofox.greencasket.databinding.ActivityMainBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static com.unicofox.greencasket.Utility.Config.PRODUCTS_LIST;


public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
     int cartItems = 0;
     ProductAdapter productAdapter;
     Menu menu;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.mytoolbar);
        ini();
    }


    void ini() {
        Config.PRODUCTS_LIST.clear();
        productAdapter = new ProductAdapter(MainActivity.this, PRODUCTS_LIST);
        binding.mainRv.setAdapter(productAdapter);
        loadProducts();
    }


    void loadProducts() {
        AsyncTask.execute(() -> {
            StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.API_PRODUCTS,
                    response -> {
                        try {
                            JSONObject obj = new JSONObject(response);
                            JSONArray productArray = obj.getJSONArray("ProductsData");

                            for (int i = 0; i < productArray.length(); i++) {
                                JSONObject dataObj = productArray.getJSONObject(i);
                                ModelMain modelMain = new ModelMain(dataObj.getInt("product_id"), dataObj.getString("product_title"), dataObj.getString("product_brand"), dataObj.getString("product_image"), dataObj.getDouble("product_ratings"), dataObj.getInt("product_raters"), dataObj.getInt("product_current_price"), dataObj.getInt("product_real_price"), dataObj.getString("product_description"));
                                PRODUCTS_LIST.add(modelMain);
                            }
                            if (binding.skeletonContainer.getVisibility()== View.VISIBLE) binding.skeletonContainer.setVisibility(View.GONE);
                            productAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    },
                    error -> {}) {
                protected Map<String, String> getParams() {
                    Map<String, String> serverData = new HashMap<>();
                    serverData.put("for", Config.FOR_PRODUCTS);
                    return serverData;
                }

            };

            App.getInstance().addToRequestQueue(stringRequest);


        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_toolbar_menu, menu);
        this.menu=menu;
        getCartItemsCount();

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Intent intent = new Intent(getBaseContext(), SearchActivity.class);
                intent.putExtra("query",query);
                startActivity(intent);

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });


        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.cart:
                startActivity(new Intent(this,CartActivity.class));
                break;
            case R.id.orders:
                startActivity(new Intent(this,OrdersActivity.class));
                break;
        }
        return true;
    }




    @Override
    protected void onRestart() {
        super.onRestart();
        getCartItemsCount();
    }

    void getCartItemsCount(){
      if (menu!=null){
          final MenuItem menuCart = menu.findItem(R.id.cart);
          StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.API_CART,
                  response -> {
                      try {
                          cartItems= Objects.requireNonNull(NumberFormat.getInstance().parse(response)).intValue();
                      } catch (ParseException e) {
                          e.printStackTrace();
                      }
                      menuCart.setIcon(Tools.convertLayoutToImage(this, cartItems, R.drawable.ic_shopping));
                  },
                  error -> {
                  }) {
              protected Map<String, String> getParams() {
                  Map<String, String> serverData = new HashMap<>();
                  serverData.put("for", Config.COUNT_CART_ITEMS);
                  serverData.put("email", Config.USER_EMAIL);
                  return serverData;
              }
          };
          App.getInstance().addToRequestQueue(stringRequest);
      }

    }



}