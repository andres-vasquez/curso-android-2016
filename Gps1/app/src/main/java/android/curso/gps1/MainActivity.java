package android.curso.gps1;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView lat;
    private TextView lon;

    public LocationManager locationManager;
    public LocationListener locationListener;
    public Location location;

    private int veces_gps_captado = 0;
    private double nlat;
    private double nlon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        lat = (TextView) findViewById(R.id.lat);
        lon = (TextView) findViewById(R.id.Lon);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(getApplicationContext(),"No tiene permisos de GPS",Toast.LENGTH_LONG).show();
            return;
        }

        location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

        locationListener = new LocationListener() {
            public void onLocationChanged(Location location) {
                nlat=location.getLatitude();
                nlon=location.getLongitude();

                lat.setText(""+nlat);
                lon.setText(""+nlon);

                veces_gps_captado++;

                if(veces_gps_captado==1)
                {
                    Vibrator vibrator = (Vibrator) getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
                    vibrator.vibrate(2000);
                }

                if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getApplicationContext(),"No tiene permisos de GPS",Toast.LENGTH_LONG).show();
                    return;
                }
                location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            }

            public void onProviderDisabled(String provider) {
                Log.i("LocAndroid",
                        "Proveedor GPS deshabilitado, habilitelo por favor");
                Intent in = new Intent(
                        android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(in);
            }

            public void onProviderEnabled(String provider) {
                Log.i("LocAndroid", "Proveedor habilitado");
            }

            public void onStatusChanged(String provider, int GpsStatus,
                                        Bundle extras)
            {
                switch(GpsStatus)
                {
                    case LocationProvider.AVAILABLE:
                        Log.i("Estado GPS", "Disponible");
                        break;
                    case LocationProvider.OUT_OF_SERVICE:
                        Log.i("Estado GPS", "Fuera de servicio");
                        break;
                    case LocationProvider.TEMPORARILY_UNAVAILABLE:
                        Log.i("Estado GPS", "Temporalmente no disponible");
                        break;
                }
            }
        };
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                5000, 0, locationListener);
    }
}
