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
import com.example.project_myvntour.R;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

public class AdapterListKhachSanChinh extends RecyclerView.Adapter<AdapterListKhachSanChinh.ViewHolder> {
    private List<KhachSan> list;
    private Context mContext;
    Listernaer mListerner;
    private NumberFormat fm = new DecimalFormat("#,###");
    public interface Listernaer{
        public void onClickListChinh(View v , int position);
    }
    public AdapterListKhachSanChinh (Context context , Listernaer mListerner){
        this.mContext = context;
        this.mListerner = mListerner;
    }
    public void setDataListChinh(List<KhachSan> list){
        this.list = list;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemkhachsanchinh , parent , false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull  AdapterListKhachSanChinh.ViewHolder holder, int position) {
        KhachSan khach = list.get(position);
        if(khach != null) {
            holder.ivAnhKhachSan.setImageResource(khach.getImages());
            holder.tvTenKhachSan.setText(khach.getTenKhachSan());
            holder.tvSoTien.setText(fm.format(khach.getGiaThue()) + " VND/Year");
            holder.tvSoPhongBathRoom.setText(khach.getSoLUongPHongTam() + " BathRoom");
            holder.tvSoPhongBedRoom.setText(khach.getSoluongPHongNGu() + " BedRoom");
            holder.itemView.setOnClickListener(v->{
                mListerner.onClickListChinh(v , position);
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
        private TextView tvSoTien;
        private TextView tvSoPhongBedRoom;
        private TextView tvSoPhongBathRoom;
        public ViewHolder(@NonNull  View itemView) {
            super(itemView);


            ivAnhKhachSan = (ImageView)itemView. findViewById(R.id.ivAnhKhachSan);
            tvTenKhachSan = (TextView)itemView. findViewById(R.id.tvTenKhachSan);
            tvSoTien = (TextView) itemView.findViewById(R.id.tvSoTien);
            tvSoPhongBedRoom = (TextView) itemView.findViewById(R.id.tvSoPhongBedRoom);
            tvSoPhongBathRoom = (TextView) itemView.findViewById(R.id.tvSoPhongBathRoom);

        }
    }
}
