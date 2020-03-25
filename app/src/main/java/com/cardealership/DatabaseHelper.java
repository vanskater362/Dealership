package com.cardealership;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
//This class can function like a Web API
//Each function would be an Endpoint to access the DataBase
public class DatabaseHelper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 3;

    // Database Name
    private static final String DATABASE_NAME = "dealership_db";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        // create notes table
        db.execSQL(Cars.CREATE_TABLE);
        //fillDB(db);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + Cars.TABLE_NAME);

        // Create tables again
        onCreate(db);
    }

    public List<Cars> getSearched(String query){
        List<Cars> cars = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()) {
            do {
                Cars car = new Cars();

                car.setId(cursor.getInt(cursor.getColumnIndex(Cars.COLUMN_ID)));
                car.set_Id(cursor.getString(cursor.getColumnIndex(Cars.COLUMN__ID)));
                car.setMake(cursor.getString(cursor.getColumnIndex(Cars.COLUMN_MAKE)));
                car.setYear(cursor.getString(cursor.getColumnIndex(Cars.COLUMN_YEAR)));
                car.setColor(cursor.getString(cursor.getColumnIndex(Cars.COLUMN_COLOR)));
                car.setPrice(cursor.getString(cursor.getColumnIndex(Cars.COLUMN_PRICE)));
                car.setHasSunroof(cursor.getInt(cursor.getColumnIndex(Cars.COLUMN_HASSUNROOF)));
                car.setIsFourWheelDrive(cursor.getInt(cursor.getColumnIndex(Cars.COLUMN_ISFOURWHEELDRIVE)));
                car.setHasLowMiles(cursor.getInt(cursor.getColumnIndex(Cars.COLUMN_HASLOWMILES)));
                car.setHasPowerWindows(cursor.getInt(cursor.getColumnIndex(Cars.COLUMN_HASPOWERWINDOWS)));
                car.setHasNavigation(cursor.getInt(cursor.getColumnIndex(Cars.COLUMN_HASNAVIGATION)));
                car.setHasHeatedSeats(cursor.getInt(cursor.getColumnIndex(Cars.COLUMN_HASHEATEDSEATS)));

                cars.add(car);
            } while (cursor.moveToNext());
        }

        return cars;
    }

    public List<Cars> getAllCars() {
        List<Cars> cars = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + Cars.TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()){
            do{
                Cars car = new Cars();
                car.setId(cursor.getInt(cursor.getColumnIndex(Cars.COLUMN_ID)));
                car.set_Id(cursor.getString(cursor.getColumnIndex(Cars.COLUMN__ID)));
                car.setMake(cursor.getString(cursor.getColumnIndex(Cars.COLUMN_MAKE)));
                car.setYear(cursor.getString(cursor.getColumnIndex(Cars.COLUMN_YEAR)));
                car.setColor(cursor.getString(cursor.getColumnIndex(Cars.COLUMN_COLOR)));
                car.setPrice(cursor.getString(cursor.getColumnIndex(Cars.COLUMN_PRICE)));
                car.setHasSunroof(cursor.getInt(cursor.getColumnIndex(Cars.COLUMN_HASSUNROOF)));
                car.setIsFourWheelDrive(cursor.getInt(cursor.getColumnIndex(Cars.COLUMN_ISFOURWHEELDRIVE)));
                car.setHasLowMiles(cursor.getInt(cursor.getColumnIndex(Cars.COLUMN_HASLOWMILES)));
                car.setHasPowerWindows(cursor.getInt(cursor.getColumnIndex(Cars.COLUMN_HASPOWERWINDOWS)));
                car.setHasNavigation(cursor.getInt(cursor.getColumnIndex(Cars.COLUMN_HASNAVIGATION)));
                car.setHasHeatedSeats(cursor.getInt(cursor.getColumnIndex(Cars.COLUMN_HASHEATEDSEATS)));

                cars.add(car);
            } while (cursor.moveToNext());
        }

        db.close();

        return cars;
    }

    public HashSet getMakes(){
        HashSet makes = new HashSet();
        String selectQuery = "SELECT " + Cars.COLUMN_MAKE + " FROM " + Cars.TABLE_NAME;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()) {
            do {
                makes.add(cursor.getString(cursor.getColumnIndex(Cars.COLUMN_MAKE)));
            } while (cursor.moveToNext());
        }

        return makes;
    }

    public HashSet getYears(){
        HashSet years = new HashSet();
        String selectQuery = "SELECT " + Cars.COLUMN_YEAR + " FROM " + Cars.TABLE_NAME;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()) {
            do {
                years.add(cursor.getString(cursor.getColumnIndex(Cars.COLUMN_YEAR)));
            } while (cursor.moveToNext());
        }

        return years;
    }

    public HashSet getColors(){
        HashSet colors = new HashSet();
        String selectQuery = "SELECT " + Cars.COLUMN_COLOR + " FROM " + Cars.TABLE_NAME;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()) {
            do {
                colors.add(cursor.getString(cursor.getColumnIndex(Cars.COLUMN_COLOR)));
            } while (cursor.moveToNext());
        }

        return colors;
    }



    public void fillDB(){
        //data for the database
        //this is just for this test app
        //data is from the sample data set

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        //create a map of each car to later insert into the database
        Map<String, String> car1 = new HashMap<>();
            car1.put("_id", "59d2698c2eaefb1268b69ee5");
            car1.put("make", "Chevy");
            car1.put("year", "2016");
            car1.put("color", "Gray");
            car1.put("price", "16106");
            car1.put("hasSunroof", "0");
            car1.put("isFourWheelDrive", "1");
            car1.put("hasLowMiles", "1");
            car1.put("hasPowerWindows", "0");
            car1.put("hasNavigation", "1");
            car1.put("hasHeatedSeats", "0");

        Map<String, String> car2 = new HashMap<>();
            car2.put("_id", "59d2698c05889e0b23959106");
            car2.put("make", "Toyota");
            car2.put("year", "2012");
            car2.put("color", "Silver");
            car2.put("price", "18696");
            car2.put("hasSunroof", "1");
            car2.put("isFourWheelDrive", "1");
            car2.put("hasLowMiles", "0");
            car2.put("hasPowerWindows", "1");
            car2.put("hasNavigation", "0");
            car2.put("hasHeatedSeats", "1");


        Map<String, String> car3 = new HashMap<>();
            car3.put("_id", "59d2698c6f1dc2cbec89c413");
            car3.put("make", "Mercedes");
            car3.put("year", "2016");
            car3.put("color", "Black");
            car3.put("price", "18390");
            car3.put("hasSunroof", "1");
            car3.put("isFourWheelDrive", "0");
            car3.put("hasLowMiles", "0");
            car3.put("hasPowerWindows", "1");
            car3.put("hasNavigation", "1");
            car3.put("hasHeatedSeats", "0");


        Map<String, String> car4 = new HashMap<>();
            car4.put("_id", "59d2698c340f2728382c0a73");
            car4.put("make", "Toyota");
            car4.put("year", "2015");
            car4.put("color", "White");
            car4.put("price", "15895");
            car4.put("hasSunroof", "1");
            car4.put("isFourWheelDrive", "0");
            car4.put("hasLowMiles", "1");
            car4.put("hasPowerWindows", "1");
            car4.put("hasNavigation", "0");
            car4.put("hasHeatedSeats", "1");


        Map<String, String> car5 = new HashMap<>();
            car5.put("_id", "59d2698cba9b82c2347cd13a");
            car5.put("make", "Ford");
            car5.put("year", "2014");
            car5.put("color", "Gray");
            car5.put("price", "19710");
            car5.put("hasSunroof", "0");
            car5.put("isFourWheelDrive", "1");
            car5.put("hasLowMiles", "0");
            car5.put("hasPowerWindows", "0");
            car5.put("hasNavigation", "1");
            car5.put("hasHeatedSeats", "1");


        Map<String, String> car6 = new HashMap<>();
            car6.put("_id", "59d2698ce2e7eeeb4f109001");
            car6.put("make", "Toyota");
            car6.put("year", "2014");
            car6.put("color", "Red");
            car6.put("price", "19248");
            car6.put("hasSunroof", "1");
            car6.put("isFourWheelDrive", "0");
            car6.put("hasLowMiles", "1");
            car6.put("hasPowerWindows", "1");
            car6.put("hasNavigation", "1");
            car6.put("hasHeatedSeats", "1");

        Map<String, String> car7 = new HashMap<>();
            car7.put("_id", "59d2698cd6a3b8f0dd994c9d");
            car7.put("make", "Toyota");
            car7.put("year", "2015");
            car7.put("color", "Black");
            car7.put("price", "13170");
            car7.put("hasSunroof", "1");
            car7.put("isFourWheelDrive", "0");
            car7.put("hasLowMiles", "1");
            car7.put("hasPowerWindows", "1");
            car7.put("hasNavigation", "0");
            car7.put("hasHeatedSeats", "0");

        Map<String, String> car8 = new HashMap<>();
            car8.put("_id", "59d2698c86ab54cee8acdc7b");
            car8.put("make", "Mercedes");
            car8.put("year", "2013");
            car8.put("color", "Gray");
            car8.put("price", "15669");
            car8.put("hasSunroof", "0");
            car8.put("isFourWheelDrive", "0");
            car8.put("hasLowMiles", "1");
            car8.put("hasPowerWindows", "0");
            car8.put("hasNavigation", "0");
            car8.put("hasHeatedSeats", "0");

        Map<String, String> car9 = new HashMap<>();
            car9.put("_id", "59d2698cda9e8d39476c678a");
            car9.put("make", "Toyota");
            car9.put("year", "2015");
            car9.put("color", "White");
            car9.put("price", "16629");
            car9.put("hasSunroof", "0");
            car9.put("isFourWheelDrive", "0");
            car9.put("hasLowMiles", "1");
            car9.put("hasPowerWindows", "0");
            car9.put("hasNavigation", "0");
            car9.put("hasHeatedSeats", "1");

        //create an arraylist of each map created
        //so one for loop can be use to insert the
        //entire sample data set
        ArrayList<Map> carMap = new ArrayList<>();
        carMap.add(car1);
        carMap.add(car2);
        carMap.add(car3);
        carMap.add(car4);
        carMap.add(car5);
        carMap.add(car6);
        carMap.add(car7);
        carMap.add(car8);
        carMap.add(car9);


        for(int i = 0; i < carMap.size(); i++) {
            values.put(Cars.COLUMN__ID, carMap.get(i).get("_id").toString());
            values.put(Cars.COLUMN_MAKE, carMap.get(i).get("make").toString());
            values.put(Cars.COLUMN_YEAR, carMap.get(i).get("year").toString());
            values.put(Cars.COLUMN_COLOR, carMap.get(i).get("color").toString());
            values.put(Cars.COLUMN_PRICE, carMap.get(i).get("price").toString());
            values.put(Cars.COLUMN_HASSUNROOF, carMap.get(i).get("hasSunroof").toString());
            values.put(Cars.COLUMN_ISFOURWHEELDRIVE, carMap.get(i).get("isFourWheelDrive").toString());
            values.put(Cars.COLUMN_HASLOWMILES, carMap.get(i).get("hasLowMiles").toString());
            values.put(Cars.COLUMN_HASPOWERWINDOWS, carMap.get(i).get("hasPowerWindows").toString());
            values.put(Cars.COLUMN_HASNAVIGATION, carMap.get(i).get("hasNavigation").toString());
            values.put(Cars.COLUMN_HASHEATEDSEATS, carMap.get(i).get("hasHeatedSeats").toString());

            //insert each car
            db.insert(Cars.TABLE_NAME, null, values);
        }
        db.close();
    }
}
