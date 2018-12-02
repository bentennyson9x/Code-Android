package com.trantri.z79_lesson6_ex1_tritd4.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.trantri.z79_lesson6_ex1_tritd4.R;
import com.trantri.z79_lesson6_ex1_tritd4.utils.Songs;

import java.util.ArrayList;


public class SongsAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Songs> songsArrayList;

    public SongsAdapter(Context context, ArrayList<Songs> songsArrayList) {
        this.context = context;
        this.songsArrayList = songsArrayList;
    }

    @Override
    public int getCount() {
        return songsArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.iteam_song, null);
        TextView textViewName = (TextView) convertView.findViewById(R.id.tv_nameSong);
        TextView textViewTime = (TextView) convertView.findViewById(R.id.tv_timeSong);
        TextView textViewKichThuoc = (TextView) convertView.findViewById(R.id.tv_kichThuoc);
        TextView txtAuthor = convertView.findViewById(R.id.tv_nameAuthor);
        Songs songs = songsArrayList.get(position);
        textViewName.setText(songs.getNameSongs());
        textViewTime.setText(songs.getTimeSongs());
        textViewKichThuoc.setText(songs.getKichThuocSongs());
        txtAuthor.setText(songs.getAuthor());
        return convertView;
    }
}
