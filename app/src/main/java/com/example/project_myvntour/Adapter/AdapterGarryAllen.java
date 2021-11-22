package com.example.project_myvntour.Adapter;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_myvntour.R;

import java.util.List;

public class AdapterGarryAllen extends RecyclerView.Adapter<AdapterGarryAllen.ViewHolder>{
    private List<byte[]> list;
    private Context context;
    private Listionner mListerner;
    public interface Listionner{
        public void clickShowImageView(View view , int position);

    }
    public void setData(List<byte[]> data){
        this.list = data;
    }
    public AdapterGarryAllen(Context context, Listionner mListerner) {
        this.context = context;
        this.mListerner = mListerner;
    }
    @NonNull
    @Override
    public AdapterGarryAllen.ViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_photo_gallery, parent , false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull  AdapterGarryAllen.ViewHolder holder, int position) {
        holder.ivAnhKhachSan.setImageBitmap(BitmapFactory.decodeByteArray(list.get(position) , 0 , list.get(position).length));
        holder.itemView.setOnClickListener(v->{
            mListerner.clickShowImageView(v , position);
        });
        if(position == 3){
            if( list.size() -4 == 0){
                holder.btnSoLuong.setVisibility(View.GONE);
            }else{
                holder.btnSoLuong.setVisibility(View.VISIBLE);
                holder.btnSoLuong.setText("+"+(list.size() -4));
            }

        }
    }
    @Override
    public int getItemCount() {
        return list.size() < 4 ? list.size() :4;
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivAnhKhachSan;
        private TextView btnSoLuong;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);


            ivAnhKhachSan = (ImageView)itemView. findViewById(R.id.ivAnhKhachSan);
            btnSoLuong = (TextView)itemView. findViewById(R.id.btnSoLuong);


        }
    }
}
