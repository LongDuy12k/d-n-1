package com.example.project_myvntour.ActivityMaintain;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.Manifest;
import android.animation.LayoutTransition;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.project_myvntour.Adapter.AdapterItemKhachSanMap;
import com.example.project_myvntour.Database.SelectAll;
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
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
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
    private List<KhachSan> listKhachSan2;
    private AdapterItemKhachSanMap adapter;
    private NumberFormat fm = new DecimalFormat("#,###");
    private MarkerOptions markerOptions;
    private CardView cvBoLoc;
    private View dongke;
    List<LatLng> arrayLatLngDirection = new ArrayList<LatLng>();
    Polyline polyline1;
    private CheckBox cbHouse;
    private CheckBox cbApartments;
    private CheckBox cbHotel;
    private CheckBox cbVilla;
    private CheckBox cbAll;
    private LinearLayout layoutContainer;
    private LinearLayout mGridLayout;
    private double a , b ,tong22 ,tong44;
    private SelectAll mSelectAll;
    private View vKhong;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nearby);

        // mu???n map hi???n l??n ph???i c?? c??i n??y
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map2);


        mapFragment.getMapAsync(this);
        toolBar = (MaterialToolbar) findViewById(R.id.tool_bar2);
        recyclerview = (RecyclerView) findViewById(R.id.recyclerview);




       // Expandable Card View







        setSupportActionBar(toolBar);
        getSupportActionBar().setTitle("G???n t??i Nh???t");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.

        listKhachSan = new ArrayList<>();
        listKhachSan2 = new ArrayList<>();
        mSelectAll =new SelectAll(this);
        adapter = new AdapterItemKhachSanMap(this ,this);
        listKhachSan = mSelectAll.getListKhachSan();




        // s???a l?? ?????a ??i???m g???n nh???t
        getListGanNhat(listKhachSan);

        // s???a l?? ?????a ??i???m g???n nh???t
        adapter.setData(listKhachSan2);

        recyclerview.setItemAnimator(new DefaultItemAnimator());
        recyclerview.setLayoutManager(new LinearLayoutManager(this , LinearLayoutManager.HORIZONTAL , false));
        recyclerview.setAdapter(adapter);
        // h??m l???y v??? tr?? c???a item trong reclerview
        recyclerview.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE){
                    int position = getCurrentItem();// l???y v??? tr??
                    KhachSan khach = listKhachSan2.get(position);
                    liaCam(khach);
                }
            }
        });
// d??? nguy??n item c???a recylerview ??? gi???a m??n h??nh c?? 2 c??ch


//        SnapHelper snapHelper = new LinearSnapHelper();
//        snapHelper.attachToRecyclerView(recyclerview);
        PagerSnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(recyclerview);
//        cvBoLoc.setOnClickListener(v->{
//            int view = (mGridLayout.getVisibility() == View.GONE) ?View.VISIBLE :View.GONE;
//          //  int view2 = (vKhong.getVisibility() == View.GONE) ?View.VISIBLE :View.GONE;
//            TransitionManager.beginDelayedTransition(layoutContainer , new AutoTransition());
//            mGridLayout.setVisibility(view);
//           // vKhong.setVisibility(view2);
//        });


    }

    public void liaCam(KhachSan khach){
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(getBaseContext(), "H??y c???p quy???n truy c???p GPS cho ???ng d???ng", Toast.LENGTH_SHORT).show();
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
            Toast.makeText(getBaseContext(), "Kh??ng l???y ???????c th??ng tin ?????nh v???, h??y b???t GPS v?? b???m n??t ?????nh v??? tr??n b???n ?????", Toast.LENGTH_LONG).show();
        }
        DrawPolyline();
    }
    public void getListGanNhat(List<KhachSan> list){
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(getBaseContext(), "H??y c???p quy???n truy c???p GPS cho ???ng d???ng", Toast.LENGTH_SHORT).show();
            return;
        }
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        @SuppressLint("MissingPermission") Location location = locationManager.getLastKnownLocation(locationManager.getBestProvider(criteria, false));
        if(location != null){
            for (KhachSan khach: list
            ) {
                a = location.getLatitude();
                b = location.getLongitude();
                tong22 = ((khach.getKinhdo() - a)*(khach.getKinhdo() - a)) + ((khach.getVido() - b)*(khach.getVido() - b));
                tong44 = Math.sqrt(tong22);
                if(tong44 <= 1000){
                    listKhachSan2.add(khach);
                }
            }
        }
    }
    private int getCurrentItem(){
        return ((LinearLayoutManager)recyclerview.getLayoutManager())
                .findFirstVisibleItemPosition();
    }

    public void getCurrentLocationItemMap(KhachSan khach) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(getBaseContext(), "H??y c???p quy???n truy c???p GPS cho ???ng d???ng", Toast.LENGTH_SHORT).show();
            return;
        }
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        Location location = locationManager.getLastKnownLocation(locationManager.getBestProvider(criteria, false));

