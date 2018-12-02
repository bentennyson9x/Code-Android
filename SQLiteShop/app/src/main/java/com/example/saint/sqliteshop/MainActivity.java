package com.example.saint.sqliteshop;

import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadData();
    }
    private void loadData (){
        Product_Manager manager = new  Product_Manager(MainActivity.this);
        ArrayList <Product> list  = manager.read();
        String arrProduct []=  new String[list.size()];
        // các em cần viết chi tiết adapter để đổ dữ liệu product
        for (int i=0;i<arrProduct.length;i++){
            arrProduct[i]= list.get(i).name;
        }
        ListView listView = (ListView ) findViewById(R.id.listview);
        ArrayAdapter< String > adapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1,arrProduct);
        listView.setAdapter(adapter);
    }
}
