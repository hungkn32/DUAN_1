package com.example.duan_1.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public static final String dbname = "QLSHOES";
    public static final int dbvesion =17;

    public DBHelper(Context context) {

        super(context, dbname, null, dbvesion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String tb_Admin = "Create table ADMIN(madn text primary key , " +
                "tenDangNhap TEXT  NOT NULL," +
                "matkhau TEXT  NOT NULL)";
        db.execSQL(tb_Admin);
        String tb_giay = "CREATE TABLE GIAY (maGiay INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "tenGiay TEXT NOT NULL, " +
                "loaiGiay TEXT NOT NULL, " +
                "giaTien TEXT NOT NULL, " +
                "avatagiay TEXT NOT NULL)";
        db.execSQL(tb_giay);

        String tb_khachhang = "Create table KHACHHANG(maKH integer primary key autoincrement," +
                "tenKH TEXT  NOT NULL," +
                "namSinh TEXT  NOT NULL," +
                "sdt TEXT  NOT NULL," +
                "diaChi TEXT  NOT NULL," +
                "urlkhachhang TEXT  NOT NULL)";
        db.execSQL(tb_khachhang);
        String tb_dathang = "Create table DATHANG(maDH integer primary key autoincrement," +
                "tenKH TEXT  REFERENCES KHACHHANG(tenKH) NOT NULL," +
                "diaChi TEXT    REFERENCES KHACHHANG(diaChi) NOT NULL," +
                "tenGiay TEXT    REFERENCES GIAY(tenGiay) NOT NULL," +
                "loaiGiay TEXT    REFERENCES GIAY(loaiGiay) NOT NULL," +
                "giaTien TEXT   REFERENCES GIAY(giaTien) NOT NULL," +
                "ngayMua DATE NOT NULL," +
                "trangThai INTEGER NOT NULL)";
        db.execSQL(tb_dathang);

        db.execSQL("Insert into ADMIN values('admin','admin','admin123')," +
                "('nhanvien','nhanvien','nhanvien123')");

        db.execSQL("INSERT INTO GIAY VALUES(1,'Giày Nike White','Nike','19999','https://shopgiayreplica.com/wp-content/uploads/2018/07/nike-air-force-1-low-replica.jpeg')," +
            "(2,'Giày Adidas','Adidas','29999','https://product.hstatic.net/200000255805/product/z3744531959920_d9759f17887e00d6899ff0650ba0b8ca_70e805bd5bba4a89b08f7cab50030603_master.jpg')," +
            "(3,'Giày Bitis','Bitis','39999','https://product.hstatic.net/1000230642/product/-hunter-x-wavy-collection-hsm001400xnh-xanh-nhot-5o654-color-xanh-nhot_1d01269da8544dc7956d1c0c363b2f6b_grande.jpg')");
        // Ví dụ về cách chèn dữ liệu mẫu vào bảng "GIAY"
        db.execSQL("INSERT INTO KHACHHANG VALUES(1,'Hưng','2004','0347980858','Thôn 9 Hà Nội ','https://png.pngtree.com/png-vector/20190623/ourlarge/pngtree-accountavataruser--flat-color-icon--vector-icon-banner-templ-png-image_1491720.jpg')");
        db.execSQL("INSERT INTO KHACHHANG VALUES(2,'Thành','2003','0377929586','Bắc Giang ','https://png.pngtree.com/png-vector/20190623/ourlarge/pngtree-accountavataruser--flat-color-icon--vector-icon-banner-templ-png-image_1491720.jpg')");
        db.execSQL("INSERT INTO KHACHHANG VALUES(3,'Giang','2004','0385312544','Hưng Yên ','https://png.pngtree.com/png-vector/20190623/ourlarge/pngtree-accountavataruser--flat-color-icon--vector-icon-banner-templ-png-image_1491720.jpg')");





    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        if (i != i1) {
            db.execSQL("DROP TABLE IF EXISTS ADMIN");
            db.execSQL("DROP TABLE IF EXISTS GIAY");
            db.execSQL("DROP TABLE IF EXISTS KHACHHANG");
            db.execSQL("DROP TABLE IF EXISTS DATHANG");
            onCreate(db);
        }

    }
}
