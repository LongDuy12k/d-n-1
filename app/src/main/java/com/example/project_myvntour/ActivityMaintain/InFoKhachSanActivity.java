package com.example.project_myvntour.ActivityMaintain;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project_myvntour.Mode.KhachSan;
import com.example.project_myvntour.R;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.appbar.MaterialToolbar;

import de.hdodenhof.circleimageview.CircleImageView;

public class InFoKhachSanActivity extends AppCompatActivity {
    private CardView contenTOp;
    private MaterialToolbar toolBar2;
    private ImageView ivAnhKhachSan;
    private TextView tvTenKhachSan;
    private TextView tvDiaChi;
    private TextView tvSoPhongBedRoom;
    private TextView tvSoPhongBathRoom;
    private ImageView imsao1;
    private ImageView imsao2;
    private ImageView imsao3;
    private ImageView imsao4;
    private ImageView imsao5;
    private TextView tvSeeMoerTiennghi;
    private RecyclerView listtiennghi;
    private TextView tvTieudD;
    private TextView tvSeeMoerChinhSachVeSinh;
    private TextView tvChinhsSach;
    private LinearLayout contenbacsi;
    private CircleImageView hinhanhquanli;
    private TextView tenQuanLi;
    private ImageButton btLogin;
    private RecyclerView listimageanh;
    private TextView timeNhan;
    private TextView timeTra;
    private TextView GiaMoPhong;
    private Button btnRentNow;
    private KhachSan khach;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_fo_khach_san);



        contenTOp = (CardView) findViewById(R.id.contenTOp);
        toolBar2 = (MaterialToolbar) findViewById(R.id.tool_bar2);
        ivAnhKhachSan = (ImageView) findViewById(R.id.ivAnhKhachSan);
        tvTenKhachSan = (TextView) findViewById(R.id.tvTenKhachSan);
        tvDiaChi = (TextView) findViewById(R.id.tvDiaChi);
        tvSoPhongBedRoom = (TextView) findViewById(R.id.tvSoPhongBedRoom);
        tvSoPhongBathRoom = (TextView) findViewById(R.id.tvSoPhongBathRoom);
        imsao1 = (ImageView) findViewById(R.id.imsao1);
        imsao2 = (ImageView) findViewById(R.id.imsao2);
        imsao3 = (ImageView) findViewById(R.id.imsao3);
        imsao4 = (ImageView) findViewById(R.id.imsao4);
        imsao5 = (ImageView) findViewById(R.id.imsao5);
        tvSeeMoerTiennghi = (TextView) findViewById(R.id.tvSeeMoerTiennghi);
        listtiennghi = (RecyclerView) findViewById(R.id.listtiennghi);
        tvTieudD = (TextView) findViewById(R.id.tvTieudD);
        tvSeeMoerChinhSachVeSinh = (TextView) findViewById(R.id.tvSeeMoerChinhSachVeSinh);
        tvChinhsSach = (TextView) findViewById(R.id.tvChinhsSach);
        contenbacsi = (LinearLayout) findViewById(R.id.contenbacsi);
        hinhanhquanli = (CircleImageView) findViewById(R.id.hinhanhquanli);
        tenQuanLi = (TextView) findViewById(R.id.tenQuanLi);
        btLogin = (ImageButton) findViewById(R.id.btLogin);
        listimageanh = (RecyclerView) findViewById(R.id.listimageanh);
        timeNhan = (TextView) findViewById(R.id.timeNhan);
        timeTra = (TextView) findViewById(R.id.timeTra);
        GiaMoPhong = (TextView) findViewById(R.id.GiaMoPhong);
        btnRentNow = (Button) findViewById(R.id.btnRentNow);


        setSupportActionBar(toolBar2);
        toolBar2.setNavigationIcon(R.drawable.ic_baseline_arrow_back_ios_new_24);
        toolBar2.setBackground(null);
        toolBar2.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        getSupportActionBar().setTitle("");
        khach = new KhachSan();


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.itemboomak, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.iconbookmak) {
            if (khach.getTrangThaiLuu() == 0) {
                khach.setTrangThaiLuu(1);
                item.setIcon(R.drawable.ic_baseline_bookmark_24_trang_full);
            }else if(khach.getTrangThaiLuu() == 1){
                khach.setTrangThaiLuu(0);
                item.setIcon(R.drawable.ic_baseline_bookmark_border_24_khungtranglon);
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}