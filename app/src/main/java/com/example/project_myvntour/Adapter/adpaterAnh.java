package com.example.project_myvntour.Adapter;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.project_myvntour.R;

import java.util.List;

public class adpaterAnh extends RecyclerView.Adapter<adpaterAnh.holder>{
    Context context;
    List<byte[]> list;
    listenner listenner;
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
        holder.itemView.setOnClickListener(v->{
            listenner.onclick(v , position);
        });
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