//         a = location.getLatitude();
//         b = location.getLongitude();
//         tong22 = ((khach.getKinhdo() - a)*(khach.getKinhdo() - a)) + ((khach.getVido() - b)*(khach.getVido() - b));
//         tong44 = Math.sqrt(tong22);
//       if(tong44 <= 1.0){
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
                Toast.makeText(getBaseContext(), "Kh??ng l???y ???????c th??ng tin ?????nh v???, h??y b???t GPS v?? b???m n??t ?????nh v??? tr??n b???n ?????", Toast.LENGTH_LONG).show();
            }
//       }



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
        getCurrentLocation();// h??m l???y v??? tr?? ch??? m??nh ??ang ?????ng
        for ( KhachSan x: listKhachSan2) {
            getCurrentLocationItemMap(x);// hineje b???ng gi?? ??? tr??n map
        }
        //t???o s??? ki???n click v??o button
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {

            // Return true if the click event is consumed (and therefore no
            // info window is displayed) or false to produce default behavior.
            @SuppressLint("MissingPermission")
            public boolean onMarkerClick(final Marker marker) {
                LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                Criteria criteria = new Criteria();
                Location location = locationManager.getLastKnownLocation(locationManager.getBestProvider(criteria, false));
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

                if(!String.valueOf(marker.getPosition().latitude).equals(String.valueOf(location.getLatitude())) && !String.valueOf(marker.getPosition().longitude).equals(String.valueOf(location.getLongitude()))){// ??o???n n??y ph???i check xem c?? tr??ng v??? tr?? m??nh ??ang ?????ng kh??ng n???u m?? tr??ng th?? s??? l???i v?? v??? tr?? ?????ng kh??ng l???y trong recylerview
                    recyclerview.smoothScrollToPosition(Integer.parseInt(marker.getId().substring(1)) -1);// di chuy???n ?????n v??? tr?? c???a recylerview
                }else{
                     return (marker != null && marker.getTag() != null && ((Boolean)marker.getTag()).booleanValue()); // th??? hi???n v??? tr?? c???a ng?????i d??ng kh??ng ??c click v??o n???u click v??o s??? b??? v??ng l??n l?? ph???i ????? l?? true
                }


                //return (marker != null && marker.getTag() != null && ((Boolean)marker.getTag()).booleanValue());
                return false;
            }
        });
    }
    public void getCurrentLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(getBaseContext(), "H??y c???p quy???n truy c???p GPS cho ???ng d???ng", Toast.LENGTH_SHORT).show();
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

            // v??? maker
            LatLng myLocation = new LatLng(location.getLatitude(), location.getLongitude());
            currentUserLocation = myLocation;
             markerOptions = new MarkerOptions()
                    .position(myLocation)
                    .title("Your position at here")
                    .snippet("Hello MyVnTour Company")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
            currentUser = mMap.addMarker(markerOptions);
            currentUser.setTag(false);

        } else {
            Toast.makeText(getBaseContext(), "Kh??ng l???y ???????c th??ng tin ?????nh v???, h??y b???t GPS v?? b???m n??t ?????nh v??? tr??n b???n ?????", Toast.LENGTH_LONG).show();
        }
    }
    //h??m n??y l?? hi???n xin quy???n map hi???n dialog xin qu???n

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
                    Toast.makeText(NearbyActivity.this.getBaseContext(), "B???n kh??ng c???p quy???n GPS kh??ng th??? ho???t ?????ng", Toast.LENGTH_SHORT).show();
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
        KhachSan khach = listKhachSan.get(position);
        Intent intent = new Intent(NearbyActivity.this , InFoKhachSanActivity.class);
        intent.putExtra("khachsan" , khach);
        startActivity(intent);
    }
    void DrawPolyline(){
        if(polyline1 != null)
            polyline1.remove();
        PolylineOptions polylineOptions = new PolylineOptions() .clickable(true);
        for(int i = 0; i< arrayLatLngDirection.size(); i++){
            LatLng latLng = arrayLatLngDirection.get(i);
            polylineOptions.add(  latLng  );
        }
        polylineOptions.color(R.color.purple_500);
        polyline1 = mMap.addPolyline(polylineOptions);

    }

}