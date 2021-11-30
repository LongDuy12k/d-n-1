package com.example.project_myvntour.ActivityMaintain;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import com.example.project_myvntour.Adapter.AdpaterRevenue;
import com.example.project_myvntour.R;
import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RevenuaActivity extends AppCompatActivity {
    MaterialToolbar toolbar;
    ExpandableListView listView;
    List<String> Title;
    HashMap<String, List<String>> topics;
    AdpaterRevenue setAdapter;
    EditText etSearch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_revenua);
        toolbar = findViewById(R.id.tool_bar2);
        listView = findViewById(R.id.list_support);
        etSearch = findViewById(R.id.etSearch);
        fillData();
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("Trung tâm hỗ trợ");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        setAdapter = new AdpaterRevenue(this,Title,topics);
        listView.setAdapter(setAdapter);
        check();
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                setAdapter.getFilter().filter(etSearch.getText().toString());
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                setAdapter.getFilter().filter(etSearch.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                setAdapter.getFilter().filter(etSearch.getText().toString());
            }
        });

    }

    private void check() {
        listView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            int check = -1;
            @Override
            public void onGroupExpand(int groupPosition) {
                if(check != -1 && groupPosition != check){
                    listView.collapseGroup(check);
                }
                check = groupPosition;
            }
        });
    }


    public void fillData(){
        Title = new ArrayList<>();
        topics = new HashMap<>();

        Title.add("Các câu hỏi thường gặp");
        Title.add("Thông tin chung");
        Title.add("Tìm khách sạn");
        Title.add("Đặt phòng khách sạn");
        Title.add("Giá phòng khách sạn");
        Title.add("Bảo hiểm du lịch");
        Title.add("Hủy đặt chỗ, đổi phòng & hoàn tiền");
        Title.add("Tiện nghi khách sạn");
        Title.add("Yêu cầu đặc biệt");
        Title.add("Chính sách khách sạn");

        List<String> c1 = new ArrayList<>();
        c1.add("Cách hủy phòng và hoàn tiền cho đặt phòng khách sạn");
        c1.add("Làm thế nào tôi có thể gửi lại vé điện tử của mình");
        c1.add("Cách đặt khách sạn");
        c1.add("Làm cách nào để kiểm tra trạng thái hoàn tiền của tôi");
        List<String> c2 = new ArrayList<>();
        c2.add("Quan hệ đối tác của MyVntour với khách sạn");
        c2.add("Cách đăng ký nơi lưu trú của tôi trên MyVntour");
        List<String> c3 = new ArrayList<>();
        c3.add("Làm thế nào để tôi tìm được khách sạn ưng ý nhất cho điểm đến của mình");
        c3.add("Tìm khách sạn trong một khu vực nhất định");
        c3.add("Không hoàn tiền và miễn phí đặt phòng nghĩa là gì");
        List<String> c4 = new ArrayList<>();
        c4.add("Cách đặt khách sạn");
        c4.add("Cách đổi lịch đặt phòng khách sạn của tôi");
        List<String> c5 = new ArrayList<>();
        c5.add("Giá phòng khách sạn bao gồm những gì");
        List<String> c6 = new ArrayList<>();
        c6.add("Giá phòng đã bao gồm phí bảo hiểm du lịch chưa?");
        List<String> c7 = new ArrayList<>();
        c7.add("Cách hủy phòng và hoàn tiền cho đặt phòng khách sạn");
        c7.add("Làm thế nào tôi có thể gửi lại vé điện tử của mình");
        c7.add("Làm cách nào để kiểm tra trạng thái hoàn tiền của tôi");
        List<String> c8 = new ArrayList<>();
        c8.add("Tôi có thể xem tiện nghi của khách sạn như thế nào?");
        List<String> c9 = new ArrayList<>();
        c9.add("Đặt phòng cho hút thuốc");
        c9.add("Yêu cầu giường phụ");
        c9.add("Nhận phòng trễ");
        List<String> c10 = new ArrayList<>();
        c10.add("Chính sách vệ sinh ở khách sạn có đảm bảo tiêu chuẩn");
        topics.put(Title.get(0),c1);
        topics.put(Title.get(1),c2);
        topics.put(Title.get(2),c3);
        topics.put(Title.get(3),c4);
        topics.put(Title.get(4),c5);
        topics.put(Title.get(5),c6);
        topics.put(Title.get(6),c7);
        topics.put(Title.get(7),c8);
        topics.put(Title.get(8),c9);
        topics.put(Title.get(9),c10);

    }

}