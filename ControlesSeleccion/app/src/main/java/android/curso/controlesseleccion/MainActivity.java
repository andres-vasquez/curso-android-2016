package android.curso.controlesseleccion;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Spinner spinner;
    ListView lista;
    String[] opciones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        spinner=(Spinner)findViewById(R.id.spinner);
        lista=(ListView)findViewById(R.id.lista);

        opciones=new String[]{"opcion1","opcion2","opcion3"};
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, opciones);

        spinner.setAdapter(adaptador);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> array, View vista,
                                       int posicion, long id) {
                Log.e("Item seleccionado", (String) array.getItemAtPosition(posicion));
            }

            public void onNothingSelected(AdapterView<?> array) {
            }
        });

        opciones=getResources().getStringArray(R.array.array_productos);
        ArrayAdapter<String> adaptador1 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, opciones);
        lista.setAdapter(adaptador1);
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> array, View vista, int posicion, long id) {
                TextView texto = (TextView) vista.findViewById(android.R.id.text1);
                String contenido = texto.getText().toString();
                Log.e("Item seleccionado", contenido);
            }
        });
    }
}
