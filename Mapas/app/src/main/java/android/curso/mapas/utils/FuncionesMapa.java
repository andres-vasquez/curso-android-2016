package android.curso.mapas.utils;

import android.curso.mapas.clasesmapa.ObjCirculo;
import android.curso.mapas.clasesmapa.ObjMarker;
import android.curso.mapas.clasesmapa.ObjPoligono;
import android.curso.mapas.clasesmapa.ObjPunto;
import android.curso.mapas.clasesmapa.ObjRectangulo;
import android.graphics.Color;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;

import java.util.List;

/**
 * Created by andresvasquez on 4/3/15.
 */
public class FuncionesMapa {

    final static String LOG = "FuncionesMapa";

    public static Marker CrearMarker(ObjMarker objMarker, GoogleMap mapa) {

        MarkerOptions options=new MarkerOptions();
        options.position(new LatLng(objMarker.getPunto().getLat(), objMarker.getPunto().getLon()));
        options.title(objMarker.getNombre());
        options.icon(BitmapDescriptorFactory.fromResource(ObtenerIcono(objMarker.getIcono())));

        Marker marker=mapa.addMarker(options);
        marker.setTitle(objMarker.getNombre());
        return marker;
    }

    public static Circle CrearCirculo(ObjCirculo objCirculo, GoogleMap mapa) {

        CircleOptions options=new CircleOptions();
        options.center(new LatLng(objCirculo.getCentro().getLat(),objCirculo.getCentro().getLon()));
        options.fillColor(Color.parseColor(objCirculo.getColor()));
        options.radius(Double.parseDouble(objCirculo.getRadio()));
        options.strokeWidth(1);
        options.zIndex(1);

        Circle circulo=mapa.addCircle(options);
        return circulo;
    }

    public static Polygon CrearPoligono(ObjPoligono objPoligono, GoogleMap mapa) {

        PolygonOptions options=new PolygonOptions();
        options.fillColor(Color.parseColor(objPoligono.getColor()));
        options.strokeWidth(1);
        options.zIndex(1);

        List<Double> puntos=objPoligono.getPuntos();
        int i=0;
        while(i<puntos.size())
        {
            options.add(new LatLng(puntos.get(i),puntos.get(i+1)));
            i=i+2;
        }
        Polygon poligono=mapa.addPolygon(options);
        return poligono;
    }

    public static Polygon CrearRectangulo(ObjRectangulo objRectangulo, GoogleMap mapa) {

        PolygonOptions options=new PolygonOptions();
        options.fillColor(Color.parseColor(objRectangulo.getColor()));
        options.strokeWidth(1);
        options.zIndex(1);

        ObjPunto noreste=objRectangulo.getNoreste();
        ObjPunto sudoeste=objRectangulo.getSudoeste();

        options.add(new LatLng(noreste.getLat(),noreste.getLon()));
        options.add(new LatLng(sudoeste.getLat(),noreste.getLon()));
        options.add(new LatLng(sudoeste.getLat(),sudoeste.getLon()));
        options.add(new LatLng(noreste.getLat(),sudoeste.getLon()));

        Polygon poligono=mapa.addPolygon(options);
        return poligono;
    }

    public static int ObtenerIcono(String strIcono)
    {
        int icono=0;
        switch (strIcono)
        {
            case "icono1": icono= android.R.drawable.ic_media_play; break;
            case "icono2": icono= android.R.drawable.ic_menu_camera; break;
            case "icono3": icono= android.R.drawable.ic_menu_compass; break;
        }
        return icono;
    }
}
