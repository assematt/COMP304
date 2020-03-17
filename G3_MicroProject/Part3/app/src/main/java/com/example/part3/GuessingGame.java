package com.example.part3;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.ToneGenerator;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class GuessingGame extends AppCompatActivity {

    private static final String TAG = "GuessingGame";

    static final String[] numbers  = new String[]{"1","2","3","4","5","6","7","8","9","10",
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
    int attempts = 10;               // number of attempts/chances
    int score = attempts * 10;       // score
    int timeToPlay;
    int guessNumber;

    boolean timeNotExhausted = true; // checker for time limit

    String musicToPlay;
    MediaPlayer mediaPlayer;
    TextView timer;


    ProgressBar progressBar;         // progress bar

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guessing_game);


            Intent intent = getIntent();
            musicToPlay = intent.getStringExtra("musicToPlay");

            if(musicToPlay.equals("Song 1")){
                mediaPlayer = MediaPlayer.create(this,R.raw.bensound_summer);
            }
            if(musicToPlay.equals( "Song 2")){
                mediaPlayer = MediaPlayer.create(this,R.raw.bensound_ukulele);
            }
            if(musicToPlay.equals( "Song 3")){
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

        Button btnGuess = (Button) findViewById( R.id.btnGuess );

        btnGuess.setOnClickListener( new View.OnClickListener()
        {
            public void onClick( View v )
            {
                if( timeNotExhausted ) // if the time limit is not reached yet, user is allowed to guess
                {
                    guess(v);
                }
            }
        });
    }

    public void displayToastResult( String result )
    {
        Toast.makeText( this, result, Toast.LENGTH_SHORT ).show();
    }

    public void guess( View view )
    {
        //EditText edtNum = (EditText) findViewById( R.id.edtNum );
        GridView gridNum = (GridView) findViewById(R.id.numGrid);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,numbers);

        gridNum.setAdapter(adapter);
        gridNum.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //((TextView) view).setTextColor(0xD86565);
                guessNumber = Integer.parseInt(((TextView)view).getText().toString());

            }
        });

        try
        {
            //int guessNumber = Integer.parseInt( edtNum.getText().toString() ); // number entered by user as a guess


            if ( attempts == 0 ) // if chances are exhausted, display loseMessage from strings.xml as a Toast
            {
                displayToastResult( getString( R.string.loseMessage ) );
                mediaPlayer.stop();
                disable();
            }
            else
            {
                if ( guessNumber > randNum ) // if guess is greater than the number generated by system, display greatMessage from strings.xml as a Toast
                {
                    displayToastResult( getString( R.string.greatMessage ) );
                }
                else if (guessNumber < randNum) // if guess is less than the number generated by system, display lessMessage from strings.xml as a Toast
                {
                    displayToastResult( getString( R.string.lessMessage ) );
                }
                else // if guess is the number generated by system, display winMessage from strings.xml as a Toast, generate beep sound and vibrate phone
                {
                    displayToastResult( getString( R.string.winMessage ) );
                    mediaPlayer.stop();
                    beepAndVibrate();
                    disable();
                }
            }

            updateAttempts(); // update the chances/attempts to try on display
            updateScore( attempts ); // update the score on the display based on the attempts/chances left
        }
        catch ( Exception e )
        {
            displayToastResult( getString( R.string.invalidNumber ) );
        }

        clear();
    }

    public void updateAttempts()
    {
        TextView attemptsView = (TextView) findViewById(R.id.tvAttempts);
        attemptsView.setText(String.valueOf(attempts));
        attempts--;
    }

    public void updateScore( int attempts )
    {
        TextView scoreView = (TextView) findViewById( R.id.tvScore );
        scoreView.setText( String.valueOf( score ) );
        score = attempts * 10;
    }

    public void clear()
    {
        //EditText edtNum = (EditText) findViewById( R.id.edtNum );
        //edtNum.setText( "" );
    }

    public void disable()
    {
       // EditText edtNum = (EditText) findViewById( R.id.edtNum );
      //  edtNum.setEnabled( false );
        timeNotExhausted = false;
        progressBar.setProgress(0);
        afterFinish();
    }

    public void afterFinish()
    {
        Button btnRestart = (Button) findViewById( R.id.btnGuess );
        btnRestart.setText( "Restart" );
        timeNotExhausted = false;
        progressBar.setProgress(0);

        final Intent intent = new Intent(this, MainActivity.class);
        btnRestart.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mediaPlayer.stop();
                startActivity(intent);
            } //  onClick
        });
    }

    public void beepAndVibrate()
    {
        ToneGenerator toneGenerator = new ToneGenerator( AudioManager.STREAM_MUSIC, 100 );
        toneGenerator.startTone( ToneGenerator.TONE_PROP_BEEP, 1400 ); // start the TONE_PROP_BEEP sound when the user wins

        Vibrator vibrator = (Vibrator) getSystemService( Context.VIBRATOR_SERVICE );
        vibrator.vibrate( VibrationEffect.createOneShot( 700, VibrationEffect.DEFAULT_AMPLITUDE ) ); // start the vibration effect when the user wins
    }


}
