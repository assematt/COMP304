package com.example.g3.guessinggameguianddb;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class UserRegistration extends AppCompatActivity {
  private String userName;
  private String password;
  private static SQLiteDatabase UserScore;  /*Database - Object*/
  private final String dbName = "user_score";
  private final String dbTableCreate = "CREATE TABLE IF NOT EXISTS User (Username VARCHAR(50), Password VARCHAR(50), Overall_Score INTEGER, User_Picture BLOB)"; /*Table Creation*/

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.user_registration);

    /*Creating DB*/
    createDatabase(dbName);

    /*After registration success!*/
    Button btnRegister = (Button) findViewById(R.id.registerBtn);
    final EditText edtName = (EditText) findViewById(R.id.edtName);
    final EditText edtPassword = (EditText) findViewById(R.id.edtPassword);
    btnRegister.setOnClickListener(new View.OnClickListener() {
      public void onClick(View v) {
        /*Calling method from main class*/
        checkAndAdd(edtName, edtPassword);
        loadRegistration(v);
      } //  onClick
    });
  } //  onCreate

  public void createDatabase(String name){
    try {
      UserScore = SQLiteDatabase.openDatabase(getApplicationContext().getDatabasePath(name).getPath(), null, SQLiteDatabase.OPEN_READWRITE);
      Toast.makeText(this, "Database connection success!", Toast.LENGTH_LONG).show();
    }
    catch (Exception e)
    {
      //Create database
      UserScore = openOrCreateDatabase(name, MODE_PRIVATE, null);
      Toast.makeText(this, "Database created successfully!", Toast.LENGTH_LONG).show();
      UserScore.execSQL(dbTableCreate);
    } //  tryCatch
  } //  createDb

  public void insertEntry(String userName, String password){
    try{
      ContentValues newValues = new ContentValues();
      newValues.put("USERNAME", userName);
      newValues.put("PASSWORD", password);
      //  Store image
      Toast.makeText(this, userName + " added successfully!", Toast.LENGTH_LONG).show();
    } catch (Exception e){
      Log.e("Note", "Exception: " + e);
      Toast.makeText(this, "Error, Check log!", Toast.LENGTH_LONG).show();
    } //  tryCatch
  } //  InsertUserName

  public void loadRegistration(View view) {
    Intent intent = new Intent(this, MainActivity.class);
    startActivity(intent);
  } //  loadRegistration

    /*Check
    * 1. Already Exists
    * 2. Wrong Input*/
  public void checkAndAdd(EditText name, EditText pass){
    try{
      userName = name.getText().toString().toLowerCase();
      password = pass.getText().toString();
      String query = "SELECT * FROM USER";
      UserScore.execSQL(query);
      Cursor rs = UserScore.rawQuery(query, null);
      rs.moveToFirst();
      /*Multiple entries check doesn't work*/
      do {
        if(rs.equals(userName)){
          Toast.makeText(this, "The username already exists!", Toast.LENGTH_SHORT).show();
        } //  Already Exists
        else{
          insertEntry(userName, hashedPass(password));
        } //  else
      } while(rs.moveToNext());
    } catch(Exception e){
      Toast.makeText(this, "Check log!", Toast.LENGTH_SHORT).show();
      Log.e("userCheck", "Exception: " + e);
    } //  tryCatch
  } //  Check

  /*Salting password to store in db*/
  private String hashedPass(String password){
    String securedPass = null;
    try{
      securedPass = password + "1";
    } catch(Exception e){
      Log.e("ErrorPass", "Exception: " + e);
      Toast.makeText(this, "Pass error, Check log!", Toast.LENGTH_SHORT).show();
    } //  tryCatch
    return securedPass;
  } //  passWordLogic

} //  classEnd
