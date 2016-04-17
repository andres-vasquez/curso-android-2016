package android.curso.contentproviders2;

import android.provider.BaseColumns;

/**
 * Created by andresvasquez on 17/4/16.
 */
public class Clientes implements BaseColumns
{
    private Clientes() {}

    public static final String COLUMNA_NOMBRE = "nombre";
    public static final String COLUMNA_EMAIL = "email";
    public static final String COLUMNA_DEUDA = "deuda";
}

