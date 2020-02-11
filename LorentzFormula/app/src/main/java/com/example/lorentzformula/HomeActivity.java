package com.example.lorentzformula;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {

    String firstname,lastname;
    String result;
    double weightDif, height, weight;
    boolean canCalculate = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


    }

    void calculate()
    {
        EditText fname = (EditText)findViewById(R.id.firstname);

        EditText lname = (EditText)findViewById(R.id.lastname);

        EditText heighttext = (EditText)findViewById(R.id.height);

        EditText weighttext = (EditText)findViewById(R.id.weight);


        RadioGroup rg = (RadioGroup) findViewById(R.id.radio_group);
        int radioId = rg.getCheckedRadioButtonId();

        RadioButton radioButton = (RadioButton) findViewById(radioId);

        String gender = radioButton.getText().toString();

        canCalculate = false;
        if(isEmpty(fname)){
            setError(fname, "Enter first name");
        }
        else
        {
            canCalculate = true;
            firstname = fname.getText().toString();
            clearError(fname);
        }
        if(isEmpty(lname)){
            setError(lname, "Enter last name");
        }
        else
        {
            canCalculate = true;
            lastname = lname.getText().toString();
            clearError(lname);
        }
        if(isEmpty(heighttext)){

            setError(heighttext, "Enter height");
        }
        else
        {
            canCalculate = true;
            height = Float.parseFloat(heighttext.getText().toString());
            clearError(heighttext);
        }
        if(isEmpty(weighttext)){

            setError(weighttext, "Enter weight");
        }
        else
        {
            canCalculate = true;
            weight = Float.parseFloat(weighttext.getText().toString());
            clearError(weighttext);
        }
        if(canCalculate)
        {
            calculation(gender, height, weight);
        }
    }

    void calculation(String gender, double height, double weight)
    {
        double w;
        if(gender.toLowerCase().equals("female"))
        {
            w = (height - 100)-((height - 150)/2.5);
        }
        else
        {
            w = (height - 100) - ((height - 150)/4);
        }
        weightDif = weight - w;
        if(weightDif<0)
            result = "Hello "+firstname+" "+lastname+" You need to gain "+Math.abs(weightDif)+"kg:gain";
        else
            result = "Hello "+firstname+" "+lastname+" You need to lose "+Math.abs(weightDif)+"kg:loose";

    }

    public void LoadResultActivity(View view)
    {
        calculate();

        if(canCalculate) {
            Intent intent = new Intent(this, ResultActivity.class);

            intent.putExtra("details", result);

            startActivity(intent);
        }
    }

    public static boolean isEmpty(EditText editText) {

        String input = editText.getText().toString().trim();
        return input.length() == 0;

    }
    public static void setError(EditText editText, String errorString) {

        editText.setError(errorString);

    }

    public static void clearError(EditText editText) {

        editText.setError(null);

    }
}
