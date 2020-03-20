package com.example.g3.guessinggameguianddb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{
    private String userName;
    private String password;
    private static SQLiteDatabase UserScore;
    private final String dbName = "user_score";
    EditText edtLogin, edtPassword;
    Button btnLogin;
    ImageView imgGuessingGame, imgMusic, imgHighScore, imgOurGame;

    private static SQLiteDatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        openDatabase();

        edtLogin = (EditText) findViewById(R.id.edtLogin);
        edtPassword = (EditText) findViewById(R.id.edtLogPass);
        imgGuessingGame = (ImageView) findViewById(R.id.imgGuessingGame);
        imgMusic = (ImageView) findViewById(R.id.imgMusic);
        imgHighScore = (ImageView) findViewById(R.id.imgHighScore);
        imgOurGame = (ImageView) findViewById(R.id.imgOurGame);

        /*Login*/
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /* Check empty field and then check db */
                if( validateFields() ){
                    if( validateDB() ){
                        loadLogin(v);
                    }
                }
            }
        });

        /*Adding link to textView*/
        TextView tvRegister = (TextView) findViewById(R.id.tvRegister);
        tvRegister.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v)
            {
                loadRegistration(v);
            }
        });
    }

    public void openDatabase()
    {
        /*Create DB - UserScore
         * Tables - User
         * Fields - Username(String),
         * Password(String){After logic},
         * Overall score(Integer),
         * UserPicture(BLOB)*/
        try // if the database exits, skip creating it
        {
            UserScore = SQLiteDatabase.openDatabase(getApplicationContext().getDatabasePath(dbName).getPath(), null, SQLiteDatabase.OPEN_READONLY);
            Toast.makeText(this, "Database connection success!", Toast.LENGTH_LONG).show();
        }
        catch (Exception e) // if the database doesn't exist, create the database
        {
            Log.e("DbError", "Exception: " + e);

            // Create database
            UserScore = openOrCreateDatabase(dbName, MODE_PRIVATE, null);

            // Create User table
            UserScore.execSQL("CREATE TABLE IF NOT EXISTS User (Username VARCHAR(50), Password VARCHAR(50), Overall_Score INTEGER, User_Picture BLOB)");

            // Insert Test User
            //UserScore.execSQL("INSERT INTO User values('Test', '1234567', 0, '')");
        }
    }

    /* Open registration activity */
    public void loadRegistration(View view)
    {
        Intent intent = new Intent(getApplicationContext(), UserRegistration.class);
        startActivity(intent);
    }

    public void loadMusic(View view)
    {
        Intent intent = new Intent(getApplicationContext(), Music.class);
        startActivity(intent);
    }

    /*logs in displaying userData including scores*/
    public void loadLogin(View view)
    {
        imageActive();
    }

    /*Activating clickable images*/
    public void imageActive()
    {
        //Toast.makeText(this, "Images activated!", Toast.LENGTH_LONG).show();

        LinearLayout ll_clickable_frames = (LinearLayout) findViewById(R.id.ll_clickable_frames);
        ll_clickable_frames.setVisibility(View.VISIBLE);

        /*Set onClickListeners*/
        imgGuessingGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, GuessingGame.class);
                startActivity(intent);
            }
        });

        imgHighScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, HighScore.class);
                startActivity(intent);
            }
        });

        imgMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Music.class);
                startActivity(intent);
            }
        });

        imgOurGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, OurGame.class);
                startActivity(intent);
            }
        });
    }

    public Boolean validateFields()
    {
        Boolean bool = false;

        if( edtLogin.getText().toString().trim().length()==0 )
        {
            edtLogin.setError("Username is not entered");
            edtLogin.requestFocus();
        }
        if( edtPassword.getText().toString().trim().length()==0 )
        {
            edtPassword.setError("Password is not entered");
            edtPassword.requestFocus();
        }
        else
        {
            bool = true;
        }

        return bool;
    }

    public Boolean validateDB()
    {
        Boolean bool = false;

        try
        {
            userName = edtLogin.getText().toString().toLowerCase();
            password = edtPassword.getText().toString();

            String query = "SELECT * FROM User WHERE lower(Username) = '" + userName + "' AND Password = '" + password + "'";
            Log.d("validateDB", "Query: " + query);
            Cursor rs = UserScore.rawQuery(query, null);

            if(rs.getCount()>0)
            {
                bool = true;
            }
            else if(rs.getCount()==0)
            {
                Toast.makeText(this, "Wrong details", Toast.LENGTH_SHORT).show();
            }
        }
        catch(Exception e)
        {
            Toast.makeText(this, "Check log (loginCheck)!", Toast.LENGTH_SHORT).show();
            Log.e("loginCheck", "Exception: " + e);
        }

        return bool;
    }
} //  classEnd