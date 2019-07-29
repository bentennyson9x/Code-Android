package com.example.andy.aiforphotonic;

import android.util.Log;
import android.widget.CheckBox;

public class Department {
    int departmentName;
    Gate [] gateName;

    public Department(int departmentName) {
        this.departmentName = departmentName;
    }

    public int getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(int departmentName) {
        this.departmentName = departmentName;
    }

    public Gate[] getGateName() {
        return gateName;
    }

    public void setGateName(Gate[] gateName) {
        this.gateName = gateName;
    }
    public boolean isClosed(){
        boolean result = true;
        for (int i=1;i<=gateName.length-1;i++){
            if (gateName[i].isChecked()==false) result =false;
        }
        return result;
    }
}



