package com.example.g3.leaseorrenthome;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class HomeTypeActivity extends AppCompatActivity {
    private static final String TAG = "MenuActivity";

    private boolean showaApartments = true;
    private boolean showDetached = true;
    private boolean showSemiDetached = true;
    private boolean showCondo = true;
    private boolean showTownHouse = true;
    //String[] selectedHouses;
    ArrayList<CheckBox> checkBoxes = new ArrayList<CheckBox>();
    //CheckBox[] checkBoxes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_type);

        String menuItemId ="";
        if(getIntent().getExtras() != null)
        {
            menuItemId = getIntent().getExtras().getString("id");
            if(menuItemId !=null)
            {
                if(!menuItemId.equals("menu_all")) {
                    showaApartments = false;
                    showDetached = false;
                    showSemiDetached = false;
                    showTownHouse = false;
                    showCondo = false;
                }
                if(menuItemId.equals("menu_apartment")){
                    showaApartments = true;
                }
                else if(menuItemId.equals("menu_detached")){
                    showDetached = true;
                }
                else if(menuItemId.equals("menu_semidetachedt")){
                    showSemiDetached = true;
                }
                else if(menuItemId.equals("menu_condo")){
                    showCondo = true;
                }
                else{
                    showTownHouse = true;
                }
            }
        }

        LinearLayout layout = (LinearLayout) findViewById(R.id.HomeTypeLayout);

        String[] apartmentDetails = getResources().getStringArray(R.array.apartment_details);
        TypedArray apartmentImgs = getResources().obtainTypedArray(R.array.apartment_imgs);

        String[] detachedDetails = getResources().getStringArray(R.array.detached_details);
        TypedArray detachedImgs = getResources().obtainTypedArray(R.array.detached_imgs);

        String[] semidetachedDetails = getResources().getStringArray(R.array.semidetached_details);
        TypedArray semidetachedImgs = getResources().obtainTypedArray(R.array.semidetached_imgs);

        String[] townHouseDetails = getResources().getStringArray(R.array.townhouse_details);
        TypedArray townHouseImgs = getResources().obtainTypedArray(R.array.townhouse_imgs);

        String[] condoDetails = getResources().getStringArray(R.array.condominum_details);
        TypedArray condoImgs = getResources().obtainTypedArray(R.array.condominum_imgs);

        if(showaApartments)
        {
            DisplayHouses(apartmentDetails, apartmentImgs, layout);
        }
        if(showDetached)
        {
            DisplayHouses(detachedDetails, detachedImgs, layout);
        }
        if(showSemiDetached)
        {
            DisplayHouses(semidetachedDetails, semidetachedImgs, layout);
        }
        if(showTownHouse)
        {
            DisplayHouses(townHouseDetails, townHouseImgs, layout);
        }
        if(showCondo)
        {
            DisplayHouses(condoDetails, condoImgs, layout);
        }

    }

    private void DisplayHouses(String[] houseDetails, TypedArray imgs, LinearLayout layout)
    {
        for(int i=0; i<houseDetails.length; i++)
        {
            CheckBox checkBox = new CheckBox(this);
            checkBox.setId(i+1);
            checkBox.setTag(houseDetails[i]);
            checkBoxes.add(checkBox);


            ImageView imageView = new ImageView(this);
            imageView.setImageResource(imgs.getResourceId(i, -1));
            int height=600;
            int width=1450;
            imageView.setLayoutParams(new LinearLayout.LayoutParams(width, height));
            imageView.requestLayout();
            String[] details = houseDetails[i].split(":",3);
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
            layout.addView(checkBox);
            layout.addView(imageView);
            layout.addView(aptName);
            layout.addView(aptAddress);
            layout.addView(aptPrice);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.home_types, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        Intent intent = new Intent(this,HomeTypeActivity.class);

        switch (item.getItemId())
        {
            case R.id.menu_apartment:
                showDetached = false;
                intent.putExtra("id", "menu_apartment");
                startActivity(intent);
                break;
            case R.id.menu_detached:
                intent.putExtra("id", "menu_detached");
                startActivity(intent);
                break;
            case R.id.menu_semidetachedt:
                intent.putExtra("id", "menu_semidetachedt");
                startActivity(intent);
                break;
            case R.id.menu_condo:
                intent.putExtra("id", "menu_condo");
                startActivity(intent);
                break;
            case R.id.menu_townhouse:
                intent.putExtra("id", "menu_townhouse");
                startActivity(intent);
                break;
            case R.id.menu_all:
                intent.putExtra("id", "menu_all");
                startActivity(intent);
                break;
        }
        return true;
    }

    public void LoadCheckoutActivity(View view)
    {
        ArrayList<String> selectedHouses = new ArrayList<String>();

        for(int i = 0; i < checkBoxes.size(); i++){
            Log.d("AAAA","OOOO");
            if(checkBoxes.get(i).isChecked()){
                Log.d("FFFF","GGGG");
                selectedHouses.add(checkBoxes.get(i).getTag().toString());

            }
        }
        String[] selectedHousesArray = new String[selectedHouses.size()];
        for(int i = 0; i < selectedHousesArray.length; i++){
            selectedHousesArray[i] = selectedHouses.get(i);
        }
        Intent intent = new Intent(this, CheckoutActivity.class);
        Bundle b = new Bundle();
        b.putStringArray("selectedHouses", selectedHousesArray);
        intent.putExtras(b);
        startActivity(intent);
    }
}
