package com.example.cqngu.doggie;

import android.content.Intent;
import android.graphics.Bitmap;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import org.opencv.android.OpenCVLoader;
import android.provider.Settings.Secure;
import android.content.Context;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    Button cameraBttn;
    Button listBttn;
    ImageView iv;
    Bitmap bitmap;
    MyLocationListener mLocationListener;
    LocationManager locationManager;
    String contents;
    static Firebase myFirebaseRef;
 //   static Firebase myFirebaseRef2;
    static String android_id;
    static UserProfile currentUser;

//    static {
//        if (!OpenCVLoader.initDebug()) {
//            Log.i("opencv", "opencv initialization failed");
//        } else {
//            Log.i("opencv", "opencv initialization successful");
//        }
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Firebase.setAndroidContext(this);
        android_id = Secure.getString(getContentResolver(), Secure.ANDROID_ID);
        myFirebaseRef = new Firebase("https://husciiprogramming.firebaseIO.com/");








        myFirebaseRef.child("users").child(android_id).addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if (snapshot == null) {
                    Firebase myFirebaseRef2 = myFirebaseRef.child("users").child(android_id);
                    currentUser = new UserProfile(android_id);
                    myFirebaseRef2.setValue(currentUser);
                } else {
                    currentUser = ((HashMap<String, UserProfile>) (snapshot.getValue())).get(android_id);
                }
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
//                Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
//                startActivityForResult(intent, 0);
                IntentIntegrator scanIntegrator = new IntentIntegrator(MainActivity.this);
                scanIntegrator.initiateScan();
            }
        });

//        listBttn = (Button) findViewById(R.id.listBttn);
//        listBttn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(MainActivity.this, ListActivity.class));
//            }
//        });
//
//
//
//        Button location = (Button) findViewById(R.id.locationBttn);
//        location.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mLocationListener = new MyLocationListener();
//                locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
//
//                if (ContextCompat.checkSelfPermission(MainActivity.this,
//                        Manifest.permission.READ_CONTACTS)
//                        != PackageManager.PERMISSION_GRANTED) {
//                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, mLocationListener);
//                    locationManager.removeUpdates(mLocationListener);
//                } else {
//                    System.out.println("Not permission");
//                }
//
//            }
//
//        });
//
//
//
//        Button qrBttn = (Button) findViewById(R.id.qrBttn);
//        qrBttn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                             startActivity(new Intent(MainActivity.this, QRActivity.class));
//
//            }
//        });
//

    }



    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, intent);

        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if (scanningResult != null) {
            String key = scanningResult.getContents();
//            TextView myAss = (TextView) findViewById(R.id.textView5);
//            myAss.setText("CONTENT: " + key);
            System.out.println("I got the key" + key);
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
        }
        else{
            Toast toast = Toast.makeText(getApplicationContext(),
                    "No scan data received!", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

//    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
//        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
//        if (scanningResult != null) {
//            String scanContent = scanningResult.getContents();
//            String scanFormat = scanningResult.getFormatName();
//            TextView myAss = (TextView) findViewById(R.id.textView5);
//            myAss.setText("CONTENT: " + scanContent);
//        }
//        else{
//            Toast toast = Toast.makeText(getApplicationContext(),
//                    "No scan data received!", Toast.LENGTH_SHORT);
//            toast.show();
//        }
//    }
}
