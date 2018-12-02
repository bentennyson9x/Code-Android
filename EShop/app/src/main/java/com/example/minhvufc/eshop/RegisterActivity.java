package com.example.minhvufc.eshop;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.minhvufc.eshop.entity.ResultEntity;

import org.json.JSONException;

public class RegisterActivity extends AppCompatActivity {

    ProgressDialog mPrD;
    Runnable taskReg = new Runnable() {
        @Override
        public void run() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mPrD.show(); // Bật dialog progress
                }
            });

            String username = getDataForm(R.id.txtUsername);
            String password = getDataForm(R.id.txtPassword);
            String fullname = getDataForm(R.id.txtFullname);
            int gender = 0;
            if (R.id.radMale == getRadioChecked(R.id.radGGender)) {
                gender = 1;
            }
            String email = getDataForm(R.id.txtEmail);

            String data[][] = {
                    {"username", username},
                    {"password", password},
                    {"fullname", fullname},
                    {"gender", String.valueOf(gender)},
                    {"email", email}
            };

            BkapServer server = new BkapServer();
            final String result = server.sendRequest(1,
                    BkapConstant.HOSTING_API + BkapConstant.PAGE_INSERT, data);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mPrD.dismiss(); // Tắt dialog progress
                    ResultEntity resultEntity = null;
                    try {
                        resultEntity = new ResultEntity(result);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    if(resultEntity == null){
                        Log.e("MinhVT", "NULL");
                    }
                    Toast.makeText(RegisterActivity.this, "Kết quả: " +
                            resultEntity.getMessage(), Toast.LENGTH_SHORT).show();
                    finish();
                }
            });
        }
    };

    private String getDataForm(int resource) {
        EditText text = (EditText) findViewById(resource);
        return text.getText().toString();
    }

    private int getRadioChecked(int resource) {
        return ((RadioGroup) findViewById(resource)).getCheckedRadioButtonId();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Khởi tạo dialog progress
        mPrD = new ProgressDialog(RegisterActivity.this);
        mPrD.setMessage("Vui lòng đợi trong chốc lát");
        mPrD.setCancelable(false);
    }

    public void actionRegister(View view) {
        Thread th = new Thread(taskReg);
        th.start();
    }
}
