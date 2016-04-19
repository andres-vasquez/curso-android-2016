package android.curso.hilosejecucion1;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button ejecutar;

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

        ejecutar=(Button)findViewById(R.id.Ejecutar);

        ejecutar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Segunda accion lista", Toast.LENGTH_SHORT).show();
            }
        });
        //accion_larga();
        new Accion_larga().execute("Url de un pdf","Hola que ase","Soy Andres");
    }

   /*private void accion_larga()
	{
		try {
			Thread.sleep(5000);
			Log.i("Estado","Terminada");
		} catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}
    */

	/*private void accion_larga()
	{
		Thread hilo=new Thread()
		{
			public void run()
			{
				try {
					Thread.sleep(5000);
					Log.i("Estado","Terminada");
				} catch (InterruptedException e)
				{
					e.printStackTrace();
				}
			}
		};
		hilo.start();
	}*/

    /*
	private void accion_larga()
	{
		Runnable proceso=new Runnable()
		{
			public void run()
			{
				try {
					Thread.sleep(5000);
					Log.i("Estado","Terminada");
				} catch (InterruptedException e)
				{
					e.printStackTrace();
				}
			}
		};
		new Thread(proceso).start();
	}*/

    private ProgressDialog Dialog;
    class Accion_larga extends AsyncTask<String, String, String>
    {
        protected void onPreExecute()
        {
            super.onPreExecute();
            Dialog = new ProgressDialog(MainActivity.this);
            Dialog.setMessage("Realizando acción larga. Espere por favor...");
            //Dialog.setIndeterminate(false);
            Dialog.setMax(100);
            Dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            Dialog.show();
            Dialog.setProgress(30);
        }
        protected String doInBackground(String... args)
        {
            try {
                Thread.sleep(9000);
                Log.i("Estado", "Terminada "+args[0]);

            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute(String file_url)
        {
            Toast.makeText(getApplicationContext(), "Tarea terminada", Toast.LENGTH_SHORT).show();
            Dialog.dismiss();
        }
    }
}
