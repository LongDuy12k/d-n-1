package com.example.project_myvntour.Database;

import android.app.DatePickerDialog;
import android.content.Context;
import android.util.Base64;
import android.view.View;
import android.widget.DatePicker;

import com.example.project_myvntour.Mode.HoaDon;
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
                        "select DISTINCT KHACHSAN.MaKS , TenKS ,Hang , ChinhSachVS , DiaDiem ,Longitude ,Latitude,TimeDat,TimeTra ,SoLuongP ,MoTa ,LOAIHINH.TenLH,GiaDD,\n" +
                        "TIENNGHIHD.WifiSanh,WifiPhong,BeBoi,DauXe,Spa,VatNuoi,DieuHoa,NhaHang,Bar,Gym ,\n" +
                        " CHUKHACHSAN.SDT , CHUKHACHSAN.Ten\n" +
                        "\n" +
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
                    ks.setGiaThue(rs.getInt("GiaDD"));
                  //  ks.setAnhchukhachsan(rs.getBytes("Anh"));
                    ks.setSoDienThoaiChuKhachSan(rs.getString("SDT"));
                    ks.setTenChuKhachSan(rs.getString("Ten"));
                   // ks.setTrangThaiLuu(rs.getInt("TrangThai"));
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
                    sql = "select DISTINCT KHACHSAN.MaKS , TenKS ,Hang , ChinhSachVS , DiaDiem ,Longitude ,Latitude,TimeDat,TimeTra ,SoLuongP ,MoTa ,LOAIHINH.TenLH,GiaDD,\n" +
                            "TIENNGHIHD.WifiSanh,WifiPhong,BeBoi,DauXe,Spa,VatNuoi,DieuHoa,NhaHang,Bar,Gym ,\n" +
                            " CHUKHACHSAN.SDT , CHUKHACHSAN.Ten\n" +
                            "\n" +
                            "from KHACHSAN join LOAIHINH on LOAIHINH.MaLH = KHACHSAN.MaLH\n" +
                            "join TIENNGHIHD on TIENNGHIHD.MaKS = KHACHSAN.MaKS\n" +
                            "join CHUKHACHSAN on CHUKHACHSAN.MaKS = KHACHSAN.MaKS\n" +
                            "join CHECKTT on CHECKTT.MaKS = KHACHSAN.MaKS\n" +
                            "where LOAIHINH.TenLh like 'Hotel' ";
                }else if(id == 2){
                    sql = "select DISTINCT KHACHSAN.MaKS , TenKS ,Hang , ChinhSachVS , DiaDiem ,Longitude ,Latitude,TimeDat,TimeTra ,SoLuongP ,MoTa ,LOAIHINH.TenLH,GiaDD,\n" +
                            "TIENNGHIHD.WifiSanh,WifiPhong,BeBoi,DauXe,Spa,VatNuoi,DieuHoa,NhaHang,Bar,Gym ,\n" +
                            "  CHUKHACHSAN.SDT , CHUKHACHSAN.Ten\n" +
                            "\n" +
                            "from KHACHSAN join LOAIHINH on LOAIHINH.MaLH = KHACHSAN.MaLH\n" +
                            "join TIENNGHIHD on TIENNGHIHD.MaKS = KHACHSAN.MaKS\n" +
                            "join CHUKHACHSAN on CHUKHACHSAN.MaKS = KHACHSAN.MaKS\n" +
                            "join CHECKTT on CHECKTT.MaKS = KHACHSAN.MaKS\n" +
                            "where LOAIHINH.TenLh like 'Vila' ";
                }else if(id == 3) {
                    sql = "select DISTINCT KHACHSAN.MaKS , TenKS ,Hang , ChinhSachVS , DiaDiem ,Longitude ,Latitude,TimeDat,TimeTra ,SoLuongP ,MoTa ,LOAIHINH.TenLH,GiaDD,\n" +
                            "TIENNGHIHD.WifiSanh,WifiPhong,BeBoi,DauXe,Spa,VatNuoi,DieuHoa,NhaHang,Bar,Gym ,\n" +
                            " CHUKHACHSAN.SDT , CHUKHACHSAN.Ten\n" +
                            "\n" +
                            "from KHACHSAN join LOAIHINH on LOAIHINH.MaLH = KHACHSAN.MaLH\n" +
                            "join TIENNGHIHD on TIENNGHIHD.MaKS = KHACHSAN.MaKS\n" +
                            "join CHUKHACHSAN on CHUKHACHSAN.MaKS = KHACHSAN.MaKS\n" +
                            "join CHECKTT on CHECKTT.MaKS = KHACHSAN.MaKS\n" +
                            "where LOAIHINH.TenLh like 'Resort' ";
                }else if(id == 4){
                    sql = "select DISTINCT KHACHSAN.MaKS , TenKS ,Hang , ChinhSachVS , DiaDiem ,Longitude ,Latitude,TimeDat,TimeTra ,SoLuongP ,MoTa ,LOAIHINH.TenLH,GiaDD,\n" +
                            "TIENNGHIHD.WifiSanh,WifiPhong,BeBoi,DauXe,Spa,VatNuoi,DieuHoa,NhaHang,Bar,Gym ,\n" +
                            " CHUKHACHSAN.SDT , CHUKHACHSAN.Ten\n" +
                            "\n" +
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
                    ks.setGiaThue(rs.getInt("GiaDD"));
                  //  ks.setAnhchukhachsan(rs.getBytes("Anh"));
                    ks.setSoDienThoaiChuKhachSan(rs.getString("SDT"));
                    ks.setTenChuKhachSan(rs.getString("Ten"));
                   // ks.setTrangThaiLuu(rs.getInt("TrangThai"));
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

    public Phong getPHongById(int id_phong) {
        Phong phong = new Phong();
        try {
            if(connection!=null){
                String sql ="select * from TIENNGHIPHONG where TIENNGHIPHONG.MaPhong =" + id_phong;
                Statement st = connection.createStatement();
                ResultSet rs = st.executeQuery(sql);
                while(rs.next()){
                    phong.setDieuhoa(rs.getInt(3));
                    phong.setTivi(rs.getInt(4));
                    phong.setKetantoan(rs.getInt(5));
                    phong.setTulanh(rs.getInt(6));
                    phong.setBep(rs.getInt(7));
                    phong.setBan(rs.getInt(8));
                    phong.setWifi(rs.getInt(9));
                    phong.setDichvuphong(rs.getInt(10));
                    phong.setMaygiat(rs.getInt(11));
                    phong.setMaysaytoc(rs.getInt(12));
                    phong.setBanui(rs.getInt(13));
                    phong.setKhonghutthuoc(rs.getInt(14));
                    phong.setBontam(rs.getInt(15));
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return phong;
    }

    public byte[] anhKH(String name) {
        byte[] anh = null;
        try {
            if(connection!=null){
                String sql ="select KHACHHANG.Anh from KHACHHANG where KHACHHANG.UserName like '"+name+"'" ;
                Statement st = connection.createStatement();
                ResultSet rs = st.executeQuery(sql);
                while(rs.next()){
                    anh = rs.getBytes(1);
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return anh;
    }

    public List<KhachSan> listTK(String toString) {
        List<KhachSan> list = new ArrayList<KhachSan>();
        try {
            if(connection !=null){
                String sql = "\n" +
                        "select DISTINCT KHACHSAN.MaKS , TenKS ,Hang , ChinhSachVS , DiaDiem ,Longitude ,Latitude,TimeDat,TimeTra ,SoLuongP ,MoTa ,LOAIHINH.TenLH,GiaDD,\n" +
                        "TIENNGHIHD.WifiSanh,WifiPhong,BeBoi,DauXe,Spa,VatNuoi,DieuHoa,NhaHang,Bar,Gym ,\n" +
                        " CHUKHACHSAN.SDT , CHUKHACHSAN.Ten\n" +
                        "\n" +
                        "from KHACHSAN join LOAIHINH on LOAIHINH.MaLH = KHACHSAN.MaLH\n" +
                        "join TIENNGHIHD on TIENNGHIHD.MaKS = KHACHSAN.MaKS\n" +
                        "join CHUKHACHSAN on CHUKHACHSAN.MaKS = KHACHSAN.MaKS\n" +
                        "join CHECKTT on CHECKTT.MaKS = KHACHSAN.MaKS where KHACHSAN.TenKS like '%"+toString+"%'";
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
                    ks.setGiaThue(rs.getInt("GiaDD"));
                 //   ks.setAnhchukhachsan(rs.getBytes("Anh"));
                    ks.setSoDienThoaiChuKhachSan(rs.getString("SDT"));
                    ks.setTenChuKhachSan(rs.getString("Ten"));
                    //ks.setTrangThaiLuu(rs.getInt("TrangThai"));
                    list.add(ks);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

    public List<KhachSan> getListKhachSanLoc(int i, int max) {
        List<KhachSan> list = new ArrayList<KhachSan>();
        try {
            if(connection !=null){
                String sql = "select DISTINCT KHACHSAN.MaKS , TenKS ,Hang , ChinhSachVS , DiaDiem ,Longitude ,Latitude,TimeDat,TimeTra ,SoLuongP ,MoTa ,LOAIHINH.TenLH,GiaDD,\n" +
                        "TIENNGHIHD.WifiSanh,WifiPhong,BeBoi,DauXe,Spa,VatNuoi,DieuHoa,NhaHang,Bar,Gym ,\n" +
                        " CHUKHACHSAN.SDT , CHUKHACHSAN.Ten\n" +
                        "\n" +
                        "from KHACHSAN join LOAIHINH on LOAIHINH.MaLH = KHACHSAN.MaLH\n" +
                        "join TIENNGHIHD on TIENNGHIHD.MaKS = KHACHSAN.MaKS\n" +
                        "join CHUKHACHSAN on CHUKHACHSAN.MaKS = KHACHSAN.MaKS\n" +
                        "join CHECKTT on CHECKTT.MaKS = KHACHSAN.MaKS where GiaDD between "+i+" and "+max+"";

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
                    ks.setGiaThue(rs.getInt("GiaDD"));
                 //   ks.setAnhchukhachsan(rs.getBytes("Anh"));
                    ks.setSoDienThoaiChuKhachSan(rs.getString("SDT"));
                    ks.setTenChuKhachSan(rs.getString("Ten"));
                 //   ks.setTrangThaiLuu(rs.getInt("TrangThai"));
                    list.add(ks);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

    public KhachHang getKhacHang(String username) {
        KhachHang khacHang = new KhachHang();
        try {
            if(connection!=null){
                String sql ="select * from KHACHHANG where KHACHHANG.UserName like '"+username+"'";
                Statement st = connection.createStatement();
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    khacHang.setId(rs.getInt(1));
                    khacHang.setEmail(rs.getString(2));
                    khacHang.setUsername(rs.getString(3));
                    khacHang.setPassword(rs.getString(4));
                    khacHang.setAnhKhachHang(rs.getBytes(5));
                    khacHang.setDiachir(rs.getString(6));
                    khacHang.setSdt(rs.getString(7));
                    khacHang.setGioitinh(rs.getInt(8));
                    khacHang.setCmnd(rs.getString(9));
                    khacHang.setIsAdmin(rs.getInt(10));
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return khacHang;
    }

    public KhachSan getGetKhachSanByIdPhong(int id_phong) {
        KhachSan khacHang = new KhachSan();
        try {
            if(connection!=null){
                String sql ="select * from KHACHSAN where KHACHSAN.MaKS = " + id_phong;
                Statement st = connection.createStatement();
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    khacHang.setId(rs.getInt(1));
                    khacHang.setIdLoai(rs.getInt(2));
                    khacHang.setTenKhachSan(rs.getString(3));
                    khacHang.setSoSao(rs.getInt(4));
                    khacHang.setTimeNhan(rs.getString(9));
                    khacHang.setTimetra(rs.getString(10));
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return khacHang;
    }

    public Phong getPHongByIdVer1(int maphongng) {
        Phong khacHang = new Phong();
        try {
            if(connection!=null){
                String sql ="select * from PHONG where PHONG.MaPhong = " + maphongng;
                Statement st = connection.createStatement();
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    khacHang.setTenPhong(rs.getString(3));
                   khacHang.setGia(rs.getInt(6));
                   khacHang.setNguoiLon(rs.getInt(8));
                   khacHang.setTreNho(rs.getInt(9));
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return khacHang;
    }

    public void insertPhieuThue(int parseInt, int parseInt1, int i, String datenow, String dateTomorrow, String hoten, String sdt, String email, String yeucaudacbiet) {

        try {
            if(connection!=null){
                String sql ="insert into PHIEUTHUEPHONG(MaKH , MaPhong , SoNguoiThue , NgayDen , NgayDi , HoTen , SDT , Email , YeuCauDB) values (\n" +
                        ""+parseInt+" ,"+parseInt1+" ,"+i+" , '"+datenow+"' , '"+dateTomorrow+"' , N'"+hoten+"' , '"+sdt+"' , N'"+email+"' , N'"+yeucaudacbiet+"'\n" +
                        ")\n" ;
                Statement st = connection.createStatement();
                st.execute(sql);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public int getIdUser(String userName) {
        int id = 0;
        try {
            if(connection!=null){
                String sql ="select * from KHACHHANG where KHACHHANG.UserName like '"+userName+"' " ;
                Statement st = connection.createStatement();
               ResultSet rs = st.executeQuery(sql);
               while (rs.next()) {
                   id = rs.getInt(1);
               }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return id;

    }

    public List<HoaDon> getListHoaDaon(int idUsername) {
        List<HoaDon> list = new ArrayList<>();
        try {
            if(connection!=null){
                String sql ="select PHIEUTHUEPHONG.MaPT , PHIEUTHUEPHONG.MaPhong ,SoNguoiThue  ,NgayDen , NgayDi , HoTen ,\n" +
                        "PHIEUTHUEPHONG.SDT , PHIEUTHUEPHONG.Email , YeuCauDB , \n" +
                        "KHACHSAN.TimeDat , KHACHSAN.TimeTra , PHONG.SoGiuong ,PHONG.TenPhong , KHACHSAN.TenKS , PHONG.Gia ,KHACHSAN.MaKS\n" +
                        "\n" +
                        "from PHIEUTHUEPHONG \n" +
                        "join PHONG on PHONG.MaPhong = PHIEUTHUEPHONG.MaPhong\n" +
                        "join KHACHSAN on KHACHSAN.MaKS  = PHONG.MaKS\n" +
                        "join KHACHHANG on KHACHHANG.MaKH = PHIEUTHUEPHONG.MaKH\n" +
                        "where KHACHHANG.MaKH = "+idUsername ;
                Statement st = connection.createStatement();
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                  HoaDon hoa = new HoaDon();
                    hoa.setId(rs.getInt(1));
                    hoa.setMaPhong(rs.getInt(16));
                    hoa.setSoNguoiLon(rs.getInt(3));
                    hoa.setNgayden(rs.getString(4));
                    hoa.setNgaydi(rs.getString(5));
                    hoa.setTennguoidat(rs.getString(6));
                    hoa.setSodienthoai(rs.getString(7));
                    hoa.setEmail(rs.getString(8));
                    hoa.setYeuCauDB(rs.getString(9));
                    hoa.setGionhanphong(rs.getString(10));
                    hoa.setGiotraphong(rs.getString(11));
                    hoa.setSoGiuong(rs.getInt(12));
                    hoa.setTenPhong(rs.getString(13));
                    hoa.setTenKhachSan(rs.getString(14));
                    hoa.setTongtien(rs.getInt(15));
                  list.add(hoa);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

    public List<byte[]> getPhotBIdPhong(int maPhong) {
        List<byte[]> list = new ArrayList<byte[]>();
        try {
            if(connection!=null){
                String sql ="select * from HINHANHPHONG where HINHANHPHONG.MaPhong =" + maPhong ;
                Statement st = connection.createStatement();
                ResultSet rs = st.executeQuery(sql);
                while(rs.next()){
                    list.add(rs.getBytes(3))  ;
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }
    public void insertCheck(int idKhachSanj , int idKhachHang ){
        try {
            String sql = "insert into  CHECKTT (MaKS , MaKH, TrangThai ) values ( "+idKhachSanj+" , "+idKhachHang+", 1)";
            Statement st = connection.createStatement();
            st.executeUpdate(sql);

        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public void deleteFromTranThaiLuu(int idKhachSanj , int idKhachHang  ){
        try {
            String sql = "delete from CHECKTT where CHECKTT.MaKH ="+idKhachHang+"   and CHECKTT.MaKS = "+idKhachSanj+" and CHECKTT.TrangThai = 1";
            Statement st = connection.createStatement();
            st.executeUpdate(sql);

        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public  int getTrangThaiLuu(int idKhachHang, int idKhachSanj){
        int id = 0;
        try {
            if(connection!=null){
                String sql ="select * from CHECKTT where CHECKTT.MaKH ="+idKhachHang+" and CHECKTT.MaKS = "+idKhachSanj ;
                Statement st = connection.createStatement();
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    id = rs.getInt(4);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return id;
    }

    public byte[] getAnhChuKhachSan(int id) {
        byte[] anh =null;
        try {
            if(connection!=null){
                String sql ="select * from CHUKHACHSAN where CHUKHACHSAN.MaKS ="+id ;
                Statement st = connection.createStatement();
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    anh = rs.getBytes("Anh");
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return  anh;
    }
    public void insertCheck2(int idKhachSanj , int idKhachHang ){
        try {
            String sql = "update CHECKTT set CHECKTT.MaKH = "+idKhachHang+"  , CHECKTT.MaKS ="+idKhachSanj+" ,CHECKTT.TrangThai = 1";
            Statement st = connection.createStatement();
            st.executeUpdate(sql);

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public List<KhachSan> getListKhachSanDaLuu(int id) {
        List<KhachSan> list = new ArrayList<KhachSan>();
        try {
            if(connection !=null){
                String sql = "\n" +
                        "\n" +
                        "select  KHACHSAN.MaKS , TenKS ,Hang , ChinhSachVS , DiaDiem ,Longitude ,Latitude,TimeDat,TimeTra ,SoLuongP ,MoTa ,LOAIHINH.TenLH,GiaDD,\n" +
                        "                        TIENNGHIHD.WifiSanh,WifiPhong,BeBoi,DauXe,Spa,VatNuoi,DieuHoa,NhaHang,Bar,Gym ,\n" +
                        "                        CHUKHACHSAN.SDT , CHUKHACHSAN.Ten\n" +
                        "                        \n" +
                        "                        from KHACHSAN join LOAIHINH on LOAIHINH.MaLH = KHACHSAN.MaLH\n" +
                        "                        join TIENNGHIHD on TIENNGHIHD.MaKS = KHACHSAN.MaKS\n" +
                        "                        join CHUKHACHSAN on CHUKHACHSAN.MaKS = KHACHSAN.MaKS\n" +
                        "                        join CHECKTT on CHECKTT.MaKS = KHACHSAN.MaKS\n" +
                        "\n" +
                        "\t\t\t\t\t\twhere CHECKTT.TrangThai =1 and CHECKTT.MaKH = "+id;
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
                    ks.setGiaThue(rs.getInt("GiaDD"));
                    //  ks.setAnhchukhachsan(rs.getBytes("Anh"));
                    ks.setSoDienThoaiChuKhachSan(rs.getString("SDT"));
                    ks.setTenChuKhachSan(rs.getString("Ten"));
                    // ks.setTrangThaiLuu(rs.getInt("TrangThai"));
                    list.add(ks);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

    public void updateSOluongPHong(int id, int i) {
        try {
            String sql = "update PHONG set PHONG.SoPhong ="+i+" where PHONG.MaPhong = "+id;
            Statement st = connection.createStatement();
            st.executeUpdate(sql);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public int getSoLuongPhongkhachSan(int id) {
        int idt = 0;
        try {
            if(connection!=null){
                String sql ="select * from KHACHSAN where KHACHSAN.MaKS = "+id ;
                Statement st = connection.createStatement();
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    idt = rs.getInt(11);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return idt;
    }

    public void updateSOluongPHongKhachSan(int id, int i) {
        try {
            String sql = "update KHACHSAN set KHACHSAN.SoLuongP = "+i+" where KHACHSAN.MaKS = "+id;
            Statement st = connection.createStatement();
            st.executeUpdate(sql);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void deleteHoaDon(int id) {
        try {
            String sql = "delete from PHIEUTHUEPHONG where PHIEUTHUEPHONG.MaPT = "+id;
            Statement st = connection.createStatement();
            st.execute(sql);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public int getIdByTenPhong(String tenPhong) {
        int id = 0;
        try {
            if(connection!=null){
                String sql ="select * from PHONG where PHONG.TenPhong like N'"+tenPhong+"'" ;
                Statement st = connection.createStatement();
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    id = rs.getInt(1);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return id;
    }
    public int getSoluongPHongByByName(String tenPhong) {
        int id = 0;
        try {
            if(connection!=null){
                String sql ="select * from PHONG where PHONG.TenPhong like N'"+tenPhong+"'" ;
                Statement st = connection.createStatement();
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    id = rs.getInt(4);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return id;
    }
}
