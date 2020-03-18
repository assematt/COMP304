//package com.example.guessinggame;
//
//import android.content.ContentValues;
//import android.database.sqlite.SQLiteDatabase;
//import android.util.Log;
//import android.widget.Toast;
//
//public class Database {
//  private String userName;
//  private String password;
//  private static SQLiteDatabase UserScore;  /*Database - Object*/
//  private final String dbName = "user_score";
//  private final String dbTableCreate = "CREATE TABLE IF NOT EXISTS USER" +
//          "(ID INTEGER PRIMARY KEY AUTOINCREMENT," +
//          "USERNAME TEXT," +
//          "PASSWORD TEXT," +
//          "SCORE INTEGER," +
//          "USERPICTURE LONGBLOB);"; /*Table Creation*/
//
//  /*Insert Entries
//  * 1. Username + Password => UserRegistration    Target = Username + Password + Image
//  * 2. Score => Login*/
//  public void insertEntry(Class className, String userName, String password){
//    try{
//      ContentValues newValues = new ContentValues();
//      newValues.put("USERNAME", userName);
//      newValues.put("PASSWORD", password);
//      //  Store image
//      Toast.makeText(this, userName + " added successfully!", Toast.LENGTH_LONG).show();
//    } catch (Exception e){
//      Log.e("Note", "Exception: " + e);
//      Toast.makeText(this, "Error, Check log!", Toast.LENGTH_LONG).show();
//    } //  tryCatch
//  } //  InsertUserName
//} //  classEnd
