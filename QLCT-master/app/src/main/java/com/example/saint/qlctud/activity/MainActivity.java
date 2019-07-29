package com.example.saint.qlctud.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.saint.qlctud.R;
import com.example.saint.qlctud.fragment.FragmentFinance;
import com.example.saint.qlctud.fragment.FragmentHome;
import com.example.saint.qlctud.fragment.FragmentPay;
import com.example.saint.qlctud.fragment.FragmentPlus;
import com.example.saint.qlctud.fragment.FragmentSetting;
import com.example.saint.qlctud.fragment.FragmentWallet;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    Toolbar toolbar;
    TextView txtToolbarTitle;
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AddView();
    }

    private void AddView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        txtToolbarTitle = findViewById(R.id.toolbar_title);
        toolbar.setBackgroundColor(Color.parseColor("#e75454"));
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        setTitle("");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.nav_share) {
            txtToolbarTitle.setText("Share");
        } else if (id == R.id.nav_send) {
            txtToolbarTitle.setText("Send");
        }
        else if (id == R.id.nav_logout){
            Intent intent = new Intent(MainActivity.this,Login.class);
            startActivity(intent);
        }else {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
            Fragment fragment= null;
            String tag =null;
            if (id == R.id.nav_home) {
                txtToolbarTitle.setText("Home");
                fragment = new FragmentHome();
                tag = "fragment_home";// để tìm fragment by tag sử dụng khi một fragment muốn gọi 1 fragment khác từ trong các view của nó
            } else if (id == R.id.nav_wallet) {
                txtToolbarTitle.setText("Wallet");
                fragment = new FragmentWallet();
                tag = "fragment_wallet";
            } else if (id == R.id.nav_plus) {
                txtToolbarTitle.setText("Plus");
                fragment = new FragmentPlus();
                tag = "fragment_plus";
            } else if (id == R.id.nav_setting) {
                txtToolbarTitle.setText("Setting");
                fragment = new FragmentSetting();
                tag = "fragment_setting";
            } else if (id == R.id.nav_finance){
                txtToolbarTitle.setText("Finance");
                fragment = new FragmentFinance();
                tag = "fragment_finance";
            } else if (id == R.id.nav_pay){
                txtToolbarTitle.setText("Pay");
                fragment = new FragmentPay();
                tag = "fragment_pay";
            }
            fragmentTransaction.replace(R.id.content_main,fragment,tag);
            fragmentTransaction.commit();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
