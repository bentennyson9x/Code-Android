package com.example.minhvufc.eshop;

import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.minhvufc.eshop.entity.ProductEntity;

import java.util.Locale;

public class ProductActivity extends AppCompatActivity {

    ImageView image;
    TextView name;
    TextView desc;
    TextView priceOld;
    TextView priceNew;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        this.image = findViewById(R.id.item_image);
        this.name = findViewById(R.id.item_name);
        this.desc = findViewById(R.id.item_desc);
        this.priceOld = findViewById(R.id.item_price_old);
        this.priceNew = findViewById(R.id.item_price_new);
        this.priceOld.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);

        GlobalApplication global = (GlobalApplication) getApplicationContext();
        ProductEntity prod = global.gProduct;

        ImageViewLoader.load(this, image, prod.getImagelink());
        name.setText(prod.getName());
        desc.setText(prod.getDescription());
        priceOld.setText(BkapUtils.formatCurrency(prod.getPrice(), new Locale("vi", "VN")));
        priceNew.setText(BkapUtils.formatCurrency(prod.getPrice(), new Locale("vi", "VN")));
    }
}
