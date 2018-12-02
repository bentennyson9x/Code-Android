package com.example.minhvufc.eshop;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.minhvufc.eshop.entity.UserEntityManager;

import org.json.JSONException;


public class LoginActivity extends AppCompatActivity {

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

            String data[][] = {
                    {"username", username},
                    {"password", password}
            };

            BkapServer server = new BkapServer();
            final String result = server.sendRequest(1,
                    BkapConstant.HOSTING_API + BkapConstant.PAGE_INDEX, data);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mPrD.dismiss(); // Tắt dialog progress
                    try {
                        UserEntityManager manager = new UserEntityManager(result);
                        // Nếu đăng nhập sai
                        if (manager.lstUser.size() == 0) {
                            Toast.makeText(LoginActivity.this, "Không tồn tại tài khoản hoặc sai", Toast.LENGTH_SHORT).show();
                        } else {
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            intent.putExtra("fullname", manager.lstUser.get(0).getFullname());
                            intent.putExtra("email", manager.lstUser.get(0).getEmail());
                            intent.putExtra("gender", manager.lstUser.get(0).getGender() == 1 ? "Nam" : "Nữ");
                            // Gửi dữ liệu sang MainActivity
                            startActivity(intent);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(LoginActivity.this, "Dữ liệu lỗi", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    };

    private String getDataForm(int resource) {
        EditText text = (EditText) findViewById(resource);
        return text.getText().toString();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Khởi tạo dialog progress
        mPrD = new ProgressDialog(LoginActivity.this);
        mPrD.setMessage("Vui lòng đợi trong chốc lát");
        mPrD.setCancelable(false);
    }

    public void actionLogin(View view) {
        Thread th = new Thread(taskReg);
        th.start();
    }

    public void openRegister(View view) {
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);
    }
}
