package com.example.guessinggame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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

    /*Registration button*/
    Button btnLogin = (Button) findViewById(R.id.btnLogin);
    btnLogin.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        /*Login steps here*/
      } //  onClick
    }); //  btnLogin
    /*btn Register END*/

    /*Adding link to textView*/
    TextView tvRegister = (TextView) findViewById(R.id.tvRegister);
    tvRegister.setOnClickListener(new View.OnClickListener(){
      public void onClick(View v){
        loadRegistration(v);
      } //  onClick
    }); //  tvRegister
    /*TextView link END*/

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
      Toast.makeText(this, "Database connection success!", Toast.LENGTH_LONG).show();
      UserScore = SQLiteDatabase.openDatabase(getApplicationContext().getDatabasePath("user_score").getPath(), null, SQLiteDatabase.OPEN_READONLY);
    } catch (Exception e) {
      //Create database
      Toast.makeText(this, "Database created successfully!", Toast.LENGTH_LONG).show();
      UserScore = openOrCreateDatabase("user_score", MODE_PRIVATE, null);
    } //  tryCatch
  } //  createDb

  public void insertEntry(){
    try{

      int score = calculateScore();
      ContentValues newValues = new ContentValues();
      newValues.put("SCORE", score);
      Toast.makeText(this, "Score has been updated!", Toast.LENGTH_LONG).show();
      //  Store image
    } catch (Exception e){
      Log.e("Note", "Exception: " + e);
      Toast.makeText(this, "Error, Check log!", Toast.LENGTH_LONG).show();
    } //  tryCatch
  } //  InsertUserName

//  public Object getImage(){
//    Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
//    startActivityForResult(intent, CAMERA);
//  } //  getImage

  public int calculateScore(){
    return 0;
  } //  calculateScore

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

  /*CHECK*/
  /*Doesn't exists*/

  /*Incorrect Password*/

  /**/

} //  classEnd
