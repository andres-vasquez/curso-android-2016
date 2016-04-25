package android.curso.mapas.clasesmapa;

/**
 * Created by andresvasquez on 4/16/15.
 */
public class ObjCirculo {
    public int id;
    public String nombre;
    public String color;
    public String radio;
    public ObjPunto centro;

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

    public String getRadio() {
        return radio;
    }

    public void setRadio(String radio) {
        this.radio = radio;
    }

    public ObjPunto getCentro() {
        return centro;
    }

    public void setCentro(ObjPunto centro) {
        this.centro = centro;
    }
}
