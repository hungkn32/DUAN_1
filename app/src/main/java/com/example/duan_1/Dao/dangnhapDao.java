package com.example.duan_1.Dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duan_1.Database.DBHelper;
import com.example.duan_1.Model.admin;

import java.util.ArrayList;
import java.util.List;

public class dangnhapDao {
        DBHelper dbHelper;
        SQLiteDatabase db;

        public dangnhapDao(Context context) {
                DBHelper dbHelper = new DBHelper(context);
                db = dbHelper.getWritableDatabase();
        }

        public long insert(admin obj) {
                ContentValues values = new ContentValues();
                values.put("madn", obj.getMadn());
                values.put("tenDangNhap", obj.getTendangnhap());
                values.put("matkhau", obj.getMatkhau());
                return db.insert("ADMIN", null, values);
        }

        public long updatePass(admin obj) {
                ContentValues values = new ContentValues();
                values.put("tenDangNhap", obj.getTendangnhap());
                values.put("matkhau", obj.getMatkhau());
                return db.update("ADMIN", values, "madn = ?", new String[]{String.valueOf(obj.getMadn())});
        }



        public List<admin> getAll() {
                String sql = "SELECT * FROM ADMIN";
                return getData(sql);
        }

        public admin getID(String id) {
                String sql = "SELECT * FROM ADMIN WHERE madn=?";
                List<admin> list = getData(sql, id);
                return list.get(0);
        }

        // check login
        public int checkLogin(String id, String password) {
                String sql = "SELECT * FROM ADMIN WHERE madn=? AND matkhau=?";
                List<admin> list = getData(sql, id, password);
                if (list.size() == 0) {
                        return -1;
                }
                return 1;
        }

        @SuppressLint("Range")
        private List<admin> getData(String sql, String... selectionArgs) {
                List<admin> list = new ArrayList<>();
                Cursor cursor = db.rawQuery(sql, selectionArgs);
                while (cursor.moveToNext()) {
                        admin obj= new admin();
                        obj.setMadn(cursor.getString(cursor.getColumnIndex("madn")));
                        obj.setTendangnhap(cursor.getString(cursor.getColumnIndex("tenDangNhap")));
                        obj.setMatkhau(cursor.getString(cursor.getColumnIndex("matkhau")));
                        list.add(obj);
                }
                return list;
        }
}
