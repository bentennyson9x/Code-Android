package com.example.minhvufc.eshop;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.minhvufc.eshop.entity.ProductEntity;

import java.util.ArrayList;
import java.util.Locale;

public class AdapterProduct extends RecyclerView.Adapter<AdapterProduct.ViewHolder> {

    ArrayList<ProductEntity> mList;
    Context mContext;

    public AdapterProduct(ArrayList<ProductEntity> mList, Context mContext) {
        this.mList = mList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Load layout
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_product, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // holder.image;
        ImageViewLoader.load(mContext, holder.image, mList.get(position).getImagelink());
        holder.name.setText(mList.get(position).getName());
        holder.desc.setText(mList.get(position).getDescription());
        holder.priceOld.setText(BkapUtils.formatCurrency(mList.get(position).getPrice(), new Locale("vi", "VN")));
        holder.priceNew.setText(BkapUtils.formatCurrency(mList.get(position).getPrice(), new Locale("vi", "VN")));

        final ProductEntity prod = mList.get(position);

        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Cài đặt biến toàn cục để có thể nhận dễ dàng từ ProductActivity
                GlobalApplication global = (GlobalApplication) mContext;
                global.gProduct = prod;
                Intent intent = new Intent(mContext, ProductActivity.class);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView name;
        TextView desc;
        TextView priceOld;
        TextView priceNew;

        public ViewHolder(View v) {
            super(v);
            this.image = v.findViewById(R.id.item_image);
            this.name = v.findViewById(R.id.item_name);
            this.desc = v.findViewById(R.id.item_desc);
            this.priceOld = v.findViewById(R.id.item_price_old);
            this.priceNew = v.findViewById(R.id.item_price_new);
            this.priceOld.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        }
    }
}
