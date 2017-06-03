package com.chesnowitz.mapping;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback,
        GoogleMap.OnMarkerClickListener {

  private GoogleMap mMap;

  private static final LatLng Miami = new LatLng(25.7823907,-80.2994982);
  private static final LatLng Boca = new LatLng(26.3728251,-80.1523849);
  private static final LatLng Dunedin = new LatLng(28.0458001,-82.8292188);

  private Marker markerMiami;
  private Marker markerBoca;
  private Marker markerDunedin;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
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

    List<Marker> listOfMarkers = new ArrayList<>();

    mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

    markerBoca = mMap.addMarker(new MarkerOptions()
            .position(Boca)
            .title("Boca Raton"));
    markerBoca.setTag(0);
    listOfMarkers.add(markerBoca);


    markerDunedin = mMap.addMarker(new MarkerOptions()
            .position(Dunedin)
            .title("Dunedin")
            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
    markerDunedin.setTag(0);
    listOfMarkers.add(markerDunedin);

    markerMiami = mMap.addMarker(new MarkerOptions()
            .position(Miami)
            .title("Miami")
            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA)));
    markerMiami.setTag(0);
    listOfMarkers.add(markerMiami);


    mMap.setOnMarkerClickListener(this); // register the click listener

    for (Marker m : listOfMarkers) {
      Log.d("Marker -->", m.getTitle());


      LatLng latLngValue = new LatLng(m.getPosition().latitude, m.getPosition().longitude);
      mMap.addMarker(new MarkerOptions().position(latLngValue));
      mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLngValue, 5));
      mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLngValue, 7));
    }

    // Add a marker in Sydney and move the camera

//    LatLng cuba = new LatLng(23.114135, -82.3633567);
//    LatLng sydney = new LatLng(-34, 151);
//    mMap.addMarker(new MarkerOptions().position(cuba).title("Welcome to Cuba")
//    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN)));
//    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(cuba, 6));
  }

  @Override
  public boolean onMarkerClick(Marker marker) {

    Integer clickCount = (Integer) marker.getTag();

    if (clickCount != null) {
      clickCount = clickCount + 1;

      marker.setTag(clickCount);
      Toast.makeText(this, marker.getTitle() + " has been clicked " +
              clickCount +" times ", Toast.LENGTH_SHORT).show();
    }

    return false;
  }
}
