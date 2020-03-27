package com.example.g3.guessinggameguianddb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class ScoreActivity extends AppCompatActivity
{
    private static final String TAG = "ScoreActivity";

    public static final String MyPREFERENCES = "MyPrefs" ;
    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        String user = sharedpreferences.getString("username", null);

        String query = "SELECT * FROM User WHERE Username = '" + user + "'";
        Log.d(TAG, "query: " + query);
        Cursor rs = MainActivity.UserScore.rawQuery(query, null);
        rs.moveToFirst();

        displayTable(rs);
    }

    public void displayTable(Cursor rs)
    {
        Log.d(TAG, "rs.getCount(): " + rs.getCount());
        TableLayout ll = (TableLayout) findViewById(R.id.score_table);

        if (rs.getCount() > 0)
        {
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

                String title = rs.getColumnName(j).replace("_"," ").toUpperCase();

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
        else
        {
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

                String title = rs.getColumnName(j).replace("_"," ").toUpperCase();

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
            TableRow row= new TableRow(this);
            TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);

            row.setLayoutParams(lp);
            row.setBackgroundColor(Color.GRAY);

            TextView textView = new TextView(this);

            TableRow.LayoutParams param = new TableRow.LayoutParams(
                    TableRow.LayoutParams.WRAP_CONTENT,
                    TableRow.LayoutParams.WRAP_CONTENT,
                    1.0f
            );
            textView.setLayoutParams(param);

            textView.setText("No Results Found!");
            textView.setTextColor(Color.WHITE);
            textView.setWidth(250);
            textView.setPadding(10,10,10,10);

            row.addView(textView);
            ll.addView(row,1);
            /*
             * END: Populate row from query result
             * */
        }

    }
}
