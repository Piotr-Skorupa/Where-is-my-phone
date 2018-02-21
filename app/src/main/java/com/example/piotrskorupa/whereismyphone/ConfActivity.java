package com.example.piotrskorupa.whereismyphone;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class ConfActivity extends AppCompatActivity {

    private Form form = Form.getInstance();

    private EditText loginText;
    private EditText passText;
    private EditText euiText;
    private EditText intervalText;
    private EditText secretText;

    private Button saveButton1;
    private Button saveButton2;
    private Button testButton;
    private ImageButton helpButton;

    private boolean isActive;
    private boolean isRegistred;
    SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conf2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        isActive = false;
        isRegistred = false;

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // input text fields
        loginText = (EditText) findViewById(R.id.login);
        passText = (EditText) findViewById(R.id.password);
        euiText = (EditText) findViewById(R.id.eui);
        intervalText = (EditText) findViewById(R.id.interval);
        secretText = (EditText) findViewById(R.id.secret);
        // get saved form
        sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        form.setLogin(sharedPref.getString("login", ""));
        form.setPassword(sharedPref.getString("pass", ""));
        form.setEui(sharedPref.getString("eui", ""));
        form.setInterval(sharedPref.getInt("interval", 1000));
        form.setSecret(sharedPref.getString("secret", ""));

        // set text when activity start
        loginText.setText(form.getLogin());
        passText.setText(form.getPassword());
        euiText.setText(form.getEui());
        intervalText.setText(String.valueOf(form.getInterval()));
        secretText.setText(form.getSecret());


        // buttons
        saveButton1 = (Button) findViewById(R.id.button_save);
        saveButton2 = (Button) findViewById(R.id.button_save2);
        testButton = (Button) findViewById(R.id.button_test);
        helpButton = (ImageButton) findViewById(R.id.help_button);

        saveButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                form.setLogin(loginText.getText().toString());
                form.setPassword(passText.getText().toString());
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("login", loginText.getText().toString());
                editor.putString("pass", passText.getText().toString());
                editor.commit();
                Toast.makeText(ConfActivity.this, "Data has been saved.", Toast.LENGTH_SHORT).show();
            }
        });

        saveButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                form.setEui(euiText.getText().toString());
                form.setInterval(Integer.parseInt(intervalText.getText().toString()));
                form.setSecret(secretText.getText().toString());
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("eui", euiText.getText().toString());
                editor.putInt("interval", Integer.parseInt(intervalText.getText().toString()));
                editor.putString("secret", secretText.getText().toString());
                editor.commit();
                Toast.makeText(ConfActivity.this, "Data has been saved.", Toast.LENGTH_SHORT).show();
            }
        });


        helpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://signomix.signocom.com/help/wimp/index.html?language=en"));
                startActivity(browserIntent);

            }
        });


        testButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //TODO: testing func
                new HttpClient().execute();

            }
        });

    }

}
