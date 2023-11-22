package com.example.duan_1.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duan_1.Database.DBHelper;
import com.example.duan_1.Model.giay;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class giayDao {

    SQLiteDatabase database;
    DBHelper dbHelper;


    public  giayDao(Context context) {
        dbHelper = new DBHelper(context);
    }

    public ArrayList<giay> getAll() {
        ArrayList list = new ArrayList();
        database = dbHelper.getReadableDatabase();
        Cursor cursor = database.rawQuery("Select* from GIAY", null);
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            do {
                list.add(new giay(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getInt(3),cursor.getString(4)));
            } while (cursor.moveToNext());
        }
        return list;
    }
    public boolean insert(String tengiay,String loaigiay,int giatien,String urlavata) {
        database = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("tenGiay",tengiay);
        values.put("loaiGiay", loaigiay);
        values.put("giaTien", giatien);
        values.put("avatagiay", urlavata);
        long check = database.insert("GIAY",null,values);
        if(check == -1){
            return false;
        }else{
            return true;
        }
    }

    public int delete(int magiay){
         database = dbHelper.getWritableDatabase();
        Cursor cursor = database.rawQuery("select * from DONHANG where maGiay = ?", new String[]{String.valueOf(magiay)});
        if (cursor.getCount() != 0) {
            return -1;
        }

        long check = database.delete("GIAY", "maGiay = ?", new String[]{String.valueOf(magiay)});
        if (check == -1) {
            return 0;
        } else {
            return 1;
        }
    }
    public boolean update(int ma,String ten, String loai,int gia,String url){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("maGiay",ma);
        values.put("tenGiay",ten);
        values.put("loaiGiay", loai);
        values.put("giaTien", gia);
        values.put("avatagiay", url);
        long check = db.update("GIAY",values,"maGiay = ?",new String[]{String.valueOf(ma)});
        if(check == -1){
            return false;
        }else{
            return true;
        }
    }
}
