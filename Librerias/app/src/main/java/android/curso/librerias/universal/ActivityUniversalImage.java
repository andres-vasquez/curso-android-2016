package android.curso.librerias.universal;

import android.content.Context;
import android.curso.librerias.R;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.display.SimpleBitmapDisplayer;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;


public class ActivityUniversalImage extends AppCompatActivity {

    private Context context;
    private ImageView imgImagen;
    private static String URL="http://savin-it.com/images/2016/01/21/avatar-wallpaper-hd-download-avatar-backgrounds-.jpg"; //Imagen FULL HD 1920 x 1080
    private boolean controlAcceso=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_universal_image);

        context=this;

        // Handle Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        imgImagen=(ImageView)findViewById(R.id.imgImagen);

        //Caracteristias de la configuracion
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this.context)
                .memoryCacheExtraOptions(480, 800) // dimensiones de pantalla
                .diskCacheExtraOptions(480, 800, null)
                .threadPoolSize(3) // default
                .threadPriority(Thread.NORM_PRIORITY - 2) // default
                .tasksProcessingOrder(QueueProcessingType.FIFO) // default
                .memoryCacheSizePercentage(13) // default
                .diskCacheSize(50 * 1024 * 1024) // Tamano de cache 50MB
                .diskCacheFileCount(100)
                .imageDownloader(new BaseImageDownloader(this.context)) // default
                .defaultDisplayImageOptions(DisplayImageOptions.createSimple()) // default
                .writeDebugLogs()
                .build();

        //Opciones al mostrar la imagen
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.img_no_disponible) // Imagen mientras carga
                .showImageForEmptyUri(R.drawable.img_no_disponible) // No se encontro la imagen
                .showImageOnFail(R.drawable.img_no_disponible) // Error al desplegar imagenes
                .resetViewBeforeLoading(false)
                .delayBeforeLoading(0)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .considerExifParams(false) // default
                .imageScaleType(ImageScaleType.IN_SAMPLE_POWER_OF_2) // 2x escala
                .bitmapConfig(Bitmap.Config.ARGB_8888) // Colores
                .displayer(new SimpleBitmapDisplayer()) // Como mostrar
                .handler(new Handler()) // Nuevo hilo
                .build();

        //Mostrar la imagen
        ImageLoader img=ImageLoader.getInstance();
        img.init(config);
        img.displayImage(URL, imgImagen, options, new ImageLoadingListener() {
            public void onLoadingStarted(String imageUri, View view) {
                if(controlAcceso)
                    view.setVisibility(View.INVISIBLE);
            }
            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                if(controlAcceso)
                    view.setVisibility(View.VISIBLE);
            }
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                if(controlAcceso)
                    view.setVisibility(View.VISIBLE);
            }
            public void onLoadingCancelled(String imageUri, View view) {
                if(controlAcceso)
                    view.setVisibility(View.VISIBLE);
            }
        });
    //Se utiliza la variable controlAcceso para evitar NullPointerException cuando salimos de la app mientras carga la imagen

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_activity_zxing, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPause()
    {
        controlAcceso=false;
        super.onPause();
    }

    @Override
    protected void onResume()
    {
        controlAcceso=true;
        super.onResume();
    }

}
