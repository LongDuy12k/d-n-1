package com.example.project_myvntour.Adapter;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_myvntour.Database.SelectAll;
import com.example.project_myvntour.Mode.HoaDon;
import com.example.project_myvntour.R;

import org.jetbrains.annotations.NotNull;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class AdapterHoaDon extends RecyclerView.Adapter<AdapterHoaDon.ViewHolder> {
    private Context mContext;
    private List<HoaDon> list;
    private Listionner mListerner;
    private SelectAll mSelectAll;
    private NumberFormat fm = new DecimalFormat("#,###");
    long soDem;
    public interface Listionner{
        public void onClick(View view , int position);
    }

    public AdapterHoaDon(Context mContext, Listionner mListerner) {
        this.mContext = mContext;
        this.mListerner = mListerner;
        mSelectAll = new SelectAll(mContext);
    }
    public void setDate(List<HoaDon> list) {
            this.list = list;
    }
    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hoadon , parent , false);
                return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String ngayNhan = null,ngaytra = null;


        HoaDon a = list.get(position);
        if(a != null){
            List<byte[]> listPhot = mSelectAll.getListPhotById(a.getMaPhong());
            if(listPhot.size() >1) {
                holder.ivAnhKhachSan.setImageBitmap(BitmapFactory.decodeByteArray(listPhot.get(0) , 0 , listPhot.get(0).length));
            }

            System.out.println("zzzzzzzzzzzzzzzzzz " + ngayNhan);
            System.out.println("zzzzzzzzzzzzzzzzzz " + ngaytra);
            holder.tvMaDonHang.setText("MVT" + a.getId());
            holder.tvTenKhachSan.setText(a.getTenKhachSan());
            holder.tvTenPHong.setText("1x " + a.getTenPhong());
            holder.tvSoNguoi.setText(a.getSoNguoiLon() +  " người");
            holder.tvNgayNhanPhong.setText(a.getNgayden());
            holder.tvNgayTra.setText(a.getNgaydi());
            holder.tvTimeNhanPhong.setText(a.getGionhanphong());
            holder.tvTimeTra.setText(a.getGiotraphong());




            try {
                 soDem = getDaysDifference(sdf.parse(String.valueOf(a.getNgayden())) , sdf.parse(String.valueOf(a.getNgaydi())));
                holder.TVsoDem.setText(soDem + "");
            } catch (ParseException e) {
                e.printStackTrace();
            }
           long giachinhthuc = soDem * Long.parseLong(String.valueOf(a.getTongtien()));
            holder.tongtien.setText(fm.format(Long.parseLong(String.valueOf(giachinhthuc))) +" VND");

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
        private TextView tvSonguoi;
        private TextView tvMaDonHang;
        private TextView tvTenKhachSan;
        private TextView tvTenPHong;
        private TextView tvSoNguoi;
        private ImageView ivAnhKhachSan;
        private TextView tvNgayNhanPhong;
        private TextView tvTimeNhanPhong;
        private TextView tvNgayTra;
        private TextView tongtien;
        private TextView tvTimeTra;
        private TextView TVsoDem;
        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);


            tvSonguoi = (TextView) itemView.findViewById(R.id.tvSonguoi);
            tvMaDonHang = (TextView) itemView.findViewById(R.id.tvMaDonHang);
            tvTenKhachSan = (TextView) itemView.findViewById(R.id.tvTenKhachSan);
            tvTenPHong = (TextView)itemView. findViewById(R.id.tvTenPHong);
            tvSoNguoi = (TextView) itemView.findViewById(R.id.tvSoNguoi);
            ivAnhKhachSan = (ImageView) itemView.findViewById(R.id.ivAnhKhachSan);
            tvNgayNhanPhong = (TextView)itemView. findViewById(R.id.tvNgayNhanPhong);
            tvTimeNhanPhong = (TextView) itemView.findViewById(R.id.tvTimeNhanPhong);
            tvNgayTra = (TextView) itemView.findViewById(R.id.tvNgayTra);
            tvTimeTra = (TextView) itemView.findViewById(R.id.tvTimeTra);
            tongtien = (TextView) itemView.findViewById(R.id.tongtien);
            TVsoDem = (TextView) itemView.findViewById(R.id.TVsoDem);

        }
    }
    private long getDaysDifference(Date fromDate,Date toDate) {

        if(fromDate == null || toDate == null)
            return 0;


        int a = Integer.parseInt(DateFormat.format("dd",   fromDate)+"");
        int b = Integer.parseInt(DateFormat.format("dd",   toDate)+"");

        if ( b <= a){
            return Calendar.getInstance().getActualMaximum(Calendar.DAY_OF_MONTH) + b - a;
        }
        return b - a;
    }
}
