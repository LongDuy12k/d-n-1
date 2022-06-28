package com.example.project_myvntour.Database;

import android.os.StrictMode;

import java.sql.Connection;
import java.sql.DriverManager;

public class Connect {
    Connection connection;
    public Connection getCollection(){
        String ip = "192.168.21.104", port = "1433", user = "abc", pass = "123456", db = "MYVNTOUR";
        StrictMode.ThreadPolicy threadPolicy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(threadPolicy);
        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            String connectUrl = "jdbc:jtds:sqlserver://" + ip + ":" + port + ";databasename=" + db +";user=" + user +";password=" + pass +";";
            this.connection = DriverManager.getConnection(connectUrl);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }
}
