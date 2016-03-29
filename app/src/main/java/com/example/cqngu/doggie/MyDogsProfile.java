package com.example.cqngu.doggie;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.zxing.WriterException;

public class MyDogsProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_dogs_profile);
        Bitmap bitmap;
        Intent in = getIntent();

        TextView iv = (TextView) findViewById(R.id.myDogNameTextView);
        iv.setText(iv.getText() + " " + in.getStringExtra("name"));
        iv = (TextView) findViewById(R.id.myDogTypeTextView);
        iv.setText(iv.getText() + " " + in.getStringExtra("type"));
        ImageView myView = (ImageView) findViewById(R.id.imageView);
        System.out.println(in.getStringExtra("key"));
        try {
             bitmap = QRActivity.encodeAsBitmap(in.getStringExtra("key"));
            myView.setImageBitmap(bitmap);
        } catch (WriterException e) {
            e.printStackTrace();
        }


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
