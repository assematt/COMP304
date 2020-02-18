package com.example.assignment3_2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class MainActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ArrayList<String> exercise = new ArrayList<>();
        exercise.add("Exercise 1");
        exercise.add("Exercise 2");
        exercise.add("Exercise 3");



        ListView listView = (ListView) findViewById(R.id.exercise_list);


        ArrayAdapter adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, exercise);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                String entry = exercise.get(position);
                Log.d("TESSSST",entry);
                Intent intent = new Intent(MainActivity.this, DrawLines.class);
                intent.putExtra(EXTRA_MESSAGE, entry);
                startActivity(intent);
            }
        });

    }





}
