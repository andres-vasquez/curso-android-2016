package android.curso.almacenamiento2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText username;
    private EditText password;
    private Button enviar;
    private TextView resultados;

    String nombre_ingresado;
    String password_ingresado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Buscamos el archivo de preferencias llamado MisPreferencias
        SharedPreferences prefs =
                getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);

        //Se busca los datos almacenados. SI no hay el resultado será no.
        String usuario_almacenado = prefs.getString("usuario","no");
        String password_almacenado = prefs.getString("password","no");

        //Si hay datos de usuarios almacenados se irá a menu principal.
        if(usuario_almacenado.compareTo("no")!=0 && password_almacenado.compareTo("no")!=0)
        {
            Intent a=new Intent(getApplicationContext(),MenuPrincipal.class);
            finish();
            startActivity(a);
        }


        username=(EditText)findViewById(R.id.username);
        password=(EditText)findViewById(R.id.password);
        enviar=(Button)findViewById(R.id.enviar);
        resultados=(TextView)findViewById(R.id.resultados);

        enviar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                nombre_ingresado=username.getText().toString();
                password_ingresado=password.getText().toString();

                //Abrimos el archivo de preferencias
                SharedPreferences prefs =
                        getSharedPreferences("MisPreferencias",Context.MODE_PRIVATE);

                //Editamos los campos existentes
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("usuario", nombre_ingresado);
                editor.putString("password", password_ingresado);
                //Concretamos la edicion
                editor.commit();

                Toast.makeText(getApplicationContext(), "Usuario: " + nombre_ingresado + ", Password: " + password_ingresado, Toast.LENGTH_SHORT).show();

                Intent a=new Intent(getApplicationContext(),MenuPrincipal.class);
                startActivity(a);
                finish();
            }
        });
    }
}
