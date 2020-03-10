package com.g3.geographicalentities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

public class SearchActivity extends AppCompatActivity {

    String[] list_items = new String[] { "Provinces", "Municipality", "Districts", "County", "City", "Town", "Village", "Ward" };
    AutoCompleteTextView autoCompleteTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, list_items);
        autoCompleteTextView = (AutoCompleteTextView)
                findViewById(R.id.autoCompleteTextView);
        autoCompleteTextView.setThreshold(1);
        autoCompleteTextView.setAdapter(adapter);
    }

    public void TableSearch(View view)
    {
        Intent search_intent = new Intent(SearchActivity.this, TableListActivity.class);
        String s = autoCompleteTextView.getText().toString();
        search_intent.putExtra("searched_item", s);
        search_intent.putExtra("from", "search");
        startActivity(search_intent);
    }
}
