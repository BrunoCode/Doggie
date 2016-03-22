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

        String[] data = loadMyDogsData();
        if (data != null) {
            System.out.println(data.length);
            dogList = new ArrayList<Dog>();

            for (String oneDog : data) {
                String[] info = oneDog.split("\t");

                Dog dog = new Dog(info[0], info[1], info[2]);
                dogList.add(dog);
            }

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
                    in.putExtra("key", id);
                    startActivity(in);
                }
            });
        }

        Button addBttn = (Button) findViewById(R.id.myDogAddBttn);
        addBttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MyDogsActivity.this, NewDog.class));
            }
        });


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    String[] loadMyDogsData() {
        //   android_id
        String[] data = null;
        File file = new File(getFilesDir(), getString(R.string.my_dogs_file));
        if (file != null) {
            System.out.println(file.getAbsolutePath());
        }
        byte[] bytes = new byte[(int) file.length()];

        try {
            BufferedInputStream buf = new BufferedInputStream(new FileInputStream(file));
            buf.read(bytes, 0, bytes.length);
            buf.close();

            String converted = new String(bytes, "UTF-8");
            data = converted.split("\n");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return data;
    }
}