package com.example.quanlynhansu.Model;

import java.sql.Time;
import java.util.Date;

public class NhanSu {
    String hoVaten;
    String sinhNhat;
    String viTri;
    String gioiTinh;
    String sDT;
    String diaChi;
    Time thoiGianPV;
    Date ngayHen;
    String diaChiHenGap;
    String tenNguoiHen;
    String sDTNguoiHen;

    public NhanSu(String hoVaten, String sinhNhat, String viTri, String gioiTinh, String sDT, String diaChi, Time thoiGianPV, Date ngayHen, String diaChiHenGap, String tenNguoiHen, String sDTNguoiHen) {
        this.hoVaten = hoVaten;
        this.sinhNhat = sinhNhat;
        this.viTri = viTri;
        this.gioiTinh = gioiTinh;
        this.sDT = sDT;
        this.diaChi = diaChi;
        this.thoiGianPV = thoiGianPV;
        this.ngayHen = ngayHen;
        this.diaChiHenGap = diaChiHenGap;
        this.tenNguoiHen = tenNguoiHen;
        this.sDTNguoiHen = sDTNguoiHen;
    }

    public NhanSu() {
    }


    public String getHoVaten() {
        return hoVaten;
    }

    public void setHoVaten(String hoVaten) {
        this.hoVaten = hoVaten;
    }

    public String getSinhNhat() {
        return sinhNhat;
    }

    public void setSinhNhat(String sinhNhat) {
        this.sinhNhat = sinhNhat;
    }

    public String getViTri() {
        return viTri;
    }

    public void setViTri(String viTri) {
        this.viTri = viTri;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getsDT() {
        return sDT;
    }

    public void setsDT(String sDT) {
        this.sDT = sDT;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public Time getThoiGianPV() {
        return thoiGianPV;
    }

    public void setThoiGianPV(Time thoiGianPV) {
        this.thoiGianPV = thoiGianPV;
    }

    public Date getNgayHen() {
        return ngayHen;
    }

    public void setNgayHen(Date ngayHen) {
        this.ngayHen = ngayHen;
    }

    public String getDiaChiHenGap() {
        return diaChiHenGap;
    }

    public void setDiaChiHenGap(String diaChiHenGap) {
        this.diaChiHenGap = diaChiHenGap;
    }

    public String getTenNguoiHen() {
        return tenNguoiHen;
    }

    public void setTenNguoiHen(String tenNguoiHen) {
        this.tenNguoiHen = tenNguoiHen;
    }

    public String getsDTNguoiHen() {
        return sDTNguoiHen;
    }

    public void setsDTNguoiHen(String sDTNguoiHen) {
        this.sDTNguoiHen = sDTNguoiHen;
    }

}
