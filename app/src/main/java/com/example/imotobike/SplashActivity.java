package com.example.imotobike;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.example.imotobike.Util.Appconfig;

public class SplashActivity extends AppCompatActivity {
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        checkDangNhap();
    }

    private void checkDangNhap() {
        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                String phone = Appconfig.getPhoneNumber(SplashActivity.this);
                if (phone == null) {
                    Intent intentlogin = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(intentlogin);
                    finish();
                } else {
                    Intent intent = new Intent(SplashActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        }, 1000);


    }
}
