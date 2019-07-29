package com.example.andy.aiforphotonic;

import android.util.Log;
import android.widget.CheckBox;
import android.widget.RadioButton;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Chips {
    int chipName;
    int keTrai,kePhai,keTren,keDuoi;
    Department cucTrai,cucPhai,cucTren,cucDuoi;
    boolean activeKeTrai=true,
            activeKePhai=true,
            activeKeTren=true,
            activeKeDuoi=true;
    int Trace;
    List <Integer> ke = new ArrayList<>();

    public boolean xetActiveKe( int i ){
        if (i == keTrai){
            // trai
            if (cucTrai.isClosed()){
                activeKeTrai = false;
            }
            else {
                activeKeTrai = true;
            }
            return activeKeTrai;
        }
        else if(i == kePhai){
            // phai

            if (cucPhai.isClosed()){
                activeKePhai = false;
            }
            else {
                activeKePhai = true;
            }
            return activeKePhai;
        }
        else if(i == keTren){
            // tren
            if (cucTren.isClosed()){
                activeKeTren = false;
            }
            else {
                activeKeTren = true;
            }
            return activeKeTren;
        }
        else if(i == keDuoi){
            // duoi
            if (cucDuoi.isClosed()){
                activeKeDuoi = false;
            }
            else {
                activeKeDuoi = true;
            }
            return activeKeDuoi;
        }
        return false ;
    }
    public boolean isActiveKeTrai() {
        return activeKeTrai;
    }

    public void setActiveKeTrai(boolean activeKeTrai) {
        this.activeKeTrai = activeKeTrai;
    }

    public boolean isActiveKePhai() {
        return activeKePhai;
    }

    public void setActiveKePhai(boolean activeKePhai) {
        this.activeKePhai = activeKePhai;
    }

    public boolean isActiveKeTren() {
        return activeKeTren;
    }

    public void setActiveKeTren(boolean activeKeTren) {
        this.activeKeTren = activeKeTren;
    }

    public boolean isActiveKeDuoi() {
        return activeKeDuoi;
    }

    public void setActiveKeDuoi(boolean activeKeDuoi) {
        this.activeKeDuoi = activeKeDuoi;
    }

    public List<Integer> getKe() {
        return ke;
    }

    public void setKe(List<Integer> ke) {
        this.ke = ke;
    }

    public int getTrace() {
        return Trace;
    }

    public void setTrace(int trace) {
        Trace = trace;
    }

    public Chips(int chipName) {
        this.chipName = chipName;
    }

    public int getChipName() {
        return chipName;
    }

    public void setChipName(int chipName) {
        this.chipName = chipName;
    }

    public int getKeTrai() {
        return keTrai;
    }

    public void setKeTrai(int keTrai) {
        this.keTrai = keTrai;
    }

    public int getKePhai() {
        return kePhai;
    }

    public void setKePhai(int kePhai) {
        this.kePhai = kePhai;
    }

    public int getKeTren() {
        return keTren;
    }

    public void setKeTren(int keTren) {
        this.keTren = keTren;
    }

    public int getKeDuoi() {
        return keDuoi;
    }

    public void setKeDuoi(int keDuoi) {
        this.keDuoi = keDuoi;
    }

    public Department getCucTrai() {
        return cucTrai;
    }

    public void setCucTrai(Department cucTrai) {
        this.cucTrai = cucTrai;
    }

    public Department getCucPhai() {
        return cucPhai;
    }

    public void setCucPhai(Department cucPhai) {
        this.cucPhai = cucPhai;
    }

    public Department getCucTren() {
        return cucTren;
    }

    public void setCucTren(Department cucTren) {
        this.cucTren = cucTren;
    }

    public Department getCucDuoi() {
        return cucDuoi;
    }

    public void setCucDuoi(Department cucDuoi) {
        this.cucDuoi = cucDuoi;
    }
}
