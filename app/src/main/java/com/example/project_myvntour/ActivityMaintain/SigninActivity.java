package com.example.project_myvntour.ActivityMaintain;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

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

        textDangKi.setOnClickListener(v ->{
            this.onBackPressed();
        });

    }
}