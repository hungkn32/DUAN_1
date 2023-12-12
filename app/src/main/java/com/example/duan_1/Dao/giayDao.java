package com.example.duan_1.Dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.duan_1.Database.DBHelper;
import com.example.duan_1.Model.giay;

import java.util.ArrayList;
import java.util.List;

public class giayDao {

  private   SQLiteDatabase database;
    private DBHelper dbHelper;


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
                list.add(new giay(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getInt(3),cursor.getString(4),cursor.getInt(5)));
            } while (cursor.moveToNext());
        }
        return list;
    }
    public boolean insert(String tengiay,String loaigiay,int giatien,String urlavata,int soluong) {
        database = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("tenGiay",tengiay);
        values.put("loaiGiay", loaigiay);
        values.put("giaTien", giatien);
        values.put("avatagiay", urlavata);
        values.put("soLuong",soluong);
        long check = database.insert("GIAY",null,values);
        if(check == -1){
            return false;
        }else{
            return true;
        }
    }
    public giay getID(String id) {
        String sql = "SELECT * FROM GIAY WHERE maGiay=?";
        List<giay> list = getData(sql, id);
        return list.get(0);
    }

    public boolean delete(int magiay){
         database = dbHelper.getWritableDatabase();
        long row = database.delete("GIAY", "maGiay=?", new String[]{String.valueOf(magiay)});
        return (row > 0);
    }
    public boolean update(int ma,String ten, String loai,int gia,String url,int soluong){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("maGiay",ma);
        values.put("tenGiay",ten);
        values.put("loaiGiay", loai);
        values.put("giaTien", gia);
        values.put("avatagiay", url);
        values.put("soLuong",soluong);
        long check = db.update("GIAY",values,"maGiay = ?",new String[]{String.valueOf(ma)});
        if(check == -1){
            return false;
        }else{
            return true;
        }
    }
    @SuppressLint("Range")
    private List<giay> getData(String sql, String... selectionArgs) {
        List<giay> list = new ArrayList<>();
        Cursor cursor = database.rawQuery(sql, selectionArgs);
        while (cursor.moveToNext()) {
            giay obj = new giay(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getInt(3), cursor.getString(4),cursor.getInt(5));
            obj.setTenGiay((cursor.getString(cursor.getColumnIndex("tenGiay"))));
            obj.setLoaiGiay(cursor.getString(cursor.getColumnIndex("loaiGiay")));
            obj.setMagiay(Integer.parseInt(cursor.getString(cursor.getColumnIndex("maGiay"))));
            obj.setGiaTien(Integer.parseInt(cursor.getString(cursor.getColumnIndex("giaTien"))));
            obj.setAvataanh(cursor.getString(cursor.getColumnIndex("avatagiay")));
            obj.setSoluong(Integer.parseInt(cursor.getString(cursor.getColumnIndex("soLuong"))));
            Log.i("//==", obj.toString());
            list.add(obj);
        }
        return list;
    }
}
