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

        // seekbar
        seekbar.setMinRange(0);
        seekbar.setMax(5000000);
        final int[] min = {0};
        final int[] max = {0};
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
                    giaMax.setText(fm.format(i1)+" đ+");
                }else {
                    giaMax.setText(fm.format(i1)+" đ");
                }
                giaMin.setText(fm.format(i)+" đ");

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
                listKhachSan3 = mSelectAll.getListKhachSanLoc(min[0],max[0]);
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
//        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
//                && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            Toast.makeText(getActivity().getBaseContext(), "Hãy cấp quyền truy cập GPS cho ứng dụng", Toast.LENGTH_SHORT).show();
//            return;
//        }
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
}