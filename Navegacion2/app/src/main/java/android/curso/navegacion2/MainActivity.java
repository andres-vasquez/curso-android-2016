package android.curso.navegacion2;

import android.content.Context;
import android.content.res.Configuration;
import android.curso.navegacion2.fragments.Pagina1;
import android.curso.navegacion2.fragments.Pagina2;
import android.curso.navegacion2.fragments.Pagina3;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;

import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.iconics.typeface.FontAwesome;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.accountswitcher.AccountHeader;
import com.mikepenz.materialdrawer.accountswitcher.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

public class MainActivity extends AppCompatActivity {
    public static final int DRAWER_ITEM_PAGINA1 = 1;
    public static final int DRAWER_ITEM_PAGINA2 = 2;
    public static final int DRAWER_ITEM_PAGINA3 = 3;
    private Drawer drawer;

    public int DRAWER_SELECCIONADO=0;

    private static final String LOG = "MainActivity";
    private Context context;
    private Toolbar toolbar;

    private String nombre="";
    private String email="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context=this;

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        context=this;

        nombre="Alumno";
        email="alumno@ine.gob.bo";

        AccountHeader headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .addProfiles(
                        new ProfileDrawerItem()
                                .withName(nombre)
                                .withEmail(email)
                                .withIcon(new IconicsDrawable(this)
                                        .icon(FontAwesome.Icon.faw_user)
                                        .color(getResources().getColor(R.color.blanco))
                                        .backgroundColor(getResources().getColor(R.color.colorAccent))
                                        .paddingDp(2)
                                        .sizeDp(20))
                )
                .withHeaderBackground(R.color.colorAccent)
                .build();

        drawer = new DrawerBuilder(this)
                .withToolbar(toolbar)
                .withAccountHeader(headerResult)
                .withActionBarDrawerToggle(true)
                .addDrawerItems(
                        new DividerDrawerItem(),
                        new PrimaryDrawerItem().
                                withIdentifier(DRAWER_ITEM_PAGINA1).
                                withName("Pagina1").
                                withSelectedIconColor(getResources().getColor(R.color.colorAccent)).
                                withSelectedTextColor(getResources().getColor(R.color.colorAccent)).
                                withIcon(FontAwesome.Icon.faw_home),
                        new PrimaryDrawerItem().
                                withIdentifier(DRAWER_ITEM_PAGINA2).
                                withName("Pagina2").
                                withSelectedIconColor(getResources().getColor(R.color.colorAccent)).
                                withSelectedTextColor(getResources().getColor(R.color.colorAccent)).
                                withIcon(FontAwesome.Icon.faw_line_chart),
                        new PrimaryDrawerItem().
                                withIdentifier(DRAWER_ITEM_PAGINA3).
                                withName("Pagina3").
                                withSelectedIconColor(getResources().getColor(R.color.colorAccent)).
                                withSelectedTextColor(getResources().getColor(R.color.colorAccent)).
                                withIcon(FontAwesome.Icon.faw_money)
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(AdapterView<?> adapterView, View view, int i, long l, IDrawerItem drawerItem) {
                        selectItem(drawerItem.getIdentifier());
                        return false;
                    }
                })
                .withSelectedItem(DRAWER_ITEM_PAGINA1)
                .withSavedInstance(savedInstanceState)
                .build();

        selectItem(DRAWER_ITEM_PAGINA1);
        toolbar.setTitle("Página 1");
    }

    public void selectItem(int idMenu)
    {
        String titulo = "";
        Fragment f=new Pagina1();

        Bundle args=new Bundle();

        switch (idMenu)
        {
            case DRAWER_ITEM_PAGINA1:
                titulo="Página 1";
                f=new Pagina1();
                break;
            case DRAWER_ITEM_PAGINA2:
                titulo="Página 2";
                f=new Pagina2();
                break;
            case DRAWER_ITEM_PAGINA3:
                titulo="Página 3";
                f=new Pagina3();
                break;
        }
        toolbar.setTitle(titulo);
        args.putString("param1", titulo);
        f.setArguments(args);

        FragmentManager fm = getSupportFragmentManager();
        Fragment oldFragment = getSupportFragmentManager().findFragmentById(R.id.contenedor);
        if (oldFragment != null)
        {
                fm.beginTransaction()
                        .remove(oldFragment)
                        .addToBackStack("tag")
                        .replace(R.id.contenedor, f)
                        .commit();
        }
        else
            fm.beginTransaction()
                    .addToBackStack("tag")
                    .replace(R.id.contenedor, f)
                    .commit();

        DRAWER_SELECCIONADO=idMenu;
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if (drawer != null) {
            outState = drawer.saveInstanceState(outState);
            super.onSaveInstanceState(outState);
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        if(DRAWER_SELECCIONADO== DRAWER_ITEM_PAGINA2)
        {
            selectItem(DRAWER_ITEM_PAGINA2);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_menu_principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home) {
            if (drawer.isDrawerOpen())
                drawer.closeDrawer();
            else
                drawer.openDrawer();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        DRAWER_SELECCIONADO=0;
        //handle the back press :D close the drawer first and if the drawer is closed close the activity
        if (drawer != null && drawer.isDrawerOpen()) {
            drawer.closeDrawer();
        } else
        {
            super.onBackPressed();
            //moveTaskToBack(true);
        }
    }
}
