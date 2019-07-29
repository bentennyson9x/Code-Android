package com.example.temp;

import android.widget.CheckBox;

import java.util.ArrayList;
import java.util.List;

public class Chips {
    int chipName;
    Gate[] gates;

    public Gate[] getGates() {
        return gates;
    }

    public void setGates(Gate[] gates) {
        this.gates = gates;
    }

    public void setChipName(int chipName) {
        this.chipName = chipName;
    }

    public int getChipName() {
        return chipName;
    }

    public Chips(int chipName) {
        this.chipName = chipName;
    }
}
