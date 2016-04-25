package android.curso.mapas.clasesmapa;

/**
 * Created by andresvasquez on 4/16/15.
 */
public class ObjPunto {
    public double lat;
    public double lon;

    public ObjPunto(double lat, double lon) {
        this.lat = lat;
        this.lon = lon;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }
}
