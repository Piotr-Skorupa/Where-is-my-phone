package com.example.piotrskorupa.whereismyphone;

import android.content.Intent;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.content.SharedPreferences;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public static int NOT_REGISTERED = 0;
    private static int NOT_RUNNING = 1;
    private static int RUNNING = 2;
    private Button mStartButton;
    private Button mMapButton;
    private int status;

    //location variables
    private double mLocationLatitude = 0.0; // latitude (-90 to +90)
    private double mLocationLongitude = 0.0; // longitude (-180 to +180)

    private final int myCode = 1;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    //mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    runConf();

                    return true;
                case R.id.navigation_notifications:
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://signomix.signocom.com/help/wimp/index.html?language=en"));
                    startActivity(browserIntent);
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Context context = getActivity();
        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        status = sharedPref.getInt("status", 0);


        mStartButton = (Button) findViewById(R.id.start_button);
        updateButton();

        mMapButton = (Button) findViewById(R.id.map_button);

        mMapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // start map activity

                Intent intent = new Intent(MainActivity.this, MapsActivity.class);
                startActivityForResult(intent, myCode);


            }
        });

        //mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        mStartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (status){
                    case 0:
                        runConf();
                        break;
                    case 1:
                        run();
                        break;
                    case 2:
                        stop();
                        break;
                }
            }
        });
    }

    @Override
    public void onActivityResult (int requestCode, int resultCode, Intent data)
    {
        if(requestCode == myCode)
        {
            if(resultCode == RESULT_OK)
            {
                mLocationLatitude = data.getDoubleExtra("ltl", 0.0);
                mLocationLongitude = data.getDoubleExtra("ltn", 0.0);

                Toast.makeText(this, "Location: " +mLocationLatitude + " " + mLocationLongitude, Toast.LENGTH_SHORT ).show();
            }
        }
    }

    protected void updateButton(){
        switch (status){
            case 0:
                mStartButton.setText("Please Configure");
                break;
            case 1:
                mStartButton.setText("Start");
                break;
            case 2:
                mStartButton.setText("Stop");
                break;
            default:
                mStartButton.setText("Please Configure");
                break;
        }
    }

    protected void runConf(){
        Intent config = new Intent(MainActivity.this, ConfActivity.class);
        MainActivity.this.startActivity(config);
        //to powinno być po skonfigurowaniu zmiennych
        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt("status", 1);
        editor.commit();
    }

    protected void run(){

        status = 2;
        updateButton();
        Intent intent = new Intent(MainActivity.this, MapsActivity.class);
        startActivityForResult(intent, myCode);


    }
    protected void stop(){
        status = 1;
        updateButton();
    }



}
