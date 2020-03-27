package com.example.g3.guessinggameguianddb;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class UserRegistration extends AppCompatActivity
{
    private static final String TAG = "UserRegistration";

    private String userName;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_registration);

        Button btnRegister = (Button) findViewById(R.id.registerBtn);

        final EditText edtName = (EditText) findViewById(R.id.edtName);
        final EditText edtPassword = (EditText) findViewById(R.id.edtPassword);

        btnRegister.setOnClickListener( new View.OnClickListener()
        {
            public void onClick( View v )
            {
                checkAndAdd(edtName, edtPassword);
            }
        });
    }

    public void insertEntry(String userName, String password)
    {
        try
        {
            ContentValues newValues = new ContentValues();
            newValues.put("Username", userName);
            newValues.put("Password", password);
            newValues.put("Overall_Score", 0);

            MainActivity.UserScore.insert("User", null, newValues);

            // TODO: Store image

            Toast.makeText(this, userName + " added successfully!", Toast.LENGTH_LONG).show();
        }
        catch ( Exception e )
        {
            Toast.makeText(this, "Error, Check log!", Toast.LENGTH_LONG).show();
            Log.e("Note", "Exception: " + e);
        }
    }

    // check if username already exist, otherwise add user to db
    public void checkAndAdd(EditText name, EditText pass)
    {
        try
        {
            userName = name.getText().toString().toLowerCase();
            password = pass.getText().toString();

            String query = "SELECT * FROM USER";
            Cursor rs = MainActivity.UserScore.rawQuery(query, null);
            rs.moveToFirst();

            do
            {
                if ( rs.equals(userName) )
                {
                    Toast.makeText(this, "The username already exists!", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    insertEntry(userName, password);
                }
            } while ( rs.moveToNext() );
        }
        catch ( Exception e )
        {
            Toast.makeText( this, "Check log!", Toast.LENGTH_SHORT ).show();
            Log.e( "userCheck", "Exception: " + e );
        }
    }
}
