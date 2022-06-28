package com.example.project_myvntour.ActivityMaintain;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project_myvntour.Database.SelectAll;
import com.example.project_myvntour.R;
import com.google.android.material.appbar.MaterialToolbar;
import com.mikhaellopez.circularimageview.CircularImageView;

import de.hdodenhof.circleimageview.CircleImageView;

public class Profile_Activity extends AppCompatActivity {
    MaterialToolbar toolbar;
    private LinearLayout itemPhone;
    private TextView tvPhone;
    private LinearLayout itemDx;
    private CircleImageView imgAnh;
    private TextView tvTen;
    private LinearLayout itemTtnd;
    private LinearLayout itemTb;
    private LinearLayout itemBookmark;
    private LinearLayout itemShare;
    private LinearLayout itemVntour;
    private LinearLayout itemCs;
    SelectAll selectAll;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        toolbar = findViewById(R.id.tool_bar2);
        itemPhone = (LinearLayout) findViewById(R.id.item_phone);
        tvPhone = (TextView) findViewById(R.id.tv_phone);
        itemDx = (LinearLayout) findViewById(R.id.item_dx);
        imgAnh = (CircleImageView) findViewById(R.id.hinhanhquanli);
        tvTen = (TextView) findViewById(R.id.tv_ten);
        itemTtnd = (LinearLayout) findViewById(R.id.item_ttnd);
        itemTb = (LinearLayout) findViewById(R.id.item_tb);
        itemBookmark = (LinearLayout) findViewById(R.id.item_bookmark);
        itemShare = (LinearLayout) findViewById(R.id.item_share);
        itemVntour = (LinearLayout) findViewById(R.id.item_vntour);
        itemCs = (LinearLayout) findViewById(R.id.item_cs);
        selectAll = new SelectAll(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("Hồ Sơ");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        String name = LoginActivity.userName;
        tvTen.setText(name);
        if(selectAll.anhKH(name) == null){
            return;
        }
        itemBookmark.setOnClickListener(v->{
            startActivity(new Intent(Profile_Activity.this , BookMartActivity.class));
        });
        itemTb.setOnClickListener(v->{
            startActivity(new Intent(this,TourKhamPhaActivity.class));
        });
        itemTtnd.setOnClickListener(v->{
            startActivity(new Intent(this,HoSoActivity.class));
        });
        imgAnh.setImageBitmap(BitmapFactory.decodeByteArray(selectAll.anhKH(name),0,selectAll.anhKH(name).length));
        System.out.println("zzzzzzzzzzzzzzzzzzz" + selectAll.anhKH(name));
        itemPhone.setOnClickListener(v ->{
            String phone = tvPhone.getText().toString();
            String call = "tel:" + phone.trim();
            Intent i = new Intent(Intent.ACTION_DIAL);
            i.setData(Uri.parse(call));
            startActivity(i);
        });
        itemDx.setOnClickListener(v -> {
            AlertDialog dialog = new AlertDialog.Builder(this).setTitle("Thông Báo").setMessage("Bạn có chắc muốn đăng xuất không").setPositiveButton("Ok" , null).setNegativeButton("Cancel" , null).show();
            Button positiveButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
            positiveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(Profile_Activity.this,LoginActivity.class));
                }
            });
        });
        itemVntour.setOnClickListener(v -> {
            startActivity(new Intent(this,VeMyVnTourActivity.class));
        });
        itemCs.setOnClickListener(v -> {
            startActivity(new Intent(this,CSBMatActivity.class));
        });

    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        String name = LoginActivity.userName;
        tvTen.setText(name);
        if(selectAll.anhKH(name) == null){
            return;
        }
        imgAnh.setImageBitmap(BitmapFactory.decodeByteArray(selectAll.anhKH(name),0,selectAll.anhKH(name).length));

    }
}