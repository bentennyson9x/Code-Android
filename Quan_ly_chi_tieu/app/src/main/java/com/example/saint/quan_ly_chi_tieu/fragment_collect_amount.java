package com.example.saint.quan_ly_chi_tieu;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class fragment_collect_amount extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
      View Display_fragment_collect_amount ;
      Display_fragment_collect_amount = inflater.inflate(R.layout.fragment_collect_amount,container,false);


        return Display_fragment_collect_amount;
    }
}
