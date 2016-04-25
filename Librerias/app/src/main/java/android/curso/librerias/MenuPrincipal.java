package android.curso.librerias;

import android.content.Context;
import android.content.Intent;
import android.curso.librerias.gson.ActivityGson;
import android.curso.librerias.iconis.ActivityIconics;
import android.curso.librerias.material.ActivityMaterial;
import android.curso.librerias.orm.ActivityOrm;
import android.curso.librerias.slider.ActivitySlider;
import android.curso.librerias.universal.ActivityUniversalImage;
import android.curso.librerias.zxing.ActivityZxing;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MenuPrincipal extends AppCompatActivity implements View.OnClickListener{

    private Button btnGson;
    private Button btnOrm;
    private Button btnUniversal;
    private Button btnIconics;
    private Button btnMaterial;
    private Button btnSlider;
    private Button btnZxing;

    private Context context;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Handle Toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().setHomeButtonEnabled(true);
        toolbar.setTitle("Android Libs");

        context=this;

         btnGson=(Button)findViewById(R.id.btnGson);
         btnOrm=(Button)findViewById(R.id.btnOrm);
         btnUniversal=(Button)findViewById(R.id.btnUniversal);
         btnIconics=(Button)findViewById(R.id.btnIconics);
         btnMaterial=(Button)findViewById(R.id.btnMaterial);
         btnSlider=(Button)findViewById(R.id.btnSlider);
         btnZxing=(Button)findViewById(R.id.btnZxing);

        btnGson.setOnClickListener(this);
        btnOrm.setOnClickListener(this);
        btnUniversal.setOnClickListener(this);
        btnIconics.setOnClickListener(this);
        btnMaterial.setOnClickListener(this);
        btnSlider.setOnClickListener(this);
        btnZxing.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Toast.makeText(context, "Aplicación creada por Andrés Vasquez", Toast.LENGTH_LONG).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.btnGson: IniciarActivity(ActivityGson.class);break;
            case R.id.btnOrm:IniciarActivity(ActivityOrm.class);break;
            case R.id.btnUniversal:IniciarActivity(ActivityUniversalImage.class);break;
            case R.id.btnIconics:IniciarActivity(ActivityIconics.class);break;
            case R.id.btnMaterial:IniciarActivity(ActivityMaterial.class);break;
            case R.id.btnSlider:IniciarActivity(ActivitySlider.class);break;
            case R.id.btnZxing:IniciarActivity(ActivityZxing.class);break;
        }
    }

    private void IniciarActivity(Class<?> clase)
    {
        Intent intent=new Intent(context,clase);
        startActivity(intent);
    }
}
