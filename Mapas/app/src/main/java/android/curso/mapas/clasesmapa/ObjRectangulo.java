package android.curso.mapas.clasesmapa;

/**
 * Created by andresvasquez on 4/16/15.
 */
public class ObjRectangulo {
    public int id;
    public String nombre;
    public String color;
    public ObjPunto noreste;
    public ObjPunto sudoeste;

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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public ObjPunto getNoreste() {
        return noreste;
    }

    public void setNoreste(ObjPunto noreste) {
        this.noreste = noreste;
    }

    public ObjPunto getSudoeste() {
        return sudoeste;
    }

    public void setSudoeste(ObjPunto sudoeste) {
        this.sudoeste = sudoeste;
    }
}
