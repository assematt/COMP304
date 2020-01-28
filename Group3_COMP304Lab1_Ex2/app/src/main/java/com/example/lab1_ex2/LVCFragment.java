package com.example.lab1_ex2;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class LVCFragment extends Fragment {
    public LVCFragment()
    {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_lvc, container, false);

        // populate the list view items for the List View Control Fragment
        String activities[] = new String [] { "AIActivity", "VRActivity" };
        ListView listView = (ListView) rootView.findViewById(R.id.lv_activity);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(), R.layout.activity_main, activities);
        listView.setAdapter(arrayAdapter);

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_lvc, container, false);
    }
}
