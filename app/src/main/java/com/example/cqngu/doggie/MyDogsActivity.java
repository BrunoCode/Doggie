package com.example.cqngu.doggie;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.io.BufferedInputStream;
import java.io.File;

import com.firebase.client.DataSnapshot;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class MyDogsActivity extends AppCompatActivity {

    ArrayList<Dog> dogList;
    ArrayAdapter<Dog> dogAdapter;
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_dogs);



            dogList = new ArrayList<Dog>();

            loadMyDogsData();

            dogAdapter = new ArrayAdapter<Dog>(this, android.R.layout.simple_list_item_1, dogList);
            ListView lv = (ListView) findViewById(R.id.theListView);
            lv.setAdapter(dogAdapter);
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position,
                                        long id) {
                    Dog setDog = dogList.get((int) id);

                    Intent in = new Intent(MyDogsActivity.this, MyDogsProfile.class);
                    in.putExtra("name", setDog.getName());
                    in.putExtra("type", setDog.getType());
                    in.putExtra("key", setDog.getID());
                    startActivity(in);
                }
            });


        Button addBttn = (Button) findViewById(R.id.myDogAddBttn);
        addBttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MyDogsActivity.this, NewDog.class));
            }
        });


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    void loadMyDogsData() {
//        File file = new File(getFilesDir(), "mydogs");
        String[] keyList = readFile("mydogs");
        String[] aDog;

        if (keyList != null) {
            for (int i = 0; i < keyList.length; i++) {
                aDog = readFile(keyList[i]);

                System.out.println(keyList[i]);

                Dog dog = new Dog(aDog[0], aDog[1], keyList[i]);
                dogList.add(dog);
            }
        }
    }

    String[] readFile(String name) {
        String[] context = null;

        try {
            FileInputStream fis = openFileInput(name);
            BufferedInputStream bis = new BufferedInputStream(fis);
            byte[] b = new byte[(int) fis.getChannel().size()];
            bis.read(b);
            context = new String(b).split("\n");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return context;
    }
}