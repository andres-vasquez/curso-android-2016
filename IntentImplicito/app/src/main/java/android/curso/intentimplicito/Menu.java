package android.curso.intentimplicito;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;

public class Menu extends AppCompatActivity {

    private TextView titulo;
    private Button llamar;
    private Button marcar;
    private Button geo;
    private Button web;
    private Button mail;
    private Button foto;
    private Button atras;
    private String mensaje_recibido;
    private ImageView mostrar_foto;
    private static int TAKE_PICTURE = 1;
    private String ruta_foto;

    private String numero_tel="60507900";
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Recibimos el mensaje del anterior activitu.
        Intent datos=getIntent();
        mensaje_recibido=datos.getStringExtra("mensaje");


        //Declaramos los componentes
        titulo=(TextView)findViewById(R.id.titulo_menu);
        llamar=(Button)findViewById(R.id.llamar);
        marcar=(Button)findViewById(R.id.marcar);
        geo=(Button)findViewById(R.id.geo);
        web=(Button)findViewById(R.id.web);
        mail=(Button)findViewById(R.id.mail);
        foto=(Button)findViewById(R.id.foto);
        atras=(Button)findViewById(R.id.salir);
        mostrar_foto=(ImageView)findViewById(R.id.foto_tomada);

        titulo.setText("Hola "+mensaje_recibido);

        llamar.setText("Llamar a: "+numero_tel);
        llamar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                llamar(numero_tel);
            }
        });

        marcar.setText("Marcar a: "+numero_tel);
        marcar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent a = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+numero_tel));
                startActivity(a);
            }
        });

        geo.setText("ir al INE");
        geo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent a= new Intent(Intent.ACTION_VIEW, Uri.parse("geo:-16.496178,-68.130066?z=19&q=-16.496178,-68.130066"));
                startActivity(a);
            }
        });

        web.setText("ir a la página del INE");
        web.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent a = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.ine.gob.bo"));
                startActivity(a);
            }
        });

        mail.setText("Enviar mail a Andres");
        mail.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("text/plain");
                i.putExtra(Intent.EXTRA_EMAIL, new String[]{"andres.vasquez.a@hotmail.com"});
                i.putExtra(Intent.EXTRA_SUBJECT,"Mensaje de prueba");
                i.putExtra(Intent.EXTRA_TEXT,"Enviado por android");
                startActivity(Intent.createChooser(i, "Seleccione la aplicacion de mail."));
            }
        });

        foto.setText("Tomar foto");
        foto.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ruta_foto= Environment.getExternalStorageDirectory() + "/"+mensaje_recibido+".jpg";
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                Uri output = Uri.fromFile(new File(ruta_foto));
                intent.putExtra(MediaStore.EXTRA_OUTPUT, output);
                startActivityForResult(intent, TAKE_PICTURE);
            }
        });

        atras.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });
    }

    @TargetApi(Build.VERSION_CODES.M)
    public void llamar(String numero)
    {
        if (context.checkSelfPermission(Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
            Intent a = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + numero_tel));
            startActivity(a);
        }
    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        File file = new File(ruta_foto);
        if (file.exists())
        {
            //Bitmap bit_ =Bitmap.createScaledBitmap(BitmapFactory.decodeFile(ruta_foto), 400, 300,false);
            mostrar_foto.setImageBitmap(BitmapFactory.decodeFile(ruta_foto));
        }
    }
}

