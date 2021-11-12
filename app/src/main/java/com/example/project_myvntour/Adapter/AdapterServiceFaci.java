package com.example.project_myvntour.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_myvntour.Mode.TIENNGHIDV;
import com.example.project_myvntour.R;

import java.util.List;

public class AdapterServiceFaci extends RecyclerView.Adapter<AdapterServiceFaci.ViewHolder>{
    private List<TIENNGHIDV> list;
    private Context context;

    public AdapterServiceFaci(List<TIENNGHIDV> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public AdapterServiceFaci.ViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_servicefaci , parent , false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull  AdapterServiceFaci.ViewHolder holder, int position) {
        TIENNGHIDV tien = list.get(position);
        if (tien != null) {
            holder.tenTieuDe.setText(tien.getTenDichVu());
            if(tien.getMote().equals("")){
                holder.view.setVisibility(View.GONE);
                holder.noidung.setVisibility(View.GONE);
            }else{
                holder.noidung.setText(tien.getMote());
            }
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tenTieuDe;
        private TextView noidung;
        private View view;
        public ViewHolder(@NonNull  View itemView) {
            super(itemView);



            view = (View) itemView.findViewById(R.id.view);

            tenTieuDe = (TextView)itemView. findViewById(R.id.tenTieuDe);
            noidung = (TextView) itemView.findViewById(R.id.noidung);

        }
    }
}
