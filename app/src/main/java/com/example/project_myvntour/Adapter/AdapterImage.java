package com.example.project_myvntour.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_myvntour.R;

import java.util.List;

public class AdapterImage extends RecyclerView.Adapter<AdapterImage.ViewHolder>{
    private List<byte[]> list;
    private Context context;

    public AdapterImage(List<byte[]> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public AdapterImage.ViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemphotofullscrenn, parent , false);
        return new AdapterImage.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull  AdapterImage.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull  View itemView) {
            super(itemView);
        }
    }
}
