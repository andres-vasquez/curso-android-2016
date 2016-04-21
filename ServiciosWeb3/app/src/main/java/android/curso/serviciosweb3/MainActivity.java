package android.curso.serviciosweb3;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView txtResultado;
    private List<Empleado> empleados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        txtResultado=(TextView)findViewById(R.id.resultado);

        CargarXml tarea = new CargarXml();
        //tarea.execute("http://dreamyourapps.com/webservices/xml/servicios.php");
        tarea.execute("http://172.20.10.3:8888/webservices/xml/servicios.php");

    }

    private class CargarXml extends AsyncTask<String,Integer,Boolean> {

        protected Boolean doInBackground(String... params) {

            DOMxml saxparser = new DOMxml(params[0]);
            empleados = saxparser.parse();

            return true;
        }

        protected void onPostExecute(Boolean result) {

            txtResultado.setText("");
            for(int i=0; i<empleados.size(); i++)
            {
                txtResultado.setText(
                        txtResultado.getText().toString() +
                                System.getProperty("line.separator") +
                                empleados.get(i).getNombres()+"   "+empleados.get(i).getDepartamento()+"   "+empleados.get(i).getSueldo());
            }
        }
    }
}
