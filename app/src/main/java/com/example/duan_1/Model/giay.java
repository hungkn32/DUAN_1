package com.example.duan_1.Model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class giay implements Parcelable {
    private int magiay;
    private String tenGiay;
    private String loaiGiay;
    private int giaTien;

    private String avataanh;

    public giay() {
    }

    public giay(int magiay, String tenGiay, String loaiGiay, int giaTien, String avataanh) {
        this.magiay = magiay;
        this.tenGiay = tenGiay;
        this.loaiGiay = loaiGiay;
        this.giaTien = giaTien;
        this.avataanh = avataanh;
    }

    protected giay(Parcel in) {
        magiay = in.readInt();
        tenGiay = in.readString();
        loaiGiay = in.readString();
        giaTien = in.readInt();
        avataanh = in.readString();
    }

    public static final Creator<giay> CREATOR = new Creator<giay>() {
        @Override
        public giay createFromParcel(Parcel in) {
            return new giay(in);
        }

        @Override
        public giay[] newArray(int size) {
            return new giay[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeInt(magiay);
        parcel.writeString(tenGiay);
        parcel.writeString(loaiGiay);
        parcel.writeInt(giaTien);
        parcel.writeString(avataanh);
    }
}


