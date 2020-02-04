package com.example.assignment1_3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.Dictionary;
import java.util.Hashtable;

public class MainActivity extends AppCompatActivity {
    public static String WORD_CODE = "This is the word to be encoded.";
    String tag = "Test";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
    }

    public void SendWordToEncode(View view)
    {
        Intent intent = new Intent(this, CodeActivity.class);
        TextView editText = (TextView) findViewById(R.id.wordToEncode);
        String wordToEncode = editText.getText().toString().toLowerCase();
        Log.d(tag, "wordToEncode: " +  wordToEncode);

        intent.putExtra(WORD_CODE, wordToEncode);
        startActivity(intent);
    }

    public void SendWordToDecode(View view)
    {
        Intent intent = new Intent(this, DecodeActivity.class);
        TextView editText = (TextView) findViewById(R.id.wordToEncode);
        String wordToEncode = editText.getText().toString().toLowerCase();
        Log.d(tag, "wordToEncode: " +  wordToEncode);

        intent.putExtra(WORD_CODE, wordToEncode);
        startActivity(intent);
    }
}
