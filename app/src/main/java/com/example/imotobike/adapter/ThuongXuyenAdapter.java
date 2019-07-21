package com.example.imotobike.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.imotobike.R;
import com.example.imotobike.model.ThuongXuyenResult;

import java.util.List;

public class ThuongXuyenAdapter extends RecyclerView.Adapter<ViewHolder> {
    Context context;
    List<ThuongXuyenResult> data;

    public void setContext(Context context) {
        this.context = context;
    }

    public void setData(List<ThuongXuyenResult> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_thuong_xuyen_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ThuongXuyenResult thuongXuyenResult = data.get(position);
        holder.tvTitle.setText(thuongXuyenResult.getMaterialName());
        holder.tvGia.setText(thuongXuyenResult.getPrice().toString());


    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}

class ViewHolder extends RecyclerView.ViewHolder {
    TextView tvTitle, tvGia;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        tvTitle = itemView.findViewById(R.id.tv_thuong_xuyen_title);
        tvGia = itemView.findViewById(R.id.tv_thuong_xuyen_gia);
    }
}
