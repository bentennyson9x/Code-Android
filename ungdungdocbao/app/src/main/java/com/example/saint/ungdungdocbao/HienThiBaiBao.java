package com.example.saint.ungdungdocbao;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class HienThiBaiBao extends AppCompatActivity {
    WebView viewBaiBao;
    String link;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hien_thi_bai_bao);
        addButton();
        getDataFromMainActivity();
        ofViewBaiBao();
    }

    private void ofViewBaiBao() {
        viewBaiBao.loadUrl(link);
        viewBaiBao.setWebViewClient(new WebViewClient()); // chi xem web o trong app cua lap trinh vien
    }

    private void getDataFromMainActivity() {
     link = DataManagement.getData("linkTinTuc");
    }

    private void addButton() {
    viewBaiBao = findViewById(R.id.viewBaiBao);
    }
}
