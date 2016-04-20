package android.curso.serviciosweb2;

import android.os.AsyncTask;
import android.util.Log;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;


public class SOAPClient extends AsyncTask<String, Void, String>{

	private static final String SOAP_ACTION = "ServerMensaje"; 
	private static final String METHOD_NAME = "ServerMensaje"; 
	public static String NAMESPACE="http://dreamyourapps.com/webservices/soap";
	private static final String URL = "http://dreamyourapps.com/webservices/soap/server.php?wsdl";
	
	public static String PARAM="value";
	public static String DATO="CURSO ANDROID";

	Receiver receiver;
	public SOAPClient(Receiver receiver){
		this.receiver = receiver;
	}
	
	@Override
	protected String doInBackground(String... params) {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		
		try {
			 
		    // Modelo el request
		    SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME); //namespace, Metodo
		    request.addProperty(PARAM, DATO);
		 
		    // Model el Sobre
		    SoapSerializationEnvelope sobre = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		    sobre.dotNet = true;
		    sobre.setOutputSoapObject(request);
		 
		    // Modelo el transporte
		    HttpTransportSE transporte = new HttpTransportSE(URL);//url
		    //transporte.debug= true;
		    
		    // Llamada
		    transporte.call(SOAP_ACTION, sobre);//Constants.SOAP_ACTION
		    // Resultado
		    String resultado = (String) sobre.getResponse();
			Log.e("SOAP", resultado);
		    sb.append(resultado);
		    //sb.append(resultado.getPrimitivePropertyAsString("value"));
		    
		} catch (Exception e) {
		    e.printStackTrace();
		}
		
		return sb.toString();
	}
	
	protected void onPostExecute(String result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		
		receiver.onLoad(result);
	}
	
	public interface Receiver{
		void onLoad(String input);
	}
}