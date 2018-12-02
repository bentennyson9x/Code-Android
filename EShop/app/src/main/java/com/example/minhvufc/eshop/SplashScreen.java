package com.example.minhvufc.eshop;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
    }

    @Override
    protected void onResume() {
        super.onResume();
        checkPermision();
    }

    /**
     * Kiểm tra từ phiên bản Marshmallow cần check runtime
     */
    private void checkPermision() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                    || checkSelfPermission(Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{
                        android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.INTERNET}, 911);
            } else {
                autoChimCut(); // Tình huống 3: đã cấp quyền rồi => CÚT
            }
        } else {
            autoChimCut(); // Tình huống 2: Dưới phiên bản M => CÚT
        }
    }

    private void autoChimCut() {
        finish(); // Kết thúc Flashscreen
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent); // Chuyển sang MainActivity
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 911) {
            Toast.makeText(SplashScreen.this, "Đã cung cấp đủ quyền", Toast.LENGTH_SHORT).show();
            autoChimCut(); // Tình huống 1: cung cấp xong => CÚT
        }
    }
}
