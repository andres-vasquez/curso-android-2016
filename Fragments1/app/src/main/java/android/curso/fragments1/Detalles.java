package android.curso.fragments1;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

public class Detalles extends Fragment
{
	//Recibe la posicion y la agrega al paquete bunble
	 public static Detalles newInstance(int index) 
	 {
	        Detalles f = new Detalles();
	        Bundle args = new Bundle();
	        args.putInt("index", index);
	        f.setArguments(args);
	        return f;
	    }
	 	
	 	//Devuelve la posicion actual del Fragment o por defecto 0
	    public int indice() 
	    {
	        return getArguments().getInt("index", 0);
	    }
	    
	    
	    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) 
	    {
	    	//Si no hay contenedor donde almacenar el Fragment se acaba
	        if (container == null) 
	        {
	            
	            return null;
	        }
	        
	        //Crea un ScrillView y un TV
	        ScrollView scroller = new ScrollView(getActivity());
	        TextView texto = new TextView(getActivity());
	        //Agrega padding 
	        int padding = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, getActivity().getResources().getDisplayMetrics());
	        texto.setPadding(padding, padding, padding, padding);
	        scroller.addView(texto);
	        
	        //Llena el Tv con informaci�n del so_array segun la posicion=indice() 
	        String values [] =  getResources().getStringArray(R.array.desc);
	        texto.setText(values[indice()]);
	        return scroller;
	    }
}
