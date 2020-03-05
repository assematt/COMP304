package com.example.g3.progressbar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.ToneGenerator;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class GuessingGame extends AppCompatActivity
{
    private static final String TAG = "GuessingGame";

    int randNum;
    int attempts = 10;
    int score = attempts * 10;

    boolean timeNotExhausted = true;

    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guessing_game);

        progressBar = ( ProgressBar ) findViewById( R.id.progressBar );

        Intent intent = getIntent();
        final int timeToPlay = intent.getIntExtra("timeToPlay", 60);

        new CountDownTimer(timeToPlay * 1000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                Log.d( TAG,"timeToPlay: " + timeToPlay );
                float progressCounter = millisUntilFinished/1000;
                float progressPercentage = (progressCounter / timeToPlay) * 100;
                Log.d( TAG, "progressCounter: " + progressCounter);
                Log.d( TAG, "progressPercentage: " + progressPercentage);
                int progress = (int) progressPercentage;
                Log.d( TAG, "progress: " + progress);

                if( timeNotExhausted )
                {
                    progressBar.setProgress(progress);
                }
            }
            @Override
            public void onFinish() {
                timeNotExhausted = false;
                progressBar.setProgress(0);
                displayToastResult( getString( R.string.timeover ) );
                disable();
            }
        }.start();

        Random random = new Random();
        randNum = random.nextInt(100);

        Button btnGuess = (Button) findViewById( R.id.btnGuess );

        btnGuess.setOnClickListener( new View.OnClickListener()
        {
            public void onClick( View v )
            {
                if( timeNotExhausted )
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
        EditText edtNum = (EditText) findViewById( R.id.edtNum );
        try
        {
            int guessNumber = Integer.parseInt( edtNum.getText().toString() );

            if ( attempts == 0 )
            {
                displayToastResult( getString( R.string.loseMessage ) );
                disable();
            }
            else
            {
                if ( guessNumber > randNum )
                {
                    displayToastResult( getString( R.string.greatMessage ) );
                }
                else if (guessNumber < randNum) {
                    displayToastResult( getString( R.string.lessMessage ) );
                }
                else
                {
                    displayToastResult( getString( R.string.winMessage ) );
                    beepAndVibrate();
                    disable();
                }
            }

            updateAttempts();
            updateScore( attempts );
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
        EditText edtNum = (EditText) findViewById( R.id.edtNum );
        edtNum.setText( "" );
    }

    public void disable()
    {
        EditText edtNum = (EditText) findViewById( R.id.edtNum );
        edtNum.setEnabled( false );
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
                startActivity(intent);
            } //  onClick
        });
    }

    public void beepAndVibrate()
    {
        ToneGenerator toneGenerator = new ToneGenerator( AudioManager.STREAM_MUSIC, 100 );
        toneGenerator.startTone( ToneGenerator.TONE_PROP_BEEP, 1400 );

        Vibrator vibrator = (Vibrator) getSystemService( Context.VIBRATOR_SERVICE );
        vibrator.vibrate( VibrationEffect.createOneShot( 700, VibrationEffect.DEFAULT_AMPLITUDE ) );
    }
}
