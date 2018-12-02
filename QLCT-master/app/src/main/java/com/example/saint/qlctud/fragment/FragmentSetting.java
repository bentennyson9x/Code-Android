package com.example.saint.qlctud.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.saint.qlctud.R;

public class FragmentSetting extends Fragment {
    View displayFragment;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        displayFragment = inflater.inflate(R.layout.fragment_setting,null);
        return displayFragment;
    }
}
