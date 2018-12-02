package com.example.saint.jsonarrayobject;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    String result ;
    ListView listView;
    ArrayList<String> arrayCourse;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addView();
        ReadJson readJson = new ReadJson();
        readJson.execute("https://khoapham.vn/KhoaPhamTraining/json/tien/demo4.json");
        listView.setAdapter(adapter);

    }

    private void addView() {
        listView = findViewById(R.id.ListViewKhoaHoc);
        arrayCourse = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrayCourse);

    }

    private void ReadObjectJSonArray() {
        try {
            JSONArray array = new JSONArray(result);
            for (int i=0;i<array.length();i++){
                JSONObject object = array.getJSONObject(i);
                String khoaHoc = object.getString("khoahoc");
                String hocPhi = object.getString("hocphi");
                arrayCourse.add(khoaHoc+"-"+hocPhi);
            }
            adapter.notifyDataSetChanged();

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private class ReadJson extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            String str = strings[0];
            StringBuilder content = new StringBuilder();
            try {
                URL uri = new URL(str);
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(uri.openConnection().getInputStream()));
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    content.append(line);
                }
                bufferedReader.close();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return content.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            result = s;
            ReadObjectJSonArray();
        }
    }
}
