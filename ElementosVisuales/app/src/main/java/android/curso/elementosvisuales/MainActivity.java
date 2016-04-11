package android.curso.elementosvisuales;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    ToggleButton toggle;
    TextView txtToggle;

    CheckBox checkbox;
    TextView txtCheckbox;

    RadioGroup grupo;
    TextView txtGrupo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toggle=(ToggleButton)findViewById(R.id.toggle);
        txtToggle=(TextView)findViewById(R.id.txtToggle);

        toggle.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked==true)
                    txtToggle.setText("Control activado");
                else
                    txtToggle.setText("Control desactivado");
            }
        });

        toggle.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

            }
        });

        checkbox=(CheckBox)findViewById(R.id.CheckBox);
        txtCheckbox=(TextView)findViewById(R.id.txtCheckBox);

        checkbox.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                if(checkbox.isChecked()==true)
                    txtCheckbox.setText("Checkbox marcado");
                else
                    txtCheckbox.setText("Checkbox desmarcado");
            }
        });

        grupo=(RadioGroup)findViewById(R.id.grupo);
        txtGrupo=(TextView)findViewById(R.id.txtGrupo);

        //Mostrar que es el mismo Listener que Toggle pero con otro constructor
        grupo.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton seleccionado = (RadioButton) findViewById(checkedId);
                txtGrupo.setText(seleccionado.getText());
            }
        });
    }
}
