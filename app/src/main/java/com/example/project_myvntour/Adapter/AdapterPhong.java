package com.example.project_myvntour.Adapter;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_myvntour.Database.SelectAll;
import com.example.project_myvntour.Mode.Phong;
import com.example.project_myvntour.R;
import com.smarteist.autoimageslider.SliderView;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

public class AdapterPhong extends RecyclerView.Adapter<AdapterPhong.holder> {
    Context context;
    List<Phong> listPhong;
    SelectAll mselectAll;
    Listener mlistener;

    public AdapterPhong(Context context, Listener mlistener) {
        this.context = context;
        this.mlistener = mlistener;
        mselectAll = new SelectAll(context);
    }

    public void setDataListPhong(List<Phong> listPhong){
        this.listPhong = listPhong;
    }

    public interface Listener{
        public void DatPhong(View v, int position);
        public void AnhPhong(View v, int position);

    }
    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_room,parent,false);
        return new holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull holder holder, int position) {
        Phong phong = listPhong.get(position);
        List<byte[]> list = mselectAll.getListAnhPhong(phong.getId_Phong());
        if(list.size()>1){
            holder.imgPhong.setImageBitmap(BitmapFactory.decodeByteArray(list.get(0),0,list.get(0).length));
        }
        if(phong.getTreNho()<=0){
            holder.liTreEm.setVisibility(View.GONE);
        }
        NumberFormat format = new DecimalFormat("#,###"); // chuyển tiển về kiểu 1.000.000
        holder.tvTenPhong.setText(phong.getTenPhong());
        holder.tvDienTich.setText(phong.getDienTich()+"m²");
        holder.tvGia.setText(format.format(phong.getGia())+" VNĐ/đêm");
        holder.tvSoNguoi.setText(phong.getNguoiLon()+" người");
        holder.tvSoTre.setText(phong.getTreNho()+" trẻ nhỏ");
        holder.tvGiuong.setText(phong.getSoGiuong()+" giường");

    }

    @Override
    public int getItemCount() {
        return listPhong.size();
    }

    public class holder extends RecyclerView.ViewHolder{
        private ImageView imgPhong;
        SliderView sliderView;
        private TextView tvTenPhong;
        private TextView tvSoNguoi;
        private LinearLayout liTreEm;
        private TextView tvSoTre;
        private TextView tvGiuong;
        private TextView tvDienTich;
        private TextView tvGia;
        private Button btnDat;
        public holder(@NonNull View itemView) {
            super(itemView);
//            sliderView = itemView.findViewById(R.id.image_slider_Phong);
            imgPhong = itemView.findViewById(R.id.img_phong);
            tvTenPhong = itemView.findViewById(R.id.tv_tenPhong);
            tvSoNguoi = itemView.findViewById(R.id.tv_soNguoi);
            liTreEm = itemView.findViewById(R.id.li_treEm);
            tvSoTre = itemView.findViewById(R.id.tv_soTre);
            tvGiuong = itemView.findViewById(R.id.tv_giuong);
            tvDienTich = itemView.findViewById(R.id.tv_dienTich);
            tvGia = itemView.findViewById(R.id.tv_gia);
            btnDat = itemView.findViewById(R.id.btn_dat);
        }
    }
}
