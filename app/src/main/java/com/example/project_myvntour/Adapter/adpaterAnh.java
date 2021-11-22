package com.example.project_myvntour.Adapter;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.project_myvntour.R;

import java.util.ArrayList;
import java.util.List;

public class adpaterAnh extends RecyclerView.Adapter<adpaterAnh.holder>{
    Context context;
    List<byte[]> list;
    listenner listenner;
    private int row_index = -1;
    private boolean selected = true;
    private boolean check = true;
    public interface listenner{
        public void onclick(View v, int postion);
    }

    public adpaterAnh(Context context, List<byte[]> list, adpaterAnh.listenner listenner) {
        this.context = context;
        this.list = list;
        this.listenner = listenner;
    }

    @Override
    public holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.custom,parent,false));
    }

    @Override
    public void onBindViewHolder( holder holder, int position) {

        holder.imageView.setImageBitmap(BitmapFactory.decodeByteArray(list.get(position) , 0 , list.get(position).length));
        if(check){
            check = false;
        }
        holder.itemView.setOnClickListener(v->{
            listenner.onclick(v , position);
            row_index =position;
            notifyDataSetChanged();

        });
        if(selected){
            if(position == 0){
                holder.itemView.setBackgroundColor(Color.parseColor("#FFFF00"));
            }
            selected = false;
        }else{
            if(row_index==position){
                holder.itemView.setBackgroundColor(Color.parseColor("#FFFF00"));
            }
            else
            {
                holder.itemView.setBackgroundColor(Color.parseColor("#000000"));
            }
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class holder extends RecyclerView.ViewHolder {
        ImageView imageView;
        View view;
        public holder( View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.img);
            view = itemView.findViewById(R.id.nen);
        }
    }
}
