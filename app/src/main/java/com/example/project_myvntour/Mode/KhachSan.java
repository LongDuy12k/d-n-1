package com.example.project_myvntour.Mode;

public class KhachSan {
    private int id , idLoai , hang , soLuongPHong , soluongPHongNGu , soLUongPHongTam , soKhacToDa , images ,giaThue ,trangThaiLuu , soSao;
    private String tenKhachSan , diaDiem , timetra , timeNhan ,Mota , tenPhong , tienNghi , chinhsachveSinh , loaisachsan;
    private double kinhdo , vido ;

    public int getTrangThaiLuu() {
        return trangThaiLuu;
    }

    public void setTrangThaiLuu(int trangThaiLuu) {
        this.trangThaiLuu = trangThaiLuu;
    }

    public int getSoSao() {
        return soSao;
    }

    public void setSoSao(int soSao) {
        this.soSao = soSao;
    }

    public String getLoaisachsan() {
        return loaisachsan;
    }

    public void setLoaisachsan(String loaisachsan) {
        this.loaisachsan = loaisachsan;
    }

    public KhachSan() {
    }

    public int getGiaThue() {
        return giaThue;
    }

    public void setGiaThue(int giaThue) {
        this.giaThue = giaThue;
    }

    public KhachSan(int id, int soluongPHongNGu, int soLUongPHongTam, int images, String tenKhachSan, String diaDiem, double kinhdo, double vido , int giathue , String loaikhachsan, int trangThaiLuu , int soSao) {
        this.id = id;
        this.soluongPHongNGu = soluongPHongNGu;
        this.soLUongPHongTam = soLUongPHongTam;
        this.images = images;
        this.tenKhachSan = tenKhachSan;
        this.diaDiem = diaDiem;
        this.kinhdo = kinhdo;
        this.vido = vido;
        this.giaThue = giathue;
        this.loaisachsan = loaikhachsan;
        this.trangThaiLuu = trangThaiLuu;
        this.soSao = soSao;
    }

    public int getImages() {
        return images;
    }

    public void setImages(int images) {
        this.images = images;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdLoai() {
        return idLoai;
    }

    public void setIdLoai(int idLoai) {
        this.idLoai = idLoai;
    }

    public int getHang() {
        return hang;
    }

    public void setHang(int hang) {
        this.hang = hang;
    }

    public int getSoLuongPHong() {
        return soLuongPHong;
    }

    public void setSoLuongPHong(int soLuongPHong) {
        this.soLuongPHong = soLuongPHong;
    }

    public int getSoluongPHongNGu() {
        return soluongPHongNGu;
    }

    public void setSoluongPHongNGu(int soluongPHongNGu) {
        this.soluongPHongNGu = soluongPHongNGu;
    }

    public int getSoLUongPHongTam() {
        return soLUongPHongTam;
    }

    public void setSoLUongPHongTam(int soLUongPHongTam) {
        this.soLUongPHongTam = soLUongPHongTam;
    }

    public int getSoKhacToDa() {
        return soKhacToDa;
    }

    public void setSoKhacToDa(int soKhacToDa) {
        this.soKhacToDa = soKhacToDa;
    }

    public String getTenKhachSan() {
        return tenKhachSan;
    }

    public void setTenKhachSan(String tenKhachSan) {
        this.tenKhachSan = tenKhachSan;
    }

    public String getDiaDiem() {
        return diaDiem;
    }

    public void setDiaDiem(String diaDiem) {
        this.diaDiem = diaDiem;
    }

    public String getTimetra() {
        return timetra;
    }

    public void setTimetra(String timetra) {
        this.timetra = timetra;
    }

    public String getTimeNhan() {
        return timeNhan;
    }

    public void setTimeNhan(String timeNhan) {
        this.timeNhan = timeNhan;
    }

    public String getMota() {
        return Mota;
    }

    public void setMota(String mota) {
        Mota = mota;
    }

    public String getTenPhong() {
        return tenPhong;
    }

    public void setTenPhong(String tenPhong) {
        this.tenPhong = tenPhong;
    }

    public String getTienNghi() {
        return tienNghi;
    }

    public void setTienNghi(String tienNghi) {
        this.tienNghi = tienNghi;
    }

    public String getChinhsachveSinh() {
        return chinhsachveSinh;
    }

    public void setChinhsachveSinh(String chinhsachveSinh) {
        this.chinhsachveSinh = chinhsachveSinh;
    }

    public double getKinhdo() {
        return kinhdo;
    }

    public void setKinhdo(double kinhdo) {
        this.kinhdo = kinhdo;
    }

    public double getVido() {
        return vido;
    }

    public void setVido(double vido) {
        this.vido = vido;
    }
}
