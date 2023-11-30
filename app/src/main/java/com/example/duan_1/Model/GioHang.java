package com.example.duan_1.Model;

public class GioHang {

    private int maGioHang;
    private int magiay;
    private int madn;

    private int soLuongMua;
    private String tenSanPham;
    private int giaSanPham;
    private boolean isSelected;

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public GioHang() {
    }


    public GioHang(int magiay, int madn, int soLuongMua) {
        this.magiay = magiay;
        this.madn = madn;
        this.soLuongMua = soLuongMua;
    }

    public GioHang(int maGioHang, int magiay, int madn, int soLuongMua, String tenSanPham, int giaSanPham) {
        this.maGioHang = maGioHang;
        this.magiay = magiay;
        this.madn = madn;
        this.soLuongMua = soLuongMua;
        this.tenSanPham = tenSanPham;
        this.giaSanPham = giaSanPham;
    }

    public int getMaGioHang() {
        return maGioHang;
    }

    public void setMaGioHang(int maGioHang) {
        this.maGioHang = maGioHang;
    }

    public int getMagiay() {
        return magiay;
    }

    public void setMagiay(int magiay) {
        this.magiay = magiay;
    }

    public int getMadn() {
        return madn;
    }

    public void setMadn(int maNguoiDung) {
        this.madn = madn;
    }

    public int getSoLuongMua() {
        return soLuongMua;
    }

    public void setSoLuongMua(int soLuongMua) {
        this.soLuongMua = soLuongMua;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public int getGiaSanPham() {
        return giaSanPham;
    }

    public void setGiaSanPham(int giaSanPham) {
        this.giaSanPham = giaSanPham;
    }
}
