package com.example.saint.sqliteshop;

public class Product {
    int id;
    String name;
    int quantity;
    double price;
    String inputdate;

    public Product(String name, int quantity, double price, String inputdate) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.inputdate = inputdate;
    }

    public Product(int id, String name, int quantity, double price, String inputdate) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.inputdate = inputdate;
    }
}
