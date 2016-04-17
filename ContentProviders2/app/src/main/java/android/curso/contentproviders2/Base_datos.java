package android.curso.contentproviders2;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by andresvasquez on 17/4/16.
 */
public class Base_datos extends SQLiteOpenHelper
{
    public static final String NOMBREBD = "prueba.sqlite";
    //Versión de la base de datos
    public static final int VERSION = 1;

    public Base_datos(Context context)
    {
        super(context, NOMBREBD, null, VERSION);
    }

    //Método utilizado cuando se crea la base de datos.
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("create table clientes (_id integer primary key autoincrement not null, nombre varchar, email varchar, deuda varchar);");
        Log.d("Todos los tablas: ", "Se crearon las tablas");

        ContentValues andres=new ContentValues();
        andres.put("nombre", "Andrés Vasquez");
        andres.put("deuda", "50");
        andres.put("email", "andres.vasquez.agramont@gmail.com");
        db.insert("clientes",null ,andres);
    }

    //Método utilizado cuando se actualiza la base de datos
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {

    }
}

