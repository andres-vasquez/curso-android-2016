package android.curso.almacenamiento1;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    //Variables en pantalla
    private EditText texto;
    private TextView resultados;
    private Button interna;
    private Button sd;


    //Variables del menu
    private static final int opcion1= 1;
    private static final int opcion2 = 2;
    private static final int opcion3 = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Declaramos las variables
        texto=(EditText)findViewById(R.id.texto);
        resultados=(TextView)findViewById(R.id.resultados);
        interna=(Button)findViewById(R.id.Interno);
        sd=(Button)findViewById(R.id.memoria_sd);

        interna.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                //obtenemos el texto
                String mostrar=texto.getText().toString();
                try
                {
                    //Definimos la variable fout, como un archivo que sólo se puede acceder desde la app
                    OutputStreamWriter fout=
                            new OutputStreamWriter(
                                    openFileOutput("prueba_curso_android.txt", Context.MODE_PRIVATE));
                    //**** Mostrar como APPEND

                    //Guardamos lo que hemos incluido en el editText
                    fout.write(mostrar);
                    fout.close();
                    //Las notificaciones serán explicadas más adelante
                    Toast.makeText(getApplicationContext(), "Texto guardado",Toast.LENGTH_SHORT).show();
                }
                catch (Exception ex)
                {
                    //Si no logró guardar en memoria, mostrar un mensaje de error en el Log.
                    Log.e("Ficheros", "Error al escribir fichero a memoria interna");
                }
            }
        });

        sd.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v) {

                String estado = Environment.getExternalStorageState();
                boolean lista=false;

                if (estado.equals(Environment.MEDIA_MOUNTED))
                {
                    lista=true;
                    Toast.makeText(getApplicationContext(), "Memoria SD lista!", Toast.LENGTH_SHORT).show();
                }
                else if (estado.equals(Environment.MEDIA_MOUNTED_READ_ONLY))
                {
                    Toast.makeText(getApplicationContext(), "Memoria SD sin permisos de escritura", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Memoria SD no disponible", Toast.LENGTH_SHORT).show();
                }

                //Comprueba si la memora SD está lista con permisos de escritura.
                if(lista)
                {
                    //Guardamos en mostrar lo que escribimos
                    String mostrar=texto.getText().toString();

                    try
                    {
                        //Busca la ruta de la SD
                        File ruta_sd = Environment.getExternalStorageDirectory();
                        //Obtiene la dirección raiz de la SD
                        File f = new File(ruta_sd.getAbsolutePath(), "prueba_curso_sd.txt");

                        //Define que el archivo que será modificado se encuentra en la variable f
                        OutputStreamWriter fout =
                                new OutputStreamWriter(
                                        new FileOutputStream(f));

                        //Guardamos lo que hemos escrito en el archivo.
                        fout.write(mostrar);
                        fout.close();
                        Toast.makeText(getApplicationContext(), "Texto guardado", Toast.LENGTH_SHORT).show();
                    }
                    catch (Exception ex)
                    {
                        //Si no logró guardar en memoria SD, mostrar un mensaje de error en el Log.
                        Log.e("Ficheros", "Error al escribir fichero a tarjeta SD");
                    }
                }

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(Menu.NONE, opcion1, Menu.NONE, "Interna")
                .setIcon(android.R.drawable.ic_menu_add);
        menu.add(Menu.NONE, opcion2, Menu.NONE, "SD")
                .setIcon(android.R.drawable.stat_notify_sdcard_prepare);
        menu.add(Menu.NONE, opcion3, Menu.NONE, "Recursos")
                .setIcon(android.R.drawable.ic_dialog_info);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case opcion1:
                try
                {
                    //Abriremos el contenido guardado en la memoria Interna
                    BufferedReader archivo =
                            new BufferedReader(
                                    new InputStreamReader(
                                            openFileInput("prueba_curso_android.txt")));

                    //Guardamos el contenido del archivo en texto.
                    String texto = archivo.readLine();
                    archivo.close();

                    //Mostramos el contenido en el TextView de la parte inferior llamados resultados
                    resultados.setText(texto);
                }
                catch (Exception ex)
                {
                    Log.e("Ficheros", "Error al leer desde memoria interna");
                }
                return true;
            case opcion2:
                try
                {
                    //Obtiene la ruta de la tarjeta SD
                    File ruta_sd = Environment.getExternalStorageDirectory();
                    //En la variable f llamamos al archivo creado
                    File f = new File(ruta_sd.getAbsolutePath(), "prueba_curso_sd.txt");

                    BufferedReader fin =
                            new BufferedReader(
                                    new InputStreamReader(
                                            new FileInputStream(f)));
                    //Guardamos el contenido en la variable Texto
                    String texto = fin.readLine();
                    fin.close();

                    //Mostramos el contenido en el TextView de la parte inferior llamados resultados
                    resultados.setText(texto);
                }
                catch (Exception ex)
                {
                    Log.e("Ficheros", "Error al leer desde tarjeta SD");
                }
                return true;

            case opcion3:
                try
                {
                    //Obtenemos la ruta desde Res/raw
                    InputStream ruta =
                            getResources().openRawResource(R.raw.prueba_csv);

                    //Lo almacenamos en el buffer de lectura
                    BufferedReader archivo =
                            new BufferedReader(new InputStreamReader(ruta));

                    //Vaciamos resultados
                    resultados.setText("");

                    //Abrimos el archivo
                    String texto="";
                    while((texto=archivo.readLine())!=null)
                    {
                        //Hacemos desaparecer las comas.
                        String texto_sin_comas=texto.replace(",","  ");
                        //Mostramos los resultados
                        resultados.append(texto_sin_comas+"\n");
                    }
                    archivo.close();
                }
                catch (Exception ex)
                {
                    Log.e("Ficheros", "Error al leer fichero desde recurso raw");
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
