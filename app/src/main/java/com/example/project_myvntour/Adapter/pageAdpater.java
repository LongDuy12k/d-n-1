package com.example.project_myvntour.Adapter;

import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.project_myvntour.R;

import java.util.List;


public class pageAdpater extends PagerAdapter {
    List<byte[]> list;

    public pageAdpater(List<byte[]> list) {
        this.list = list;
    }

    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.item_slide,container,false);
        ImageView imageView = view.findViewById(R.id.img_view);
        imageView.setImageBitmap(BitmapFactory.decodeByteArray(list.get(position) , 0  ,list.get(position).length ));

        container.addView(view);
        return view;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

}
