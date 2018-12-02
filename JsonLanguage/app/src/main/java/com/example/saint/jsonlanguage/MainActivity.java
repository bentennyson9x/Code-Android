package com.example.saint.jsonlanguage;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ImageButton en, vn;
    TextView view;
    String result = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AddButton();
        ReadJsonEx();
        Click();
    }

    private void ReadJsonEx() {
    ReadJson readJson = new ReadJson();
    readJson.execute("https://khoapham.vn/KhoaPhamTraining/json/tien/demo3.json");
    }

    private void Click() {
        en.setOnClickListener(this);
        vn.setOnClickListener(this);
    }

    private void ReadJsonLanguage(String choiceLanguage) {
        JSONObject object = null;
        try {
            object = new JSONObject(result);
            JSONObject objLanguage = object.getJSONObject("language");
            JSONObject objLanguageEn = objLanguage.getJSONObject(choiceLanguage);
            String name = objLanguageEn.getString("name");
            String address = objLanguageEn.getString("address");
            String course1 = objLanguageEn.getString("course1");
            String course2 = objLanguageEn.getString("course2");
            String course3 = objLanguageEn.getString("course3");
            view.setText(name+"\n"+address+"\n"+course1+"\n"+course2+"\n"+course3+"\n");
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    private void AddButton() {
        en = findViewById(R.id.btnEn);
        vn = findViewById(R.id.btnVn);
        view = findViewById(R.id.viewTemp);
    }

    @Override
    public void onClick(View view) {
        if (view == en){
            ReadJsonLanguage("en");
        }
        if (view == vn ){
            ReadJsonLanguage("vn");
        }

    }

    private class ReadJson extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            String str = strings[0];
            StringBuilder content = new StringBuilder();
            try {
                URL url = new URL(str);
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openConnection().getInputStream()));
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    content.append(line);
                }
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
        }
    }
}
