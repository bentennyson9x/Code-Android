package com.example.saint.quan_ly_chi_tieu.CustomSpinner;

import android.widget.ImageView;

public class Custom_spinner {
    public int resource_icon;
    String Text;

    public Custom_spinner(int resource_icon, String text) {
        this.resource_icon = resource_icon;
        Text = text;
    }

    public int getResource_icon() {
        return resource_icon;
    }

    public void setResource_icon(int resource_icon) {
        this.resource_icon = resource_icon;
    }

    public String getText() {
        return Text;
    }

    public void setText(String text) {
        Text = text;
    }
}
