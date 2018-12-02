package com.example.minhvufc.demodraganddrop;

import android.app.DatePickerDialog;
import android.content.ClipData;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Lấy ảnh trên layout
        ImageView imageInMain = (ImageView) findViewById(R.id.carInMain);
        LinearLayout lilMain = (LinearLayout) findViewById(R.id.lilMain);

        // Cài đặt sự kiện
        imageInMain.setOnLongClickListener(olclForCar);
        lilMain.setOnDragListener(odlForCarInMain);

        // Demo 2: kéo thả ảnh ô tô sang 2 hộp khác nhau
        ImageView imageInPart = (ImageView) findViewById(R.id.carInPart);
        LinearLayout lilPart1 = (LinearLayout) findViewById(R.id.lilPart1);
        LinearLayout lilPart2 = (LinearLayout) findViewById(R.id.lilPart2);

        // Cài đặt sự kiện
        imageInPart.setOnLongClickListener(olclForCar);
        lilPart1.setOnDragListener(odlForCarInPart);
        lilPart2.setOnDragListener(odlForCarInPart);
    }

    View.OnLongClickListener olclForCar = new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View view) {
            // Truyền id của ảnh theo sự kiện kéo sử dụng ClipData
            ClipData cd = ClipData.newPlainText("id", String.valueOf(view.getId()));

            // Phát sinh sự kiện kéo thả
            View.DragShadowBuilder dsb = new View.DragShadowBuilder(view);
            view.startDrag(cd, dsb, view, 0);
            view.setVisibility(View.INVISIBLE);
            return true;
        }
    };

    View.OnDragListener odlForCarInMain = new View.OnDragListener() {
        @Override
        public boolean onDrag(View view, DragEvent dragEvent) {
            int action = dragEvent.getAction();
            // Lấy ảnh đang kéo
            View myImage = (View) dragEvent.getLocalState();
            switch (action) {
                case DragEvent.ACTION_DRAG_STARTED:
                    Log.e("MinhVT", "Bắt đầu kéo");
                    break;
                case DragEvent.ACTION_DRAG_ENDED:
                    Log.e("MinhVT", "Kết thúc kéo");
                    break;
                case DragEvent.ACTION_DROP:
                    Log.e("MinhVT", "Thả");

                    ClipData cd = dragEvent.getClipData();
                    Log.e("ClipData", cd.getItemAt(0).getText().toString());

                    // Lấy tọa độ vị trí MỚI
                    float cord_x = dragEvent.getX();
                    float cord_y = dragEvent.getY();

                    // Đặt vị trí mới cho ảnh
                    myImage.setX(cord_x - (myImage.getWidth() / 2));
                    myImage.setY(cord_y - (myImage.getHeight() / 2));

                    // Hiển thị lại ảnh
                    myImage.setVisibility(View.VISIBLE);
                    break;
            }
            return true;
        }
    };

    View.OnDragListener odlForCarInPart = new View.OnDragListener() {
        @Override
        public boolean onDrag(View view, DragEvent dragEvent) {
            int action = dragEvent.getAction();
            // Lấy ảnh đang kéo
            View myImage = (View) dragEvent.getLocalState();
            // Lấy Layout đang chứa ảnh
            ViewGroup vg = (ViewGroup) myImage.getParent();

            switch (action) {
                case DragEvent.ACTION_DRAG_STARTED:
                    Log.e("MinhVT", "Bắt đầu kéo");
                    break;
                case DragEvent.ACTION_DRAG_ENDED:
                    Log.e("MinhVT", "Kết thúc kéo");
                    break;
                case DragEvent.ACTION_DRAG_ENTERED:
                    Log.e("MinhVT", "COME IN");
                    view.setBackgroundResource(R.drawable.bg_violet);
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    Log.e("MinhVT", "GET OUT");
                    view.setBackgroundResource(android.R.color.transparent);
                    break;
                case DragEvent.ACTION_DROP:
                    Log.e("MinhVT", "Thả");
                    ClipData cd = dragEvent.getClipData();
                    Toast.makeText(MainActivity.this,cd.getItemAt(0).getText().toString(),Toast.LENGTH_SHORT ).show();
                    int idImage = Integer.parseInt(cd.getItemAt(0).getText().toString());
                    Log.e("ClipData", "#" + idImage);
                    // Check
                    int idCarInPart = R.id.carInPart;
                    switch (idImage) {
                        case R.id.carInPart:
                            Toast.makeText(MainActivity.this, "OK", Toast.LENGTH_SHORT).show();
                            break;
                        default:
                            Toast.makeText(MainActivity.this, "NO", Toast.LENGTH_SHORT).show();
                            break;
                    }

                    // Ở layout GỐC thì xóa ảnh đi
                    vg.removeView(myImage);

                    // Ở layout MỚI thì thêm ảnh vào
                    ((LinearLayout) view).addView(myImage);

                    // Hiển thị lại ảnh
                    myImage.setVisibility(View.VISIBLE);
                    break;
            }
            return true;
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_datepicker:
                Intent intent = new Intent(MainActivity.this, DatePickerActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
