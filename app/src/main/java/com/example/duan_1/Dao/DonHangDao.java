package com.example.duan_1.Dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duan_1.Database.DBHelper;
import com.example.duan_1.Model.hoadon;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class DonHangDao {
    DBHelper dbHelper;
    private SQLiteDatabase database;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
    public DonHangDao(Context context){
        dbHelper = new DBHelper(context);
    }
    public long insert(hoadon obj) {
        database = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("maDH", obj.getMadh());
        values.put("tenKH", obj.getTenkh());
        values.put("diaChi", obj.getDiachi());
        values.put("tenGiay", obj.getTengiay());
        values.put("loaiGiay", obj.getLoaigiay());
        values.put("giaTien", obj.getGiatien());
        values.put("ngayMua", sdf.format(obj.getNgaymua()));
        values.put("trangThai", obj.getTrangthai());
        return database.insert("DONHANG", null, values);
    }

    public long update(hoadon obj) {
        database = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("maDH", obj.getMadh());
        values.put("tenKH", obj.getTenkh());
        values.put("diaChi", obj.getDiachi());
        values.put("tenGiay", obj.getTengiay());
        values.put("loaiGiay", obj.getLoaigiay());
        values.put("giaTien", obj.getGiatien());
        values.put("ngayMua", sdf.format(obj.getNgaymua()));
        values.put("trangThai", obj.getTrangthai());
        return database.update("DONHANG", values, "maDH = ?", new String[]{String.valueOf(obj.getMadh())});
    }

    public long delete(int id) {
        database = dbHelper.getWritableDatabase();
        return database.delete("DONHANG", "maDH = ?", new String[]{String.valueOf(id)});
    }

    public List<hoadon> getAll() {
        String sql = "SELECT * FROM DONHANG";
        return getData(sql);
    }

    public hoadon getID(String id) {
        String sql = "SELECT * FROM PhieuMuon WHERE maPM=?";
        List<hoadon> list = getData(sql, id);
        return list.get(0);
    }

    @SuppressLint("Range")
    private List<hoadon> getData(String sql, String... selectionArgs) {
        List<hoadon> list = new ArrayList<>();
        Cursor cursor = database.rawQuery(sql, selectionArgs);
        while (cursor.moveToNext()) {
           hoadon obj = new hoadon();
            obj.setMadh(Integer.parseInt(cursor.getString(cursor.getColumnIndex("maDH"))));
            obj.setTenkh(cursor.getString(cursor.getColumnIndex("tenKH")));
            obj.setDiachi((cursor.getString(cursor.getColumnIndex("diaChi"))));
            obj.setTengiay((cursor.getString(cursor.getColumnIndex("tenGiay"))));
            obj.setLoaigiay(cursor.getString(cursor.getColumnIndex("loaiGiay")));
            obj.setGiatien(cursor.getString(cursor.getColumnIndex("giaTien")));
            try {
                obj.setNgaymua(Integer.parseInt(String.valueOf(sdf.parse(cursor.getString(cursor.getColumnIndex("ngayMua"))))));
//                Log.d("jjfjn", "getData: "+sdf.parse(cursor.getString(cursor.getColumnIndex("ngay"))));
            } catch (ParseException e) {
                e.printStackTrace();
//                Log.i("akhjj","123");
            }
            obj.setTrangthai(Integer.parseInt(cursor.getString(cursor.getColumnIndex("trangThai"))));
            list.add(obj);
        }
        return list;
    }


    // thống kê top 10
//    @SuppressLint("Range")
//    public List<Top> getTop() {
//        String sqlTop = "SELECT maSach,count(maSach) as soLuong FROM PhieuMuon GROUP BY maSach ORDER BY soLuong DESC LIMIT 10";
//        List<Top> list = new ArrayList<Top>();
//        SachDAO sachDAO = new SachDAO(context);
//        Cursor cursor = db.rawQuery(sqlTop, null);
//        while (cursor.moveToNext()) {
//            Top top = new Top();
//            @SuppressLint("Range") Sach sach = sachDAO.getID(cursor.getString(cursor.getColumnIndex("maSach")));
//            top.setTenSach(sach.getTenSach());
//            top.setSoLuong(Integer.parseInt(cursor.getString(cursor.getColumnIndex("soLuong"))));
//            list.add(top);
//
//        }
//        return list;
//    }

    // thống kê doanh thu
//    @SuppressLint("Range")
//    public int getDoanhThu(String tuNgay, String denNgay) {
//        String sqlDoanhThu = "SELECT SUM(tienThue) as doanhThu FROM PhieuMuon WHERE ngay BETWEEN ? AND ?";
//        List<Integer> list = new ArrayList<Integer>();
//        Cursor cursor = db.rawQuery(sqlDoanhThu, new String[]{tuNgay, denNgay});
//        while (cursor.moveToNext()) {
//            try {
//                list.add(Integer.parseInt(cursor.getString(cursor.getColumnIndex("doanhThu"))));
//
//            } catch (Exception e) {
//                list.add(0);
//            }
//        }
//        return list.get(0);
//    }
}
