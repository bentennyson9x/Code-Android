package com.example.saint.quan_ly_chi_tieu;

import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

public class FlashScreen extends AppCompatActivity implements Runnable {
    TextView Title_app, progress_percent;
    ProgressBar progressBar;
    int status_progress;
    Handler handler = new Handler();
    Thread progress = new Thread(){
        public void run(){
            while (status_progress++<100){
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        progressBar.setProgress(status_progress);
                        progress_percent.setText(status_progress+"%");
                    }
                });
                if (status_progress>=60&&status_progress<70){
                    try {
                        Thread.sleep(150);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if (status_progress>=80&&status_progress<90){
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if (status_progress>=90){
                    try {
                        Thread.sleep(80);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Intent intent = new Intent(FlashScreen.this,MainActivity.class);
            startActivity(intent);
            finish();

        }
    };
    Thread setTextTitleBlink = new Thread(){
        public void run (){
            int count = 0 ;
            while (count ++ <10){
                if (count%2==0){
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            Title_app.setTextColor(Color.parseColor("#c6414040"));//
                        }
                    });
                }
                else {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            Title_app.setTextColor(Color.parseColor("#f56b6b"));// do
                        }
                    });
                }
                if (count==10) count=0;
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash_screen);
        FindButton();
        progress.start();
        setTextTitleBlink.start();
    }

    private void FindButton() {
        Title_app = findViewById(R.id.titleApp);
        progress_percent = findViewById(R.id.progress_percent);
        progressBar = findViewById(R.id.progressBar);
    }

    @Override
    public void run() {

    }
}
