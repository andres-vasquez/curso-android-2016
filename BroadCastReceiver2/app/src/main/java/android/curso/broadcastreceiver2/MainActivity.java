package android.curso.broadcastreceiver2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private Button enviar;
    private EditText mensaje;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        enviar=(Button)findViewById(R.id.enviar);
        mensaje=(EditText)findViewById(R.id.mensaje);

        enviar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction("com.cursoandroid.INTENT");
                intent.putExtra("mensaje", mensaje.getText().toString());
                sendBroadcast(intent);
            }
        });
    }

}
