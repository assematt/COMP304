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

    /** Called when the user taps the Generate button */
    public void generateMagicSquare(View view) {
        // Pass the Magic Square Size from Main Activity to the DisplayGridActivity
        Intent intent = new Intent(this, DisplayGridActivity.class);
        EditText txtSquareSize = (EditText) findViewById(R.id.txtSquareSize);
        int magic_square_size = Integer.parseInt(txtSquareSize.getText().toString()); // get the input from the EditText field and parse it to integer
        intent.putExtra("magic_square_size", magic_square_size);
        startActivity(intent);
    }
}
