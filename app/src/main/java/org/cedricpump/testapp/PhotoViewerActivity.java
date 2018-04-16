package org.cedricpump.testapp;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class PhotoViewerActivity  extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    Model model;
    int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_viewer);
        //SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
        //        .findFragmentById(R.id.photoMapView);
        //mapFragment.getMapAsync(this);


        ImageView imageView = findViewById(R.id.photoImageView);
        MapView mapView = findViewById(R.id.photoMapView);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);
        model = MainActivity.model;

        i = (int) getIntent().getExtras().get("photo_index");

        imageView.setImageBitmap(((Photo) model.photos.get(i)).bytedata);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        try{
            if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},2);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        Location location = ((Photo) model.photos.get(i)).location;

        if (location != null) {
            LatLng pos = new LatLng(location.getLatitude(), location.getLongitude());
            mMap.addMarker(new MarkerOptions().position(pos).title("photo position"));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(pos, 10f));
        }
    }
}
