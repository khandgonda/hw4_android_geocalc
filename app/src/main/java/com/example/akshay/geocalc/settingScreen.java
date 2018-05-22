package com.example.akshay.geocalc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.ImageView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;


public class settingScreen extends AppCompatActivity {

    ImageView check;
    Spinner dspinner, bspinner;
    int flag =0 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_screen);
        dspinner = (Spinner) findViewById(R.id.spinner1);
        bspinner = (Spinner) findViewById(R.id.spinner2);
        check = (ImageView) findViewById(R.id.imageView4);



        /*

        // adds the items of the spinner
        ArrayAdapter<String> dspinner_Adapter = new ArrayAdapter<String>(settingScreen.this,
                android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.Distance_units));
        //displays the items of the spinner
        dspinner_Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dspinner.setAdapter(dspinner_Adapter);

        ArrayAdapter<String> bspinner_Adapter = new ArrayAdapter<String>(settingScreen.this,
                android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.Bearing_units));
        bspinner_Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bspinner.setAdapter(bspinner_Adapter);


        */
        List<String> dist_list = new ArrayList<String>();

        dist_list.add("Kilometers");
        dist_list.add("Miles");

        ArrayAdapter<String> dataAdapter_dist = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, dist_list);
        dataAdapter_dist.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dspinner.setAdapter(dataAdapter_dist);

        // Adding options for selecting bearing units
        List<String> bear_list = new ArrayList<String>();

        bear_list.add("Degrees");
        bear_list.add("Mils");

        ArrayAdapter<String> dataAdapter_bear = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, bear_list);
        dataAdapter_bear.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bspinner.setAdapter(dataAdapter_bear);

        Intent payload = getIntent();
        if (payload.hasExtra("flag")) {


            if (dspinner.getSelectedItem() == "Kilometers" && bspinner.getSelectedItem() == "Degrees") {
                flag = 1;
            } else if (dspinner.getSelectedItem() == "Kilometers" && bspinner.getSelectedItem() == "Mils") {
                flag = 2;

            } else if (dspinner.getSelectedItem() == "Miles" && bspinner.getSelectedItem() == "Degrees") {
                flag = 3;

            } else if (dspinner.getSelectedItem() == "Miles" && bspinner.getSelectedItem() == "Mils") {
                flag = 4;

            }
        }

        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent1;
                intent1 = new Intent(getApplicationContext(),homeScreen.class);
                String dselected = dspinner.getSelectedItem().toString();
                String bselected = bspinner.getSelectedItem().toString();
                intent1.putExtra("dselected", dselected);
                intent1.putExtra("bselected", bselected);
                startActivity(intent1);
            }
        });



    }
}
