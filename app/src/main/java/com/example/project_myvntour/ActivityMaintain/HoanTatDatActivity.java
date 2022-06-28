package com.example.project_myvntour.ActivityMaintain;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project_myvntour.Database.SelectAll;
import com.example.project_myvntour.Mode.HoaDon;
import com.example.project_myvntour.Mode.KhachSan;
import com.example.project_myvntour.Mode.Phong;
import com.example.project_myvntour.R;
import com.google.android.material.appbar.MaterialToolbar;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Calendar;

public class HoanTatDatActivity extends AppCompatActivity {
    private MaterialToolbar toolBar2;
    private LinearLayout contenTop;
    private TextView tvTanKhachSan;
    private LinearLayout contenMId;
    private TextView tvHOtenTenDat;
    private TextView tvEmail;
    private NumberFormat fm = new DecimalFormat("#,###");
    private TextView tvSoDienThoai;
    private TextView tvTenPHong;
    private TextView tvNgayDat;
    private TextView tvNgayTra;
    private TextView tvSoLuongPhogn;
    private TextView tvSoKhachToiTa;
    private TextView tvGiaCuoiCung;
    private RelativeLayout contenBot;
    private TextView GiaMoPhong;
    private Button btnRentNow;
    private String makhachhang;
    private String maphongng;
    private String nguoilon;
    private String trenho;
    private String sophong;
    private String ngayden , ngaydi ,yeucaudacbiet , hoten , email , sdt ,ngayDao;
    private SelectAll mSelectAll;
    private Phong phong;
    private String datenow  ,dateTomorrow , giaChinhThuc ,sodem;
    private KhachSan khach;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoan_tat_dat);
        toolBar2 = (MaterialToolbar) findViewById(R.id.tool_bar2);
        setSupportActionBar(toolBar2);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("Hoàn Tất Đặt Phòng");
        contenTop = (LinearLayout) findViewById(R.id.contenTop);
        tvTanKhachSan = (TextView) findViewById(R.id.tvTanKhachSan);
        contenMId = (LinearLayout) findViewById(R.id.contenMId);
        tvHOtenTenDat = (TextView) findViewById(R.id.tvHOtenTenDat);
        tvEmail = (TextView) findViewById(R.id.tvEmail);
        tvSoDienThoai = (TextView) findViewById(R.id.tvSoDienThoai);
        tvTenPHong = (TextView) findViewById(R.id.tvTenPHong);
        tvNgayDat = (TextView) findViewById(R.id.tvNgayDat);
        tvNgayTra = (TextView) findViewById(R.id.tvNgayTra);
        tvSoLuongPhogn = (TextView) findViewById(R.id.tvSoLuongPhogn);
        tvSoKhachToiTa = (TextView) findViewById(R.id.tvSoKhachToiTa);
        tvGiaCuoiCung = (TextView) findViewById(R.id.tvGiaCuoiCung);
        contenBot = (RelativeLayout) findViewById(R.id.contenBot);
        GiaMoPhong = (TextView) findViewById(R.id.GiaMoPhong);
        btnRentNow = (Button) findViewById(R.id.btnRentNow);
        mSelectAll = new SelectAll(this);
        makhachhang = this.getIntent().getStringExtra("makhachhang");
        final Calendar cl = Calendar.getInstance();
        datenow = cl.get(Calendar.YEAR)  + "-" + (cl.get(Calendar.MONTH) + 1) +"-" + cl.get(Calendar.DAY_OF_MONTH);

     //   dateTomorrow = (cl.get(Calendar.DAY_OF_MONTH) +1) + "/" + (cl.get(Calendar.MONTH) + 1) +"/" + cl.get(Calendar.YEAR);
        maphongng = getIntent().getStringExtra("maphong");
        nguoilon = getIntent().getStringExtra("nguoilon");
        trenho = getIntent().getStringExtra("trenho");
        ngayden = getIntent().getStringExtra("ngayden");
        ngaydi = getIntent().getStringExtra("ngaydi");
        ngayDao = getIntent().getStringExtra("ngaydi2");
        giaChinhThuc = getIntent().getStringExtra("giachinhthuc");
        sodem = getIntent().getStringExtra("sodem");
        sophong = getIntent().getStringExtra("sophong");


        khach = (KhachSan) getIntent().getSerializableExtra("khachSan");
        int soLuongPhongKhachSan = mSelectAll.getSoLuongPhongkhachSan(khach.getId());
        yeucaudacbiet = getIntent().getStringExtra("yeucaudacbiet");
        hoten = getIntent().getStringExtra("hoten");
        email = getIntent().getStringExtra("email");
        sdt = getIntent().getStringExtra("sdt");
        phong= mSelectAll.getPHongByIdVer1(Integer.parseInt(String.valueOf(maphongng)));
        showData();
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            CharSequence name;
            NotificationChannel chanal = new NotificationChannel("my", "myname",NotificationManager.IMPORTANCE_DEFAULT );
            NotificationManager manage = getSystemService(NotificationManager.class);
            manage.createNotificationChannel(chanal);
        }
        btnRentNow.setOnClickListener(v->{
            mSelectAll.insertPhieuThue(Integer.parseInt(makhachhang) , Integer.parseInt(maphongng) ,(Integer.parseInt(nguoilon) + Integer.parseInt(trenho)) ,datenow ,ngayDao , hoten , sdt , email ,yeucaudacbiet   );
            mSelectAll.updateSOluongPHong(Integer.parseInt(maphongng) ,(Integer.parseInt(sophong) - 1));
            mSelectAll.updateSOluongPHongKhachSan(khach.getId() ,(mSelectAll.getSoLuongPhongkhachSan(khach.getId()) - 1));
            Toast.makeText(this, "Đặt Phòng thành công", Toast.LENGTH_SHORT).show();
            String text = "Bạn đã đặt phòng thành công";
            NotificationCompat.Builder buiilder = new NotificationCompat.Builder(HoanTatDatActivity.this , "my");
            buiilder.setContentTitle("Đặt phòng thành công");
            buiilder.setContentText(phong.getTenPhong() + " Đã tiếp nhận yêu cầu của bạn");
            buiilder.setSmallIcon(R.drawable.logoapp);
            buiilder.setAutoCancel(true);
            NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(HoanTatDatActivity.this);
            notificationManagerCompat.notify(1 , buiilder.build());
            Intent notifyIntent = new Intent(this, HoaDonActivity.class);
// Set the Activity to start in a new, empty task
            notifyIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                    | Intent.FLAG_ACTIVITY_CLEAR_TASK);
// Create the PendingIntent
            PendingIntent notifyPendingIntent = PendingIntent.getActivity(
                    this, 0, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT
            );
            buiilder.setContentIntent(notifyPendingIntent);


            Toast.makeText(this, "Đặt phòng thành công", Toast.LENGTH_SHORT).show();
          startActivity(new Intent(HoanTatDatActivity.this , MainActivity.class));
        });


    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
    public void showData(){
        tvGiaCuoiCung.setText("Giá cuối cùng: "+fm.format(Long.parseLong(giaChinhThuc)) +" VND / " + sodem +" đêm");
        tvEmail.setText(email);
        tvNgayDat.setText(ngayden);
        tvNgayTra.setText(ngaydi);
        tvTenPHong.setText(phong.getTenPhong());
        tvHOtenTenDat.setText(hoten);
        tvSoLuongPhogn.setText(1 + " Phòng" );
        tvSoDienThoai.setText(sdt);
        tvSoKhachToiTa.setText("Số khách tối đa "+(Integer.parseInt(nguoilon) + Integer.parseInt(trenho)));
        GiaMoPhong.setText(fm.format(Long.parseLong(giaChinhThuc)) +" VND / " + sodem +" đêm");
    }
}