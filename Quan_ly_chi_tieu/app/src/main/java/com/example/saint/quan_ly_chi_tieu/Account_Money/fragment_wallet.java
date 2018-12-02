package com.example.saint.quan_ly_chi_tieu.Account_Money;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.saint.quan_ly_chi_tieu.Account_Money.AccountMoney;
import com.example.saint.quan_ly_chi_tieu.Account_Money.Adapter_accountMoney;
import com.example.saint.quan_ly_chi_tieu.Account_Money.AddAccountMoney;
import com.example.saint.quan_ly_chi_tieu.R;

import java.math.BigDecimal;
import java.util.ArrayList;

public class fragment_wallet extends Fragment implements View.OnClickListener {
    ListView listTaiKhoan;
    ImageButton BtnAddAccountMoney;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View display = inflater.inflate(R.layout.fragment_wallet,container,false);
        AddButton(display);
        SetListTaiKhoan();
        Click();
        return display;
    }

    private void Click() {
    BtnAddAccountMoney.setOnClickListener(this);

    }


    private void SetListTaiKhoan() {
        ArrayList <AccountMoney> accountMonies = new ArrayList<>();
        accountMonies.add(new AccountMoney(new BigDecimal("1000000"),"Temp",
                "Temp","Temp",R.drawable.hambuger));
        ArrayAdapter <AccountMoney > accountMoneyArrayAdapter = new Adapter_accountMoney (getActivity(), R.layout.account_money_custom_list,accountMonies);
        listTaiKhoan.setAdapter(accountMoneyArrayAdapter);
    }

    private void AddButton(View Display) {
    listTaiKhoan = Display.findViewById(R.id.listTaiKhoan);
    BtnAddAccountMoney = Display.findViewById(R.id.addAccountWallet);
    }

    @Override
    public void onClick(View view) {
        if (view == BtnAddAccountMoney){
            Intent intent = new Intent(getActivity(),AddAccountMoney.class);
            startActivity(intent);
        }
    }
}
