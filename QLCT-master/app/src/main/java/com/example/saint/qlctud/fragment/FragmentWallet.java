package com.example.saint.qlctud.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.saint.qlctud.Adapter.AdapterAccountMoney;
import com.example.saint.qlctud.R;
import com.example.saint.qlctud.accountmoney.AccountMoney;
import com.example.saint.qlctud.activity.AddAccount;

import java.math.BigDecimal;
import java.util.ArrayList;

public class FragmentWallet extends Fragment {
    View displayFragment;
    ListView listTaiKhoan;
    ImageButton BtnAddAccountMoney;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        displayFragment = inflater.inflate(R.layout.fragment_wallet,null);
        setHasOptionsMenu(true);
        AddButton(displayFragment);
        SetListTaiKhoan();
        return displayFragment;
    }
    private void SetListTaiKhoan() {
        ArrayList <AccountMoney> accountMonies = new ArrayList<>();
        accountMonies.add(new AccountMoney(new BigDecimal("100000000"),"Temp",
                "Temp","Temp",R.drawable.hambuger));
        ArrayAdapter <AccountMoney > accountMoneyArrayAdapter = new AdapterAccountMoney(getActivity(), R.layout.account_money_custom_list,accountMonies);
        listTaiKhoan.setAdapter(accountMoneyArrayAdapter);
    }

    private void AddButton(View Display) {
        listTaiKhoan = Display.findViewById(R.id.listTaiKhoan);
       // BtnAddAccountMoney = Display.findViewById(R.id.addAccountWallet);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.walletMenuAddAccount){
            startActivity(new Intent(getActivity(), AddAccount.class));
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.wallet_menu,menu);
    }
}
