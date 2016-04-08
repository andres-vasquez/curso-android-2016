package android.curso.loginprogrammatically;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    LinearLayout lyVertical;
        TextView lblUsuario;
        EditText txtUsuario;
        TextView lblPassword;
        EditText txtPassword;
        LinearLayout lyHorizontal;
            Button btnEnviar;
            Button btnLimpiar;
        TextView txtResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        //Creamos las variables
        lyVertical =new LinearLayout(this);
        lblUsuario =new TextView(this);
        txtUsuario =new EditText(this);
        lblPassword =new TextView(this);
        txtPassword =new EditText(this);
        lyHorizontal =new LinearLayout(this);
        btnEnviar =new Button(this);
        btnLimpiar =new Button(this);
        txtResultado =new TextView(this);

        //Primeras carateristicas
        lyVertical.setOrientation(LinearLayout.VERTICAL);
        lblUsuario.setText("Usuario");
        lblPassword.setText("Password");
        txtPassword.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
        lyHorizontal.setOrientation(LinearLayout.HORIZONTAL);
        btnEnviar.setText("Enviar");
        btnLimpiar.setText("Limpiar");

        //Construmimos las vistas del nivel inferior al nivel superior
        //Agregamos los botones
        lyHorizontal.addView(btnEnviar);
        lyHorizontal.addView(btnLimpiar);

        //Agregamos los componentes segun el orden en el que apareceran en pantalla
        lyVertical.addView(lblUsuario);
        lyVertical.addView(txtUsuario);
        lyVertical.addView(lblPassword);
        lyVertical.addView(txtPassword);
        lyVertical.addView(lyHorizontal);
        lyVertical.addView(txtResultado);

        setContentView(lyVertical);

        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String campo_usuario = txtUsuario.getText().toString();
                String campo_password = txtPassword.getText().toString();

                if (campo_usuario.compareTo("hola") == 0 && campo_password.compareTo("mundo") == 0)
                    txtResultado.setText("Login aceptado");
                else
                    txtResultado.setText("Login fallido");
            }
        });

        btnLimpiar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                txtUsuario.setText("");
                txtPassword.setText("");
                txtResultado.setText("");
            }
        });
    }
}