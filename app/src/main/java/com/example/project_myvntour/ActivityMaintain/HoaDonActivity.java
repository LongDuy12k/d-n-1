package com.example.project_myvntour.ActivityMaintain;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.project_myvntour.Adapter.AdapterHoaDon;
import com.example.project_myvntour.Database.SelectAll;
import com.example.project_myvntour.Mode.HoaDon;
import com.example.project_myvntour.R;
import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;
import java.util.List;

public class HoaDonActivity extends AppCompatActivity implements AdapterHoaDon.Listionner {
    private MaterialToolbar toolBar2;
    private RecyclerView recyclerview;
    private List<HoaDon> list;
    private SelectAll mSelectAll;
    private AdapterHoaDon mAdapterHoaDon;
    private int idUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoa_don);

        toolBar2 = (MaterialToolbar) findViewById(R.id.tool_bar2);
        setSupportActionBar(toolBar2);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("Hóa Đơn");
        recyclerview = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerview.setLayoutManager(new LinearLayoutManager(this , LinearLayoutManager.VERTICAL , false));
        list = new ArrayList<>();
        mSelectAll = new SelectAll(this);mAdapterHoaDon = new AdapterHoaDon(this , this);
        idUsername = LoginActivity.id;
        loadDataa();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        loadDataa();
    }
    public void loadDataa(){
        list = mSelectAll.getListHoaDaon(idUsername);
        mAdapterHoaDon.setDate(list);
        recyclerview.setAdapter(mAdapterHoaDon);
    }

    @Override
    public void onClick(View view, int position) {
        HoaDon hoa = list.get(position);
        Intent i = new Intent(HoaDonActivity.this , HoaDonChiTietActivity.class);
        i.putExtra("hoadon" , hoa);
        startActivity(i);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}