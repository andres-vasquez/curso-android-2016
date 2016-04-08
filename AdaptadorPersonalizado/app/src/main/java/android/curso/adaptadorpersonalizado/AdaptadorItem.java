package android.curso.adaptadorpersonalizado;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class AdaptadorItem extends BaseAdapter
{
	protected Activity activity;
	protected ArrayList<Item> items;
	
	//Se crear el constructor
	public AdaptadorItem(Activity activity, ArrayList<Item> items)
	{
		this.activity=activity;
		this.items=items;
	}
	
	//Funcion que define el tamano
	public int getCount() {
		return items.size();
	}

	//Funcion que devuelve cada item
	public Object getItem(int posicion) {
		return items.get(posicion);
	}

	//Funcion que devuelve el id de cada item
	public long getItemId(int posicion) {
		return items.get(posicion).getId();
	}
	
	//El viewHolder guardara los elementos visuales
	//para que no sean declarados por cada fila
	static class ViewHolder{
		TextView titulo;
		TextView descripcion;
		ImageView imagen;
	}
	
	//la funcion getView crea la vista personalizada en cada posicion
	//de la lista
	public View getView(int posicion, View vista, ViewGroup vista_grupal) {
		ViewHolder holder;
		if(vista==null)
		{
			holder=new ViewHolder();
			LayoutInflater inflater=(LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			vista=inflater.inflate(R.layout.vista_item,null);
			
			holder.titulo=(TextView)vista.findViewById(R.id.titulo);
			holder.descripcion=(TextView)vista.findViewById(R.id.descripcion);
			holder.imagen=(ImageView)vista.findViewById(R.id.imagen);
			
			vista.setTag(holder);
		}
		else
		{
			holder=(ViewHolder)vista.getTag();
		}
		
		Item item=items.get(posicion);
		holder.titulo.setText(item.getTitulo());
		holder.descripcion.setText(item.getDescripcion());
		holder.imagen.setImageResource(item.getImagen());
		
		return vista;
	}
}
