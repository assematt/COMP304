package com.g3.geographicalentities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Spinner;

public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
    }

    public void TableSearch(View view)
    {
        Spinner spinner = findViewById(R.id.entityList);
        EditText editBox = findViewById(R.id.entityNameText);

        Intent search_intent = new Intent(SearchActivity.this, TableListActivity.class);
        String searched_item = editBox.getText().toString();
        String searched_table = spinner.getSelectedItem().toString();

        search_intent.putExtra("searched_item", searched_item);
        search_intent.putExtra("searched_table", searched_table);
        search_intent.putExtra("from", "search");
        startActivity(search_intent);
    }
}
