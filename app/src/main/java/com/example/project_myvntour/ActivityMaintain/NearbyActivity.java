package com.example.project_myvntour.ActivityMaintain;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.EditText;
import android.widget.Toast;

import com.example.project_myvntour.Adapter.AdapterItemKhachSanMap;
import com.example.project_myvntour.Mode.KhachSan;
import com.example.project_myvntour.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.maps.android.ui.IconGenerator;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class NearbyActivity extends AppCompatActivity implements OnMapReadyCallback, AdapterItemKhachSanMap.Listernaer  {
    public static final int code = 100;
    private GoogleMap mMap;
    private EditText etNhapDiaChi;
    LatLng currentUserLocation, searchPointLocation;
    Marker currentUser, searchPoint;
    private MaterialToolbar toolBar;
    private RecyclerView recyclerview;
    private List<KhachSan> listKhachSan;
    private AdapterItemKhachSanMap adapter;
    private NumberFormat fm = new DecimalFormat("#,###");
    private MarkerOptions markerOptions;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nearby);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map2);
        mapFragment.getMapAsync(this);
        toolBar = (MaterialToolbar) findViewById(R.id.tool_bar2);
        recyclerview = (RecyclerView) findViewById(R.id.recyclerview);






        setSupportActionBar(toolBar);
        getSupportActionBar().setTitle("Nearby");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.

        listKhachSan = new ArrayList<>();
        adapter = new AdapterItemKhachSanMap(this ,this);

        int[] id1={ 1 , 2, 3, 4, 5};
        int[] image = {R.drawable.anh5, R.drawable.anh4, R.drawable.anh3, R.drawable.anh2, R.drawable.anh1};
        String[] tenkhachsan = {"Marriott International", "Hilton Worldwide", "InterContinental Hotels Group (IHG)", "Accor Hotels", "Wyndham Hotel Group"};
        String[] diadiem = {"Tiểu bang Maryland, Mỹ", "Bang Virginia, Mỹ", "Denham, Vương quốc Anh", "Paris, Pháp", "Wyndham Hotel Group"};
        String[] LoaiKhachSan = {"Hotel", "Apartments", "Villa", "Wooden house", "Condos"};
        int[]soluongPHongNGu ={ 5 , 7 ,8 ,3 ,5 };
        int[]soLUongPHongTam ={ 9 , 2 ,2 ,4 ,3 };
        int[]soSao ={ 3 , 4 ,5 ,2 ,1 };
        int[]trangthai ={ 0 , 1 ,0 ,1 ,0 };
        int[]giathue ={ 9000000,20000000 ,4000000 ,300000 , 60000000};
        double[] kinhdo = {20.7554032 , 20.7305544 , 20.7310787, 20.7316318, 20.7318967};
        double[] vido = {106.3717384, 106.3940725, 106.3965079, 106.3958132, 106.393657};

        for(int i=0; i<id1.length; i++) {
            listKhachSan.add(new KhachSan(id1[i] , soluongPHongNGu[i] , soLUongPHongTam [i] ,image[i] ,
                    tenkhachsan[i],
                    diadiem[i],
                    kinhdo[i],
                    vido[i]
                    ,
                    giathue[i]
                    ,LoaiKhachSan[i],trangthai[i] ,soSao[i]
            ));
        }


        adapter.setData(listKhachSan);
        recyclerview.setItemAnimator(new DefaultItemAnimator());
        recyclerview.setLayoutManager(new LinearLayoutManager(this , LinearLayoutManager.HORIZONTAL , false));
        recyclerview.setAdapter(adapter);
        recyclerview.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE){
                    int position = getCurrentItem();
                    KhachSan khach = listKhachSan.get(position);
                    liaCam(khach);


                }
            }
        });
