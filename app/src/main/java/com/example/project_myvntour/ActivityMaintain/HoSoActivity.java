package com.example.project_myvntour.ActivityMaintain;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.project_myvntour.Database.SelectAll;
import com.example.project_myvntour.Mode.KhachHang;
import com.example.project_myvntour.R;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.textfield.TextInputEditText;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import de.hdodenhof.circleimageview.CircleImageView;

public class HoSoActivity extends AppCompatActivity {
    private MaterialToolbar toolBar2;
    private CircleImageView ivProfile;
    private ImageView btnChupAnh;
    private TextInputEditText etHoten;
    private TextInputEditText etEmail;
    private TextInputEditText etDiaChi;
    private TextInputEditText etSoDienThoai;
    private Button btnLuuLai;
    private SelectAll mSelectAll;
    private KhachHang khach;
    final int REQUEST_CODE_GALLERY = 999;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ho_so);




        toolBar2 = (MaterialToolbar) findViewById(R.id.tool_bar2);
        setSupportActionBar(toolBar2);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("Thông tin tài khoản");
        ivProfile = (CircleImageView) findViewById(R.id.ivProfile);
        btnChupAnh = (ImageView) findViewById(R.id.btnChupAnh);
        etHoten = (TextInputEditText) findViewById(R.id.etHoten);
        etEmail = (TextInputEditText) findViewById(R.id.etEmail);
        etDiaChi = (TextInputEditText) findViewById(R.id.etDiaChi);
        etSoDienThoai = (TextInputEditText) findViewById(R.id.etSoDienThoai);
        btnLuuLai = (Button) findViewById(R.id.btnLuuLai);

        mSelectAll= new SelectAll(this);
        khach = mSelectAll.getKhacHang(LoginActivity.userName);
        showData();
        btnChupAnh.setOnClickListener(v->{
            ActivityCompat.requestPermissions(
                    HoSoActivity.this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    REQUEST_CODE_GALLERY
            );
        });
        btnLuuLai.setOnClickListener(v->{
            mSelectAll.updateInFoUser(LoginActivity.userName , etHoten.getText().toString() , etEmail.getText().toString() , etDiaChi.getText().toString() ,etSoDienThoai.getText().toString() ,  imageViewToByte(ivProfile) );
            Toast.makeText(this, "Lưu thành công", Toast.LENGTH_SHORT).show();
            onBackPressed();
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode == REQUEST_CODE_GALLERY && resultCode == RESULT_OK && data != null){
            Uri uri = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);

                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                ivProfile.setImageBitmap(bitmap);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if(requestCode == REQUEST_CODE_GALLERY){
            if(grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_CODE_GALLERY);
            }
            else {
                Toast.makeText(getApplicationContext(), "You don't have permission to access file location!", Toast.LENGTH_SHORT).show();
            }
            return;
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
    public static byte[] imageViewToByte(ImageView image) {
        Bitmap bitmap = ((BitmapDrawable)image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        Bitmap resized = Bitmap.createScaledBitmap(bitmap, (int)(bitmap.getWidth()*0.8), (int)(bitmap.getHeight()*0.8), true);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }
    public void showData(){
        etDiaChi.setText(khach.getDiachir());
        etHoten.setText(khach.getCmnd());
        etSoDienThoai.setText(khach.getSdt());
        etEmail.setText(khach.getEmail());
        if(khach.getAnhKhachHang().length >0){
            ivProfile.setImageBitmap(BitmapFactory.decodeByteArray(khach.getAnhKhachHang() , 0 , khach.getAnhKhachHang().length));
        }

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}