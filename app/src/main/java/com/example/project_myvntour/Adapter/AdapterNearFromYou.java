package com.example.project_myvntour.Adapter;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AdapterNearFromYou extends RecyclerView.Adapter<AdapterNearFromYou.ViewHolder>{
    @NonNull
    @Override
    public AdapterNearFromYou.ViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull  AdapterNearFromYou.ViewHolder holder, int position) {

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
