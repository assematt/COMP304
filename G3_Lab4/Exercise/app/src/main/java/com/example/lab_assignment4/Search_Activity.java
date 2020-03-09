package com.example.lab_assignment4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;

public class Search_Activity extends AppCompatActivity {

    String[] list_items = new String[] { "Provinces", "Municipality", "Districts", "County", "City", "Town", "Village", "Ward" };
    AutoCompleteTextView autoCompleteTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_layout);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, list_items);
        autoCompleteTextView = (AutoCompleteTextView)
                findViewById(R.id.autoCompleteTextView);
        autoCompleteTextView.setThreshold(1);
        autoCompleteTextView.setAdapter(adapter);
    }

    public void TableSearch(View view)
    {
        Intent search_intent = new Intent(Search_Activity.this, TableList_Avtivity.class);
        String s = autoCompleteTextView.getText().toString();
        search_intent.putExtra("searched_item", s);
        search_intent.putExtra("from", "search");
        startActivity(search_intent);
    }
}
