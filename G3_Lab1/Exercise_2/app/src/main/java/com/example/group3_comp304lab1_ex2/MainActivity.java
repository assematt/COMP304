package com.example.group3_comp304lab1_ex2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements ActivityListFragment.OnListFragmentInteractionListener, ActivityLifetimeFragment.OnLifetimeFragmentInteractionListener {

    FragmentManager fragmentManager;

    ActivityLifetimeFragment activityLifetimeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();

        activityLifetimeFragment = (ActivityLifetimeFragment)fragmentManager.findFragmentById(R.id.lifecycleFragment);
        activityLifetimeFragment.updateText("MainActivity");
        activityLifetimeFragment.updateText("OnCreate");
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        activityLifetimeFragment.updateText("OnStart");
    }

    @Override
    protected void onStop()
    {
        super.onStop();
        activityLifetimeFragment.updateText("OnStop");
    }

    @Override
    protected void onDestroy()
    {
        activityLifetimeFragment.updateText("OnDestroy");
        super.onDestroy();
    }

    @Override
    public void onListFragmentInteraction(String item) {

        switch (item)
        {
            case "VRActivity": {
                Intent intent = new Intent(this, VRActivity.class);
                startActivity(intent);
            } break;

            case "AIActivity": {
                Intent intent = new Intent(this, AIActivity.class);
                startActivity(intent);
            } break;
        }
    }

    @Override
    public void onLifetimeFragmentInteraction(String item) {

    }
}
