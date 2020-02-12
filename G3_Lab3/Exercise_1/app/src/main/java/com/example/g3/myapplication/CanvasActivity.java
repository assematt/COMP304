package com.example.g3.myapplication;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class CanvasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        String wordToDecode = intent.getStringExtra(MainActivity.TAG);

        DrawableCanvas drawableCanvas = new DrawableCanvas(this);
        drawableCanvas.whatToDraw = wordToDecode;
        setContentView(drawableCanvas);
    }
}
