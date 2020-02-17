package com.example.g3.exercise_2_2;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button start_btn = (Button) findViewById(R.id.btn_start);
        Button stop_btn = (Button) findViewById(R.id.btn_stop);
        ImageView cat_walk_animation = (ImageView) findViewById(R.id.imageView);

        // set the cat_walk animation list to the image view
        cat_walk_animation.setImageResource(R.drawable.cat_walk);

        // create the frame-by-frame animation
        final AnimationDrawable cat_walk = (AnimationDrawable) cat_walk_animation.getDrawable();

        // start animation when start button is clicked
        start_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cat_walk.start();
            }
        });

        // stop animation when stop button is clicked
        stop_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cat_walk.stop();
            }
        });
    }
}
