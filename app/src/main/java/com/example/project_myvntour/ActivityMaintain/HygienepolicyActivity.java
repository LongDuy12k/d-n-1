package com.example.project_myvntour.ActivityMaintain;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.LinearLayout;

import com.example.project_myvntour.R;
import com.google.android.material.appbar.MaterialToolbar;

public class HygienepolicyActivity extends AppCompatActivity {
    private MaterialToolbar toolBar2;
    private LinearLayout contenbacsi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hygienepolicy);


        toolBar2 = (MaterialToolbar) findViewById(R.id.tool_bar2);
        setSupportActionBar(toolBar2);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("Chính sách vệ sinh");
        contenbacsi = (LinearLayout) findViewById(R.id.contenbacsi);

    }

    @Override
    public boolean onSupportNavigateUp() {
        this.onBackPressed();
        return super.onSupportNavigateUp();
    }
}