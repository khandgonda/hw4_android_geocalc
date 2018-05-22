package com.example.akshay.geocalc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.SpannableStringBuilder;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import static android.R.attr.data;


public class homeScreen extends AppCompatActivity {
    Button settings;
    TextView dtv, btv;
    String dmeasure ="Kilometer";
    String bmeasure = "Degrees";

    public double dist = 0.0;
    public double bear = 0.0;
    double lat1,long1,lat2,long2;

    private Button Calculatedis, Clear;
    private EditText latp1, longp1, latp2, longp2;
    private TextView  distance, bearing;
    public static final int DUNITS_SELECTION = 1;
    public static final int BUNITS_SELECTION = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        settings = (Button) findViewById(R.id.SettingsPressed);
        dtv = (TextView) findViewById(R.id.dmetric);
        btv = (TextView) findViewById(R.id.bmetric);

        Calculatedis = (Button) findViewById(R.id.Calculate);
        Clear = (Button) findViewById(R.id.Clear);
        latp1 = (EditText) findViewById(R.id.LP1);
        longp1 = (EditText) findViewById(R.id.LoP1);
        latp2 = (EditText) findViewById(R.id.LP2);
        longp2 = (EditText) findViewById(R.id.LoP2);
        distance = (TextView) findViewById(R.id.Distance);
        bearing = (TextView) findViewById(R.id.Bearing);


        Intent intentcheck = getIntent();
        if (intentcheck.hasExtra("dselected")){
            dmeasure = getIntent().getStringExtra("dselected");
        }else
        {
            dmeasure= "Kilometers";
        }
        if (intentcheck.hasExtra("bselected")){
            bmeasure = getIntent().getStringExtra("bselected");
        }else
        {
            bmeasure= "Degrees";
        }

        dtv.setText(dmeasure);
        btv.setText(bmeasure);



        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getApplicationContext(),settingScreen.class);
                String dselected = dtv.getText().toString();
                String bselected = btv.getText().toString();
                intent1.putExtra("dselected", dselected);
                intent1.putExtra("bselected", bselected);
                startActivity(intent1);
            }
        });


        Clear.setOnClickListener(v-> {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(Clear.getWindowToken(),0);
            latp1.setText("");
            latp2.setText("");
            longp1.setText("");
            longp2.setText("");
            distance.setText("");
            bearing.setText("");

        });

        Calculatedis.setOnClickListener(v -> {
            /*String l1 = latp1.getText().toString();
            if(l1.length()==0){
                Snackbar.make(latp1,"Enter a value", Snackbar.LENGTH_LONG).show();
            }*/
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(Calculatedis.getWindowToken(),0);

            lat1 = Double.parseDouble(latp1.getText().toString());
            long1 = Double.parseDouble(longp1.getText().toString());
            lat2 = Double.parseDouble(latp2.getText().toString());
            long2 = Double.parseDouble(longp2.getText().toString());

            Location loc1 = new Location("location1");

            loc1.setLatitude(lat1);
            loc1.setLongitude(long1);

            Location loc2 = new Location("location2");

            loc2.setLatitude(lat2);
            loc2.setLongitude(long2);

            dist = loc1.distanceTo(loc2);
            dist = dist/1000;

            bear = loc1.bearingTo(loc2);

            if (dmeasure.compareTo("Kilometers")== 0){
                distance.setText(""+(Double.toString(dist).substring(0,6))+ " kms");
            } else {

                dist = dist * 0.621;

                distance.setText(""+(Double.toString(dist).substring(0,6)) + "Miles");
            }
            if(bmeasure.compareTo("Degrees")==0){
                bearing.setText(""+(Double.toString(bear).substring(0,6)) + "Degrees");
            } else{

                bear = 17.77 * bear;
                bearing.setText(""+(Double.toString(bear).substring(0,6)) + "Mils");
            }



        });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.settings:
                Intent intent1 =new Intent(this,settingScreen.class);
                this.startActivity(intent1);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
