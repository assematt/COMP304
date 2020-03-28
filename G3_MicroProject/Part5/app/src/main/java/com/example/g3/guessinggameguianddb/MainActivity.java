package com.example.g3.guessinggameguianddb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
    private static final String TAG = "MainActivity";

    private String userName;
    private String password;
    public static SQLiteDatabase UserScore;
    private final String dbName = "user_score";
    EditText edtLogin, edtPassword;
    Button btnLogin;
    ImageView imgGuessingGame, imgMusic, imgHighScore;
    String user = "";
    TextView tvLogin, tvPassword;

    public static final String MyPREFERENCES = "MyPrefs" ;
    SharedPreferences sharedpreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createDatabase();

        edtLogin = (EditText) findViewById(R.id.edtLogin);
        edtPassword = (EditText) findViewById(R.id.edtLogPass);
        imgGuessingGame = (ImageView) findViewById(R.id.imgGuessingGame);
        imgMusic = (ImageView) findViewById(R.id.imgMusic);
        imgHighScore = (ImageView) findViewById(R.id.imgHighScore);

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        user = sharedpreferences.getString("username", "-");

        Log.d(TAG, "user: " + user);

        /*Login*/
        btnLogin = (Button) findViewById(R.id.btnLogin);

        if( user != "-" )
        {
            Log.d(TAG, "Got here !user.isEmpty()");
            loadLogin();
        }

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( user != "-" )
                {
                    Log.d(TAG, "Got here else of user.isEmpty()");
                    loadLogout();

                    edtLogin.setText("");
                    edtPassword.setText("");

                    editor = sharedpreferences.edit();
                    editor.clear();
                    editor.commit();

                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }
                else
                {
                    Log.d(TAG, "Got here user.isEmpty()");
                    /* Check empty field and then check db */
                    if( validateFields() ){
                        if( validateDB() ){
                            loadLogin();
                        }
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

    public void createDatabase()
    {
        try // if the database exits, skip creating it
        {
            UserScore = SQLiteDatabase.openDatabase(getApplicationContext().getDatabasePath(dbName).getPath(), null, SQLiteDatabase.OPEN_READWRITE);
            //Toast.makeText(this, "Database connection success!", Toast.LENGTH_LONG).show();
        }
        catch (Exception e) // if the database doesn't exist, create the database
        {
            Log.e("DbError", "Exception: " + e);

            // Create database
            UserScore = openOrCreateDatabase(dbName, MODE_PRIVATE, null);

            // Create User table
            UserScore.execSQL("CREATE TABLE IF NOT EXISTS User (Username VARCHAR(50), Password VARCHAR(50), Overall_Score INTEGER, User_Picture BLOB)");
        }
    }

    /* Open registration activity */
    public void loadRegistration(View view)
    {
        Intent intent = new Intent(getApplicationContext(), UserRegistration.class);
        startActivity(intent);
    }

    public void loadLogin()
    {
        imageActive();

        tvLogin = (TextView) findViewById(R.id.tvLogin);
        tvPassword = (TextView) findViewById(R.id.tvPassword);
        TextView tvRegister = (TextView) findViewById(R.id.tvRegister);

        btnLogin.setText( "Logout" );
        tvLogin.setText("Hello " + user);
        edtLogin.setVisibility(View.INVISIBLE);
        edtPassword.setVisibility(View.INVISIBLE);
        tvPassword.setVisibility(View.INVISIBLE);
        tvRegister.setVisibility(View.INVISIBLE);
    }

    public void loadLogout()
    {
        LinearLayout ll_clickable_frames = (LinearLayout) findViewById(R.id.ll_clickable_frames);
        ll_clickable_frames.setVisibility(View.INVISIBLE);

        btnLogin.setText( "Login" );
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
                Intent intent = new Intent(MainActivity.this, ScoreActivity.class);
                startActivity(intent);
            }
        });

        imgMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MusicActivity.class);
                startActivity(intent);
            }
        });
    }

    public Boolean validateFields()
    {
        Log.d(TAG, "Validate Fields");
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
        Log.d(TAG, "Validate DB");

        Boolean bool = false;

        try
        {
            userName = edtLogin.getText().toString().toLowerCase();
            password = edtPassword.getText().toString();

            String query = "SELECT * FROM User WHERE lower(Username) = '" + userName + "' AND Password = '" + password + "'";
            Log.d(TAG, "Query: " + query);
            Cursor rs = UserScore.rawQuery(query, null);

            if( rs.getCount() > 0 ) // user found
            {
                editor = sharedpreferences.edit();
                editor.putString("username", userName);
                editor.commit();

                user = sharedpreferences.getString("username", "-");

                bool = true;
            }
            else if( rs.getCount() == 0 )
            {
                Toast.makeText(this, "Incorrect username or password!", Toast.LENGTH_SHORT).show();
            }
        }
        catch(Exception e)
        {
            Toast.makeText(this, "Check log (loginCheck)!", Toast.LENGTH_SHORT).show();
            Log.e("loginCheck", "Exception: " + e);
        }

        return bool;
    }
}