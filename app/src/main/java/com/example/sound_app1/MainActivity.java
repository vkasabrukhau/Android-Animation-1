package com.example.sound_app1;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    ImageView image1;
    Integer cursor1;
    MediaPlayer player1;
    ImageButton backs, play, forward;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        player1 = MediaPlayer.create(this, R.raw.song);
        cursor1 = 1;
        backs = findViewById(R.id.backs);
        play = findViewById(R.id.play);
        forward = findViewById(R.id.next);
        image1 = findViewById(R.id.imageView);

        backs.setOnClickListener(this);
        play.setOnClickListener(this);
        forward.setOnClickListener(this);

        Animation animation1 = AnimationUtils.loadAnimation(this,R.anim.animation1);
        new Thread(() -> {
            while(true){
                image1.startAnimation(animation1);
                try {
                    Thread.sleep(4000);
                } catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.backs:
                switch(--cursor1){
                    case 1:
                        player1.stop();
                        player1 = MediaPlayer.create(this, R.raw.song);
                        player1.start();
                        break;
                    case 2:
                        player1.stop();
                        player1 = MediaPlayer.create(this, R.raw.song2);
                        player1.start();
                        break;
                    default:
                        cursor1 = 2;
                        player1.stop();
                        player1 = MediaPlayer.create(this, R.raw.song2);
                        player1.start();
                }
                break;

            case R.id.play:
                if(player1.isPlaying()){
                    player1.pause();
                }
                else{
                    player1.start();
                }
                break;
            case R.id.next:
                switch(++cursor1){
                    case 1:
                        player1.stop();
                        player1 = MediaPlayer.create(this, R.raw.song);
                        player1.start();
                        break;
                    case 2:
                        player1.stop();
                        player1 = MediaPlayer.create(this, R.raw.song2);
                        player1.start();
                        break;
                    default:
                        cursor1 = 1;
                        player1.stop();
                        player1 = MediaPlayer.create(this, R.raw.song);
                        player1.start();
                        break;
                }
                break;
        }
    }
}