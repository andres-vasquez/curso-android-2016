package android.curso.ejemplosms;

import android.content.Context;
import android.telephony.SmsManager;

/**
 * Created by andresvasquez on 5/11/15.
 */
public class EnviarMensaje
{
    private Context context;
    public EnviarMensaje(Context context) {
        this.context = context;
    }

    public long id_sms=0;

    public void sendSMS(final String phoneNumber, final String message)
    {
        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(phoneNumber, null, message,null,null);
    }
}
