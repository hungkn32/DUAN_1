package com.example.duan_1.Model;

public class giay {
    private int magiay;
    private String tenGiay;
    private String loaiGiay;
    private int giaTien;

    private String avataanh;



    public giay(int magiay, String tenGiay, String loaiGiay, int giaTien,String avataanh) {
        this.magiay = magiay;
        this.tenGiay = tenGiay;
        this.loaiGiay = loaiGiay;
        this.giaTien = giaTien;
        this.avataanh = avataanh;
    }

    public int getMagiay() {
        return magiay;
    }

    public void setMagiay(int magiay) {
        this.magiay = magiay;
    }

    public String getTenGiay() {
        return tenGiay;
    }

    public void setTenGiay(String tenGiay) {
        this.tenGiay = tenGiay;
    }

    public String getLoaiGiay() {
        return loaiGiay;
    }

    public void setLoaiGiay(String loaiGiay) {
        this.loaiGiay = loaiGiay;
    }

    public int getGiaTien() {
        return giaTien;
    }

    public void setGiaTien(int giaTien) {
        this.giaTien = giaTien;
    }

    public String getAvataanh() {
        return avataanh;
    }

    public void setAvataanh(String avataanh) {
        this.avataanh = avataanh;
    }
}


