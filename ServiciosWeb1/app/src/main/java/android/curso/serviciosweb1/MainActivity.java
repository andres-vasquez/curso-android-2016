package android.curso.serviciosweb1;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.os.AsyncTask;
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

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button get;
    private Button post;
    private TableLayout tabla;
    private TableRow fila;
    TableRow.LayoutParams layoutFila;

    private String nuevo_nombre;
    private String nuevo_depto;
    private String nuevo_sueldo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        get=(Button)findViewById(R.id.get);
        post=(Button)findViewById(R.id.post);
        tabla=(TableLayout)findViewById(R.id.tabla);

        layoutFila = new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT);

        get.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                tabla.removeAllViews();
                new MetodoGet().execute();
            }
        });

        post.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                final Dialog popup=new Dialog(MainActivity.this);
                popup.setContentView(R.layout.popup);
                popup.setTitle("Nuevo registro");

                final EditText Nnombre=(EditText)popup.findViewById(R.id.nuevo_nombre);
                Spinner Ndepto=(Spinner)popup.findViewById(R.id.nuevo_departamento);
                final EditText Nsueldo=(EditText)popup.findViewById(R.id.nuevo_sueldo);
                Button enviar=(Button)popup.findViewById(R.id.enviar);

                ArrayAdapter adapterEs=ArrayAdapter.createFromResource(MainActivity.this,R.array.array_deptos, android.R.layout.simple_spinner_item);
                adapterEs.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                Ndepto.setAdapter(adapterEs);

                popup.show();

                Ndepto.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    public void onItemSelected(AdapterView<?> arg0, View arg1,
                                               int arg2, long arg3) {
                        nuevo_depto=arg0.getItemAtPosition(arg2).toString();
                    }

                    public void onNothingSelected(AdapterView<?> arg0) {

                    }
                });

                enviar.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v)
                    {
                        popup.dismiss();
                        nuevo_nombre=Nnombre.getText().toString();
                        nuevo_sueldo=Nsueldo.getText().toString();
                        new MetodoPost().execute();
                    }
                });
            }
        });
    }


    private void agregarFilas(String nombre,String departamento,String sueldo)
    {
        fila=new TableRow(this);
        fila.setLayoutParams(layoutFila);

        TextView nombre_empleado=new TextView(this);
        TextView departamento_empleado=new TextView(this);
        TextView sueldo_empleado=new TextView(this);

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
    JSONParser jParser = new JSONParser();
    ArrayList<HashMap<String, String>> datos_empleados;

    private static String link_get = "http://dreamyourapps.com/webservices/android_json.php";
    private static String link_post = "http://dreamyourapps.com/webservices/android_json_post.php";

    private String resultado;
    JSONArray JsonArray = null;
    int success;
    JSONArray ArrayDatos = null;

    class MetodoGet extends AsyncTask<String, String, String>
    {
        protected void onPreExecute()
        {
            super.onPreExecute();
            pDialog = new ProgressDialog(MainActivity.this);
            pDialog.setMessage("Descargando datos de empleados");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }

        protected String doInBackground(String... args)
        {
            success=0;
            resultado="";
            List<NameValuePair> params = new ArrayList<NameValuePair>();

            try
            {
                JSONObject json = jParser.makeHttpRequest(link_get, "GET", params);
                Log.d("El resultado en JSON: ", json.toString());

                try {
                    success = json.getInt("correcto");
                    if (success == 1)
                    {
                        resultado="ok";

                        ArrayDatos = json.getJSONArray("empleados");
                    }
                } catch (JSONException e)
                {
                    e.printStackTrace();
                }

            }catch (NullPointerException e)
            {
                resultado="no";
            }


            return null;
        }
        protected void onPostExecute(String file_url)
        {
            pDialog.dismiss();

            if(resultado.compareTo("ok")==0)
            {
                agregarFilas("NOMBRE", "DEPARTAMENTO", "SUELDO");

                for (int i = 0; i < ArrayDatos.length(); i++)
                {
                    ContentValues values = new ContentValues();
                    JSONObject c;
                    try {
                        c = ArrayDatos.getJSONObject(i);
                        String nombre=c.getString("nombres");
                        String departamento=c.getString("departamento");
                        String sueldo=c.getString("sueldo");
                        agregarFilas(nombre, departamento, sueldo);
                    }
                    catch (JSONException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }

                Toast.makeText(getApplicationContext(),
                        "Lista de empleados actualizada", Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(getApplicationContext(),
                        "Error en la conexión", Toast.LENGTH_SHORT).show();
            }

        }

    }

    class MetodoPost extends AsyncTask<String, String, String>
    {
        protected void onPreExecute()
        {
            super.onPreExecute();
            pDialog = new ProgressDialog(MainActivity.this);
            pDialog.setMessage("Enviando datos de empleados");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }

        protected String doInBackground(String... args)
        {
            success=0;
            resultado="";
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("nombres",nuevo_nombre));
            params.add(new BasicNameValuePair("departamento",nuevo_depto));
            params.add(new BasicNameValuePair("sueldo",nuevo_sueldo));

            try
            {
                JSONObject json = jParser.makeHttpRequest(link_post, "POST", params);

                Log.d("El resultado en JSON: ", json.toString());

                try {
                    success = json.getInt("correcto");
                    if (success == 1)
                    {
                        resultado="ok";
                    }
                } catch (JSONException e)
                {
                    e.printStackTrace();
                    resultado="no";
                }

            }catch (NullPointerException e)
            {
                resultado="no";
            }


            return null;
        }
        protected void onPostExecute(String file_url)
        {
            pDialog.dismiss();

            if(resultado.compareTo("ok")==0)
            {
                Toast.makeText(getApplicationContext(),
                        "Empleado insertado", Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(getApplicationContext(),
                        "Error en la conexión", Toast.LENGTH_SHORT).show();
            }

        }

    }
}
