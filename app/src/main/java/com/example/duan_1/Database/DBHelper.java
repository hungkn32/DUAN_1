package com.example.duan_1.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public static final String dbname = "QLSHOES";
    public static final int dbvesion =15;

    public DBHelper(Context context) {

        super(context, dbname, null, dbvesion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String tb_Admin = "Create table ADMIN(madn text primary key , " +
                "tenDangNhap TEXT  NOT NULL," +
                "matkhau TEXT  NOT NULL)";
        db.execSQL(tb_Admin);
        String tb_giay = "CREATE TABLE GIAY (maGiay INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "tenGiay TEXT NOT NULL, " +
                "loaiGiay TEXT NOT NULL, " +
                "giaTien TEXT NOT NULL, " +
                "avatagiay TEXT NOT NULL)";
        db.execSQL(tb_giay);

        String tb_khachhang = "Create table KHACHHANG(maKH integer primary key autoincrement," +
                "tenKH TEXT  NOT NULL," +
                "namSinh TEXT  NOT NULL," +
                "sdt TEXT  NOT NULL," +
                "diaChi TEXT  NOT NULL," +
                "urlkhachhang TEXT  NOT NULL)";
        db.execSQL(tb_khachhang);
        String tb_dathang = "Create table DATHANG(maDH integer primary key autoincrement," +
                "tenKH TEXT  REFERENCES KHACHHANG(tenKH) NOT NULL," +
                "diaChi TEXT    REFERENCES KHACHHANG(diaChi) NOT NULL," +
                "tenGiay TEXT    REFERENCES GIAY(tenGiay) NOT NULL," +
                "loaiGiay TEXT    REFERENCES GIAY(loaiGiay) NOT NULL," +
                "giaTien TEXT   REFERENCES GIAY(giaTien) NOT NULL," +
                "ngayMua DATE NOT NULL," +
                "trangThai INTEGER NOT NULL)";
        db.execSQL(tb_dathang);

        db.execSQL("Insert into ADMIN values('admin','admin','admin123')," +
                "('nhanvien','nhanvien','nhanvien123')");


        // Ví dụ về cách chèn dữ liệu mẫu vào bảng "GIAY"





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
