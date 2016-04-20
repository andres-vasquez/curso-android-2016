package android.curso.serviciosweb2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final TextView tv = (TextView)findViewById(R.id.resultado);

        SOAPClient client = new SOAPClient(new SOAPClient.Receiver()
        {
            public void onLoad(String input)
            {
                tv.setText(input);
            }
        });
        client.execute();
    }
}
