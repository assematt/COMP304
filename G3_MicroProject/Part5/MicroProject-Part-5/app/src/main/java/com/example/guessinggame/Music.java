package com.example.guessinggame;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Music extends AppCompatActivity {
  MediaPlayer mediaPlayer;
  Boolean isRunning = false;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.music);
    Button btnSong1, btnSong2, btnSong3, btnStopSong;
    btnSong1 = (Button) findViewById(R.id.songBtn1);
    btnSong2 = (Button) findViewById(R.id.songBtn2);
    btnSong3 = (Button) findViewById(R.id.songBtn3);
    btnStopSong = (Button) findViewById(R.id.btnStopSong);
    btnSong1.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if(isRunning){  mediaPlayer.stop(); }
        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.song1);
        mediaPlayer.start();
        isRunning = true;
      } //  onClick
    });
    btnSong2.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if(isRunning){  mediaPlayer.stop(); }
        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.song2);
        mediaPlayer.start();
        isRunning = true;
      } //  onClick
    });
    btnSong3.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if(isRunning){  mediaPlayer.stop(); }
        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.song3);
        mediaPlayer.start();
        isRunning = true;
      } //  onClick
    });
    btnStopSong.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if(isRunning){
          mediaPlayer.stop();
          isRunning = false;
        } //  ifSongIsRunning
      } //  onClick
    });
  } //  onCreate
} //  classEnd
