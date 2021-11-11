package com.example.project_myvntour.Adapter;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_myvntour.Database.SelectAll;
import com.example.project_myvntour.Mode.KhachSan;
import com.example.project_myvntour.R;

import java.util.List;

public class AdapterItemKhachSanMap extends RecyclerView.Adapter<AdapterItemKhachSanMap.ViewHolder>{

    private List<KhachSan> list;
    private Context mContext;
    Listernaer mListerner;
    private SelectAll mSelectAll;
    public interface Listernaer{
        public void onClickKhachSanMap(View v , int position);
    }
    public AdapterItemKhachSanMap (Context context , Listernaer mListerner){
        this.mContext = context;
        this.mListerner = mListerner;
       this.mSelectAll = new SelectAll(context);
    }
    public void setData(List<KhachSan> list){
        this.list = list;
    }

    @NonNull
    @Override
    public AdapterItemKhachSanMap.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemkhachsanmap , parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        KhachSan khacsan = list.get(position);
        if(khacsan != null){
            List<byte[]> listphot = mSelectAll.getListPhotoById(khacsan.getId());
            if(listphot.size() >=1 ){// doạn này lên check phòng trường hợp không lấy đc anh
                holder.ivAnhKhachSan.setImageBitmap(BitmapFactory.decodeByteArray(listphot.get(0) , 0 , listphot.get(0).length));
            }
            holder.tvTenKhachSan.setText(khacsan.getTenKhachSan());
            holder.tvLoaiKhachSan.setText(khacsan.getLoaisachsan());
            holder.itemView.setOnClickListener(v->{
                mListerner.onClickKhachSanMap(v , position);
            });
            if(khacsan.getSoSao()==1){
                holder.imsao2.setVisibility(View.INVISIBLE);
                holder.imsao3.setVisibility(View.INVISIBLE);
                holder.imsao4.setVisibility(View.INVISIBLE);
                holder.imsao5.setVisibility(View.INVISIBLE);
            }else if(khacsan.getSoSao()==2){
                holder.imsao3.setVisibility(View.INVISIBLE);
                holder.imsao4.setVisibility(View.INVISIBLE);
                holder.imsao5.setVisibility(View.INVISIBLE);
            }else if(khacsan.getSoSao()==3){
                holder.imsao4.setVisibility(View.INVISIBLE);
                holder.imsao5.setVisibility(View.INVISIBLE);
            }else if(khacsan.getSoSao()==4){
                holder.imsao5.setVisibility(View.INVISIBLE);
            }
            if(khacsan.getTrangThaiLuu() == 1){
                holder.imgSaveLocation.setBackgroundResource(R.drawable.ic_baseline_bookmark_24);
            }else{

                holder.imgSaveLocation.setBackgroundResource(R.drawable.ic_baseline_bookmark_border_24);
            }

            holder.imgSaveLocation.setOnClickListener(v->{
                if (khacsan.getTrangThaiLuu() == 0) {
                    khacsan.setTrangThaiLuu(1);
                    holder.imgSaveLocation.setBackgroundResource(R.drawable.ic_baseline_bookmark_24);
                    holder.imgSaveLocation.setSelected(true);

                }else if(khacsan.getTrangThaiLuu() == 1){
                    khacsan.setTrangThaiLuu(0);
                    holder.imgSaveLocation.setBackgroundResource(R.drawable.ic_baseline_bookmark_border_24);
                    holder.imgSaveLocation.setSelected(false);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private RelativeLayout rating;
        private ImageView imsao1;
        private ImageView imsao2;
        private ImageView imsao3;
        private ImageView imsao4;
        private ImageView imsao5;
        private ImageView ivAnhKhachSan;
        private ImageView imgSaveLocation;
        private TextView tvTenKhachSan;
        private TextView tvLoaiKhachSan;
        public ViewHolder(@NonNull  View itemView) {
            super(itemView);


            rating = (RelativeLayout)itemView. findViewById(R.id.rating);
            imsao1 = (ImageView) itemView.findViewById(R.id.imsao1);
            imsao2 = (ImageView) itemView.findViewById(R.id.imsao2);
            imsao3 = (ImageView) itemView.findViewById(R.id.imsao3);
            imsao4 = (ImageView) itemView.findViewById(R.id.imsao4);
            imsao5 = (ImageView)itemView. findViewById(R.id.imsao5);
            ivAnhKhachSan = (ImageView)itemView. findViewById(R.id.ivAnhKhachSan);
            imgSaveLocation = (ImageView) itemView.findViewById(R.id.imgSaveLocation);
            tvTenKhachSan = (TextView) itemView.findViewById(R.id.tvTenKhachSan);
            tvLoaiKhachSan = (TextView)itemView. findViewById(R.id.tvLoaiKhachSan);

        }
    }
}
