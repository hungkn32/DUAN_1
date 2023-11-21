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
                list.add(new giay(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getInt(3),cursor.getInt(4)));
            } while (cursor.moveToNext());
        }
        return list;
    }

}
