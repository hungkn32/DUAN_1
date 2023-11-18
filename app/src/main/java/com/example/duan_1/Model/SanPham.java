package com.example.duan_1.Model;

public class SanPham {
    private String magiamgia;
    private String resouceimg;
    private String gia;

    public SanPham() {
    }

    public SanPham(String magiamgia, String resouceimg, String gia) {
        this.magiamgia = magiamgia;
        this.resouceimg = resouceimg;
        this.gia = gia;
    }

    public String getMagiamgia() {
        return magiamgia;
    }

    public void setMagiamgia(String magiamgia) {
        this.magiamgia = magiamgia;
    }

    public String getResouceimg() {
        return resouceimg;
    }

    public void setResouceimg(String resouceimg) {
        this.resouceimg = resouceimg;
    }

    public String getGia() {
        return gia;
    }

    public void setGia(String gia) {
        this.gia = gia;
    }
}
