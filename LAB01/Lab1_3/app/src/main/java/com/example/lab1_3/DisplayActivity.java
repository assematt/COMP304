package com.example.lab1_3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class DisplayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        Intent intent = getIntent();
        String outputText = intent.getExtras().getString("output");
        TextView textView = (TextView) findViewById(R.id.display_text);
        textView.setText(outputText);
    }
}
