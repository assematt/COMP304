package com.example.g3.leaseorrenthome;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class PaymentOptionsActivity extends AppCompatActivity {
    String purchaseDetails;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_options);

        purchaseDetails = this.getIntent().getStringExtra("purchaseDetails");
        String details[] = purchaseDetails.split("\\r?\\n");
        TextView amount = findViewById(R.id.amount);
        amount.setText(details[2]);
    }

    public void LoadCreditCradActivity(View view)
    {
        Intent intent = new Intent(this, CreditCardActivity.class);
        intent.putExtra("purchaseDetails",purchaseDetails );
        startActivity(intent);
    }
}
