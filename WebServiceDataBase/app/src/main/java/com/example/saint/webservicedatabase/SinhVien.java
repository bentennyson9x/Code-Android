package com.example.saint.webservicedatabase;

import java.io.Serializable;

public class SinhVien implements Serializable {
    int id;
    String Name;
    String NamSinh;
    String DiaChi;

    public SinhVien(int id, String name, String namSinh, String diaChi) {
        this.id = id;
        Name = name;
        NamSinh = namSinh;
        DiaChi = diaChi;
    }

    public SinhVien() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getNamSinh() {
        return NamSinh;
    }

    public void setNamSinh(String namSinh) {
        NamSinh = namSinh;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String diaChi) {
        DiaChi = diaChi;
    }
}
