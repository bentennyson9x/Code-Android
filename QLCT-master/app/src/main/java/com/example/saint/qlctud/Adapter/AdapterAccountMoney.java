package com.example.saint.qlctud.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.saint.qlctud.R;
import com.example.saint.qlctud.accountmoney.AccountMoney;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class AdapterAccountMoney extends ArrayAdapter<AccountMoney> {
    Context context; int resource; @NonNull
    ArrayList<AccountMoney> objects;
    public AdapterAccountMoney(@NonNull Context context, int resource, @NonNull ArrayList<AccountMoney> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }
    private class viewHolder{
        TextView stylePay;
        TextView amount;
        ImageView Icon;

        public viewHolder() {
        }
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        viewHolder viewHolder = null;
        if (convertView == null){
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            convertView = layoutInflater.inflate(R.layout.account_money_custom_list,null);
            viewHolder = new viewHolder();
            viewHolder.Icon = convertView.findViewById(R.id.icon_accountMoney);
            viewHolder.amount = convertView.findViewById(R.id.amountAccountMoney);
            viewHolder.stylePay = convertView.findViewById(R.id.style_payAccountMoney);
        }
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        // can use formatter.format(//something number//);
        AccountMoney accountMoney = objects.get(position);
        viewHolder.Icon.setImageResource(accountMoney.getIcon());
        viewHolder.stylePay.setText(accountMoney.getTypeMoney());
        viewHolder.amount.setText(formatter.format(accountMoney.getAmountOfAccount())+" VNĐ");
        return convertView;
    }
}
