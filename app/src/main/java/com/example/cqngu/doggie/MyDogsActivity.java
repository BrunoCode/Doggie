package com.example.cqngu.doggie;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;

public class MyDogsActivity extends AppCompatActivity {

    ArrayList<Dog> dogList;
    ArrayAdapter<Dog> dogAdapter;
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_dogs);


        dogList = MainActivity.currentUser.myDogList;


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
        //   android_id

    }
}