package com.example.saint.bkap_sm;

import android.os.Bundle;
import android.app.Activity;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class Home extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ArrayList <SinhVien > sinhViens = new ArrayList<>();
        sinhViens.add(new SinhVien("Đỗ Hoàng Khôi Nguyên","0912569581","saintsmiles2210@gmail.com","Android",true));
        sinhViens.add(new SinhVien("Đỗ Hoàng Khôi Nguyên","0912569581","saintsmiles2210@gmail.com","Android",true));
        sinhViens.add(new SinhVien("Đỗ Hoàng Khôi Nguyên","0912569581","saintsmiles2210@gmail.com","Android",true));
        ArrayAdapter <SinhVien > sinhVienArrayAdapter = new ArrayAdapter<>(this,R.layout.custom_list_sinhvien,sinhViens);
    }

}
