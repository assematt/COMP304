package com.example.lab_assignment4;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.database.sqlite.SQLiteDatabase;

import org.w3c.dom.Text;

public class TableList_Avtivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.table_layout);

        String from = getIntent().getStringExtra("from");
        if(from.equals("list"))
        {
            String selected_item = getIntent().getStringExtra("selected_item");

            //Open database canada_db
            SQLiteDatabase canadaDb = openOrCreateDatabase("canada_db", MODE_PRIVATE, null);

            String query = "select * from "+selected_item;
            Cursor rs = canadaDb.rawQuery(query, null);
            rs.moveToFirst();

            displayTable(rs);

        }
        else if(from.equals("search"))
        {
            String searched_item = getIntent().getStringExtra("searched_item");

            //Open database canada_db
            SQLiteDatabase canadaDb = openOrCreateDatabase("canada_db", MODE_PRIVATE, null);

            String query = "select * from "+searched_item;
            Cursor rs = canadaDb.rawQuery(query, null);
            rs.moveToFirst();

            displayTable(rs);
        }

    }

    public void displayTable(Cursor rs)
    {
        TableLayout ll = (TableLayout) findViewById(R.id.canada_table);

        for (int i = 0; i <rs.getCount(); i++)
        {
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
               if(i == 0)
                {
                    String title = rs.getColumnName(j).replace("_"," ").toUpperCase();
                    textView.setText(title);
                    textView.setGravity(Gravity.CENTER);
                    textView.setTextColor(Color.WHITE);
                    textView.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                }
                else
                {
                    textView.setText(rs.getString(j));
                    textView.setTextColor(Color.WHITE);
                }
                textView.setWidth(250);
                textView.setPadding(10,10,10,10);

                row.addView(textView);
            }
            ll.addView(row,i);
            rs.moveToNext();
        }
    }
}
