package com.example.project_myvntour.ActivityMaintain;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.project_myvntour.Adapter.AdapterAnhPhong;
import com.example.project_myvntour.Adapter.adpaterAnh;
import com.example.project_myvntour.Adapter.pageAdpater;
import com.example.project_myvntour.Database.SelectAll;
import com.example.project_myvntour.Mode.KhachSan;
import com.example.project_myvntour.Mode.Phong;
import com.example.project_myvntour.R;
import com.google.android.material.appbar.MaterialToolbar;

import java.util.List;

public class ImagePhongActivity extends AppCompatActivity implements AdapterAnhPhong.listenner{
    private ViewPager viewPage;
    private TextView tvBd;
    private TextView tvTong;
    private RecyclerView recycle;
    List<byte[]> list;
    private SelectAll mSelectAll;
    private MaterialToolbar toolBar2;
    private AdapterAnhPhong mAdapterAnhPhong;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_phong);
        toolBar2 = (MaterialToolbar) findViewById(R.id.tool_bar2);
        setSupportActionBar(toolBar2);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("");
        toolBar2.setBackground(null);

        viewPage = (ViewPager) findViewById(R.id.viewPage);
        tvBd = (TextView) findViewById(R.id.tv_bd);
        tvTong = (TextView) findViewById(R.id.tv_tong);
        recycle = (RecyclerView) findViewById(R.id.recycle);
        Phong khach = (Phong) getIntent().getSerializableExtra("image");
        mSelectAll = new SelectAll(this);
        list = mSelectAll.getListAnhPhong(khach.getId_Phong());
        mAdapterAnhPhong = new AdapterAnhPhong(this,list,this);
        recycle.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        recycle.setAdapter(mAdapterAnhPhong);
        pageAdpater pageAdpater = new pageAdpater(list);
        viewPage.setAdapter(pageAdpater);
        tvTong.setText(list.size()+"");
        viewPage.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tvBd.setText(position+1+"");
                recycle.smoothScrollToPosition(position);

            }
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
    @Override
    public void onclick(View v, int postion) {
        viewPage.setCurrentItem(postion);
    }

    @Override
    public boolean onSupportNavigateUp() {
        this.onBackPressed();
        return super.onSupportNavigateUp();
    }
}