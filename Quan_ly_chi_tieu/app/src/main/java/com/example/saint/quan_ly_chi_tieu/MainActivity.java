package com.example.saint.quan_ly_chi_tieu;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.saint.quan_ly_chi_tieu.Account_User.Account;
import com.example.saint.quan_ly_chi_tieu.Account_User.Account_manager;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    EditText User,Password;
    ImageView IconUser,IconPassword;
    Button SignIn,SignUp,ForgotPassWord;
    CheckBox Rememberme;
    Animation rotate;
    ArrayList <Account> listAccount;
    private void Animation(){
       rotate = AnimationUtils.loadAnimation(this, R.anim.rolate_anim);
    }
    private void AddButton(){
        User = findViewById(R.id.User_input);
        Password = findViewById(R.id.Password_input);
        IconUser = findViewById(R.id.iconUser);
        IconPassword = findViewById(R.id.iconPassword);
        SignIn = findViewById(R.id.SignIn_button);
        Rememberme = findViewById(R.id.RememberMe);
        listAccount = new ArrayList<>();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AddButton();
        Animation();
        Click();
        Touch();
        CheckAccount();
    }

    private boolean CheckAccount() {
        boolean result = false;
        Account_manager account_manager = new Account_manager(MainActivity.this);
        listAccount = account_manager.read();
        for (int i=0;i<listAccount.size();i++){
            if (User.getText().toString().equals(listAccount.get(i).getUser().toString())
                    &&Password.getText().toString().equals(listAccount.get(i).getPassword().toString())){
                result = true;
            }
        }
        return result;
    }

    private void Touch() {
        User.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                IconUser.startAnimation(rotate);
                return false;
            }
        });
        Password.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                IconPassword.startAnimation(rotate);
                return false;
            }
        });
    }

    @Override
    protected void onResume() {
        LoadPreference();
        super.onResume();
    }

    private void LoadPreference() {
        SharedPreferences sharedPreferences = getSharedPreferences("SaveData",MODE_PRIVATE);
        User.setText(sharedPreferences.getString("User",""));
        Password.setText(sharedPreferences.getString("PassWord",""));
        Rememberme.toggle();

    }

    protected void Click(){
        SignIn.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        if(view == SignIn){
            if (CheckAccount()== true){
                if(Rememberme.isChecked()==true){
                    SavePreference();
                }
                final ProgressDialog dialog = new ProgressDialog(MainActivity.this);
                final Handler handler = new Handler();
                dialog.setTitle("Sign In");
                dialog.setMessage("Loading...Please Wait ! ");
                dialog.setCancelable(false);
                dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                dialog.show();
                final Thread ProgressDialog = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                dialog.dismiss();
                            }
                        },2000);
                    }
                });
                Thread startActivity = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        Intent intent = new Intent(MainActivity.this,Home.class);
                        startActivity(intent);
                        //finish();
                    }
                });

                ProgressDialog.start();
                try {
                    ProgressDialog.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                startActivity.start();
            }
            else {
                final ProgressDialog dialog = new ProgressDialog(MainActivity.this);
                final Handler handler = new Handler();
                dialog.setTitle("Sign In");
                dialog.setMessage("Loading...Please Wait ! ");
                dialog.setCancelable(false);
                dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                dialog.show();
                final Thread ProgressDialog = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                dialog.dismiss();
                            }
                        },1000);
                    }
                });
                Thread HienThongBao = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1200);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                Thong_bao_dang_nhap_sai();
                            }
                        });
                    }
                });
              ProgressDialog.start();
                try {
                    ProgressDialog.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                HienThongBao.start();
            }
        }

    }

    private void Thong_bao_dang_nhap_sai() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Notice: ");
        builder.setIcon(R.drawable.warning);
        builder.setMessage("Wrong User or Password. Please Try Again ! ");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.show();
    }

    private void SavePreference() {
        SharedPreferences sharedPreferences = getSharedPreferences("SaveData",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("User",User.getText().toString());
        editor.putString("PassWord",Password.getText().toString());
        editor.putBoolean("RememberMe",Rememberme.isChecked());
        editor.commit();
    }
}
