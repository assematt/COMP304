package com.example.guessinggame;

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
  private final String dbTableCreate = "CREATE TABLE IF NOT EXISTS USER" +
          "(ID INTEGER PRIMARY KEY AUTOINCREMENT," +
          "USERNAME TEXT NOT NULL," +
          "PASSWORD TEXT NOT NULL," +
          "SCORE INTEGER," +
          "USERPICTURE OBJECT);"; /*Table Creation*/

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
        /*Check empty field and then check db*/
        if(validateFields(edtName, edtPassword)){
          if(validateDB(edtName, edtPassword)){
            loadRegistration(v);
          }
        }
      } //  onClick
    });
  } //  onCreate

  public void createDatabase(String name){
    try {
      UserScore = SQLiteDatabase.openDatabase(getApplicationContext().getDatabasePath(name).getPath(), null, SQLiteDatabase.OPEN_READWRITE);
      Toast.makeText(this, "Database connection success!", Toast.LENGTH_LONG).show();
      UserScore.execSQL(dbTableCreate);
    } catch (Exception e) {
      //Create database
      UserScore = openOrCreateDatabase(name, MODE_PRIVATE, null);
      Toast.makeText(this, "Database created successfully!", Toast.LENGTH_LONG).show();
      UserScore.execSQL(dbTableCreate);
    } //  tryCatch
  } //  createDb

  public Boolean insertEntry(String userName, String password){
    Boolean bool = false;
    try{
      String insertQuery = "INSERT INTO USER (USERNAME, PASSWORD, SCORE, USERPICTURE) VALUES" +
              "('"+userName+"','"+ password+"'," + 0 +","+"NULL)";
      UserScore.execSQL(insertQuery);
      bool = true;
      //  Store image
      Toast.makeText(this, userName + " added successfully!", Toast.LENGTH_LONG).show();
    } catch (Exception e){
      Log.e("Note", "Exception: " + e);
      Toast.makeText(this, "Check log (Note)!", Toast.LENGTH_LONG).show();
    } //  tryCatch
    return bool;
  } //  insertEntry

  public void loadRegistration(View view) {
    if(insertEntry(userName, password)){
      Intent intent = new Intent(this, MainActivity.class);
      startActivity(intent);
      UserScore.close();
    }
  } //  loadRegistration

  /*Validate registration details*/
  public Boolean validateFields(EditText name, EditText pass){
    Boolean bool = false;
    if(name.getText().toString().trim().length()==0){
      name.setError("Username is not entered");
      name.requestFocus();
    } //  emptyUser
    if(pass.getText().toString().trim().length()==0){
      pass.setError("Password is not entered");
      pass.requestFocus();
    } //  emptyPass
    else{
      bool = true;
    }
    return bool;
  } //  validateField
  public Boolean validateDB(EditText name, EditText pass){
    Boolean bool = false;
    try{
      userName = name.getText().toString().toLowerCase();
      password = pass.getText().toString();
      String query = "SELECT * FROM USER WHERE USERNAME = '" + userName + "' AND PASSWORD = '" + password + "'";
      Cursor rs = UserScore.rawQuery(query, null);
      rs.moveToFirst();
      /*Multiple entries check doesn't work*/
      if(rs.getCount()>0){
        Toast.makeText(this, "The username already exists!", Toast.LENGTH_SHORT).show();
      } //  Already Exists
      else{
        bool=true;
      }
    } catch(Exception e){
      Toast.makeText(this, "Check log (userCheck)!", Toast.LENGTH_SHORT).show();
      Log.e("userCheck", "Exception: " + e);
    } //  tryCatch
    return bool;
  } //  validateDB

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
