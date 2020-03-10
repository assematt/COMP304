package com.g3.geographicalentities;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class TableListActivity extends AppCompatActivity {

    private final String TAG = "TableListActivity";
    private String selected_title = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_list);

        String from = getIntent().getStringExtra("from");

        if(from.equals("list"))
        {
            String selected_item = getIntent().getStringExtra("selected_item");

            selected_title = selected_item;
            String query = "select * from "+selected_item;
            Log.d(TAG, "query: " + query);
            Cursor rs = MainActivity.canadaDb.rawQuery(query, null);
            rs.moveToFirst();

            displayTable(rs);

        }
        else if(from.equals("search"))
        {
            String searched_item = getIntent().getStringExtra("searched_item");
            String searched_table = getIntent().getStringExtra("searched_table");


            selected_title = searched_table;

            //Open database canada_db

            String query = "select * from "+searched_table + " WHERE name LIKE '%"+searched_item+"%'";
            Log.d(TAG, "query: " + query);
            Cursor rs = MainActivity.canadaDb.rawQuery(query, null);
            rs.moveToFirst();

            displayTable(rs);
        }
    }

    public void displayTable(Cursor rs)
    {
        Log.d(TAG, "rs.getCount(): " + rs.getCount());
        TableLayout ll = (TableLayout) findViewById(R.id.canada_table);

        /*
        * START: Populate Table Header by getting them from the query
        * */
        TableRow rowHeader = new TableRow(this);
        TableRow.LayoutParams lpHeader = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);

        rowHeader.setLayoutParams(lpHeader);
        rowHeader.setBackgroundColor(Color.BLUE);
        for(int j=0;j<rs.getColumnCount();j++)
        {
            TextView textView = new TextView(this);

            TableRow.LayoutParams param = new TableRow.LayoutParams(
                    TableRow.LayoutParams.WRAP_CONTENT,
                    TableRow.LayoutParams.WRAP_CONTENT,
                    1.0f
            );
            textView.setLayoutParams(param);


            String title = "";

            if (j == 0)
                title +=  selected_title.toUpperCase() + " ";

            title += rs.getColumnName(j).replace("_"," ").toUpperCase();

            textView.setText(title);
            Log.d(TAG, "title: " + title);
            textView.setGravity(Gravity.CENTER);
            textView.setTextColor(Color.WHITE);
            textView.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));

            textView.setWidth(250);
            textView.setPadding(10,10,10,10);

            rowHeader.addView(textView);
        }
        ll.addView(rowHeader,0);
        /*
         * END: Populate Table Header by getting them from the query
         * */

        /*
         * START: Populate row from query result
         * */
        Integer i = 1;
        do
        {
            Log.d(TAG, "i: " + i);

            TableRow row= new TableRow(this);
            TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);

            row.setLayoutParams(lp);
            if(i == 0) {
                row.setBackgroundColor(Color.BLUE);
            }
            else
            {
                row.setBackgroundColor(Color.DKGRAY);
                if(i%2 == 0)
                {
                    row.setBackgroundColor(Color.GRAY);
                }
            }

            for(int j=0;j<rs.getColumnCount();j++)
            {
                TextView textView = new TextView(this);

                TableRow.LayoutParams param = new TableRow.LayoutParams(
                        TableRow.LayoutParams.WRAP_CONTENT,
                        TableRow.LayoutParams.WRAP_CONTENT,
                        1.0f
                );
                textView.setLayoutParams(param);

                Log.d(TAG, "rs.getString(j): " + rs.getString(j));
                textView.setText(rs.getString(j));
                textView.setTextColor(Color.WHITE);
                textView.setWidth(250);
                textView.setPadding(10,10,10,10);

                row.addView(textView);
            }
            ll.addView(row,i);

            i++;
        } while ( rs.moveToNext() );
        /*
         * END: Populate row from query result
         * */
    }
}
