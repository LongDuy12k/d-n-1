package com.example.project_myvntour.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_myvntour.ActivityMaintain.InfoRoomActivity;
import com.example.project_myvntour.Database.SelectAll;
import com.example.project_myvntour.Mode.Phong;
import com.example.project_myvntour.R;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

public class AdapterPhong extends RecyclerView.Adapter<AdapterPhong.holder> implements AdapterSidePhong.Listionner {
    Context context;
    List<Phong> listPhong;
    SelectAll mselectAll;
    Listener mlistener;
    private AdapterSidePhong mAdapterSidePhong;
    public AdapterPhong(Context context, Listener mlistener) {
        this.context = context;
        this.mlistener = mlistener;
        mselectAll = new SelectAll(context);

    }

    public void setDataListPhong(List<Phong> listPhong){
        this.listPhong = listPhong;
    }

    @Override
    public void onClick(View view, int position) {
        Phong x = listPhong.get(position);
        Intent i = new Intent(context, InfoRoomActivity.class);
        i.putExtra("phong" , x);
        context.startActivity(i);
    }

    public interface Listener{
        public void DatPhong(View v, int position);
        public void AnhPhong(View v, int position);
        public void onItemClick(View v, int position);

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
        mAdapterSidePhong= new AdapterSidePhong(list , this);

        holder.imgPhong.setSliderAdapter(mAdapterSidePhong);
        holder.imgPhong.setIndicatorAnimation(IndicatorAnimationType.THIN_WORM); //set indicator animation by using IndicatorAnimationType. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        holder.imgPhong.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        holder.imgPhong.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        holder.imgPhong.setIndicatorSelectedColor(Color.WHITE);
        holder. imgPhong.setIndicatorUnselectedColor(Color.GRAY);
        holder. imgPhong.setScrollTimeInSec(4); //set scroll delay in seconds :
        holder.imgPhong.startAutoCycle();
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
        holder.btnDat.setOnClickListener(view ->
        {
            mlistener.DatPhong(view , position);
        }); holder.itemView.setOnClickListener(view ->
        {
            mlistener.onItemClick(view , position);
        });

    }

    @Override
    public int getItemCount() {
        return listPhong.size();
    }

    public class holder extends RecyclerView.ViewHolder{
       // private ImageView imgPhong;
        SliderView sliderView;
        private TextView tvTenPhong;
        private TextView tvSoNguoi;
        private LinearLayout liTreEm;
        private TextView tvSoTre;
        private TextView tvGiuong;
        private TextView tvDienTich;
        private TextView tvGia;
        private Button btnDat;
        private SliderView imgPhong;
        public holder(@NonNull View itemView) {

            super(itemView);
//            sliderView = itemView.findViewById(R.id.image_slider_Phong);
        //    imgPhong = itemView.findViewById(R.id.img_phong);
            tvTenPhong = itemView.findViewById(R.id.tv_tenPhong);
            tvSoNguoi = itemView.findViewById(R.id.tv_soNguoi);
            liTreEm = itemView.findViewById(R.id.li_treEm);
            tvSoTre = itemView.findViewById(R.id.tv_soTre);
            tvGiuong = itemView.findViewById(R.id.tv_giuong);
            tvDienTich = itemView.findViewById(R.id.tv_dienTich);
            tvGia = itemView.findViewById(R.id.tv_gia);
            btnDat = itemView.findViewById(R.id.btn_dat);


            imgPhong = (SliderView) itemView.findViewById(R.id.img_phong);

        }
    }
}
