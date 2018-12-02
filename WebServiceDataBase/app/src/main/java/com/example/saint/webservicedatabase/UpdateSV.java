package com.example.saint.webservicedatabase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.BufferedReader;
import java.util.HashMap;
import java.util.Map;

public class UpdateSV extends AppCompatActivity implements View.OnClickListener {
    EditText edtName,edtAddr,edtYearOfBirth;
    Button btnSuaSV,btnHuy;
    SinhVien sinhVien;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_sv);
        AddView();
        Intent intent = getIntent();
        sinhVien = (SinhVien) intent.getSerializableExtra("dataSinhVien");
        edtName.setText(sinhVien.getName());
        edtAddr.setText(sinhVien.getDiaChi());
        edtYearOfBirth.setText(sinhVien.getNamSinh());
        Click();

    }

    private void Click() {
    btnSuaSV.setOnClickListener(this);

    }

    private void UpdateSinhVien(String url) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("xemr",response.toString());
                if (response.trim().equals("success")) {
                    Toast.makeText(UpdateSV.this, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(UpdateSV.this,MainActivity.class));
                }
                else {
                    Toast.makeText(UpdateSV.this, "Cập nhật thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(UpdateSV.this, "Lỗi rồi", Toast.LENGTH_SHORT).show();
                Log.e("xem",error.toString());
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map <String,String> param = new HashMap<>();
                param.put("idSV", String.valueOf(sinhVien.getId()));
                param.put("hotenSV",edtName.getText().toString());
                param.put("namsinhSV",edtYearOfBirth.getText().toString());
                param.put("diachiSV",edtAddr.getText().toString());
                return param;// de y phai tra ve param
            }

        };
        requestQueue.add(request);
    }

    private void AddView() {
        edtName = findViewById(R.id.updateNameSV);
        edtAddr = findViewById(R.id.updateAddress);
        edtYearOfBirth = findViewById(R.id.updateYearOfBirth);
        btnSuaSV = findViewById(R.id.btnSuaSV);
        btnHuy = findViewById(R.id.btnHuySua);
    }

    @Override
    public void onClick(View view) {
        if (view == btnSuaSV){
            String hoten = edtName.getText().toString().trim();
            String addr = edtAddr.getText().toString().trim();
            String yearOfBirth = edtYearOfBirth.getText().toString().trim();
            if (hoten.matches("")||addr.equals("")||yearOfBirth.length()==0){
                Toast.makeText(this, "Vui lòng nhập đủ thông tin", Toast.LENGTH_SHORT).show();
            }
            else {
                UpdateSinhVien("http://192.168.5.104/webservice/update.php");
            }

        }
    }
}
