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
import com.unicofox.greencasket.databinding.ActivitySignUpBinding;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class SignUpActivity extends AppCompatActivity {

    ActivitySignUpBinding binding;
    String message = null;
    ProgressDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        dialog = new ProgressDialog(this);
        dialog.setCancelable(false);
        dialog.setMessage("Please Wait !");
        iniSystem();
    }


    private void iniSystem() {
        binding.labelLogin.setOnClickListener(v -> finish());

        binding.btnSignUp.setOnClickListener(v -> {
            if (validateInput()) {
                dialog.show();
                doRegister();
            } else if (message != null) Tools.showThemeSnackBar(binding.parentView, message);
            else Tools.showThemeSnackBar(binding.parentView, "Please Enter Valid Details !");
        });
    }


    private void doRegister() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.API_SIGNUP,
                response -> {

                    Tools.showThemeSnackBar(binding.parentView, response);
                    if (response.equals("Signup Success !")) {
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
                    Tools.showToast(SignUpActivity.this, "" + error.getMessage());
                    dialog.dismiss();
                }) {
            protected Map<String, String> getParams() {
                Map<String, String> serverData = new HashMap<>();
                serverData.put("username", binding.nameTextBox.getText().toString());
                serverData.put("email", binding.emailTextBox.getText().toString());
                serverData.put("number", binding.phoneTextBox.getText().toString());
                serverData.put("address", binding.addressTextBox.getText().toString());
                serverData.put("password", binding.passwordTextBox.getText().toString());
                return serverData;
            }
        };
        App.getInstance().addToRequestQueue(stringRequest);
    }


    private boolean validateInput() {
        message = null;
        if (Tools.isValidPhoneNumber(binding.phoneTextBox.getText().toString()) && binding.phoneTextBox.getText().length() == 10) {
            if (Tools.isValidMail(binding.emailTextBox.getText().toString())) {
                return binding.nameTextBox.getText().length() > 5 && binding.addressTextBox.getText().length() > 5 && binding.passwordTextBox.getText().length() > 4;
            } else message = "Enter A Valid Email !";
        } else message = "Enter A Valid Phone Number !";
        return false;
    }





}
