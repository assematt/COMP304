package com.example.g3.animation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class Tweened extends AppCompatActivity {
    private Button btn;
    private ImageView MoonImg;
    private Animation moonRevolution;
    private Boolean bool = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tweened);

        btn = (Button) findViewById(R.id.button);
        MoonImg = (ImageView) findViewById(R.id.moon);
        moonRevolution = AnimationUtils.loadAnimation(this, R.anim.revolution);
        moonRevolution.setRepeatCount(Animation.INFINITE);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(bool){
                    btn.setText(getResources().getString(R.string.stopBtn));
                    btn.setBackgroundColor(getResources().getColor(R.color.stopBtn));
                    MoonImg.startAnimation(moonRevolution);
                    bool = !bool;
                } else{
                    btn.setText(getResources().getString(R.string.startBtn));
                    btn.setBackgroundColor(getResources().getColor(R.color.startBtn));
                    MoonImg.clearAnimation();
                    bool = !bool;
                } //  ifElse
            } //  onClick
        });
    }
}
