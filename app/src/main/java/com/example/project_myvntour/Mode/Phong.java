package com.example.project_myvntour.Mode;

import java.io.Serializable;

public class Phong implements Serializable {
    private int id_Phong, id_Ks, gia, dienTich, soGiuong, nguoiLon, treNho, soPhong , dieuhoa , tivi , ketantoan , tulanh , bep , ban , wifi , dichvuphong , maygiat , maysaytoc , banui , khonghutthuoc , bontam ;
    private String tenPhong, moTa;

    public Phong() {
    }

    public Phong(int id_Phong, int id_Ks, int gia, int dienTich, int soGiuong, int nguoiLon, int treNho, int soPhong, String tenPhong, String moTa) {
        this.id_Phong = id_Phong;
        this.id_Ks = id_Ks;
        this.gia = gia;
        this.dienTich = dienTich;
        this.soGiuong = soGiuong;
        this.nguoiLon = nguoiLon;
        this.treNho = treNho;
        this.soPhong = soPhong;
        this.tenPhong = tenPhong;
        this.moTa = moTa;
    }

    public int getDieuhoa() {
        return dieuhoa;
    }

    public void setDieuhoa(int dieuhoa) {
        this.dieuhoa = dieuhoa;
    }

    public int getTivi() {
        return tivi;
    }

    public void setTivi(int tivi) {
        this.tivi = tivi;
    }

    public int getKetantoan() {
        return ketantoan;
    }

    public void setKetantoan(int ketantoan) {
        this.ketantoan = ketantoan;
    }

    public int getTulanh() {
        return tulanh;
    }

    public void setTulanh(int tulanh) {
        this.tulanh = tulanh;
    }

    public int getBep() {
        return bep;
    }

    public void setBep(int bep) {
        this.bep = bep;
    }

    public int getBan() {
        return ban;
    }

    public void setBan(int ban) {
        this.ban = ban;
    }

    public int getWifi() {
        return wifi;
    }

    public void setWifi(int wifi) {
        this.wifi = wifi;
    }

    public int getDichvuphong() {
        return dichvuphong;
    }

    public void setDichvuphong(int dichvuphong) {
        this.dichvuphong = dichvuphong;
    }

    public int getMaygiat() {
        return maygiat;
    }

    public void setMaygiat(int maygiat) {
        this.maygiat = maygiat;
    }

    public int getMaysaytoc() {
        return maysaytoc;
    }

    public void setMaysaytoc(int maysaytoc) {
        this.maysaytoc = maysaytoc;
    }

    public int getBanui() {
        return banui;
    }

    public void setBanui(int banui) {
        this.banui = banui;
    }

    public int getKhonghutthuoc() {
        return khonghutthuoc;
    }

    public void setKhonghutthuoc(int khonghutthuoc) {
        this.khonghutthuoc = khonghutthuoc;
    }

    public int getBontam() {
        return bontam;
    }

    public void setBontam(int bontam) {
        this.bontam = bontam;
    }

    public int getId_Phong() {
        return id_Phong;
    }

    public void setId_Phong(int id_Phong) {
        this.id_Phong = id_Phong;
    }

    public int getId_Ks() {
        return id_Ks;
    }

    public void setId_Ks(int id_Ks) {
        this.id_Ks = id_Ks;
    }

    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }

    public int getDienTich() {
        return dienTich;
    }

    public void setDienTich(int dienTich) {
        this.dienTich = dienTich;
    }

    public int getSoGiuong() {
        return soGiuong;
    }

    public void setSoGiuong(int soGiuong) {
        this.soGiuong = soGiuong;
    }

    public int getNguoiLon() {
        return nguoiLon;
    }

    public void setNguoiLon(int nguoiLon) {
        this.nguoiLon = nguoiLon;
    }

    public int getTreNho() {
        return treNho;
    }

    public void setTreNho(int treNho) {
        this.treNho = treNho;
    }

    public String getTenPhong() {
        return tenPhong;
    }

    public void setTenPhong(String tenPhong) {
        this.tenPhong = tenPhong;
    }

    public int getSoPhong() {
        return soPhong;
    }

    public void setSoPhong(int soPhong) {
        this.soPhong = soPhong;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }
}
