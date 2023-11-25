package com.example.duan_1.Model;

public class admin {
    private String madn;
    private String tendangnhap;
    private String matkhau;


    public admin() {
    }

    public admin(String madn, String tendangnhap, String matkhau) {
        this.madn = madn;
        this.tendangnhap = tendangnhap;
        this.matkhau = matkhau;
    }

    public String getMadn() {
        return madn;
    }

    public void setMadn(String madn) {
        this.madn = madn;
    }

    public String getTendangnhap() {
        return tendangnhap;
    }

    public void setTendangnhap(String tendangnhap) {
        this.tendangnhap = tendangnhap;
    }

    public String getMatkhau() {
        return matkhau;
    }

    public void setMatkhau(String matkhau) {
        this.matkhau = matkhau;
    }
}
