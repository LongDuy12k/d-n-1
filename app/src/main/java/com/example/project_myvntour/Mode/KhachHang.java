package com.example.project_myvntour.Mode;

public class KhachHang {
    private int id , gioitinh , isAdmin;
    private String email , username , password , diachir , sdt ,cmnd ;
    private byte[] anhKhachHang;

    public KhachHang() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGioitinh() {
        return gioitinh;
    }

    public void setGioitinh(int gioitinh) {
        this.gioitinh = gioitinh;
    }

    public int getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(int isAdmin) {
        this.isAdmin = isAdmin;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDiachir() {
        return diachir;
    }

    public void setDiachir(String diachir) {
        this.diachir = diachir;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getCmnd() {
        return cmnd;
    }

    public void setCmnd(String cmnd) {
        this.cmnd = cmnd;
    }

    public byte[] getAnhKhachHang() {
        return anhKhachHang;
    }

    public void setAnhKhachHang(byte[] anhKhachHang) {
        this.anhKhachHang = anhKhachHang;
    }
}
