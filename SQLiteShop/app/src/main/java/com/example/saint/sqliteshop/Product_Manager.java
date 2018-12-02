package com.example.saint.sqliteshop;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class Product_Manager {
    Context mContext;
    SQLiteDatabase mdb;

    public Product_Manager(Context mContext, SQLiteDatabase mdb) {
        this.mContext = mContext;
        this.mdb = mdb;
    }

    public Product_Manager(Context mContext) {
        this.mContext = mContext;
        // Khởi tạo đối tượng databaseHelper để kết nối CSDL
        DataBaseHelper helper = new DataBaseHelper(mContext);
        // Từ Helper lấy đối tượng SQLiteDataBase để thao tác với dữ liệu
        mdb=helper.getWritableDatabase();
    }
    public ArrayList<Product> read(){
        ArrayList<Product> list = new ArrayList<>();
        //TODO : lấy dữ liệu
        String sql = "SELECT * FROM product";
        Cursor cursor = mdb.rawQuery(sql,null); // la mang, neu khong co gia tri thi la null
        while (cursor.moveToNext()){
            int id = cursor.getInt(0); // se tro vao cot dau tien la cot 0
            String name = cursor.getString(cursor.getColumnIndex("name"));
            int quantity = cursor.getInt(2);
            double price = cursor.getDouble(3);
            String date = cursor.getString(4);
            Product p = new Product(id,name,quantity,price,date);
            list.add(p);
        }
        return list;
    }
    public void create (Product product){
        // có thể dùng ContentValue => tham khảo bài lab
        String sql = "INSERT INTO product ( name,quantity,price,inputdate) VALUES ('"+product.name+"',"+product.quantity+","+product.price+",'"+product.inputdate+"')\n" ;
        mdb.execSQL(sql);
        sql = "SELECT * FROM tblSinhVien";
        mdb.rawQuery(sql, null);
    }
    public void update (Product product){

    }
    public void delete (int id){

    }
}
