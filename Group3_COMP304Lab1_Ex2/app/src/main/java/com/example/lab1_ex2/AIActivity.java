package com.example.lab1_ex2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class AIActivity extends AppCompatActivity {
    String tag = "Lifecycle";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ai);

        // add the executed life cycle method on the Text View Control Fragment
        TextView textView = (TextView) findViewById(R.id.ai_activity_lc);
        textView.append("\n onCreate executed");

        // create the toast message for the executed life cycle method
        Toast.makeText(getBaseContext(),"AI Activity onCreate executed", Toast.LENGTH_SHORT).show();

        Log.d(tag, "In this State (onCreate), the activity is created.");
    }

    public void onStart() {
        super.onStart();

        TextView textView = (TextView) findViewById(R.id.ai_activity_lc);
        textView.append("\n onStart executed");

        Toast.makeText(getBaseContext(),"AI Activity onStart executed", Toast.LENGTH_SHORT).show();

        Log.d(tag, "In this State (onStart), the activity is started.");
    }

    public void onRestart() {
        super.onRestart();

        TextView textView = (TextView) findViewById(R.id.ai_activity_lc);
        textView.append("\n onRestart executed");

        Toast.makeText(getBaseContext(),"AI Activity onRestart executed", Toast.LENGTH_SHORT).show();

        Log.d(tag, "In this State (onRestart), the activity is restarted.");
    }

    public void onResume() {
        super.onResume();

        TextView textView = (TextView) findViewById(R.id.ai_activity_lc);
        textView.append("\n onResume executed");

        Toast.makeText(getBaseContext(),"AI Activity onResume executed", Toast.LENGTH_SHORT).show();

        Log.d(tag, "In this State (onResume), the activity is in the " +
                "foreground, and the user can interact with it.");
    }

    public void onPause() {
        super.onPause();

        TextView textView = (TextView) findViewById(R.id.ai_activity_lc);
        textView.append("\n onPause executed");

        Toast.makeText(getBaseContext(),"AI Activity onPause executed", Toast.LENGTH_SHORT).show();

        Log.d(tag, "In this State (onPause), the activity is partially obscured " +
                "by the another activity that's in the foreground is semi-transparent.");
    }

    public void onStop() {
        super.onStop();

        TextView textView = (TextView) findViewById(R.id.ai_activity_lc);
        textView.append("\n onStop executed");

        Toast.makeText(getBaseContext(),"AI Activity onStop executed", Toast.LENGTH_SHORT).show();

        Log.d(tag, "In this State (onStop), the activity is stopped.");
    }

    public void onDestroy() {
        super.onDestroy();

        TextView textView = (TextView) findViewById(R.id.ai_activity_lc);
        textView.append("\n onDestroy executed");

        Toast.makeText(getBaseContext(),"AI Activity onDestroy executed", Toast.LENGTH_SHORT).show();

        Log.d(tag, "In this case (onDestroy), the activity is destroyed " +
                "and removed from the memory.");
    }
}
