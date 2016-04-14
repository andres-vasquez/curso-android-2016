package android.curso.broadcastreceiver1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;

/**
 * Created by andresvasquez on 11/4/16.
 */
public class LlamadasEntrantes extends BroadcastReceiver
{
    public void onReceive(Context context, Intent intent)
    {
        //Llamo a la clase estado llamadas
        EstadoLlamadas phoneListener=new EstadoLlamadas();

        //Me conecto con el manager de llamadas
        TelephonyManager telephony = (TelephonyManager)
                context.getSystemService(Context.TELEPHONY_SERVICE);
        //Escucha el estado del canal de llamadas
        telephony.listen(phoneListener, PhoneStateListener.LISTEN_CALL_STATE);
    }
}

class EstadoLlamadas extends PhoneStateListener
{
    //Cuando el estado cambia
    public void onCallStateChanged(int state,String incomingNumber){
        switch(state){
            case TelephonyManager.CALL_STATE_IDLE:
                Log.d("ESTADO", "IDLE");
                break;
            case TelephonyManager.CALL_STATE_OFFHOOK:
                Log.d("ESTADO", "OFFHOOK");
                break;
            case TelephonyManager.CALL_STATE_RINGING:
                Log.d("ESTADO", "RINGING de :"+incomingNumber);
                break;
        }
    }
}