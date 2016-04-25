package android.curso.librerias.material;

import android.content.Context;
import android.curso.librerias.R;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.mikepenz.iconics.typeface.FontAwesome;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.accountswitcher.AccountHeader;
import com.mikepenz.materialdrawer.accountswitcher.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;


public class ActivityMaterial extends AppCompatActivity {

    //IDs de los items de navegacion
    private static final int DRAWER_ITEM_UNO = 1;
    private static final int DRAWER_ITEM_DOS = 2;
    private static final int DRAWER_ITEM_TRES = 3;
    private static final int DRAWER_ITEM_CUATRO = 4;

    //Definimos el entorno
    private Drawer drawer;
    private Context context;
    private FrameLayout contenedor;

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_material);
        context=this;

        //Antes de comenzar
        //Adicionar el tema  android:theme="@style/MaterialDrawerTheme.Light.DarkToolbar el manifest

        // Handle Toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        //Contenedor es el espacio central
        contenedor=(FrameLayout)findViewById(R.id.contenedor);

        //Definimos el header
        AccountHeader headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.drawable.header)
                .addProfiles(
                        new ProfileDrawerItem().
                                withName("Alumno Android").
                                withEmail("android@ine.gob.bo")
                )
                .build();

        //Definimos el Navigacion Drawer
        drawer = new DrawerBuilder(this)
                .withToolbar(toolbar)
                .withAccountHeader(headerResult)
                .addDrawerItems(
                        new DividerDrawerItem(),
                        //Deinimos los items de navegacion
                        new PrimaryDrawerItem().
                                withIdentifier(DRAWER_ITEM_UNO).
                                withName(R.string.item_uno).
                                withTextColor(getResources().getColor(R.color.primary)).
                                withIconColor(getResources().getColor(R.color.primary)).
                                withSelectedTextColor(getResources().getColor(R.color.colorAccent)).
                                withSelectedIconColor(getResources().getColor(R.color.colorAccent)).
                                withIcon(FontAwesome.Icon.faw_home),
                        new PrimaryDrawerItem().
                                withIdentifier(DRAWER_ITEM_DOS).
                                withName(R.string.item_dos).
                                withTextColor(getResources().getColor(R.color.primary)).
                                withIconColor(getResources().getColor(R.color.primary)).
                                withSelectedTextColor(getResources().getColor(R.color.colorAccent)).
                                withSelectedIconColor(getResources().getColor(R.color.colorAccent)).
                                withIcon(FontAwesome.Icon.faw_newspaper_o),
                        new PrimaryDrawerItem().
                                withIdentifier(DRAWER_ITEM_TRES).
                                withName(R.string.item_tres).
                                withTextColor(getResources().getColor(R.color.primary)).
                                withIconColor(getResources().getColor(R.color.primary)).
                                withSelectedTextColor(getResources().getColor(R.color.colorAccent)).
                                withSelectedIconColor(getResources().getColor(R.color.colorAccent)).
                                withIcon(FontAwesome.Icon.faw_calendar)
                ).addStickyDrawerItems(
                        //Este item se encuentra en la parte inferior
                        new SecondaryDrawerItem()
                                .withName(R.string.item_cuatro)
                                .withIdentifier(DRAWER_ITEM_CUATRO)
                                .withIcon(FontAwesome.Icon.faw_info)
                                .withTextColor(getResources().getColor(R.color.primary))
                                .withIconColor(getResources().getColor(R.color.primary))
                                .withSelectedTextColor(getResources().getColor(R.color.colorAccent))
                                .withSelectedIconColor(getResources().getColor(R.color.colorAccent))
                                .withCheckable(false)
                )
                //Accion Click sobre los items de menu
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener()
                {
                    @Override
                    public boolean onItemClick(AdapterView<?> adapterView, View view, int i, long l, IDrawerItem drawerItem) {
                        seleccionartItem(drawerItem.getIdentifier());
                        return false;
                    }
                })
                .withSelectedItem(0)
                .withSavedInstance(savedInstanceState)
                .build();

        //Al inicializar se selecciona el primer item
        seleccionartItem(DRAWER_ITEM_UNO);
    }

    //Funcion de seleccion de item
    private void seleccionartItem(int i)
    {
        Toast.makeText(context, "Selecciono el item N " + i, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //Guardamos la info del drawer
        if (drawer != null) {
            outState = drawer.saveInstanceState(outState);
        }
        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_menu_principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //Boton hamburguesa en el ActionBar para Abrir y Cerrar el Navigation Drawer
        if(item.getItemId()==android.R.id.home) {
            if (drawer.isDrawerOpen())
                drawer.closeDrawer();
            else
                drawer.openDrawer();
        }
        return super.onOptionsItemSelected(item);
    }
}
