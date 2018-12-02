package com.example.saint.sqliteshop;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME="shop.sqlite";
    private static final int DATABASE_VERSION=1;
    private final String TBLSHOP ="CREATE TABLE product (\n" +
            "    id        INTEGER      PRIMARY KEY AUTOINCREMENT,\n" +
            "    name      VARCHAR (32) NOT NULL,\n" +
            "    quantity  INTEGER,\n" +
            "    price     DOUBLE       DEFAULT (0),\n" +
            "    inputdate DATE\n" +
            ");\n";
    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     *   Phương thức sẽ được gọi lần đầu tiên khi cài đặt ứng dụng
     hoặc khi csdl không tồn tại
     * @param sqLiteDatabase
     */
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
    // Tạo bằng product
        sqLiteDatabase.execSQL(TBLSHOP);
        String sqlin = "INSERT INTO product ( name,quantity,price,inputdate) VALUES ('Ban phim',5,150000,'2018-02-15')\n";
        sqLiteDatabase.execSQL(sqlin);
        sqlin = "INSERT INTO product ( name,quantity,price,inputdate) VALUES ('Man Hinh',8,190000,'2018-03-15')\n";
        sqLiteDatabase.execSQL(sqlin);
    }

    /**
     * Được gọi khi ứng dụng trên thiết bị ( của người dùng ) có tồn tại csdl x
     * Nhưng sai khác phiên bản thì gọi thực nâng cấp cơ sở dữ liệu
     * @param sqLiteDatabase
     * @param i
     * @param i1
     */
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
