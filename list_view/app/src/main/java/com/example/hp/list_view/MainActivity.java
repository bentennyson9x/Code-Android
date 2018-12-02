package com.example.hp.list_view;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    String arrFruit[] = {
            "Bưởi", "Cam","Quýt","Dừa","Bòng",
            "Chuối","Dâu","Mướp","Bơ","Dưa",
            "Óc chó","Xoài","Bí","Cau","Chanh",
    };
    ArrayList<weather> weatherArrayList= new ArrayList<weather>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ListView listView = findViewById(R.id.ListView);
//        ArrayAdapter<String>adapter= new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item,arrFruit);
//        listView.setAdapter(adapter);
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Toast.makeText(MainActivity.this, "Bạn đã chọn "+ arrFruit[i],Toast.LENGTH_SHORT).show();
//            }
//        });
        weatherArrayList.add(new weather(R.drawable.may_nang,"London","Nắng","20°C"));
        weatherArrayList.add(new weather(R.drawable.may_den,"Hà Nội","Mây đen","28°C"));
        weatherArrayList.add(new weather(R.drawable.mua_bao,"Mỹ","Mưa bão","15°C"));
        weatherArrayList.add(new weather(R.drawable.mua_tuyet,"Nhật","Tuyết","-3°C"));
        weatherArrayList.add(new weather(R.drawable.may_nang,"London","Nắng","20°C"));
        weatherArrayList.add(new weather(R.drawable.may_den,"Hà Nội","Mây đen","28°C"));
        weatherArrayList.add(new weather(R.drawable.mua_bao,"Mỹ","Mưa bão","15°C"));
        weatherArrayList.add(new weather(R.drawable.mua_tuyet,"Nhật","Tuyết","-3°C"));
        weatherArrayList.add(new weather(R.drawable.may_nang,"London","Nắng","20°C"));
        weatherArrayList.add(new weather(R.drawable.may_den,"Hà Nội","Mây đen","28°C"));
        weatherArrayList.add(new weather(R.drawable.mua_bao,"Mỹ","Mưa bão","15°C"));
        weatherArrayList.add(new weather(R.drawable.mua_tuyet,"Nhật","Tuyết","-3°C"));
        weatherArrayList.add(new weather(R.drawable.may_nang,"London","Nắng","20°C"));
        weatherArrayList.add(new weather(R.drawable.may_den,"Hà Nội","Mây đen","28°C"));
        weatherArrayList.add(new weather(R.drawable.mua_bao,"Mỹ","Mưa bão","15°C"));
        weatherArrayList.add(new weather(R.drawable.mua_tuyet,"Nhật","Tuyết","-3°C"));
        ArrayAdapter adapter = new AdapterWeathers(this, R.layout.item_weather,weatherArrayList);
        listView.setAdapter(adapter);
        registerForContextMenu(listView);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo)  {
        getMenuInflater().inflate(R.menu.menu,menu);
        AdapterView.AdapterContextMenuInfo info=  (AdapterView.AdapterContextMenuInfo) menuInfo;
        weather obj = weatherArrayList.get(info.position) ;
        String title = "Mời chọn thành phố cho " +obj.city;
        ActionBar actionBar= getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#00ff00")));
        menu.setHeaderTitle(title);
        super.onCreateContextMenu(menu, v, menuInfo);

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        int id = item.getItemId();
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        weather obj = weatherArrayList.get(info.position);
        String city = obj.city;
                switch (id){
                    case R.id.chitiet :
                        Toast.makeText(this, "Chi tiết "+city ,Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.huongdan :
                        Toast.makeText(this,"Hướng dẫn "+city,Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.caidat:
                        Toast.makeText(this,"Cài đặt "+city,Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.lienhe:
                        Toast.makeText(this, "Liên Hệ "+city,Toast.LENGTH_SHORT).show();
                        break;
                }
        return super.onContextItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        menu.add("Tùy chọn thêm");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
                switch (id) {
                    case R.id.caidatAI :
                        Toast.makeText(this, "Vào cài đặt AI ",Toast.LENGTH_SHORT).show();
                        break;
                        case R.id.caidatamthanh:
                        Toast.makeText(this, "Vào cài đặt Âm thanh ",Toast.LENGTH_SHORT).show();
                        break;
                        case R.id.caidathinhanh:
                        Toast.makeText(this, "Vào cài đặt Hình ảnh ",Toast.LENGTH_SHORT).show();
                        break;
                        case R.id.caidatSFX:
                        Toast.makeText(this, "Vào cài đặt SFX ",Toast.LENGTH_SHORT).show();
                        break;

                }
        return super.onOptionsItemSelected(item);
    }
}
