package com.example.project_myvntour.ActivityMaintain;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;

import com.example.project_myvntour.Database.SelectAll;
import com.example.project_myvntour.Mode.KhachSan;
import com.example.project_myvntour.Mode.Phong;
import com.example.project_myvntour.R;
import com.google.android.material.appbar.MaterialToolbar;

public class BookNowActivity extends AppCompatActivity {
    private MaterialToolbar toolBar;
    private TextView tvTanKhachSan;
    private ImageView imsao1;
    private ImageView imsao2;
    private ImageView imsao3;
    private ImageView imsao4;
    private ImageView imsao5;
    private ImageView ivAnhKhachSan;
    private TextView tvNgayNhanPhong;
    private TextView tvTimeNhanPhong;
    private TextView tvNgayTra;
    private TextView tvTimeTra;
    private TextView tvTenPhong;
    private TextView tvSonguoi;
    private TextView tvDienTich;
    private TextView tvMota;
    private TextView tvSoGiuong;
    private TextView tvHoanHuyNGay;
    private TextView tvtienIichPhong;
    private TextView tvHutThuoc;
    private TextView tvSoLuongPhong;
    private EditText etHoten;
    private EditText etSoDienThoai;
    private EditText etEmail;
    private CheckBox checkBoxdatHo;
    private LinearLayout contenDatHo;
    private EditText etHotenNguuoiDatHo;
    private EditText etSoDienThoaiNguuoiDatHo;
    private EditText etEmailNguuoiDatHo;
    private RadioGroup radioGroup1;
    private RadioButton radioGiuongDon;
    private RadioButton radioGiuongDôi;
    private Switch switchThueVat;
    private LinearLayout contenVAT;
    private EditText etMaSoThue;
    private EditText etTenCongTy;
    private EditText etDiaChiCongTi;
    private TextView tvGia;
    private TextView tvGiaVAT;
    private TextView GiaMoPhong;
    private Button btnRentNow;
    private SelectAll mSelectAll;
    private KhachSan khachSan;
    private Phong phong;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_now);
        intFindId();
        intClick();
        mSelectAll = new SelectAll(this);


    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
    public void intFindId(){





        toolBar = (MaterialToolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("Đặt Phòng");
        tvTanKhachSan = (TextView) findViewById(R.id.tvTanKhachSan);
        imsao1 = (ImageView) findViewById(R.id.imsao1);
        imsao2 = (ImageView) findViewById(R.id.imsao2);
        imsao3 = (ImageView) findViewById(R.id.imsao3);
        imsao4 = (ImageView) findViewById(R.id.imsao4);
        imsao5 = (ImageView) findViewById(R.id.imsao5);
        ivAnhKhachSan = (ImageView) findViewById(R.id.ivAnhKhachSan);
        tvNgayNhanPhong = (TextView) findViewById(R.id.tvNgayNhanPhong);
        tvTimeNhanPhong = (TextView) findViewById(R.id.tvTimeNhanPhong);
        tvNgayTra = (TextView) findViewById(R.id.tvNgayTra);
        tvTimeTra = (TextView) findViewById(R.id.tvTimeTra);
        tvTenPhong = (TextView) findViewById(R.id.tvTenPhong);
        tvSonguoi = (TextView) findViewById(R.id.tvSonguoi);
        tvDienTich = (TextView) findViewById(R.id.tvDienTich);
        tvMota = (TextView) findViewById(R.id.tvMota);
        tvSoGiuong = (TextView) findViewById(R.id.tvSoGiuong);
        tvHoanHuyNGay = (TextView) findViewById(R.id.tvHoanHuyNGay);
        tvtienIichPhong = (TextView) findViewById(R.id.tvtienIichPhong);
        tvHutThuoc = (TextView) findViewById(R.id.tvHutThuoc);
        tvSoLuongPhong = (TextView) findViewById(R.id.tvSoLuongPhong);
        etHoten = (EditText) findViewById(R.id.etHoten);
        etSoDienThoai = (EditText) findViewById(R.id.etSoDienThoai);
        etEmail = (EditText) findViewById(R.id.etEmail);
        checkBoxdatHo = (CheckBox) findViewById(R.id.checkBoxdatHo);
        contenDatHo = (LinearLayout) findViewById(R.id.contenDatHo);
        etHotenNguuoiDatHo = (EditText) findViewById(R.id.etHotenNguuoiDatHo);
        etSoDienThoaiNguuoiDatHo = (EditText) findViewById(R.id.etSoDienThoaiNguuoiDatHo);
        etEmailNguuoiDatHo = (EditText) findViewById(R.id.etEmailNguuoiDatHo);
        radioGroup1 = (RadioGroup) findViewById(R.id.radioGroup1);
        radioGiuongDon = (RadioButton) findViewById(R.id.radioGiuongDon);
        radioGiuongDôi = (RadioButton) findViewById(R.id.radioGiuongDôi);
        switchThueVat = (Switch) findViewById(R.id.switchThueVat);
        contenVAT = (LinearLayout) findViewById(R.id.contenVAT);
        etMaSoThue = (EditText) findViewById(R.id.etMaSoThue);
        etTenCongTy = (EditText) findViewById(R.id.etTenCongTy);
        etDiaChiCongTi = (EditText) findViewById(R.id.etDiaChiCongTi);
        tvGia = (TextView) findViewById(R.id.tvGia);
        tvGiaVAT = (TextView) findViewById(R.id.tvGiaVAT);
        GiaMoPhong = (TextView) findViewById(R.id.GiaMoPhong);
        btnRentNow = (Button) findViewById(R.id.btnRentNow);


    }
    public void intClick(){
        checkBoxdatHo.setOnClickListener(v->{
            if(checkBoxdatHo.isChecked()){
                contenDatHo.setVisibility(View.VISIBLE);
            }else{
                contenDatHo.setVisibility(View.GONE);
            }
        });
        radioGroup1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId){
                    case R.id.radioGiuongDon:
                        break;
                    case R.id.radioGiuongDôi:
                        break;

                }
            }
        });

        switchThueVat.setOnClickListener(v->{
            if(switchThueVat.isChecked()){
                contenVAT.setVisibility(View.VISIBLE);
            }else{
                contenVAT.setVisibility(View.GONE);
            }
        });
        switchThueVat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(switchThueVat.isChecked()){
                    contenVAT.setVisibility(View.VISIBLE);
                }else{
                    contenVAT.setVisibility(View.GONE);
                }
            }
        });
    }
}