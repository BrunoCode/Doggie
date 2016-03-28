package com.example.cqngu.doggie;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.HashMap;

public class NewDog extends AppCompatActivity {
    char NEW_LINE = '\n';
    char TAB = '\t';

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_dog);

        Button addDogBttn = (Button) findViewById(R.id.okAddBttn);
        addDogBttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = ((TextView) findViewById(R.id.editText)).getText().toString();
                String type = ((TextView) findViewById(R.id.editText2)).getText().toString();
                addToDB(name, type);
                startActivity(new Intent(NewDog.this, MyDogsActivity.class));
            }
        });
    }

    void addToDB(String name, String type) {
        Firebase firebaseref = MainActivity.myFirebaseRef.push();
        String key = firebaseref.getKey();

        Dog theDog = new Dog(name, type, key);
        MainActivity.currentUser.myDogList.add(theDog);
        firebaseref.child(key).setValue(theDog);

        try {
            // Add new dog to a list
            FileOutputStream fos = openFileOutput("mydogs", Context.MODE_APPEND);
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            bos.write(key.getBytes());
            bos.write(NEW_LINE);
            bos.close();

            // Create new dog profile file
            fos = openFileOutput(key, Context.MODE_APPEND);
            bos = new BufferedOutputStream(fos);
            bos.write(name.getBytes());
            bos.write(NEW_LINE);
            bos.write(type.getBytes());
            bos.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
