package com.example.duan_1.Model;

public class hoadon {
    private int madh;
    private String tenkh;
    private String diachi;
    private String tengiay;
    private String loaigiay;
    private String giatien;
    private int ngaymua;
    private int trangthai;


    public hoadon() {
    }

    public hoadon(int madh, String tenkh, String diachi, String tengiay, String loaigiay, String giatien, int ngaymua, int trangthai) {
        this.madh = madh;
        this.tenkh = tenkh;
        this.diachi = diachi;
        this.tengiay = tengiay;
        this.loaigiay = loaigiay;
        this.giatien = giatien;
        this.ngaymua = ngaymua;
        this.trangthai = trangthai;
    }

    public int getMadh() {
        return madh;
    }

    public String getTenkh() {
        return tenkh;
    }

    public String getDiachi() {
        return diachi;
    }

    public String getTengiay() {
        return tengiay;
    }

    public String getLoaigiay() {
        return loaigiay;
    }

    public String getGiatien() {
        return giatien;
    }

    public int getNgaymua() {
        return ngaymua;
    }

    public int getTrangthai() {
        return trangthai;
    }

    public void setMadh(int madh) {
        this.madh = madh;
    }

    public void setTenkh(String tenkh) {
        this.tenkh = tenkh;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public void setTengiay(String tengiay) {
        this.tengiay = tengiay;
    }

    public void setLoaigiay(String loaigiay) {
        this.loaigiay = loaigiay;
    }

    public void setGiatien(String giatien) {
        this.giatien = giatien;
    }

    public void setNgaymua(int ngaymua) {
        this.ngaymua = ngaymua;
    }

    public void setTrangthai(int trangthai) {
        this.trangthai = trangthai;
    }
}
