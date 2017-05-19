package schirmer.nicolas.aula09;

import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Main2Activity extends AppCompatActivity implements OnMapReadyCallback {

    GoogleMap mMap;
    Location location;
    double x = 0, y = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        getLocation();

        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.gmap);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng location = new LatLng(x, y);
        mMap.addMarker(
                new MarkerOptions().position(location)
                        .title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(location));
    }

    private void getLocation(){
        // dialog de permissão
        if ( ContextCompat.checkSelfPermission( this,
                android.Manifest.permission.ACCESS_COARSE_LOCATION ) != PackageManager.PERMISSION_GRANTED ) {

            ActivityCompat.requestPermissions( this,
                    new String[] {
                            android.Manifest.permission.ACCESS_FINE_LOCATION },
                     101);
        }

        if ( Build.VERSION.SDK_INT >= 23 &&
                ContextCompat.checkSelfPermission( this,
                        android.Manifest.permission.ACCESS_FINE_LOCATION ) != PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission( this,
                        android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return  ;
        }

        LocationManager locationManager = (LocationManager)
                getSystemService(LOCATION_SERVICE);

        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10, 10, new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                if(location != null) {
                    x =  (location.getLatitude());
                    y =  (location.getLongitude());
                }
            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {

            }
        });

        Criteria criteria = new Criteria();
        location = locationManager.getLastKnownLocation(
                locationManager.getBestProvider(criteria, false)
        );

        if(location != null) {
            x =  (location.getLatitude());
            y =  (location.getLongitude());
        }
    }


}