// dữ nguyên item của recylerview ở giữa màn hình có 2 cách
//        SnapHelper snapHelper = new LinearSnapHelper();
//        snapHelper.attachToRecyclerView(recyclerview);
        PagerSnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(recyclerview);



    }

    public void liaCam(KhachSan khach){
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
                    .zoom(17)
                    .bearing(0)
                    .tilt(40)
                    .build();
            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

        } else {
            Toast.makeText(getBaseContext(), "Không lấy được thông tin định vị, hãy bật GPS và bấm nút định vị trên bản đồ", Toast.LENGTH_LONG).show();
        }
    }

    private int getCurrentItem(){
        return ((LinearLayoutManager)recyclerview.getLayoutManager())
                .findFirstVisibleItemPosition();
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
            LatLng myLocation = new LatLng(khach.getKinhdo(), khach.getVido());
            currentUserLocation = myLocation;
            IconGenerator iconfactory = new IconGenerator(this);
            iconfactory.setBackground(getResources().getDrawable(R.drawable.marker_background));
            iconfactory.setTextAppearance(R.style.iconGenText);
             markerOptions = new MarkerOptions()
                    .position(myLocation)
                    .title(khach.getTenKhachSan())
                    .snippet(khach.getDiaDiem())
                    .icon(BitmapDescriptorFactory.fromBitmap(iconfactory.makeIcon(fm.format(khach.getGiaThue()) + " VND")));
            currentUser = mMap.addMarker(markerOptions);
            currentUser.setTag(false);
        } else {
            Toast.makeText(getBaseContext(), "Không lấy được thông tin định vị, hãy bật GPS và bấm nút định vị trên bản đồ", Toast.LENGTH_LONG).show();
        }
    }


    @Override
    public void onMapReady(@NonNull  GoogleMap googleMap) {
        mMap = googleMap;
        if (ActivityCompat.checkSelfPermission(NearbyActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(NearbyActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions( new String[]{
                        Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, code);
            }
            return;
        }
        mMap.setMyLocationEnabled(true);
        getCurrentLocation();
        for ( KhachSan x: listKhachSan) {
            getCurrentLocationItemMap(x);
        }
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {

            // Return true if the click event is consumed (and therefore no
            // info window is displayed) or false to produce default behavior.

            public boolean onMarkerClick(final Marker marker) {

               // mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(), location.getLongitude()), 13));
                CameraPosition cameraPosition = new CameraPosition.Builder()
                        .target(new LatLng(marker.getPosition().latitude, marker.getPosition().longitude))
                        .zoom(15)
                        .bearing(0)
                        .tilt(40)
                        .build();
                mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                System.out.println("ddddddddddddddddddd" + marker.getPosition().latitude);
                System.out.println("ddddddddddddddddddd" + marker.getTitle());
                System.out.println("ddddddddddddddddddd" + marker.getTag());
                System.out.println("ddddddddddddddddddd" + Integer.parseInt(marker.getId().substring(1)));
                recyclerview.smoothScrollToPosition(Integer.parseInt(marker.getId().substring(1)) -1);// di chuyển đến vị trí của recylerview
                //return (marker != null && marker.getTag() != null && ((Boolean)marker.getTag()).booleanValue());
                return false;
            }
        });
    }
    public void getCurrentLocation() {
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
                    .target(new LatLng(location.getLatitude(), location.getLongitude()))
                    .zoom(15)
                    .bearing(0)
                    .tilt(40)
                    .build();
            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
            LatLng myLocation = new LatLng(location.getLatitude(), location.getLongitude());
            currentUserLocation = myLocation;
             markerOptions = new MarkerOptions()
                    .position(myLocation)
                    .title("Your position at here")
                    .snippet("Hello MyVnTour Company")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
            currentUser = mMap.addMarker(markerOptions);
            currentUser.setTag(true);

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
                        && (ActivityCompat.checkSelfPermission(NearbyActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                        || ActivityCompat.checkSelfPermission(NearbyActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED)) {
                    onMapReady(mMap);

                }else {
                    Toast.makeText(NearbyActivity.this.getBaseContext(), "Bạn không cấp quyền GPS không thể hoạt động", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        this.onBackPressed();
        return super.onSupportNavigateUp();
    }

    @Override
    public void onClickKhachSanMap(View v, int position) {

    }

}