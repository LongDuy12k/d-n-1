package com.example.project_myvntour.ActivityMaintain;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.project_myvntour.R;
import com.google.android.material.appbar.MaterialToolbar;

public class SettingActivity extends AppCompatActivity {
    private LinearLayout itemPhone;
    private TextView phone;
    private TextView csbm;
    private TextView vemyvntour;
    MaterialToolbar toolbar;
    private TextView tvSupport;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        itemPhone = (LinearLayout) findViewById(R.id.item_phone);
        phone = (TextView) findViewById(R.id.phone);
        csbm = (TextView) findViewById(R.id.csbm);
        vemyvntour = (TextView) findViewById(R.id.vemyvntour);
        tvSupport = (TextView) findViewById(R.id.tv_support);
        toolbar = findViewById(R.id.tool_bar2);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("Setting");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        tvSupport.setOnClickListener(v -> {
            startActivity(new Intent(this,RevenuaActivity.class));
        });
        itemPhone.setOnClickListener(view -> {
            String call = "tel:" + phone.getText().toString().trim();
            Intent i = new Intent(Intent.ACTION_DIAL);
            i.setData(Uri.parse(call));
            startActivity(i);
        });

        csbm = (TextView) findViewById(R.id.csbm);
        csbm.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent i = new Intent(SettingActivity.this,CSBMatActivity.class);
                startActivity(i);
            }
        });

        vemyvntour = (TextView) findViewById(R.id.vemyvntour);
        vemyvntour.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent i = new Intent(SettingActivity.this,VeMyVnTourActivity.class);
                startActivity(i);
            }
        });
    }
}