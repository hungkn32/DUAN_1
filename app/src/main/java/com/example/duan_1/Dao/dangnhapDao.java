package com.example.duan_1.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duan_1.Database.DBHelper;
import com.example.duan_1.Model.admin;

import java.util.ArrayList;

public class dangnhapDao {
        DBHelper dbHelper;
        SQLiteDatabase database;

        public dangnhapDao(Context context){
                dbHelper = new DBHelper(context);
        }
        public boolean checklogin(String user,String pass) {
                SQLiteDatabase database= dbHelper.getReadableDatabase();
                Cursor cursor = database.rawQuery("SELECT * FROM ADMIN WHERE tenDangNhap = ? AND matkhau =?",new String[]{user,pass});
                if (cursor.getCount() > 0) {
                        return true;
                } else {
                        return false;
                }
        }

        public boolean Register( String tendn, String password,String loaitk) {
                SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("tenDangNhap", tendn);
                values.put("matkhau", password);
                values.put("loaitk", loaitk);

                long check = sqLiteDatabase.insert("ADMIN", null, values);
                if (check != 0) {
                        return true;
                } else {
                        return false;
                }

        }}
