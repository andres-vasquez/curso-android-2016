package android.curso.fragments1;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Items extends ListFragment
{
	//Extendido a ListFragment osea un ListView de tipo Fragment
	boolean DoblePanel;
	int Posicion = 0;

	public void onActivityCreated(Bundle savedInstanceState) 
	{
		super.onActivityCreated(savedInstanceState);
		
		//Revisamos que versi�n tiene, si es menor a 3.0 muestra un layout en adapter sin forma, si no coloreado 
		if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) 
		{
			setListAdapter(new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.so_array)));
		}
		else 
		{
			//Coloreado = activated_1
			setListAdapter(new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_activated_1, getResources().getStringArray(R.array.so_array)));
		}

		//Definimos la vista interior del fragment
		View detailsFrame = getActivity().findViewById(R.id.detalles);
		
		//si hay fragment != null y el fragment es visible doble panel=true
		
		DoblePanel = detailsFrame != null && detailsFrame.getVisibility() == View.VISIBLE;

		//Es el metodo de guardar variables cuando se gira la pantalla, con tal de no reconstruir toda la activity
		if (savedInstanceState != null) 
		{
			//Recuperamos la posicion escogida
			Posicion = savedInstanceState.getInt("Posicion_escogida", 0);
		}
		
		//Si hay doble panel
		if (DoblePanel)  
		{
			//Elegimos SINGLE CHOICE.
			getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
			//Mostramos la informaci�n segun la posicion
			MostrarInfo(Posicion);
		}
	}
	
	//Metodo es para guardar los datos antes de girar el telefono
	public void onSaveInstanceState(Bundle outState) 
	{
		super.onSaveInstanceState(outState);
		//Agregar la variable llamada posicion_escogida con el valor de posicion
		outState.putInt("Posicion_escogida", Posicion);
	}
	
	//Por defecto, implementa onListItemClick
	public void onListItemClick(ListView l, View v, int position, long id) 
	{
		//Mostramos la info
		MostrarInfo(position);
	}

	void MostrarInfo(int index) 
	{
		//Refresca la variable global posicion
		Posicion = index;

		if (DoblePanel) 
		{
			//Colorea la posicion
			getListView().setItemChecked(index, true);

			//Busca la src de Detalles (Fragment)
			Detalles detalles = (Detalles)
					getActivity().getSupportFragmentManager().findFragmentById(R.id.detalles);
			
			//Si hay Fragment y la posicion escogida es != a la anterior escogida
			if (detalles == null || detalles.indice() != index) 
			{
				//Crea de nuevo! el Fragment mandando la posicion a la funcion newInstance
				detalles = Detalles.newInstance(index);
				
				//Ejecuta la transaccion
				FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
				//Reemplaza el anterior
				ft.replace(R.id.detalles, detalles);
				ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
				ft.commit();
			}

		} 
		else 
		{
			//Manda la posicion a otra Activity via Intent
			Intent intent = new Intent();
			intent.setClass(getActivity(), DetallesActivity.class);
			intent.putExtra("index", index);
			startActivity(intent);
		}
	}
}
