package com.example.cqngu.doggie;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    ArrayList<Dog> dogList;
    ArrayAdapter<Dog> dogAdapter;
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);


        dogList = new ArrayList<Dog>();
        testList();

        dogAdapter = new ArrayAdapter<Dog>(this, android.R.layout.simple_list_item_1, dogList);
        ListView lv= (ListView) findViewById(R.id.theListView);
        lv.setAdapter(dogAdapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                Dog setDog = dogList.get((int) id);

                Intent in = new Intent(ListActivity.this, DogProfile.class);
                in.putExtra("name", setDog.getName());
                in.putExtra("type", setDog.getType());
                startActivity(in);
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    void testList() {
        Dog dog1 = new Dog("Chinh", "Chihuahua", "011");
        Dog dog2 = new Dog("Chris", "Husky", "112");

        dogList.add(dog1);
        dogList.add(dog2);
    }
}
