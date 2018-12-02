package com.example.saint.qlctud.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.example.saint.qlctud.R;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class FragmentHome extends Fragment {
    View displayFragment;
    PieChart pieChart;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        displayFragment = inflater.inflate(R.layout.fragment_home,null);
        return displayFragment;
    }
    private void CustomPieChart(){
        pieChart.setRotationEnabled(true);
        pieChart.animateY(500, Easing.EasingOption.Linear); // set chuyen dong
        pieChart.setTransparentCircleRadius(61f);
        pieChart.setCenterTextColor(Color.parseColor("#e75454"));
        pieChart.setHoleRadius(50);// more info
        pieChart.getDescription().setEnabled(false);
        pieChart.setTransparentCircleAlpha(100);// more info
        pieChart.setTransparentCircleColor(Color.parseColor("#e6e6e6"));
        pieChart.setCenterTextSize(10);
        pieChart.setCenterText("Your Money");
        pieChart.setOnTouchListener(new View.OnTouchListener() {


            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                view.getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });
    }
    private void valueSelectedForPieChart() {
        pieChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {

            }

            @Override
            public void onNothingSelected() {

            }
        });
    }
    private void add_Data_For_PieChart() {
        ArrayList<PieEntry> pieEntries = new ArrayList<>();
        // can use formatter.format(//something number//);
        PieDataSet pieDataSet = new PieDataSet(pieEntries,"");
        pieDataSet.setSliceSpace(2);
        pieDataSet.setSelectionShift(20);
        pieDataSet.setValueTextSize(10);

        // Add color
        ArrayList<Integer> color = new ArrayList<>();
        color.add(Color.parseColor("#8cef70"));// Xanh
        color.add(Color.parseColor("#da5959"));// Do
        color.add(Color.parseColor("#b365ea"));// Tim
        color.add(Color.parseColor("#ffe354"));// Vang
        color.add(Color.parseColor("#3bc7fa"));// Luc
        color.add(Color.parseColor("#3ff4e2"));// Lam
        color.add(Color.parseColor("#ffa43c"));// Cam
        for (int i : ColorTemplate.MATERIAL_COLORS){
            color.add(i);
        }
        for (int i : ColorTemplate.COLORFUL_COLORS){
            color.add(i);
        }
        for (int i : ColorTemplate.JOYFUL_COLORS){
            color.add(i);
        }
        for (int i : ColorTemplate.LIBERTY_COLORS){
            color.add(i);
        }
        for (int i : ColorTemplate.PASTEL_COLORS){
            color.add(i);
        }
        for (int i : ColorTemplate.VORDIPLOM_COLORS){
            color.add(i);
        }

        pieDataSet.setColors(color);
        // add legend to chart
        Legend legend = pieChart.getLegend();
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setFormSize(15);
        legend.setTextSize(12);
        // creat pie data object
        PieData pieData = new PieData(pieDataSet);
        pieData.setValueFormatter(new PercentFormatter());
        pieChart.setData(pieData);
        pieChart.invalidate();

    }

}
