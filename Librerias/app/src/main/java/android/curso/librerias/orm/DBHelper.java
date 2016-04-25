package android.curso.librerias.orm;

import android.content.Context;
import android.curso.librerias.Persona;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

/**
 * Created by andresvasquez on 11/7/15.
 */
public class DBHelper  extends OrmLiteSqliteOpenHelper {

    private static final String DATABASE_NOMBRE = "devfest_ormlite.db";
    private static final int DATABASE_VERSION = 1;

    private Dao<Persona, Integer> personaDao;

    public DBHelper(Context context) {
        super(context, DATABASE_NOMBRE, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
        try {
            //Creamos la tabla
            TableUtils.createTable(connectionSource, Persona.class);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        onCreate(db, connectionSource);
    }

    public Dao<Persona, Integer> getPersonaDao() throws SQLException {
        //Obtenemos el DAO de persona Mas info: https://es.wikipedia.org/wiki/Data_Access_Object
        if (personaDao == null) {
            personaDao = getDao(Persona.class);
        }
        return personaDao;
    }


    @Override
    public void close() {
        //Cerramos la base de datos
        super.close();
        personaDao = null;
    }

}
