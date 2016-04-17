package android.curso.servicios;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private ServicioLista s;

    private ArrayAdapter<String> adaptador;
    private List<String> palabras;

    TimerTask scanTask;
    final Handler handler = new Handler();
    Timer t = new Timer();
    ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        lista=(ListView)findViewById(R.id.lista);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        Iniciar_tiempo();
        palabras = new ArrayList<String>();

        adaptador = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1,
                palabras);

        lista.setAdapter(adaptador);


        Button detener=(Button)findViewById(R.id.detener);
        detener.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v) {
                unbindService(conexion); //Nos desconectamos del servicio
                stopService(new Intent(MainActivity.this, ServicioLista.class)); //Lo detenemos

                Intent intent= new Intent(MainActivity.this, ServicioLista.class);
                bindService(intent, conexion,
                        Context.BIND_AUTO_CREATE); //Abrimos nuevamente el canal pero vacio
                Toast.makeText(getApplicationContext(), "El servicio fue detenido", Toast.LENGTH_SHORT).show();
            }
        });
    }

    protected void onResume() {
        super.onResume();
        Intent intent= new Intent(this, ServicioLista.class);
        bindService(intent, conexion,
                Context.BIND_AUTO_CREATE);
    }

    protected void onPause() {
        super.onPause();
        unbindService(conexion);
    }

    private ServiceConnection conexion = new ServiceConnection() {

        public void onServiceConnected(ComponentName className,
                                       IBinder binder)
        {
            ServicioLista.MyBinder b = (ServicioLista.MyBinder) binder;
            s = b.getService();
            Toast.makeText(MainActivity.this, "Conectado", Toast.LENGTH_SHORT)
                    .show();
        }

        public void onServiceDisconnected(ComponentName className) {
            s = null;
        }
    };


    public void onClick(View view)
    {
        if (s != null)
        {
            Toast.makeText(this, "Hay " + s.getLista().size()+" elementos en la lista",
                    Toast.LENGTH_SHORT).show();
            palabras.clear(); //Limpia el ListView
            palabras.addAll(s.getLista()); //Obtiene la nueva lista
            adaptador.notifyDataSetChanged(); //Actualiza el ListView
        }
    }

    private void Iniciar_tiempo()
    {
        Intent service = new Intent(MainActivity.this, ServicioLista.class);
        startService(service);

        scanTask = new TimerTask()
        {
            public void run() {
                handler.post(new Runnable() {
                    public void run() {
                        Intent service = new Intent(MainActivity.this, ServicioLista.class);
                        startService(service);
                    }
                });
            }};
        t.schedule(scanTask, 1000, 5000);
    }
}
