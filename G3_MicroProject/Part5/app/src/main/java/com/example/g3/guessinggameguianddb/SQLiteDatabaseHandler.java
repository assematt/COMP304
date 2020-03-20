package com.example.g3.guessinggameguianddb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteDatabaseHandler extends SQLiteOpenHelper
{
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "UserScore";
    private static final String TABLE_NAME = "User";
    private static final String KEY_USERNAME = "Username";
    private static final String KEY_PASSWORD = "Password";
    private static final String KEY_OVERALL_SCORE = "Overall_Score";
    private static final String KEY_USER_PICTURE = "User_Picture";
    private static final String[] COLUMNS = { KEY_USERNAME, KEY_PASSWORD, KEY_OVERALL_SCORE, KEY_USER_PICTURE };

    public SQLiteDatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATION_TABLE = "CREATE TABLE IF NOT EXISTS User (Username VARCHAR(50), Password VARCHAR(50), Overall_Score INTEGER, User_Picture BLOB)";

        db.execSQL(CREATION_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // you can implement here migration process
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        this.onCreate(db);
    }

    public User getUser(String userName) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, // a. table
                COLUMNS, // b. column names
                " id = ?", // c. selections
                new String[] { String.valueOf(userName) }, // d. selections args
                null, // e. group by
                null, // f. having
                null, // g. order by
                null); // h. limit

        if (cursor != null)
            cursor.moveToFirst();

        User user = new User();
        user.setUsername(cursor.getString(0));
        user.setPassword(cursor.getString(1));
        user.setOverallscore(Integer.parseInt(cursor.getString(2)));
        user.setUserpicture(cursor.getString(3));

        return user;
    }

    public void addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_USERNAME, user.getUsername());
        values.put(KEY_PASSWORD, user.getPassword());
        values.put(KEY_OVERALL_SCORE, user.getOverallscore());
        values.put(KEY_USER_PICTURE, user.getUserpicture());
        // insert
        db.insert(TABLE_NAME,null, values);
        db.close();
    }
}
