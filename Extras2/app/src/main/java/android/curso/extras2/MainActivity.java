package android.curso.extras2;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    private ImageView imgFirma;

    private Context context;
    private SharedPreferences preferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        context=this;
        preferences= PreferenceManager.getDefaultSharedPreferences(context);

        imgFirma=(ImageView)findViewById(R.id.imgFirma);

        final File firma = new File(Environment.getExternalStorageDirectory(),"firmas/firma.jpg");
        if(firma.exists())
        {
            Toast.makeText(this,"La firma se utilizará desde Memoria",Toast.LENGTH_SHORT).show();
            InputStream stream = null;
            try {
                stream = new FileInputStream(firma);
                Bitmap bitmap = BitmapFactory.decodeStream(stream);
                imgFirma.setImageBitmap(bitmap);

                FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
                fab.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String strFirma=ObtenerFoto(firma);
                        SharedPreferences.Editor editor=preferences.edit();
                        editor.putString("foto",strFirma);
                        editor.commit();
                        Toast.makeText(context,"Imagen guardada en SharedPreferences",Toast.LENGTH_SHORT).show();
                    }
                });

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        else
        {
            Toast.makeText(this,"La firma se utilizará desde SharedPreferences",Toast.LENGTH_SHORT).show();
            if(preferences.contains("foto"))
            {
                String strImagen=preferences.getString("foto","");
                byte[] decodedString = Base64.decode(strImagen, Base64.DEFAULT);
                Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                imgFirma.setImageBitmap(decodedByte);
            }
            else
                Toast.makeText(this,"No se encontroó la firma en SharedPreferencess",Toast.LENGTH_SHORT).show();

        }
    }

    private String ObtenerFoto(File imagen)
    {
        String resultado="";
        try
        {
            InputStream stream = null;
            stream = new FileInputStream(imagen);
            byte[] imgImagen = readBytes(stream);
            String imgString = Base64.encodeToString(imgImagen, Base64.NO_WRAP);
            return imgString;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return resultado;
    }

    public byte[] readBytes(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteBuffer = new ByteArrayOutputStream();
        int bufferSize = 1024;
        byte[] buffer = new byte[bufferSize];

        int len = 0;
        while ((len = inputStream.read(buffer)) != -1) {
            byteBuffer.write(buffer, 0, len);
        }
        return byteBuffer.toByteArray();
    }
}
