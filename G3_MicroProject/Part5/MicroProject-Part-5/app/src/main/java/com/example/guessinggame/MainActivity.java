package com.example.guessinggame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
  private String userName;
  private String password;
  private static SQLiteDatabase UserScore;
  private final String dbName = "user_score";
  EditText edtLogin, edtPassword;
  Button btnLogin;
  ImageView imgGuessingGame, imgMusic, imgHighScore, imgOurGame;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
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
        /*Check empty field and then check db*/
        if(validateFields()){
          if(validateDB()){
            loadLogin(v);
          }
        }
      } //  onClick
    }); //  btnLogin
    /*Adding link to textView*/
    TextView tvRegister = (TextView) findViewById(R.id.tvRegister);
    tvRegister.setOnClickListener(new View.OnClickListener(){
      public void onClick(View v){
        loadRegistration(v);
      } //  onClick
    }); //  tvRegister
  } //  onCreate

  public void openDatabase(){
    /*Create DB - UserScore
     * Tables - User
     * Fields - ID (Integer)[Primary Key],
     * Username(String),
     * Password(String){After logic},
     * Overall score(Integer),
     * UserPicture(LONGBLOB)*/
    try {
      UserScore = SQLiteDatabase.openDatabase(getApplicationContext().getDatabasePath(dbName).getPath(), null, SQLiteDatabase.OPEN_READONLY);
      Toast.makeText(this, "Database connection success!", Toast.LENGTH_LONG).show();
    } catch (Exception e) {
      Log.e("DbError", "Exception: " + e);
      Toast.makeText(this, "Check log (DbError)!", Toast.LENGTH_LONG).show();
    } //  tryCatch
  } //  createDb

  /*Open registration activity*/
  public void loadRegistration(View view) {
    Intent intent = new Intent(getApplicationContext(), UserRegistration.class);
    startActivity(intent);
  } //  loadRegistration

  /*Temp*/
  public void loadMusic(View view){
    Intent intent = new Intent(getApplicationContext(), Music.class);
    startActivity(intent);
  } //  loadMusic
  /*Temp*/

  /*logs in displaying userData including scores*/
  public void loadLogin(View view)  {
    imageActive();
    Intent intent = new Intent(MainActivity.this, MainActivity.class);
    startActivity(intent);
  } //  loadLogin

  /*Activating clickable images*/
  public void imageActive(){
    Toast.makeText(this, "Images activated!", Toast.LENGTH_LONG).show();

    /*Set onClickListeners*/
    imgGuessingGame.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intent = new Intent(MainActivity.this, GuessingGame.class);
        startActivity(intent);
      } //  onClick
    });
    imgHighScore.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intent = new Intent(MainActivity.this, HighScore.class);
        startActivity(intent);
      } //  onClick
    });
    imgMusic.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intent = new Intent(MainActivity.this, Music.class);
        startActivity(intent);
      } //  onClick
    });
    imgOurGame.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intent = new Intent(MainActivity.this, OurGame.class);
        startActivity(intent);
      } //  onClick
    });

  } //  imageActive

  public Boolean validateFields(){
    Boolean bool = false;
    if(edtLogin.getText().toString().trim().length()==0){
      edtLogin.setError("Username is not entered");
      edtLogin.requestFocus();
    } //  emptyUser
    if(edtPassword.getText().toString().trim().length()==0){
      edtPassword.setError("Password is not entered");
      edtPassword.requestFocus();
    } //  emptyPass
    else{
      bool = true;
    }
    return bool;
  } //  validateField
  public Boolean validateDB(){
    Boolean bool = false;
    try{
      userName = edtLogin.getText().toString().toLowerCase();
      password = edtPassword.getText().toString();
      String query = "SELECT * FROM USER WHERE USERNAME = '" + userName + "' AND PASSWORD = '" + password + "'";
      Cursor rs = UserScore.rawQuery(query, null);
      if(rs.getCount()>0){
        bool = true;
      } else if(rs.getCount()==0){
        Toast.makeText(this, "Wrong details", Toast.LENGTH_SHORT).show();
      } //  elseIf
    } catch(Exception e){
      Toast.makeText(this, "Check log (loginCheck)!", Toast.LENGTH_SHORT).show();
      Log.e("loginCheck", "Exception: " + e);
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
