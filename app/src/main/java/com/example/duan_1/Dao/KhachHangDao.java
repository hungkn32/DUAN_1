package com.example.duan_1.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duan_1.Database.DBHelper;
import com.example.duan_1.Model.khachhang;

import java.util.ArrayList;

public class KhachHangDao {
    DBHelper dbHelper;
    public KhachHangDao(Context context){
        dbHelper =new DBHelper(context);
    }
    public ArrayList<khachhang> getall(){
        SQLiteDatabase database =dbHelper.getReadableDatabase();
        ArrayList<khachhang> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM KHACHHANG",null);
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            do {
             list.add(new khachhang(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getInt(3),cursor.getString(4),cursor.getString(5)));
            } while (cursor.moveToNext());
        }
        return list;
    }

    public  boolean insert(String ten,String namsinh,int sdt,String diachi,String url){
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("tenKH ",ten);
        values.put("namSinh",namsinh);
        values.put("sdt",sdt);
        values.put("diaChi",diachi);
        values.put("urlkhachhang",url);
        long check = database.insert("KHACHHANG",null,values);
        if(check == -1){
            return false;
        }else{
            return true;
        }
    }
    public int delete(int makh){
       SQLiteDatabase database = dbHelper.getWritableDatabase();
        long check = database.delete("KHACHHANG", "maKH = ?", new String[]{String.valueOf(makh)});
        if (check == -1) {
            return 0;
        } else {
            return 1;
        }
    }
}
