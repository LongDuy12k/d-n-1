package com.example.project_myvntour.Database;

import android.content.Context;

import java.sql.Connection;

public class SelectAll {
    private Context context;
    private Connect connectSqlSever;
    private Connection connection;

    public SelectAll(Context context) {
        this.context = context;
        this.connectSqlSever = new Connect();
        this.connection = connectSqlSever.getCollection();
    }
    // viết hàm truy vấn ở đây

}
