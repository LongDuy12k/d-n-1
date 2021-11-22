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

import java.util.List;

public class NearFromYouActivity2 extends AppCompatActivity implements AdapterNearFromYou.Listernaer{
    private List<KhachSan> list;
    private RecyclerView recyclerView;
    private AdapterNearFromYou adapterNearFromYou;
    private MaterialToolbar toolBar2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_near_from_you2);
        recyclerView = findViewById(R.id.recyclerview);


        toolBar2 = (MaterialToolbar) findViewById(R.id.tool_bar2);
        setSupportActionBar(toolBar2);
        toolBar2.setNavigationIcon(R.drawable.ic_baseline_close_24);
        toolBar2.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        getSupportActionBar().setTitle("Near From You");

        SelectAll selectAll = new SelectAll(this);
        list= selectAll.getListKhachSan();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapterNearFromYou = new AdapterNearFromYou(this,this);
        adapterNearFromYou.setData(list);
        recyclerView.setAdapter(adapterNearFromYou);
    }

    @Override
    public void onClick(View v, int position) {
        KhachSan khach = list.get(position);
        Intent intent = new Intent(NearFromYouActivity2.this , InFoKhachSanActivity.class);
        intent.putExtra("khachsan" , khach);
        startActivity(intent);

    }
}