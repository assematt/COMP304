package com.example.hangman;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.view.View;

public class DrawHuman extends View {
    public DrawHuman(Context context){
        super(context);
    }
    protected void onDraw(Canvas canvas)
    {
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStrokeWidth(8);
        paint.setStyle(Paint.Style.STROKE);

        GuessingGame gg = new GuessingGame();
        int attempts = gg.attempts;
        switch (attempts){
            case 9:
                //Head
                paint.setStyle(Paint.Style.FILL_AND_STROKE);
                canvas.drawOval(new RectF(400,20,600,280),paint);
                break;
            case 8:
                //Eyes
                //paint.setStyle(Paint.Style.STROKE);
                paint.setColor(Color.WHITE);
                canvas.drawOval(new RectF(420,100,480,130),paint);
                canvas.drawOval(new RectF(520,100,580,130),paint);
                break;
            case 7:
                //mouth
                canvas.drawOval(new RectF(460,200,540,230),paint);
                break;
            case 6:
                paint.setColor(Color.RED);
                //Ears
                canvas.drawOval(new RectF(370,100,400,150),paint);
                canvas.drawOval(new RectF(600,100,630,150),paint);
                break;
            case 5:
                //paint.setStyle(Paint.Style.FILL);
                //neck
                canvas.drawRect(490,280,510,350,paint);
                break;
            case 4:
                //chest
                canvas.drawRect(450,350,550,800,paint);
                break;
            case 3:
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
                break;

            case 2:
                //legs
                Path paintPath2 = new Path();
                paintPath2.reset();
                paintPath2.moveTo(200,1100);
                paintPath2.lineTo(490, 800);
                paintPath2.lineTo(470, 780);
                paintPath2.lineTo(180, 1080);
                paintPath2.lineTo(200, 1100);
                canvas.drawPath(paintPath2,paint);
                break;

            case 1:

                Path paintPath3 = new Path();
                paintPath3.reset();
                paintPath3.moveTo(800,1100);
                paintPath3.lineTo(510, 800);
                paintPath3.lineTo(530, 780);
                paintPath3.lineTo(820, 1080);
                paintPath3.lineTo(800, 1100);
                canvas.drawPath(paintPath3,paint);
                break;

            case 0:
                //The day everyone waits for :)
                break;

            default: //Oh no, it's working day
                //This code is executed when value of variable 'day'
                //doesn't match with any of case above
                break;
        }



















        //draw an arc for rope
    }

}
