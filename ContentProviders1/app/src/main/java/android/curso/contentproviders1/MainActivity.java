package android.curso.contentproviders1;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CallLog;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {


    private Button llamadas;
    private Button sms;
    private Button contactos;
    private ListView lista;
    private EditText busqueda;

    private SimpleAdapter sa;
    HashMap<String, String> item;
    ArrayList<HashMap<String, String>> list;

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

        llamadas = (Button) findViewById(R.id.llamdas);
        sms = (Button) findViewById(R.id.mensajes);
        contactos = (Button) findViewById(R.id.contactos);
        lista = (ListView) findViewById(R.id.lista);
        busqueda = (EditText) findViewById(R.id.busqueda);

        list = new ArrayList<HashMap<String, String>>();

        llamadas.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                list.clear();

                String orden = android.provider.CallLog.Calls.DATE + " DESC";
                if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_CALL_LOG) != PackageManager.PERMISSION_GRANTED)
                {
                    Toast.makeText(MainActivity.this,"No cuenta con los permisos necesarios",Toast.LENGTH_LONG).show();
                    return;
                }
                Cursor cursor = MainActivity.this.getContentResolver().query(CallLog.Calls.CONTENT_URI, null,
                        null, null, orden);

                int numero = cursor.getColumnIndex(CallLog.Calls.NUMBER);
                int duracion = cursor.getColumnIndex(CallLog.Calls.DURATION);

                int contador=0;
                while (cursor.moveToNext())
                {

                    item = new HashMap<String,String>();
                    item.put( "linea1", "Número: "+cursor.getString(numero));
                    item.put( "linea2","Duración: "+cursor.getString(duracion)+" segundos");
                    list.add( item );
                }
                cursor.close();

                sa = new SimpleAdapter(MainActivity.this, list ,
                        android.R.layout.two_line_list_item,
                        new String[] { "linea1","linea2" },
                        new int[] {android.R.id.text1, android.R.id.text2});

                lista.setAdapter(sa);

            }
        });

        sms.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                list.clear();
                String[] args=new String[]{"77700"};
                Uri mensajes = Uri.parse("content://sms");
                Cursor cursor1 = MainActivity.this.getContentResolver().query(mensajes,
                        new String[] { "_id", "thread_id", "address", "person", "date",
                                "body", "type" }, "address=?", args, null);

                String[] columnas = new String[] { "address", "person", "date", "body",
                        "type" };
                int contador=0;
                while (cursor1.moveToNext())
                {

                    if(contador<=50)
                    {
                        item = new HashMap<String,String>();
                        item.put( "linea1", "Número: "+cursor1.getString(cursor1
                                .getColumnIndex(columnas[0])));
                        item.put( "linea2","Mensaje: "+cursor1.getString(cursor1
                                .getColumnIndex(columnas[3])));
                        list.add( item );
                        contador++;
                    }
                }
                cursor1.close();

                sa = new SimpleAdapter(MainActivity.this, list ,
                        android.R.layout.two_line_list_item,
                        new String[] { "linea1","linea2" },
                        new int[] {android.R.id.text1, android.R.id.text2});

                lista.setAdapter(sa);
            }
        });

        contactos.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                list.clear();
                Cursor cursor = getContacts();
                int contador=0;
                while (cursor.moveToNext())
                {
                    if(contador<=200)
                    {
                        item = new HashMap<String,String>();
                        item.put( "linea1", cursor.getString(cursor
                                .getColumnIndex(ContactsContract.Data.DISPLAY_NAME)));
                        item.put( "linea2","");
                        list.add( item );
                        contador++;
                    }
                }
                cursor.close();
                sa = new SimpleAdapter(MainActivity.this, list ,
                        android.R.layout.two_line_list_item,
                        new String[] { "linea1","linea2" },
                        new int[] {android.R.id.text1, android.R.id.text2});

                lista.setAdapter(sa);
            }
        });


        busqueda.addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                sa.getFilter().filter(s.toString());
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            public void afterTextChanged(Editable s) {

            }
        });
    }

    private Cursor getContacts()
    {
        Uri uri = ContactsContract.Contacts.CONTENT_URI;
        String[] contactos = new String[] { ContactsContract.Contacts._ID,
                ContactsContract.Contacts.DISPLAY_NAME };
        String condicion = ContactsContract.Contacts.IN_VISIBLE_GROUP + " = '"
                + ("1") + "'";
        String orden = ContactsContract.Contacts.DISPLAY_NAME
                + " COLLATE LOCALIZED ASC";
        return managedQuery(uri, contactos, condicion, null,orden);
    }
}
