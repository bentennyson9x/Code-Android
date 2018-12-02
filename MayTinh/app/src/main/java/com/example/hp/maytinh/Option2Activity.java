package com.example.hp.maytinh;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class Option2Activity extends AppCompatActivity {


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_function);
        final EditText txtSoA = (EditText) findViewById(R.id.SoA);
        final EditText txtSoB = (EditText) findViewById(R.id.SoB);
        final EditText txtSoC = (EditText) findViewById(R.id.SoC);
        final TextView Lbkq = (TextView) findViewById(R.id.LbKetquapt2);
        final Button Tinh = (Button) findViewById(R.id.Tinh);
        Tinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double delta;
                double a = Double.parseDouble(txtSoA.getText().toString());
                double b = Double.parseDouble(txtSoB.getText().toString());
                double c = Double.parseDouble(txtSoC.getText().toString());
                delta = ( pow(b,2) - 4 * a * c);
                if (delta < 0) {
                    Lbkq.setText("Phương trình vô nghiệm");
                } else if (delta == 0) {
                    Lbkq.setText("Phương trình có 2 nghiệm kép x1 = x2 = " + (-b / 2 * a));
                } else {
                     double x1 = (-b+sqrt(delta))/2*a;
                     double x2 =  (-b-sqrt(delta))/2*a;
                     Lbkq.setText("Phương trình có 2 nghiệm phân biệt\n x1 = "+x1+"\n x2 = "+x2 );
                }
            }
        });
    }
}
