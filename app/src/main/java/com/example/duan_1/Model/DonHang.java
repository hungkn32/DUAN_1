package com.example.duan_1.Model;

public class DonHang {
    private int maDonHang;
    private int mataikhoan;
    private String tenkh;
    private int magiay;
    private String diachi;
    private String tengiay;
    private String loaigiay;

    private String ngayDatHang;
    private int tongTien;
    private String trangthai;

    public DonHang() {
    }

    public DonHang(int mataikhoan,String ngayDatHang,int tongTien,String trangthai){
       this.mataikhoan = mataikhoan;
       this.ngayDatHang = ngayDatHang;
       this.tongTien = tongTien;
     this.trangthai = trangthai;
    }

    public DonHang(int maDonHang,int mataikhoan, String tenkh,int magiay,String diachi,String tengiay,String loaigiay, String ngayDatHang, int tongTien, String trangthai) {
        this.maDonHang = maDonHang;
        this.mataikhoan= mataikhoan;
        this.tenkh = tenkh;
        this.diachi = diachi;
        this.tengiay = tengiay;
        this.magiay =magiay;
        this.loaigiay =loaigiay;
        this.ngayDatHang = ngayDatHang;
        this.tongTien = tongTien;
        this.trangthai = trangthai;
    }


    public int getMaDonHang() {
        return maDonHang;
    }

    public void setMaDonHang(int maDonHang) {
        this.maDonHang = maDonHang;
    }

    public String getTenkh() {
        return tenkh;
    }

    public void setTenkh(String tenkh) {
        this.tenkh = tenkh;
    }

    public String getNgayDatHang() {
        return ngayDatHang;
    }

    public int getMagiay() {
        return magiay;
    }

    public void setMagiay(int magiay) {
        this.magiay = magiay;
    }

    public void setNgayDatHang(String ngayDatHang) {
        this.ngayDatHang = ngayDatHang;
    }

    public int getTongTien() {
        return tongTien;
    }

    public void setTongTien(int tongTien) {
        this.tongTien = tongTien;
    }

    public String getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(String trangthai) {
        this.trangthai = trangthai;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getTengiay() {
        return tengiay;
    }

    public int getMataikhoan() {
        return mataikhoan;
    }

    public void setMataikhoan(int mataikhoan) {
        this.mataikhoan = mataikhoan;
    }

    public void setTengiay(String tengiay) {
        this.tengiay = tengiay;
    }

    public String getLoaigiay() {
        return loaigiay;
    }

    public void setLoaigiay(String loaigiay) {
        this.loaigiay = loaigiay;
    }
}
