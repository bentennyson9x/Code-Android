package com.example.saint.quan_ly_chi_tieu;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.saint.quan_ly_chi_tieu.ListPayAmount.Adapter_payamount;
import com.example.saint.quan_ly_chi_tieu.ListPayAmount.pay_amount;
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
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class fragment_home extends Fragment  {
    ListView Display_amount;
    ArrayList <pay_amount> ListPayAmount;
    PieChart pieChart;
    View Display;
    Map<String,BigDecimal> map;
    Adapter_payamount adapter_payamount;
    TextView Total_Finance_initially;
    TextView the_current_financial_situation;
    BigDecimal number_Total_Finance_initially;
    BigDecimal number_The_current_financial_situation;
    BigDecimal SumOf_number_The_current_pay_situation;
    Interface_community_of_Fragment mcallback;
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
       if (ListPayAmount != null){
           Set <String> set = map.keySet();
           for (String i : set){
               if (map.get(i).intValue() != 0){
                   BigDecimal percent = map.get(i)
                           .divide(SumOf_number_The_current_pay_situation,2, RoundingMode.FLOOR);
                   pieEntries.add(new PieEntry( percent.floatValue()*100,i));
               }
           }

           // pieEntries.add(new PieEntry( dataPercent.floatValue(),ListPayAmount.get(i).getPay_for()));
       }
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
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
          Display = inflater.inflate(R.layout.fragment_home,container,false);
          AddButton();
          AddNew();
          loadPreferences();
            AddListViewDisplayPayMount();
            SetFromListPayAmountToListPieChart();
            CustomPieChart();
            Touch();// disable touch cua parent listview
           add_Data_For_PieChart();
           valueSelectedForPieChart();
           SelectItemDisplayAmount();
           registerForContextMenu(Display_amount);
        pieChart.setDrawSliceText(false);
        savePreferences();
        return Display;
    }

    private void AddListViewDisplayPayMount() {
        DecimalFormat formatter = new DecimalFormat("###,###,###.##");
        Total_Finance_initially.setText(formatter.format(number_Total_Finance_initially)+" VNĐ");
        the_current_financial_situation.setText(formatter.format(number_The_current_financial_situation)+" VNĐ");
        Bundle bundle = getArguments();
        if (bundle!= null){
            ListPayAmount = (ArrayList<pay_amount>) bundle.getSerializable("ListPayAmount");
        }
        adapter_payamount = new Adapter_payamount(getActivity(),R.layout.custom_display_pay_amount,ListPayAmount);
        Display_amount.setAdapter(adapter_payamount);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getActivity().getMenuInflater().inflate(R.menu.context_menu_for_display_amount,menu);
        String title = "Option";
        menu.setHeaderTitle(title);
    }
    @Override
    public boolean onContextItemSelected(MenuItem item) {

        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()){
            case R.id.xoa :
                XacNhanXoa(menuInfo.position);
                break;
        }
        return super.onContextItemSelected(item);
    }

    private void XacNhanXoa(final int position ) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
        alertDialog.setTitle("Notice !!");
        alertDialog.setIcon(R.mipmap.ic_launcher);
        alertDialog.setMessage("Do you want to delete this amount ? ");
        alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                ListPayAmount.remove(position);
                adapter_payamount.notifyDataSetChanged();
                AddListViewDisplayPayMount();
                add_Data_For_PieChart();
                mcallback.Community(null,ListPayAmount);
                // Reload current fragment
                savePreferences();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.detach(getActivity().getSupportFragmentManager().findFragmentByTag("fragment_home")).attach(getActivity().getSupportFragmentManager().findFragmentByTag("fragment_home")).commit();
            }
        });
        alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        alertDialog.show();

    }

    private void SelectItemDisplayAmount() {
        Display_amount.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void Touch() {
        Display_amount.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                view.getParent().requestDisallowInterceptTouchEvent(true);// yeu cau quyen khong cho phep su kien cham
                return false;
            }
        });
    }

    private void SetFromListPayAmountToListPieChart() {
        DecimalFormat formatter_toText = new DecimalFormat("###,###,###.##");
        BigDecimal invest = new BigDecimal(0);
        BigDecimal eating = new BigDecimal(0);
        BigDecimal cafe = new BigDecimal(0);
        BigDecimal wife = new BigDecimal(0);
        SumOf_number_The_current_pay_situation =
                new BigDecimal("0")  ;
      if (ListPayAmount!=null){
          for (int i=0;i<ListPayAmount.size();i++){
              SumOf_number_The_current_pay_situation=SumOf_number_The_current_pay_situation.add(ListPayAmount.get(i).amount);
              if (ListPayAmount.get(i).pay_for.equals("Invest")){
                  invest=invest.add(ListPayAmount.get(i).amount);
              }
              if (ListPayAmount.get(i).pay_for.equals("Eating")){
                 eating= eating.add(ListPayAmount.get(i).amount);
              }
              if (ListPayAmount.get(i).pay_for.equals("Cafe")){
                  cafe=cafe.add(ListPayAmount.get(i).amount);
              }
              if (ListPayAmount.get(i).pay_for.equals("Wife")){
                 wife= wife.add(ListPayAmount.get(i).amount);
              }
              number_The_current_financial_situation =number_The_current_financial_situation.
                      subtract(ListPayAmount.get(i).amount);
              the_current_financial_situation.setText(formatter_toText.
                      format(number_The_current_financial_situation)+" VNĐ");
          }
      }
        map = new HashMap<>();

        map.put("Invest",invest);
        map.put("Eating", eating);
        map.put("Cafe", cafe);
        map.put("Wife", wife);
}

    private void AddNew() {
        number_Total_Finance_initially = new BigDecimal("100000000");
        number_The_current_financial_situation = new BigDecimal(number_Total_Finance_initially.toString());
    }

    private void AddButton() {
        Display_amount = Display.findViewById(R.id.CacKhoanGhiChep);
        Total_Finance_initially = Display.findViewById(R.id.Total_Finance_initially);
        the_current_financial_situation = Display.findViewById(R.id.The_current_financial_situation_text);
        pieChart = Display.findViewById(R.id.PieChart);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mcallback= (Interface_community_of_Fragment) getActivity();
    }
    private void loadPreferences() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("SaveData_fragmentPay", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("ListPayAmount", null);
        Type type = new TypeToken<ArrayList<pay_amount>>() {
        }.getType();
        ListPayAmount = gson.fromJson(json, type);
        if (ListPayAmount == null) {
           ListPayAmount = new ArrayList<>();
        }

    }
        private void savePreferences(){
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("SaveData_fragmentPay",Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            Gson gson = new Gson();
            String json = gson.toJson(ListPayAmount);
            editor.putString("ListPayAmount", json);
            editor.commit();
        }

    }


