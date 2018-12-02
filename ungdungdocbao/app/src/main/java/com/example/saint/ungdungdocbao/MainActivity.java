package com.example.saint.ungdungdocbao;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listBaiBao;
    ArrayList <String> listTieuDe,listLink;
    ArrayAdapter adapterListBaiBao;
    ReadRSS readRSS ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // can cap quyen internet
        addButton();
        AsyntaskRSS();
        setListBaiBao();

    }

    private void AsyntaskRSS() {
        readRSS = new ReadRSS(MainActivity.this,listTieuDe,listLink,adapterListBaiBao);
        readRSS.execute("https://vnexpress.net/rss/du-lich.rss");
    }

    private void setListBaiBao() {
        listBaiBao.setAdapter(adapterListBaiBao);
        listBaiBao.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this,HienThiBaiBao.class);
                DataManagement.putData("linkTinTuc",listLink.get(i));
                startActivity(intent);
            }
        });
    }

    private void addButton() {
        listBaiBao= findViewById(R.id.listBaiBao);
        listTieuDe = new ArrayList();
        listLink = new ArrayList();
        adapterListBaiBao
                = new ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1,listTieuDe);
    }

}
