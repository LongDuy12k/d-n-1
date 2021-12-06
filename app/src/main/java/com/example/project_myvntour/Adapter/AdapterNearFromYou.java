package com.example.project_myvntour.Adapter;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_myvntour.Database.SelectAll;
import com.example.project_myvntour.Mode.KhachSan;
import com.example.project_myvntour.R;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

public class AdapterNearFromYou extends RecyclerView.Adapter<AdapterNearFromYou.ViewHolder>{
    private List<KhachSan> list;
    private Context mContext;
    Listernaer mListerner;
    public SelectAll mSelectAll;
    private NumberFormat fm = new DecimalFormat("#,###");
    public interface Listernaer{
        public void onClick(View v , int position);
    }
    public void setData(List<KhachSan> list){
        this.list = list;
    }
    public AdapterNearFromYou(Context mContext, Listernaer mListerner) {
        this.mContext = mContext;
        this.mListerner = mListerner;
        mSelectAll = new SelectAll(mContext);
    }
    @NonNull
    @Override
    public AdapterNearFromYou.ViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_nearfromyou,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull  AdapterNearFromYou.ViewHolder holder, int position) {
        KhachSan khach =list.get(position);
        if (khach != null){
            holder.ivAnhKhachSan.setImageResource(khach.getImages());
            List<byte[]> listPhot = mSelectAll.getListPhotById(khach.getId());
            if(listPhot.size() >1) {
                holder.ivAnhKhachSan.setImageBitmap(BitmapFactory.decodeByteArray(listPhot.get(0) , 0 , listPhot.get(0).length));
            }
            holder.tvTenKhachSan.setText(khach.getTenKhachSan());
            if(khach.getSoSao()==1){
                holder.imsao2.setVisibility(View.INVISIBLE);
                holder.imsao3.setVisibility(View.INVISIBLE);
                holder.imsao4.setVisibility(View.INVISIBLE);
                holder.imsao5.setVisibility(View.INVISIBLE);
            }else if(khach.getSoSao()==2){
                holder.imsao3.setVisibility(View.INVISIBLE);
                holder.imsao4.setVisibility(View.INVISIBLE);
                holder.imsao5.setVisibility(View.INVISIBLE);
            }else if(khach.getSoSao()==3){
                holder.imsao4.setVisibility(View.INVISIBLE);
                holder.imsao5.setVisibility(View.INVISIBLE);
            }else if(khach.getSoSao()==4){
                holder.imsao5.setVisibility(View.INVISIBLE);
            }

            holder.tvTenTheLoai.setText(khach.getLoaisachsan());
            holder.tvLoaiKhachSan.setText(khach.getDiaDiem());
            holder.tienthue.setText(fm.format(khach.getGiaThue()) + "VND");
            holder.itemView.setOnClickListener(v->{
                mListerner.onClick(v , position);
            });
            if(khach.getTrangThaiLuu() == 1){
                holder.iconLuu.setBackgroundResource(R.drawable.ic_baseline_bookmark_24);
            }else{

                holder.iconLuu.setBackgroundResource(R.drawable.ic_baseline_bookmark_border_24);
            }

            holder.iconLuu.setOnClickListener(v->{
                if (khach.getTrangThaiLuu() == 0) {
                    khach.setTrangThaiLuu(1);
                    holder.iconLuu.setBackgroundResource(R.drawable.ic_baseline_bookmark_24_trang_full);
                    holder.iconLuu.setSelected(true);

                }else if(khach.getTrangThaiLuu() == 1){
                    khach.setTrangThaiLuu(0);
                    holder.iconLuu.setBackgroundResource(R.drawable.bookmark_trang);
                    holder.iconLuu.setSelected(false);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivAnhKhachSan;
        private ImageView iconLuu;
        private TextView tvTenKhachSan;
        private LinearLayout contensao;
        private ImageView imsao1;
        private ImageView imsao2;
        private ImageView imsao3;
        private ImageView imsao4;
        private ImageView imsao5;
        private TextView tvTenTheLoai;
        private TextView tvLoaiKhachSan;
        private TextView tienthue;
        public ViewHolder(@NonNull  View itemView) {
            super(itemView);


            ivAnhKhachSan = (ImageView)itemView. findViewById(R.id.ivAnhKhachSan);
            iconLuu = (ImageView) itemView.findViewById(R.id.iconLuu);
            tvTenKhachSan = (TextView)itemView. findViewById(R.id.tvTenKhachSan);
            contensao = (LinearLayout)itemView. findViewById(R.id.contensao);
            imsao1 = (ImageView) itemView.findViewById(R.id.imsao1);
            imsao2 = (ImageView) itemView.findViewById(R.id.imsao2);
            imsao3 = (ImageView) itemView.findViewById(R.id.imsao3);
            imsao4 = (ImageView)itemView. findViewById(R.id.imsao4);
            imsao5 = (ImageView)itemView.findViewById(R.id.imsao5);
            tvTenTheLoai = (TextView) itemView.findViewById(R.id.tvTenTheLoai);
            tvLoaiKhachSan = (TextView) itemView.findViewById(R.id.tvLoaiKhachSan);
            tienthue = (TextView)itemView. findViewById(R.id.tienthue);

        }
    }
}
