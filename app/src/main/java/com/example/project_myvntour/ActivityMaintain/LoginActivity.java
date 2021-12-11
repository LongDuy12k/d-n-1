package com.example.project_myvntour.ActivityMaintain;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project_myvntour.Database.SelectAll;
import com.example.project_myvntour.R;

import java.sql.SQLException;

public class LoginActivity extends AppCompatActivity {
    private EditText etUsername;
    private EditText etPassword;
    private TextView tvDangKi;
    private Button btLogin;
    private LinearLayout ll1;
    private TextView textDangKi;
    public static String userName = "";
    public static int id ;
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
        SelectAll selectAll = new SelectAll(this);
        btLogin.setOnClickListener(v->{
            String u = etUsername.getText().toString();
            String p = etPassword.getText().toString();
            if(u.isEmpty()){
                etUsername.setError("username is empty");
            }else if(p.isEmpty()){
                etPassword.setError("password is empty");
            }else {
                if(selectAll.CheckLogin(u,p)==true){
                    userName = u;
                    id = selectAll.getIdUser(userName);
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                }else {
                    Toast.makeText(this, "Incorrect username or password", Toast.LENGTH_SHORT).show();
                }
            }

        });
        textDangKi.setOnClickListener(v->{
            startActivity(new Intent(LoginActivity.this, SigninActivity.class));
        });

    }
}