package android.curso.fragments1;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class DetallesActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Recibimos la orientacion
        if (getResources().getConfiguration().orientation== Configuration.ORIENTATION_LANDSCAPE)
        {
            Detalles detalles = new Detalles();
            detalles.setArguments(getIntent().getExtras());
            getSupportFragmentManager().beginTransaction().add(android.R.id.content, detalles).commit();
            return;
        }

        //En caso vacio mandamos detalles con posicion=null
        if (savedInstanceState == null)
        {
            Detalles detalles = new Detalles();
            detalles.setArguments(getIntent().getExtras());
            getSupportFragmentManager().beginTransaction().add(android.R.id.content, detalles).commit();
        }
    }

}
