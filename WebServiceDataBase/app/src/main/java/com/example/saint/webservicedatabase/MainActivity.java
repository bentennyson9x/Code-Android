package com.example.saint.webservicedatabase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    ListView listSinhVien;
    BaseAdapter adapterSinhVien;
    ArrayList <SinhVien> sinhViens;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listSinhVien = findViewById(R.id.listSinhVien);
        sinhViens = new ArrayList();
        adapterSinhVien = new SinhVienAdapter(MainActivity.this,R.layout.custom_list_sinhvien,sinhViens);
        listSinhVien.setAdapter(adapterSinhVien);
        getData("http://192.168.5.104/webservice/getData.php");

    }
    public void DeleteSV(String url , final int idSV ){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.trim().equals("success")){
                    getData("http://192.168.5.104/webservice/getData.php");
                }
                else {
                    Toast.makeText(MainActivity.this, "Lỗi Xóa", Toast.LENGTH_SHORT).show();
                    Log.e("xem",response.toString());
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                Log.e("xem",error.toString());

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map <String,String>  params = new HashMap<>();
                params.put("idCuaSV", String.valueOf(idSV));
                return params;
            }
        };
        requestQueue.add(request);
    }

    private void getData(String url ) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                sinhViens.clear();
                for (int i =0;i<response.length();i++){
                    try {
                        JSONObject object = response.getJSONObject(i);
                        SinhVien sinhVien = new SinhVien(object.getInt("id"),
                                object.getString("hoten")
                                ,object.getString("namsinh"),object.optString("diachi"));
                        sinhViens.add(sinhVien);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                adapterSinhVien.notifyDataSetChanged();
                Log.e("xem", String.valueOf(adapterSinhVien.getCount()));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Lỗi", Toast.LENGTH_SHORT).show();
                Log.e("xem",error.toString());
            }
        });
        requestQueue.add(jsonArrayRequest);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_student,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menuAddStudent){
            Intent intent = new Intent(MainActivity.this,AddSinhVien.class);
            startActivity(intent);

        }
        return super.onOptionsItemSelected(item);

    }
}
