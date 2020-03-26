package com.cardealership;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DatabaseHelper(this);
        db.fillDB(); //TODO need to find a way to only run once

        List<String> makesArray = fillMakes(); //only fill the drop down menu with avaiable makes
        List<String> yearArray =  fillYears(); //only fill the drop down menu with avaiable Years
        List<String> colorArray =  fillColors(); //only fill the dropdown menu with avaiable Colors

        //Set the hint but make it not a selection
        HintAdapter makesAdapter = new HintAdapter(this, makesArray, android.R.layout.simple_spinner_item);
        makesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        HintAdapter yearAdapter = new HintAdapter(this, yearArray, android.R.layout.simple_spinner_item);
        yearAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        HintAdapter colorAdapter = new HintAdapter(this, colorArray, android.R.layout.simple_spinner_item);
        colorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        final Spinner makeSpinner = (Spinner) findViewById(R.id.makeSpinner);
        final Spinner yearSpinner = (Spinner) findViewById(R.id.yearSpinner);
        final Spinner colorSpinner = (Spinner) findViewById(R.id.colorSpinner);
        final EditText priceText = (EditText) findViewById(R.id.editPrice);

        final Switch sunroof = (Switch) findViewById(R.id.sunSwitch);
        final Switch drive = (Switch) findViewById(R.id.fourwdSwitch);
        final Switch window = (Switch) findViewById(R.id.windowSwitch);
        final Switch navi = (Switch) findViewById(R.id.naviSwitch);
        final Switch hotSeats = (Switch) findViewById(R.id.hotSwitch);
        final CheckBox sunBox = findViewById(R.id.sunBox);
        final CheckBox driveBox = findViewById(R.id.driveBox);
        final CheckBox windowBox = findViewById(R.id.powerBox);
        final CheckBox naviBox = findViewById(R.id.naviBox);
        final CheckBox hotBox = findViewById(R.id.heatBox);
        Button search = findViewById(R.id.searchButton);

        makeSpinner.setAdapter(makesAdapter);
        makeSpinner.setSelection(makesAdapter.getCount());
        yearSpinner.setAdapter(yearAdapter);
        yearSpinner.setSelection(yearAdapter.getCount());
        colorSpinner.setAdapter(colorAdapter);
        colorSpinner.setSelection(colorAdapter.getCount());

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, Results.class);
                String make = makeSpinner.getSelectedItem().toString();
                String year = yearSpinner.getSelectedItem().toString();
                String color = colorSpinner.getSelectedItem().toString();
                String price = priceText.getText().toString();

                String query = buildSearchQuery(make, year, color, price, sunroof.isChecked(),
                        drive.isChecked(),window.isChecked(),navi.isChecked(),hotSeats.isChecked(),
                        sunBox.isChecked(),driveBox.isChecked(),windowBox.isChecked(),
                        naviBox.isChecked(),hotBox.isChecked());

                List<Cars> car = db.getSearched(query);
                IntentHelper.addObjectForKey(car, "Cars");

                //i.putExtra("Cars", car);
                startActivity(i);
            }
        });

        sunroof.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b)
                    sunroof.setText(R.string.yes);
                if(!b)
                    sunroof.setText(R.string.no);
            }
        });

        drive.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b)
                    drive.setText(R.string.yes);
                if(!b)
                    drive.setText(R.string.no);
            }
        });

        window.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b)
                    window.setText(R.string.yes);
                if(!b)
                    window.setText(R.string.no);
            }
        });

        navi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b)
                    navi.setText(R.string.yes);
                if(!b)
                    navi.setText(R.string.no);
            }
        });

        hotSeats.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b)
                    hotSeats.setText(R.string.yes);
                if(!b)
                    hotSeats.setText(R.string.no);
            }
        });

    }

    List<String> fillMakes() {
        HashSet<String> makesArray =  db.getMakes();
        List<String> makes = new ArrayList<>();
        Object[] obj = makesArray.toArray();

        for(int i = 0; i < obj.length; i++) {
            makes.add(obj[i].toString());
        }
        makes.add("Select Make");
        return makes;
    }

    List<String> fillYears() {
        HashSet<String> yearsArray =  db.getYears();
        List<String> years = new ArrayList<>();
        Object[] obj = yearsArray.toArray();

        for(int i = 0; i < obj.length; i++) {
            years.add(obj[i].toString());
        }
        years.add("Select Year");
        return years;
    }

    List<String> fillColors(){
        HashSet<String> colorsArray =  db.getColors();
        List<String> colors = new ArrayList<>();
        Object[] obj = colorsArray.toArray();

        for(int i = 0; i < obj.length; i++) {
            colors.add(obj[i].toString());
        }
        colors.add("Select Color");
        return colors;
    }

    String buildSearchQuery(String make, String year, String color, String price,
                            boolean sunroof, boolean hasFour, boolean hasPowerWin,
                            boolean hasNavi, boolean hasHotSeat, boolean sunBox,
                            boolean driveBox, boolean powerBox, boolean naviBox,
                            boolean hotBox){
        String select = "SELECT * ";
        String where = " WHERE ";
        String regex = "(, FROM)";
        //check for hint text
        if(!make.startsWith("Select")){
            //select += Cars.COLUMN_MAKE + ", ";
            where += Cars.COLUMN_MAKE + " = '" + make + "' AND ";
        }
        if(!year.startsWith("Select")) {
            //select += Cars.COLUMN_YEAR + ", ";
            where += Cars.COLUMN_YEAR + " = '" + year + "' AND ";
        }
        if(!color.startsWith("Select")){
            //select += Cars.COLUMN_COLOR + ", ";
            where += Cars.COLUMN_COLOR + " = '" + color + "' AND ";
        }
        if(!price.isEmpty()){
            where += Cars.COLUMN_PRICE +  " <= '" + price + "' AND ";
        }
        if(sunroof && sunBox){
            where += Cars.COLUMN_HASSUNROOF + " = " + 1 + " AND ";
        }
        if(hasFour && driveBox){
            where += Cars.COLUMN_ISFOURWHEELDRIVE + " = " + 1 + " AND ";
        }
        if(hasPowerWin && powerBox){
            where += Cars.COLUMN_HASPOWERWINDOWS + " = " + 1 + " AND ";
        }
        if(hasNavi && naviBox){
            where += Cars.COLUMN_HASNAVIGATION + " = " + 1 + " AND ";
        }
        if(hasHotSeat && hotBox){
            where += Cars.COLUMN_HASHEATEDSEATS + " = " + 1 + " AND ";
        }


        //select = select.substring(0, select.length() - 2); //remove ", " before adding the next part of the query
        select += " FROM " + Cars.TABLE_NAME;
        where = where.substring(0, where.length() - 5); //remove " AND " before adding the next part of the query


        return select+where;
    }
}
