package android.curso.hilosejecucion2;

import android.os.AsyncTask;

/**
 * Created by andresvasquez on 18/4/16.
 */
public class ParaAlejandro extends AsyncTask<Integer,Integer,Boolean>{
    @Override
    protected Boolean doInBackground(Integer... integers) {
        return true;
    }

    protected void onPostExecute(Boolean resultado)
    {
        super.onPostExecute(resultado);
    }

    protected void onProgressUpdate(Integer integer)
    {
        super.onProgressUpdate(integer);
    }
}
