package com.example.minhvufc.eshop;

import java.text.NumberFormat;
import java.util.Locale;

public class BkapUtils {

    public static String formatCurrency(float number, Locale locale){
        NumberFormat format = NumberFormat.getCurrencyInstance(locale);
        return format.format(number);
    }
}
