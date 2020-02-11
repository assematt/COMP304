package com.example.leaseorrenthome;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import static java.lang.System.exit;

public class CreditCardActivity extends AppCompatActivity {

    String purchaseDetails;
    String buyer_name = "Jokku";
    EditText editText;
    EditText cvv;
    EditText name;
    EditText expiry;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit_card);

        purchaseDetails = this.getIntent().getStringExtra("purchaseDetails");

        String amount = purchaseDetails.split("\\r?\\n")[2];
        TextView amount_text = findViewById(R.id.amount);
        amount_text.setText(amount);

        editText = findViewById(R.id.card_number);
        cvv = findViewById(R.id.cvv);
        name = findViewById(R.id.card_holder);
        expiry = findViewById(R.id.expiry_date);

    }

    public void LoadConfirmationActivity(View view)
    {
        boolean canLoad = true;
        if(isEmpty(editText)){
            canLoad = false;
            setError(editText, "Enter card number");
        }
        else
        {
            clearError(editText);
        }
        if(isEmpty(cvv)){
            canLoad = false;
            setError(cvv, "Enter cvv");
        }
        else
        {
            clearError(cvv);
        }
        if(isEmpty(name)){
            canLoad = false;
            setError(name, "Enter your name");
        }
        else
        {
            clearError(name);
        }
        if(isEmpty(expiry)){
            canLoad = false;
            setError(expiry, "Enter expiry date");
        }
        else
        {
            clearError(expiry);
        }
        if(canLoad){
            Intent intent = new Intent(this, ConfirmationActivity.class);
            intent.putExtra("purchaseDetails",purchaseDetails+":"+buyer_name );
            startActivity(intent);
        }
    }

    public static boolean isEmpty(EditText editText) {

        String input = editText.getText().toString().trim();
        return input.length() == 0;

    }
    public static void setError(EditText editText, String errorString) {

        editText.setError(errorString);

    }

    public static void clearError(EditText editText) {

        editText.setError(null);

    }
}
