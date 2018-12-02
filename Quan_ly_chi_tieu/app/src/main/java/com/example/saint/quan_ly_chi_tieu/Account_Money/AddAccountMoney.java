package com.example.saint.quan_ly_chi_tieu.Account_Money;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.example.saint.quan_ly_chi_tieu.CustomSpinner.Custom_adapter_spinner_reason;
import com.example.saint.quan_ly_chi_tieu.CustomSpinner.Custom_adapter_spinner_style_account;
import com.example.saint.quan_ly_chi_tieu.CustomSpinner.Custom_spinner;
import com.example.saint.quan_ly_chi_tieu.Home;
import com.example.saint.quan_ly_chi_tieu.R;
import com.example.saint.quan_ly_chi_tieu.fragment_finance;
import com.example.saint.quan_ly_chi_tieu.fragment_home;
import com.example.saint.quan_ly_chi_tieu.fragment_plus;
import com.example.saint.quan_ly_chi_tieu.fragment_setting;

import java.math.BigDecimal;
import java.util.ArrayList;

public class AddAccountMoney extends AppCompatActivity implements View.OnClickListener {
    Spinner spnStyleAccount;
    ImageButton imgbtnSave;
    Button btnSave;
    ImageButton btnCancle;
    EditText Desciption_name_of_account;
    View viewInflateActivityHome;
    EditText inputAmountAccountMoney;
    ArrayList <AccountMoney> AccountMoneyList;
    ArrayList <Custom_spinner > custom_spinners ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_account_money);
        AddButton();
        CustomSpinner();
        Click();

    }


    private void Click() {
        btnSave.setOnClickListener(this);
        imgbtnSave.setOnClickListener(this);
        btnCancle.setOnClickListener(this);
    }

    private void CustomSpinner() {
    custom_spinners = new ArrayList();
        custom_spinners.add(new Custom_spinner(R.drawable.atm,"ATM"));
        custom_spinners.add(new Custom_spinner(R.drawable.wallet,"Wallet"));
        custom_spinners.add(new Custom_spinner(R.drawable.company,"Company"));
        custom_spinners.add(new Custom_spinner(R.drawable.salary,"Salary"));
        Custom_adapter_spinner_style_account spinnerspnStyleAccount = new Custom_adapter_spinner_style_account (this,R.layout.custom_spinner_dropdown,custom_spinners);
        spnStyleAccount.setAdapter(spinnerspnStyleAccount);
    }

    private void AddButton() {
    spnStyleAccount = findViewById(R.id.typeAccountMoney);
    imgbtnSave = findViewById(R.id.addAccountWallet);
    btnSave = findViewById(R.id.SaveAccountMoneyBtn);
    btnCancle = findViewById(R.id.deninedAccountMoney);
    Desciption_name_of_account = findViewById(R.id.nameOfAccount);
    inputAmountAccountMoney = findViewById(R.id.input_amount_account_money);
    }

    @Override
    public void onClick(View view) {
        if ( view ==btnSave || view == imgbtnSave ) {
                AddAccount();


            }
         if (view == btnCancle){
             Intent intent = new Intent(this,Home.class);
             startActivity(intent);
         }

        }

    private void AddAccount() {
        int resourceIcon =  custom_spinners.get(spnStyleAccount.getSelectedItemPosition()).resource_icon;
        AccountMoneyList.add(new AccountMoney(new BigDecimal(inputAmountAccountMoney.getText().toString())
                ,Desciption_name_of_account.getText().toString(),spnStyleAccount.getSelectedItem().toString(),
                null,resourceIcon));
        Intent intent = new Intent(this,Home.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("ListAccountMoney",AccountMoneyList);
        intent.putExtra("BundleAccountMoney",bundle);
        startActivity(intent);
    }
}
