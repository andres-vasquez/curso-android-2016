package android.curso.serviciosweb1;

import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Created by andresvasquez on 19/4/16.
 */
public class JSONParser
{
static InputStream is = null;
static JSONObject jObj = null;
static String json = "";

        // constructor
        public JSONParser() {

        }

        //Recibe la url, el tipo de método y los paramétros
        public JSONObject makeHttpRequest(String url, String method,
                                          List<NameValuePair> params)
        {

            //Hace una petición HTTP
            try {

                //Si el método es POST
                if(method == "POST")
                {
                    DefaultHttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost(url);
                    httpPost.setEntity(new UrlEncodedFormEntity(params));

                    //Inicializa la respuesta en null
                    HttpResponse httpResponse=null;
                    //Hace el POST
                    httpResponse = httpClient.execute(httpPost);
                    //Obtiene una respuesta
                    HttpEntity httpEntity = httpResponse.getEntity();
                    is = httpEntity.getContent();

                }
                else if(method == "GET")
                {
                    //Envía los parámetros via GET. url?clave=valor
                    DefaultHttpClient httpClient = new DefaultHttpClient();
                    String paramString = URLEncodedUtils.format(params, "utf-8");
                    url += "?" + paramString;
                    HttpGet httpGet = new HttpGet(url);
                    //Obtiene una respuesta
                    HttpResponse httpResponse=null;
                    httpResponse= httpClient.execute(httpGet);
                    HttpEntity httpEntity = httpResponse.getEntity();
                    is = httpEntity.getContent();
                }

            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e)
            {
                e.printStackTrace();
            }

            try
            {
                //Hace una lectira de la respuesta
                BufferedReader reader = new BufferedReader(new InputStreamReader(
                        is, "iso-8859-1"), 8);
                //Guarda todos los resultados en un string
                StringBuilder sb = new StringBuilder();
                String line = null;
                //Lee línea por linea el resultado
                while ((line = reader.readLine()) != null)
                {
                    sb.append(line + "\n");
                }
                is.close();
                json = sb.toString();
            } catch (Exception e) {
                Log.e("Buffer Error", "Error converting result " + e.toString());
                json=null;
            }

            //Convierte la string de resultado en un objeto Jsan
            try {
                jObj = new JSONObject(json);
            } catch (JSONException e) {
                Log.e("JSON Parser", "Error parsing data " + e.toString());
            }
            //Devuelve el objeto Json
            return jObj;

        }
}