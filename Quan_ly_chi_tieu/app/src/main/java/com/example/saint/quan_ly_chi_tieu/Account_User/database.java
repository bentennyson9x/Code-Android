package com.example.saint.quan_ly_chi_tieu.Account_User;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class database  extends SQLiteOpenHelper {
    private static final String DATABASE_NAME="account_finance_manager.sqlite";
    private static final int DATABASE_VERSION=1;
    private final String LBLUSER = "CREATE TABLE account (\n" +
            "    user     VARCHAR PRIMARY KEY\n" +
            "                     NOT NULL,\n" +
            "    password VARCHAR NOT NULL\n" +
            ");\n";

    public database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(LBLUSER);
        String sql = "INSERT INTO account ( user, password ) VALUES ( 'bentennyson_9x','nguyentseo982210' )";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
