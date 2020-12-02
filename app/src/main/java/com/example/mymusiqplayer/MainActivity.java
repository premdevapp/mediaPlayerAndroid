package com.example.mymusiqplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    //make use of Mediaplayer
    private MediaPlayer mediaPlayer;
    private Button button;
    private SeekBar seekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //instantiate mediaplayer
        mediaPlayer = new MediaPlayer();

        // button
        button = findViewById(R.id.button);

        // seekbar
        seekBar = findViewById(R.id.seekBar);

/*
        //media trough cloud
        try {
            mediaPlayer.setDataSource("https://www.tamilmp3plus.com/songs/mp3/sidsiri/Mei%20Nigara-Masstamilan.In.mp3");
        } catch (IOException e) {
            e.printStackTrace();
        }

        //calback for onprepared for cloud


        MediaPlayer.OnPreparedListener preparedListner = new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(final MediaPlayer mp) {
                seekBar.setMax(mp.getDuration());
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //check player status
                        if (mp.isPlaying()){
                            //user option to start again

                                mp.pause();
                                button.setText(R.string.play);

                        }else {

                                mp.start();
                                button.setText(R.string.pause);

                        }
                    }
                });
            }
        };

        //prepare listner for cloud

        mediaPlayer.setOnPreparedListener(preparedListner);

        // call async for cloud
        mediaPlayer.prepareAsync();

        */


        //play local media

        //create mediaplayer
        mediaPlayer = MediaPlayer.create(this, R.raw.beat_of_master);

        seekBar.setMax(mediaPlayer.getDuration());

        //local onClickListner
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //check player status
                if (mediaPlayer.isPlaying()){
                    //user option to start again
                    pauseMusic();
                }else {
                playMusic();
                }
            }
        });

        // addOn completion listner
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                int duration = mp.getDuration();
                // Toast.makeText(MainActivity.this, String.valueOf((duration/1000)/60), Toast.LENGTH_LONG).show();
                Log.d("Song", "onCompletion: " + String.valueOf((duration/1000)/60));
            }
        });

        //seekbar works

        //seekBar.setMax(mediaPlayer.getDuration());

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(fromUser){
                    mediaPlayer.seekTo(progress);
                }

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }


    // a method to pause local
    private void pauseMusic(){
        if(mediaPlayer != null){
            mediaPlayer.pause();
            button.setText(R.string.play);
        }
    }
    // a method to play local
    private void playMusic(){
        if(mediaPlayer != null){
            mediaPlayer.start();
            button.setText(R.string.pause);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mediaPlayer != null){
            mediaPlayer.pause();
            mediaPlayer.release();
        }
    }
}