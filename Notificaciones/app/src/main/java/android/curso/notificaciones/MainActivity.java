package android.curso.notificaciones;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private Button toast;
    private Button toastLayout;
    private Button Dialog;
    private Button DialogAnimado;
    private Button Notificacion;
    private Button Popup;
    private Button snack;
    private Button fecha;
    private CoordinatorLayout coordinador;

    static final int DATE_DIALOG_ID = 0;
    private int año;
    private int mes;
    private int dia;

    protected Dialog onCreateDialog(int id)
    {
        switch (id) {
            case DATE_DIALOG_ID:
                return new DatePickerDialog(this, FechaListener, año, mes, dia);
        }
        return null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        coordinador=(CoordinatorLayout)findViewById(R.id.coordinador);
        toast=(Button)findViewById(R.id.toast);
        toastLayout=(Button)findViewById(R.id.toastLayout);
        Dialog=(Button)findViewById(R.id.dialog);
        DialogAnimado=(Button)findViewById(R.id.dialogAnimado);
        Notificacion=(Button)findViewById(R.id.notificacion);

        Popup=(Button)findViewById(R.id.popup);
        snack=(Button)findViewById(R.id.snack);
        fecha=(Button)findViewById(R.id.fecha);

        toast.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {

                Toast mensaje=Toast.makeText(getApplicationContext(), "Notificación Toast", Toast.LENGTH_LONG);
                mensaje.show();
            }
        });

        toastLayout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                //Layout inflater será el método para agregar un mayout
                LayoutInflater inflater = getLayoutInflater();
                //Utilizaremos el layout llamado layout_toast que contiene el id específico layout_vista
                View layouttoast = inflater.inflate(R.layout.layout_toast, (ViewGroup)findViewById(R.id.layout_vista));
                //Llenamos el TextView con id=texto con el mensaje
                ((TextView) layouttoast.findViewById(R.id.texto)).setText("El mensaje del Toast viene aqui");

                //Declaramos el context
                Toast mensaje = new Toast(getBaseContext());

                //Declaramos la ubicación en pantalla
                mensaje.setGravity(Gravity.CENTER_VERTICAL, 0, 0);

                //Declaramos el contenido
                mensaje.setView(layouttoast);
                mensaje.setDuration(Toast.LENGTH_LONG);
                mensaje.show();

            }
        });

        Dialog.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {

                AlertDialog.Builder Dialogo = new AlertDialog.Builder(
                        MainActivity.this);

                Dialogo.setTitle("Alerta con Dialogo");
                Dialogo.setMessage("La está pasando bien en el curso de android?");
                Dialogo.setIcon(R.drawable.tick);

                Dialogo.setPositiveButton("Si",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(getApplicationContext(), "Felicidades tienes 100", Toast.LENGTH_SHORT).show();
                            }
                        });
                Dialogo.setNeutralButton("Maso",
                        new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog,
                                                int which) {
                                Toast.makeText(getApplicationContext(),
                                        "Haz conocer tus sugerencias", Toast.LENGTH_SHORT)
                                        .show();
                            }
                        });
                Dialogo.setNegativeButton("No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(getApplicationContext(), "Presta más atención", Toast.LENGTH_SHORT).show();
                                dialog.cancel();
                            }
                        });
                Dialogo.show();

            }
        });

        DialogAnimado.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                AlertDialog dialogo;
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                builder.setTitle("Un Simple Dialogo");
                builder.setMessage("Con transición de entrada y salida");
                dialogo = builder.create();
                dialogo.getWindow().getAttributes().windowAnimations = R.style.transicion;
                dialogo.show();
                dialogo.setOnCancelListener(new OnCancelListener() {
                    public void onCancel(DialogInterface dialog) {
                        dialog.dismiss();
                    }
                });
            }
        });


        Popup.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                final Dialog dialogo = new Dialog(MainActivity.this);

                dialogo.setContentView(R.layout.login);

                final EditText usuario = (EditText) dialogo.findViewById(R.id.user);
                final EditText password = (EditText) dialogo.findViewById(R.id.pass);

                dialogo.setTitle("Popup Login");

                dialogo.setCancelable(false);
                dialogo.show();
                Button procesar= (Button) dialogo.findViewById(R.id.button1);


                procesar.setOnClickListener(new OnClickListener() {
                    public void onClick(View v) {
                        dialogo.dismiss();
                        Toast toast1 = Toast.makeText(getApplicationContext(),
                                "Usuario:" + usuario.getText(),
                                Toast.LENGTH_SHORT);
                        toast1.show();

                        Toast toast2 = Toast.makeText(getApplicationContext(),
                                "Pass:" + password.getText(),
                                Toast.LENGTH_SHORT);
                        toast2.show();
                    }
                });

                Button cancelar= (Button) dialogo.findViewById(R.id.cancel);
                cancelar.setOnClickListener(new OnClickListener() {
                    public void onClick(View v) {
                        dialogo.dismiss();
                        Toast toast1 = Toast.makeText(getApplicationContext(),
                                "No hiciste Login",
                                Toast.LENGTH_SHORT);
                        toast1.show();
                    }
                });

            }
        });


        snack.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar snackbar = Snackbar
                        .make(coordinador, "Funciona!", Snackbar.LENGTH_LONG)
                        .setAction("No funciona", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Toast toast1 = Toast.makeText(getApplicationContext(),
                                        "Claro que funciona!",
                                        Toast.LENGTH_SHORT);
                                toast1.show();
                            }
                        });

                snackbar.setActionTextColor(Color.RED);
                snackbar.show();
            }
        });

        Notificacion.setOnClickListener(new OnClickListener() {
            public void onClick(View v)
            {

                Intent intent = new Intent(getApplicationContext(), Informacion.class);

                PendingIntent pIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent, 0);

                Notification notificacion = new NotificationCompat.Builder(MainActivity.this)
                        .setContentTitle("Nueva notificacion")
                        .setContentText("Ir a la Activity")
                        .setSmallIcon(R.drawable.icon)
                        .setContentIntent(pIntent)
                        .setDefaults(Notification.DEFAULT_SOUND)
                        .build();

                NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                notificationManager.notify(0, notificacion);
            }
        });

        fecha.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                final Calendar c = Calendar.getInstance();
                año = c.get(Calendar.YEAR);
                mes = c.get(Calendar.MONTH);
                dia = c.get(Calendar.DAY_OF_MONTH);

                showDialog(DATE_DIALOG_ID);
            }
        });
    }

    private DatePickerDialog.OnDateSetListener FechaListener =
            new DatePickerDialog.OnDateSetListener()
            {
                public void onDateSet(DatePicker view, int yearOf, int monthOfYear, int dayOfMonth)
                {
                    Toast.makeText(MainActivity.this, "Escogiste: " + dayOfMonth + "-" + monthOfYear + "-" + yearOf, Toast.LENGTH_SHORT).show();
                }
            };

}
