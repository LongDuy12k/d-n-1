package com.example.project_myvntour.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.project_myvntour.ActivityMaintain.InFoKhachSanActivity;
import com.example.project_myvntour.ActivityMaintain.NearbyActivity;
import com.example.project_myvntour.Adapter.AdapterKhachSanj;
import com.example.project_myvntour.Adapter.AdapterListKhachSanChinh;
import com.example.project_myvntour.Adapter.AdapterLoaiKhachSanj;
import com.example.project_myvntour.Database.SelectAll;
import com.example.project_myvntour.Mode.KhachSan;
import com.example.project_myvntour.Mode.LoaiKhachSanj;
import com.example.project_myvntour.R;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements AdapterLoaiKhachSanj.UpdateRecyclerView , AdapterKhachSanj.Listernaer ,AdapterListKhachSanChinh.Listernaer{

    private EditText etSearch;
    private ImageButton btLogin;
    private RecyclerView recyclerviewCategory;
    private TextView tvSeeMoerGanNhat;
    private RecyclerView recyclerviewListHolderGanNhat;
    private TextView tvSeeMoreListChinh;
    private RecyclerView recyclerviewListChinh;
    private List<LoaiKhachSanj> listLoaiKhachSanj;
    private List<KhachSan> listKhachSan;
    private AdapterLoaiKhachSanj mAdapterLoaiKhachSanj;
    private AdapterKhachSanj mAdapterKhachSanj;
    private AdapterListKhachSanChinh mAdapterListKhachSanChinh;
    private SelectAll mSelectAll;

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
        listKhachSan = new ArrayList<>();
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

        });
        tvSeeMoreListChinh.setOnClickListener(v->{

        });


        return view;




    }
// list loại khách sạn
    @Override
    public void callbacksChanged(int position, List<KhachSan> list) {
        listKhachSan = list;
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
        KhachSan khach = listKhachSan.get(position);
        Intent intent = new Intent(getActivity() , InFoKhachSanActivity.class);
        intent.putExtra("khachsan" , khach);
        startActivity(intent);
    }
    //click list khách sạn chính
    @Override
    public void onClickListChinh(View v, int position) {
        KhachSan khach = listKhachSan.get(position);
        Intent intent = new Intent(getActivity() , InFoKhachSanActivity.class);
        intent.putExtra("khachsan" , khach);
        startActivity(intent);
    }
}