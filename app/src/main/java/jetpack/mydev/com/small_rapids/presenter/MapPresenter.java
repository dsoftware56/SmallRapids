package jetpack.mydev.com.small_rapids.presenter;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;

import com.mapbox.geojson.Feature;
import com.mapbox.geojson.Point;
import com.mapbox.mapboxsdk.camera.CameraPosition;
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.geometry.LatLngBounds;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.maps.Style;
import com.mapbox.mapboxsdk.style.layers.PropertyFactory;
import com.mapbox.mapboxsdk.style.layers.SymbolLayer;
import com.mapbox.mapboxsdk.style.sources.GeoJsonSource;

import androidx.annotation.NonNull;
import jetpack.mydev.com.small_rapids.R;
import jetpack.mydev.com.small_rapids.view.MapActivity;

import static jetpack.mydev.com.small_rapids.utils.ConstVars.LOG_TAG;
import static jetpack.mydev.com.small_rapids.utils.ConstVars.TAM_ICON_PARTNER;

/**
 * Description:
 * Created by EX383473 on 07/06/2019.
 */
public class MapPresenter implements
        OnMapReadyCallback {
    private final Activity view;
    private final MapView mapView;
    private MapboxMap mapboxMap;
    private final GpsPresenter gpsPresenter;

    public MapPresenter(final MapActivity view, final Bundle savedInstanceState) {
        //Referenciamos las variables
        this.view = view;
        this.mapView = view.mapView;
        this.gpsPresenter = new GpsPresenter(view);
        //Inicializamos el mapView
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);
    }


    @Override
    public void onMapReady(@NonNull final MapboxMap mapboxMap) {
        this.mapboxMap = mapboxMap;
        mapboxMap.setStyle(Style.MAPBOX_STREETS, new Style.OnStyleLoaded() {
            @Override
            public void onStyleLoaded(@NonNull Style style) {
                Location location = gpsPresenter.getLocation();
                if (location != null && location.getLongitude() != 0) {
                    //Ponemos en
                    CameraPosition position = new CameraPosition.Builder()
                            .target(new LatLng(location.getLatitude(), location.getLongitude())) // Sets the new camera position
                            //.zoom(3) // Sets the zoom
                            //.bearing(180) // Rotate the camera
                            .tilt(30) // Set the camera tilt
                            .build(); // Creates a CameraPosition from the builder
                    mapboxMap.animateCamera(CameraUpdateFactory
                            .newCameraPosition(position), 100);
                } else {
                    Log.i(LOG_TAG, "No se pudo extraer correctamente tu ubicación");
                }
                //Agregar un marcador
                style.addImage("marker-icon-id", BitmapFactory.decodeResource(view.getResources(), R.drawable.ico_cycle));
                GeoJsonSource geoJsonSource;
                if (location != null && location.getLongitude() != 0) {
                    //Temporalmente agregué la ubicación del usuario
                    geoJsonSource = new GeoJsonSource("source-id", Feature.fromGeometry(
                            Point.fromLngLat(location.getLongitude(), location.getLatitude())));
                } else {
                    //Aquí debo poner las coordenadas de los socios
                    geoJsonSource = new GeoJsonSource("source-id", Feature.fromGeometry(
                            Point.fromLngLat(-102.474168, 19.589456)));
                }
                style.addSource(geoJsonSource);
                SymbolLayer symbolLayer = new SymbolLayer("layer-id", "source-id");
                symbolLayer.withProperties(PropertyFactory.iconImage("marker-icon-id"), PropertyFactory.iconSize(TAM_ICON_PARTNER));
                style.addLayer(symbolLayer);
                /////////
                geoJsonSource = new GeoJsonSource("1", Feature.fromGeometry(
                        Point.fromLngLat(-99.1999952, 19.4504248)));
                style.addSource(geoJsonSource);
                symbolLayer = new SymbolLayer("1", "1");
                symbolLayer.withProperties(PropertyFactory.iconImage("marker-icon-id"), PropertyFactory.iconSize(TAM_ICON_PARTNER));
                style.addLayer(symbolLayer);
                /////////
                geoJsonSource = new GeoJsonSource("2", Feature.fromGeometry(
                        Point.fromLngLat(-99.2131273, 19.4504248)));
                style.addSource(geoJsonSource);
                symbolLayer = new SymbolLayer("2", "2");
                symbolLayer.withProperties(PropertyFactory.iconImage("marker-icon-id"), PropertyFactory.iconSize(TAM_ICON_PARTNER));
                style.addLayer(symbolLayer);
                /////////
                geoJsonSource = new GeoJsonSource("3", Feature.fromGeometry(
                        Point.fromLngLat(-99.2064325, 19.4454878)));
                style.addSource(geoJsonSource);
                symbolLayer = new SymbolLayer("3", "3");
                symbolLayer.withProperties(PropertyFactory.iconImage("marker-icon-id"), PropertyFactory.iconSize(TAM_ICON_PARTNER));
                style.addLayer(symbolLayer);
            }
        });
    }
}
