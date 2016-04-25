package android.curso.mapas.clasesmapa;

import java.util.List;

/**
 * Created by andresvasquez on 4/16/15.
 */
public class ObjPoligono {
    public int id;
    public String nombre;
    public String color;
    public List<Double> puntos;

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

    public List<Double> getPuntos() {
        return puntos;
    }

    public void setPuntos(List<Double> puntos) {
        this.puntos = puntos;
    }
}
