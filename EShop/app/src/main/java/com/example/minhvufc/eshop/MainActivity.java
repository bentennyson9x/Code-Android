package com.example.minhvufc.eshop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.widget.TextView;

import com.example.minhvufc.eshop.entity.ProductEntityManager;

import org.json.JSONException;

public class MainActivity extends AppCompatActivity {

    RecyclerView mRv;
    AdapterProduct mAdapter;
    Runnable mTaskLoadProduct = new Runnable() {
        @Override
        public void run() {
            BkapServer server = new BkapServer();
            String result = server.sendRequest(1, BkapConstant.HOSTING_API + BkapConstant.PAGE_PRODUCT, new String[][]{});
            Log.e("MinhVT", result);
            try {
                ProductEntityManager manager = new ProductEntityManager(result);
                mAdapter = new AdapterProduct(manager.lstUser, getApplicationContext());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mRv.setAdapter(mAdapter);
                    }
                });
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRv = (RecyclerView) findViewById(R.id.recyclerview);
        mRv.setHasFixedSize(true);

        // Lưới 2 cột
        GridLayoutManager glm = new GridLayoutManager(MainActivity.this, 2);
        mRv.setLayoutManager(glm);

//        // Stagered 2 cột và điều chỉnh chiều dọc
//        StaggeredGridLayoutManager sglm =
//                new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
//        mRv.setLayoutManager(sglm);


        // Tải dữ liệu ProductEntity
        Thread th = new Thread(mTaskLoadProduct);
        th.start();

//        Intent intent = getIntent();
//        ((TextView) findViewById(R.id.lblFullname)).setText(intent.getStringExtra("fullname"));
//        ((TextView) findViewById(R.id.lblEmail)).setText(intent.getStringExtra("email"));
//        ((TextView) findViewById(R.id.lblGender)).setText(intent.getStringExtra("gender"));
    }


}
