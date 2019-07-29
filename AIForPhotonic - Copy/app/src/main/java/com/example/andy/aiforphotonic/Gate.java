package com.example.andy.aiforphotonic;

import android.util.Log;
import android.widget.CheckBox;

public class Gate {
    int nameGate;
    int nameOfChipInclude;
    CheckBox checkBox;

    public Gate(int nameGate) {
        this.nameGate = nameGate;
    }

    public int getNameGate() {
        return nameGate;
    }

    public void setNameGate(int nameGate) {
        this.nameGate = nameGate;
    }

    public int getNameOfChipInclude() {
        return nameOfChipInclude;
    }

    public void setNameOfChipInclude(int nameOfChipInclude) {
        this.nameOfChipInclude = nameOfChipInclude;
    }

    public CheckBox getCheckBox() {
        return checkBox;
    }
    public void ToastVT(){


    }

    public void setCheckBox(CheckBox checkBox) {
        this.checkBox = checkBox;
    }
}
