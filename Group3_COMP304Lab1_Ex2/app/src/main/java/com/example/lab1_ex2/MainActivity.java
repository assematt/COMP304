package com.example.lab1_ex2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.service.autofill.TextValueSanitizer;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    String tag = "Lifecycle";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // add the List View Control Fragment on the Main Activity View
        Fragment lvcFragment = new LVCFragment();
        FragmentManager fmLvc = getSupportFragmentManager();
        FragmentTransaction ftLvc = fmLvc.beginTransaction();
        ftLvc.add(R.id.lvc_fragment, lvcFragment);

        // add the Text View Control Fragment on the Main Activity View
        Fragment tvcFragment = new TVCFragment();
        FragmentManager fmTvc = getSupportFragmentManager();
        FragmentTransaction ftTvc = fmTvc.beginTransaction();
        ftTvc.add(R.id.tvc_fragment, tvcFragment);

        // add the executed life cycle method on the Text View Control Fragment
        TextView textView = (TextView) findViewById(R.id.main_activity_lc);
        textView.append("\n onCreate executed");

        // create the toast message for the executed life cycle method
        Toast.makeText(getBaseContext(),"onCreate executed", Toast.LENGTH_SHORT).show();

        Log.d(tag, "In this State (onCreate), the activity is created.");
    }

    public void onStart() {
        super.onStart();

        TextView textView = (TextView) findViewById(R.id.main_activity_lc);
        textView.append("\n onStart executed");

        Toast.makeText(getBaseContext(),"onStart executed", Toast.LENGTH_SHORT).show();

        Log.d(tag, "In this State (onStart), the activity is started.");
    }

    public void onRestart() {
        super.onRestart();

        TextView textView = (TextView) findViewById(R.id.main_activity_lc);
        textView.append("\n onRestart executed");

        Toast.makeText(getBaseContext(),"onRestart executed", Toast.LENGTH_SHORT).show();

        Log.d(tag, "In this State (onRestart), the activity is restarted.");
    }

    public void onResume() {
        super.onResume();

        TextView textView = (TextView) findViewById(R.id.main_activity_lc);
        textView.append("\n onResume executed");

        Toast.makeText(getBaseContext(),"onResume executed", Toast.LENGTH_SHORT).show();

        Log.d(tag, "In this State (onResume), the activity is in the " +
                "foreground, and the user can interact with it.");
    }

    public void onPause() {
        super.onPause();

        TextView textView = (TextView) findViewById(R.id.main_activity_lc);
        textView.append("\n onPause executed");

        Toast.makeText(getBaseContext(),"onPause executed", Toast.LENGTH_SHORT).show();

        Log.d(tag, "In this State (onPause), the activity is partially obscured " +
                "by the another activity that's in the foreground is semi-transparent.");
    }

    public void onStop() {
        super.onStop();

        TextView textView = (TextView) findViewById(R.id.main_activity_lc);
        textView.append("\n onStop executed");

        Toast.makeText(getBaseContext(),"onStop executed", Toast.LENGTH_SHORT).show();

        Log.d(tag, "In this State (onStop), the activity is stopped.");
    }

    public void onDestroy() {
        super.onDestroy();

        TextView textView = (TextView) findViewById(R.id.main_activity_lc);
        textView.append("\n onDestroy executed");

        Toast.makeText(getBaseContext(),"onDestroy executed", Toast.LENGTH_SHORT).show();

        Log.d(tag, "In this case (onDestroy), the activity is destroyed " +
                "and removed from the memory.");
    }
}