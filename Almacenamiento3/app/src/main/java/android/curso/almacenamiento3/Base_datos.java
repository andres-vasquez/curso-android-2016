package android.curso.almacenamiento3;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by andresvasquez on 11/4/16.
 */
public class Base_datos extends SQLiteOpenHelper
{
    public static final String NOMBREBD = "curso_android.sqlite";
    //Versión de la base de datos
    public Base_datos(Context context, int VERSION)
    {
        super(context, NOMBREBD, null, VERSION);
    }

    //Método utilizado cuando se crea la base de datos.
    public void onCreate(SQLiteDatabase db)
    {
       db.execSQL("create table productos (id integer primary key autoincrement not null, producto varchar, precio double);");
        Log.d("Todos los tablas: ", "Se crearon las tablas");
    }

    //Método utilizado cuando se actualiza la base de datos
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {

    }

}