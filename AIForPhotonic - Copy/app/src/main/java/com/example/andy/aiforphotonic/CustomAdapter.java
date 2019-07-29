package com.example.andy.aiforphotonic;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends ArrayAdapter <Chips> {
    Context mContext;
    int mLayout;
    ArrayList<Chips> mlist;

    public CustomAdapter( Context context, int resource, List<Chips> objects) {
        super(context, resource, objects);
        mContext = context;
        mLayout = resource;
        mlist = (ArrayList<Chips>) objects;
    }

    private class ViewHolder{
        CheckBox [] gate;

        public ViewHolder() {
            gate = new CheckBox[17];

        }
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView==null){
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(R.layout.item_chips,null);
            viewHolder = new ViewHolder();
            viewHolder.gate[1] = convertView.findViewById(R.id.cong1);
            viewHolder.gate[2] = convertView.findViewById(R.id.cong2);
            viewHolder.gate[3] = convertView.findViewById(R.id.cong3);
            viewHolder.gate[4] = convertView.findViewById(R.id.cong4);
            viewHolder.gate[5] = convertView.findViewById(R.id.cong5);
            viewHolder.gate[6] = convertView.findViewById(R.id.cong6);
            viewHolder.gate[7] = convertView.findViewById(R.id.cong7);
            viewHolder.gate[8] = convertView.findViewById(R.id.cong8);
            viewHolder.gate[9] = convertView.findViewById(R.id.cong9);
            viewHolder.gate[10] = convertView.findViewById(R.id.cong10);
            viewHolder.gate[11] = convertView.findViewById(R.id.cong11);
            viewHolder.gate[12] = convertView.findViewById(R.id.cong12);
            viewHolder.gate[13] = convertView.findViewById(R.id.cong13);
            viewHolder.gate[14] = convertView.findViewById(R.id.cong14);
            viewHolder.gate[15] = convertView.findViewById(R.id.cong15);
            viewHolder.gate[16] = convertView.findViewById(R.id.cong16);
            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Chips chips = mlist.get(position);
        int count=1;
        for (int i=1;i<=16;i++){
               for (int j=1;j<=4;j++){
                   chips.getCucTren().getGateName()[j].setCheckBox(viewHolder.gate[i]);
                   Log.e("xem",chips.getCucTren().getGateName()[j].nameGate+"   "+ String.valueOf(chips.getCucTren().getGateName()[j].getCheckBox()));
                   viewHolder.gate[i].setText(String.valueOf(chips.getCucTren().getGateName()[j].getNameGate()));
                   i++;
               }
                for (int j=1;j<=4;j++){
                    chips.getCucTrai().getGateName()[j].setCheckBox(viewHolder.gate[i]);
                    Log.e("xem",chips.getCucTrai().getGateName()[j].nameGate+"   "+  String.valueOf(chips.getCucTrai().getGateName()[j].getCheckBox()));
                   viewHolder.gate[i].setText(String.valueOf(chips.getCucTrai().getGateName()[j].getNameGate()));
                   i++;
               }
                for (int j=1;j<=4;j++){
                    chips.getCucPhai().getGateName()[j].setCheckBox(viewHolder.gate[i]);
                    Log.e("xem",chips.getCucPhai().getGateName()[j].nameGate+"   "+  String.valueOf(chips.getCucPhai().getGateName()[j].getCheckBox()));
                   viewHolder.gate[i].setText(String.valueOf(chips.getCucPhai().getGateName()[j].getNameGate()));
                   i++;
               }
                for (int j=1;j<=4;j++){
                    chips.getCucDuoi().getGateName()[j].setCheckBox(viewHolder.gate[i]);
                    Log.e("xem",chips.getCucDuoi().getGateName()[j].nameGate+"   "+  String.valueOf(chips.getCucDuoi().getGateName()[j].getCheckBox()));
                   viewHolder.gate[i].setText(String.valueOf(chips.getCucDuoi().getGateName()[j].getNameGate()));
                   i++;
               }

        }
        //TRUYEN DEPARTMENT SANg
        return convertView;
    }
}
