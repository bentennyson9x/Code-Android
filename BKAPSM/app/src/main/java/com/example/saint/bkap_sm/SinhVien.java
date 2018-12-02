package com.example.saint.bkap_sm;

public class SinhVien {
    String name,sdt,email,tenMonHoc;
    boolean gioitinh;

    public SinhVien(String name, String sdt, String email, String tenMonHoc, boolean gioitinh) {
        this.name = name;
        this.sdt = sdt;
        this.email = email;
        this.tenMonHoc = tenMonHoc;
        this.gioitinh = gioitinh;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTenMonHoc() {
        return tenMonHoc;
    }

    public void setTenMonHoc(String tenMonHoc) {
        this.tenMonHoc = tenMonHoc;
    }

    public boolean isGioitinh() {
        return gioitinh;
    }

    public void setGioitinh(boolean gioitinh) {
        this.gioitinh = gioitinh;
    }
}
