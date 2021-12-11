package com.example.project_myvntour.ActivityMaintain;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;

import android.app.Dialog;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project_myvntour.Database.SelectAll;
import com.example.project_myvntour.Mode.HoaDon;
import com.example.project_myvntour.Mode.KhachSan;
import com.example.project_myvntour.R;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.appbar.MaterialToolbar;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class HoaDonChiTietActivity extends AppCompatActivity {
    private AppBarLayout appBar;
    private CollapsingToolbarLayout collapseToolbarLayout;
    private MaterialToolbar toolbar;
    private TextView tvTanKhachSan;
    private ImageView imsao1;
    private ImageView imsao2;
    private ImageView imsao3;
    private ImageView imsao4;
    private ImageView imsao5;
    private ImageView ivAnhKhachSan;
    private TextView tvNgayNhanPhong;
    private TextView tvTimeNhanPhong;
    private TextView tvSoDem;
    private LinearLayout btnChonNgayTra;
    private TextView tvNgayTra;
    private TextView tvTimeTra;
    private TextView tvTenPhong;
    private TextView tvSonguoi;
    private TextView tvSoGiuong;
    private TextView tvHoanHuyNGay;
    private TextView tvHOtenTenDat;
    private TextView tvEmail;
    private TextView tvSoDienThoai;
    private TextView tvSoPhongSoDem;
    private TextView tvGia;
    private TextView tvGiaVAT;
    private Button btnHuy;
    private HoaDon hoa;
    private TextView tvTitle;
    private TextView tvRoiKhoi;
    private TextView tvOLai;
    private SelectAll mSelectAll;
    private KhachSan khachSan;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private NumberFormat fm = new DecimalFormat("#,###");
    long soDem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoa_don_chi_tiet);


        appBar = (AppBarLayout) findViewById(R.id.appBar);
        collapseToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapseToolbarLayout);
        collapseToolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandedAppBar);
        collapseToolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapsedAppBar2);
        appBar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if ((collapseToolbarLayout.getHeight() + verticalOffset) < (2 * ViewCompat.getMinimumHeight(collapseToolbarLayout))) {

                    toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
                } else {
                    toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
                }
            }
        });
        toolbar = (MaterialToolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("Thành Công");
        tvTanKhachSan = (TextView) findViewById(R.id.tvTanKhachSan);
        imsao1 = (ImageView) findViewById(R.id.imsao1);
        imsao2 = (ImageView) findViewById(R.id.imsao2);
        imsao3 = (ImageView) findViewById(R.id.imsao3);
        imsao4 = (ImageView) findViewById(R.id.imsao4);
        imsao5 = (ImageView) findViewById(R.id.imsao5);
        ivAnhKhachSan = (ImageView) findViewById(R.id.ivAnhKhachSan);
        tvNgayNhanPhong = (TextView) findViewById(R.id.tvNgayNhanPhong);
        tvTimeNhanPhong = (TextView) findViewById(R.id.tvTimeNhanPhong);
        tvSoDem = (TextView) findViewById(R.id.tvSoDem);
        btnChonNgayTra = (LinearLayout) findViewById(R.id.btnChonNgayTra);
        tvNgayTra = (TextView) findViewById(R.id.tvNgayTra);
        tvTimeTra = (TextView) findViewById(R.id.tvTimeTra);
        tvTenPhong = (TextView) findViewById(R.id.tvTenPhong);
        tvSonguoi = (TextView) findViewById(R.id.tvSonguoi);
        tvSoGiuong = (TextView) findViewById(R.id.tvSoGiuong);
        tvHoanHuyNGay = (TextView) findViewById(R.id.tvHoanHuyNGay);
        tvHOtenTenDat = (TextView) findViewById(R.id.tvHOtenTenDat);
        tvEmail = (TextView) findViewById(R.id.tvEmail);
        tvSoDienThoai = (TextView) findViewById(R.id.tvSoDienThoai);
        tvSoPhongSoDem = (TextView) findViewById(R.id.tvSoPhong_SoDem);
        tvGia = (TextView) findViewById(R.id.tvGia);
        tvGiaVAT = (TextView) findViewById(R.id.tvGiaVAT);
        btnHuy = (Button) findViewById(R.id.btnHuy);
        hoa = (HoaDon) getIntent().getSerializableExtra("hoadon");
        mSelectAll= new SelectAll(this);
        khachSan = mSelectAll.getGetKhachSanByIdPhong(hoa.getMaPhong());


        btnHuy.setOnClickListener(v->{
            final Dialog dialog = new Dialog(this);
           // dialog.setCancelable(false); // nhấn ra ngoài nó mới không chị cancel
            dialog.setContentView(R.layout.dialogconfirm);
            Window window = dialog.getWindow();
            window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT , ViewGroup.LayoutParams.WRAP_CONTENT);
            if (dialog != null && dialog.getWindow() != null) {
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            }


            tvTitle = (TextView)dialog. findViewById(R.id.tvTitle);
            tvRoiKhoi = (TextView) dialog.findViewById(R.id.tvRoiKhoi);
            tvOLai = (TextView) dialog.findViewById(R.id.tvOLai);
            tvTitle.setText("Bạn có chắc muốn hủy không ?");
            tvRoiKhoi.setText("Không");
            tvOLai.setText("Có");

            tvRoiKhoi.setOnClickListener(view->{
                dialog.cancel();
            });
            tvOLai.setOnClickListener(view->{
                int idphong = mSelectAll.getIdByTenPhong(hoa.getTenPhong());
                int soluongPhong = mSelectAll.getSoluongPHongByByName(hoa.getTenPhong());
                mSelectAll.deleteHoaDon(hoa.getId());
                mSelectAll.updateSOluongPHong(idphong ,(soluongPhong + 1));
                mSelectAll.updateSOluongPHongKhachSan(khachSan.getId() ,(mSelectAll.getSoLuongPhongkhachSan(khachSan.getId()) + 1));
                Toast.makeText(this, "Hủy Thành Công", Toast.LENGTH_SHORT).show();
                onBackPressed();
            });
            dialog.show();


        });

        showdata();
    }

    @Override
    public boolean onSupportNavigateUp() {
        this.onBackPressed();
        return super.onSupportNavigateUp();
    }
    public void showdata(){
        tvEmail.setText(hoa.getEmail());
        tvSoDienThoai.setText(hoa.getSodienthoai());
        tvHOtenTenDat.setText(hoa.getTennguoidat());
        tvNgayNhanPhong.setText(hoa.getNgayden());
        tvNgayTra.setText(hoa.getNgaydi());
        tvSonguoi.setText(hoa.getSoNguoiLon() +" người");
        tvSoGiuong.setText(hoa.getSoGiuong() + " giường");
        tvTimeNhanPhong.setText(hoa.getGionhanphong());
        tvTimeTra.setText(hoa.getGiotraphong());
        tvTenPhong.setText(hoa.getTenPhong());
        tvTanKhachSan.setText(khachSan.getTenKhachSan());
        if(khachSan.getSoSao()==1){
            imsao2.setVisibility(View.INVISIBLE);
            imsao3.setVisibility(View.INVISIBLE);
            imsao4.setVisibility(View.INVISIBLE);
            imsao5.setVisibility(View.INVISIBLE);
        }
        else if(khachSan.getSoSao()==2){
            imsao3.setVisibility(View.INVISIBLE);
            imsao4.setVisibility(View.INVISIBLE);
            imsao5.setVisibility(View.INVISIBLE);
        }else if(khachSan.getSoSao()==3){
            imsao4.setVisibility(View.INVISIBLE);
            imsao5.setVisibility(View.INVISIBLE);
        }else if(khachSan.getSoSao()==4){
            imsao5.setVisibility(View.INVISIBLE);
        }
        List<byte[]> listPhot = mSelectAll.getListPhotById(khachSan.getId());
        if(listPhot.size() >1) {
            ivAnhKhachSan.setImageBitmap(BitmapFactory.decodeByteArray(listPhot.get(0) , 0 , listPhot.get(0).length));
        }
        try {
            soDem = getDaysDifference(sdf.parse(String.valueOf(hoa.getNgayden())) , sdf.parse(String.valueOf(hoa.getNgaydi())));
            tvSoDem.setText(soDem + "");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long giachinhthuc = soDem * Long.parseLong(String.valueOf(hoa.getTongtien()));
        tvGia.setText(fm.format(Long.parseLong(String.valueOf(giachinhthuc))) +" VND");
//        tvGiaVAT.setText(fm.format(Integer.parseInt(String.valueOf(Long.parseLong(String.valueOf(hoa.getTongtien()*0.2)) * soDem))) + " VNĐ");
//        tvGia.setText(fm.format(Integer.parseInt(String.valueOf(Long.parseLong(String.valueOf(hoa.getTongtien())) * soDem)))+" VNĐ/đêm");
        tvGia.setText(fm.format(((hoa.getTongtien())* soDem)-((hoa.getTongtien()*0.2)*soDem)  ) + " VNĐ");
        tvGiaVAT.setText(fm.format(((hoa.getTongtien()*0.2)*soDem) )+" VNĐ");
        tvSoPhongSoDem.setText("1 phòng x " + soDem + " đêm" );
    }
    private long getDaysDifference(Date fromDate, Date toDate) {

        if(fromDate == null || toDate == null)
            return 0;


        int a = Integer.parseInt(DateFormat.format("dd",   fromDate)+"");
        int b = Integer.parseInt(DateFormat.format("dd",   toDate)+"");

        if ( b <= a){
            return Calendar.getInstance().getActualMaximum(Calendar.DAY_OF_MONTH) + b - a;
        }
        return b - a;
    }
}