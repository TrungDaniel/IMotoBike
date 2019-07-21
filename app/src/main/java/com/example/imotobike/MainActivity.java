package com.example.imotobike;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.imotobike.IMotoAPI.API;
import com.example.imotobike.Util.Appconfig;
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
                            String strJson = response.body().string();
                            Gson gson = new Gson();
                            Login login = gson.fromJson(strJson, Login.class);

                            // ------ lấy dữ liệu người dùng xuống
                            String ten_user = login.getLoginResult().getCustomerName();
                            String phone_user = login.getLoginResult().getPhone().toString();
                            String bienso_user = login.getLoginResult().getListBike().toString();
                            //------
                            // kiểm tra số điện thoại có đúng không
                            if (soDienThoai.equals(phone_user)) {
                                // đưa dữu liệu người dùng vào lưu trữ
                                Appconfig.setTenUser(ten_user, MainActivity.this);
                                Appconfig.setPhoneNumber(phone_user, MainActivity.this);
                                Appconfig.setBiensoUser(bienso_user, MainActivity.this);
                                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                                startActivity(intent);
                                finish();
                            } else {
                                Toast.makeText(MainActivity.this, "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(MainActivity.this, "Vui lòng kiểm tra lại kết nối mạng", Toast.LENGTH_SHORT).show();

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
