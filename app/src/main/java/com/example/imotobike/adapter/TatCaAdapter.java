package com.example.imotobike.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.imotobike.R;
import com.example.imotobike.model.TatCa;
import com.example.imotobike.model.TatCaResult;

import java.util.ArrayList;
import java.util.List;

public class TatCaAdapter extends RecyclerView.Adapter<TatCaAdapter.ViewHolder> {
    Context context;
    List<TatCaResult> data;

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public List<TatCaResult> getData() {
        return data;
    }

    public void setData(List<TatCaResult> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_tat_ca_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TatCaResult tatCaResult = data.get(position);
        holder.tvTitle.setText(tatCaResult.getMaterialName());
        holder.tvGia.setText(tatCaResult.getPrice().toString());

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvGia;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_tat_ca_title);
            tvGia = itemView.findViewById(R.id.tv_tat_ca_gia);
        }
    }
}

