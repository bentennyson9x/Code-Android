package com.example.saint.quan_ly_chi_tieu;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.example.saint.quan_ly_chi_tieu.CustomSpinner.Custom_adapter_spinner;
import com.example.saint.quan_ly_chi_tieu.CustomSpinner.Custom_spinner;
import com.example.saint.quan_ly_chi_tieu.ListPayAmount.pay_amount;

import java.util.ArrayList;

public class fragment_plus extends Fragment {
    View display;
    Spinner Lua_chon_Plus;
    ArrayList <pay_amount> ListPayAmount;
    public void AddButton(){
        Lua_chon_Plus = display.findViewById(R.id.luaChonPlus);
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         display = inflater.inflate(R.layout.fragment_plus,container,false);
        AddButton();
        GetDataFromActivity();
        Custom_spinner_luachon();
        return display;
    }


    private void GetDataFromActivity() {
        Bundle bundle =getArguments();
        if (bundle!= null){
            ListPayAmount = (ArrayList<pay_amount>) bundle.getSerializable("ListPayAmount");
        }
        else ListPayAmount = new ArrayList<>();
    }

    private void Custom_spinner_luachon() {
        ArrayList <Custom_spinner> lua_Chon = new ArrayList<>();
        lua_Chon.add(new Custom_spinner(R.drawable.minus,"Pay"));
        lua_Chon.add(new Custom_spinner(R.drawable.plus_money,"Collect Money"));
        lua_Chon.add(new Custom_spinner(R.drawable.tranfer_money,"Tranfer Money"));
        lua_Chon.add(new Custom_spinner(R.drawable.edit,"Balance Adjustment"));
        Custom_adapter_spinner custom_adapter_spinner = new Custom_adapter_spinner(getActivity(), R.layout.custom_spinner_not_drop_down,lua_Chon);
        Lua_chon_Plus.setAdapter(custom_adapter_spinner);
        Lua_chon_Plus.setDropDownVerticalOffset(105);
        Lua_chon_Plus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                Fragment fragment = null;
                if (adapterView.getSelectedItemPosition()==0){
                    fragment = new fragment_pay();
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("ListPayAmount",ListPayAmount);
                    fragment.setArguments(bundle);

                }
                if (adapterView.getSelectedItemPosition()==1){
                    fragment = new fragment_collect_amount();
                }
                if (adapterView.getSelectedItemPosition()==2){

                }
                if (adapterView.getSelectedItemPosition()==3){

                }

                fragmentTransaction.replace(R.id.displayFragment_Plus,fragment);
                fragmentTransaction.commit();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

}
