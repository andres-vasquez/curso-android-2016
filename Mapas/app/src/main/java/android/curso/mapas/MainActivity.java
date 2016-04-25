package android.curso.mapas;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    /*public void onMapReady(GoogleMap googleMap) {
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

        FuncionesMapa.CrearPoligono(objPoligono,mMap);
    }*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
