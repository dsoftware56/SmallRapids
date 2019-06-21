package jetpack.mydev.com.small_rapids.view;

import androidx.fragment.app.FragmentActivity;
import jetpack.mydev.com.small_rapids.R;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import static jetpack.mydev.com.small_rapids.utils.ConstVars.LOG_TAG;
import static jetpack.mydev.com.small_rapids.utils.ConstVars.TAM_ICON_PARTNER;
import static jetpack.mydev.com.small_rapids.utils.ConstVars.TAM_ICON_PARTNER_INT;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(LOG_TAG, "-----------------onCreate-------------------");
        super.onCreate(savedInstanceState);
        //Quitamos el título de la ventana
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //Seteamos el layout
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(19.4405861, -99.2034689);
        Bitmap bmIcon=BitmapFactory.decodeResource(getResources(), R.drawable.ico_cycle);
        bmIcon = Bitmap.createScaledBitmap(bmIcon,
                TAM_ICON_PARTNER_INT,
                TAM_ICON_PARTNER_INT,
                false);
        BitmapDescriptor bmdIcon = BitmapDescriptorFactory.fromBitmap(bmIcon);

        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney").icon(bmdIcon));
        LatLng sydney1 = new LatLng(19.4504248, -99.2131273);
        mMap.addMarker(new MarkerOptions().position(sydney1).title("Marker in Sydney").icon(bmdIcon));
        mMap.setMaxZoomPreference(20);
        mMap.setMinZoomPreference(15);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

    private MarkerOptions getMark(final float latitud, final float longitud){
        return null;
    }
}
