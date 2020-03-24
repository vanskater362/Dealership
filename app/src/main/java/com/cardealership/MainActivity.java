package com.cardealership;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<String> makesArray =  new ArrayList<String>();
        List<String> yearArray =  new ArrayList<String>();
        List<String> colorArray =  new ArrayList<String>();

        fillMakes(makesArray);
        fillYears(yearArray);
        fillColors(colorArray);


        HintAdapter makesAdapter = new HintAdapter(this, makesArray, android.R.layout.simple_spinner_item);
        makesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        HintAdapter yearAdapter = new HintAdapter(this, yearArray, android.R.layout.simple_spinner_item);
        yearAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        HintAdapter colorAdapter = new HintAdapter(this, colorArray, android.R.layout.simple_spinner_item);
        colorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Spinner makeSpinner = (Spinner) findViewById(R.id.makeSpinner);
        Spinner yearSpinner = (Spinner) findViewById(R.id.yearSpinner);
        Spinner colorSpinner = (Spinner) findViewById(R.id.colorSpinner);
        EditText priceText = (EditText) findViewById(R.id.editPrice);
        EditText milesText = (EditText) findViewById(R.id.editMiles);
        final Switch sunroof = (Switch) findViewById(R.id.sunSwitch);
        final Switch drive = (Switch) findViewById(R.id.fourwdSwitch);
        final Switch window = (Switch) findViewById(R.id.windowSwitch);
        final Switch navi = (Switch) findViewById(R.id.naviSwitch);
        final Switch hotSeats = (Switch) findViewById(R.id.hotSwitch);

        makeSpinner.setAdapter(makesAdapter);
        makeSpinner.setSelection(makesAdapter.getCount());
        yearSpinner.setAdapter(yearAdapter);
        yearSpinner.setSelection(yearAdapter.getCount());
        colorSpinner.setAdapter(colorAdapter);
        colorSpinner.setSelection(colorAdapter.getCount());

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

    void fillMakes(List<String> makesArray) {
        makesArray.add("Make 1");
        makesArray.add("Make 2");
        makesArray.add("Select Make");
    }

    void fillYears(List<String> yearArray) {
        yearArray.add("Year 1");
        yearArray.add("Year 2");
        yearArray.add("Select Year");
    }

    void fillColors(List<String> colorArray){
        colorArray.add("Color 1");
        colorArray.add("Color 2");
        colorArray.add("Select Color");
    }
}
