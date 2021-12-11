package com.example.project_myvntour.ActivityMaintain;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project_myvntour.Database.SelectAll;
import com.example.project_myvntour.Mode.KhachHang;
import com.example.project_myvntour.Mode.KhachSan;
import com.example.project_myvntour.Mode.Phong;
import com.example.project_myvntour.R;
import com.google.android.material.appbar.MaterialToolbar;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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
    private String username;
    private KhachHang khachhang;
    private String dateNow , dateTomorrow;
    private NumberFormat format = new DecimalFormat("#,###");
    private Calendar cl = Calendar.getInstance();
    String weekDay, datanow2,datanow3;
    private int index;
    SimpleDateFormat dayFormat = new SimpleDateFormat("EE", Locale.US);
    private TextView tvSoDem;
    private LinearLayout btnChonNgayTra;
    private TextView tvSoPhongSoDem;
    Date tomorrow;
    private String ngayDao;
    private long giachinhthuc;
    private long sodem = 1;
    private int checkkkkk;
    private TextView tvTitle;
    private TextView tvRoiKhoi;
    private TextView tvOLai;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_now);
        intFindId();

        mSelectAll = new SelectAll(this);
        username = LoginActivity.userName;
        khachhang = mSelectAll.getKhacHang(username);
        phong = (Phong) getIntent().getSerializableExtra("phong");
        khachSan = mSelectAll.getGetKhachSanByIdPhong(phong.getId_Ks());
        dateNow= dayFormat.format(cl.getTime()) +", "+ cl.get(Calendar.DAY_OF_MONTH) + " tháng" + (cl.get(Calendar.MONTH) +1);
        datanow2=  (cl.get(Calendar.YEAR)) + "-" + (cl.get(Calendar.MONTH) +1) + "-" + cl.get(Calendar.DAY_OF_MONTH);
        datanow3= cl.get(Calendar.DAY_OF_MONTH) + "-" + (cl.get(Calendar.MONTH) +1) + "-" +   (cl.get(Calendar.YEAR));
        cl.add(Calendar.DAY_OF_YEAR, 1);
         tomorrow = cl.getTime();
        ngayDao =(cl.get(Calendar.YEAR)) + "-" + (cl.get(Calendar.MONTH) +1) + "-" +  (cl.get(Calendar.DAY_OF_MONTH) +1);
        dateTomorrow= (String.valueOf(tomorrow).substring(0,3) +", "+ (cl.get(Calendar.DAY_OF_MONTH)+1) + " tháng" + (cl.get(Calendar.MONTH) +1));
        tvSoGiuong.setText(phong.getSoGiuong()+" Giường Đơn");
        intClick();
        showData();
        intDate();
        giachinhthuc = Long.parseLong(String.valueOf(phong.getGia()));
        btnRentNow.setOnClickListener(v->{
            if(checkkkkk ==1){
                Toast.makeText(this, "Xin lỗi quý khách phòng này đã hết cảm ơn quý khách", Toast.LENGTH_SHORT).show();
                return;
            }else{
                Intent i = new Intent(BookNowActivity.this , HoanTatDatActivity.class);
                i.putExtra("makhachhang" , String.valueOf(khachhang.getId()));
                i.putExtra("maphong" , String.valueOf(phong.getId_Phong()));
                System.out.println("zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz " + String.valueOf(phong.getId_Phong()));
                i.putExtra("nguoilon" , String.valueOf((phong.getNguoiLon())));
                i.putExtra("trenho" , String.valueOf((phong.getTreNho())));
                i.putExtra("ngayden" ,dateNow );
                i.putExtra("ngaydi" ,tvNgayTra.getText().toString() );
                i.putExtra("ngaydi2" , ngayDao);
                i.putExtra("khachSan" , khachSan);
                i.putExtra("giachinhthuc" , String.valueOf(giachinhthuc));
                i.putExtra("sophong" , String.valueOf(phong.getSoPhong()));
                i.putExtra("sodem" , String.valueOf(sodem));
                i.putExtra("yeucaudacbiet" , (tvSoGiuong.getText().toString() + etMaSoThue.getText().toString() + etTenCongTy.getText().toString() + etDiaChiCongTi.getText().toString()));
                if(index ==1){
                    i.putExtra("hoten" , etHotenNguuoiDatHo.getText().toString());
                    i.putExtra("email" , etEmailNguuoiDatHo.getText().toString());
                    i.putExtra("sdt" , etSoDienThoaiNguuoiDatHo.getText().toString());
                }else{
                    i.putExtra("hoten" , etHoten.getText().toString());
                    i.putExtra("email" , etEmail.getText().toString());
                    i.putExtra("sdt" , etSoDienThoai.getText().toString());
                }
                startActivity(i);
            }
        });

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
        toolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(BookNowActivity.this);
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
                tvTitle.setText("Bạn có chắc muốn rời khỏi đây?");
                tvRoiKhoi.setText("Rời khỏi");
                tvOLai.setText("Ở lại");

                tvRoiKhoi.setOnClickListener(view->{
                   onBackPressed();
                });
                tvOLai.setOnClickListener(view->{
                    dialog.cancel();
                });
                dialog.show();
            }
        });
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
        tvSoDem = (TextView) findViewById(R.id.tvSoDem);
        btnChonNgayTra = (LinearLayout) findViewById(R.id.btnChonNgayTra);
        tvSoPhongSoDem = (TextView) findViewById(R.id.tvSoPhong_SoDem);

    }
    public void intClick(){
        checkBoxdatHo.setOnClickListener(v->{
            if(checkBoxdatHo.isChecked()){
                contenDatHo.setVisibility(View.VISIBLE);
                index = 1;
            }else{
                contenDatHo.setVisibility(View.GONE);
                index = 2;
            }
        });
        radioGroup1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId){
                    case R.id.radioGiuongDon:
                        tvSoGiuong.setText(phong.getSoGiuong()+" Giường Đơn");
                        break;
                    case R.id.radioGiuongDôi:
                        tvSoGiuong.setText(phong.getSoGiuong()+" Giường Đôi");
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
    public void showData(){
        etHoten.setText(khachhang.getUsername());
        etEmail.setText(khachhang.getEmail());
        etSoDienThoai.setText(khachhang.getSdt());
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
        tvTimeNhanPhong.setText(khachSan.getTimeNhan());
        tvTimeTra.setText(khachSan.getTimetra());
        tvNgayNhanPhong.setText(dateNow);
        tvNgayTra.setText(dateTomorrow);
        tvDienTich.setText(phong.getDienTich() + " m²");
        tvGia.setText(format.format(phong.getGia() - (phong.getGia()*0.2))+" VNĐ");
        tvMota.setText(phong.getMoTa());
        tvSonguoi.setText((phong.getNguoiLon() + phong.getTreNho()) +" người");
        tvHoanHuyNGay.setText("Từ ngày "+dateNow);
        if(phong.getSoPhong() <=0){
            checkkkkk =1;
            tvSoLuongPhong.setText("Hiện tại chúng tôi đã hết phòng này vui bạn vui lòng chọn phòng khách ! Xin Cảm ơn");
            return;
        }else{
            tvSoLuongPhong.setText("Đừng bỏ lỡ ! Chúng tôi chỉ còn " + phong.getSoPhong() + " phòng có giá này hãy đặt ngay");
        }


        tvTenPhong.setText(phong.getTenPhong());
        tvGiaVAT.setText(format.format(phong.getGia()*0.2) + " VNĐ");
        GiaMoPhong.setText(format.format(phong.getGia())+" VNĐ/đêm");

        List<byte[]> listPhot = mSelectAll.getPhotBIdPhong(phong.getId_Phong());

            ivAnhKhachSan.setImageBitmap(BitmapFactory.decodeByteArray(listPhot.get(0) , 0 , listPhot.get(0).length));

    }
    public void intDate(){
        btnChonNgayTra.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                final Calendar calendar = Calendar.getInstance();
                                int d = calendar.get(Calendar.DAY_OF_MONTH);
                                int m = calendar.get(Calendar.MONTH);
                                int y = calendar.get(Calendar.YEAR);
                                DatePickerDialog datePickerDialog = new DatePickerDialog(BookNowActivity.this, new DatePickerDialog.OnDateSetListener() {
                                    @Override
                                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                        final String NgayGD = year + "-" + (month + 1) + "-" + dayOfMonth;
                                        final String NgayGD2 =dayOfMonth  + "-" + (month + 1) + "-" + year;
                                        SimpleDateFormat simpledateformat = new SimpleDateFormat("EEEE");
                                        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                                        ngayDao = NgayGD;
                                        Date date = new Date(year, month, dayOfMonth-1);
                                        String dayOfWeek = simpledateformat.format(date);

                                        tvNgayTra.setText((String.valueOf(dayOfWeek).substring(0,3) +", "+ (dayOfMonth) + " tháng" + (month +1)));
                                        try {
                                            tvSoDem.setText(""+  getDaysDifference(sdf.parse(datanow3) , sdf.parse(NgayGD2)));
                                            sodem = getDaysDifference(sdf.parse(datanow3) , sdf.parse(NgayGD2));
                                        } catch (ParseException e) {
                                            e.printStackTrace();
                                        }
                                        giachinhthuc = sodem * Long.parseLong(String.valueOf(phong.getGia()));
                                        GiaMoPhong.setText(format.format(giachinhthuc) + " VND / " +sodem + " đêm" );

                                    }
                                }, y, m, d);
                                datePickerDialog.show();
                            }
                        });
    }

    private long getDaysDifference(Date fromDate,Date toDate) {

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