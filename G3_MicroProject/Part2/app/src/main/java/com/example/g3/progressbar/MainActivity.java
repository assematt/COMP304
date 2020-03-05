package com.example.g3.progressbar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TimePicker;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "ProgressBar";

    NumberPicker minutePicker;
    NumberPicker secondsPicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        minutePicker = (NumberPicker) findViewById( R.id.minutePicker );
        minutePicker.setMaxValue(60);
        minutePicker.setMinValue(0);

        secondsPicker = (NumberPicker) findViewById( R.id.secondsPicker );
        secondsPicker.setMaxValue(59);
        secondsPicker.setMinValue(0);
    }

    public void setTime(View view)
    {
        int minute = minutePicker.getValue();
        int seconds = secondsPicker.getValue();

        int timeToPlay = ( minute * 60 ) + seconds;
        Log.d( TAG,"timeToPlay: " + timeToPlay );

        Intent intent = new Intent(this, GuessingGame.class);
        intent.putExtra("timeToPlay", timeToPlay);
        startActivity(intent);
    }
}
