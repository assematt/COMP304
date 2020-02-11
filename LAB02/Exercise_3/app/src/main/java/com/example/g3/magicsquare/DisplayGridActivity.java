package com.example.g3.magicsquare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Debug;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class DisplayGridActivity extends AppCompatActivity {
    GridView magicSquareGridView;
    String tag = "debug message goes here!";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_grid);

        Intent intent = getIntent();
        int magic_square_size = intent.getIntExtra("magic_square_size", 3);
        Log.d(tag, "magic_square_size: " +  magic_square_size);

        ArrayList<String> numbers = new ArrayList<String>();
        numbers = generateSquare(magic_square_size);

        magicSquareGridView = (GridView) findViewById( R.id.gv );
        magicSquareGridView.setNumColumns(magic_square_size);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, numbers);
        magicSquareGridView.setAdapter(adapter);

        magicSquareGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText( getApplicationContext(), ( (TextView) view).getText(), Toast.LENGTH_SHORT ).show();
            }
        });

        Toast.makeText( getApplicationContext(), "The sum of each row or column for Magic Square of " + magic_square_size
                + " is " + (magic_square_size*(magic_square_size*magic_square_size+1)/2), Toast.LENGTH_LONG).show();
    }

    protected ArrayList<String> generateSquare(int n)
    {
        int[][] magicSquare = new int[n][n];

        ArrayList<String> numbers = new ArrayList<String>();

        // Initialize position for 1
        int i = 0;
        int j = n / 2;

        for ( int k = 1; k <= n * n; ++k )
        {
            magicSquare[i][j] = k;

            i--;
            j++;

            if (k%n == 0)
            {
                i += 2;
                --j;
            }
            else
            {
                if (j==n)
                    j -= n;
                else if (i<0)
                    i += n;
            }
        }

        for( i = 0; i < n; i++ )
        {
            for( j = 0; j < n; j++ ) {
                numbers.add(Integer.toString(magicSquare[i][j]));
            }
        }

        return numbers;
    }
}
