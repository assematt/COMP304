package com.example.lab_assignment4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_layout);

        ImageView imageView = findViewById(R.id.imageView2);
        imageView.setImageResource(R.drawable.flag);
        //Create database
        SQLiteDatabase canadaDb = openOrCreateDatabase("canada_db", MODE_PRIVATE, null);

        //create and populate Province Table
        createAndPopulateProvinceTable(canadaDb);

        //create and populate Districts Table
        createAndPopulateDistrictsTable(canadaDb);

        //create and populate Municipality table
        createAndPopulateMunicipalityTable(canadaDb);

        //create and populate County table
        createAndPopulateCountyTable(canadaDb);

        //create and populate Town table
        createAndPopulateTownTable(canadaDb);

        //create and populate City table
        createAndPopulateCityTable(canadaDb);

        //create and populate Village table
        createAndPopulateVillageTable(canadaDb);

        //create and populate ward table
        createAndPopulateWardTable(canadaDb);

    }

    public void loadListView(View view)
    {
        Intent listview_intent = new Intent(MainActivity.this, ListActivity.class);
        startActivity(listview_intent);
    }

    public void loadSearchView(View view)
    {
        Intent search_view_intent = new Intent(MainActivity.this, Search_Activity.class);
        startActivity(search_view_intent);
    }

    void createAndPopulateProvinceTable(SQLiteDatabase canadaDb)
    {
        //create province table
        canadaDb.execSQL("create table if not exists Provinces (province_name varchar(20), country_name varchar(20), male_population varchar(20), female_population varchar(20),national_population varchar(20), area varchar(20), density varchar(20))");

        canadaDb.execSQL("delete from Provinces");

        //insert values to Provinces table
        canadaDb.execSQL("insert into Provinces values('British Columbia', 'Canada', '2510789', '2560547', '5105576', '922503.01(kmsq)', '5.0/kmsq')");
        canadaDb.execSQL("insert into Provinces values('Alberta', 'Canada', '2199434', '2171882', '4395586', '640330.46(kmsq)', '6.4/kmsq')");
        canadaDb.execSQL("insert into Provinces values('Saskatchewan', 'Canada', '591709', '582753', '1178657', '588243.54 (kmsq)', '1.9/kmsq')");
        canadaDb.execSQL("insert into Provinces values('Manitoba', 'Canada', '684173', '685292', '1373859', '552370.99 (kmsq)', '2.3/ kmsq')");
        canadaDb.execSQL("insert into Provinces values('Ontario', 'Canada', '7195105', '7371442', '14659616', '908699.33 (kmsq)', '14.8/kmsq')");
        canadaDb.execSQL("insert into Provinces values('Quebec', 'Canada', '4238281', '4246684', '8522800', '1356625.27(kmsq)', '6.0/kmsq')");
        canadaDb.execSQL("insert into Provinces values('New Brunswick', 'Canada', '384165', '392662', '780021', '71388.81(kmsq)', '10.5/kmsq')");
        canadaDb.execSQL("insert into Provinces values('Nova Scotia', 'Canada', '475478', '495917', '976768', '52942.27(kmsq)', '17.4/kmsq')");
        canadaDb.execSQL("insert into Provinces values('Prince Edward Island', 'Canada', '77182', '79765', '157901', '5686.03(kmsq)', '25.1/kmsq')");
        canadaDb.execSQL("insert into Provinces values('Newfoundland and Labrador', 'Canada', '258030', '263512', '521922', '370514.08(kmsq)', '1.4/kmsq')");
        canadaDb.execSQL("insert into Provinces values('Yukon', 'Canada', '20788', '20066', '41022', '474712.68(kmsq)', '0.08/kmsq')");
        canadaDb.execSQL("insert into Provinces values('Northwest Territories', 'Canada', '23031', '21795', '44895', '1143793.86(kmsq)', '0.04/kmsq')");
        canadaDb.execSQL("insert into Provinces values('Nunavut', 'Canada', '19920', '18860', '38873', '1877778.53(kmsq)', '0.02/kmsq')");
    }

    void createAndPopulateDistrictsTable(SQLiteDatabase canadaDb)
    {
        //Create Districts table
        canadaDb.execSQL("create table if not exists Districts (district_name varchar(20), province_name varchar(20), male_population varchar(20), female_population varchar(20),national_population varchar(20), area varchar(20), density varchar(20))");

        canadaDb.execSQL("delete from Districts");

        //populate Districts Table
        canadaDb.execSQL("insert into Districts values('Abbotsford', 'British Columbia', '51789', '20547', '55576', '9203.01(kmsq)', '5.0/kmsq')");
        canadaDb.execSQL("insert into Districts values('Ajax', 'Ontario', '2510789', '60547', '51576', '2503.01(kmsq)', '5.0/kmsq')");
        canadaDb.execSQL("insert into Districts values('Central Nova', 'Nova Scotia Columbia', '20789', '25547', '51076', '9503.01(kmsq)', '2.0/kmsq')");
        canadaDb.execSQL("insert into Districts values('Central', 'Quebec', '35189', '25607', '51076', '9503.01(kmsq)', '3.2/kmsq')");
        canadaDb.execSQL("insert into Districts values('Nunavut', 'Nunavut', '25109', '25647', '51056', '9203.01(kmsq)', '2.3/kmsq')");
        canadaDb.execSQL("insert into Districts values('Blackstrap', 'Saskatchewan', '25189', '25547', '55576', '9223.01(kmsq)', '5.0/kmsq')");
        canadaDb.execSQL("insert into Districts values('Calgary Centre', 'Alberta', '25109', '5647', '51576', '9223.01(kmsq)', '1.0/kmsq')");
        canadaDb.execSQL("insert into Districts values('Churchill', 'Manitoba', '10789', '25607', '51056', '9203.01(kmsq)', '1.2/kmsq')");
        canadaDb.execSQL("insert into Districts values('Nanaimo', 'Yukon', '8679', '60547', '5576', '9203.01(kmsq)', '6.02/kmsq')");
        canadaDb.execSQL("insert into Districts values('Avalon', 'Newfoundland and Labrador', '20789', '5547', '5576', '22503.01(kmsq)', '1.0/kmsq')");
    }

    void createAndPopulateMunicipalityTable(SQLiteDatabase canadaDb)
    {
        //Create Municipality table
        canadaDb.execSQL("create table if not exists Municipality (municipality_name varchar(20), province_name varchar(20), male_population varchar(20), female_population varchar(20),national_population varchar(20), area varchar(20), density varchar(20))");

        canadaDb.execSQL("delete from Municipality");

        //populate Municipality Table
        canadaDb.execSQL("insert into Municipality values('Pincher Creek', 'British Columbia', '21789', '20547', '15576', '203.01(kmsq)', '5.0/kmsq')");
        canadaDb.execSQL("insert into Municipality values('Dighorn', 'Ontario', '110789', '60547', '11576', '2503.01(kmsq)', '5.0/kmsq')");
        canadaDb.execSQL("insert into Municipality values('Acadia', 'Nova Scotia Columbia', '34578', '15547', '11076', '503.01(kmsq)', '2.0/kmsq')");
        canadaDb.execSQL("insert into Municipality values('Fairview', 'Quebec', '23489', '15607', '31076', '503.01(kmsq)', '3.2/kmsq')");
        canadaDb.execSQL("insert into Municipality values('Flagstaff', 'Nunavut', '15109', '25647', '61056', '203.01(kmsq)', '2.3/kmsq')");
        canadaDb.execSQL("insert into Municipality values('Cypress', 'Saskatchewan', '75189', '15547', '7576', '223.01(kmsq)', '5.0/kmsq')");
        canadaDb.execSQL("insert into Municipality values('Clear Water', 'Alberta', '1109', '5647', '41576', '223.01(kmsq)', '1.0/kmsq')");
        canadaDb.execSQL("insert into Municipality values('Cardston', 'Manitoba', '10787', '25607', '61056', '203.01(kmsq)', '1.2/kmsq')");
        canadaDb.execSQL("insert into Municipality values('Brazeau', 'Yukon', '2678', '60547', '5576', '203.01(kmsq)', '6.02/kmsq')");
        canadaDb.execSQL("insert into Municipality values('Lacombe', 'Newfoundland and Labrador', '2789', '4547', '1576', '2503.01(kmsq)', '1.0/kmsq')");
    }

    void createAndPopulateCountyTable(SQLiteDatabase canadaDb)
    {
        //Create County table
        canadaDb.execSQL("create table if not exists County (county_name varchar(20), province_name varchar(20), male_population varchar(20), female_population varchar(20),national_population varchar(20), area varchar(20), density varchar(20))");

        canadaDb.execSQL("delete from County");

        //populate County Table
        canadaDb.execSQL("insert into County values('Red Deer County', 'British Columbia', '31782', '20547', '15576', '503.01(kmsq)', '5.0/kmsq')");
        canadaDb.execSQL("insert into County values('Leth Bridge County', 'Ontario', '107877', '60547', '11576', '1503.01(kmsq)', '5.0/kmsq')");
        canadaDb.execSQL("insert into County values('Lamont County', 'Nova Scotia Columbia', '34578', '15549', '11076', '401.01(kmsq)', '2.0/kmsq')");
        canadaDb.execSQL("insert into County values('Kneehill County', 'Quebec', '43488', '15609', '21076', '101.01(kmsq)', '3.2/kmsq')");
        canadaDb.execSQL("insert into County values('Nunavut County', 'Nunavut', '25104', '25647', '21054', '103.01(kmsq)', '2.3/kmsq')");
        canadaDb.execSQL("insert into County values('Ponoka County', 'Saskatchewan', '15188', '25547', '7576', '112.01(kmsq)', '5.0/kmsq')");
        canadaDb.execSQL("insert into County values('Rocky View County', 'Alberta', '2100', '3347', '42576', '227.01(kmsq)', '1.0/kmsq')");
        canadaDb.execSQL("insert into County values('Saddel Hill County', 'Manitoba', '11781', '23607', '61056', '673.01(kmsq)', '1.2/kmsq')");
        canadaDb.execSQL("insert into County values('Smoky Lake County', 'Yukon', '4678', '22547', '2274', '1203.01(kmsq)', '6.02/kmsq')");
        canadaDb.execSQL("insert into County values('Starland County', 'Newfoundland and Labrador', '3789', '1543', '1576', '1503.01(kmsq)', '1.0/kmsq')");
    }

    void createAndPopulateTownTable(SQLiteDatabase canadaDb)
    {
        //Create Town table
        canadaDb.execSQL("create table if not exists Town (town_name varchar(20), province_name varchar(20), municipality_name varchar(20) DEFAULT NULL, district_name varchar(20) DEFAULT NULL, county_name varchar(20) DEFAULT NULL, male_population varchar(20), female_population varchar(20),national_population varchar(20), area varchar(20), density varchar(20))");

        canadaDb.execSQL("delete from Town");

        //populate Town Table
        canadaDb.execSQL("insert into Town(town_name, province_name, municipality_name, male_population, female_population,national_population, area, density) values('Red Deer Town', 'British Columbia', 'Pincher Creek', '31782', '20547', '15576', '503.01(kmsq)', '5.0/kmsq')");
        canadaDb.execSQL("insert into Town(town_name, province_name, county_name, male_population, female_population,national_population, area, density) values('Leth Bridge Town', 'Ontario','Leth Bridge County', '107877', '60547', '11576', '1503.01(kmsq)', '5.0/kmsq')");
        canadaDb.execSQL("insert into Town(town_name, province_name, district_name, male_population, female_population,national_population, area, density) values('Lamont Town', 'Nova Scotia Columbia','Central Nova', '34578', '15549', '11076', '401.01(kmsq)', '2.0/kmsq')");
        canadaDb.execSQL("insert into Town(town_name, province_name, county_name, male_population, female_population,national_population, area, density) values('Kneehill Town', 'Quebec','Kneehill County', '43488', '15609', '21076', '101.01(kmsq)', '3.2/kmsq')");
        canadaDb.execSQL("insert into Town(town_name, province_name, county_name, male_population, female_population,national_population, area, density) values('Nunavut Town', 'Nunavut','Nunavut County', '25104', '25647', '21054', '103.01(kmsq)', '2.3/kmsq')");
        canadaDb.execSQL("insert into Town(town_name, province_name, municipality_name, male_population, female_population,national_population, area, density) values('Ponoka Town', 'Saskatchewan','Cypress', '15188', '25547', '7576', '112.01(kmsq)', '5.0/kmsq')");
        canadaDb.execSQL("insert into Town(town_name, province_name, district_name, male_population, female_population,national_population, area, density) values('Rocky View Town', 'Alberta','Calgary Centre', '2100', '3347', '42576', '227.01(kmsq)', '1.0/kmsq')");
        canadaDb.execSQL("insert into Town(town_name, province_name, district_name, male_population, female_population,national_population, area, density) values('Saddel Hill Town', 'Manitoba','Churchill', '11781', '23607', '61056', '673.01(kmsq)', '1.2/kmsq')");
        canadaDb.execSQL("insert into Town(town_name, province_name, municipality_name, male_population, female_population,national_population, area, density) values('Smoky Lake Town', 'Yukon','Brazeau', '4678', '22547', '2274', '1203.01(kmsq)', '6.02/kmsq')");
        canadaDb.execSQL("insert into Town(town_name, province_name, county_name, male_population, female_population,national_population, area, density) values('Starland Town', 'Newfoundland and Labrador','Starland County', '3789', '1543', '1576', '1503.01(kmsq)', '1.0/kmsq')");
    }

    void createAndPopulateCityTable(SQLiteDatabase canadaDb)
    {
        //Create City table
        canadaDb.execSQL("create table if not exists City (city_name varchar(20), province_name varchar(20), municipality_name varchar(20) DEFAULT NULL, district_name varchar(20) DEFAULT NULL, county_name varchar(20) DEFAULT NULL, male_population varchar(20), female_population varchar(20),national_population varchar(20), area varchar(20), density varchar(20))");

        canadaDb.execSQL("delete from City");

        //populate City Table
        canadaDb.execSQL("insert into City(city_name, province_name, municipality_name, male_population, female_population,national_population, area, density) values('Red Deer City', 'British Columbia', 'Pincher Creek', '31782', '20547', '15576', '503.01(kmsq)', '5.0/kmsq')");
        canadaDb.execSQL("insert into City(city_name, province_name, county_name, male_population, female_population,national_population, area, density) values('Leth Bridge City', 'Ontario','Leth Bridge County', '107877', '60547', '11576', '1503.01(kmsq)', '5.0/kmsq')");
        canadaDb.execSQL("insert into City(city_name, province_name, district_name, male_population, female_population,national_population, area, density) values('Lamont City', 'Nova Scotia Columbia','Central Nova', '34578', '15549', '11076', '401.01(kmsq)', '2.0/kmsq')");
        canadaDb.execSQL("insert into City(city_name, province_name, county_name, male_population, female_population,national_population, area, density) values('Kneehill City', 'Quebec','Kneehill County', '43488', '15609', '21076', '101.01(kmsq)', '3.2/kmsq')");
        canadaDb.execSQL("insert into City(city_name, province_name, county_name, male_population, female_population,national_population, area, density) values('Nunavut City', 'Nunavut','Nunavut County', '25104', '25647', '21054', '103.01(kmsq)', '2.3/kmsq')");
        canadaDb.execSQL("insert into City(city_name, province_name, municipality_name, male_population, female_population,national_population, area, density) values('Ponoka City', 'Saskatchewan','Cypress', '15188', '25547', '7576', '112.01(kmsq)', '5.0/kmsq')");
        canadaDb.execSQL("insert into City(city_name, province_name, district_name, male_population, female_population,national_population, area, density) values('Rocky View City', 'Alberta','Calgary Centre', '2100', '3347', '42576', '227.01(kmsq)', '1.0/kmsq')");
        canadaDb.execSQL("insert into City(city_name, province_name, district_name, male_population, female_population,national_population, area, density) values('Saddel Hill City', 'Manitoba','Churchill', '11781', '23607', '61056', '673.01(kmsq)', '1.2/kmsq')");
        canadaDb.execSQL("insert into City(city_name, province_name, municipality_name, male_population, female_population,national_population, area, density) values('Smoky Lake City', 'Yukon','Brazeau', '4678', '22547', '2274', '1203.01(kmsq)', '6.02/kmsq')");
        canadaDb.execSQL("insert into City(city_name, province_name, county_name, male_population, female_population,national_population, area, density) values('Starland City', 'Newfoundland and Labrador','Starland County', '3789', '1543', '1576', '1503.01(kmsq)', '1.0/kmsq')");
    }

    void createAndPopulateVillageTable(SQLiteDatabase canadaDb)
    {
        //Create Village table
        canadaDb.execSQL("create table if not exists Village (village_name varchar(20), province_name varchar(20), municipality_name varchar(20) DEFAULT NULL, district_name varchar(20) DEFAULT NULL, county_name varchar(20) DEFAULT NULL, male_population varchar(20), female_population varchar(20),national_population varchar(20), area varchar(20), density varchar(20))");

        canadaDb.execSQL("delete from Village");

        //populate Village Table
        canadaDb.execSQL("insert into Village(village_name, province_name, municipality_name, male_population, female_population,national_population, area, density) values('Red Deer Village', 'British Columbia', 'Pincher Creek', '31782', '20547', '15576', '503.01(kmsq)', '5.0/kmsq')");
        canadaDb.execSQL("insert into Village(village_name, province_name, county_name, male_population, female_population,national_population, area, density) values('Leth Bridge Village', 'Ontario','Leth Bridge County', '107877', '60547', '11576', '1503.01(kmsq)', '5.0/kmsq')");
        canadaDb.execSQL("insert into Village(village_name, province_name, district_name, male_population, female_population,national_population, area, density) values('Lamont Village', 'Nova Scotia Columbia','Central Nova', '34578', '15549', '11076', '401.01(kmsq)', '2.0/kmsq')");
        canadaDb.execSQL("insert into Village(village_name, province_name, county_name, male_population, female_population,national_population, area, density) values('Kneehill Village', 'Quebec','Kneehill County', '43488', '15609', '21076', '101.01(kmsq)', '3.2/kmsq')");
        canadaDb.execSQL("insert into Village(village_name, province_name, county_name, male_population, female_population,national_population, area, density) values('Nunavut Village', 'Nunavut','Nunavut County', '25104', '25647', '21054', '103.01(kmsq)', '2.3/kmsq')");
        canadaDb.execSQL("insert into Village(village_name, province_name, municipality_name, male_population, female_population,national_population, area, density) values('Ponoka Village', 'Saskatchewan','Cypress', '15188', '25547', '7576', '112.01(kmsq)', '5.0/kmsq')");
        canadaDb.execSQL("insert into Village(village_name, province_name, district_name, male_population, female_population,national_population, area, density) values('Rocky View Village', 'Alberta','Calgary Centre', '2100', '3347', '42576', '227.01(kmsq)', '1.0/kmsq')");
        canadaDb.execSQL("insert into Village(village_name, province_name, district_name, male_population, female_population,national_population, area, density) values('Saddel Hill Village', 'Manitoba','Churchill', '11781', '23607', '61056', '673.01(kmsq)', '1.2/kmsq')");
        canadaDb.execSQL("insert into Village(village_name, province_name, municipality_name, male_population, female_population,national_population, area, density) values('Smoky Lake Village', 'Yukon','Brazeau', '4678', '22547', '2274', '1203.01(kmsq)', '6.02/kmsq')");
        canadaDb.execSQL("insert into Village(village_name, province_name, county_name, male_population, female_population,national_population, area, density) values('Starland Village', 'Newfoundland and Labrador','Starland County', '3789', '1543', '1576', '1503.01(kmsq)', '1.0/kmsq')");
    }

    void createAndPopulateWardTable(SQLiteDatabase canadaDb)
    {
        //Create Ward table
        canadaDb.execSQL("create table if not exists Ward (ward_name varchar(20), province_name varchar(20), municipality_name varchar(20) DEFAULT NULL, district_name varchar(20) DEFAULT NULL, county_name varchar(20) DEFAULT NULL, city_name varchar(20) DEFAULT NULL, town_name varchar(20) DEFAULT NULL, village_name varchar(20) DEFAULT NULL, male_population varchar(20), female_population varchar(20),national_population varchar(20), area varchar(20), density varchar(20))");

        canadaDb.execSQL("delete from Ward");

        //populate Ward Table
        canadaDb.execSQL("insert into Ward(ward_name, province_name, municipality_name, town_name, male_population, female_population,national_population, area, density) values('Red Deer Ward', 'British Columbia', 'Pincher Creek',  'Pincher Creek', '31782', '20547', '15576', '503.01(kmsq)', '5.0/kmsq')");
        canadaDb.execSQL("insert into Ward(ward_name, province_name, county_name, city_name, male_population, female_population,national_population, area, density) values('Leth Bridge Ward', 'Ontario','Leth Bridge County', 'Leth Bridge City', '107877', '60547', '11576', '1503.01(kmsq)', '5.0/kmsq')");
        canadaDb.execSQL("insert into Ward(ward_name, province_name, district_name, village_name, male_population, female_population,national_population, area, density) values('Lamont Ward', 'Nova Scotia Columbia','Central Nova','Lamont Village', '34578', '15549', '11076', '401.01(kmsq)', '2.0/kmsq')");
        canadaDb.execSQL("insert into Ward(ward_name, province_name, county_name,town_name, male_population, female_population,national_population, area, density) values('Kneehill Ward', 'Quebec','Kneehill County','Kneehill Town', '43488', '15609', '21076', '101.01(kmsq)', '3.2/kmsq')");
        canadaDb.execSQL("insert into Ward(ward_name, province_name, county_name,city_name, male_population, female_population,national_population, area, density) values('Nunavut Ward', 'Nunavut','Nunavut County','Nunavut City', '25104', '25647', '21054', '103.01(kmsq)', '2.3/kmsq')");
        canadaDb.execSQL("insert into Ward(ward_name, province_name, municipality_name,village_name, male_population, female_population,national_population, area, density) values('Ponoka Ward', 'Saskatchewan','Cypress','Ponoka Village', '15188', '25547', '7576', '112.01(kmsq)', '5.0/kmsq')");
        canadaDb.execSQL("insert into Ward(ward_name, province_name, district_name,city_name, male_population, female_population,national_population, area, density) values('Rocky View Ward', 'Alberta','Calgary Centre','Rocky View City', '2100', '3347', '42576', '227.01(kmsq)', '1.0/kmsq')");
        canadaDb.execSQL("insert into Ward(ward_name, province_name, district_name,city_name, male_population, female_population,national_population, area, density) values('Saddel Hill Ward', 'Manitoba','Churchill','Saddel Hill City', '11781', '23607', '61056', '673.01(kmsq)', '1.2/kmsq')");
        canadaDb.execSQL("insert into Ward(ward_name, province_name, municipality_name,city_name, male_population, female_population,national_population, area, density) values('Smoky Lake Ward', 'Yukon','Brazeau','Smoky Lake City', '4678', '22547', '2274', '1203.01(kmsq)', '6.02/kmsq')");
        canadaDb.execSQL("insert into Ward(ward_name, province_name, county_name,village_name, male_population, female_population,national_population, area, density) values('Starland Ward', 'Newfoundland and Labrador','Starland County','Starland Village', '3789', '1543', '1576', '1503.01(kmsq)', '1.0/kmsq')");
    }
}
