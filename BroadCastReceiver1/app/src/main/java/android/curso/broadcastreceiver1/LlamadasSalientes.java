package android.curso.broadcastreceiver1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by andresvasquez on 11/4/16.
 */
public class LlamadasSalientes extends BroadcastReceiver
{
    public void onReceive(Context context, Intent intent) {


        if (intent.getAction().equals(Intent.ACTION_NEW_OUTGOING_CALL))
        {
            Log.i("Estado", "Detectando llamada");

            String numero = intent.getExtras().getString(Intent.EXTRA_PHONE_NUMBER);

            Log.i("Estado","Llamando a: "+numero);

        }
    }
}
