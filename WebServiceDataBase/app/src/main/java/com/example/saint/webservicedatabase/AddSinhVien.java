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

import java.util.HashMap;
import java.util.Map;

public class AddSinhVien extends AppCompatActivity implements View.OnClickListener {
    EditText edtName,edtNamSinh,edtDiaChi;
    Button Them,Huy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sinh_vien);
        AddView();
        Click();
    }

    private void Click() {
        Them.setOnClickListener(this);
        Huy.setOnClickListener(this);
    }

    private void AddView() {
    edtName = findViewById(R.id.insertNameSV);
    edtDiaChi = findViewById(R.id.insertAddress);
    edtNamSinh = findViewById(R.id.insertYearOfBirth);
    Them = findViewById(R.id.btnThemSV);
    Huy = findViewById(R.id.btnHuyThem);

    }

    @Override
    public void onClick(View view) {
        if (view == Them){
            String name = edtName.getText().toString().trim();
            String addr = edtDiaChi.getText().toString().trim();
            String yearBirth = edtNamSinh.getText().toString().trim();
            if (name.isEmpty()||addr.isEmpty()||yearBirth.isEmpty()){
                Toast.makeText(this, "Vui lòng nhập đủ thông tin", Toast.LENGTH_SHORT).show();
            }
            else {
                ThemSinhVien("http://192.168.5.104/webservice/inSert.php");
            }

        }
        if (view == Huy){

        }
    }

    private void ThemSinhVien(String url) {
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.trim().equals("success")){
                            Toast.makeText(AddSinhVien.this, "OK!!", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(AddSinhVien.this,MainActivity.class));
                        }
                        else {
                            Toast.makeText(AddSinhVien.this, "Không thành công", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(AddSinhVien.this, "Không thành công do server", Toast.LENGTH_SHORT).show();
                Log.e("xem",error.toString());
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map <String,String > params = new HashMap<>();
                params.put("hotenSV",edtName.getText().toString());
                params.put("namsinhSV",edtNamSinh.getText().toString());
                params.put("diachiSV",edtDiaChi.getText().toString());
                return params;
            }
        };
        queue.add(stringRequest);
    }
}
