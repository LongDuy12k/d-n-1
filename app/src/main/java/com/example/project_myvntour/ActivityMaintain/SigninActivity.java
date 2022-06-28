package com.example.project_myvntour.ActivityMaintain;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project_myvntour.Database.SelectAll;
import com.example.project_myvntour.R;

public class SigninActivity extends AppCompatActivity {
    private EditText etMail;
    private EditText etUsername;
    private EditText etPassword;
    private Button btLogin;
    private LinearLayout ll1;
    private TextView textDangKi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);


        etMail = (EditText) findViewById(R.id.etMail);
        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
        btLogin = (Button) findViewById(R.id.btLogin);
        ll1 = (LinearLayout) findViewById(R.id.ll1);
        textDangKi = (TextView) findViewById(R.id.textDangKi);
        SelectAll selectAll = new SelectAll(this);
        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String e = etMail.getText().toString();
                String u = etUsername.getText().toString();
                String p = etPassword.getText().toString();
                if(e.isEmpty()){
                    etMail.setError("Email không đc để trống");
                    return;
                }else if(u.isEmpty()){
                    etUsername.setError("Username không đc để trống");
                    return;
                }else if(p.isEmpty()){
                    etPassword.setError("Password không đc để trống");
                    return;
                } else if(selectAll.CheckUser(u)==true){
                    Toast.makeText(SigninActivity.this, "Tên đăng kí đã được sử dụng", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    if(selectAll.Signin(e,u,p)){
                        Toast.makeText(SigninActivity.this, "Đăng kí thành công", Toast.LENGTH_SHORT).show();
                        etMail.setText("");
                        etUsername.setText("");
                        etPassword.setText("");
                        onBackPressed();
                    }else {
                        Toast.makeText(SigninActivity.this, "Thao tác thật bại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        textDangKi.setOnClickListener(v ->{
            this.onBackPressed();
        });

    }
}