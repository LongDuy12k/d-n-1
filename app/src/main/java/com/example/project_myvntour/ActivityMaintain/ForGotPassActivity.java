package com.example.project_myvntour.ActivityMaintain;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.example.project_myvntour.Database.SelectAll;
import com.example.project_myvntour.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class ForGotPassActivity extends AppCompatActivity {
    private LottieAnimationView logo;
    private TextView tvGhiChu;
    private TextInputEditText etEmail;
    private TextInputLayout contenmatkhau;
    private TextInputEditText etMatkhau;
    private Button btnGuiYeuCau;
    private Button btnSumit;
    private TextView tvQuayVe;
    SelectAll mSelectAll;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_for_got_pass);



        logo = (LottieAnimationView) findViewById(R.id.logo);
        tvGhiChu = (TextView) findViewById(R.id.tvGhiChu);
        etEmail = (TextInputEditText) findViewById(R.id.etEmail);
        contenmatkhau = (TextInputLayout) findViewById(R.id.contenmatkhau);
        etMatkhau = (TextInputEditText) findViewById(R.id.etMatkhau);
        btnGuiYeuCau = (Button) findViewById(R.id.btnGuiYeuCau);
        btnSumit = (Button) findViewById(R.id.btnSumit);
        tvQuayVe = (TextView) findViewById(R.id.tvQuayVe);
        mSelectAll = new SelectAll(this);
        btnGuiYeuCau.setOnClickListener(v->{
            String dinhDanhGamil = "(^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$)";

            if(etEmail.getText().toString().isEmpty() || etEmail.getText().toString() == null){
                tvGhiChu.setText("Bạn chưa nhập email");
                return;
            }

            if(!etEmail.getText().toString().matches(dinhDanhGamil)){
                tvGhiChu.setText("Email Nhập không đúng định dạng");
                return;
            }



            if(mSelectAll.checkemail(etEmail.getText().toString()) ==true){
                btnSumit.setVisibility(View.VISIBLE);
                btnGuiYeuCau.setVisibility(View.GONE);
                contenmatkhau.setVisibility(View.VISIBLE);
                tvGhiChu.setText("Nhập mật khẩu mới của bạn");
            }else{
                tvGhiChu.setText("Hệ thống ghi nhận email không tồn tại");
                return;
            }
        });
        btnSumit.setOnClickListener(v->{
            if(etMatkhau.getText().toString().isEmpty() || etMatkhau.getText().toString() == null){
                tvGhiChu.setText("Mật khẩu không được để trống");
                return;
            }else{
                mSelectAll.updatePassword(etEmail.getText().toString() , etMatkhau.getText().toString());
                Toast.makeText(this, "Thao Tác thành công", Toast.LENGTH_SHORT).show();
                btnSumit.setVisibility(View.GONE);
                btnGuiYeuCau.setVisibility(View.VISIBLE);
                contenmatkhau.setVisibility(View.GONE);
            }
        });
        tvQuayVe.setOnClickListener(v->{
            onBackPressed();
        });
    }
}