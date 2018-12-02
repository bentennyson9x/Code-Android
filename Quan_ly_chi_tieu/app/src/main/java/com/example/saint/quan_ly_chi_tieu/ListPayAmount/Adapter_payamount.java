package com.example.saint.quan_ly_chi_tieu.ListPayAmount;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.saint.quan_ly_chi_tieu.R;

import java.util.ArrayList;

public class Adapter_payamount extends ArrayAdapter <pay_amount> {
    Context context; int resource; ArrayList<pay_amount> objects;
    public Adapter_payamount(@NonNull Context context, int resource, @NonNull ArrayList<pay_amount> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }
    private class ViewHolder{
        ImageView icon_payamount_display ;
        TextView text_payamount_display ;
        TextView descripton;
        TextView amount ;
        ImageView icon_style_pay ;
        TextView text_style_pay ;

        public ViewHolder() {
        }
    }
        @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView==null){
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            convertView = layoutInflater.inflate(R.layout.custom_display_pay_amount, null); // chuyen doi view can hien thi
            viewHolder = new ViewHolder();

            viewHolder.icon_payamount_display = convertView.findViewById(R.id.icon_display_payAmount_reason);
            viewHolder.text_payamount_display = convertView.findViewById(R.id.text_display_payAmount_reason);
            viewHolder.descripton = convertView.findViewById(R.id.description_display_payAmount_reason);
            viewHolder.amount = convertView.findViewById(R.id.amount_display_payAmount_reason);
            viewHolder.icon_style_pay = convertView.findViewById(R.id.IconStylePay_display_payAmount_reason);
            viewHolder.text_style_pay = convertView.findViewById(R.id.TextStylePay_display_payAmount_reason);
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) convertView.getTag();

        }
        pay_amount pay_amount = objects.get(position);
        viewHolder.icon_payamount_display.setImageResource(pay_amount.getIcon_reason());
        viewHolder.icon_style_pay.setImageResource(pay_amount.getIcon_stylePay());
        viewHolder.text_payamount_display.setText(pay_amount.getPay_for());
        viewHolder.text_style_pay.setText(pay_amount.getStyle_pay());
        viewHolder.descripton.setText(pay_amount.getDescription());
        viewHolder.amount.setText( pay_amount.getAmount().toString());
        return convertView;
    }
}
