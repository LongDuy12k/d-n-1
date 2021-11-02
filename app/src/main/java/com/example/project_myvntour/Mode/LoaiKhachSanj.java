package com.example.project_myvntour.Mode;

public class LoaiKhachSanj {
    private int id;
    private String tenLoaiKhachSanj;

    public LoaiKhachSanj(int id, String tenLoaiKhachSanj) {
        this.id = id;
        this.tenLoaiKhachSanj = tenLoaiKhachSanj;
    }

    public LoaiKhachSanj() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenLoaiKhachSanj() {
        return tenLoaiKhachSanj;
    }

    public void setTenLoaiKhachSanj(String tenLoaiKhachSanj) {
        this.tenLoaiKhachSanj = tenLoaiKhachSanj;
    }
}
