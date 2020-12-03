package com.computomovil.proyecto;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.computomovil.proyecto.classes.Alumno;

import java.util.Calendar;

public class dataUsers extends AppCompatActivity {

    //Declaramos los objetos que utilizaremos.
    Alumno alumno;
    Bundle bundle=new Bundle();
    TextView tfNombre,tfFechaNac,tfCuenta,tfCarrera;
    ImageView Carrera;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_users);

        //Inicializamos las variables y relacionamos con la interfaz
        tfNombre=findViewById(R.id.tfNombre2);
        tfFechaNac=findViewById(R.id.tfFechaNac);
        tfCuenta=findViewById(R.id.tfCuenta2);
        tfCarrera=findViewById(R.id.tfCarrera);
        Carrera=findViewById(R.id.Carrera);


        //Recogemos el bundle de la activity 1
        bundle=getIntent().getExtras();
        if(bundle!=null){
            //Creamos el objeto alumno con sus atributos traídos del bundle
            alumno=(Alumno)bundle.getSerializable(getResources().getString(R.string.clave));
            //Asignamos a la interfaz los valores del objeto
            tfNombre.setText(alumno.getName());
            //Permitimos que el nombre sea scrolleable
            tfNombre.setMovementMethod(new ScrollingMovementMethod());
            //Asignamos la edad del usuario.
            tfFechaNac.setText(getResources().getString(R.string.edad)+getEdad(alumno.getFechaNac())+getResources().getString(R.string.age));
            tfCuenta.setText(alumno.getNumCuenta());
            tfCarrera.setText(alumno.getCarrera());
            //Función para asignar la imaen
            selectImage(alumno.getId());
        }else{
            Toast.makeText(this,getResources().getString(R.string.errorObject),Toast.LENGTH_LONG);
        }
    }

    private int getEdad(String date) {
        int age=0;
        //Obtenemo la fecha actual
        Calendar c=Calendar.getInstance();
        int year=c.get(Calendar.YEAR);
        int month=c.get(Calendar.MONTH);
        int day=c.get(Calendar.DAY_OF_MONTH);
        //Obtenemos la fecha ingresada en el Datepicker
        String bd[]=date.split(getResources().getString(R.string.diagonal));
        int yearbd=Integer.parseInt(bd[2]);
        int monthbd=Integer.parseInt(bd[1]);
        int daybd=Integer.parseInt(bd[0]);

        //Obtenemos la edad
        if(month>monthbd) age=year-yearbd;
        else if(month==monthbd){
            if(day>=daybd) age=year-yearbd;
            else age=year-yearbd-1;
        }else age=year-yearbd-1;

        return age;

    }

    //Seleccionamos la edad, dependiendo del indice seleccionado en el spinner
    protected void selectImage(int id){
        switch (id){
            case 1:
                Carrera.setImageDrawable(getResources().getDrawable(R.drawable.aeroespacial));
                break;
            case 2:
                Carrera.setImageDrawable(getResources().getDrawable(R.drawable.ambiental));
                break;
            case 3:
                Carrera.setImageDrawable(getResources().getDrawable(R.drawable.biomedica));
                break;
            case 4:
                Carrera.setImageDrawable(getResources().getDrawable(R.drawable.civil));
                break;
            case 5:
                Carrera.setImageDrawable(getResources().getDrawable(R.drawable.computacion));
                break;
            case 6:
                Carrera.setImageDrawable(getResources().getDrawable(R.drawable.electrica));
                break;
            case 7:
                Carrera.setImageDrawable(getResources().getDrawable(R.drawable.geofisica));
                break;
            case 8:
                Carrera.setImageDrawable(getResources().getDrawable(R.drawable.geologica));
                break;
            case 9:
                Carrera.setImageDrawable(getResources().getDrawable(R.drawable.geomatica));
                break;
            case 10:
                Carrera.setImageDrawable(getResources().getDrawable(R.drawable.industrial));
                break;
            case 11:
                Carrera.setImageDrawable(getResources().getDrawable(R.drawable.mecanica));
                break;
            case 12:
                Carrera.setImageDrawable(getResources().getDrawable(R.drawable.mecatronica));
                break;
            case 13:
                Carrera.setImageDrawable(getResources().getDrawable(R.drawable.minas));
                break;
            case 14:
                Carrera.setImageDrawable(getResources().getDrawable(R.drawable.petrolera));
                break;
            case 15:
                Carrera.setImageDrawable(getResources().getDrawable(R.drawable.telecomunicaciones));
                break;
            default:
                Toast.makeText(this,getResources().getString(R.string.errorObject),Toast.LENGTH_LONG);
                break;
        }
    }

    public void getHome(View view){
        //Llamamos a la actividad principal
        Intent intent=new Intent(this,LOG_IN.class);
        //Limpiamos la pila de aplicaciones
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        //Iniciamos la actividad principal
        startActivity(intent);
    }
}