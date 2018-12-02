package com.example.saint.qlctud.customspinner;

public class CustomSpinner {
    public int resource_icon;
    String Text;

    public CustomSpinner(int resource_icon, String text) {
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
