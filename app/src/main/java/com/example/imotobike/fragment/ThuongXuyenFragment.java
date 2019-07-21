package com.example.imotobike.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.imotobike.IMotoAPI.API;
import com.example.imotobike.R;
import com.example.imotobike.adapter.ThuongXuyenAdapter;
import com.example.imotobike.model.Thuongxuyen;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * A simple {@link Fragment} subclass.
 */
public class ThuongXuyenFragment extends Fragment {
    View view;
    RecyclerView rvThuongXuyen;

    public ThuongXuyenFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_thuong_xuyen, container, false);
        init();
        configRv();
        return view;
    }

    private void configRv() {
        GetThuongXuyen getThuongXuyen = new
                GetThuongXuyen("28B1-0009", "", "1");
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://45.118.144.19:1904/api/")
                .build();
        retrofit.create(API.class).getThuongXuyen(getThuongXuyen).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
                rvThuongXuyen.setLayoutManager(layoutManager);

                try {
                    String strJson = response.body().string();
                    Gson gson = new Gson();
                    Thuongxuyen thuongxuyen = gson.fromJson(strJson, Thuongxuyen.class);
                    ThuongXuyenAdapter adapter = new ThuongXuyenAdapter();
                    adapter.setContext(getContext());
                    adapter.setData(thuongxuyen.getThuongXuyenResults());
                    rvThuongXuyen.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
                    rvThuongXuyen.setAdapter(adapter);

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }


            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(getContext(), "Lấy dữ liệu thất bại, vui lòng kiểm tra lại kết nối mạng", Toast.LENGTH_SHORT).show();

            }
        });


    }

    private void init() {
        rvThuongXuyen = view.findViewById(R.id.rv_thuong_xuyen);
    }

    class GetThuongXuyen {
        String BikeID, SearchKey, Page;

        public GetThuongXuyen(String bikeID, String searchKey, String page) {
            BikeID = bikeID;
            SearchKey = searchKey;
            Page = page;
        }
    }

}
