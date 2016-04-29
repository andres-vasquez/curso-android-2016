package android.curso.ejemplosms;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private Context context;
    private EditText txtNumero;
    private EditText txtMensaje;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        context=this;
        txtNumero=(EditText)findViewById(R.id.txtNumero);
        txtMensaje=(EditText)findViewById(R.id.txtMensaje);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EnviarMensaje enviarMensaje=new EnviarMensaje(context);
                enviarMensaje.sendSMS(txtNumero.getText().toString(),txtMensaje.getText().toString());
            }
        });
    }
}
