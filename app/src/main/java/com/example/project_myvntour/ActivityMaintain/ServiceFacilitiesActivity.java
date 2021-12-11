package com.example.project_myvntour.ActivityMaintain;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.project_myvntour.Adapter.AdapterServiceFaci;
import com.example.project_myvntour.Database.SelectAll;
import com.example.project_myvntour.Mode.KhachSan;
import com.example.project_myvntour.Mode.TIENNGHIDV;
import com.example.project_myvntour.R;
import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;
import java.util.List;

public class ServiceFacilitiesActivity extends AppCompatActivity {
    private MaterialToolbar toolBar2;
    private LinearLayout listtiennghi;
    private LinearLayout liWifiTaiSanh;
    private LinearLayout liWifiTaiPhong;
    private LinearLayout libeboi;
    private LinearLayout lispa;
    private LinearLayout liDauXe;
    private LinearLayout liVatNuoi;
    private LinearLayout limaydieuhoa;
    private LinearLayout linhahang;
    private LinearLayout likhachsan;
    private LinearLayout ligym;
    private RecyclerView recyclerView;
    private KhachSan khach;
    private AdapterServiceFaci mAdapterServiceFaci;
    private List<TIENNGHIDV>list;
    private SelectAll mSelectAll;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_facilities);


        toolBar2 = (MaterialToolbar) findViewById(R.id.tool_bar2);
        setSupportActionBar(toolBar2);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("Cơ sở dịch vụ");
        listtiennghi = (LinearLayout) findViewById(R.id.listtiennghi);
        liWifiTaiSanh = (LinearLayout) findViewById(R.id.liWifiTaiSanh);
        liWifiTaiPhong = (LinearLayout) findViewById(R.id.liWifiTaiPhong);
        libeboi = (LinearLayout) findViewById(R.id.libeboi);
        lispa = (LinearLayout) findViewById(R.id.lispa);
        liDauXe = (LinearLayout) findViewById(R.id.liDauXe);
        liVatNuoi = (LinearLayout) findViewById(R.id.liVatNuoi);
        limaydieuhoa = (LinearLayout) findViewById(R.id.limaydieuhoa);
        linhahang = (LinearLayout) findViewById(R.id.linhahang);
        likhachsan = (LinearLayout) findViewById(R.id.likhachsan);
        ligym = (LinearLayout) findViewById(R.id.ligym);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        khach =(KhachSan) this.getIntent().getSerializableExtra("khachsan");
        list = new ArrayList<>();
        mSelectAll = new SelectAll(this);
        list = mSelectAll.getListTIenIchTheoIdKhachSan(khach.getId());
        mAdapterServiceFaci = new AdapterServiceFaci( list, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this , LinearLayoutManager.VERTICAL , false));
        recyclerView.setAdapter(mAdapterServiceFaci);
        showData();

    }

    @Override
    public boolean onSupportNavigateUp() {
        this.onBackPressed();
        return super.onSupportNavigateUp();
    }
    public void showData(){
        if(khach.getWifiPhong() == 0){
            liWifiTaiPhong.setAlpha(0.4f);
        }
        if(khach.getWifiSanh() == 0){
            liWifiTaiSanh.setAlpha(0.4f);
        }
        if(khach.getBeBoi() == 0){
            libeboi.setAlpha(0.4f);
        }
        if(khach.getSpa() == 0){
            lispa.setAlpha(0.4f);
        }
        if(khach.getDauXe() == 0){
            liDauXe.setAlpha(0.4f);
        }
        if(khach.getVatNuoi() == 0){
            liVatNuoi.setAlpha(0.4f);
        }
        if(khach.getDieuHoa() ==0){
            limaydieuhoa.setAlpha(0.4f);
        }
        if(khach.getNhaHang() ==0){
            linhahang.setAlpha(0.4f);
        }

         if(khach.getBar() ==0){
            likhachsan.setAlpha(0.4f);
        }

         if(khach.getGym() ==0){
            ligym.setAlpha(0.4f);
        }
    }
}