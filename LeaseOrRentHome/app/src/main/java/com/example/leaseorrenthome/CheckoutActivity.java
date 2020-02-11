package com.example.leaseorrenthome;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class CheckoutActivity extends AppCompatActivity {

    RadioGroup selectHouseRadioGroup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        LinearLayout layout = (LinearLayout) findViewById(R.id.checkout_layout);
        Bundle b = this.getIntent().getExtras();
        String[] selectedHouses=b.getStringArray("selectedHouses");

        selectHouseRadioGroup = new RadioGroup(this);

        for(int i=0; i<selectedHouses.length; i++)
        {
            String[] details = selectedHouses[i].split(":",3);
            String apt_name = details[0];
            String apt_address = details[1];
            String apt_price = details[2];

            TextView aptName = new TextView(this);
            aptName.setText(apt_name);
            aptName.setGravity(Gravity.CENTER_HORIZONTAL);

            TextView aptAddress = new TextView(this);
            aptAddress.setText(apt_address);
            aptAddress.setGravity(Gravity.CENTER_HORIZONTAL);
            TextView aptPrice = new TextView(this);
            aptPrice.setText(apt_price);
            aptPrice.setGravity(Gravity.CENTER_HORIZONTAL);

            RadioButton selectHouseRadio = new RadioButton(this);
            selectHouseRadio.setId(i);
            selectHouseRadio.setText(apt_name+"\n"+apt_address+"\n"+apt_price+"\n");

            selectHouseRadioGroup.addView(selectHouseRadio);
        }
        layout.addView(selectHouseRadioGroup);
    }
    public void LoadPaymentOptionsActivity(View view)
    {
        Intent intent = new Intent(this, PaymentOptionsActivity.class);

        int selectedId = selectHouseRadioGroup.getCheckedRadioButtonId();

        RadioButton radioButton = (RadioButton) findViewById(selectedId);

        intent.putExtra("purchaseDetails",radioButton.getText());
        startActivity(intent);
    }
}
