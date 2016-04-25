package android.curso.librerias.orm;

import android.content.Context;
import android.curso.librerias.Persona;
import android.curso.librerias.R;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.iconics.typeface.FontAwesome;

import java.util.List;

/**
 * Created by andresvasquez on 11/7/15.
 */
public class ListaAdapter extends BaseAdapter {

    private Context context;
    private List<Persona> items;
    Callback callback;

    //Constructor del adapter, definimos callback para acciones de Editar y Eliminar
    public ListaAdapter(Context context, List<Persona> items,Callback callback) {
        this.context = context;
        this.items = items;
        this.callback=callback;
    }


    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Persona getItem(int i) {
        return items.get(i);
    }

    @Override
    public long getItemId(int i) {
        return items.get(i).getId();
    }

    static class ViewHolder{
        //Campos de mi item_layout
        TextView txtEdad;
        TextView txtNombre;
        ImageView imgEditar;
        ImageView imgEliminar;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView==null)
        {
            //Inicializamos cada fila
            holder=new ViewHolder();
            LayoutInflater infalInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.layout_orm, null);

            holder.txtEdad = (TextView)convertView.findViewById(R.id.txtEdad);
            holder.txtNombre = (TextView)convertView.findViewById(R.id.txtNombre);
            holder.imgEditar = (ImageView)convertView.findViewById(R.id.imgEditar);
            holder.imgEliminar = (ImageView)convertView.findViewById(R.id.imgEliminar);

            convertView.setTag(holder);
        }
        else {
            holder=(ViewHolder)convertView.getTag();
        }

        //Llenamos los datos de cada item_layout con el objeto persona correspondiente
        final Persona item=items.get(position);
        holder.txtNombre.setText(item.getNombre()+" "+item.getApellido());
        holder.txtEdad.setText(String.valueOf(item.getEdad()));

        holder.imgEditar.setImageDrawable(new IconicsDrawable(context)
                .icon(FontAwesome.Icon.faw_pencil)
                .color(context.getResources().getColor(R.color.colorPrimary))
                .sizeDp(24));

        holder.imgEliminar.setImageDrawable(new IconicsDrawable(context)
                .icon(FontAwesome.Icon.faw_trash)
                .color(context.getResources().getColor(R.color.colorAccent))
                .sizeDp(24));

        //Definimos los callback para los botones Editar y Eliminar
        holder.imgEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback.editar(item);
            }
        });
        holder.imgEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback.eliminar(item);
            }
        });

        return convertView;
    }

    //Creamos la interface callback y los metodos de implementacion
    public interface Callback{
        public void eliminar(Persona persona);
        public void editar(Persona persona);
    }
}
