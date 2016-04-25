package android.curso.librerias;

import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;

/**
 * Created by andresvasquez on 11/7/15.
 */
public class Persona {

    public Persona() {
    }

    public Persona(String nombre, String apellido, int edad) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
    }

    public Persona(int id, String nombre, String apellido, int edad) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
    }

    @SerializedName("id")
    @DatabaseField(generatedId = true, columnName = "_id")
    public int id;

    @SerializedName("nombre")
    @DatabaseField(columnName = "nombre")
    public String nombre;

    @SerializedName("apellido")
    @DatabaseField(columnName = "apellido")
    public String apellido;

    @SerializedName("edad")
    @DatabaseField(columnName = "edad")
    public int edad;

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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }
}
