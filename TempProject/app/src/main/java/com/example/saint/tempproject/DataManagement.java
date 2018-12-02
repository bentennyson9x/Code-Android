package com.example.saint.tempproject;

import java.io.Serializable;
import java.util.HashMap;

public class DataManagement {
    public static HashMap <String,String> data = new HashMap<>();
    public static void putData(String Key, String Value){
        data.put(Key,Value);
    }
    public static String getData(String Key){
        return data.get(Key);
    }
}
