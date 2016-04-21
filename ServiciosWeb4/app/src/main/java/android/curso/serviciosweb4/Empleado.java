package android.curso.serviciosweb4;

import com.google.gson.annotations.SerializedName;

/**
 * Created by andresvasquez on 20/4/16.
 */
public class Empleado {

    @SerializedName("nombres")
    public String nombres;

    @SerializedName("departamento")
    public String departamento;

    @SerializedName("sueldo")
    public double sueldo;

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public double getSueldo() {
        return sueldo;
    }

    public void setSueldo(double sueldo) {
        this.sueldo = sueldo;
    }
}
