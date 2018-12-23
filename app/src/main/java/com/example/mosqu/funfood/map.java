package com.example.mosqu.funfood;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;

public class map extends AppCompatActivity implements OnMapReadyCallback{

    private final static int REQUEST_PERMISSIONS = 1;
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case REQUEST_PERMISSIONS:
                for(int result:grantResults){
                    if(result != PackageManager.PERMISSION_GRANTED) {
                        finish();
                    } else {
                        SupportMapFragment map = (SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.map);
                        map.getMapAsync((OnMapReadyCallback) this);
                    }
                }
                break;
        }
    }

    food.Food food;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        if (ActivityCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_COARSE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED)
            ActivityCompat.requestPermissions(this, new
                    String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_PERMISSIONS);
        else{
            SupportMapFragment map =
                    (SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.map);
            map.getMapAsync((OnMapReadyCallback) this);
        }

        Bundle bundle = getIntent().getExtras();
        String foodJson = bundle.getString("json");
        food = new Gson().fromJson(foodJson, food.Food.class);
    }

    @Override
    public void onMapReady(GoogleMap googleMap){
        if (ActivityCompat.checkSelfPermission(map.this,
                android.Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(map.this,
                android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
            return;
        googleMap.setMyLocationEnabled(true);
        MarkerOptions m1 = new MarkerOptions();
        m1.position(new LatLng(food.lat, food.lon));
        m1.title(food.name + '\n' + "地址" + food.addr + '\n' + "電話" + food.tel);
        m1.draggable(true);
        googleMap.addMarker(m1);
        /*LocationManager locationManager = (LocationManager) getApplication().getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        Location location = locationManager.getLastKnownLocation(locationManager.getBestProvider(criteria, false));
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(new LatLng(location.getLatitude(), location.getLongitude()))
                .zoom(12.8f)
                .build();
*/
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(food.lat, food.lon), 12.8f));
    }
}
