package com.example.imotobike;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.imotobike.IMotoAPI.API;
import com.example.imotobike.model.Login;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    Button btnDangNhap;
    EditText edtSoDienThoai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        dangnhap();
    }

    private void dangnhap() {
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String soDienThoai = edtSoDienThoai.getText().toString();

                // lay du lieu
                GetLogin getLogin = new GetLogin("0387490078", "hjgjhg", "android");
                Retrofit retrofit = new Retrofit.Builder()
                        .addConverterFactory(GsonConverterFactory.create())
                        .baseUrl("http://45.118.144.19:1904/api/")
                        .build();
                retrofit.create(API.class).GetLogin(getLogin).enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        try {
                            String strJson =response.body().string();
                            Gson gson = new Gson();
                            Login login = gson.fromJson(strJson, Login.class);
                            String phone = login.getLoginResult().getPhone().toString();
                           if (soDienThoai.equals(phone)){
                                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                                intent.putExtra("user",login);
                                startActivity(intent);
                                finish();
                            }
                            else {
                                Toast.makeText(MainActivity.this, "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(MainActivity.this, "fail", Toast.LENGTH_SHORT).show();

                    }
                });

            }
        });
    }

    private void init() {
        btnDangNhap = findViewById(R.id.btn_dang_nhap);
        edtSoDienThoai = findViewById(R.id.edt_phone_number);

    }

    class GetLogin {
        String PhoneNumber, DeviceID, Os;

        public GetLogin(String phoneNumber, String deviceID, String os) {
            PhoneNumber = phoneNumber;
            DeviceID = deviceID;
            Os = os;
        }
    }
}
