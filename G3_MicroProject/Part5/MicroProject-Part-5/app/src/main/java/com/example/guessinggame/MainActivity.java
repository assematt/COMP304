package com.example.guessinggame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
  private String userName;
  private String password;
  private static SQLiteDatabase UserScore;
  private final String dbName = "user_score";
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    openDatabase();
    final EditText edtLogin = (EditText) findViewById(R.id.edtLogin);
    final EditText edtPassword = (EditText) findViewById(R.id.edtLogPass);
    /*Login*/
    Button btnLogin = (Button) findViewById(R.id.btnLogin);
    btnLogin.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        /*Check empty field and then check db*/
        if(validateFields(edtLogin, edtPassword)){
          if(validateDB(edtLogin, edtPassword)){
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
      UserScore = SQLiteDatabase.openDatabase(getApplicationContext().getDatabasePath("user_score").getPath(), null, SQLiteDatabase.OPEN_READONLY);
      Toast.makeText(this, "Database connection success!", Toast.LENGTH_LONG).show();
    } catch (Exception e) {
      Log.e("DbError", "Exception: " + e);
      Toast.makeText(this, "DB error, check log!", Toast.LENGTH_LONG).show();
    } //  tryCatch
  } //  createDb

  /*Open registration activity*/
  public void loadRegistration(View view) {
    Intent intent = new Intent(getApplicationContext(), UserRegistration.class);
    startActivity(intent);
  } //  loadRegistration

  /*logs in displaying userData including scores*/
  public void loadLogin(View view)  {
    btnActive();
    Intent intent = new Intent(MainActivity.this, MainActivity.class);
    startActivity(intent);
  } //  loadLogin

  /*Activating clickable images*/
  void btnActive(){
    Toast.makeText(this, "Images activated!", Toast.LENGTH_LONG).show();
//    ImageView imgCasino = (ImageView) findViewById(R.id.imgCasino);
//    ImageView imgClassList = (ImageView) findViewById(R.id.imgClassList);
    ImageView imgGuessingGame = (ImageView) findViewById(R.id.imgGuessingGame);
    ImageView imgMusic = (ImageView) findViewById(R.id.imgMusic);
    ImageView imgHighScore = (ImageView) findViewById(R.id.imgHighScore);
    ImageView imgOurGame = (ImageView) findViewById(R.id.imgOurGame);

//    imgCasino.setClickable(true);
//    imgClassList.setClickable(true);
    imgGuessingGame.setClickable(true);
    imgMusic.setClickable(true);
    imgHighScore.setClickable(true);
    imgOurGame.setClickable(true);

    /*Set onClickListeners*/
    imgGuessingGame.setOnClickListener(new View.OnClickListener() {
      public void onClick(View v) {
        Intent intent = new Intent(MainActivity.this, GuessingGame.class);
        startActivity(intent);
      } //  onClick
    });
    imgHighScore.setOnClickListener(new View.OnClickListener() {
      public void onClick(View v) {
        Intent intent = new Intent(MainActivity.this, HighScore.class);
        startActivity(intent);
      } //  onClick
    });
    imgMusic.setOnClickListener(new View.OnClickListener() {
      public void onClick(View v) {
        Intent intent = new Intent(MainActivity.this, Music.class);
        startActivity(intent);
      } //  onClick
    });
    imgOurGame.setOnClickListener(new View.OnClickListener() {
      public void onClick(View v) {
        Intent intent = new Intent(MainActivity.this, OurGame.class);
        startActivity(intent);
      } //  onClick
    });

  } //  btnActive

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
