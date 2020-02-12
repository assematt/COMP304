package com.example.g3.lorentzformula;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        String[] details = this.getIntent().getStringExtra("details").split(":");
        TextView result = findViewById(R.id.result_text);
        result.setText(details[0]);

        ImageView imageView = new ImageView(this);
        if(details[1].equals("gain"))
        {
            imageView.setImageResource(R.drawable.gain);
        }
        else
        {
            imageView.setImageResource(R.drawable.loss);
        }
        int height=600;
        int width=600;
        imageView.setLayoutParams(new LinearLayout.LayoutParams(width, height));
        imageView.requestLayout();


        RelativeLayout layout = (RelativeLayout) findViewById(R.id.ResultView);
        layout.addView(imageView);

        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)imageView.getLayoutParams();
        params.addRule(RelativeLayout.CENTER_HORIZONTAL);
        params.addRule(RelativeLayout.ABOVE, R.id.result_text);

        imageView.setLayoutParams(params); //causes layout update
    }
}
