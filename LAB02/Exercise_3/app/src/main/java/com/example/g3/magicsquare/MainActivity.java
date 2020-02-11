package com.example.g3.magicsquare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /** Called when the user taps the Send button */
    public void generateMagicSquare(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, DisplayGridActivity.class);
        EditText txtSquareSize = (EditText) findViewById(R.id.txtSquareSize);
        int magic_square_size = Integer.parseInt(txtSquareSize.getText().toString());
        intent.putExtra("magic_square_size", magic_square_size);
        startActivity(intent);
    }
}
