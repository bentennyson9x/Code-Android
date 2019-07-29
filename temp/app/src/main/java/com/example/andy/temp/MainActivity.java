package com.example.andy.temp;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Canvas canvas = new Canvas(this);
        canvas.setBackgroundColor(Color.RED);
        setContentView(canvas);
    }
}
