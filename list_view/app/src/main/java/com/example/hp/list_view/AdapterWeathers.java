package com.example.hp.list_view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class AdapterWeathers extends ArrayAdapter <weather> {
    Context mContext;
    int mLayout;
    ArrayList<weather> mlist;
    public AdapterWeathers(@NonNull Context context, int resource, @NonNull ArrayList<weather> objects) {
        super(context, resource, objects);
        this.mContext=context;
        this.mLayout=resource;
        this.mlist= objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row = convertView;
        if (row==null){
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.item_weather,null);
        }
        // Lay du lieu
        weather a = mlist.get(position);
        // Lấy widget trên layout row
        ImageView itemImage = (ImageView) row.findViewById(R.id.item_image);
        TextView itemCity = ( TextView ) row.findViewById(R.id.lbl_City);
        TextView itemDesc = ( TextView ) row.findViewById(R.id.lbl_statusWeather);
        TextView itemTemp = ( TextView ) row.findViewById(R.id.temp);
        itemImage.setImageResource(a.image);
        itemCity.setText(a.city);
        itemDesc.setText(a.desc);
        itemTemp.setText(a.temp);
       // return super.getView(position, convertView, parent);
        return row;
    }
}
