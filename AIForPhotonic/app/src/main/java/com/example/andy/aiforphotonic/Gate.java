package com.example.andy.aiforphotonic;

public class Gate {
    int nameGate;
    int nameOfChipInclude;
    boolean Checked;

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

    public boolean isChecked() {
        return Checked;
    }

    public void setChecked(boolean checked) {
        Checked = checked;
    }
}
