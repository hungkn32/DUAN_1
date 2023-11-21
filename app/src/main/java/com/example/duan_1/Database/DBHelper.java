package com.example.duan_1.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public static final String dbname = "QLSHOES";
    public static final int dbvesion = 7;

    public DBHelper(Context context) {

        super(context, dbname, null, dbvesion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String tb_Admin = "Create table ADMIN(maadmin integer primary key , tenDangNhap text,matkhau text,loaitk integer)";
        db.execSQL(tb_Admin);
        String tb_giay = "CREATE TABLE GIAY (maGiay INTEGER PRIMARY KEY AUTOINCREMENT, tenGiay TEXT, loaiGiay TEXT, giaTien INTEGER, avatagiay TEXT)";
        db.execSQL(tb_giay);

        String tb_khachhang = "Create table KHACHHANG(maKH integer primary key autoincrement,tenKH text,namSinh text,sdt text ,diaChi text)";
        db.execSQL(tb_khachhang);
        String tb_dathang = "Create table DATHANG(maDH integer primary key autoincrement,tenKH text,diaChi tex,tenGiay text,loaiGiay text,giaTien integer,ngayMua date,trangThai integer)";
        db.execSQL(tb_dathang);

        db.execSQL("Insert into ADMIN values(0,'admin','admin123',1)," +
                "(1,'nhanvien','nhanvien123',2)," +
                "(2,'khachhang','khachhang123',3)");

        // Ví dụ về cách chèn dữ liệu mẫu vào bảng "GIAY"

        String img1 = "/path/to/your/img_21.png";
        String img2 = "/path/to/your/img_22.png";
        String img3 = "/path/to/your/img_23.png";

        String insertData = "INSERT INTO GIAY VALUES " +
                "(1,'Giày Sneaker A', 'Sneaker', 100, '" + img1 + "')," +
                "(2,'Giày Formal B', 'Formal', 150, '" + img2 + "')," +
                "(3,'Giày Sport C', 'Sport', 80, '" + img3 + "');";



        db.execSQL(insertData);

        db.execSQL("Insert into KHACHHANG values(0,'ThànhLQ','2003',02928222,'Bắc Giang')," +
                "(1,'HưngKN','2004',098228722,'Hà Nội')," +
                "(2,'GiangTV','2004',09872262,'HÀ Nội')");
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
