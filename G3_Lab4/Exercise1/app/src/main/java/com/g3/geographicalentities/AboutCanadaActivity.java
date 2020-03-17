package com.g3.geographicalentities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

public class AboutCanadaActivity extends AppCompatActivity {

    TextView textView;
    String[] canadaInfoStrings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_canada);

        textView = findViewById(R.id.canadaInfoText);

        canadaInfoStrings = getResources().getStringArray(R.array.about_canada_info);

        Spinner spinner = findViewById(R.id.infoChoices);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position)
                {
                    case 0: textView.setText("Please select an item from the list above to learn more about Canada.");
                            break;
                    default: textView.setText(canadaInfoStrings[position - 1]);
                    break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
