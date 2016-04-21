package android.curso.serviciosweb4;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by andresvasquez on 20/4/16.
 */
public class Resultado {

    @SerializedName("empleados")
    public List<Empleado> empleados;

    @SerializedName("correcto")
    public int correcto;

    public List<Empleado> getEmpleados() {
        return empleados;
    }

    public void setEmpleados(List<Empleado> empleados) {
        this.empleados = empleados;
    }

    public int getCorrecto() {
        return correcto;
    }

    public void setCorrecto(int correcto) {
        this.correcto = correcto;
    }
}
