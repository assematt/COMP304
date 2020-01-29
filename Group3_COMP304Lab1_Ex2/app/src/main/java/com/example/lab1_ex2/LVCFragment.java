package com.example.lab1_ex2;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


public class LVCFragment extends Fragment {
    String tag = "";

    public LVCFragment()
    {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_lvc, container, false);

        // populate the list view items for the List View Control Fragment
        String activities[] = new String [] { "AIActivity", "VRActivity" };
        final ListView listView = (ListView) rootView.findViewById(R.id.lv_activity);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, activities);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener( new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                String selectedItem = (String) parent.getItemAtPosition(position);
                Log.d(tag, "selectedItem: " + selectedItem);

                // show the AI Activity View
                if( selectedItem == "AIActivity" ) {
                    Intent intent = new Intent(getActivity(), AIActivity.class);
                    startActivity(intent);
                }

                // show the VR Activity View
                if( selectedItem == "VRActivity" ) {
                    Intent intent = new Intent(getActivity(), VRActivity.class);
                    startActivity(intent);
                }
            }
        });

        // Inflate the layout for this fragment
        return rootView;
    }
}
