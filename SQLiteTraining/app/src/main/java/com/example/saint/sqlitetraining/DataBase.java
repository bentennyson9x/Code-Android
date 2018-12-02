package com.example.saint.sqlitetraining;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBase extends SQLiteOpenHelper {
    public DataBase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
// Truy van khong tra ket qua : Creat,  Insert , Update, Delete ...
    public void QueryData ( String Sql ){

        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(Sql);
    }
// Truy van tra ve ket qua :  Select
    private Cursor GetData( String Sql ){
        SQLiteDatabase database = getWritableDatabase();
        return database.rawQuery(Sql,null);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
