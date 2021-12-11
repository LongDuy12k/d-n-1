package com.example.project_myvntour.Mode;

import java.io.Serializable;

public class HoaDon implements Serializable {
    private int id , tongtien , maPhong;
    private String tenKhachSan , tenPhong , ngayden , ngaydi , gionhanphong , giotraphong , tennguoidat , email , sodienthoai ,YeuCauDB;
    private int soNguoiLon, sotreem  ,soGiuong;

    public HoaDon() {
    }

    public String getYeuCauDB() {
        return YeuCauDB;
    }

    public void setYeuCauDB(String yeuCauDB) {
        YeuCauDB = yeuCauDB;
    }

    public int getSoGiuong() {
        return soGiuong;
    }

    public void setSoGiuong(int soGiuong) {
        this.soGiuong = soGiuong;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTongtien() {
        return tongtien;
    }

    public void setTongtien(int tongtien) {
        this.tongtien = tongtien;
    }

    public int getMaPhong() {
        return maPhong;
    }

    public void setMaPhong(int maPhong) {
        this.maPhong = maPhong;
    }

    public String getTenKhachSan() {
        return tenKhachSan;
    }

    public void setTenKhachSan(String tenKhachSan) {
        this.tenKhachSan = tenKhachSan;
    }

    public String getTenPhong() {
        return tenPhong;
    }

    public void setTenPhong(String tenPhong) {
        this.tenPhong = tenPhong;
    }

    public String getNgayden() {
        return ngayden;
    }

    public void setNgayden(String ngayden) {
        this.ngayden = ngayden;
    }

    public String getNgaydi() {
        return ngaydi;
    }

    public void setNgaydi(String ngaydi) {
        this.ngaydi = ngaydi;
    }

    public String getGionhanphong() {
        return gionhanphong;
    }

    public void setGionhanphong(String gionhanphong) {
        this.gionhanphong = gionhanphong;
    }

    public String getGiotraphong() {
        return giotraphong;
    }

    public void setGiotraphong(String giotraphong) {
        this.giotraphong = giotraphong;
    }

    public String getTennguoidat() {
        return tennguoidat;
    }

    public void setTennguoidat(String tennguoidat) {
        this.tennguoidat = tennguoidat;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSodienthoai() {
        return sodienthoai;
    }

    public void setSodienthoai(String sodienthoai) {
        this.sodienthoai = sodienthoai;
    }

    public int getSoNguoiLon() {
        return soNguoiLon;
    }

    public void setSoNguoiLon(int soNguoiLon) {
        this.soNguoiLon = soNguoiLon;
    }

    public int getSotreem() {
        return sotreem;
    }

    public void setSotreem(int sotreem) {
        this.sotreem = sotreem;
    }
}
