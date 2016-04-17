package android.curso.servicios;

/**
 * Created by andresvasquez on 17/4/16.
 */

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ServicioLista extends Service
{
    private final IBinder mBinder = new MyBinder();
    private ArrayList<String> lista = new ArrayList<String>();

    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("Palabra generada","Si");
        int min = 1;
        int max = 5;

        Random random = new Random();
        int num = random.nextInt(max - min + 1) + min;

        switch(num)
        {
            case 1:lista.add("Linux");break;
            case 2:lista.add("Android");break;
            case 3:lista.add("iPhone");break;
            case 4:lista.add("Windows Phone");break;
        }
        return Service.START_NOT_STICKY;
    }

    public IBinder onBind(Intent arg0) {
        return mBinder;
    }

    public class MyBinder extends Binder {
        ServicioLista getService() {
            return ServicioLista.this;
        }
    }

    public List<String> getLista() {
        return lista;
    }

    public void onDestroy()
    {
        super.onDestroy();
        Log.e("Servicio ","Destruido");
    }
}
