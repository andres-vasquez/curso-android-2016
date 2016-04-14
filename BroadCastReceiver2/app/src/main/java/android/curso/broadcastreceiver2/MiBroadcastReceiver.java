package android.curso.broadcastreceiver2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by andresvasquez on 11/4/16.
 */
public class MiBroadcastReceiver extends BroadcastReceiver {

    private String mensaje_recibido;
    public void onReceive(Context context, Intent intent)
    {
        mensaje_recibido=intent.getStringExtra("mensaje");
        Toast.makeText(context, "Mensaje recibido: "+mensaje_recibido, Toast.LENGTH_LONG).show();
    }
}

