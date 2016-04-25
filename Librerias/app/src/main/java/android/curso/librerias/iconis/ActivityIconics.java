package android.curso.librerias.iconis;

import android.curso.librerias.R;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.iconics.typeface.FontAwesome;


public class ActivityIconics extends AppCompatActivity {

    private ImageView imgUno;
    private ImageView imgDos;
    private ImageView imgTres;
    private ImageView imgCuatro;
    private ImageView imgCinco;
    private ImageView imgSeis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_iconics);

        // Handle Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        //Soporta
        //FontAwesome
        //Google Material Design Icons

        //Meteocons   compile 'com.mikepenz.iconics:meteocons-typeface:+@aar'
        //Octicons    compile 'com.mikepenz.iconics:octicons-typeface:+@aar'
        //Community MaterialIcons    compile 'com.mikepenz.iconics:community-material-typeface:+@aar'

         imgUno=(ImageView)findViewById(R.id.imgUno);
         imgDos=(ImageView)findViewById(R.id.imgDos);
         imgTres=(ImageView)findViewById(R.id.imgTres);
         imgCuatro=(ImageView)findViewById(R.id.imgCuatro);
         imgCinco=(ImageView)findViewById(R.id.imgCinco);
         imgSeis=(ImageView)findViewById(R.id.imgSeis);

        //Definimos las caracteristicas de la imagen
        Drawable drawable=new IconicsDrawable(this)
                .icon(FontAwesome.Icon.faw_android)
                .color(getResources().getColor(R.color.colorAccent))
                .sizeDp(24);
         imgUno.setImageDrawable(drawable);

        imgDos.setImageDrawable(new IconicsDrawable(this)
                .icon(FontAwesome.Icon.faw_dashboard)
                .backgroundColor(Color.BLUE)
                .color(Color.WHITE)
                .sizeDp(24));

        imgTres.setImageDrawable(new IconicsDrawable(this)
                .icon(FontAwesome.Icon.faw_spinner)
                .color(getResources().getColor(R.color.colorPrimary))
                .sizeDp(24));

        imgCuatro.setImageDrawable(new IconicsDrawable(this)
                .icon(GoogleMaterial.Icon.gmd_map)
                .color(Color.RED)
                .sizeDp(24));

        imgCinco.setImageDrawable(new IconicsDrawable(this)
                .icon(GoogleMaterial.Icon.gmd_bluetooth)
                .backgroundColor(Color.BLUE)
                .color(Color.WHITE)
                .sizeDp(24));

        imgSeis.setImageDrawable(new IconicsDrawable(this)
                .icon(GoogleMaterial.Icon.gmd_thumb_up)
                .color(getResources().getColor(R.color.colorPrimary))
                .sizeDp(24));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_activity_zxing, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
