package com.example.duan_1.Dao;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.duan_1.Database.DBHelper;
import com.example.duan_1.Model.DonHang;
import com.example.duan_1.Model.hoadon;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class DonHangDao {
    DBHelper dbHelper;
    public DonHangDao(Context context){
        dbHelper = new DBHelper(context);
    }
    public ArrayList<DonHang> getDsDonHang() {
        ArrayList<DonHang> list = new ArrayList<>();
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        try {
            Cursor cursor = database.rawQuery("SELECT *FROM DONHANG", null);
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                do {
                    DonHang donHang = new DonHang();
                    donHang.setMaDonHang(cursor.getInt(0));
                    donHang.setTenkh(cursor.getString(1));
                    donHang.setDiachi(cursor.getString(2));
                    donHang.setTengiay(cursor.getString(3));
                    donHang.setLoaigiay(cursor.getString(4));
                    donHang.setNgayDatHang(cursor.getString(5));
                    donHang.setTongTien(cursor.getInt(6));
                    donHang.setTrangthai(cursor.getString(7));
                    list.add(donHang);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.d(TAG, "Lỗi", e);
        }
        return list;
    }
    public boolean xoaDonHang(DonHang donHang){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        long check = sqLiteDatabase.delete("DONHANG","madonhang = ?",new String[]{String.valueOf(donHang.getMaDonHang())});
        return check >0;

    }
//    public boolean updateDonHang(DonHang donHang) {
//        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put("mataikhoan", donHang.getMaTaiKhoan());
//        values.put("ngaydathang", donHang.getNgayDatHang());
//        values.put("tongtien", donHang.getTongTien());
//
//        long check = sqLiteDatabase.update("DONHANG", values, "madonhang = ?", new String[]{String.valueOf(donHang.getMaDonHang())});
//        return check > 0;
//    }
    public boolean insertDonHang(DonHang donHang){
        SQLiteDatabase da = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("tenKH",donHang.getTenkh());
        values.put("ngaydathang",donHang.getNgayDatHang());
        values.put("tongtien",donHang.getTongTien());
        values.put("trangthai",donHang.getTrangthai());
        long check = da.insert("DONHANG",null,values);
        return check>0;
    }
    public ArrayList<DonHang> getDonHangByMaTaiKhoan(int maTaiKhoan) {
        ArrayList<DonHang> list = new ArrayList<>();
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        try {
            String query = "SELECT DONHANG.madonhang, TAIKHOAN.mataikhoan, TAIKHOAN.hoten, DONHANG.ngaydathang, DONHANG.tongtien, DONHANG.trangthai FROM DONHANG JOIN TAIKHOAN ON DONHANG.mataikhoan = TAIKHOAN.mataikhoan WHERE TAIKHOAN.mataikhoan = ?";

            Cursor cursor = database.rawQuery(query, new String[]{String.valueOf(maTaiKhoan)});

            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                do {
                    DonHang donHang = new DonHang();
                    donHang.setMaDonHang(cursor.getInt(0));
                    donHang.setTenkh(cursor.getString(2));
                    donHang.setNgayDatHang(cursor.getString(3));
                    donHang.setTongTien(cursor.getInt(4));
                    donHang.setTrangthai(cursor.getString(5));
                    list.add(donHang);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.d(TAG, "Lỗi", e);
        }
        return list;
    }
}
