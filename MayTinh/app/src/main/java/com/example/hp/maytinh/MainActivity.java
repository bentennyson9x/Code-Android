package com.example.hp.maytinh;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText TxtSoA = (EditText) findViewById(R.id.sothunhat);
        final EditText TxtSoB = (EditText) findViewById(R.id.sothuhai);
        final Button btnCong = (Button) findViewById(R.id.btncong);
        final Button btnTru = (Button) findViewById(R.id.btntru);
        final Button btnNhan = (Button) findViewById(R.id.btnnhan);
        final Button btnChia = (Button) findViewById(R.id.btnchia);
        final RelativeLayout ManHinh = (RelativeLayout) findViewById(R.id.Manhinh);
        final ImageButton option2 = (ImageButton) findViewById(R.id.optionbnt);



        option2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,Option2Activity.class);
                startActivity(intent);
            }
        });
        btnCong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double a = Double.parseDouble(TxtSoA.getText().toString());
                double b = Double.parseDouble(TxtSoB.getText().toString());
                TextView Lblkq = (TextView) findViewById(R.id.Lbketqua);
                Lblkq.setText("" + (a + b));
            }
        });
        btnTru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double a = Double.parseDouble(TxtSoA.getText().toString());
                double b = Double.parseDouble(TxtSoB.getText().toString());
                TextView Kq = (TextView) findViewById(R.id.Lbketqua);
                Kq.setText(""+(a+b));
            }
        });
        btnNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double a = Double.parseDouble(TxtSoA.getText().toString());
                double b = Double.parseDouble(TxtSoB.getText().toString());
                TextView kq = (TextView) findViewById(R.id.Lbketqua);
                kq.setText(""+ (a+b));
            }
        });
        btnChia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double a = Double.parseDouble(TxtSoA.getText().toString());
                double b = Double.parseDouble(TxtSoB.getText().toString());
                TextView kq = (TextView) findViewById(R.id.Lbketqua);
                if ( b ==0 ){
                    kq.setText("Không có đáp án vì số B bằng 0\n Vui Lòng nhập B khác 0");
                }else
                {
                    kq.setText(""+ (a/b));
                }
            }
        });
    }
}
