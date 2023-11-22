package com.example.duan_1.Model;

public class khachhang {
    private int makh;
    private String tenkh;
    private String namsinh;
    private int sdt;
    private String diachi;
    private String urlkh;

    public khachhang() {
    }

    public khachhang(int makh, String tenkh, String namsinh, int sdt, String diachi,String urlkh) {
        this.makh = makh;
        this.tenkh = tenkh;
        this.namsinh = namsinh;
        this.sdt = sdt;
        this.diachi = diachi;
        this.urlkh = urlkh;
    }

    public int getMakh() {
        return makh;
    }

    public void setMakh(int makh) {
        this.makh = makh;
    }

    public String getTenkh() {
        return tenkh;
    }

    public void setTenkh(String tenkh) {
        this.tenkh = tenkh;
    }

    public String getNamsinh() {
        return namsinh;
    }

    public void setNamsinh(String namsinh) {
        this.namsinh = namsinh;
    }

    public int getSdt() {
        return sdt;
    }

    public void setSdt(int sdt) {
        this.sdt = sdt;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public void setUrlkh(String urlkh) {
        this.urlkh = urlkh;
    }

    public String getUrlkh() {
        return urlkh;
    }
}
