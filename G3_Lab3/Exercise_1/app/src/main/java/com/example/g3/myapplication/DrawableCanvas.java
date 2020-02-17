package com.example.g3.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Shader;
import android.view.View;

public class DrawableCanvas extends View {

    public String whatToDraw = "Car";

    public DrawableCanvas(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // Draws a car

        switch (whatToDraw) {
            case "Car": DrawCar(canvas);
                break;

            case "Flag": DrawFlag(canvas);
                break;

            case "Arcs": DrawArcs(canvas);
                break;
        }

    }

    private void DrawCar(Canvas canvas) {
        // Create the paint object
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(14);

        // Draw the body
        canvas.drawRect(200, 365, 725, 450, paint);

        // Draws the wheels
        canvas.drawCircle(285, 460, 40, paint);
        canvas.drawCircle(660, 460, 40, paint);

        // Draw the top part
        Path topPart = new Path();
        topPart.reset();
        topPart.moveTo(200,365);
        topPart.lineTo(285,250);
        topPart.lineTo(585,250);
        topPart.lineTo(645,450);
        canvas.drawPath(topPart, paint);
    }

    private void DrawFlag(Canvas canvas) {
        // Create the paint object
        Paint paint = new Paint();
        paint.setColor(Color.RED);

        // Draws the 2 sides
        canvas.drawRect(0, 0, 250, 500, paint);
        canvas.drawRect(canvas.getWidth() - 250, 0, canvas.getWidth(), 500, paint);

        // Draw the weed leaf
        Path leaf = new Path();
        leaf.reset();

        float scale = 200;

        leaf.moveTo(1, -3);
        leaf.lineTo(5, -4);
        leaf.lineTo(4, -3);
        leaf.lineTo(9, 1);
        leaf.lineTo(7, 2);
        leaf.lineTo(8, 5);
        leaf.lineTo(5, 4);
        leaf.lineTo(5, 5);
        leaf.lineTo(3, 4);
        leaf.lineTo(4, 9);
        leaf.lineTo(2, 7);
        leaf.lineTo(0, 10);
        leaf.lineTo(-2, 7);
        leaf.lineTo(-4, 8);
        leaf.lineTo(-3, 3);
        leaf.lineTo(-5, 6);
        leaf.lineTo(-5, 4);
        leaf.lineTo(-8, 5);
        leaf.lineTo(-7, 2);
        leaf.lineTo(-9, 1);
        leaf.lineTo(-4, -3);
        leaf.lineTo(-5, -4);
        leaf.lineTo(-1, -3);
        leaf.lineTo(-1, -6);
        leaf.lineTo(1, -6);
        leaf.lineTo(1, -3);

        Matrix scaleMatrix = new Matrix();
        scaleMatrix.preScale(21, -21);
        scaleMatrix.preTranslate(26, -15);
        leaf.transform(scaleMatrix);

        canvas.drawPath(leaf, paint);
    }

    private void DrawArcs(Canvas canvas) {
        // Create the paint object
        Paint paint = new Paint();
        paint.setStrokeWidth(15);

        int size = 50;
        LinearGradient linearGradient = new LinearGradient(0, 0, size, size, Color.RED, Color.BLUE, Shader.TileMode.MIRROR);
        paint.setShader(linearGradient);

        for (int x = -size; x < canvas.getWidth(); x += size)
        {
            for (int y = -size; y < canvas.getHeight(); y += size / 2)
            {
                RectF arcRect = new RectF(x, y, size + x, size + y);
                canvas.drawArc(arcRect, 0, 90, false, paint);
                canvas.drawArc(arcRect, 90, 90, false, paint);
            }
        }
    }
}
