package com.example.duan_1.Dao;

import static android.content.ContentValues.TAG;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.duan_1.Database.DBHelper;
import com.example.duan_1.Model.GioHang;

import java.util.ArrayList;

public class GioHangDao {
     private DBHelper dbHelper;
    public GioHangDao(Context context) {
        this.dbHelper =new DBHelper(context);
    }
    public ArrayList<GioHang> getDSGioHang() {
        ArrayList<GioHang> list = new ArrayList<>();
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        try {
            Cursor c = database.rawQuery("SELECT GIOHANG.magiohang, GIAY.maGiay, GIOHANG.madn, GIOHANG.soluong,GIAY.tenGiay, SANPHAM.gia FROM GIOHANG, GIAY WHERE GIOHANG.maGiay = GIAY.maGiay", null);
            if (c.getCount() != 0) {
                c.moveToFirst();
                do {
                    GioHang gioHang = new GioHang();
                    gioHang.setMaGioHang(c.getInt(0));
                    gioHang.setMagiay(c.getInt(1));
                    gioHang.setMaNguoiDung(c.getInt(2));
                    gioHang.setSoLuongMua(c.getInt(3));
                    gioHang.setTenSanPham(c.getString(4));
                    gioHang.setGiaSanPham(c.getInt(5));
                    list.add(gioHang);
                } while (c.moveToNext());
            }
        } catch (Exception e) {
            Log.i(TAG, "Lỗiiii", e);
        }
        return list;
    }
    public boolean insertGioHang(GioHang gioHang){
        SQLiteDatabase da = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("masanpham",gioHang.getMagiay());
        values.put("mataikhoan",gioHang.getMaNguoiDung());
        values.put("soluong",gioHang.getSoLuongMua());
        long check = da.insert("GIOHANG",null,values);
        return check>0;
    }
    public boolean updateGioHang(GioHang gioHang) {
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("soluong", gioHang.getSoLuongMua());
        int rowsAffected = database.update("GIOHANG", values, "magiohang = ?", new String[]{String.valueOf(gioHang.getMaGioHang())});
        return rowsAffected > 0;
    }
    public boolean deleteGioHang(GioHang gioHang){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        long check = sqLiteDatabase.delete("GIOHANG","magiohang = ?",new String[]{String.valueOf(gioHang.getMaGioHang())});
        return check >0;
    }
    public GioHang getGioHangByMasp(int masp, int mand) {
        GioHang gioHang = null;
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        try {
            Cursor cursor = database.rawQuery("SELECT * FROM GIOHANG WHERE masanpham = ? AND mataikhoan = ?", new String[]{String.valueOf(masp), String.valueOf(mand)});
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                gioHang = new GioHang();
                gioHang.setMaGioHang(cursor.getInt(0));
                gioHang.setMagiay(cursor.getInt(1));
                gioHang.setMaNguoiDung(cursor.getInt(2));
                gioHang.setSoLuongMua(cursor.getInt(3));
            }
            cursor.close();
        } catch (Exception e) {
            Log.e(TAG, "Error", e);
        }
        return gioHang;
    }
}
