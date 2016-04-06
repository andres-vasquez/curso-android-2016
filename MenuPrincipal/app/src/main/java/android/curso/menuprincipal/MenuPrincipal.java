package android.curso.menuprincipal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

public class MenuPrincipal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Recibimos los datos
        Intent b=getIntent();
        String[] datos_recibidos=new String[2];
        datos_recibidos=b.getStringArrayExtra("datos_usuario");

        //Mostramos el mensaje de bienvenida en pantalla
        Toast.makeText(this,"Bienvenido "+datos_recibidos[0],Toast.LENGTH_SHORT).show();

        /*
        Si entraste aca es porque tienes interÈs en realizar tu dashboard y crear tu carrito de compra.
                Tu tarea en este punto es:

        1. Darle funcionalidad a tu login, que realmente autentique, no permita campos en blanco, etc.
        2. Crear tu dashboard con los iconos que veas necesarios.
        3. Empezar a crear activities independientes, de manera conceptual con tal de mostrar que podrÌa
        ir en cada pantalla al presionar el boton en el dashboard.
        4. Conseguir algunos productos que ofrecer con sus respectivos precios.
        */

    }

}
