package android.curso.fragment2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    private FragmentManager fragmentManager;
    private Fragment f1;
    private Fragment f2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        f1=new Registro();
        f2=new Lista();

        fragmentManager = getSupportFragmentManager();

        fragmentManager.beginTransaction()
                .replace(R.id.fragment1, f1)
                .commit();

        fragmentManager.beginTransaction()
                .replace(R.id.fragment2, f2)
                .commit();
    }

    public void recargar_lista()
    {
        f2=new Lista();
        fragmentManager.beginTransaction()
                .replace(R.id.fragment2, f2)
                .commit();
    }
}
