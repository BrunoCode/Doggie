package com.example.cqngu.doggie;

import android.content.Intent;
import android.graphics.Bitmap;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import android.provider.Settings.Secure;

public class MainActivity extends AppCompatActivity {


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
        setContentView(R.layout.activity_main);
        android_id = Secure.getString(getContentResolver(), Secure.ANDROID_ID);
        myFirebaseRef = new Firebase("https://husciiprogramming.firebaseIO.com/");


        System.out.println(android_id );




//        if (currentUser == null) {
//
//            myFirebaseRef.child("users").child(android_id).addValueEventListener(new ValueEventListener() {
//
//                @Override
//                public void onDataChange(DataSnapshot snapshot) {
//                    currentUser = ((HashMap<String, UserProfile>) (snapshot.getValue())).get(android_id);
//
//                    if (currentUser == null) {
//                        Firebase myFirebaseRef2 = myFirebaseRef.child("users").child(android_id);
//                        currentUser = new UserProfile(android_id);
//                        myFirebaseRef2.setValue(currentUser);
//                    }
//                }
//
//                @Override
//                public void onCancelled(FirebaseError error) {
//                }
//
//            });
//        }



        Button cameraBttn = (Button) findViewById(R.id.cmrBttn);
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

//                Button myDogBttn = (Button) findViewById(R.id.myDogsBttn);
//        myDogBttn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                             startActivity(new Intent(MainActivity.this, MyDogsActivity.class));
//
//            }
//        });


    }



    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, intent);



        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if (scanningResult != null) {
            String key = scanningResult.getContents();

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
