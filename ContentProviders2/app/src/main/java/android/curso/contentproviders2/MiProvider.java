package android.curso.contentproviders2;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

/**
 * Created by andresvasquez on 17/4/16.
 */
public class MiProvider  extends ContentProvider
{
    private static final String uri = "content://com.example.content_proviers_ejemplo2/clientes";
    public static final Uri CONTENT_URI = Uri.parse(uri);

    private Base_datos db;
    String NOMBREBD  = "prueba";
    int BD_VERSION = 1;
    String TABLA_CLIENTES = "Clientes";


    private static int CLIENTES = 1;
    private static int CLIENTES_ID = 2;
    private static UriMatcher uriMatcher;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI("com.example.content_proviers_ejemplo2", "clientes", CLIENTES);//Toda la tabla
        uriMatcher.addURI("com.example.content_proviers_ejemplo2", "clientes/#", CLIENTES_ID); //Un id
    }


    public String getType(Uri uri) {

        int match = uriMatcher.match(uri);
        switch (match)
        {
            case 1: //Registro unico
                return "vnd.android.cursor.dir/vnd.curso_android.cliente";
            case 2: // Lista de registros
                return "vnd.android.cursor.item/vnd.curso_android.cliente";
            default:
                return null;
        }
    }


    public boolean onCreate() {
        db = new Base_datos(getContext());
        return true;
    }


    public int delete(Uri uri, String selection, String[] selectionArgs)
    {

        int cont;
        String where = selection;
        if(uriMatcher.match(uri) == CLIENTES_ID)
        {
            where = "_id=" + uri.getLastPathSegment();
        }

        SQLiteDatabase db = this.db.getWritableDatabase();

        cont = db.delete(TABLA_CLIENTES, where, selectionArgs);

        return cont;
    }

    public Uri insert(Uri uri, ContentValues values) {

        long regId = 1;
        SQLiteDatabase db = this.db.getWritableDatabase();

        regId = db.insert(TABLA_CLIENTES, null, values);
        Uri newUri = ContentUris.withAppendedId(CONTENT_URI, regId);
        return newUri;
    }

    public Cursor query(Uri uri, String[] projection,
                        String selection, String[] selectionArgs, String sortOrder) {

        //Si es una consulta a un ID concreto construimos el WHERE
        String where = selection;
        if(uriMatcher.match(uri) == CLIENTES_ID){
            where = "_id=" + uri.getLastPathSegment();
        }

        SQLiteDatabase db = this.db.getWritableDatabase();

        Cursor c = db.query(TABLA_CLIENTES, projection, where,
                selectionArgs, null, null, sortOrder);

        return c;
    }


    public int update(Uri uri, ContentValues values,
                      String selection, String[] selectionArgs)
    {
        int cont;

        //Si es una consulta a un ID concreto construimos el WHERE
        String where = selection;
        if(uriMatcher.match(uri) == CLIENTES_ID){
            where = "_id=" + uri.getLastPathSegment();
        }

        SQLiteDatabase db = this.db.getWritableDatabase();
        cont = db.update(TABLA_CLIENTES, values, where, selectionArgs);

        return cont;
    }

}

