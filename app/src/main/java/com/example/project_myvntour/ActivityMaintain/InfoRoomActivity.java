package com.example.project_myvntour.ActivityMaintain;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.project_myvntour.Adapter.AdapterSidePhong;
import com.example.project_myvntour.Database.SelectAll;
import com.example.project_myvntour.Mode.Phong;
import com.example.project_myvntour.R;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.appbar.MaterialToolbar;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Calendar;
import java.util.List;

public class InfoRoomActivity extends AppCompatActivity implements AdapterSidePhong.Listionner{
    private AppBarLayout appBar;
    private CollapsingToolbarLayout collapseToolbarLayout;
    private MaterialToolbar toolbar;
    private TextView tvtenPhong;
    private TextView tvSonguoi;
    private TextView tvDienTich;
    private TextView tvMota;
    private TextView tvSoGiuong;
    private TextView tvHoanHuyNGay;
    private TextView tvtienIichPhong;
    private TextView tvSoPhong;
    private TextView tvHutThuoc;
    private TextView tvChinhSachHuyPhong;
    private TextView tvGia;
    private LinearLayout iconDieuhoa;
    private LinearLayout iconTV;
    private LinearLayout iconketantoan;
    private LinearLayout icontulanh;
    private LinearLayout iconbep;
    private LinearLayout iconaban;
    private LinearLayout iconwifi;
    private LinearLayout icDichVu;
    private LinearLayout iconMayGiat;
    private LinearLayout iconMaySaytoc;
    private LinearLayout iconBanLa;
    private LinearLayout IconKhongHutThuoc;
    private LinearLayout onClickImage;
    private LinearLayout IconBonTam;
    private Button btnThem;
    private ImageView anhphong;
    private SelectAll mSelectAll;
    private Phong phong1 ;
    private Phong phong2 ;
    private ImageView soluonganh;
    private NumberFormat format = new DecimalFormat("#,###");
    private String datenow ;
    private AdapterSidePhong mAdapterSidePhong;
    private TextView tvSoLuongAnh;
    private List<byte[]> list;
    private SliderView imateside;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_room);


        appBar = (AppBarLayout) findViewById(R.id.appBar);
        collapseToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapseToolbarLayout);

        collapseToolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandedAppBar);
        collapseToolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapsedAppBar);
        appBar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if ((collapseToolbarLayout.getHeight() + verticalOffset) < (2 * ViewCompat.getMinimumHeight(collapseToolbarLayout))) {

                    toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_ATOP);
                } else {
                    toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
                }
            }
        });


     //   anhphong = (ImageView) findViewById(R.id.anhphong);

        toolbar = (MaterialToolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        tvSonguoi = (TextView) findViewById(R.id.tvSonguoi);
        tvDienTich = (TextView) findViewById(R.id.tvDienTich);
        tvSoPhong = (TextView) findViewById(R.id.tvSoPhong);
        tvMota = (TextView) findViewById(R.id.tvMota);
        tvChinhSachHuyPhong = (TextView) findViewById(R.id.tvChinhSachHuyPhong);
        tvSoGiuong = (TextView) findViewById(R.id.tvSoGiuong);
        tvHoanHuyNGay = (TextView) findViewById(R.id.tvHoanHuyNGay);
        tvtienIichPhong = (TextView) findViewById(R.id.tvtienIichPhong);
        tvHutThuoc = (TextView) findViewById(R.id.tvHutThuoc);
        tvGia = (TextView) findViewById(R.id.tv_gia);
        iconDieuhoa = (LinearLayout) findViewById(R.id.icon_dieuhoa);
        iconTV = (LinearLayout) findViewById(R.id.iconTV);
        iconketantoan = (LinearLayout) findViewById(R.id.iconketantoan);
        icontulanh = (LinearLayout) findViewById(R.id.icontulanh);
        iconbep = (LinearLayout) findViewById(R.id.iconbep);
        iconaban = (LinearLayout) findViewById(R.id.iconaban);
        iconwifi = (LinearLayout) findViewById(R.id.iconwifi);
        icDichVu = (LinearLayout) findViewById(R.id.icDichVu);
        onClickImage = (LinearLayout) findViewById(R.id.onClickImage);
        iconMayGiat = (LinearLayout) findViewById(R.id.iconMayGiat);
        iconMaySaytoc = (LinearLayout) findViewById(R.id.iconMaySaytoc);
        iconBanLa = (LinearLayout) findViewById(R.id.iconBanLa);
        IconKhongHutThuoc = (LinearLayout) findViewById(R.id.IconKhongHutThuoc);
        IconBonTam = (LinearLayout) findViewById(R.id.IconBonTam);
        btnThem = (Button) findViewById(R.id.btnThem);



        imateside = (SliderView) findViewById(R.id.imateside);



        tvSoLuongAnh = (TextView) findViewById(R.id.tvSoLuongAnh);



        mSelectAll = new SelectAll(this);
        phong1 = (Phong) this.getIntent().getSerializableExtra("phong");
        phong2 = (Phong) mSelectAll.getPHongById(phong1.getId_Phong());
        final Calendar cl = Calendar.getInstance();
        datenow = (cl.get(Calendar.DAY_OF_MONTH) +1) + "/" + (cl.get(Calendar.MONTH) + 1) +"/" + cl.get(Calendar.YEAR);
        list = mSelectAll.getListAnhPhong(phong1.getId_Phong());
        mAdapterSidePhong = new AdapterSidePhong(list , this);


        imateside.setSliderAdapter(mAdapterSidePhong);
        imateside.setIndicatorAnimation(IndicatorAnimationType.THIN_WORM); //set indicator animation by using IndicatorAnimationType. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        imateside.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        imateside.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        imateside.setIndicatorSelectedColor(Color.WHITE);
        imateside.setIndicatorUnselectedColor(Color.GRAY);
        imateside.setScrollTimeInSec(4); //set scroll delay in seconds :
        imateside.startAutoCycle();

        btnThem.setOnClickListener(v->{
            Intent i = new Intent(InfoRoomActivity.this, BookNowActivity.class);
            i.putExtra("phong" , phong1);
            startActivity(i);
        });
        shodata();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
    public void shodata(){
        collapseToolbarLayout.setTitle(phong1.getTenPhong());

//        if(list.size()>1){
//            anhphong.setImageBitmap(BitmapFactory.decodeByteArray(list.get(0),0,list.get(0).length));
//        }
        tvSoLuongAnh.setText(list.size() + "");
        tvDienTich.setText(phong1.getDienTich() + " m²");
        tvGia.setText(format.format(phong1.getGia())+" VNĐ/đêm");
        tvMota.setText(phong1.getMoTa());
        tvSoGiuong.setText(phong1.getSoGiuong()+" Giường lớn");
        tvSonguoi.setText((phong1.getNguoiLon() + phong1.getTreNho()) +" người");
        tvHoanHuyNGay.setText("Từ ngày "+datenow);
        tvChinhSachHuyPhong.setText("Bạn sẽ mất phí " +format.format(phong1.getGia()) + " VND từ 12:00 ngày " + datenow);
        tvSoPhong.setText("Chỉ còn " + phong1.getSoPhong() +" phòng trống");

        if(phong2.getDieuhoa() == 0){
            iconDieuhoa.setVisibility(View.GONE);
        }if(phong2.getTivi() == 0){
            iconTV.setVisibility(View.GONE);
        }if(phong2.getKetantoan() == 0){
            iconketantoan.setVisibility(View.GONE);
        }if(phong2.getTulanh() == 0){
            icontulanh.setVisibility(View.GONE);
        }if(phong2.getBep() == 0){
            iconbep.setVisibility(View.GONE);
        }if(phong2.getBan() == 0){
            iconaban.setVisibility(View.GONE);
        }if(phong2.getWifi() == 0){
            iconwifi.setVisibility(View.GONE);
        }if(phong2.getDichvuphong() == 0){
            icDichVu.setVisibility(View.GONE);
        }if(phong2.getMaygiat() == 0){
            iconMayGiat.setVisibility(View.GONE);
        }if(phong2.getMaysaytoc() == 0){
            iconMaySaytoc.setVisibility(View.GONE);
        }if(phong2.getBanui() == 0){
            iconBanLa.setVisibility(View.GONE);
        }
        if(phong2.getKhonghutthuoc() == 0){
            IconKhongHutThuoc.setVisibility(View.GONE);
            tvHutThuoc.setText("Có đc hút thuốc");
        }else{
            tvHutThuoc.setText("Không được hút thuốc");
        }

        if(phong2.getBontam() == 0){
            IconBonTam.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View view, int position) {
        Intent i = new Intent(InfoRoomActivity.this , ImagePhongActivity.class);
        i.putExtra("image" , phong1);
        startActivity(i);
    }
}