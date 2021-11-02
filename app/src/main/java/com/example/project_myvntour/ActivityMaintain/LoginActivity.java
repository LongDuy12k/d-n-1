package com.example.project_myvntour.ActivityMaintain;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.project_myvntour.R;

public class LoginActivity extends AppCompatActivity {
    private EditText etUsername;
    private EditText etPassword;
    private TextView tvDangKi;
    private Button btLogin;
    private LinearLayout ll1;
    private TextView textDangKi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
        tvDangKi = (TextView) findViewById(R.id.tvDangKi);
        btLogin = (Button) findViewById(R.id.btLogin);
        ll1 = (LinearLayout) findViewById(R.id.ll1);
        textDangKi = (TextView) findViewById(R.id.textDangKi);
        btLogin.setOnClickListener(v->{
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
        });
        textDangKi.setOnClickListener(v->{
            startActivity(new Intent(LoginActivity.this, SigninActivity.class));
        });

    }
}