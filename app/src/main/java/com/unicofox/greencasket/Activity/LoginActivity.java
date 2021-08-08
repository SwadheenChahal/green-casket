package com.unicofox.greencasket.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.unicofox.greencasket.App;
import com.unicofox.greencasket.Utility.Config;
import com.unicofox.greencasket.Utility.Tools;
import com.unicofox.greencasket.databinding.ActivityLoginBinding;

import java.util.HashMap;
import java.util.Map;


public class LoginActivity extends AppCompatActivity {

    ActivityLoginBinding binding;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        dialog = new ProgressDialog(this);
        dialog.setCancelable(false);
        dialog.setMessage("Please Wait !");
        iniSystem();
    }


    private void iniSystem(){
        binding.labelSignUp.setOnClickListener(v -> {
            startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
        });

        binding.btnLogin.setOnClickListener(v -> {
            if (validateInput()){
                doLogin();
                dialog.show();
            }
            else Tools.showThemeSnackBar(binding.parentView,"Please Enter Valid Details !");
        });
    }


    private void doLogin(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.API_LOGIN,
                response -> {
                    Tools.showThemeSnackBar(binding.parentView, response);
                    if (response.equals("Login Success !")) {
                        SharedPreferences.Editor editor = getSharedPreferences(Config.APP_PREF, MODE_PRIVATE).edit();
                        editor.putString("user_email", binding.emailTextBox.getText().toString());
                        editor.apply();
                        Intent i = new Intent(getApplicationContext(), MainActivity.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(i);
                        finishAffinity();
                    }
                    dialog.dismiss();
                },
                error -> {
                    Tools.showToast(LoginActivity.this, "" + error.getMessage());
                    dialog.dismiss();
                }) {
            protected Map<String, String> getParams() {
                Map<String, String> serverData = new HashMap<>();
                serverData.put("email", binding.emailTextBox.getText().toString());
                serverData.put("password", binding.passwordTextBox.getText().toString());
                return serverData;
            }
        };
        App.getInstance().addToRequestQueue(stringRequest);

    }


    private boolean validateInput(){return binding.emailTextBox.getText().length()>8&&binding.passwordTextBox.getText().length()>4;}

}
