package com.example.imotobike.fragment;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.imotobike.IMotoAPI.API;
import com.example.imotobike.R;
import com.example.imotobike.adapter.TatCaAdapter;
import com.example.imotobike.model.TatCa;
import com.example.imotobike.model.TatCaResult;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TatCaFragment extends Fragment {
    View view;
    RecyclerView rvTatCa;


    public TatCaFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_tat_ca, container, false);
        init();
        configRv();
        return view;

    }


    private void configRv() {
        GetTatCa getTatCa = new GetTatCa("28B1-0009", "", "1");
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://45.118.144.19:1904/api/")
                .build();
        retrofit.create(API.class).getAll(getTatCa).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String strJson = response.body().string();
                    Gson gson = new Gson();
                    TatCa tatCa = gson.fromJson(strJson, TatCa.class);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
                    rvTatCa.setLayoutManager(linearLayoutManager);
                    TatCaAdapter adapter = new TatCaAdapter();
                    adapter.setContext(getContext());
                    adapter.setData(tatCa.getTatCaResults());
                    rvTatCa.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
                    rvTatCa.setAdapter(adapter);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(getContext(), "fail", Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void init() {
        rvTatCa = view.findViewById(R.id.rv_tat_ca);
    }

    class GetTatCa {
        String bikeId, SearchKey, Page;

        public GetTatCa(String bikeId, String searchKey, String page) {
            this.bikeId = bikeId;
            SearchKey = searchKey;
            Page = page;
        }
    }
}
