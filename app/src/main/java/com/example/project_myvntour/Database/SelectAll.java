package com.example.project_myvntour.Database;

import android.content.Context;

import com.example.project_myvntour.Mode.LoaiKhachSanj;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SelectAll {
    private Context context;
    private Connect connectSqlSever;
    private Connection connection;

    public SelectAll(Context context) {
        this.context = context;
        this.connectSqlSever = new Connect();
        this.connection = connectSqlSever.getCollection();
    }

    public List<LoaiKhachSanj> getListLoaiKhachSan(){
        List<LoaiKhachSanj> list = new ArrayList<LoaiKhachSanj>();
        LoaiKhachSanj loai;
        try {
            if(connection !=null){
                String sql = "select * from LOAIHINH";
                Statement st = connection.createStatement();
                ResultSet re = st.executeQuery(sql);
                while(re.next()) {
                    loai = new LoaiKhachSanj();
                    loai.setId(re.getInt("MaLH"));
                    loai.setTenLoaiKhachSanj(re.getString("TenLH"));
                    list.add(loai);
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

}
