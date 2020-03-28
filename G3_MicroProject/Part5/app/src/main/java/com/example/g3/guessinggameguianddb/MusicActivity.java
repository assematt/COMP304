package com.example.g3.guessinggameguianddb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class MusicActivity extends AppCompatActivity
{
    private Button forwardButton, backwardButton, pauseButton, playButton;
    private MediaPlayer mPlayer;
    private TextView songName, startTime, songTime, kombo;
    private SeekBar songPrgs;
    private static int oTime = 0, sTime = 0, eTime = 0, fTime = 5000, bTime = 5000;
    private Handler hdlr = new Handler();

    String musicToPlay = null;
    String[] values = new String[]{"Song 1", "Song 2", "Song 3"};

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);

        backwardButton = (Button) findViewById(R.id.buttonBack);
        forwardButton = (Button) findViewById(R.id.buttonForward);
        playButton = (Button) findViewById(R.id.buttonPlay);
        pauseButton = (Button) findViewById(R.id.buttonPause);
        songName = (TextView) findViewById(R.id.txtSongName);
        startTime = (TextView) findViewById(R.id.txtStartTime);
        songTime = (TextView) findViewById(R.id.txtSongTime);
        songPrgs = (SeekBar) findViewById(R.id.sBar);
        songPrgs.setClickable(false);
        pauseButton.setEnabled(false);

        Intent intent = getIntent();
        musicToPlay = intent.getStringExtra("musicToPlay");

        ListView songList = (ListView) findViewById(R.id.songList);
        ListAdapter adapter = new ArrayAdapter(this, R.layout.custom_layout, R.id.hightLightText, values);
        songList.setAdapter(adapter);
        songList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                view.setSelected(true);
                musicToPlay = values[position];

                stopMusic();
                setMusicPlayer(musicToPlay);
            }
        });

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playMusic();
            }
        });

        pauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pauseMusic();
            }
        });

        forwardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((sTime + fTime) <= eTime) {
                    sTime = sTime + fTime;
                    mPlayer.seekTo(sTime);
                } else {
                    Toast.makeText(getApplicationContext(),
                            "5 seconds forward not allowed", Toast.LENGTH_SHORT).show();
                }
                if (!playButton.isEnabled()) {
                    playButton.setEnabled(true);
                }
            }
        });

        backwardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((sTime - bTime) > 0) {
                    sTime = sTime - bTime;
                    mPlayer.seekTo(sTime);
                } else {
                    Toast.makeText(getApplicationContext(),
                            "5 seconds backward not allowed", Toast.LENGTH_SHORT).show();
                }
                if (!playButton.isEnabled()) {
                    playButton.setEnabled(true);
                }
            }
        });
    }

    private void playMusic()
    {
        if( mPlayer != null )
        {
            Toast.makeText(MusicActivity.this, "Song playing", Toast.LENGTH_SHORT).show();
            mPlayer.start();
            eTime = mPlayer.getDuration();
            sTime = mPlayer.getCurrentPosition();
            if (oTime == 0) {
                songPrgs.setMax(eTime);
                oTime = 1;
            }
            songTime.setText(String.format("%d min, %d sec",
                    TimeUnit.MILLISECONDS.toMinutes(eTime),
                    TimeUnit.MILLISECONDS.toSeconds(eTime) -
                            TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(eTime))));
            startTime.setText(String.format("%d min, %d sec",
                    TimeUnit.MILLISECONDS.toMinutes(sTime),
                    TimeUnit.MILLISECONDS.toSeconds(sTime) -
                            TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(sTime))));
            songPrgs.setProgress(sTime);
            hdlr.postDelayed(UpdateSongTime, 100);
            pauseButton.setEnabled(true);
            playButton.setEnabled(false);
        }
    }

    private void pauseMusic() {
        mPlayer.pause();
        pauseButton.setEnabled(false);
        playButton.setEnabled(true);
        Toast.makeText(getApplicationContext(), "Pause", Toast.LENGTH_SHORT).show();
    }

    private void stopMusic()
    {
        Log.d("stop", "mPlayer: " + mPlayer);
        if ( mPlayer != null )
        {
            oTime = 0;
            sTime = 0;
            eTime = 0;
            fTime = 5000;
            bTime = 5000;

            Log.d("stop1", "mPlayer: " + mPlayer);
            mPlayer.stop();
            mPlayer.release();
            mPlayer = null;
            Log.d("stop2", "mPlayer: " + mPlayer);
        }
    }

    private void setMusicPlayer(String musicToPlay)
    {
        Log.d("Test", "musicToPlay: " + musicToPlay);
        if( musicToPlay != null )
        {
            if(musicToPlay.equals("Song 1"))
            {
                mPlayer = MediaPlayer.create(this, R.raw.bensound_summer);
                songName.setText("Summer");
            }

            if(musicToPlay.equals( "Song 2"))
            {
                mPlayer = MediaPlayer.create(this, R.raw.bensound_ukulele);
                songName.setText("Ukulele");
            }

            if(musicToPlay.equals( "Song 3"))
            {
                mPlayer = MediaPlayer.create(this, R.raw.bensound_creativeminds);
                songName.setText("Creative Minds");
            }

            playMusic();
        }
    }

    private void getTrackInfo(Uri audioFileUri) {
        MediaMetadataRetriever metaRetriever = new MediaMetadataRetriever();
        metaRetriever.setDataSource(getRealPathFromURI(audioFileUri));
        String artist = metaRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST);
        String title = metaRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE);
    }

    private String getRealPathFromURI(Uri uri) {
        File myFile = new File(uri.getPath().toString());
        String s = myFile.getAbsolutePath();
        return s;
    }

    private Runnable UpdateSongTime = new Runnable() {
        @Override
        public void run() {
            sTime = mPlayer.getCurrentPosition();
            startTime.setText(String.format("%d min, %d sec", TimeUnit.MILLISECONDS.toMinutes(sTime),
                    TimeUnit.MILLISECONDS.toSeconds(sTime) -
                            TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(sTime))));
            songPrgs.setProgress(sTime);
            hdlr.postDelayed(this, 100);
        }
    };
}
