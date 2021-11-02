package com.example.project_myvntour.Fragment;

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

import com.example.project_myvntour.Adapter.AdapterKhachSanj;
import com.example.project_myvntour.Adapter.AdapterListKhachSanChinh;
import com.example.project_myvntour.Adapter.AdapterLoaiKhachSanj;
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
        listLoaiKhachSanj = new ArrayList<>();
        listKhachSan = new ArrayList<>();


        int [] id = { 1 , 2, 3, 4, 5, 6 , 7 ,8 , 9};
        String[] tenLoai = {"House" , "Apartments" , "Hotel" , "Villa" , "Wooden house" , "Condos" , "Townhouses", "Multifamily homes" , "Single-family homes"};
//        int id,
//        int soluongPHongNGu,
//        int soLUongPHongTam,
//        int images, String tenKhachSan,
//                String diaDiem,
//        double kinhdo,
//        double vido
        int[] id1={ 1 , 2, 3, 4, 5};
        int[] image = {R.drawable.anh5, R.drawable.anh4, R.drawable.anh3, R.drawable.anh2, R.drawable.anh1};
        String[] tenkhachsan = {"Marriott International", "Hilton Worldwide", "InterContinental Hotels Group (IHG)", "Accor Hotels", "Wyndham Hotel Group"};
        String[] diadiem = {"Tiểu bang Maryland, Mỹ", "Bang Virginia, Mỹ", "Denham, Vương quốc Anh", "Paris, Pháp", "Wyndham Hotel Group"};
        int[]soluongPHongNGu ={ 5 , 7 ,8 ,3 ,5 };
        int[]soLUongPHongTam ={ 9 , 2 ,2 ,4 ,3 };
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
                    ));
        }

        for(int i=0; i<id.length; i++) {
            listLoaiKhachSanj.add(new LoaiKhachSanj(id[i] , tenLoai[i]));
        }
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
        return view;




    }
// list loại khách sạn
    @Override
    public void callbacksChanged(int position, List<KhachSan> list) {

    }
// list khách sạn gần nhất
    @Override
    public void onClick(View v, int position) {

    }

    @Override
    public void onClickListChinh(View v, int position) {

    }
}