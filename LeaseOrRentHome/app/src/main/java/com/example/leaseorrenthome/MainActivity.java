package com.example.leaseorrenthome;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_home);
    }

    public void LoadHomeTypeActivity(View view)
    {
        Intent intent = new Intent(this, HomeTypeActivity.class);
        startActivity(intent);
    }
}
