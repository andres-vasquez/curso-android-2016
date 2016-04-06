package android.curso.ciclovida;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.e("Estado: ", "Create");
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        Log.e("Estado: ","Pause");
    }

    @Override
    protected void onStop()
    {
        super.onStop();
        Log.e("Estado: ","Stop");
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        Log.e("Estado: ","Resume");
    }

    @Override
    protected void onRestart()
    {
        super.onRestart();
        Log.e("Estado: ","Restart");
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        Log.e("Estado: ","Destroy");
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
