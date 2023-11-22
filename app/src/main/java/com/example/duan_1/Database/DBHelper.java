package com.example.duan_1.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public static final String dbname = "QLSHOES";
    public static final int dbvesion =11;

    public DBHelper(Context context) {

        super(context, dbname, null, dbvesion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String tb_Admin = "Create table ADMIN(maadmin integer primary key , tenDangNhap text,matkhau text,loaitk integer)";
        db.execSQL(tb_Admin);
        String tb_giay = "CREATE TABLE GIAY (maGiay INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "tenGiay TEXT NOT NULL, " +
                "loaiGiay TEXT NOT NULL, " +
                "giaTien INTEGER NOT NULL, " +
                "avatagiay TEXT NOT NULL)";
        db.execSQL(tb_giay);

        String tb_khachhang = "Create table KHACHHANG(maKH integer primary key autoincrement," +
                "tenKH text NOT NULL," +
                "namSinh text NOT NULL," +
                "sdt text NOT NULL," +
                "diaChi text NOT NULL," +
                "urlkhachhang text NOT NULL)";
        db.execSQL(tb_khachhang);
        String tb_dathang = "Create table DATHANG(maDH integer primary key autoincrement,tenKH text,diaChi tex,tenGiay text,loaiGiay text,giaTien integer,ngayMua date,trangThai integer)";
        db.execSQL(tb_dathang);

        db.execSQL("Insert into ADMIN values(0,'admin','admin123',1)," +
                "(1,'nhanvien','nhanvien123',2)," +
                "(2,'khachhang','khachhang123',3)");

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
