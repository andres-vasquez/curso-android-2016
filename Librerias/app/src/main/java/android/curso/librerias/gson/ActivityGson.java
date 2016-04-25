package android.curso.librerias.gson;

import android.content.Context;
import android.content.SharedPreferences;
import android.curso.librerias.Persona;
import android.curso.librerias.R;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;


public class ActivityGson extends AppCompatActivity {

    private Button btnSerializar;
    private Button btnDeSerializar;

    private TextView txtResultado;

    private EditText txtNombre;
    private EditText txtApellido;
    private EditText txtEdad;

    private LinearLayout lyResultado;

    private Context context;
    private SharedPreferences preferences;

    private String strPersona="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_gson);

        context=this;
        preferences= PreferenceManager.getDefaultSharedPreferences(context);
        // Handle Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        //Campos del formulario
        lyResultado=(LinearLayout)findViewById(R.id.lyResultado);

        btnSerializar=(Button)findViewById(R.id.btnSerializar);
        btnDeSerializar=(Button)findViewById(R.id.btnDeSerializar);

        txtResultado=(TextView)findViewById(R.id.txtResultado);

        txtNombre=(EditText)findViewById(R.id.txtNombre);
        txtApellido=(EditText)findViewById(R.id.txtApellido);
        txtEdad=(EditText)findViewById(R.id.txtEdad);

        //Click serializar
        btnSerializar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Contruimos el objeto person
                Persona persona=new Persona();
                persona.setNombre(txtNombre.getText().toString());
                persona.setApellido(txtApellido.getText().toString());

                try{
                    persona.setEdad(Integer.parseInt(txtEdad.getText().toString()));
                }catch (Exception e){
                    persona.setEdad(0);
                }

                //Lo convertimos en un String
                Gson gson=new Gson();
                strPersona=gson.toJson(persona);

                //Puede ser util guardar Objetos como STRING en SharedPreferences
                SharedPreferences.Editor editor=preferences.edit();
                editor.putString("persona",strPersona);
                editor.commit();

                //Mostramos el String en un TextView resultado
                txtResultado.setText(strPersona);
                btnSerializar.setEnabled(false);

                //Limpiamos los campos
                txtNombre.setText("");
                txtApellido.setText("");
                txtEdad.setText("");

                lyResultado.setVisibility(View.VISIBLE);
            }
        });

        btnDeSerializar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try
                {
                    //Obtenemos el objeto desde el String
                    Gson gson=new Gson();
                    Persona persona=gson.fromJson(strPersona,Persona.class);

                    //Mostramos en el formulario
                    txtNombre.setText(persona.getNombre());
                    txtEdad.setText(String.valueOf(persona.getEdad()));
                    txtApellido.setText(persona.getApellido());

                }catch (Exception e)
                {

                }

                //Vaciamos el resultado
                txtResultado.setText("");
                btnSerializar.setEnabled(true);
                lyResultado.setVisibility(View.GONE);
            }
        });
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
}
