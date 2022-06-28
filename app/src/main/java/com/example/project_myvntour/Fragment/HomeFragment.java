package com.example.project_myvntour.Fragment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project_myvntour.ActivityMaintain.BestForYouActivity;
import com.example.project_myvntour.ActivityMaintain.InFoKhachSanActivity;
import com.example.project_myvntour.ActivityMaintain.NearFromYouActivity2;
import com.example.project_myvntour.ActivityMaintain.NearbyActivity;
import com.example.project_myvntour.Adapter.AdapterKhachSanj;
import com.example.project_myvntour.Adapter.AdapterListKhachSanChinh;
import com.example.project_myvntour.Adapter.AdapterLoaiKhachSanj;
import com.example.project_myvntour.Database.SelectAll;
import com.example.project_myvntour.Mode.KhachSan;
import com.example.project_myvntour.Mode.LoaiKhachSanj;
import com.example.project_myvntour.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.innovattic.rangeseekbar.RangeSeekBar;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements AdapterLoaiKhachSanj.UpdateRecyclerView , AdapterKhachSanj.Listernaer ,AdapterListKhachSanChinh.Listernaer, OnMapReadyCallback {

    private EditText etSearch;
    private ImageButton btLogin;
    private RecyclerView recyclerviewCategory;
    private TextView tvSeeMoerGanNhat;
    private RecyclerView recyclerviewListHolderGanNhat;
    private TextView tvSeeMoreListChinh;
    private RecyclerView recyclerviewListChinh;
    private List<LoaiKhachSanj> listLoaiKhachSanj;
    private List<KhachSan> listKhachSan;
    private List<KhachSan> listKhachSan3;
    private AdapterLoaiKhachSanj mAdapterLoaiKhachSanj;
    private AdapterKhachSanj mAdapterKhachSanj;
    private AdapterListKhachSanChinh mAdapterListKhachSanChinh;
    private SelectAll mSelectAll;
    private GoogleMap mMap;
    private List<KhachSan> listKhachSan2;
    public static final int code = 100;
    private MarkerOptions markerOptions;
    LatLng currentUserLocation, searchPointLocation;
    Marker currentUser, searchPoint;
    private double a , b ,tong22 ,tong44;
    private NumberFormat fm = new DecimalFormat("#,###");
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        etSearch = (EditText) view.findViewById(R.id.etSearch);
        btLogin = (ImageButton)view. findViewById(R.id.btLogin);
        recyclerviewCategory = (RecyclerView)view. findViewById(R.id.recyclerviewCategory);
        tvSeeMoerGanNhat = (TextView) view.findViewById(R.id.tvSeeMoerGanNhat);
        recyclerviewListHolderGanNhat = (RecyclerView)view. findViewById(R.id.recyclerviewListHolderGanNhat);
        tvSeeMoreListChinh = (TextView) view.findViewById(R.id.tvSeeMoreListChinh);
        recyclerviewListChinh = (RecyclerView) view.findViewById(R.id.recyclerviewListChinh);

        // khởi tạo các hàm gọi sql

        listLoaiKhachSanj = new ArrayList<>();
        // listKhachSan2 = new ArrayList<>();
        listKhachSan = new ArrayList<>();
        listKhachSan3 = new ArrayList<>();
        mSelectAll = new SelectAll(getActivity());
        listLoaiKhachSanj = mSelectAll.getListLoaiKhachSan();

        mAdapterLoaiKhachSanj = new AdapterLoaiKhachSanj(getActivity() , listLoaiKhachSanj , this);
        mAdapterKhachSanj = new AdapterKhachSanj(getActivity() , this);
        mAdapterListKhachSanChinh = new AdapterListKhachSanChinh(getActivity() , this);
        mAdapterKhachSanj.setData(listKhachSan);
        mAdapterListKhachSanChinh.setDataListChinh(listKhachSan);
        recyclerviewCategory.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        recyclerviewListHolderGanNhat.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        recyclerviewListChinh.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        recyclerviewCategory.setAdapter(mAdapterLoaiKhachSanj);
        recyclerviewListHolderGanNhat.setAdapter(mAdapterKhachSanj);
        recyclerviewListChinh.setAdapter(mAdapterListKhachSanChinh);
        // Inflate the layout for this fragment
        check();
        tvSeeMoerGanNhat.setOnClickListener(v->{
            Intent i = new Intent(getActivity(), NearFromYouActivity2.class);
            startActivity(i);
        });
        tvSeeMoreListChinh.setOnClickListener(v->{
            Intent i = new Intent(getActivity(), BestForYouActivity.class);
            startActivity(i);
        });
        etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
//                   Toast.makeText(getActivity(), "helllo", Toast.LENGTH_SHORT).show();
                    listKhachSan3 = mSelectAll.listTK(etSearch.getText().toString());
                    mAdapterListKhachSanChinh.setDataListChinh(listKhachSan3);
                    mAdapterKhachSanj.setData(listKhachSan3);
                    recyclerviewListChinh.setAdapter(mAdapterListKhachSanChinh);
                    recyclerviewListHolderGanNhat.setAdapter(mAdapterKhachSanj);
                    return true;
                }
                return false;
            }
        });
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                listKhachSan3 = mSelectAll.listTK(etSearch.getText().toString());
                mAdapterListKhachSanChinh.setDataListChinh(listKhachSan3);
                mAdapterKhachSanj.setData(listKhachSan3);
                recyclerviewListChinh.setAdapter(mAdapterListKhachSanChinh);
                recyclerviewListHolderGanNhat.setAdapter(mAdapterKhachSanj);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                listKhachSan3 = mSelectAll.listTK(etSearch.getText().toString());
                mAdapterListKhachSanChinh.setDataListChinh(listKhachSan3);
                mAdapterKhachSanj.setData(listKhachSan3);
                recyclerviewListChinh.setAdapter(mAdapterListKhachSanChinh);
                recyclerviewListHolderGanNhat.setAdapter(mAdapterKhachSanj);
            }

            @Override
            public void afterTextChanged(Editable s) {
                listKhachSan3 = mSelectAll.listTK(etSearch.getText().toString());
                mAdapterListKhachSanChinh.setDataListChinh(listKhachSan3);
                mAdapterKhachSanj.setData(listKhachSan3);
                recyclerviewListChinh.setAdapter(mAdapterListKhachSanChinh);
                recyclerviewListHolderGanNhat.setAdapter(mAdapterKhachSanj);
            }
        });
        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFeedback(Gravity.CENTER);
            }
        });
        return view;

    }
    // list loại khách sạn
    @Override
    public void callbacksChanged(int position, List<KhachSan> list) {
        listKhachSan3 = list;
        listKhachSan =   getListGanNhat(list);
        mAdapterKhachSanj = new AdapterKhachSanj(getActivity() , this);
        mAdapterKhachSanj.setData(listKhachSan);
        recyclerviewListHolderGanNhat.setAdapter(mAdapterKhachSanj);
    }

    @Override
    public void callbacksChanged2(int position, List<KhachSan> list) {
        mAdapterListKhachSanChinh = new AdapterListKhachSanChinh(getActivity() , this);
        mAdapterListKhachSanChinh.setDataListChinh(list);
        recyclerviewListChinh.setAdapter(mAdapterListKhachSanChinh);
    }

    //click list khách sạn gần nhất
    @Override
    public void onClick(View v, int position) {

        KhachSan khach = listKhachSan3.get(position);
        Intent intent = new Intent(getActivity() , InFoKhachSanActivity.class);
        intent.putExtra("khachsan" , khach);
        startActivity(intent);
    }
    //click list khách sạn chính
    @Override
    public void onClickListChinh(View v, int position) {
        KhachSan khach = listKhachSan3.get(position);
        Intent intent = new Intent(getActivity() , InFoKhachSanActivity.class);
        intent.putExtra("khachsan" , khach);
        startActivity(intent);
    }

    private void openFeedback(int gravity){

        final Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.item_loc);
        Window window = dialog.getWindow();
        if(window == null){
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.MATCH_PARENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.gravity = gravity;
        window.setAttributes(layoutParams);
        TextView giaMin;
        TextView giaMax;
        RangeSeekBar seekbar;
        CheckBox btn2sao;
        CheckBox btn3sao;
        CheckBox btn4sao;
        CheckBox btn5sao;
        CheckBox btnHotel;
        CheckBox btnVila;
        CheckBox btnApatment;
        CheckBox btnResort;
        Button btnHuy;
        Button btnLoc;
        giaMin = dialog.findViewById(R.id.giaMin);
        giaMax = dialog.findViewById(R.id.giaMax);
        seekbar = dialog.findViewById(R.id.seekbar);
        btn2sao = dialog.findViewById(R.id.btn_2sao);
        btn3sao = dialog.findViewById(R.id.btn_3sao);
        btn4sao = dialog.findViewById(R.id.btn_4sao);
        btn5sao = dialog.findViewById(R.id.btn_5sao);
        btnHotel = dialog.findViewById(R.id.btn_hotel);
        btnVila = dialog.findViewById(R.id.btn_vila);
        btnApatment = dialog.findViewById(R.id.btn_apatment);
        btnResort = dialog.findViewById(R.id.btn_resort);
        btnHuy = dialog.findViewById(R.id.btn_huy);
        btnLoc = dialog.findViewById(R.id.btn_loc);
        NumberFormat format = new DecimalFormat("#,###");
        // seekbar
        seekbar.setMinRange(0);
        seekbar.setMax(5000000);
        final int[] min = {0};
        final int[] max = {5000000};
        seekbar.setSeekBarChangeListener(new RangeSeekBar.SeekBarChangeListener() {
            @Override
            public void onStartedSeeking() {
            }
            @Override
            public void onStoppedSeeking() {
            }
            @Override
            public void onValueChanged(int i, int i1) {
                min[0] = i;
                max[0] = i1;
                if(i1 == 5000000){
                    giaMax.setText(format.format(i1)+"đ+");
                }else {
                    giaMax.setText(format.format(i1)+"đ");
                }
                giaMin.setText(format.format(i)+"đ");
            }
        });
        //
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btnLoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int h1 = 0,h2=0,h3=0,h4=0,h5=0;
                int l1 =0,l2=0,l3=0,l4=0,l5=0;
                StringBuilder builder = new StringBuilder();
                StringBuilder builder1 = new StringBuilder();
                if(btn2sao.isChecked()){
                    builder.append(1);
                    builder.append(2);
                }if(btn3sao.isChecked()){
                    builder.append(3);
                }if(btn4sao.isChecked()){
                    builder.append(4);
                }if(btn5sao.isChecked()){
                    builder.append(5);
                }if(btnHotel.isChecked()){
                    builder1.append(1);
                }if(btnVila.isChecked()){
                    builder1.append(2);
                }if(btnResort.isChecked()){
                    builder1.append(3);
                }if(btnApatment.isChecked()){
                    builder1.append(4);
                }
                String chuoi = builder.toString();
                String chuoi1 = builder1.toString();
                if(chuoi == ""){
                    h1 = 1;h2 = 2; h3 = 3;h4 = 4;h5 = 5;
                }else {
                    int check = Integer.parseInt(chuoi);
                    if(check == 12){
                        h1 = 1;
                        h2=h3=h4=h5=2;
                    }if(check == 3){
                        h1=h2=h3=h4=h5 = 3;
                    }if(check == 4){
                        h1=h2=h3=h4=h5 = 4;
                    }if(check == 5){
                        h1=h2=h3=h4=h5 = 5;
                    }if(check == 123){
                        h1 = 1;
                        h2 = 2;
                        h3=h4=h5=3;
                    }if(check == 124){
                        h1 = 1;
                        h2 = 2;
                        h3=h4=h5=4;
                    }if(check == 125){
                        h1 = 1;
                        h2 = 2;
                        h3=h4=h5=5;
                    }if(check == 1234){
                        h1 = 1;
                        h2 = 2;
                        h3 = 3;
                        h4 =h5=4;
                    }if(check == 1235){
                        h1 = 1;
                        h2 = 2;
                        h3 = 3;
                        h4 =h5=5;
                    }if(check == 34){
                        h1 = 3;
                        h2=h3=h4=h5=4;
                    }
                    if(check == 35){
                        h1 = 3;
                        h2=h3=h4=h5=5;
                    }
                    if(check == 45){
                        h1 = 4;
                        h2=h3=h4=h5=5;
                    }if(check == 12345){
                        h1 = 1;
                        h2 = 2;
                        h3 = 3;
                        h4 = 4;
                        h5 = 5;
                    }
                }
//                loại ks
                if(chuoi1 == ""){
                    l1=1;l2=2;l3=3;l4=4;
                }else {
                    int check1 = Integer.parseInt(chuoi1);
                    if(check1 == 1){
                        l1=l2=l3=l4=1;
                    }if(check1 == 2){
                        l1=l2=l3=l4=2;
                    }if(check1 == 3){
                        l1=l2=l3=l4=3;
                    }if(check1 == 4){
                        l1=l2=l3=l4=4;
                    }if(check1 == 12){
                        l1 = 1;
                        l2=l3=l4=2;
                    }if(check1 == 13){
                        l1 = 1;
                        l2=l3=l4=3;
                    }if(check1 == 14){
                        l1 = 1;
                        l2=l3=l4=4;
                    }if(check1 == 23){
                        l1 = 2;
                        l2=l3=l4=3;;
                    }if(check1 == 24){
                        l1 = 2;
                        l2=l3=l4=4;
                    }if(check1 == 34){
                        l1 = 3;
                        l2=l3=l4=4;;
                    }
                    if(check1 == 1234){
                        l1=1;l2=2;l3=3;l4=4;
                    }
                }

                listKhachSan3 = mSelectAll.getListKhachSanLoc(min[0],max[0],h1,h2,h3,h4,h5,l1,l2,l3,l4);
                mAdapterListKhachSanChinh.setDataListChinh(listKhachSan3);
                mAdapterKhachSanj.setData(listKhachSan3);
                recyclerviewListChinh.setAdapter(mAdapterListKhachSanChinh);
                recyclerviewListHolderGanNhat.setAdapter(mAdapterKhachSanj);
                dialog.dismiss();
            }
        });
        dialog.show();
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case code: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED
                        && (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                        || ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED)) {
                    onMapReady(mMap);

                }else {
                    Toast.makeText(getActivity().getBaseContext(), "Bạn không cấp quyền GPS không thể hoạt động", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        }
    }
    @Override
    public void onMapReady(@NonNull  GoogleMap googleMap) {
        mMap = googleMap;
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions( new String[]{
                        Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, code);
            }
            return;
        }
        mMap.setMyLocationEnabled(true);
        getCurrentLocation();// hàm lấy vị trí chỗ mình đang đứng

        //tạo sự kiện click vào button

    }
    public void getCurrentLocation() {
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(getActivity().getBaseContext(), "Hãy cấp quyền truy cập GPS cho ứng dụng", Toast.LENGTH_SHORT).show();
            return;
        }
        LocationManager locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
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

            // vẽ maker
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
            Toast.makeText(getActivity().getBaseContext(), "Không lấy được thông tin định vị, hãy bật GPS và bấm nút định vị trên bản đồ", Toast.LENGTH_LONG).show();
        }
    }
    public List<KhachSan> getListGanNhat(List<KhachSan> list){

        listKhachSan2 = new ArrayList<>();
        LocationManager locationManager = (LocationManager)getActivity(). getSystemService(Context.LOCATION_SERVICE);
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
        return  listKhachSan2;
    }

    @Override
    public void onResume() {
        super.onResume();
    }
    public void check(){
                if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(getActivity().getBaseContext(), "Hãy cấp quyền truy cập GPS cho ứng dụng", Toast.LENGTH_SHORT).show();
            return;
        }
    }
}