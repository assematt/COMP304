package com.example.lab1_3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class MainActivity extends AppCompatActivity {

    Dictionary mapTable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mapTable = new Hashtable<String,String>();
        GenerateMapTable(mapTable);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void SendEncodedText(View view)
    {
        Intent intent = new Intent(this, DisplayActivity.class);
        TextView editText = (TextView) findViewById(R.id.entered_text);
        String text =editText.getText().toString();

        String outputText= EncodeText(text, mapTable);

        intent.putExtra("output", outputText);
        startActivity(intent);
    }

    public void SendDecodedText(View view)
    {
        Intent intent = new Intent(this, DisplayActivity.class);
        TextView editText = (TextView) findViewById(R.id.entered_text);
        String text =editText.getText().toString();

        String outputText= DecodeText(text, mapTable);

        intent.putExtra("output", outputText);
        startActivity(intent);
    }

    public void GenerateMapTable(Dictionary  table)
    {
        table.put("a","t");
        table.put("b","m");
        table.put("c","e");
        table.put("d","s");
        table.put("e"," ");
        table.put("f","k");
        table.put("g","j");
        table.put("h","a");
        table.put("i","x");
        table.put("j","n");
        table.put("k","o");
        table.put("l","v");
        table.put("m","l");
        table.put("n","u");
        table.put("o","c");
        table.put("p","h");
        table.put("q","z");
        table.put("r","g");
        table.put("s","y");
        table.put("t","b");
        table.put("u","w");
        table.put("v","p");
        table.put("w","d");
        table.put("x","r");
        table.put("y","i");
        table.put("z","q");
        table.put(" ","f");
    }

    public String EncodeText(String text, Dictionary mapTable)
    {
        String outputText="";
        for(int i=0;i<text.length();i++)
        {
            outputText = outputText + mapTable.get(Character.toString(text.charAt(i)));
        }
        return  outputText;
    }

    public String DecodeText(String text, Dictionary mapTable)
    {
        String outputText="";
        for(int i=0;i<text.length();i++){
            Enumeration keys = mapTable.keys();
            while (keys.hasMoreElements()) {
                String key = keys.nextElement().toString();
                if(mapTable.get(key).equals(Character.toString(text.charAt(i)))){
                    outputText = outputText + key;
                    break;
                }
            }
        }
        return  outputText;
    }
}
