package com.example.quanlynhansu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnDienThongTinNS,btnSXepNS,btnTuDongPhanTichNS,btnChonLichVaHenNS,btnPhanBieuLichTrinhGap,btnThoatCT;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addView();
    }

    private void addView() {
    btnDienThongTinNS.findViewById(R.id.btnDienThongTinNhanSu);
    btnSXepNS.findViewById(R.id.btnSapXepNhanSuTheoTieuChi);
    btnTuDongPhanTichNS.findViewById(R.id.btnTuDongPhanTichNhanSu);
    btnChonLichVaHenNS.findViewById(R.id.btnChonLichVaDiaDiemGapNhanSu);
    btnPhanBieuLichTrinhGap.findViewById(R.id.btnPhanBieuLichGap);
    btnThoatCT.findViewById(R.id.btnThoatChuongTrinh);
    }

    @Override
    public void onClick(View v) {

    }
}
