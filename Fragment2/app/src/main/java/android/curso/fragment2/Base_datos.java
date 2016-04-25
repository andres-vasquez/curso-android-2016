package android.curso.fragment2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class Base_datos extends SQLiteOpenHelper
{
   public static final String NOMBREBD = "curso_android.sqlite";
	 //Versi�n de la base de datos
   public static final int VERSION = 1;
   
	public Base_datos(Context context) 
	{
		 super(context, NOMBREBD, null, VERSION);
	}
	
	//M�todo utilizado cuando se crea la base de datos.
	public void onCreate(SQLiteDatabase db) 
	{
		db.execSQL("create table productos (id integer primary key autoincrement not null, producto varchar, precio double);");
		Log.d("Todos los tablas: ", "Se crearon las tablas");
	}
	
	//M�todo utilizado cuando se actualiza la base de datos
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) 
	{
		
	}

}
