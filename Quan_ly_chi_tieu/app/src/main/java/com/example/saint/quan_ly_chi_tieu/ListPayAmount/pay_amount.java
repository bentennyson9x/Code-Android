package com.example.saint.quan_ly_chi_tieu.ListPayAmount;

import java.io.Serializable;
import java.math.BigDecimal;

public class pay_amount implements Serializable {
   public BigDecimal amount;
    String style_pay;
    public String pay_for;
    String date,description,trip,with_whom,location,time;
    int Icon_reason,Icon_stylePay;
    public int getIcon_reason() {
        return Icon_reason;
    }

    public void setIcon_reason(int icon_reason) {
        Icon_reason = icon_reason;
    }

    public int getIcon_stylePay() {
        return Icon_stylePay;
    }

    public void setIcon_stylePay(int icon_stylePay) {
        Icon_stylePay = icon_stylePay;
    }

    public pay_amount(BigDecimal amount, String style_pay, String pay_for, String date, String description, String trip, String with_whom, String location, String time, int icon_reason, int icon_stylePay) {
        this.amount = amount;
        this.style_pay = style_pay;
        this.pay_for = pay_for;
        this.date = date;
        this.description = description;
        this.trip = trip;
        this.with_whom = with_whom;
        this.location = location;
        this.time = time;
        Icon_reason = icon_reason;
        Icon_stylePay = icon_stylePay;
    }

    public pay_amount(BigDecimal amount, String style_pay, String pay_for, String date, String description, String trip, String with_whom, String location, String time) {
        this.amount = amount;
        this.style_pay = style_pay;
        this.pay_for = pay_for;
        this.date = date;
        this.description = description;
        this.trip = trip;
        this.with_whom = with_whom;
        this.location = location;
        this.time = time;
    }

    public pay_amount(BigDecimal amount, String style_pay, String pay_for, String date, String description) {
        this.amount = amount;
        this.style_pay = style_pay;
        this.pay_for = pay_for;
        this.date = date;
        this.description = description;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getStyle_pay() {
        return style_pay;
    }

    public void setStyle_pay(String style_pay) {
        this.style_pay = style_pay;
    }

    public String getPay_for() {
        return pay_for;
    }

    public void setPay_for(String pay_for) {
        this.pay_for = pay_for;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTrip() {
        return trip;
    }

    public void setTrip(String trip) {
        this.trip = trip;
    }

    public String getWith_whom() {
        return with_whom;
    }

    public void setWith_whom(String with_whom) {
        this.with_whom = with_whom;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
