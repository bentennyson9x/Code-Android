package com.example.minhvufc.demodraganddrop;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

public class DatePickerActivity extends AppCompatActivity {
    EditText txtDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_picker);
        txtDate = (EditText) findViewById(R.id.editText);
    }

    public void openPicker(View view) {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                txtDate.setText(i + "-" + i1 + "-" + i2);
            }
        };

        DatePickerDialog dialog = new DatePickerDialog(this, dateSetListener, 2018,1,15);
        dialog.show();
    }
}
