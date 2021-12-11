package com.example.project_myvntour.ActivityMaintain;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.project_myvntour.Adapter.AdapterNearFromYou;
import com.example.project_myvntour.Database.SelectAll;
import com.example.project_myvntour.Mode.KhachSan;
import com.example.project_myvntour.R;
import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;
import java.util.List;

public class BookMartActivity extends AppCompatActivity implements AdapterNearFromYou.Listernaer{
    private MaterialToolbar toolBar2;
    private RecyclerView recyclerview;
    private AdapterNearFromYou mAdapterNearFromYou;
    private List<KhachSan> mList;
    private SelectAll mSelectAll;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_mart);


        toolBar2 = (MaterialToolbar) findViewById(R.id.tool_bar2);
        setSupportActionBar(toolBar2);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("Khách Sạn bạn Yêu Thích");
        recyclerview = (RecyclerView) findViewById(R.id.recyclerview);
        mList = new ArrayList<>();
        mSelectAll = new SelectAll(this);
        mList= mSelectAll.getListKhachSanDaLuu(LoginActivity.id);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        mAdapterNearFromYou = new AdapterNearFromYou(this,this);
        mAdapterNearFromYou.setData(mList);
        recyclerview.setAdapter(mAdapterNearFromYou);
    }

    @Override
    public void onClick(View v, int position) {
        KhachSan khach = mList.get(position);
        Intent intent = new Intent(BookMartActivity.this , InFoKhachSanActivity.class);
        intent.putExtra("khachsan" , khach);
        startActivity(intent);
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}