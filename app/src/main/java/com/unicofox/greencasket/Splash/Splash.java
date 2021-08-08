package com.unicofox.greencasket.Splash;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;

import androidx.appcompat.app.AppCompatActivity;
import com.unicofox.greencasket.Activity.LoginActivity;
import com.unicofox.greencasket.Activity.MainActivity;
import com.unicofox.greencasket.Utility.Config;
import com.unicofox.greencasket.databinding.SplashScreenBinding;


public class Splash extends AppCompatActivity {
    SharedPreferences prefs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        com.unicofox.greencasket.databinding.SplashScreenBinding binding = SplashScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        prefs = getSharedPreferences(Config.APP_PREF, MODE_PRIVATE);

        Config.isUserLoggedIn=prefs.getString("user_email", null)!=null;
        if (Config.isUserLoggedIn) Config.USER_EMAIL=prefs.getString("user_email", "");

                new Handler(Looper.getMainLooper()).postDelayed(() -> {
            if (Config.isUserLoggedIn) startActivity(new Intent(Splash.this, MainActivity.class));
            else startActivity(new Intent(Splash.this, LoginActivity.class));
            finish();
        }, 500);
    }


}
