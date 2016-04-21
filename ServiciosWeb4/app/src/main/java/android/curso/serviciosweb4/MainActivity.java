package android.curso.serviciosweb4;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private Button get;
    private Button post;
    private TableLayout tabla;
    private TableRow fila;
    TableRow.LayoutParams layoutFila;

    private String nuevo_nombre;
    private String nuevo_depto;
    private String nuevo_sueldo;

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        context=this;

        get = (Button) findViewById(R.id.get);
        post = (Button) findViewById(R.id.post);
        tabla = (TableLayout) findViewById(R.id.tabla);

        layoutFila = new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT);

        get.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                tabla.removeAllViews();
                MetodoGet();
            }
        });

        post.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                final Dialog popup = new Dialog(MainActivity.this);
                popup.setContentView(R.layout.popup);
                popup.setTitle("Nuevo registro");

                final EditText Nnombre = (EditText) popup.findViewById(R.id.nuevo_nombre);
                Spinner Ndepto = (Spinner) popup.findViewById(R.id.nuevo_departamento);
                final EditText Nsueldo = (EditText) popup.findViewById(R.id.nuevo_sueldo);
                Button enviar = (Button) popup.findViewById(R.id.enviar);

                ArrayAdapter adapterEs = ArrayAdapter.createFromResource(MainActivity.this, R.array.array_deptos, android.R.layout.simple_spinner_item);
                adapterEs.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                Ndepto.setAdapter(adapterEs);

                popup.show();

                Ndepto.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    public void onItemSelected(AdapterView<?> arg0, View arg1,
                                               int arg2, long arg3) {
                        nuevo_depto = arg0.getItemAtPosition(arg2).toString();
                    }

                    public void onNothingSelected(AdapterView<?> arg0) {

                    }
                });

                enviar.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        popup.dismiss();
                        nuevo_nombre = Nnombre.getText().toString();
                        nuevo_sueldo = Nsueldo.getText().toString();
                        MetodoPost();
                    }
                });
            }
        });
    }

    private void agregarFilas(String nombre, String departamento, String sueldo) {
        fila = new TableRow(this);
        fila.setLayoutParams(layoutFila);

        TextView nombre_empleado = new TextView(this);
        TextView departamento_empleado = new TextView(this);
        TextView sueldo_empleado = new TextView(this);

        nombre_empleado.setText(nombre);
        departamento_empleado.setText(departamento);
        sueldo_empleado.setText(sueldo);

        nombre_empleado.setBackgroundResource(R.drawable.celda_cuerpo);
        departamento_empleado.setBackgroundResource(R.drawable.celda_cuerpo);
        sueldo_empleado.setBackgroundResource(R.drawable.celda_cuerpo);


        sueldo_empleado.setGravity(Gravity.RIGHT);

        //Agregamos las vistas a la fila
        fila.addView(nombre_empleado);
        fila.addView(departamento_empleado);
        fila.addView(sueldo_empleado);
        tabla.addView(fila);

    }

    private ProgressDialog pDialog;
    private static String link_get = "http://dreamyourapps.com/webservices/android_json.php";
    private static String link_post = "http://dreamyourapps.com/webservices/android_json_post.php";

    private void MetodoGet()
    {
        pDialog = new ProgressDialog(context);
        HashMap<String, String> parametros = new HashMap();
        pDialog.setMessage("Cargando...");
        pDialog.show();

        JsonObjectRequest jsArrayRequest = new JsonObjectRequest(
                Request.Method.GET,
                link_get,
                new JSONObject(parametros),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        if (pDialog != null)
                            if (pDialog.isShowing())
                                pDialog.hide();
                        try
                        {
                            Resultado resultadoJson = new Gson().fromJson(response.toString(), Resultado.class);
                            if (resultadoJson.getCorrecto()==1)
                            {
                                agregarFilas("NOMBRE", "DEPARTAMENTO", "SUELDO");

                                for (Empleado empleado : resultadoJson.getEmpleados()) {
                                    agregarFilas(
                                            empleado.getNombres(),
                                            empleado.getDepartamento(),
                                            String.valueOf(empleado.getSueldo())
                                    );
                                }
                                Toast.makeText(getApplicationContext(),
                                        "Lista de empleados actualizada", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getApplicationContext(),
                                        "Error en la conexión", Toast.LENGTH_SHORT).show();
                            }


                        } catch (Exception ex) {
                            Log.e("Ejemplo", "Respuesta Volley:" + ex.toString());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (pDialog != null)
                            if (pDialog.isShowing())
                                pDialog.hide();
                        // Manejo de errores
                        Log.e("Ejemplo", "Error:" + error.getMessage());

                    }
                });
        WsPeticiones.getInstance(context).addToRequestQueue(jsArrayRequest);
    }

    private void MetodoPost()
    {
        pDialog = new ProgressDialog(context);
        pDialog.setMessage("Cargando...");
        pDialog.show();


        StringRequest jsArrayRequest = new StringRequest(
                Request.Method.POST,
                link_post,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (pDialog != null)
                            if (pDialog.isShowing())
                                pDialog.hide();
                        try {
                            Log.e("Respuesta", response);
                            Resultado resultadoJson = new Gson().fromJson(response, Resultado.class);
                            if (resultadoJson.getCorrecto() == 1) {
                                Toast.makeText(getApplicationContext(),
                                        "Empleado insertado", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getApplicationContext(),
                                        "Error en la conexión", Toast.LENGTH_SHORT).show();
                            }


                        } catch (Exception ex) {
                            Log.e("Ejemplo", "Respuesta Volley:" + ex.toString());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (pDialog != null)
                            if (pDialog.isShowing())
                                pDialog.hide();
                        // Manejo de errores
                        Log.e("Ejemplo", "Error:" + error.getMessage());

                    }
                }
        ) {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> pars = new HashMap<String, String>();
                pars.put("Content-Type", "application/x-www-form-urlencoded");
                return pars;
            }

            @Override
            public Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> parametros = new HashMap();
                parametros.put("nombres", nuevo_nombre);
                parametros.put("departamento", nuevo_depto);
                parametros.put("sueldo", nuevo_sueldo);
                return parametros;
            }
        };
        WsPeticiones.getInstance(context).addToRequestQueue(jsArrayRequest);
    }
}
