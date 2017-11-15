package com.example.piotrskorupa.whereismyphone;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conf2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // input text fields
        loginText = (EditText) findViewById(R.id.login);
        passText = (EditText) findViewById(R.id.password);
        euiText = (EditText) findViewById(R.id.eui);
        intervalText = (EditText) findViewById(R.id.interval);
        secretText = (EditText) findViewById(R.id.secret);
        // buttons
        saveButton1 = (Button) findViewById(R.id.button_save);
        saveButton2 = (Button) findViewById(R.id.button_save2);
        testButton = (Button) findViewById(R.id.button_test);

        saveButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                form.setLogin(loginText.getText().toString());
                form.setPassword(passText.getText().toString());
                Toast.makeText(ConfActivity.this, form.getLogin(), Toast.LENGTH_LONG).show();
            }
        });

        saveButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                form.setEui(euiText.getText().toString());
                form.setInterval(intervalText.getText().toString());
                Toast.makeText(ConfActivity.this, form.getEui(), Toast.LENGTH_LONG).show();
            }
        });


    }

}
