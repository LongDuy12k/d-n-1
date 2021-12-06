package com.example.project_myvntour.Adapter;

import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.project_myvntour.R;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.List;

public class AdapterSidePhong extends SliderViewAdapter<AdapterSidePhong.Holder> {
    private List<byte[]> list;
    public interface Listionner{
        public void onClick(View view , int position);
    }
    private Listionner mListerner;
    @Override
    public AdapterSidePhong.Holder onCreateViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.slider_item,parent,false);
        return new AdapterSidePhong.Holder(view);
    }

    public AdapterSidePhong(List<byte[]> list , Listionner mListerner) {
        this.list = list;
        this.mListerner = mListerner;
    }

    @Override
    public void onBindViewHolder(AdapterSidePhong.Holder viewHolder, int position) {
        viewHolder.imageView.setImageBitmap(BitmapFactory.decodeByteArray(list.get(position) , 0 , list.get(position).length));
        viewHolder.imageView.setOnClickListener(v ->{
            mListerner.onClick(v  , position);
        });


    }

    @Override
    public int getCount() {
        return list.size();
    }

    public class Holder extends SliderViewAdapter.ViewHolder {
        ImageView imageView;
        public Holder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_view);
        }
    }
}
