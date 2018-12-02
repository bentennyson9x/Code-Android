package com.example.saint.jsonobject;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ReadJson readJson = new ReadJson();
        readJson.execute("https://khoapham.vn/KhoaPhamTraining/json/tien/demo1.json");

    }
    private class ReadJson extends AsyncTask <String,Void,String>{

        public ReadJson() {

        }

        @Override
        protected String doInBackground(String... strings) {
            String str = strings[0];
            URL url = null;
            StringBuilder content=new StringBuilder();
            try {
                url = new URL(str);
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openConnection().getInputStream()));
                String line="";
                while ((line = bufferedReader.readLine())!=null){
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
            String result = s;
            try {
                JSONObject object = new JSONObject(result);
                String monhoc = object.getString("monhoc");
                String noihoc = object.getString("noihoc");
                String website = object.getString("website");
                Toast.makeText(MainActivity.this, monhoc+"\n"+noihoc+"\n"+website, Toast.LENGTH_SHORT).show();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
