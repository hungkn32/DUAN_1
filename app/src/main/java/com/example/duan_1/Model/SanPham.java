package com.example.duan_1.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class SanPham  implements Parcelable {
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

    protected SanPham(Parcel in) {
        magiamgia = in.readString();
        resouceimg = in.readString();
        gia = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(magiamgia);
        dest.writeString(resouceimg);
        dest.writeString(gia);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<SanPham> CREATOR = new Creator<SanPham>() {
        @Override
        public SanPham createFromParcel(Parcel in) {
            return new SanPham(in);
        }

        @Override
        public SanPham[] newArray(int size) {
            return new SanPham[size];
        }
    };

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
