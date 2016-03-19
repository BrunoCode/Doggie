package com.example.cqngu.doggie;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class DogProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dog_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent in = getIntent();

        TextView iv = (TextView) findViewById(R.id.textView2);
        iv.setText(iv.getText() + " " + in.getStringExtra("name"));
        iv = (TextView) findViewById(R.id.textView3);
        iv.setText(iv.getText() + " " + in.getStringExtra("type"));

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


}
