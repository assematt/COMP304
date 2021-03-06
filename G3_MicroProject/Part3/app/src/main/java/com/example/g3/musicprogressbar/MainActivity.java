package com.example.g3.musicprogressbar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity
{
    String musicToPlay;
    String[] values = new String[] { "Song 1", "Song 2", "Song 3"};
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView songList = (ListView) findViewById(R.id.songList);
        ListAdapter adapter = new ArrayAdapter(this, R.layout.custom_layout, R.id.hightLightText, values);
        songList.setAdapter(adapter);

        songList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                view.setSelected(true);
                musicToPlay = values[position];
            }
        });
    }

    public void playMusic(View view)
    {
        Intent intent = new Intent(this, GuessingGameActivity.class);
        intent.putExtra("musicToPlay", musicToPlay);
        startActivity(intent);
    }
}
