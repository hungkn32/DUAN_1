package com.example.duan_1.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public static final String dbname = "QLSHOES";
    public static final int dbvesion = 3;

    public DBHelper(Context context) {
        super(context, dbname, null, dbvesion);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        String tb_Admin = "Create table ADMIN(maadmin integer primary key , tenDangNhap text,matkhau text,loaitk integer)";
        db.execSQL(tb_Admin);
        String tb_giay = "Create table GIAY(maGiay integer primary key  autoincrement, tenGiay text,loaiGiay text ,giaTien integer)";
        db.execSQL(tb_giay);
        String tb_khachhang = "Create table KHACHHANG(maKH integer,tenKH text,namSinh text,diaChi text)";
        db.execSQL(tb_khachhang);
        String tb_dathang = "Create table DATHANG(maDH integer primary key autoincrement,tenKH text,diaChi tex,tenGiay text,loaiGiay text,giaTien integer,ngayMua date,trangThai integer)";
        db.execSQL(tb_dathang);

        db.execSQL("Insert into ADMIN values(0,'admin','admin123',1)," +
                "(1,'nhanvien','nhanvien123',2)," +
                "(2,'khachhang','khachhang123',3)");
        db.execSQL("Insert into GIAY values(0,'Nike White','Nike',200000)," +
                "(1,'Adidas 101For','Adidas',98333)," +
                "(2,'Bits 12','Btit',39999)");
        db.execSQL("Insert into KHACHHANG values(0,'ThànhLQ','2003','Bắc Giang')," +
                "(1,'HưngKN','2004','Hà Nội')," +
                "(2,'GiangTV','2004','HÀ Nội')");
        db.execSQL("Insert into DATHANG values(1234,'HiếuNv','Thôn Muồng Xã Tản Viên','Adidas 101for','Adidas','98333','19/11/2023',1)");




    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        if (i != i1) {
            db.execSQL("DROP TABLE IF EXISTS ADMIN");
            db.execSQL("DROP TABLE IF EXISTS GIAY");
            db.execSQL("DROP TABLE IF EXISTS KHACHHANG");
            db.execSQL("DROP TABLE IF EXISTS DATHANG");
            onCreate(db);
        }

    }
}
