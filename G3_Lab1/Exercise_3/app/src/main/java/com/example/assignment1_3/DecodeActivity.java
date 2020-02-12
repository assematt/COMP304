package com.example.assignment1_3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;

public class DecodeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decode);

        Dictionary mapTable = new Hashtable<String,String>();
        GenerateMapTable(mapTable);

        Intent intent = getIntent();
        String wordToDecode = intent.getStringExtra(MainActivity.WORD_CODE);

        String decodedWord = "";
        for(int i = 0; i < wordToDecode.length(); i++) {
            Enumeration keys = mapTable.keys();
            while(keys.hasMoreElements()) {
                String key = keys.nextElement().toString();
                if(mapTable.get(key).equals(Character.toString(wordToDecode.charAt(i)))){
                    decodedWord = decodedWord + key;
                    break;
                }
            }
        }

        TextView textView = (TextView) findViewById(R.id.displayDecodedWord);
        textView.setText(decodedWord);
    }

    private void GenerateMapTable(Dictionary  table)
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
}
