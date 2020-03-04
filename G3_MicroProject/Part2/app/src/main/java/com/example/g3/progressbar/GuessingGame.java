package com.example.g3.progressbar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.ToneGenerator;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class GuessingGame extends AppCompatActivity
{
    private static final String TAG = "GuessingGame";

    int randNum;
    int attempts = 10;
    int score = attempts * 10;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guessing_game);

        Random random = new Random();
        randNum = random.nextInt(100);

        Log.d(TAG,"randNum: " + randNum);

        Button btnGuess = (Button) findViewById( R.id.btnGuess );
        btnGuess.setOnClickListener( new View.OnClickListener()
        {
            public void onClick( View v )
            {
                guess(v);
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
        afterFinish();
    }

    public void afterFinish()
    {
        Button btnRestart = (Button) findViewById( R.id.btnGuess );
        btnRestart.setText( "Restart" );

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
