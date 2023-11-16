package com.example.duan_1.Model;

public class User {
    private String magiamgia;
    private int resouceimg;
    private String name;


    public User(String magiamgia, int resouceimg, String name) {
        this.magiamgia = magiamgia;
        this.resouceimg = resouceimg;
        this.name = name;
    }

    public String getMagiamgia() {
        return magiamgia;
    }

    public void setMagiamgia(String magiamgia) {
        this.magiamgia = magiamgia;
    }

    public int getResouceimg() {
        return resouceimg;
    }

    public void setResouceimg(int resouceimg) {
        this.resouceimg = resouceimg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
