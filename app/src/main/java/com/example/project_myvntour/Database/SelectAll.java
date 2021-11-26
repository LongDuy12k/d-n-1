package com.example.project_myvntour.Database;

import android.app.DatePickerDialog;
import android.content.Context;
import android.util.Base64;
import android.view.View;
import android.widget.DatePicker;

import com.example.project_myvntour.Mode.KhachHang;
import com.example.project_myvntour.Mode.KhachSan;
import com.example.project_myvntour.Mode.LoaiKhachSanj;
import com.example.project_myvntour.Mode.Phong;
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
                        "select KHACHSAN.MaKS , TenKS ,Hang , ChinhSachVS , DiaDiem ,Longitude ,Latitude,TimeDat,TimeTra ,SoLuongP ,MoTa ,LOAIHINH.TenLH,GiaDD,\n" +
                        "TIENNGHIHD.WifiSanh,WifiPhong,BeBoi,DauXe,Spa,VatNuoi,DieuHoa,NhaHang,Bar,Gym ,\n" +
                        "CHUKHACHSAN.Anh , CHUKHACHSAN.SDT , CHUKHACHSAN.Ten,\n" +
                        "CHECKTT.TrangThai\n" +
                        "from KHACHSAN join LOAIHINH on LOAIHINH.MaLH = KHACHSAN.MaLH\n" +
                        "join TIENNGHIHD on TIENNGHIHD.MaKS = KHACHSAN.MaKS\n" +
                        "join CHUKHACHSAN on CHUKHACHSAN.MaKS = KHACHSAN.MaKS\n" +
                        "join CHECKTT on CHECKTT.MaKS = KHACHSAN.MaKS";
                Statement st = connection.createStatement();
                ResultSet rs = st.executeQuery(sql);
                while(rs.next()) {
                    KhachSan ks = new KhachSan();
                    ks.setId(rs.getInt("MaKS"));
                    ks.setTenKhachSan(rs.getString("TenKS"));
                    ks.setSoSao(rs.getInt("Hang"));
                    ks.setChinhsachveSinh(String.valueOf(rs.getInt("ChinhSachVS")));
                    ks.setDiaDiem(rs.getString("DiaDiem"));
                    ks.setKinhdo(rs.getDouble("Latitude"));
                    ks.setVido(rs.getDouble("Longitude"));
                    ks.setTimeNhan(rs.getString("TimeDat"));
                    ks.setTimetra(rs.getString("TimeTra"));
                    ks.setSoLuongPHong(rs.getInt("SoLuongP"));
                    ks.setMota(rs.getString("MoTa"));
                    ks.setLoaisachsan(rs.getString("TenLH"));
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
                    ks.setGiaThue(rs.getString("GiaDD"));
                    ks.setAnhchukhachsan(rs.getBytes("Anh"));
                    ks.setSoDienThoaiChuKhachSan(rs.getString("SDT"));
                    ks.setTenChuKhachSan(rs.getString("Ten"));
                    ks.setTrangThaiLuu(rs.getInt("TrangThai"));
                    list.add(ks);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

    public List<Phong> getListPhong(int id){
        List<Phong> list = new ArrayList<>();
        try {
            if(connection!=null){
                String sql = "select * from PHONG where MaKS ="+id;
                Statement st = connection.createStatement();
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()){
                    Phong phong = new Phong();
                    phong.setId_Phong(rs.getInt(1));
                    phong.setId_Ks(rs.getInt(2));
                    phong.setTenPhong(rs.getString(3));
                    phong.setSoPhong(rs.getInt(4));
                    phong.setDienTich(rs.getInt(5));
                    phong.setGia(rs.getInt(6));
                    phong.setSoGiuong(rs.getInt(7));
                    phong.setNguoiLon(rs.getInt(8));
                    phong.setTreNho(rs.getInt(9));
                    phong.setMoTa(rs.getString(10));
                    list.add(phong);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

    public List<KhachSan> getListKhachSanByHotel(int id) {
        List<KhachSan> list = new ArrayList<KhachSan>();
        String sql = "";
        try {
            if(connection !=null){
                if(id == 1){
                    sql = "select KHACHSAN.MaKS , TenKS ,Hang , ChinhSachVS , DiaDiem ,Longitude ,Latitude,TimeDat,TimeTra ,SoLuongP ,MoTa ,LOAIHINH.TenLH,GiaDD,\n" +
                            "TIENNGHIHD.WifiSanh,WifiPhong,BeBoi,DauXe,Spa,VatNuoi,DieuHoa,NhaHang,Bar,Gym ,\n" +
                            "CHUKHACHSAN.Anh , CHUKHACHSAN.SDT , CHUKHACHSAN.Ten,\n" +
                            "CHECKTT.TrangThai\n" +
                            "from KHACHSAN join LOAIHINH on LOAIHINH.MaLH = KHACHSAN.MaLH\n" +
                            "join TIENNGHIHD on TIENNGHIHD.MaKS = KHACHSAN.MaKS\n" +
                            "join CHUKHACHSAN on CHUKHACHSAN.MaKS = KHACHSAN.MaKS\n" +
                            "join CHECKTT on CHECKTT.MaKS = KHACHSAN.MaKS\n" +
                            "where LOAIHINH.TenLh like 'Hotel' ";
                }else if(id == 2){
                    sql = "select KHACHSAN.MaKS , TenKS ,Hang , ChinhSachVS , DiaDiem ,Longitude ,Latitude,TimeDat,TimeTra ,SoLuongP ,MoTa ,LOAIHINH.TenLH,GiaDD,\n" +
                            "TIENNGHIHD.WifiSanh,WifiPhong,BeBoi,DauXe,Spa,VatNuoi,DieuHoa,NhaHang,Bar,Gym ,\n" +
                            "CHUKHACHSAN.Anh , CHUKHACHSAN.SDT , CHUKHACHSAN.Ten,\n" +
                            "CHECKTT.TrangThai\n" +
                            "from KHACHSAN join LOAIHINH on LOAIHINH.MaLH = KHACHSAN.MaLH\n" +
                            "join TIENNGHIHD on TIENNGHIHD.MaKS = KHACHSAN.MaKS\n" +
                            "join CHUKHACHSAN on CHUKHACHSAN.MaKS = KHACHSAN.MaKS\n" +
                            "join CHECKTT on CHECKTT.MaKS = KHACHSAN.MaKS\n" +
                            "where LOAIHINH.TenLh like 'Vila' ";
                }else if(id == 3) {
                    sql = "select KHACHSAN.MaKS , TenKS ,Hang , ChinhSachVS , DiaDiem ,Longitude ,Latitude,TimeDat,TimeTra ,SoLuongP ,MoTa ,LOAIHINH.TenLH,GiaDD,\n" +
                            "TIENNGHIHD.WifiSanh,WifiPhong,BeBoi,DauXe,Spa,VatNuoi,DieuHoa,NhaHang,Bar,Gym ,\n" +
                            "CHUKHACHSAN.Anh , CHUKHACHSAN.SDT , CHUKHACHSAN.Ten,\n" +
                            "CHECKTT.TrangThai\n" +
                            "from KHACHSAN join LOAIHINH on LOAIHINH.MaLH = KHACHSAN.MaLH\n" +
                            "join TIENNGHIHD on TIENNGHIHD.MaKS = KHACHSAN.MaKS\n" +
                            "join CHUKHACHSAN on CHUKHACHSAN.MaKS = KHACHSAN.MaKS\n" +
                            "join CHECKTT on CHECKTT.MaKS = KHACHSAN.MaKS\n" +
                            "where LOAIHINH.TenLh like 'Resort' ";
                }else if(id == 4){
                    sql = "select KHACHSAN.MaKS , TenKS ,Hang , ChinhSachVS , DiaDiem ,Longitude ,Latitude,TimeDat,TimeTra ,SoLuongP ,MoTa ,LOAIHINH.TenLH,GiaDD,\n" +
                            "TIENNGHIHD.WifiSanh,WifiPhong,BeBoi,DauXe,Spa,VatNuoi,DieuHoa,NhaHang,Bar,Gym ,\n" +
                            "CHUKHACHSAN.Anh , CHUKHACHSAN.SDT , CHUKHACHSAN.Ten,\n" +
                            "CHECKTT.TrangThai\n" +
                            "from KHACHSAN join LOAIHINH on LOAIHINH.MaLH = KHACHSAN.MaLH\n" +
                            "join TIENNGHIHD on TIENNGHIHD.MaKS = KHACHSAN.MaKS\n" +
                            "join CHUKHACHSAN on CHUKHACHSAN.MaKS = KHACHSAN.MaKS\n" +
                            "join CHECKTT on CHECKTT.MaKS = KHACHSAN.MaKS\n" +
                            "where LOAIHINH.TenLh like 'Apartment' ";
                }
                Statement st = connection.createStatement();
                ResultSet rs = st.executeQuery(sql);
                while(rs.next()) {
                    KhachSan ks = new KhachSan();
                    ks.setId(rs.getInt("MaKS"));
                    ks.setTenKhachSan(rs.getString("TenKS"));
                    ks.setSoSao(rs.getInt("Hang"));
                    ks.setChinhsachveSinh(String.valueOf(rs.getInt("ChinhSachVS")));
                    ks.setDiaDiem(rs.getString("DiaDiem"));
                    ks.setKinhdo(rs.getDouble("Latitude"));
                    ks.setVido(rs.getDouble("Longitude"));
                    ks.setTimeNhan(rs.getString("TimeDat"));
                    ks.setTimetra(rs.getString("TimeTra"));
                    ks.setSoLuongPHong(rs.getInt("SoLuongP"));
                    ks.setMota(rs.getString("MoTa"));
                    ks.setLoaisachsan(rs.getString("TenLH"));
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
                    ks.setGiaThue(rs.getString("GiaDD"));
                    ks.setAnhchukhachsan(rs.getBytes("Anh"));
                    ks.setSoDienThoaiChuKhachSan(rs.getString("SDT"));
                    ks.setTenChuKhachSan(rs.getString("Ten"));
                    ks.setTrangThaiLuu(rs.getInt("TrangThai"));
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

    public List<byte[]> getListAnhPhong(int id) {
        List<byte[]> list = new ArrayList<byte[]>();
        try {
            if (connection!=null){
                Statement st = connection.createStatement();
                ResultSet rs ;
                String sql = "Select * from HINHANHPHONG where MaPhong =" + id;
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

    public Boolean CheckLogin(String u , String p){
        try {
                String sql = "select * from KHACHHANG where UserName = '"+u+"' and Pass ='"+p+"' ";
                Statement st = connection.createStatement();
                ResultSet rs = st.executeQuery(sql);
                if(rs.next()){
                    return true;
                }
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public Boolean Signin(String e, String u, String p){
        try {
            String sql = "insert into KHACHHANG (Email , UserName, Pass)\n" +
                    "values ('"+e+"','"+u+"','"+p+"')";
            Statement st = connection.createStatement();
            if(st.executeUpdate(sql)>0){
                return true;
            }
        }catch (Exception ee){
            ee.printStackTrace();
        }
        return false;
    }

    public boolean CheckUser(String u){
        try {
            String sql = "select * from KHACHHANG where UserName = '"+u+"'";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if(rs.next()){
                return true;
            }
            if(st.executeUpdate(sql)>0){
                return false;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
}
