package com.example.g3.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    public static String TAG = "WhatToDraw";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void DrawCar(View view)
    {
        Intent intent = new Intent(this, CanvasActivity.class);
        intent.putExtra(TAG, "Car");
        startActivity(intent);
    }

    public void DrawFlag(View view)
    {
        Intent intent = new Intent(this, CanvasActivity.class);
        intent.putExtra(TAG, "Flag");
        startActivity(intent);
    }

    public void DrawArcs(View view)
    {
        Intent intent = new Intent(this, CanvasActivity.class);
        intent.putExtra(TAG, "Arcs");
        startActivity(intent);
    }
}
