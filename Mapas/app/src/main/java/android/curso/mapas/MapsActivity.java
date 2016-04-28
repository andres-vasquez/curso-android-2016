package android.curso.mapas;

import android.curso.mapas.clasesmapa.ObjCirculo;
import android.curso.mapas.clasesmapa.ObjMarker;
import android.curso.mapas.clasesmapa.ObjPoligono;
import android.curso.mapas.clasesmapa.ObjPunto;
import android.curso.mapas.clasesmapa.ObjRectangulo;
import android.curso.mapas.utils.FuncionesMapa;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps2);
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
        LatLng sydney = new LatLng(-17.418905, -66.129765);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));


        ObjMarker objMarker=new ObjMarker();
        objMarker.setId(1);
        objMarker.setIcono("icono1");
        objMarker.setNombre("Punto1");
        objMarker.setPunto(new ObjPunto(-17.418905, -66.129765));

        FuncionesMapa.CrearMarker(objMarker, mMap);

        ObjCirculo objCirculo=new ObjCirculo();
        objCirculo.setId(2);
        objCirculo.setNombre("Circulo1");
        objCirculo.setColor("#D0402F");
        objCirculo.setRadio("100");//m en Multicine
        objCirculo.setCentro(new ObjPunto(-16.511244, -68.121878));

        FuncionesMapa.CrearCirculo(objCirculo, mMap);

        ObjRectangulo objRectangulo=new ObjRectangulo();
        objRectangulo.setId(3);
        objRectangulo.setNombre("Rectangulo1");
        objRectangulo.setColor("#A3C0F2");
        objRectangulo.setNoreste(new ObjPunto(-16.522065, -68.112641));
        objRectangulo.setSudoeste(new ObjPunto(-16.526288, -68.109848));

        FuncionesMapa.CrearRectangulo(objRectangulo, mMap);//Obrajes

        ObjPoligono objPoligono=new ObjPoligono();
        objPoligono.setId(4);
        objPoligono.setNombre("Poligono1");
        objPoligono.setColor("#FFE168");

        List<Double> puntos=new ArrayList<Double>();
        puntos.add(-17.4220887989);
        puntos.add(-66.1310612876);
        puntos.add(-17.4220632073);
        puntos.add(-66.1305463035);
        puntos.add(-17.4220324973);
        puntos.add(-66.1299133021);
        puntos.add(-17.4216281491);
        puntos.add(-66.1299454886);
        puntos.add(-17.4210753677);
        puntos.add(-66.1299723107);
        puntos.add(-17.4210804861);
        puntos.add(-66.1305302102);
        puntos.add(-17.4211009595);
        puntos.add(-66.1310934741);
        puntos.add(-17.4211009595);
        puntos.add(-66.1311363894);
        objPoligono.setPuntos(puntos);

        FuncionesMapa.CrearPoligono(objPoligono, mMap);//Cochabamba

        final List<Double> lstPunto=new ArrayList<Double>();

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                if(lstPunto.size()<=10)
                {
                    lstPunto.add(latLng.latitude);
                    lstPunto.add(latLng.longitude);
                    Toast.makeText(getApplicationContext(),"Lat: "+latLng.latitude+", lon:"+latLng.longitude,Toast.LENGTH_SHORT).show();
                }
                else
                {
                    ObjPoligono objPoligono=new ObjPoligono();
                    objPoligono.setId(14);
                    objPoligono.setNombre("Poligono1");
                    objPoligono.setColor("#FFE168");
                    objPoligono.setPuntos(lstPunto);
                    FuncionesMapa.CrearPoligono(objPoligono, mMap);
                }
            }
        });
    }
}
