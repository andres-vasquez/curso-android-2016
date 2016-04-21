package android.curso.serviciosweb4;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by andresvasquez on 13/4/16.
 */
public class WsPeticiones {

    // Atributos
    private static WsPeticiones singleton;
    private RequestQueue requestQueue;
    private static Context context;

    private WsPeticiones(Context context) {
        WsPeticiones.context = context;
        requestQueue = getRequestQueue();
    }

    public static synchronized WsPeticiones getInstance(Context context) {
        if (singleton == null) {
            singleton = new WsPeticiones(context);
        }
        return singleton;
    }

    public RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        }
        return requestQueue;
    }

    public  void addToRequestQueue(Request req) {
        getRequestQueue().add(req);
    }

}
