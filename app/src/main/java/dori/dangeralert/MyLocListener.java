package dori.dangeralert;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by Dori on 5/8/2016.
 */
public class MyLocListener implements LocationListener {
    @Override
    public void onLocationChanged(Location location)
    {
        if(location != null){
            Log.e("Latitude :", "" + location.getLatitude());
            Log.e("Latitude :", "" +location.getLongitude());
        }
    }

    @Override
    public void onProviderEnabled(String provider){

    }

    @Override
    public void onProviderDisabled(String provider){

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras){

    }
}
