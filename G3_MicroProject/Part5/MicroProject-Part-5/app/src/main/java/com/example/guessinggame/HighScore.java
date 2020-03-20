package com.example.guessinggame;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class HighScore extends AppCompatActivity {
  String userName, password;
  Boolean bool = false;
  private static SQLiteDatabase UserScore;
  private final String dbName = "user_score";
  TextView txtScore;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.high_score);
    openDatabase();
    userName = getIntent().getStringExtra("userName");
    password = getIntent().getStringExtra("password");
    fetchScore();
  } //  onCreate

  /*Get score from DB*/
  public void fetchScore(){
    try{
//      openDatabase();
      txtScore = (TextView) findViewById(R.id.txtScore);
      String query = "SELECT * FROM USER WHERE USERNAME = '" + userName + "' AND PASSWORD = '" + password + "'";
      Cursor rs = UserScore.rawQuery(query, null);
      rs.moveToFirst();
      txtScore.setText(rs.getString(4));
    } catch(Exception e){
      Toast.makeText(this, "Check log (loginCheck)!", Toast.LENGTH_SHORT).show();
      Log.e("scoreCheck", "Exception: " + e);
    } //  tryCatch
  } //  score

  public void openDatabase(){
    try {
      UserScore = SQLiteDatabase.openDatabase(getApplicationContext().getDatabasePath(dbName).getPath(), null, SQLiteDatabase.OPEN_READONLY);
//      Toast.makeText(this, "Database connection success!", Toast.LENGTH_SHORT).show();
    } catch (Exception e) {
      Log.e("DbError", "Exception: " + e);
      Toast.makeText(this, "Check log (DbError)!", Toast.LENGTH_LONG).show();
    } //  tryCatch
  } //  createDb

} //  classEnd
