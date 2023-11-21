package com.example.duan_1.Dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duan_1.Database.DBHelper;
import com.example.duan_1.Model.giay;
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
             list.add(new khachhang(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getInt(3),cursor.getString(4)));
            } while (cursor.moveToNext());
        }
        return list;
    }
}
