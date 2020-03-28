package com.example.g3.guessinggameguianddb;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.ByteArrayOutputStream;

public class UserRegistration extends AppCompatActivity
{
    private static final String TAG = "UserRegistration";

    private String userName;
    private String password;
    private byte imageInByte[];

    private Button btnCapture;
    private ImageView imgCapture;

    private static final int Image_Capture_Code = 1;

    public static final String MyPREFERENCES = "MyPrefs" ;
    SharedPreferences sharedpreferences;

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

        btnCapture =(Button)findViewById(R.id.btnTakePicture);
        imgCapture = (ImageView) findViewById(R.id.capturedImage);
        btnCapture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cInt = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cInt,Image_Capture_Code);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Image_Capture_Code)
        {
            if (resultCode == RESULT_OK)
            {
                Log.d("data", "data: " + data.getExtras().get("data"));
                Bitmap bp = (Bitmap) data.getExtras().get("data");
                imgCapture.setImageBitmap(bp);

                // convert bitmap to byte
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bp.compress(Bitmap.CompressFormat.PNG, 100, stream);
                imageInByte = stream.toByteArray();
                Log.e("output before conversion", imageInByte.toString());
            }
            else if (resultCode == RESULT_CANCELED)
            {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
            }
        }
    }

    public void insertEntry(String userName, String password)
    {
        try
        {
            ContentValues newValues = new ContentValues();
            newValues.put("Username", userName);
            newValues.put("Password", password);
            newValues.put("Overall_Score", 0);
            newValues.put("User_Picture", imageInByte);

            MainActivity.UserScore.insert("User", null, newValues);

            Toast.makeText(this, userName + " you are now successfully registered!", Toast.LENGTH_LONG).show();
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
                if ( rs.getCount() > 0 && rs.getString(0).equalsIgnoreCase(userName) )
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
