package android.curso.extras1;

import android.content.Context;
import android.gesture.Gesture;
import android.gesture.GestureOverlayView;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Display;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity implements GestureOverlayView.OnGesturePerformedListener {

    GestureOverlayView gestureOverlayView;
    private Bitmap bitmap;
    private ImageView imageView;

    private FileOutputStream out;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        context=this;

        gestureOverlayView = (GestureOverlayView) findViewById(R.id.gestureOverlayView1);
        gestureOverlayView.setDrawingCacheEnabled(true);
        gestureOverlayView.setGestureColor(Color.BLUE);
        gestureOverlayView.addOnGesturePerformedListener(this);

        Display display= getWindowManager().getDefaultDisplay();
        bitmap=Bitmap.createBitmap(display.getWidth(), display.getHeight(), Bitmap.Config.ARGB_8888);
    }

    @Override
    public void onGesturePerformed(GestureOverlayView overlay, Gesture gesture)
    {
        Log.e("Dibujando", "Si");
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStrokeWidth(4);

        Bitmap gestureImg = overlay.getGesture().toBitmap(100, 100,
                8, Color.BLACK);

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        gestureImg.compress(Bitmap.CompressFormat.PNG, 100, bos);
        byte[] bArray = bos.toByteArray();

        try {
            File carpeta = new File(Environment.getExternalStorageDirectory(), "firmas");
            if(!carpeta.exists())
                carpeta.mkdirs();

            File file = new File(Environment.getExternalStorageDirectory(), "firmas"+File.separator+"firma.jpg");

            out = new FileOutputStream(file);
            out.write(bArray);
            out.flush();
            out.close();
            Toast.makeText(context,"Firma guardada",Toast.LENGTH_SHORT).show();
        }
        catch (IOException e)
        {
            e.printStackTrace();
            Log.e("Dibujando",""+e.getMessage());
        }
        catch (Exception e)
        {
            e.printStackTrace();
            Log.e("Dibujando", "" + e.getMessage());
        }
    }
}
