package com.example.g3.animation;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class DrawLines extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Canvas canvas;
    Paint paint;
    SurfaceView surfaceView;
    int xval = 100;
    int yval = 400;
    Path paintPath;
    int color = Color.RED;
    int increment =1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw_lines);

        final ArrayList<Integer> thickness_list = new ArrayList<>();
        thickness_list.add(10);
        thickness_list.add(20);
        thickness_list.add(30);

        Spinner spinner = (Spinner) findViewById(R.id.spinner);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,thickness_list);

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);


        surfaceView = (SurfaceView) findViewById(R.id.canvas_layout);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(surfaceView.getWidth(), surfaceView.getHeight());






        surfaceView.getHolder().addCallback(new SurfaceHolder.Callback() {

                                            @Override
                                            public void surfaceCreated(SurfaceHolder holder) {


                                                RadioGroup radioGroup = (RadioGroup) findViewById(R.id.color_group);
                                                int selectedId = radioGroup.getCheckedRadioButtonId();
                                                final RadioButton radioButton = (RadioButton) findViewById(selectedId);

                                                radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
                                                {
                                                    @Override
                                                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                                                        TextView textView = (TextView) findViewById(R.id.linecolor_txt);



                                                        RadioButton radioButton = (RadioButton) findViewById(checkedId);
                                                        String newColor =  radioButton.getText().toString();

                                                        if(newColor.equals("Red")){

                                                            color = Color.RED;
                                                        }
                                                        if(newColor.equals("Yellow")){

                                                            color = Color.YELLOW;
                                                        }
                                                        if(newColor.equals("Blue")){

                                                            color = Color.BLUE;
                                                        }
                                                        paint.setColor(color);
                                                    }
                                                });

                                                canvas = holder.lockCanvas();

                                                canvas.drawColor( Color.BLACK);


                                                paint = new Paint();
                                                paint.setColor(color);
                                                paint.setStrokeWidth(8);
                                                paint.setStyle(Paint.Style.STROKE);
                                                paintPath = new Path();

                                                paintPath.moveTo(100,400);
                                                holder.unlockCanvasAndPost(canvas);



                                                ImageButton right = (ImageButton) findViewById(R.id.arrow_right);
                                                right.setOnTouchListener(new View.OnTouchListener() {
                                                    public boolean onTouch(View v, MotionEvent event) {

                                                        if (event.getAction() == MotionEvent.ACTION_DOWN) {

                                                                canvas = surfaceView.getHolder().lockCanvas();


                                                            //Path p  = new Path();
                                                            paintPath.moveTo(xval-5,yval);
                                                            xval = xval+ 5;

                                                            paintPath.lineTo(xval, yval);

                                                                canvas.drawPath(paintPath,paint);

                                                                surfaceView.getHolder().unlockCanvasAndPost(canvas);

                                                        }
                                                        else if(event.getAction() == MotionEvent.ACTION_BUTTON_RELEASE){
                                                            //increment = 0;
                                                        }

                                                        return false;
                                                    }

                                                });

                                                ImageButton left = (ImageButton) findViewById(R.id.arrow_left);
                                                left.setOnTouchListener(new View.OnTouchListener() {
                                                    public boolean onTouch(View v, MotionEvent event) {

                                                        if (event.getAction() == MotionEvent.ACTION_DOWN) {

                                                            canvas = surfaceView.getHolder().lockCanvas();
                                                            //Path p  = new Path();
                                                            paintPath.moveTo(xval+5,yval);
                                                            xval = xval- 5;

                                                            paintPath.lineTo(xval, yval);

                                                            canvas.drawPath(paintPath,paint);
                                                            surfaceView.getHolder().unlockCanvasAndPost(canvas);
                                                        }
                                                        else if(event.getAction() == MotionEvent.ACTION_BUTTON_RELEASE){
                                                            //increment = 0;
                                                        }

                                                        return false;
                                                    }

                                                });

                                                ImageButton up = (ImageButton) findViewById(R.id.arrow_up);
                                                up.setOnTouchListener(new View.OnTouchListener() {
                                                    public boolean onTouch(View v, MotionEvent event) {

                                                        if (event.getAction() == MotionEvent.ACTION_DOWN) {

                                                            canvas = surfaceView.getHolder().lockCanvas();
                                                            //Path p  = new Path();
                                                            paintPath.moveTo(xval,yval+5);
                                                            yval = yval- 5;

                                                            paintPath.lineTo(xval, yval);

                                                            canvas.drawPath(paintPath,paint);
                                                            surfaceView.getHolder().unlockCanvasAndPost(canvas);

                                                        }
                                                        else if(event.getAction() == MotionEvent.ACTION_BUTTON_RELEASE){
                                                            //increment = 0;
                                                        }

                                                        return false;
                                                    }

                                                });

                                                ImageButton down = (ImageButton) findViewById(R.id.arrow_down);
                                                down.setOnTouchListener(new View.OnTouchListener() {
                                                    public boolean onTouch(View v, MotionEvent event) {

                                                        if (event.getAction() == MotionEvent.ACTION_DOWN) {

                                                            canvas = surfaceView.getHolder().lockCanvas();
                                                           // Path p  = new Path();
                                                            paintPath.moveTo(xval,yval-5);
                                                            yval = yval+ 5;

                                                            paintPath.lineTo(xval, yval);

                                                            canvas.drawPath(paintPath,paint);
                                                            surfaceView.getHolder().unlockCanvasAndPost(canvas);

                                                        }
                                                        else if(event.getAction() == MotionEvent.ACTION_BUTTON_RELEASE){
                                                            //increment = 0;
                                                        }

                                                        return false;
                                                    }

                                                });

                                                Button button = (Button) findViewById(R.id.clear);
                                                button.setOnTouchListener(new View.OnTouchListener() {
                                                    public boolean onTouch(View v, MotionEvent event) {
                                                        canvas = surfaceView.getHolder().lockCanvas();

                                                        paintPath.moveTo(100,400);

                                                        paintPath.reset();
                                                        paintPath.rewind();
                                                        canvas.drawColor(Color.BLACK);
                                                        xval = 100;
                                                        yval = 400;
                                                        surfaceView.getHolder().unlockCanvasAndPost(canvas);
                                                        return  true;
                                                    }
                                                });


                                            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            }
        });


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String size = parent.getItemAtPosition(position).toString();
        paint.setStrokeWidth(Float.parseFloat(size));
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
