package android.curso.fragment2;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class Lista extends Fragment
{

	private TableLayout tabla;
	private TableRow fila;
	TableRow.LayoutParams layoutFila;
	private LinearLayout cargando;
	
	private SQLiteDatabase db;
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) 
	{
		View view = inflater.inflate(R.layout.lista, container, false);
		
		Base_datos crearBD = new Base_datos(getActivity());
		db = crearBD.getWritableDatabase();
		
		tabla=(TableLayout)view.findViewById(R.id.tabla);
		cargando=(LinearLayout)view.findViewById(R.id.cargando);
		
		new Thread(new Runnable() 
		{
			public void run() {
				try {
					Thread.sleep(2000);
					getActivity().runOnUiThread(new Runnable()
					{
					    public void run()
					    {
					    	cargando.setVisibility(View.GONE);
					    }
					});

				} catch (InterruptedException e) 
				{
					e.printStackTrace();
				}
			}
		}).start();
					

		//Definimos el tama�o de cada celda
		layoutFila = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
				TableRow.LayoutParams.WRAP_CONTENT);

		//Ponemos los encabezados
		agregarFilas("Producto", "Precio","0");
		//Recorremos los productos existentes
		Cursor productos_existentes=db.rawQuery("SELECT id,producto,precio FROM productos", null);
		if(productos_existentes.moveToFirst())
		{
			do{
				//Vamos colocando cada fila de la tabla llamando a la funci�n
				agregarFilas(productos_existentes.getString(1),productos_existentes.getString(2),productos_existentes.getString(0));

				int id_editar=Integer.parseInt(productos_existentes.getString(0))+1000;
				int id_eliminar=Integer.parseInt(productos_existentes.getString(0))+5000;

				((ImageView)view.findViewById(id_editar)).setOnClickListener(new OnClickListener() {
					public void onClick(View v) 
					{
						int id_seleccionado=v.getId()-1000;
						Toast.makeText(getActivity(), "Editando..."+id_seleccionado, Toast.LENGTH_SHORT).show();
						String[] args=new String[]{""+id_seleccionado};
						db.execSQL("UPDATE productos SET precio=999 WHERE id=?",args);
						((MainActivity)getActivity()).recargar_lista();
					}
				});

				((ImageView)view.findViewById(id_eliminar)).setOnClickListener(new OnClickListener() 
				{
					public void onClick(View v) 
					{
						int id_seleccionado=v.getId()-5000;
						Toast.makeText(getActivity(), "Eliminando..."+id_seleccionado, Toast.LENGTH_SHORT).show();
						String[] args=new String[]{""+id_seleccionado};
						db.execSQL("DELETE FROM productos WHERE id=?",args);
						((MainActivity)getActivity()).recargar_lista();
					}
				});
			}while(productos_existentes.moveToNext());
		}
		return view;
	}


	private void agregarFilas(String prod,String precio1,String id)
	{
		//Definimos la variable fila
		fila=new TableRow(getActivity());
		//Le asignamos el tama�o de celda
		fila.setLayoutParams(layoutFila);

		//Agregamso dos campos, de texto donde se mostrar�n producto y precio.
		TextView nombre_producto=new TextView(getActivity());
		TextView precio_producto=new TextView(getActivity());

		//LLenamos el campo.
		nombre_producto.setText(prod);
		nombre_producto.setGravity(Gravity.CENTER_HORIZONTAL);
		//Le damos margenes
		nombre_producto.setBackgroundResource(R.drawable.celda_cuerpo);

		precio_producto.setText(precio1);
		precio_producto.setBackgroundResource(R.drawable.celda_cuerpo);
		precio_producto.setGravity(Gravity.CENTER_HORIZONTAL);

		nombre_producto.setLayoutParams(new TableRow.LayoutParams(0,TableRow.LayoutParams.MATCH_PARENT,2));
		precio_producto.setLayoutParams(new TableRow.LayoutParams(0,TableRow.LayoutParams.MATCH_PARENT,2));
		
		//Agregamos las vistas a la fila
		fila.addView(nombre_producto);
		fila.addView(precio_producto);

		//Encabezado lleva id=0. Si no es encabezado agregaremos los botones de edicion y eliminar
		if(id.compareTo("0")!=0)
		{
			
			precio_producto.setGravity(Gravity.RIGHT);
			
			//Creamos dos im�genes	
			ImageView editar=new ImageView(getActivity());
			ImageView eliminar=new ImageView(getActivity());

			//Les asignamos un id. A editar se le suma 1000 y a eliminar 5000. Para que no se repitan.
			int id_editar=Integer.parseInt(id)+1000;
			int id_eliminar=Integer.parseInt(id)+5000;

			//Se le asigna el id
			editar.setId(id_editar);
			//Se define el tama�o m�ximo de las im�genes.
			editar.setMaxHeight(40);
			editar.setMaxWidth(45);
			//Se ajustan al contenido a los l�mites
			editar.setAdjustViewBounds(true);
			//Declaramos la imagen de fondo.
			editar.setImageDrawable(getResources().getDrawable(R.drawable.refresh));
			//Le agregamos m�rgenes.
			editar.setBackgroundResource(R.drawable.celda_cuerpo);

			eliminar.setId(id_eliminar);
			eliminar.setMaxHeight(40);
			eliminar.setMaxWidth(45);
			eliminar.setAdjustViewBounds(true);
			eliminar.setImageDrawable(getResources().getDrawable(R.drawable.close));
			eliminar.setBackgroundResource(R.drawable.celda_cuerpo);

			//Agregamos los botones a la lista.
			editar.setLayoutParams(new TableRow.LayoutParams(0,TableRow.LayoutParams.MATCH_PARENT,1));
			eliminar.setLayoutParams(new TableRow.LayoutParams(0,TableRow.LayoutParams.MATCH_PARENT,1));
			fila.addView(editar);
			fila.addView(eliminar);
		}
		else
		{
			TextView vacio=new TextView(getActivity());
			TextView vacio1=new TextView(getActivity());
			vacio.setLayoutParams(new TableRow.LayoutParams(0,TableRow.LayoutParams.MATCH_PARENT,1));
			vacio1.setLayoutParams(new TableRow.LayoutParams(0,TableRow.LayoutParams.MATCH_PARENT,1));
			fila.addView(vacio);
			fila.addView(vacio1);
		}
		//Agregamos toda la fila a la tabla
		tabla.addView(fila);
	}

}
