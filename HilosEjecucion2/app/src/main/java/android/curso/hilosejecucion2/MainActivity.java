package android.curso.hilosejecucion2;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RemoteViews;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ProgressBar progreso;
    private Button iniciar;
    private Button detener;
    private boolean seguir=true;

    private Context context;
    private NotificationManager barra;
    private Notification notificacion;
    private final int STATUS_BAR_NOTIFICATION = 1;
    private boolean mRun;

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

        progreso=(ProgressBar)findViewById(R.id.progressBar1);
        iniciar=(Button)findViewById(R.id.iniciar);
        detener=(Button)findViewById(R.id.detener);

        barra = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
        String Info = "Iniciando la carga";
        notificacion = new Notification(R.mipmap.ic_launcher, Info, 0);
        //0 es de tiempo de espera
        context = this.getApplicationContext();


        Intent intent = new Intent(context, MainActivity.class);
        PendingIntent pi = PendingIntent.getService(context, 0, intent, 0);
        //Primer 0 es request code y el segundo flags
        notificacion.flags |= Notification.FLAG_AUTO_CANCEL;

        String titulo = "Notificación curso android";
        RemoteViews layout_notificacion = new RemoteViews(getPackageName(), R.layout.notificacion);

        layout_notificacion.setImageViewResource(R.id.imagen, R.mipmap.ic_launcher);
        layout_notificacion.setTextViewText(R.id.titulo, titulo);
        layout_notificacion.setProgressBar(R.id.barra_progreso, 100, 0, false);
        //100 max, 0 progreso false indeterminado barra con bucle

        notificacion.contentView = layout_notificacion;
        notificacion.contentIntent = pi;
        barra.notify(STATUS_BAR_NOTIFICATION, notificacion);

        iniciar.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Toast.makeText(getApplicationContext(), "Iniciando tarea", Toast.LENGTH_SHORT).show();

                new Thread(new Runnable() {
                    public void run()
                    {
                        progreso.setMax(100);
                        progreso.setProgress(0);

                        for(int i=0;i<10;i++)
                        {
                            if(seguir)
                            {
                                accion_larga();
                                progreso.incrementProgressBy(10);

                                int progreso=(i+1)*10;
                                CharSequence texto = "Descargando: " + progreso + "%";
                                notificacion.contentView.setTextViewText(R.id.titulo, texto);
                                notificacion.contentView.setProgressBar(R.id.barra_progreso, 100, progreso, false);
                                barra.notify(STATUS_BAR_NOTIFICATION, notificacion);
                            }
                        }

                        runOnUiThread(new Runnable() {
                            public void run()
                            {
                                Toast.makeText(MainActivity.this, "Tarea finalizada!",
                                        Toast.LENGTH_SHORT).show();
                                seguir=true;
                            }
                        });
                    }
                }).start();
            }
        });


        detener.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Cancelado", Toast.LENGTH_SHORT).show();
                seguir = false;
            }
        });
    }

    private void accion_larga()
    {
        try {
            Thread.sleep(2000);
            Log.i("Estado", "Terminada");
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}
