package com.example.minhvufc.bkapdemonewview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView mRv;
    ArrayList<Food> mLstFood = new ArrayList<>();

    private void fakeData() {
        mLstFood.add(new Food("Bánh mỳ thịt", R.drawable.img_banhmythit));
        mLstFood.add(new Food("Hoa quả", R.drawable.img_hoaqua));
        mLstFood.add(new Food("Ngô Nướng", R.drawable.img_ngonuong));
        mLstFood.add(new Food("Cá hồi Chile", R.drawable.img_cahoi_chile));
        mLstFood.add(new Food("Sandwitch", R.drawable.img_sandwitch));
        mLstFood.add(new Food("Hoa quả", R.drawable.img_hoaqua));
        mLstFood.add(new Food("Ngô Nướng", R.drawable.img_ngonuong));
        mLstFood.add(new Food("Bánh mỳ thịt", R.drawable.img_banhmythit));
        mLstFood.add(new Food("Cá hồi Chile", R.drawable.img_cahoi_chile));
        mLstFood.add(new Food("Hoa quả", R.drawable.img_hoaqua));
        mLstFood.add(new Food("Ngô Nướng", R.drawable.img_ngonuong));
        mLstFood.add(new Food("Sandwitch", R.drawable.img_sandwitch));
        mLstFood.add(new Food("Hoa quả", R.drawable.img_hoaqua));
        mLstFood.add(new Food("Ngô Nướng", R.drawable.img_ngonuong));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Dữ liệu ảo
        fakeData();

        // Gọi RecycleView và đổ dữ liệu
        mRv = (RecyclerView) findViewById(R.id.rvFood);

        // tăng hiệu năng nếu thay đổi nội dung KHÔNG thay đổi kích thước layout
//        mRv.setHasFixedSize(true);

        // Cài đặt hiển thị kiểu Linear
        mRv.setLayoutManager(new LinearLayoutManager(this));

        MyAdapter adapter = new MyAdapter(mLstFood, this);
        mRv.setAdapter(adapter);
    }

    public void checkLinear(View view) {
        mRv.setLayoutManager(new LinearLayoutManager(this));
    }

    public void checkGrid(View view) {
        // Grid 2 cột
        mRv.setLayoutManager(new GridLayoutManager(this, 2));
    }

    public void checkStagered(View view) {
        // Stagered 2 cột và điều chỉnh chiều dọc
        StaggeredGridLayoutManager sglm = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mRv.setLayoutManager(sglm);
    }
}
