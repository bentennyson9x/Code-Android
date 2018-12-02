package com.example.saint.tempproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Main2Activity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        String a = "dmm";
        DataManagement.putData("Key",a);
        Intent  intent = new Intent(Main2Activity.this,MainActivity.class);
        startActivity(intent);
    }
}
