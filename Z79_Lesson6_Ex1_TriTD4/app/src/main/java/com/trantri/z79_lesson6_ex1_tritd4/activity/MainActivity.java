package com.trantri.z79_lesson6_ex1_tritd4.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.trantri.z79_lesson6_ex1_tritd4.R;
import com.trantri.z79_lesson6_ex1_tritd4.adapter.AuthorAdapter;
import com.trantri.z79_lesson6_ex1_tritd4.adapter.SongsAdapter;
import com.trantri.z79_lesson6_ex1_tritd4.utils.Authors;
import com.trantri.z79_lesson6_ex1_tritd4.utils.Songs;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ListView lvSongs;
    private RecyclerView recyclerView;
    private ArrayList<Songs> listSongs;
    private ArrayList<Authors> listAuthors;
    private SongsAdapter adapterSongs;
    private AuthorAdapter adapterAuthors;
    private ArrayList<Songs> listSongTemp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Innit();
        addView();
        getSupportActionBar().setTitle("Bài Hát");
    }

    void Innit() {
        lvSongs = findViewById(R.id.lv_song);
        recyclerView = findViewById(R.id.RecycleViewAuthor);

    }

    private void addView() {
        listSongs = new ArrayList<Songs>();
        listSongs.add(new Songs("Lạc Trôi", "3:45", "128KB", "Sơn Tùng M-TP"));
        listSongs.add(new Songs("Chạy Ngay Đi", "3:03", "128KB", "Sơn Tùng M-TP"));
        listSongs.add(new Songs("Nơi Này Có Anh", "4:05", "270KB", "Sơn Tùng M-TP"));
        listSongs.add(new Songs("Gửi Anh Và Cô Ấy", "3:15", "128KB", "Hương Tràm"));
        listSongs.add(new Songs("Chút Tình Đã Quên", "3:35", "128KB", "Hương Tràm"));
        listSongs.add(new Songs("Còn Gì Là Của Nhau", "3.05", "270KB", "Châu Khải Phong"));
        listSongs.add(new Songs("Anh Không Hối Tiếc", "4.15", "270KB", "Châu Khải Phong"));
        listSongs.add(new Songs("Ngắm Hoa Lệ Rơi", "3.25", "270KB", "Châu Khải Phong"));
        listSongs.add(new Songs("1 2 3 4", "3.25", "270KB", "Chi Dân"));
        listSongs.add(new Songs("Đâu Ai Ngờ", "2.55", "270KB", "Chi Dân"));
        listSongs.add(new Songs("Lỡ Như Anh Yêu Em", "3.35", "128KB", "Chi Dân"));
        listSongs.add(new Songs("Yêu Đơn Phương", "3.55", "128KB", "OnlyC, Karik"));
        listSongs.add(new Songs("Quan Trọng Là Thần Thái", "3.45", "128KB", "OnlyC, Karik"));
        listSongs.add(new Songs("Người Lạ Ơi", "2.45", "128KB", "OnlyC, Karik"));
        listSongs.add(new Songs("Chạm Khẽ Tim Anh Một Chút Thôi", "3.45", "128KB", "Noo Phước Thịnh"));
        listSongs.add(new Songs("Vị Quê Nhà", "3.45", "128KB", "Noo Phước Thịnh"));
        listSongs.add(new Songs("Dream Team", "3.45", "128KB", "Noo Phước Thịnh"));
        //list ca sĩ
        listAuthors = new ArrayList<Authors>();
        listAuthors.add(new Authors("Sơn Tùng M-TP"));
        listAuthors.add(new Authors("Hương Tràm"));
        listAuthors.add(new Authors("Châu Khải Phong"));
        listAuthors.add(new Authors("Chi Dân"));
        listAuthors.add(new Authors("OnlyC, Karik"));
        listAuthors.add(new Authors("Noo Phước Thịnh"));
        adapterSongs = new SongsAdapter(this, listSongs);
        lvSongs.setAdapter(adapterSongs);
        lvSongs.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, listSongs.get(position).getNameSongs() +"\n"+listSongs.get(position).getTimeSongs()+"\n"
                        +listSongs.get(position).getKichThuocSongs()+"\n"
                        +listSongs.get(position).getAuthor(), Toast.LENGTH_SHORT).show();
                return true;
            }
        });
        adapterAuthors = new AuthorAdapter(this, listAuthors);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(MainActivity.this,LinearLayoutManager.HORIZONTAL));
        recyclerView.setAdapter(adapterAuthors);
        adapterAuthors.setOnClick(new AuthorAdapter.OnCliCk() {
            @Override
            public void setOnClick(int postion) {
                capNhatListSong(listAuthors.get(postion).getName());
                adapterSongs.notifyDataSetChanged();
            }
        });

    }

    private void capNhatListSong(String nameOfAuthor) {
        listSongTemp = new ArrayList<>();
        for (Songs songs : listSongs) {
            if (nameOfAuthor.equals(songs.getAuthor())) {
                listSongTemp.add(songs);
            }
        }
        adapterSongs = new SongsAdapter(this, listSongTemp);
        lvSongs.setAdapter(adapterSongs);
        adapterSongs.notifyDataSetChanged();

    }
}

