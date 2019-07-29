package com.example.andy.aiforphotonic;

import java.util.Hashtable;

public class SingletonData {
    static Hashtable<Object,Object> dataFactory = new Hashtable();
    public static void putData(Object Key, Object Value) {
        dataFactory.put(Key,Value);
    }

    public static Object getData(Object Key) {
        return dataFactory.get(Key);
    }
}
