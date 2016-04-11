package android.curso.intentimplicito;

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
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText nombre;
    private Button enviar;
    private String mensaje;

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=this;

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Apuntamos a los id definidos en activity_main para llamar a los componentes visuales
        nombre=(EditText)findViewById(R.id.nombre);
        enviar=(Button)findViewById(R.id.enviar);

        enviar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mensaje = nombre.getText().toString();
                Intent ir = new Intent(getApplicationContext(), Menu.class);
                ir.putExtra("mensaje", mensaje);
                startActivity(ir);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        /*menu.add(Menu.NONE, 1, Menu.NONE, "Opcion1")
        .setIcon(android.R.drawable.ic_menu_camera);
		menu.add(Menu.NONE, 2, Menu.NONE, "Opcion2")
        .setIcon(android.R.drawable.ic_menu_compass);
		menu.add(Menu.NONE, 3, Menu.NONE, "Opcion3")
        .setIcon(android.R.drawable.ic_menu_agenda);*/
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.Menu1:
                //Accion del Menu 1
                Toast.makeText(context,"Menu 1",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.Menu2:
                //Accion del Menu 2
                Toast.makeText(context,"Menu 2",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.Menu3:
                //Accion del Menu 3
                Toast.makeText(context,"Menu 3",Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
