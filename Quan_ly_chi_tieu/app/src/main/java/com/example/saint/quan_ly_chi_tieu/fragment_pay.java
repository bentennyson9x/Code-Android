package com.example.saint.quan_ly_chi_tieu;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.saint.quan_ly_chi_tieu.CustomSpinner.Custom_adapter_spinner_reason;
import com.example.saint.quan_ly_chi_tieu.CustomSpinner.Custom_spinner;
import com.example.saint.quan_ly_chi_tieu.ListPayAmount.pay_amount;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class fragment_pay extends Fragment implements View.OnClickListener {
    View fragment_pay_display;
    ArrayList <Custom_spinner> custom_spinners_reason;
    Spinner spinner_reason ;
    Spinner style_Pay ;
    static StaticImage image;
    EditText Ngay_pay;
    EditText Time_pay;
    EditText input_amount;
    EditText mô_tả;
    EditText Trip;
    EditText WhitWhom;
    EditText location;
    Button xacnhan;
    ImageButton Home;
    Interface_community_of_Fragment mCallback;
    ArrayList <pay_amount> List_pay_amounts ;
    ArrayList <Custom_spinner> custom_spinners_style_pay;

    private void ChonNgay(final EditText editText) {
        final Calendar calendar = Calendar.getInstance();
        int ngay = calendar.get(Calendar.DAY_OF_MONTH);
        int thang = calendar.get(Calendar.MONTH);
        int nam = calendar.get(Calendar.YEAR);
        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yy");
                calendar.set(year, month, dayOfMonth);
                editText.setText(simpleDateFormat.format(calendar.getTime()));
            }
        }, nam, thang, ngay);
        datePickerDialog.show();
    }
    private void ChonTime ( final EditText editText ){
        final Calendar calendar = Calendar.getInstance();
        int gio = calendar.get(Calendar.HOUR_OF_DAY);
        int phut = calendar.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
                calendar.set(0,0,0,i,i1);
                editText.setText(simpleDateFormat.format(calendar.getTime()));
            }
        },gio,phut,true );
        timePickerDialog.show();
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragment_pay_display = inflater.inflate(R.layout.fragment_pay,container,false);
        List_pay_amounts = new ArrayList<>();
        AddButton();
        loadPreferences();
        spinner_custom();
        AddArrayListPayAmountFromActivity();
        Click();
        return fragment_pay_display;
    }

    @Override
    public void onPause() {
        super.onPause();
        savePreferences();
    }

    private void AddArrayListPayAmountFromActivity() {
        Bundle bundle = getArguments();
        if (bundle!= null){
            List_pay_amounts = (ArrayList<pay_amount>) bundle.getSerializable("ListPayAmount");
            Log.e("xem","tu pay "+ String.valueOf(List_pay_amounts.size()));
        }
        savePreferences();
    }

    private void Confirm() {
        int position_of_spinner_reason = spinner_reason.getSelectedItemPosition();
        int position_of_spinner_style_pay_ = style_Pay.getSelectedItemPosition();
        BigDecimal amount = new BigDecimal(input_amount.getText().toString());
        String stylePay = custom_spinners_style_pay.get(position_of_spinner_style_pay_).getText();
        String payFor = custom_spinners_reason.get(position_of_spinner_reason).getText();
        String date = Ngay_pay.getText().toString();
        String timepay = Time_pay.getText().toString();
        String description = mô_tả.getText().toString();
        String Location = location.getText().toString();
        String whit_whom = WhitWhom.getText().toString();
        String trip = Trip.getText().toString();
        int resourceIconReason =  custom_spinners_reason.get(position_of_spinner_reason).resource_icon;
        int resourceIconStylePay = custom_spinners_style_pay.get(position_of_spinner_style_pay_).resource_icon;
        List_pay_amounts.add(new pay_amount(amount, stylePay, payFor, date, description,trip,
                whit_whom, Location, timepay,resourceIconReason, resourceIconStylePay));
         mCallback.Community(null, List_pay_amounts);
         input_amount.setText("0");
        Home.performClick();
        Toast.makeText(getActivity(), "Success!",Toast.LENGTH_SHORT).show();
    }

    private void Click() {
        Ngay_pay.setOnClickListener(this);
        Time_pay.setOnClickListener(this);
        xacnhan.setOnClickListener(this);
    }

    private void spinner_custom() {
         image = new StaticImage();
        custom_spinners_reason = new ArrayList<>();
        custom_spinners_reason.add(new Custom_spinner(image.get("Invest"),"Invest"));
        custom_spinners_reason.add(new Custom_spinner(image.get("Eating"),"Eating"));
        custom_spinners_reason.add(new Custom_spinner(image.get("Cafe"),"Cafe"));
        custom_spinners_reason.add(new Custom_spinner(image.get("Wife"),"Wife"));
        Custom_adapter_spinner_reason custom_adapter_spinner_reason = new Custom_adapter_spinner_reason(getActivity(),R.layout.custom_spinner_dropdown,custom_spinners_reason);
        spinner_reason.setAdapter(custom_adapter_spinner_reason);
        custom_spinners_style_pay = new ArrayList<>();
        custom_spinners_style_pay.add(new Custom_spinner(image.get("ATM"),"ATM"));
        custom_spinners_style_pay.add(new Custom_spinner(image.get("Wallet"),"Wallet"));
        custom_spinners_style_pay.add(new Custom_spinner(image.get("Company"),"Company"));
        custom_spinners_style_pay.add(new Custom_spinner(image.get("Salary"),"Salary"));
        Custom_adapter_spinner_reason custom_adapter_spinner_style_pay = new Custom_adapter_spinner_reason(getActivity(),R.layout.custom_spinner_dropdown,custom_spinners_style_pay);
        style_Pay.setAdapter(custom_adapter_spinner_style_pay);


    }

    private void AddButton() {
        spinner_reason = fragment_pay_display.findViewById(R.id.reason);
        style_Pay = fragment_pay_display.findViewById(R.id.style_pay);
        Ngay_pay = fragment_pay_display.findViewById(R.id.ngay_pay);
        input_amount=  fragment_pay_display.findViewById(R.id.input_amount);
        Time_pay = fragment_pay_display.findViewById(R.id.gio_pay);
        xacnhan= fragment_pay_display.findViewById(R.id.Comfirm);
        mô_tả = fragment_pay_display.findViewById(R.id.diengiaikhoanchi);
        location = fragment_pay_display.findViewById(R.id.dia_diem);
        Trip = fragment_pay_display.findViewById(R.id.chuyendi);
        WhitWhom = fragment_pay_display.findViewById(R.id.voi_ai);
        Home = getActivity().findViewById(R.id.Home_icon);

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mCallback = (Interface_community_of_Fragment) getActivity();

    }

    @Override
    public void onClick(View view) {
        if (view == Ngay_pay ){
            ChonNgay(Ngay_pay);
        }
        if (view == Time_pay){
            ChonTime(Time_pay);
        }
        if (view == xacnhan ){
          Confirm();
        }
    }

    private void loadPreferences() {
        SharedPreferences sharedPreferences =getActivity().getSharedPreferences("SaveData_fragmentPay",Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("ListPayAmount",null);
        Type type = new TypeToken<ArrayList<pay_amount>>(){}.getType();
        List_pay_amounts = gson.fromJson(json,type);
        input_amount.setText(sharedPreferences.getString("InputAmount",""));
        if (List_pay_amounts== null){
            List_pay_amounts= new ArrayList<>();
        }

    }
   private void savePreferences(){
       SharedPreferences sharedPreferences = getActivity().getSharedPreferences("SaveData_fragmentPay",Context.MODE_PRIVATE);
       SharedPreferences.Editor editor = sharedPreferences.edit();
       Gson gson = new Gson();
       String json = gson.toJson(List_pay_amounts);
       editor.putString("ListPayAmount", json);
       editor.putString("InputAmount",input_amount.getText().toString());
       editor.commit();
   }
    @Override
    public void onResume() {
        super.onResume();
        Log.e("xem", "onResume: " );
        loadPreferences();
    }

}
