package jetpack.mydev.com.small_rapids.presenter;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.util.Log;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import static jetpack.mydev.com.small_rapids.utils.ConstVars.LOG_TAG;

/**
 * Description:
 * Created by EX383473 on 07/06/2019.
 */
public class GpsPresenter {
    private final Activity context;
    private LocationManager locationManager;
    private Location location;

    public GpsPresenter(final Activity context) {
        this.context = context;
        if (ContextCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_FINE_LOCATION) ==
                PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_COARSE_LOCATION) ==
                        PackageManager.PERMISSION_GRANTED) {
            locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        } else {
            ActivityCompat.requestPermissions(context, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 200);
        }
    }

    public Location getLocation() {
        Log.i(LOG_TAG, "--------------------getLocation---------------------");
        if (ContextCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_FINE_LOCATION) ==
                PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_COARSE_LOCATION) ==
                        PackageManager.PERMISSION_GRANTED) {
            Log.i(LOG_TAG, "Si tiene permisos...");
            location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            if (location != null) {
                Log.i(LOG_TAG, "latitud:" + location.getLatitude() + " longitud:" + location.getLongitude());
                Toast.makeText(context, "latitude:" + location.getLatitude() + " longitude:" + location.getLongitude(), Toast.LENGTH_LONG).show();
                return location;
            } else {
                Log.i(LOG_TAG, "La locación de NETWORK_PROVIDER es null...");
                location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                if (location != null) {
                    Log.i(LOG_TAG, "latitud:" + location.getLatitude() + " longitud:" + location.getLongitude());
                    Toast.makeText(context, "latitude:" + location.getLatitude() + " longitude:" + location.getLongitude(), Toast.LENGTH_LONG).show();
                    return location;
                } else {
                    Log.i(LOG_TAG, "La locación de GPS_PROVIDER es null...");
                }
            }
        } else {
            ActivityCompat.requestPermissions(context, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 200);
        }
        return null;
    }

}
