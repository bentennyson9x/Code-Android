package com.example.saint.quan_ly_chi_tieu.Account_User;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class Account_manager {
    Context mContext;
    SQLiteDatabase mdb;

    public Account_manager(Context mContext, SQLiteDatabase mdb) {
        this.mContext = mContext;
        this.mdb = mdb;
    }
    public Account_manager(Context mContext) {
        this.mContext = mContext;
        // Khởi tạo đối tượng databaseHelper để kết nối CSDL
        database helper = new database(mContext);
        // Từ Helper lấy đối tượng SQLiteDataBase để thao tác với dữ liệu
        mdb=helper.getWritableDatabase();
    }
    public ArrayList <Account> read(){
        ArrayList<Account>list =new ArrayList<>();
        String sql = "SELECT * From account";
        Cursor cursor = mdb.rawQuery(sql,null); // la mang, neu khong co gia tri thi la null
        while (cursor.moveToNext()){
            String User = cursor.getString(cursor.getColumnIndex("user"));
            String Password = cursor.getString(cursor.getColumnIndex("password"));
            Account account = new Account(User,Password);
            list.add(account);
        }
        return list;
    }
    public void Create (Account account){
        String sql = "INSERT INTO account ( user, password ) VALUES ( '"+account.getUser()+"','"+account.getPassword()+"' )\n" +
                "SELECT * From account";
        mdb.execSQL(sql);

    }
    public void update (Account account){


    }
    public void delete (Account account){

    }
}

