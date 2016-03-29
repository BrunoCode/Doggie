package com.example.cqngu.doggie;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class ListActivity extends AppCompatActivity {

    ArrayList<Dog> dogList;
    ArrayAdapter<Dog> dogAdapter;
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);


        dogList = new ArrayList<Dog>();
        loadList();

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

    void loadList() {
//        File file = new File(getFilesDir(), "mydogs");
        final String[] keyList = readFile("dogList");
        String[] aDog;
        Set<String> added = new HashSet<String>();
        if (keyList != null) {
            for (int i = 0; i < keyList.length; i++) {
//                System.out.println(keyList[i]);
                if (!added.contains(keyList[i])) {
                    added.add(keyList[i]);

                    MainActivity.myFirebaseRef.child(keyList[i]).addValueEventListener(new ValueEventListener() {

                        @Override
                        public void onDataChange(DataSnapshot snapshot) {
                            HashMap<String, String> test = (HashMap<String, String>) snapshot.getValue();
//                        System.out.println(test);
                            Dog dog = new Dog(test.get("name"), test.get("type"));

                            dogList.add(dog);
                        }

                        @Override
                        public void onCancelled(FirebaseError error) {
                        }

                    });

                }
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
