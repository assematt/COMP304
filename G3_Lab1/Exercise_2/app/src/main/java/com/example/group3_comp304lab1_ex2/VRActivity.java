package com.example.group3_comp304lab1_ex2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

public class VRActivity extends AppCompatActivity implements ActivityLifetimeFragment.OnLifetimeFragmentInteractionListener {

    FragmentManager fragmentManager;

    ActivityLifetimeFragment activityLifetimeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vr);

        fragmentManager = getSupportFragmentManager();

        activityLifetimeFragment = (ActivityLifetimeFragment)fragmentManager.findFragmentById(R.id.lifecycleFragment);
        activityLifetimeFragment.updateText("VRActivity");
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
    public void onLifetimeFragmentInteraction(String item) {

    }
}
