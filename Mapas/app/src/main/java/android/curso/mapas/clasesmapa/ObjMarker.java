package android.curso.mapas.clasesmapa;

/**
 * Created by andresvasquez on 4/16/15.
 */
public class ObjMarker {

    public int id;
    public String nombre;
    public String icono;
    public ObjPunto punto;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getIcono() {
        return icono;
    }

    public void setIcono(String icono) {
        this.icono = icono;
    }
    public ObjPunto getPunto() {
        return punto;
    }

    public void setPunto(ObjPunto punto) {
        this.punto = punto;
    }
}
