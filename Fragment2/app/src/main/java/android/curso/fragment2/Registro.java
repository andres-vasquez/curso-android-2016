package android.curso.fragment2;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Registro extends Fragment{
	
	private EditText producto;
	private EditText precio;
	private Button guardar;
	
	private SQLiteDatabase db;
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) 
	{
		View view = inflater.inflate(R.layout.formulario, container, false);
		
		Base_datos crearBD = new Base_datos(getActivity());
		db = crearBD.getWritableDatabase();
		
		//Declaramos a las variables
		producto=(EditText)view.findViewById(R.id.producto);
		precio=(EditText)view.findViewById(R.id.precio);
		guardar=(Button)view.findViewById(R.id.enviar);
		
		guardar.setOnClickListener(new OnClickListener() {
			public void onClick(View v) 
			{
				//Insertamos en la base de datos
				ContentValues values = new ContentValues();
				values.put("producto",producto.getText().toString());
				values.put("precio",precio.getText().toString());
				db.insert("productos",null, values);
				Toast.makeText(getActivity(),"Registro Agregado", Toast.LENGTH_SHORT).show();
				((MainActivity)getActivity()).recargar_lista();
			}
		});
		return view;
	}
	
}
