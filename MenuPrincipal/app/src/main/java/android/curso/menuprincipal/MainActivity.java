package android.curso.menuprincipal;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText txtUsuario;
    private EditText txtPassword;
    private Button btnEnviar;
    private TextView txtResultado;

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Donde se ejecuta la App
        context=this;

        //Comentado porque no necesitamos el action bar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Vinculamos las variables con los IDs de la interfaz
        txtUsuario=(EditText)findViewById(R.id.txtUsuario);
        txtPassword=(EditText)findViewById(R.id.txtPassword);
        btnEnviar=(Button)findViewById(R.id.btnEnviar);
        txtResultado=(TextView)findViewById(R.id.txtResultado);

        //Evento de click en el botón
        btnEnviar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String campo_usuario = txtUsuario.getText().toString();
                String campo_password = txtPassword.getText().toString();

                if (campo_usuario.compareTo("hola") == 0 && campo_password.compareTo("mundo") == 0)
                {
                    Intent a=new Intent(context,MenuPrincipal.class);
                    String[] datos=new String[2];
                    datos[0]=txtUsuario.getText().toString();
                    datos[1]=txtPassword.getText().toString();
                    a.putExtra("datos_usuario", datos);
                    startActivity(a);

                }
                else
                    txtResultado.setText("Login fallido");
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
