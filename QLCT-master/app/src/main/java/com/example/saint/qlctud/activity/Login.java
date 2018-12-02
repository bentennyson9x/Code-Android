package com.example.saint.qlctud.activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.saint.qlctud.DataSingleton;
import com.example.saint.qlctud.R;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity implements View.OnClickListener {
    EditText User,Password;
    ImageView IconUser,IconPassword;
    Button SignIn,btnSignUp,ForgotPassWord;
    CheckBox Rememberme;
    Animation rotate;
    private void AddButton(){
        User = findViewById(R.id.User_input);
        Password = findViewById(R.id.Password_input);
        IconUser = findViewById(R.id.iconUser);
        IconPassword = findViewById(R.id.iconPassword);
        SignIn = findViewById(R.id.SignIn_button);
        Rememberme = findViewById(R.id.RememberMe);
        btnSignUp = findViewById(R.id.btnSignUpAccount);
    }
    private void Animation(){
        rotate = AnimationUtils.loadAnimation(this, R.anim.rolate_anim);
    }
    @Override
    protected void onResume() {
        LoadPreference();
        super.onResume();
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
    protected void Click(){
        SignIn.setOnClickListener(this);
        btnSignUp.setOnClickListener(this);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        DataSingleton.putData("IP","192.168.5.104");
        AddButton();
        Animation();
        Click();
        Touch();

    }
    private void SavePreference() {
        SharedPreferences sharedPreferences = getSharedPreferences("SaveData",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("User",User.getText().toString());
        editor.putString("PassWord",Password.getText().toString());
        editor.putBoolean("RememberMe",Rememberme.isChecked());
        editor.commit();
    }
    private void LoadPreference() {
        SharedPreferences sharedPreferences = getSharedPreferences("SaveData",MODE_PRIVATE);
        User.setText(sharedPreferences.getString("User",""));
        Password.setText(sharedPreferences.getString("PassWord",""));
        if (sharedPreferences.getBoolean("RememberMe",false)==true){
            Rememberme.toggle();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(Rememberme.isChecked()==true) {
            SavePreference();
        }
    }

    @Override
    public void onClick(View view) {
        if(view == SignIn){
            String url = "http://"+getString(R.string.IP)+"/QLCT/Login.php";
            Log.e("xem",url);
            CheckAccount(url);
        }
        else if (view == btnSignUp){
            Intent intent = new Intent(Login.this,SignUp.class);
            startActivity(intent);
        }
    }

    private void SignInError() {
        final ProgressDialog dialog = new ProgressDialog(Login.this);
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

    private void SignIn() {
        final ProgressDialog dialog = new ProgressDialog(Login.this);
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
                Intent intent = new Intent(Login.this,MainActivity.class);
                startActivity(intent);
                finish();
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

    private boolean CheckAccount(String url) {
        boolean result = false;
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.trim().equals("Wrong user or password")==false) {
                    DataSingleton.putData("UserID",response.trim());
                    SignIn();
                }
                else {
                    SignInError();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("xem",error.toString());
                Toast.makeText(Login.this, "You may have been lost connection. Please check your internet and try again ! ", Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("idClient",User.getText().toString().trim());
                params.put("password",Password.getText().toString().trim());
                return params;
            }
        };
        requestQueue.add(request);
        return result;
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

}
