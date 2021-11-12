package com.example.project_myvntour.Database;

import android.content.Context;
import android.util.Base64;

import com.example.project_myvntour.Mode.KhachHang;
import com.example.project_myvntour.Mode.KhachSan;
import com.example.project_myvntour.Mode.LoaiKhachSanj;
import com.example.project_myvntour.Mode.TIENNGHIDV;

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
    public List<KhachSan> getListKhachSan() {
        List<KhachSan> list = new ArrayList<KhachSan>();
        try {
            if(connection !=null){
                String sql = "\n" +
                        "select KHACHSAN.MaKS \n" +
                        ", TenKS \n" +
                        ",Hang \n" +
                        ", ChinhSachVS \n" +
                        ", DiaDiem ,\n" +
                        "Longitude ,\n" +
                        "Latitude,\n" +
                        "TimeDat,\n" +
                        "TimeTra ,\n" +
                        "SoLuongP ,\n" +
                        "MoTa,\n" +
                        "TrangThai ,\n" +
                        "LOAIHINH.TenLH,\n" +
                        "TIENNGHIHD.WifiSanh,\n" +
                        "WifiPhong,\n" +
                        "BeBoi,\n" +
                        "DauXe,\n" +
                        "Spa,\n" +
                        "VatNuoi,\n" +
                        "DieuHoa,\n" +
                        "NhaHang,\n" +
                        "Bar,\n" +
                        "Gym ,\n" +
                        "KHACHSAN.GiaDD\n" +
                        "from KHACHSAN join LOAIHINH on LOAIHINH.MaLH = KHACHSAN.MaLH\n" +
                        "join TIENNGHIHD on TIENNGHIHD.MaKS = KHACHSAN.MaKS";
                Statement st = connection.createStatement();
                ResultSet rs = st.executeQuery(sql);
                while(rs.next()) {
                    KhachSan ks = new KhachSan();
                    ks.setId(rs.getInt(1));
                    ks.setTenKhachSan(rs.getString(2));
                    ks.setSoSao(rs.getInt("Hang"));
                    ks.setChinhsachveSinh(String.valueOf(rs.getInt(4)));
                    ks.setDiaDiem(rs.getString(5));
                    ks.setKinhdo(rs.getDouble("Latitude"));
                    ks.setVido(rs.getDouble("Longitude"));
                    ks.setTimeNhan(rs.getString(8));
                    ks.setTimetra(rs.getString(9));
                    ks.setSoLuongPHong(rs.getInt(10));
                    ks.setMota(rs.getString(11));
                    ks.setTrangThaiLuu(rs.getInt(12));
                    ks.setLoaisachsan(rs.getString(13));
                    ks.setWifiSanh(rs.getInt(14));
                    ks.setWifiPhong(rs.getInt(15));
                    ks.setBeBoi(rs.getInt(16));
                    ks.setDauXe(rs.getInt(17));
                    ks.setSpa(rs.getInt(18));
                    ks.setVatNuoi(rs.getInt(19));
                    ks.setDieuHoa(rs.getInt(20));
                    ks.setNhaHang(rs.getInt(21));
                    ks.setBar(rs.getInt(22));
                    ks.setGym(rs.getInt(23));
                    ks.setGiaThue(rs.getString(24));
                    list.add(ks);
                }
            }
        }catch (Exception e){

        }
        return list;
    }

    public List<KhachSan> getListKhachSanByHotel(int id) {
        List<KhachSan> list = new ArrayList<KhachSan>();
        String sql = "";
        try {
            if(connection !=null){
                if(id == 1){
                    sql = "select KHACHSAN.MaKS , TenKS ,Hang , ChinhSachVS , DiaDiem ,Longitude ,Latitude,TimeDat,TimeTra ,SoLuongP ,MoTa,TrangThai ,LOAIHINH.TenLH,\n" +
                            "TIENNGHIHD.WifiSanh,WifiPhong,BeBoi,DauXe,Spa,VatNuoi,DieuHoa,NhaHang,Bar,Gym ,\n" +
                            "KHACHSAN.GiaDD\n" +
                            "from KHACHSAN join LOAIHINH on LOAIHINH.MaLH = KHACHSAN.MaLH\n" +
                            "join TIENNGHIHD on TIENNGHIHD.MaKS = KHACHSAN.MaKS\n" +
                            "\n" +
                            "where LOAIHINH.TenLh like 'Hotel' ";
                }else if(id == 2){
                    sql = "select KHACHSAN.MaKS , TenKS ,Hang , ChinhSachVS , DiaDiem ,Longitude ,Latitude,TimeDat,TimeTra ,SoLuongP ,MoTa,TrangThai ,LOAIHINH.TenLH,\n" +
                            "TIENNGHIHD.WifiSanh,WifiPhong,BeBoi,DauXe,Spa,VatNuoi,DieuHoa,NhaHang,Bar,Gym ,\n" +
                            "KHACHSAN.GiaDD\n" +
                            "from KHACHSAN join LOAIHINH on LOAIHINH.MaLH = KHACHSAN.MaLH\n" +
                            "join TIENNGHIHD on TIENNGHIHD.MaKS = KHACHSAN.MaKS\n" +
                            "\n" +
                            "where LOAIHINH.TenLh like 'Vila' ";
                }else if(id == 3) {
                    sql = "select KHACHSAN.MaKS , TenKS ,Hang , ChinhSachVS , DiaDiem ,Longitude ,Latitude,TimeDat,TimeTra ,SoLuongP ,MoTa,TrangThai ,LOAIHINH.TenLH,\n" +
                            "TIENNGHIHD.WifiSanh,WifiPhong,BeBoi,DauXe,Spa,VatNuoi,DieuHoa,NhaHang,Bar,Gym ,\n" +
                            "KHACHSAN.GiaDD\n" +
                            "from KHACHSAN join LOAIHINH on LOAIHINH.MaLH = KHACHSAN.MaLH\n" +
                            "join TIENNGHIHD on TIENNGHIHD.MaKS = KHACHSAN.MaKS\n" +
                            "\n" +
                            "where LOAIHINH.TenLh like 'Resort' ";
                }else if(id == 4){
                    sql = "select KHACHSAN.MaKS , TenKS ,Hang , ChinhSachVS , DiaDiem ,Longitude ,Latitude,TimeDat,TimeTra ,SoLuongP ,MoTa,TrangThai ,LOAIHINH.TenLH,\n" +
                            "TIENNGHIHD.WifiSanh,WifiPhong,BeBoi,DauXe,Spa,VatNuoi,DieuHoa,NhaHang,Bar,Gym ,\n" +
                            "KHACHSAN.GiaDD\n" +
                            "from KHACHSAN join LOAIHINH on LOAIHINH.MaLH = KHACHSAN.MaLH\n" +
                            "join TIENNGHIHD on TIENNGHIHD.MaKS = KHACHSAN.MaKS\n" +
                            "\n" +
                            "where LOAIHINH.TenLh like 'Apartment' ";
                }
                Statement st = connection.createStatement();
                ResultSet rs = st.executeQuery(sql);
                while(rs.next()) {
                    KhachSan ks = new KhachSan();
                    ks.setId(rs.getInt(1));
                    ks.setTenKhachSan(rs.getString(2));
                    ks.setSoSao(rs.getInt("Hang"));
                    ks.setChinhsachveSinh(String.valueOf(rs.getInt(4)));
                    ks.setDiaDiem(rs.getString(5));
                    ks.setKinhdo(rs.getDouble("Latitude"));
                    ks.setVido(rs.getDouble("Longitude"));
                    ks.setTimeNhan(rs.getString(8));
                    ks.setTimetra(rs.getString(9));
                    ks.setSoLuongPHong(rs.getInt(10));
                    ks.setMota(rs.getString(11));
                    ks.setTrangThaiLuu(rs.getInt(12));
                    ks.setLoaisachsan(rs.getString(13));
                    ks.setWifiSanh(rs.getInt("WifiSanh"));
                    ks.setWifiPhong(rs.getInt("WifiPhong"));
                    ks.setBeBoi(rs.getInt("BeBoi"));
                    ks.setDauXe(rs.getInt("DauXe"));
                    ks.setSpa(rs.getInt("Spa"));
                    ks.setVatNuoi(rs.getInt("VatNuoi"));
                    ks.setDieuHoa(rs.getInt("DieuHoa"));
                    ks.setNhaHang(rs.getInt("NhaHang"));
                    ks.setBar(rs.getInt("Bar"));
                    ks.setGym(rs.getInt("Gym"));
                    ks.setGiaThue(rs.getString(24));
                    list.add(ks);
                }
            }
        }catch (Exception e){

        }
        return list;
    }
    public List<byte[]> getListPhotoById(int id) {
        List<byte[]> list = new ArrayList<byte[]>();
        try {
            if (this.connection != null) {
                String sqlQuery = "select HINHANH.HinhAnh from KHACHSAN join HINHANH on  HINHANH.MaKS = KHACHSAN.MaKS where KHACHSAN.MaKS =" + id;
                Statement statement = this.connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sqlQuery);
                while (resultSet.next()) {
                    byte[] bytes = resultSet.getBytes("HinhAnh");
                    list.add(bytes);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;


    }


    public List<byte[]> getListPhotById(int id) {
        List<byte[]> list = new ArrayList<byte[]>();
        try {
            if (connection!=null){
                Statement st = connection.createStatement();
                ResultSet rs ;
                String sql = "Select * from HINHANH where HINHANH.MaKS =" + id;
                rs = st.executeQuery(sql);
                while (rs.next()) {
                    list.add(rs.getBytes("HinhAnh"));
                }

            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<TIENNGHIDV> getListTIenIchTheoIdKhachSan(int id) {
        List<TIENNGHIDV> list = new ArrayList<>();
        try {
            if (connection!=null){
                Statement st = connection.createStatement();
                ResultSet rs ;
                String sql = "\n" +
                        "Select * from TIENNGHIDV where TIENNGHIDV.MaKS =" + id;
                rs = st.executeQuery(sql);

                while (rs.next()) {
                    TIENNGHIDV tien = new TIENNGHIDV();
                    tien.setTenDichVu(rs.getString("TenDV"));
                    if(rs.getString("MoTa") == null){
                        tien.setMote("");
                    }else{
                        tien.setMote(rs.getString("MoTa"));
                    }
                    list.add(tien);
                }

            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
