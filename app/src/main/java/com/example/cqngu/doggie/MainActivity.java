package com.example.cqngu.doggie;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    Button cameraBttn;
    Button listBttn;
    ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
