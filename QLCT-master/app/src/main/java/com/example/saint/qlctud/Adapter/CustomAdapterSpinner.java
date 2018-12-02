package com.example.saint.qlctud.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.saint.qlctud.R;
import com.example.saint.qlctud.customspinner.CustomSpinner;

import java.util.ArrayList;

public class CustomAdapterSpinner extends BaseAdapter {
    Context context;
    int Layout;
    ArrayList<CustomSpinner> lua_Chon = new ArrayList<>();
    @Override
    public int getCount() {

        return lua_Chon.size();
    }

    public CustomAdapterSpinner(Context context, int layout, ArrayList<CustomSpinner> lua_Chon) {
        this.context = context;
        Layout = layout;
        this.lua_Chon = lua_Chon;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getDropDownView(int position, View view, ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = layoutInflater.inflate(R.layout.custom_spinner_drop_down, null);
        RelativeLayout relativeLayout = (RelativeLayout) view;
        TextView textView = relativeLayout.findViewById(R.id.dropdowntextspiner);
        ImageView icon = relativeLayout.findViewById(R.id.icon_spinner_select);
        textView.setText(lua_Chon.get(position).getText());
        icon.setImageResource(lua_Chon.get(position).resource_icon);
        return view;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = layoutInflater.inflate(R.layout.custom_spinner_not_drop_down, null);
        TextView textView = view.findViewById(R.id.text_spinner_select);
        textView.setText(lua_Chon.get(i).getText());
        return view;
    }
}
