package org.cedricpump.testapp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Camera;
import android.location.Location;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    static final int IMAGE_REQUEST_CODE = 0;

    static Model model;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        model = new Model();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        update();
    }

    public void update(){
        TextView tv = (TextView) findViewById(R.id.welcomeText);
        tv.setText("Welcome!\nPhotos: " + model.photos.size());
    }

    public void onButtonTap(View v){
        Intent switchToList = new Intent(MainActivity.this, ListActivity.class);
        startActivity(switchToList);
    }

    public void onFABCameraTap(View v){
        Intent takePhoto = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(takePhoto,IMAGE_REQUEST_CODE);
    }

    public void onFABMapTap(View v){
        Intent switchToMap = new Intent(MainActivity.this, MapsActivity.class);
        startActivity(switchToMap);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == IMAGE_REQUEST_CODE) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                // TODO sauber machen mit Android Photo
                Photo photo = new Photo((Bitmap) data.getExtras().get("data"));

                try{
                    if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
                        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},2);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }

                GPSTracker gps = new GPSTracker(MainActivity.this);
                photo.setLocation(gps.getLocation());

                model.photos.add(photo);
                gps.stopUsingGPS();
                Toast t = Toast.makeText(getApplicationContext(), "Photo taken! (" + model.photos.size()+") \n" + photo.location.toString(),Toast.LENGTH_LONG);
                t.show();
                update();
            }
        }
    }
}
