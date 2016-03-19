package com.example.cqngu.doggie;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.firebase.client.ServerValue;

import java.util.ServiceConfigurationError;

public class MainActivity extends AppCompatActivity {

    Button cameraBttn;
    Button listBttn;
    ImageView iv;
    MyLocationListener mLocationListener;
    LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Firebase.setAndroidContext(this);
        Firebase myFirebaseRef = new Firebase("https://husciiprogramming.firebaseIO.com/");
        Firebase myFirebaseRef2 = myFirebaseRef.push();
        String key = myFirebaseRef2.getKey();


        Dog a = new Dog("Tarik", "NO", key

        );

        myFirebaseRef2.setValue(a);




        myFirebaseRef.child(key).addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot snapshot) {
                Object test = snapshot.getValue();
                System.out.println(test.toString());
            }

            @Override
            public void onCancelled(FirebaseError error) {
            }

        });

        setContentView(R.layout.activity_main);

        iv=(ImageView)findViewById(R.id.imageView);

        cameraBttn = (Button) findViewById(R.id.cameraBttn);
        cameraBttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,0);
            }
        });

        listBttn = (Button) findViewById(R.id.listBttn);
        listBttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ListActivity.class));
            }
        });



        Button location = (Button) findViewById(R.id.locationBttn);
        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLocationListener = new MyLocationListener();
                 locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);


                if (ContextCompat.checkSelfPermission(MainActivity.this,
                        Manifest.permission.READ_CONTACTS)
                        != PackageManager.PERMISSION_GRANTED) {
                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, mLocationListener);
                    locationManager.removeUpdates(mLocationListener);
                    } else {
                        System.out.println("Not permission");
                }


                }


        });
    }



    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            // Image captured and saved to fileUri specified in the Intent
            Bitmap bp = (Bitmap) data.getExtras().get("data");
            iv.setImageBitmap(bp);

        } else if (resultCode == RESULT_CANCELED) {
            // User cancelled the image capture
            startActivity(new Intent(MainActivity.this, MainActivity.class));
        } else {
            // Image capture failed, advise user
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
