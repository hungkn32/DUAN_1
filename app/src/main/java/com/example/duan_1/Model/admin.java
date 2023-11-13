package com.example.duan_1.Model;

public class admin {
    private int maadmin;
    private String tendangnhap;
    private String matkhau;
     private int role;

    public admin() {
    }

    public admin(int maadmin, String tendangnhap, String matkhau, int role) {
        this.maadmin = maadmin;
        this.tendangnhap = tendangnhap;
        this.matkhau = matkhau;
        this.role = role;
    }

    public int getMaadmin() {
        return maadmin;
    }

    public void setMaadmin(int maadmin) {
        this.maadmin = maadmin;
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

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }
}
