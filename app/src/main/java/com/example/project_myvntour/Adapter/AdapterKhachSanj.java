package com.example.project_myvntour.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_myvntour.Mode.KhachSan;
import com.example.project_myvntour.Mode.LoaiKhachSanj;
import com.example.project_myvntour.R;

import java.util.List;

public class AdapterKhachSanj extends RecyclerView.Adapter<AdapterKhachSanj.ViewHolder> {
    private List<KhachSan> list;
    private Context mContext;
    Listernaer mListerner;
    public interface Listernaer{
        public void onClick(View v , int position);
    }
    public AdapterKhachSanj (Context context , Listernaer mListerner){
        this.mContext = context;
        this.mListerner = mListerner;
    }
    public void setData(List<KhachSan> list){
        this.list = list;
    }
    @NonNull
    @Override
    public AdapterKhachSanj.ViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemkhachsan ,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull  AdapterKhachSanj.ViewHolder holder, int position) {
        KhachSan khach = list.get(position);
        if(khach != null) {
            holder.ivAnhKhachSan.setImageResource(khach.getImages());
            holder.tvTenKhachSan.setText(khach.getTenKhachSan());
            holder.tvDiaChi.setText(khach.getDiaDiem());
            holder.itemView.setOnClickListener(v->{
                mListerner.onClick(v , position);
            });
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivAnhKhachSan;
        private TextView tvTenKhachSan;
        private TextView tvDiaChi;
        public ViewHolder(@NonNull  View itemView) {
            super(itemView);


            ivAnhKhachSan = (ImageView) itemView.findViewById(R.id.ivAnhKhachSan);
            tvTenKhachSan = (TextView) itemView.findViewById(R.id.tvTenKhachSan);
            tvDiaChi = (TextView) itemView.findViewById(R.id.tvDiaChi);

        }
    }
}
