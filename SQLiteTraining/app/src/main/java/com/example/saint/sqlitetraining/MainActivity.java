package com.example.saint.sqlitetraining;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    DataBase dataBase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dataBase = new DataBase(this,"app_ghi_chu.sqlite",null,1);
        dataBase.QueryData("CREATE TABLE IF NOT EXISTS CongViec()");
    }
}
