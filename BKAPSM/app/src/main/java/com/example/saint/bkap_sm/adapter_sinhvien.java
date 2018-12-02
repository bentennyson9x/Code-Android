package com.example.saint.bkap_sm;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

class adapter_sinhvien extends ArrayAdapter <SinhVien> {
    Context mContext;
    int mLayout;
    ArrayList<SinhVien> mlist;
    public adapter_sinhvien(@NonNull Context context, int resource, @NonNull ArrayList<SinhVien> objects) {
        super(context, resource, objects);
        this.mContext=context;
        this.mLayout=resource;
        this.mlist= objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row = convertView;
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.custom_list_sinhvien,null);
        SinhVien a = mlist.get(position);

        return super.getView(position, convertView, parent);
    }
}
