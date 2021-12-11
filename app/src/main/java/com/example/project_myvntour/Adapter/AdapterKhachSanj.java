package com.example.project_myvntour.Adapter;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_myvntour.Database.SelectAll;
import com.example.project_myvntour.Mode.KhachSan;
import com.example.project_myvntour.Mode.LoaiKhachSanj;
import com.example.project_myvntour.R;

import java.util.ArrayList;
import java.util.List;

public class AdapterKhachSanj extends RecyclerView.Adapter<AdapterKhachSanj.ViewHolder> implements Filterable {
    private List<KhachSan> list;
    private Context mContext;
    Listernaer mListerner;
    public SelectAll mSelectAll;
    private List<KhachSan> listTemp;


    public interface Listernaer{
        public void onClick(View v , int position);
    }
    public AdapterKhachSanj (Context context , Listernaer mListerner){
        this.mContext = context;
        this.mListerner = mListerner;
        mSelectAll= new SelectAll(context);
    }
    public void setData(List<KhachSan> list){
        this.list = list;
        this.listTemp = list;
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
            List<byte[]> listPhot = mSelectAll.getListPhotById(khach.getId());
            if(listPhot.size() >1) {
                holder.ivAnhKhachSan.setImageBitmap(BitmapFactory.decodeByteArray(listPhot.get(0) , 0 , listPhot.get(0).length));
            }
            holder.tvTenKhachSan.setText(khach.getTenKhachSan());
            holder.tvDiaChi.setText(khach.getDiaDiem());
            holder.itemView.setOnClickListener(v->{
                mListerner.onClick(v , position);
            });
        }
    }

    @Override
    public int getItemCount() {
        return  list.size() < 10 ? list.size() :10;
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
    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String query = constraint.toString();
                if(query.isEmpty()){
                    list = listTemp;
                }else {
                    List<KhachSan> list1 = new ArrayList<>();
                    for (KhachSan khachSan : listTemp){
                        if(khachSan.getTenKhachSan().toLowerCase().contains(query.toLowerCase())){
                            list1.add(khachSan);
                        }
                        list = list1;
                    }
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = list;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                list = (List<KhachSan>) results.values;
                notifyDataSetChanged();
            }
        };
    }
}
