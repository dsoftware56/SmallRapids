package jetpack.mydev.com.small_rapids.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.maps.MapView;

import jetpack.mydev.com.small_rapids.R;
import jetpack.mydev.com.small_rapids.presenter.MapPresenter;

import static jetpack.mydev.com.small_rapids.utils.ConstVars.LOG_TAG;

public class MapActivity extends Activity {
    public MapView mapView;
    private MapPresenter mapPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.i(LOG_TAG, "-----------------onCreate-------------------");
        super.onCreate(savedInstanceState);
        //Quitamos el título de la ventana
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //Llave para los mapas
        Mapbox.getInstance(this, "pk.eyJ1IjoiamF2aWVycHVsaWRvNzMiLCJhIjoiY2p3bWk2cm5xMDFmdTQ5bnNwaHI0Z21ibiJ9.3tc_UdaV74WWoO2by0tcRw");
        //Seteamos el layout
        setContentView(R.layout.activity_map);
        //Inicializamos el mapa
        mapView = findViewById(R.id.mapView);
        //Inicializamos el presentador
        mapPresenter = new MapPresenter(this, savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    public void onBackPressed() {
        Log.i(LOG_TAG, "LoginActivity: ----------------------onBackPressed---------------------------");
        //Cuando se presiona el botón back minimizamos la aplicación
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}