package com.example.g3.guessinggameguianddb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class HangmanActivity extends AppCompatActivity
{
    private static final String TAG = "GuessingGame";

    public static final String MyPREFERENCES = "MyPrefs" ;
    SharedPreferences sharedpreferences;

    static final String[] numbers  = new String[]{
            "1","2","3","4","5","6","7","8","9","10",
            "11","12","13","14","15","16","17","18","19","20",
            "21","22","23","24","25","26","27","28","29","30",
            "31","32","33","34","35","36","37","38","39","40",
            "41","42","43","44","45","46","47","48","49","50",
            "51","52","53","54","55","56","57","58","59","60",
            "61","62","63","64","65","66","67","68","69","70",
            "71","72","73","74","75","76","77","78","79","80",
            "81","82","83","84","85","86","87","88","89","90",
            "91","92","93","94","95","96","97","98","99","100",
    };

    int randNum;                     // random number
    int attempts = 9;                // number of attempts/chances
    int score = attempts * 10;       // score
    int timeToPlay;
    int guessNumber;

    boolean timeNotExhausted = true; // checker for time limit

    String musicToPlay;
    MediaPlayer mediaPlayer;
    TextView timer;
    int color = Color.RED;
    Canvas canvas;
    Paint paint;
    Path paintPath;
    SurfaceView surfaceView;
    SurfaceHolder holder;

    ProgressBar progressBar;         // progress bar

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hangman);

        Button btnGuess = (Button) findViewById( R.id.btnGuess );
        btnGuess.setVisibility(View.INVISIBLE);

        surfaceView = (SurfaceView) findViewById(R.id.canvas);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(surfaceView.getWidth(), surfaceView.getHeight());
        holder = surfaceView.getHolder();

        Intent intent = getIntent();
        musicToPlay = intent.getStringExtra("musicToPlay");

        GridView numGrid = (GridView) findViewById(R.id.numGrid);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.grid_text_view,numbers);
        numGrid.setAdapter(adapter);

        if(musicToPlay.equals("Song 1"))
        {
            mediaPlayer = MediaPlayer.create(this,R.raw.bensound_summer);
        }

        if(musicToPlay.equals( "Song 2"))
        {
            mediaPlayer = MediaPlayer.create(this,R.raw.bensound_ukulele);
        }

        if(musicToPlay.equals( "Song 3"))
        {
            mediaPlayer = MediaPlayer.create(this,R.raw.bensound_creativeminds);
        }

        mediaPlayer.start();

        progressBar = ( ProgressBar ) findViewById( R.id.progressBar );
        timeToPlay = mediaPlayer.getDuration();

        new CountDownTimer(timeToPlay,1000) // initiate a countdown timer based on the desired time to play by user
        {
            @Override
            public void onTick(long millisUntilFinished) // count down based on interval time
            {
                //Log.d( TAG,"timeToPlay: " + timeToPlay );
                float progressCounter = millisUntilFinished; // get the seconds on the current count down timer
                float progressPercentage = (progressCounter / timeToPlay) * 100; // get the percentage of progress
                //Log.d( TAG, "progressCounter: " + progressCounter);
                //Log.d( TAG, "progressPercentage: " + progressPercentage);

                if( timeNotExhausted ) // if time limit is not reached yet, update the progress bar based on the calculated percentage
                {
                    progressBar.setProgress( (int) progressPercentage );
                    timer = (TextView) findViewById(R.id.timer);
                    timer.setText("seconds remaining: " + millisUntilFinished/1000);
                }
            }
            @Override
            public void onFinish() // when time is exhausted, display the timeover message from strings.xml as Toast
            {
                timeNotExhausted = false;
                progressBar.setProgress(0);
                mediaPlayer.stop();
                timer.setText("seconds remaining: 0" );
                displayToastResult( getString( R.string.timeover ) );
                disable();
            }
        }.start();

        Random random = new Random();
        randNum = random.nextInt(100); // get a random number from 1-100

        Log.d(TAG, "timeNotExhausted: " + timeNotExhausted);
        numGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                if( timeNotExhausted ) // if the time limit is not reached yet, user is allowed to guess
                {

                    int number = Integer.parseInt(((TextView)view).getText().toString());
                    int result = guess(number);
                    Log.d(TAG, "result: " + result);

                    if( result < 0 )
                    {
                        view.setBackgroundColor(0xFFFFFF00);
                    }
                    else if( result > 0 )
                    {
                        view.setBackgroundColor(0xFFD86565);
                    }
                    else
                    {
                        view.setBackgroundColor(0xFF90EE90);
                        timeNotExhausted = false;
                        progressBar.setProgress(0);
                        mediaPlayer.stop();
                        timer.setText("seconds remaining: 0" );
                        disable();
                    }

                    updateAttempts(); // update the chances/attempts to try on display
                    updateScore( attempts ); // update the score on the display based on the attempts/chances left
                }
            }
        });
    }

    public void displayToastResult( String result )
    {
        Toast.makeText( this, result, Toast.LENGTH_SHORT ).show();
    }

    public int guess(int gridNumber)
    {
        guessNumber = gridNumber;

        if ( attempts == 0 ) // if chances are exhausted, display loseMessage from strings.xml as a Toast
        {
            displayToastResult( getString( R.string.loseMessage ) );
            timeNotExhausted = false;

            mediaPlayer.stop();
            disable();
        }

        if ( guessNumber > randNum ) // if guess is greater than the number generated by system, display greatMessage from strings.xml as a Toast
        {
            displayToastResult( getString( R.string.greatMessage ) );

            return 1;
        }
        else if (guessNumber < randNum) // if guess is less than the number generated by system, display lessMessage from strings.xml as a Toast
        {
            displayToastResult( getString( R.string.lessMessage ) );
            return -1;
        }
        else // if guess is the number generated by system, display winMessage from strings.xml as a Toast, generate beep sound and vibrate phone
        {
            Log.d(TAG, "Score: " + score);
            updateScoreInDatabase(score);

            displayToastResult( getString( R.string.winMessage ) );
            return 0;
            //mediaPlayer.stop();
            //disable();
        }

    }

    public void updateAttempts()
    {
        TextView attemptsView = (TextView) findViewById(R.id.tvAttempts);
        attemptsView.setText(String.valueOf(attempts));
        attempts--;
        drawMan(holder);
    }

    public void updateScore( int attempts )
    {
        TextView scoreView = (TextView) findViewById( R.id.tvScore );
        scoreView.setText( String.valueOf( score ) );
        score = attempts * 10;
    }

    public void disable()
    {
        timeNotExhausted = false;
        progressBar.setProgress(0);
        afterFinish();
    }

    public void afterFinish()
    {
        Button btnRestart = (Button) findViewById( R.id.btnGuess );
        btnRestart.setVisibility(View.VISIBLE);
        btnRestart.setText( "Restart" );
        timeNotExhausted = false;
        progressBar.setProgress(0);

        final Intent intent = new Intent(this, GuessingGame.class);

        btnRestart.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mediaPlayer.stop();
                startActivity(intent);
            } //  onClick
        });
    }

    public void drawMan(SurfaceHolder holder)
    {
        canvas = holder.lockCanvas();

        color = Color.RED;

        paint = new Paint();
        paint.setColor(color);
        paint.setStrokeWidth(8);
        paint.setStyle(Paint.Style.STROKE);
        paintPath = new Path();

        if (attempts < 10)
        {
            paint.setStyle(Paint.Style.FILL_AND_STROKE);
            canvas.drawOval(new RectF(400,20,600,280),paint);
        }

        if(attempts < 9)
        {
            paint.setStyle(Paint.Style.STROKE);
            paint.setColor(Color.WHITE);
            canvas.drawOval(new RectF(420,100,480,130),paint);
            canvas.drawOval(new RectF(520,100,580,130),paint);
        }

        if(attempts < 8)
        {
            //mouth
            canvas.drawOval(new RectF(460,200,540,230),paint);
        }

        if(attempts < 7)
        {
            paint.setColor(Color.RED);
            //Ears
            canvas.drawOval(new RectF(370,100,400,150),paint);
            canvas.drawOval(new RectF(600,100,630,150),paint);
        }

        if(attempts < 6)
        {
            paint.setStyle(Paint.Style.FILL);
            //neck
            canvas.drawRect(490,280,510,350,paint);
        }

        if(attempts < 5)
        {
            //chest
            canvas.drawRect(450,350,550,800,paint);
        }

        if(attempts < 4)
        {
            //hands
            Path paintPath = new Path();
            paintPath.reset();
            paintPath.moveTo(200,600);
            paintPath.lineTo(490, 350);
            paintPath.lineTo(470, 330);
            paintPath.lineTo(180, 580);
            paintPath.lineTo(200, 600);
            canvas.drawPath(paintPath,paint);

            paintPath.reset();
            paintPath.moveTo(800,600);
            paintPath.lineTo(510, 350);
            paintPath.lineTo(530, 330);
            paintPath.lineTo(820, 580);
            paintPath.lineTo(800, 600);
            canvas.drawPath(paintPath,paint);
        }

        if(attempts < 3)
        {
            //legs
            Path paintPath2 = new Path();
            paintPath2.reset();
            paintPath2.moveTo(200,1100);
            paintPath2.lineTo(490, 800);
            paintPath2.lineTo(470, 780);
            paintPath2.lineTo(180, 1080);
            paintPath2.lineTo(200, 1100);
            canvas.drawPath(paintPath2,paint);
        }

        if(attempts < 2)
        {

            Path paintPath3 = new Path();
            paintPath3.reset();
            paintPath3.moveTo(800,1100);
            paintPath3.lineTo(510, 800);
            paintPath3.lineTo(530, 780);
            paintPath3.lineTo(820, 1080);
            paintPath3.lineTo(800, 1100);
            canvas.drawPath(paintPath3,paint);
        }

        if(attempts < 1)
        {
            // draw noose
            paint.setStyle(Paint.Style.STROKE);
            paint.setColor(Color.YELLOW);
            RectF arcF = new RectF(100,50,1000,300);
            canvas.drawArc(arcF,0,180,false,paint);
        }

        holder.unlockCanvasAndPost(canvas);
    }

    public void updateScoreInDatabase(int score)
    {
        try
        {
            sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

            String user = sharedpreferences.getString("username", null);
            Log.d(TAG, "user: " + user);

            String query = "SELECT * FROM User WHERE lower(Username) = '" + user + "'";
            Log.d(TAG, "Query: " + query);
            Cursor rs = MainActivity.UserScore.rawQuery(query, null);
            rs.moveToFirst();
            Log.d(TAG, "rs.getCount(): " + rs.getCount());

            if( rs.getCount() > 0 ) // user found
            {
                Log.d(TAG, "got here rs count > 0");

                int prevScore = rs.getInt(2);
                Log.d(TAG, "prevScore: " + prevScore);

                int updatedScore = score + prevScore;
                Log.d(TAG, "updatedScore: " + updatedScore);

                ContentValues cv = new ContentValues();
                cv.put("Overall_Score", updatedScore);

                MainActivity.UserScore.update("User", cv, "lower(Username) = '" + user + "'", null);
            }
            else if( rs.getCount() == 0 )
            {
                Toast.makeText(this, "User not found!", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "User not found!");
            }
        }
        catch(Exception e)
        {
            Toast.makeText(this, "Check log (loginCheck)!", Toast.LENGTH_SHORT).show();
            Log.e("loginCheck", "Exception: " + e);
        }
    }
}