package com.example.project_myvntour.ActivityMaintain;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project_myvntour.Adapter.AdapterGarryAllen;
import com.example.project_myvntour.Database.SelectAll;
import com.example.project_myvntour.Mode.KhachSan;
import com.example.project_myvntour.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.maps.android.ui.IconGenerator;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import kr.co.prnd.readmore.ReadMoreTextView;

public class InFoKhachSanActivity extends AppCompatActivity implements AdapterGarryAllen.Listionner , OnMapReadyCallback {
    private CardView contenTOp;
    public static final int code = 100;
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
    private ReadMoreTextView tvMoto;
    private TextView tvSeeMoerTiennghi;
    private LinearLayout listtiennghi;
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
    private RelativeLayout RelaChinhSachVeSinh;
    private KhachSan khach;
    private SelectAll mSelectAll;
   private AdapterGarryAllen mAdapterGarryAllen;
    private LinearLayout liWifiTaiSanh;
    private LinearLayout liWifiTaiPhong;
    private LinearLayout libeboi;
    private LinearLayout lispa;
    private LinearLayout liDauXe;
    private LinearLayout liVatNuoi;
    private MarkerOptions markerOptions;
    Marker currentUser, searchPoint;
    LatLng currentUserLocation, searchPointLocation;
    private GoogleMap mMap;
    MenuItem menuItem;
    private NumberFormat fm = new DecimalFormat("#,###");
    List<byte[]> listPhot;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_fo_khach_san);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mapinfo);

        mapFragment.getMapAsync(this);

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
        tvMoto = (ReadMoreTextView) findViewById(R.id.tvMoto);
        tvSeeMoerTiennghi = (TextView) findViewById(R.id.tvSeeMoerTiennghi);
        listtiennghi = (LinearLayout) findViewById(R.id.listtiennghi);
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
        RelaChinhSachVeSinh = (RelativeLayout) findViewById(R.id.RelaChinhSachVeSinh);
        liWifiTaiSanh = (LinearLayout) findViewById(R.id.liWifiTaiSanh);
        liWifiTaiPhong = (LinearLayout) findViewById(R.id.liWifiTaiPhong);
        libeboi = (LinearLayout) findViewById(R.id.libeboi);
        lispa = (LinearLayout) findViewById(R.id.lispa);
        liDauXe = (LinearLayout) findViewById(R.id.liDauXe);
        liVatNuoi = (LinearLayout) findViewById(R.id.liVatNuoi);



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
        mSelectAll = new SelectAll(this);
        khach =(KhachSan) this.getIntent().getSerializableExtra("khachsan");
        mAdapterGarryAllen =new AdapterGarryAllen(this , this);
        listimageanh.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        showData();

        System.out.println("zzzzzzzzzzzzzzzzzzz" + khach.getGym() + khach.getBar() + khach.getNhaHang() + khach.getVatNuoi());
        tvSeeMoerTiennghi.setOnClickListener(v->{
            Intent inten = new Intent(InFoKhachSanActivity.this , ServiceFacilitiesActivity.class);
            inten.putExtra("khachsan" , khach);
            startActivity(inten);
        });
        tvSeeMoerChinhSachVeSinh.setOnClickListener(v->{
            startActivity(new Intent(InFoKhachSanActivity.this, HygienepolicyActivity.class));
        });
        btnRentNow.setOnClickListener(v->{
            Intent intent = new Intent(InFoKhachSanActivity.this , PhongActivity.class);
            intent.putExtra("khachsan" ,khach);
            startActivity(intent);
        });
        btLogin.setOnClickListener(v->{
            String mobilephone = khach.getSoDienThoaiChuKhachSan();
            String call = "tel:" + mobilephone.trim();
            Intent i = new Intent(Intent.ACTION_DIAL);
            i.setData(Uri.parse(call));
            startActivity(i);
        });


    }
    public void showData() {
         listPhot = mSelectAll.getListPhotById(khach.getId());
        mAdapterGarryAllen.setData(listPhot);
        listimageanh.setAdapter(mAdapterGarryAllen);
        if(listPhot.size() >1) {
            ivAnhKhachSan.setImageBitmap(BitmapFactory.decodeByteArray(listPhot.get(0) , 0 , listPhot.get(0).length));
        }
        byte[] anhchu = mSelectAll.getAnhChuKhachSan(khach.getId());
        hinhanhquanli.setImageBitmap(BitmapFactory.decodeByteArray(anhchu , 0  , anhchu.length));
        tenQuanLi.setText(khach.getTenChuKhachSan());
        tvDiaChi.setText(khach.getDiaDiem());
        tvTenKhachSan.setText(khach.getTenKhachSan());
        tvSoPhongBedRoom.setText(khach.getSoLuongPHong()+" Phòng");
        tvMoto.setText(khach.getMota());
        timeNhan.setText(khach.getTimeNhan() +" - ");
        timeTra.setText(khach.getTimetra());
        GiaMoPhong.setText(fm.format(khach.getGiaThue()) + " VND");
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
        if(Integer.parseInt(khach.getChinhsachveSinh()) == 1){
            RelaChinhSachVeSinh.setVisibility(View.VISIBLE);
        }else{
            RelaChinhSachVeSinh.setVisibility(View.GONE);
        }
        if(khach.getSoSao()==1){
            imsao2.setVisibility(View.INVISIBLE);
            imsao3.setVisibility(View.INVISIBLE);
            imsao4.setVisibility(View.INVISIBLE);
            imsao5.setVisibility(View.INVISIBLE);
        }
        else if(khach.getSoSao()==2){
            imsao3.setVisibility(View.INVISIBLE);
            imsao4.setVisibility(View.INVISIBLE);
            imsao5.setVisibility(View.INVISIBLE);
        }else if(khach.getSoSao()==3){
            imsao4.setVisibility(View.INVISIBLE);
            imsao5.setVisibility(View.INVISIBLE);
        }else if(khach.getSoSao()==4){
            imsao5.setVisibility(View.INVISIBLE);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.itemboomak, menu);
        menuItem = menu.findItem(R.id.iconbookmak);
        int trangThaiLuu = mSelectAll.getTrangThaiLuu(LoginActivity.id , khach.getId());
        if(trangThaiLuu == 1){
            menuItem.setIcon(R.drawable.ic_baseline_bookmark_24_trang_full);
        }else{
            menuItem.setIcon(R.drawable.ic_baseline_bookmark_border_24_khungtranglon);
        }
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.iconbookmak) {
            if (khach.getTrangThaiLuu() == 0) {
                khach.setTrangThaiLuu(1);
                mSelectAll.insertCheck(khach.getId() , LoginActivity.id  );
                item.setIcon(R.drawable.ic_baseline_bookmark_24_trang_full);
            }else if(khach.getTrangThaiLuu() == 1){
                khach.setTrangThaiLuu(0);
                mSelectAll.deleteFromTranThaiLuu(khach.getId() , LoginActivity.id  );
                item.setIcon(R.drawable.ic_baseline_bookmark_border_24_khungtranglon);
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void clickShowImageView(View view, int position) {
        Intent inten = new Intent(InFoKhachSanActivity.this , ImageActivity.class);
        inten.putExtra("image" ,khach );
        startActivity(inten);
    }

    @Override
    public void onMapReady(@NonNull  GoogleMap googleMap) {
        mMap = googleMap;
        if (ActivityCompat.checkSelfPermission(InFoKhachSanActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(InFoKhachSanActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions( new String[]{
                        Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, code);
            }
            return;
        }
        mMap.setMyLocationEnabled(true);
        getCurrentLocationItemMap(khach);
    }
    public void getCurrentLocationItemMap(KhachSan khach) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(getBaseContext(), "Hãy cấp quyền truy cập GPS cho ứng dụng", Toast.LENGTH_SHORT).show();
            return;
        }
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        Location location = locationManager.getLastKnownLocation(locationManager.getBestProvider(criteria, false));

        if (location != null) {
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(), location.getLongitude()), 13));
            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(new LatLng(khach.getKinhdo(), khach.getVido()))
                    .zoom(15)
                    .bearing(0)
                    .tilt(40)
                    .build();
            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
            LatLng myLocation = new LatLng(khach.getKinhdo(), khach.getVido());
            currentUserLocation = myLocation;
            markerOptions = new MarkerOptions();
            markerOptions .position(myLocation);
            markerOptions .icon(BitmapDescriptorFactory.fromResource(R.drawable.iconhotelaaa));
            currentUser = mMap.addMarker(markerOptions);
            currentUser.setTag(false);

        } else {
            Toast.makeText(getBaseContext(), "Không lấy được thông tin định vị, hãy bật GPS và bấm nút định vị trên bản đồ", Toast.LENGTH_LONG).show();
        }


    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case code: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED
                        && (ActivityCompat.checkSelfPermission(InFoKhachSanActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                        || ActivityCompat.checkSelfPermission(InFoKhachSanActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED)) {
                    onMapReady(mMap);

                }else {
                    Toast.makeText(InFoKhachSanActivity.this.getBaseContext(), "Bạn không cấp quyền GPS không thể hoạt động", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        }
    }
}