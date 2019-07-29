package com.example.temp;

import android.util.Log;
import android.widget.CheckBox;

import java.util.ArrayList;

public class Gate {
    int nameGate;
    CheckBox checkBox;
    ArrayList<Gate> GateKe=new ArrayList<>();
    int ToaDoX;
    int ToaDoY;
    double distanceOfGateToRoot;
    double fx;
    Gate Trace;

    public Gate getTrace() {
        return Trace;
    }

    public void setTrace(Gate trace) {
        Trace = trace;
    }

    public double getFx() {
        return fx;
    }

    public void setFx(double fx) {
        this.fx = fx;
    }

    public double getDistanceOfGateToRoot() {
        return distanceOfGateToRoot;
    }

    public void setDistanceOfGateToRoot(double distanceOfGateToRoot) {
        this.distanceOfGateToRoot = distanceOfGateToRoot;
    }

    public int getToaDoX() {
        return ToaDoX;
    }

    public void setToaDoX(int toaDoX) {
        ToaDoX = toaDoX;
    }

    public int getToaDoY() {
        return ToaDoY;
    }

    public void setToaDoY(int toaDoY) {
        ToaDoY = toaDoY;
    }

    public ArrayList<Gate> getGateKe() {
        return GateKe;
    }

    public void setGateKe(ArrayList<Gate> gateKe) {
        GateKe = gateKe;
    }

    public Gate(int nameGate, CheckBox checkBox) {
        this.nameGate = nameGate;
        this.checkBox = checkBox;
    }

    public Gate(int nameGate) {
        this.nameGate = nameGate;
    }

    public int getNameGate() {
        return nameGate;
    }

    public void setNameGate(int nameGate) {
        this.nameGate = nameGate;
    }

    public CheckBox getCheckBox() {
        return checkBox;
    }

    public void setCheckBox(CheckBox checkBox) {
        this.checkBox = checkBox;
    }
}
