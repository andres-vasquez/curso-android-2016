package android.curso.serviciosweb3;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class DOMxml 
{
	private URL Url;

	public DOMxml(String url)
	{
		try 
		{
			this.Url = new URL(url);
		} 
		catch (MalformedURLException e) 
		{
			throw new RuntimeException(e);
		}
	}

	public List<Empleado> parse() 
	{
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		List<Empleado> lista_empleados = new ArrayList<Empleado>();

		try 
		{
			DocumentBuilder builder = factory.newDocumentBuilder();
            Document dom = builder.parse(this.getInputStream());
            Element root = dom.getDocumentElement();
            NodeList items = root.getElementsByTagName("empleado");

			for (int i=0; i<items.getLength(); i++)
			{
				Empleado empleado = new Empleado();

				Node item = items.item(i);
				NodeList datosEmpleados = item.getChildNodes();

				for (int j=0; j<datosEmpleados.getLength(); j++)
				{
					Node dato = datosEmpleados.item(j);
					String etiqueta = dato.getNodeName();

					if (etiqueta.equals("nombres"))
					{
						String texto = obtenerTexto(dato);
						
						empleado.setNombres(texto);
					}
					else if (etiqueta.equals("departamento"))
					{
						String texto = obtenerTexto(dato);
						
						empleado.setDepartamento(texto);
					} 
					else if (etiqueta.equals("sueldo"))
					{
						String texto = obtenerTexto(dato);
						
						empleado.setSueldo(texto);
					} 
					
				}

				lista_empleados.add(empleado);
			}
		} 
		catch (Exception ex) 
		{
			throw new RuntimeException(ex);
		} 

		return lista_empleados;
	}

	private String obtenerTexto(Node dato)
	{
		StringBuilder texto = new StringBuilder();
		NodeList fragmentos = dato.getChildNodes();

		for (int k=0;k<fragmentos.getLength();k++)
		{
			texto.append(fragmentos.item(k).getNodeValue());
		}

		return texto.toString();
	}

	private InputStream getInputStream() 
	{
		try 
		{
			return Url.openConnection().getInputStream();
		} 
		catch (IOException e) 
		{
			throw new RuntimeException(e);
		}
	}
}