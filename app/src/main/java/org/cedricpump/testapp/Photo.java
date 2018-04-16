package org.cedricpump.testapp;

import android.graphics.Bitmap;
import android.location.Location;

public class Photo {
    Bitmap bytedata;
    Location location;
    String name = "";

    public Photo(Bitmap data){
        bytedata = data;
        location = null;
    }

    public void setLocation(Location location){
        this.location = location;
        name = location.getLongitude() + ", " + location.getLatitude();
    }
}
