package com.example.g3.leaseorrenthome;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ConfirmationActivity extends AppCompatActivity {

    String purchaseDetails;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);

        purchaseDetails = this.getIntent().getStringExtra("purchaseDetails");
        String amountDetails = purchaseDetails.split(":")[0];
        String amount = amountDetails.split("\\r?\\n")[2];

        TextView amountText = findViewById(R.id.amount_text);
        amountText.setText("Your payment of "+amount+" was successfully completed!\n\n"+amountDetails.split("\\r?\\n")[1]+"\n"+amountDetails.split("\\r?\\n")[0]);

    }
}
