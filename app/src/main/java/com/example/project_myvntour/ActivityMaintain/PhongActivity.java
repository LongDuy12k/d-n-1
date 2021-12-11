package com.example.project_myvntour.ActivityMaintain;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.project_myvntour.Adapter.AdapterPhong;
import com.example.project_myvntour.Database.SelectAll;
import com.example.project_myvntour.Mode.KhachSan;
import com.example.project_myvntour.Mode.Phong;
import com.example.project_myvntour.R;
import com.google.android.material.appbar.MaterialToolbar;

import java.util.List;

public class PhongActivity extends AppCompatActivity implements AdapterPhong.Listener{
    private List<Phong> list;
    private AdapterPhong adapterPhong;
    private MaterialToolbar toolBar;
    private SelectAll selectAll;
    private RecyclerView recyclerViewPhong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phong);
        recyclerViewPhong = findViewById(R.id.recyclerview_phong);
        selectAll = new SelectAll(this);
        toolBar =findViewById(R.id.tool_bar2);
        setSupportActionBar(toolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        KhachSan khach = (KhachSan) this.getIntent().getSerializableExtra("khachsan");
        getSupportActionBar().setTitle(khach.getTenKhachSan());
        recyclerViewPhong.setLayoutManager(new LinearLayoutManager(this));
        adapterPhong = new AdapterPhong(this,this);
        list = selectAll.getListPhong(khach.getId());
        adapterPhong.setDataListPhong(list);
        recyclerViewPhong.addItemDecoration(new DividerItemDecoration(this,LinearLayoutManager.VERTICAL));
        recyclerViewPhong.setAdapter(adapterPhong);
    }

    @Override
    public void DatPhong(View v, int position) {
        Phong x = list.get(position);
        Intent i = new Intent(this, BookNowActivity.class);
        i.putExtra("phong" , x);
        startActivity(i);
    }

    @Override
    public void AnhPhong(View v, int position) {

    }

    @Override
    public void onItemClick(View v, int position) {
        Phong x = list.get(position);
        Intent i = new Intent(this, InfoRoomActivity.class);
        i.putExtra("phong" , x);
        startActivity(i);
    }

    @Override
    public boolean onSupportNavigateUp() {
        this.onBackPressed();
        return super.onSupportNavigateUp();
    }
}