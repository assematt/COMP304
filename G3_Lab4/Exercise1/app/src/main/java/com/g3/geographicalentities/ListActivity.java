package com.g3.geographicalentities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ListActivity extends AppCompatActivity {

    String[] list_items;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        listView = (ListView) findViewById(R.id.selection_list);
        list_items = getResources().getStringArray(R.array.administrative_divisions);

        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.listview_text_layout, list_items);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                String selected_item = list_items[position];
                Intent intent = new Intent(ListActivity.this, TableListActivity.class);
                intent.putExtra("selected_item", selected_item);
                intent.putExtra("from", "list");
                startActivity(intent);
            }
        });
    }
}
