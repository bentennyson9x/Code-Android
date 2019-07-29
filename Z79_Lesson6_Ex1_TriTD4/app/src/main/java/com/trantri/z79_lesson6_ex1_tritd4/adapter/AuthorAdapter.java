package com.trantri.z79_lesson6_ex1_tritd4.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.trantri.z79_lesson6_ex1_tritd4.R;
import com.trantri.z79_lesson6_ex1_tritd4.utils.Authors;
import com.trantri.z79_lesson6_ex1_tritd4.utils.Songs;

import java.util.ArrayList;

public class AuthorAdapter extends RecyclerView.Adapter<AuthorAdapter.IteamViewHolder> {
    OnCliCk onCliCk;
    private Context context;
    private ArrayList<Authors> arrayList;
    private ArrayList<Songs> listSongTemp;

    public AuthorAdapter(Context context, ArrayList<Authors> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    public void setOnClick(OnCliCk onClick) {
        this.onCliCk = onClick;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public ArrayList<Authors> getArrayList() {
        return arrayList;
    }

    public void setArrayList(ArrayList<Authors> arrayList) {
        this.arrayList = arrayList;
    }

    public ArrayList<Songs> getListSongTemp() {
        return listSongTemp;
    }

    public void setListSongTemp(ArrayList<Songs> listSongTemp) {
        this.listSongTemp = listSongTemp;
    }

    @NonNull
    @Override
    public IteamViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.iteam_author, parent, false);
        IteamViewHolder iteamViewHolder = new IteamViewHolder(v);

        return iteamViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull IteamViewHolder holder, int position) {
        Authors authors = arrayList.get(position);
        holder.textView.setText(authors.getName());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public interface OnCliCk { // *
        void setOnClick(int postion);
    }

    public class IteamViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;

        public IteamViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tv_authors);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onCliCk.setOnClick(getAdapterPosition());
                }
            });
        }

    }
}
