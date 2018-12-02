package com.example.saint.quan_ly_chi_tieu;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;

import com.example.saint.quan_ly_chi_tieu.Account_Money.fragment_wallet;
import com.example.saint.quan_ly_chi_tieu.ListPayAmount.pay_amount;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class Home extends AppCompatActivity implements View.OnClickListener,Interface_community_of_Fragment {
   public ImageButton Home,Wallet,Plus,Finance,Setting;
    ArrayList<pay_amount> ListPayAmount;
    private void AddButton(){
        Home = findViewById(R.id.Home_icon);
        Wallet = findViewById(R.id.wallet);
        Plus = findViewById(R.id.Plus);
        Finance = findViewById(R.id.finance);
        Setting = findViewById(R.id.setting);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        AddButton();
        Home.performClick();// tu dong click vao khi create activity
    }

    public void addFragment(View view){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        Fragment fragment= null;
        String tag =null;
        if ( view == Home ){
            fragment = new fragment_home();
            tag = "fragment_home";
            if (ListPayAmount !=null){
                Bundle bundle = new Bundle();
                bundle.putSerializable("ListPayAmount",ListPayAmount);
                Log.e("xem","truyen home");
                fragment.setArguments(bundle);
            }
        }
        if (view == Wallet){
            fragment = new fragment_wallet();
            tag = "fragment_wallet";
        }
        if (view == Plus){
            fragment = new fragment_plus();
            tag = "fragment_plus";
            if (ListPayAmount !=null){
                Bundle bundle = new Bundle();
                bundle.putSerializable("ListPayAmount",ListPayAmount);
                Log.e("xem bundle",String.valueOf(bundle.getSerializable("ListPayAmount")) );
                Log.e("xem","truyen plus");
                fragment.setArguments(bundle);
                Log.e("xem1","argument bool"+String.valueOf(fragment.getArguments()));
            }
        }
        if (view == Finance){
            fragment = new fragment_finance();
            tag = "fragment_finance";
        }
        if (view == Setting){
            fragment = new fragment_setting();
            tag = "fragment_setting";
        }
        fragmentTransaction.replace(R.id.DisplayFragment,fragment,tag);
        fragmentTransaction.commit();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void Community(String string, Serializable object) {
       ListPayAmount = (ArrayList<pay_amount>) object;
       Log.e("xemsize","tu activi "+ListPayAmount.size());
    }
    private void savePreferences(){
        SharedPreferences sharedPreferences = getSharedPreferences("SaveData_fragmentPay", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(ListPayAmount);
        editor.putString("ListPayAmount", json);
        editor.commit();
    }
    private void loadPreferences() {
        SharedPreferences sharedPreferences =getSharedPreferences("SaveData_fragmentPay",Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("ListPayAmount",null);
        Type type = new TypeToken<ArrayList<pay_amount>>(){}.getType();
        ListPayAmount = gson.fromJson(json,type);
        if (ListPayAmount== null){
            ListPayAmount= new ArrayList<>();
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        savePreferences();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadPreferences();
    }
}
