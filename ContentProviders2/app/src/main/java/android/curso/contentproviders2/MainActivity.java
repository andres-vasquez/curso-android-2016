package android.curso.contentproviders2;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView texto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        texto=(TextView)findViewById(R.id.texto);

        String[] columnas = new String[] {
                Clientes._ID,
                Clientes.COLUMNA_NOMBRE,
                Clientes.COLUMNA_EMAIL,
                Clientes.COLUMNA_DEUDA};

        //Uri clientesUri =  MiProvider.CONTENT_URI;
        Uri clientesUri =  Uri.parse("content://android.curso.contentproviders2/clientes");
        //Uri clientesUri =  Uri.parse("content://android.curso.contentproviders2/clientes/2");

        ContentResolver cr = getContentResolver();


        //Hacemos la consulta
        Cursor cur = cr.query(clientesUri,
                columnas, //Columnas a devolver
                null,       //Condición de la query
                null,       //Argumentos variables de la query
                null);      //Orden de los resultados

        //Recorremos datos de la consulta
        if (cur.moveToFirst())
        {
            String nombre;
            String deuda;
            String email;

            int colNombre = cur.getColumnIndex(Clientes.COLUMNA_NOMBRE);
            int colDeuda = cur.getColumnIndex(Clientes.COLUMNA_DEUDA);
            int colEmail = cur.getColumnIndex(Clientes.COLUMNA_EMAIL);

            texto.setText("");

            do
            {
                nombre = cur.getString(colNombre);
                email = cur.getString(colEmail);
                deuda = cur.getString(colDeuda);

                texto.append(nombre + " - " + deuda + " - " + email + "\n");

            } while (cur.moveToNext());
        }

        ContentValues values = new ContentValues();
        values.put(Clientes.COLUMNA_NOMBRE, "Andres");
        values.put(Clientes.COLUMNA_DEUDA, "110");
        values.put(Clientes.COLUMNA_EMAIL, "andres.vasquez.a@hotmail.com");
        cr.insert(MiProvider.CONTENT_URI, values);

        ContentValues values1 = new ContentValues();
        values1.put(Clientes.COLUMNA_NOMBRE, "Andres13");
        String[] args=new String[]{"Andres"};
        cr.update(clientesUri, values1, Clientes.COLUMNA_NOMBRE+"=?", args);
    }

}
