package com.example.g3.animation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class Tweened extends AppCompatActivity {
    private Button btnStart;
    private Button btnStop;
    private ImageView MoonImg;
    private Animation moonRevolution;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tweened);

        btnStart = (Button) findViewById(R.id.btnStart);
        btnStop = (Button) findViewById(R.id.btnStop);
        MoonImg = (ImageView) findViewById(R.id.moon);
        moonRevolution = AnimationUtils.loadAnimation(this, R.anim.revolution);
        moonRevolution.setRepeatCount(Animation.INFINITE);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MoonImg.startAnimation(moonRevolution);
            }
        });
        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MoonImg.clearAnimation();
            }
        });
    }
}
