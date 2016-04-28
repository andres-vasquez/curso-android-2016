package android.curso.almacenamiento3;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText producto;
    private EditText precio;
    private Button guardar;
    private Button btnPrecio;
    private TableLayout tabla;
    private TableRow fila;
    TableRow.LayoutParams layoutFila;

    private SQLiteDatabase db;
    public static final int VERSION = 1;

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        context=this;

        Base_datos crearBD = new Base_datos(context,VERSION);
        db = crearBD.getWritableDatabase();

        producto=(EditText)findViewById(R.id.producto);
        precio=(EditText)findViewById(R.id.precio);
        guardar=(Button)findViewById(R.id.enviar);
        btnPrecio=(Button)findViewById(R.id.btnPrecio);
        tabla=(TableLayout)findViewById(R.id.tabla);

        layoutFila = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT);

        guardar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                ContentValues values = new ContentValues();
                values.put("producto",producto.getText().toString());
                values.put("precio",precio.getText().toString());
                db.insert("productos", null, values);
                db.close();

                Toast.makeText(getApplicationContext(), "Registro Agregado", Toast.LENGTH_SHORT).show();
                reiniciarActividad();
            }
        });

        agregarFilas("Producto", "Precio","0");
        Cursor productos_existentes=db.rawQuery("SELECT id,producto,precio FROM productos", null);
        if(productos_existentes.moveToFirst())
        {
            do{
                agregarFilas(productos_existentes.getString(1),productos_existentes.getString(2),productos_existentes.getString(0));
            }while(productos_existentes.moveToNext());
        }

        btnPrecio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent escaner=new Intent(context,Escanner2.class);
                startActivity(escaner);
            }
        });

    }

    private void reiniciarActividad() {
        Intent a=new Intent(getApplicationContext(),MainActivity.class);
        finish();
        startActivity(a);
    }

    private void agregarFilas(String prod,String precio1,String id)
    {
        fila=new TableRow(this);
        fila.setLayoutParams(layoutFila);

        TextView nombre_producto=new TextView(this);
        TextView precio_producto=new TextView(this);

        nombre_producto.setText(prod);
        nombre_producto.setBackgroundResource(R.drawable.celda_cuerpo);

        precio_producto.setText(precio1);
        precio_producto.setBackgroundResource(R.drawable.celda_cuerpo);
        precio_producto.setGravity(Gravity.RIGHT);

        nombre_producto.setLayoutParams(new TableRow.LayoutParams(0,
                TableRow.LayoutParams.MATCH_PARENT, 5));
        precio_producto.setLayoutParams(new TableRow.LayoutParams(0,
                TableRow.LayoutParams.MATCH_PARENT, 5));

        fila.addView(nombre_producto);
        fila.addView(precio_producto);


        if(id.compareTo("0")!=0)
        {
            ImageView editar=new ImageView(this);
            ImageView eliminar=new ImageView(this);

            editar.setId(Integer.parseInt(id));
            editar.setAdjustViewBounds(true);
            editar.setBackgroundResource(R.drawable.celda_cuerpo);
            editar.setImageResource(R.drawable.refresh);
            editar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context,"Actualizando "+view.getId(),Toast.LENGTH_SHORT).show();
                    ContentValues values = new ContentValues();
                    values.put("precio","6.00");
                    String[] args=new String[]{""+view.getId()};
                    db.update("Productos",values, "id LIKE ?", args);
                    db.update("Producots",values,"id LIKE "+view.getId(),null);
                    reiniciarActividad();


                }
            });

            eliminar.setId(Integer.parseInt(id));
            eliminar.setAdjustViewBounds(true);
            eliminar.setBackgroundResource(R.drawable.celda_cuerpo);
            eliminar.setImageResource(R.drawable.close);
            eliminar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context,"Eliminando "+view.getId(),Toast.LENGTH_SHORT).show();
                    String[] args=new String[]{""+view.getId()};
                    db.delete("Productos", "id LIKE ?", args);
                    reiniciarActividad();
                }
            });


            editar.setLayoutParams(new TableRow.LayoutParams(0,
                    TableRow.LayoutParams.WRAP_CONTENT, 1));
            eliminar.setLayoutParams(new TableRow.LayoutParams(0,
                    TableRow.LayoutParams.WRAP_CONTENT, 1));

            fila.addView(editar);
            fila.addView(eliminar);
        }
        else
        {
            TextView vacio = new TextView(this);
            vacio.setText("Acción");
            vacio.setBackgroundResource(R.drawable.celda_cuerpo);
            vacio.setLayoutParams(new TableRow.LayoutParams(0,
                    TableRow.LayoutParams.WRAP_CONTENT, 2));
            fila.addView(vacio
            );
        }

        tabla.addView(fila);
    }
}
