package android.curso.librerias.orm;

import android.app.Dialog;
import android.content.Context;
import android.curso.librerias.Persona;
import android.curso.librerias.R;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ActivityOrm extends AppCompatActivity implements ListaAdapter.Callback{

    private Context context;
    private List<Persona> items =new ArrayList<Persona>();
    private ListaAdapter adapter;

    private Button btnInsertar;
    private ListView lstBaseDatos;

    private DBHelper mDBHelper;
    private Dialog popup;

    private Button btnAccion;
    private EditText txtNombre;
    private EditText txtApellido;
    private EditText txtEdad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_orm);

        context=this;
        // Handle Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        //Definimos el popup para insertar y editar datos
        popup=new Dialog(context);
        popup.setContentView(R.layout.layout_popup);
        txtNombre=(EditText)popup.findViewById(R.id.txtNombre);
        txtApellido=(EditText)popup.findViewById(R.id.txtApellido);
        txtEdad=(EditText)popup.findViewById(R.id.txtEdad);
        btnAccion=(Button)popup.findViewById(R.id.btnAccion);

        btnInsertar=(Button)findViewById(R.id.btnInsertar);
        lstBaseDatos=(ListView)findViewById(R.id.lstBaseDatos);

        //Definimos el adapter colocamos this para que se implemente ListaAdapter.Callback de manera global
        adapter=new ListaAdapter(context, items, this);
        lstBaseDatos.setAdapter(adapter);

        //Accion de insertar
        btnInsertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnAccion.setText("Insertar");

                //Vaciamos los campos de form
                txtNombre.setText("");
                txtApellido.setText("");
                txtEdad.setText("");
                popup.show();

                btnAccion.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //Creamos el obj persona
                        Persona persona = new Persona();
                        persona.setNombre(txtNombre.getText().toString());
                        persona.setApellido(txtApellido.getText().toString());
                        try {
                            persona.setEdad(Integer.parseInt(txtEdad.getText().toString()));
                        } catch (Exception e) {
                            persona.setEdad(0);
                        }

                        //Guardamos en db
                        Dao dao;
                        try {
                            dao = getHelper().getPersonaDao();
                            dao.create(persona);
                        } catch (SQLException e) {
                            Log.e("ActivityOrm", "Error creando persona");
                        }
                        popup.dismiss();
                        obtenerRegistros();
                    }
                });

            }
        });
        obtenerRegistros();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_activity_zxing, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private DBHelper getHelper() {
        //Obtiene el archivo de interaccion con la db
        if (mDBHelper == null) {
            mDBHelper = OpenHelperManager.getHelper(this, DBHelper.class);
        }
        return mDBHelper;
    }

    @Override
    protected void onDestroy() {
        //Libera la base de datoss
        super.onDestroy();
        if (mDBHelper != null) {
            OpenHelperManager.releaseHelper();
            mDBHelper = null;
        }
    }


    private void obtenerRegistros()
    {
        //Realiza el listado de datos
        Dao dao;
        try {
            dao = getHelper().getPersonaDao();
            QueryBuilder queryBuilder = dao.queryBuilder();
            List<Persona> personas = dao.query(queryBuilder.prepare());
            if (!personas.isEmpty())
            {
                //Actualiza los items del ListView
                items.clear();
                items.addAll(personas);
            }
            else
                items.clear();

            adapter.notifyDataSetChanged();
        } catch (SQLException e) {
            Log.e("ActivityOrm", "Error cargando datos");
        }
    }

    @Override
    public void eliminar(Persona persona) {
        //Elimina la persona
        Dao dao;
        try {
            dao = getHelper().getPersonaDao();
            dao.delete(persona);
            obtenerRegistros();
        } catch (SQLException e) {
            Log.e("ActivityOrm", "Error eliminar usuario");
        }
    }

    @Override
    public void editar(final Persona persona) {
        //Muestra la persona en los campos del formulario
        btnAccion.setText("Editar");

        txtNombre.setText(persona.getNombre());
        txtApellido.setText(persona.getApellido());
        txtEdad.setText(String.valueOf(persona.getEdad()));

        popup.show();

        btnAccion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Crea el objPersona
                persona.setNombre(txtNombre.getText().toString());
                persona.setApellido(txtApellido.getText().toString());
                try {
                    persona.setEdad(Integer.parseInt(txtEdad.getText().toString()));
                } catch (Exception e) {
                    persona.setEdad(0);
                }

                //Lo actualiza en la base de datos
                Dao dao;
                try {
                    dao = getHelper().getPersonaDao();
                    dao.update(persona);
                    obtenerRegistros();
                } catch (SQLException e) {
                    Log.e("ActivityOrm", "Error editar persona");
                }
                popup.dismiss();
                obtenerRegistros();
            }
        });
    }
}
