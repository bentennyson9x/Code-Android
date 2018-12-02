package com.example.saint.qlctud.fragment;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
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
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.saint.qlctud.DataSingleton;
import com.example.saint.qlctud.R;
import com.example.saint.qlctud.activity.Login;
import com.example.saint.qlctud.Adapter.CustomAdapterSpinnerReason;
import com.example.saint.qlctud.customspinner.CustomSpinner;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class FragmentPay extends Fragment implements View.OnClickListener {
    View fragmentPayDisplay;
    ArrayList<CustomSpinner> lstSpinnersReason;
    ArrayList <CustomSpinner> lstSpinnersStylePay;
    Spinner spnReason ;
    Spinner spnStylePay ;
    EditText edtNgayPay;
    EditText edtTimePay;
    EditText edtInputAmount;
    EditText edtDescript;
    EditText edtTrip;
    EditText edtWithWhom;
    EditText edtLocation;
    Button btnConfirm;
    private void addView() {
        spnReason = fragmentPayDisplay.findViewById(R.id.reason);
        spnStylePay = fragmentPayDisplay.findViewById(R.id.style_pay);
        edtNgayPay = fragmentPayDisplay.findViewById(R.id.ngay_pay);
        edtInputAmount=  fragmentPayDisplay.findViewById(R.id.input_amount);
        edtTimePay = fragmentPayDisplay.findViewById(R.id.gio_pay);
        btnConfirm= fragmentPayDisplay.findViewById(R.id.Comfirm);
        edtDescript = fragmentPayDisplay.findViewById(R.id.diengiaikhoanchi);
        edtLocation = fragmentPayDisplay.findViewById(R.id.dia_diem);
        edtTrip = fragmentPayDisplay.findViewById(R.id.chuyendi);
        edtWithWhom = fragmentPayDisplay.findViewById(R.id.voi_ai);
    }
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
    private void spinnerCustom() {
        DataSingleton.putData("Invest",R.drawable.invest);
        DataSingleton.putData("Eating",R.drawable.hambuger);
        DataSingleton.putData("Cafe",R.drawable.cafe);
        DataSingleton.putData("Wife",R.drawable.wife);
        DataSingleton.putData("ATM",R.drawable.atm);
        DataSingleton.putData("Wallet",R.drawable.wallet);
        DataSingleton.putData("Company",R.drawable.company);
        DataSingleton.putData("Salary",R.drawable.salary);
        lstSpinnersReason = new ArrayList<>();
        lstSpinnersReason.add(new CustomSpinner((int)DataSingleton.getData("Invest"),"Invest"));
        lstSpinnersReason.add(new CustomSpinner((int)DataSingleton.getData("Eating"),"Eating"));
        lstSpinnersReason.add(new CustomSpinner((int)DataSingleton.getData("Cafe"),"Cafe"));
        lstSpinnersReason.add(new CustomSpinner((int)DataSingleton.getData("Wife"),"Wife"));
        CustomAdapterSpinnerReason customAdapterSpinnerReason
                = new  CustomAdapterSpinnerReason
                (getActivity(),R.layout.custom_spinner_drop_down,lstSpinnersReason);
        spnReason.setAdapter(customAdapterSpinnerReason);
        lstSpinnersStylePay = new ArrayList<>();
        lstSpinnersStylePay.add(new CustomSpinner((int)DataSingleton.getData("ATM"),"ATM"));
        lstSpinnersStylePay.add(new CustomSpinner((int)DataSingleton.getData("Wallet"),"Wallet"));
        lstSpinnersStylePay.add(new CustomSpinner((int)DataSingleton.getData("Company"),"Company"));
        lstSpinnersStylePay.add(new CustomSpinner((int)DataSingleton.getData("Salary"),"Salary"));
        CustomAdapterSpinnerReason  customAdapterSpinnerStyleAccount
                = new CustomAdapterSpinnerReason
                (getActivity(),R.layout.custom_spinner_drop_down,lstSpinnersStylePay);
        spnStylePay.setAdapter(customAdapterSpinnerStyleAccount);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentPayDisplay = inflater.inflate(R.layout.fragment_pay,null);
        addView();
        spinnerCustom();
        Click();
        return fragmentPayDisplay;

    }
    private void Confirm() {
        String url = "http://"+getString(R.string.IP)+"/QLCT/InsertReason.php";
        AddDataBase(url);

    }
    private void AddDataBase(String url) {
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("xem",response.toString());
                if (response.trim().equals("success")){
                    startActivity(new Intent(getContext(),Login.class));
                }
                else {
                    Toast.makeText(getContext(), " Fail ! ", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("xem",error.toString());
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map <String,String > params = new HashMap<>();
                params.put("Date",edtNgayPay.getText().toString());
                params.put("SpendingWith",edtWithWhom.getText().toString());
                params.put("Description",edtDescript.getText().toString());
                params.put("Trip",edtTrip.getText().toString());
                params.put("Location",edtLocation.getText().toString());
                params.put("Time",edtTimePay.getText().toString());
                return params;
            }
        };
        requestQueue.add(stringRequest);

    }
    private void Click() {
        edtNgayPay.setOnClickListener(this);
        edtTimePay.setOnClickListener(this);
        btnConfirm.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        if (view == edtNgayPay ){
            ChonNgay(edtNgayPay);
        }
        if (view == edtTimePay){
            ChonTime(edtTimePay);
        }
        if (view == btnConfirm ){
            Confirm();
        }
    }
}
