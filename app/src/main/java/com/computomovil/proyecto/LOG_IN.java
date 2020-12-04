package com.computomovil.proyecto;

import android.app.DatePickerDialog;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.computomovil.proyecto.classes.Alumno;
import com.computomovil.proyecto.classes.DatePickerFragment;

public class LOG_IN extends AppCompatActivity implements View.OnClickListener {

    EditText tfNombre,tfApe,tfNacimiento,tfCuenta;
    Spinner spCarreras;
    Button btnValidar;
    Alumno alumno;

    //Proceso onCreate
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Declaración de variables de la interfaz
        tfNombre=findViewById(R.id.tfNombre);
        tfApe=findViewById(R.id.tfApe);
        tfNacimiento=findViewById(R.id.tfNacimiento);
        tfNacimiento.setOnClickListener(this);
        tfCuenta=findViewById(R.id.tfCuenta);
        spCarreras=findViewById(R.id.spCarreras);
    }

    //Acción del botón
    public void clkValidar(View view) {
        //Inicializamos las variables
        String name=getResources().getString(R.string.vacio),date=getResources().getString(R.string.vacio),cuenta=getResources().getString(R.string.vacio),carrera=getResources().getString(R.string.vacio);
        int id=getResources().getInteger(R.integer.cero);

        //Si ninguno de los editText están vacíos
        if (missingField(tfNombre) && missingField(tfApe) && missingField(tfNacimiento) && missingField(tfCuenta) && tfCuenta.getText().toString().length()>getResources().getInteger(R.integer.minNum)) {
            //Comparamos si hay algún elemento seleccionado del spinner
            if (spCarreras.getSelectedItemId() != getResources().getInteger(R.integer.cero)){
                name = tfNombre.getText().toString() + " " + tfApe.getText().toString(); //Ponemos el nombre en una sola variable
                date = tfNacimiento.getText().toString(); //Ponemos la fecha de nacimiento en una variable
                cuenta = tfCuenta.getText().toString(); //Ponemos el numero de cuenta en una variable
                carrera= spCarreras.getSelectedItem().toString(); //Ponermos la carrera seleccionada en una variable
                id=(int)spCarreras.getSelectedItemId(); //Mandamos el índic para facilitar la selección de imagenes

                //Inicializamos la variable Alumno con su constructor
                alumno=new Alumno(name,date,cuenta,carrera,id);

                Intent intent=new Intent(this,dataUsers.class); //Nos pasamos a la activity 2
                Bundle bundle=new Bundle(); //Almacenamos el objeto Alumno
                bundle.putSerializable(getResources().getString(R.string.clave),alumno); //Identificamos al objeto alumno
                intent.putExtras(bundle); //Lo pasamos a la activity 2

                startActivity(intent); //Iniciamos la actividad 2
                //Toast.makeText(this,name+" "+date+" "+cuenta+" "+idCarrera+" "+carrera,Toast.LENGTH_LONG).show();

            //Mandamos error si el spinner no tiene seleccionada una opción correcta
            }else {
                Toast.makeText(this, getResources().getString(R.string.errorCarrera), Toast.LENGTH_LONG).show();
            }
        //Mostramos el error en cada campo que haga falta
        }else{
            if(!missingField(tfNombre)) tfNombre.setError(getResources().getString(R.string.errorName));
            if(!missingField(tfApe)) tfApe.setError(getResources().getString(R.string.errorApe));
            if(!missingField(tfNacimiento)) tfNacimiento.setError(getResources().getString(R.string.errorNacimiento));
            if(!missingField(tfCuenta)) tfCuenta.setError(getResources().getString(R.string.errorCuenta));
            if(tfCuenta.getText().toString().length()<=8 && missingField(tfCuenta)) Toast.makeText(this,getResources().getString(R.string.errorCuentaSize),Toast.LENGTH_LONG).show();
        }

    }
    //Verifica si los EditText están vacios.
    private boolean missingField(EditText et){
        if(et.getText().toString().isEmpty()) {
            return false;
        }
        return true;
    }
    //Cuando se pulse el boton, mandamos a llamar al DatePicker
    @Override
    public void onClick(View v) {
            switch (v.getId()){
                case R.id.tfNacimiento:
                    showDatePickerDialog();
                    break;
        }
    }

    //Implementamos el DatePicker
    private void showDatePickerDialog(){
       // String date;
        DatePickerFragment frag= DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                //Verificamos una edad minima de 15 años.
                if((year>=getResources().getInteger(R.integer.anio))){
                    if(missingField(tfNacimiento)) tfNacimiento.setError(null);
                    else{
                        tfNacimiento.setError(getResources().getString(R.string.vacio));
                        Toast toast=Toast.makeText(LOG_IN.this,getResources().getString(R.string.errorAge), Toast.LENGTH_LONG);
                        toast.setGravity(Gravity.CENTER,getResources().getInteger(R.integer.cero),getResources().getInteger(R.integer.cero));
                        toast.show();
                    }

                }else{
                    String date =dayOfMonth+getResources().getString(R.string.diagonal)+(month+1)+getResources().getString(R.string.diagonal)+year;
                    tfNacimiento.setText(date);
                }
            }
        });
        frag.show(getSupportFragmentManager(),getResources().getString(R.string.picker));
        //return date;
    }
}