package com.example.cqngu.doggie;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MyDogsProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_dogs_profile);

        Intent in = getIntent();

        TextView iv = (TextView) findViewById(R.id.myDogNameTextView);
        iv.setText(iv.getText() + " " + in.getStringExtra("name"));
        iv = (TextView) findViewById(R.id.myDogTypeTextView);
        iv.setText(iv.getText() + " " + in.getStringExtra("type"));

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
