package com.example.lenovo.demodisplaygame;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    GridView gvAnimal;
    ArrayList<Animal> listanimal;
    ImageAnimal adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        add();

        adapter = new ImageAnimal(this, R.layout.row_animal, listanimal);

        gvAnimal.setAdapter(adapter);

        Animation scale = AnimationUtils.loadAnimation(this,R.anim.anim_scale0);
        gvAnimal.startAnimation(scale);


        Thread bg = new Thread(){
            @Override
            public void run() {
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finally {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            MediaPlayer mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.bear);
                            mediaPlayer.start();
                        }
                    });
                }
                super.run();
            }
        };
        bg.start();
        MediaPlayer m2 = MediaPlayer.create(this,R.raw.congau);
        m2.start();

        final Thread begin = new Thread(){
            @Override
            public void run() {
                try {
                    sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finally {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Animation scaleon = AnimationUtils.loadAnimation(MainActivity.this,R.anim.anim_scale1);
                            gvAnimal.startAnimation(scaleon);
                        }
                    });
                }
                super.run();
            }
        };
        begin.start();


        /**gvAnimal.setVisibility(View.INVISIBLE);

        final Thread begin = new Thread(){
            @Override
            public void run() {
                try {
                    sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finally {
                    gvAnimal.setVisibility(View.VISIBLE);

                }
            }
        };
        begin.start(); **/

        gvAnimal.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(i==5){
                    Toast.makeText(MainActivity.this,""+i,Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(MainActivity.this,"Nothing",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void add() {
        gvAnimal = findViewById(R.id.gv_Animal);
        listanimal = new ArrayList<>();

        listanimal.add(new Animal(R.drawable.ic_ant));
        listanimal.add(new Animal(R.drawable.ic_bat));
        listanimal.add(new Animal(R.drawable.ic_butterfly));
        listanimal.add(new Animal(R.drawable.ic_cat));
        listanimal.add(new Animal(R.drawable.ic_chicken));
        listanimal.add(new Animal(R.drawable.ic_dog));
        listanimal.add(new Animal(R.drawable.ic_dug));
        listanimal.add(new Animal(R.drawable.ic_fish));
        listanimal.add(new Animal(R.drawable.ic_horse));
        listanimal.add(new Animal(R.drawable.ic_lion));
        listanimal.add(new Animal(R.drawable.ic_lobster));
        listanimal.add(new Animal(R.drawable.ic_octopus));
        listanimal.add(new Animal(R.drawable.ic_octopus));
        listanimal.add(new Animal(R.drawable.ic_seahorse));
        listanimal.add(new Animal(R.drawable.ic_sheep));
        listanimal.add(new Animal(R.drawable.ic_shrimp));
    }
}
