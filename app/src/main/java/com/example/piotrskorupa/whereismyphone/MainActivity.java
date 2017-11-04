package com.example.piotrskorupa.whereismyphone;

import android.content.Intent;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.content.SharedPreferences;

public class MainActivity extends AppCompatActivity {
    public static int NOT_REGISTERED = 0;
    private static int NOT_RUNNING = 1;
    private static int RUNNING = 2;
    private Button mStartButton;
    private int status;


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
                    //mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    //mTextMessage.setText(R.string.title_notifications);
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
    }
    protected void stop(){
        status = 1;
        updateButton();
    }

}
