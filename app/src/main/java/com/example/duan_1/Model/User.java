package com.example.duan_1.Model;

public class User {
    private int resouceimg;
    private String name;

    public User(int resouceimg, String name) {
        this.resouceimg = resouceimg;
        this.name = name;
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
