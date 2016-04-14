package android.curso.broadcastreceiver3;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Vibrator;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by andresvasquez on 14/4/16.
 */
public class Alarma extends BroadcastReceiver
{
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Se ha ejecutado la alarma!!!!.",
                Toast.LENGTH_LONG).show();
        Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        //4000 milisegundos (4 segundos)
        vibrator.vibrate(4000);

        try {
            Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            Ringtone r = RingtoneManager.getRingtone(context, notification);
            r.play();
        } catch (Exception e)
        {
            Log.e("Tono", "No pudo sonar");
        }
    }
}

