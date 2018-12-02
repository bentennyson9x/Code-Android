package com.example.saint.qlctud.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
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
import com.example.saint.qlctud.Adapter.CustomAdapterSpinnerReason;
import com.example.saint.qlctud.customspinner.CustomSpinner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AddAccount extends AppCompatActivity implements View.OnClickListener {
    Spinner spnStyleAccount;
    ImageButton imgbtnSave;
    Button btnSave;
    ImageButton btnCancle;
    EditText edtNameOfAccount;
    EditText edtInputAmountAccountMoney;
    ArrayList <CustomSpinner> CustomSpinners ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_account);
        AddView();
        CustomSpinner();
        Click();
    }
    private void Click() {
        btnSave.setOnClickListener(this);
        imgbtnSave.setOnClickListener(this);
        btnCancle.setOnClickListener(this);
    }

    private void CustomSpinner() {
        CustomSpinners = new ArrayList();
        CustomSpinners.add(new CustomSpinner(R.drawable.atm,"ATM"));
        CustomSpinners.add(new CustomSpinner(R.drawable.wallet,"Wallet"));
        CustomSpinners.add(new CustomSpinner(R.drawable.company,"Company"));
        CustomSpinners.add(new CustomSpinner(R.drawable.salary,"Salary"));
        CustomAdapterSpinnerReason spinnerspnStyleAccount = new CustomAdapterSpinnerReason (this,R.layout.custom_spinner_drop_down,CustomSpinners);
        spnStyleAccount.setAdapter(spinnerspnStyleAccount);
    }

    private void AddView() {
        spnStyleAccount = findViewById(R.id.typeAccountMoney);
        imgbtnSave = findViewById(R.id.addAccountWallet);
        btnSave = findViewById(R.id.SaveAccountMoneyBtn);
        btnCancle = findViewById(R.id.deninedAccountMoney);
        edtNameOfAccount = findViewById(R.id.nameOfAccount);
        edtInputAmountAccountMoney = findViewById(R.id.input_amount_account_money);
    }

    @Override
    public void onClick(View view) {
        if ( view ==btnSave || view == imgbtnSave ) {
            String url = "http://"+getString(R.string.IP)+"/QLCT/InsertAccount.php";
            AddAccount(url  );


        }
        if (view == btnCancle){
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
        }

    }

    private void AddAccount(String url) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.trim().equals("success")) {
                    Intent intent = new Intent(AddAccount.this,MainActivity.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(AddAccount.this, "Fail!!", Toast.LENGTH_SHORT).show();
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
                Map<String,String> params = new HashMap<>();
                int posOfStyleAccount = spnStyleAccount.getSelectedItemPosition();
                params.put("NameAccount",edtNameOfAccount.getText().toString().trim());
                params.put("TypeAccount",CustomSpinners.get(posOfStyleAccount).getText().trim());
                params.put("CustomerID", (String) DataSingleton.getData("UserID"));
                params.put("AccountAmount",edtInputAmountAccountMoney.getText().toString().trim());
                return params;
            }
        };
        requestQueue.add(request);
    }
}
