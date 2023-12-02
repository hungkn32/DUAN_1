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
            Cursor cursor = database.rawQuery("SELECT DONHANG.madonhang, ADMIN.mataikhoan, KHACHHANG.tenKH, KHACHHANG.diaChi, GIAY.tenGiay, GIAY.loaiGiay, DONHANG.ngaydathang, DONHANG.tongtien, DONHANG.trangthai\n" +
                    "FROM DONHANG\n" +
                    "LEFT JOIN ADMIN ON DONHANG.mataikhoan = ADMIN.mataikhoan\n" +
                    "LEFT JOIN KHACHHANG ON DONHANG.tenKH = KHACHHANG.tenKH AND DONHANG.diaChi=KHACHHANG.diaChi\n" +
                    "LEFT JOIN GIAY ON DONHANG.tenGiay = GIAY.tenGiay AND DONHANG.loaiGiay = GIAY.loaiGiay;\n", null);
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                do {
                    DonHang donHang = new DonHang();
                    donHang.setMaDonHang(cursor.getInt(0));
                    donHang.setMataikhoan(cursor.getInt(1));
                    donHang.setTenkh(cursor.getString(2));
                    donHang.setDiachi(cursor.getString(3));
                    donHang.setTengiay(cursor.getString(4));
                    donHang.setLoaigiay(cursor.getString(5));
                    donHang.setNgayDatHang(cursor.getString(6));
                    donHang.setTongTien(cursor.getInt(7));
                    donHang.setTrangthai(cursor.getString(8));
                    list.add(donHang);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.d(TAG, "Lỗi", e);
        }
        return list;
    }
    public boolean xoaDonHang(int madonhang){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        long check = sqLiteDatabase.delete("DONHANG","madonhang = ?",new String[]{String.valueOf(madonhang)});
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
        values.put("mataikhoan",donHang.getMataikhoan());
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

    public int getDoanhThu(String Start, String End){
        Start = Start.replace("/","");
        End = End.replace("/","");
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT SUM(tongTien) FROM DONHANG WHERE substr(ngaydathang,7) || substr(ngaydathang,4,2) || substr(ngaydathang,1,2) BETWEEN ? and ?",new String[]{Start,End});
        if(cursor.getCount() != 0){
            cursor.moveToFirst();
            return cursor.getInt(0);
        }
        return 0;
    }
}
