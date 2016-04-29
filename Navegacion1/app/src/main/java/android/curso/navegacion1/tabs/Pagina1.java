package android.curso.navegacion1.tabs;

import android.content.Context;
import android.curso.navegacion1.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Pagina1 extends Fragment
{
	private Context context;
	public View onCreateView(
			LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

			context=getActivity();

		return inflater.inflate(R.layout.pagina1, container, false);
	}
}
