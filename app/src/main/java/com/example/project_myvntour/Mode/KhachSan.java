package com.example.project_myvntour.Mode;

import java.io.Serializable;

public class KhachSan implements Serializable {
    private int id , idLoai , hang , soLuongPHong , soluongPHongNGu , soLUongPHongTam , soKhacToDa , images  ,trangThaiLuu , soSao , WifiSanh , WifiPhong ,BeBoi ,DauXe , Spa ,VatNuoi , DieuHoa ,NhaHang ,Bar, Gym ,ChinhSachVS ,giaThue;
    private String tenKhachSan , diaDiem , timetra , timeNhan ,Mota , tenPhong , tienNghi , chinhsachveSinh , loaisachsan ,  tenChuKhachSan , soDienThoaiChuKhachSan;
    private double kinhdo , vido ;
    private byte[] anhchukhachsan ;

    public String getTenChuKhachSan() {
        return tenChuKhachSan;
    }

    public void setTenChuKhachSan(String tenChuKhachSan) {
        this.tenChuKhachSan = tenChuKhachSan;
    }

    public String getSoDienThoaiChuKhachSan() {
        return soDienThoaiChuKhachSan;
    }

    public void setSoDienThoaiChuKhachSan(String soDienThoaiChuKhachSan) {
        this.soDienThoaiChuKhachSan = soDienThoaiChuKhachSan;
    }

    public byte[] getAnhchukhachsan() {
        return anhchukhachsan;
    }

    public void setAnhchukhachsan(byte[] anhchukhachsan) {
        this.anhchukhachsan = anhchukhachsan;
    }

    public int getChinhSachVS() {
        return ChinhSachVS;
    }

    public void setChinhSachVS(int chinhSachVS) {
        ChinhSachVS = chinhSachVS;
    }

    public int getWifiSanh() {
        return WifiSanh;
    }

    public void setWifiSanh(int wifiSanh) {
        WifiSanh = wifiSanh;
    }

    public int getWifiPhong() {
        return WifiPhong;
    }

    public void setWifiPhong(int wifiPhong) {
        WifiPhong = wifiPhong;
    }

    public int getBeBoi() {
        return BeBoi;
    }

    public void setBeBoi(int beBoi) {
        BeBoi = beBoi;
    }

    public int getDauXe() {
        return DauXe;
    }

    public void setDauXe(int dauXe) {
        DauXe = dauXe;
    }

    public int getSpa() {
        return Spa;
    }

    public void setSpa(int spa) {
        Spa = spa;
    }

    public int getVatNuoi() {
        return VatNuoi;
    }

    public void setVatNuoi(int vatNuoi) {
        VatNuoi = vatNuoi;
    }

    public int getDieuHoa() {
        return DieuHoa;
    }

    public void setDieuHoa(int dieuHoa) {
        DieuHoa = dieuHoa;
    }

    public int getNhaHang() {
        return NhaHang;
    }

    public void setNhaHang(int nhaHang) {
        NhaHang = nhaHang;
    }

    public int getBar() {
        return Bar;
    }

    public void setBar(int bar) {
        Bar = bar;
    }

    public int getGym() {
        return Gym;
    }

    public void setGym(int gym) {
        Gym = gym;
    }



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
