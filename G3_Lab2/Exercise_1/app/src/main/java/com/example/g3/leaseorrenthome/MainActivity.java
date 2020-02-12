package com.example.g3.leaseorrenthome;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

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
