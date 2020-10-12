package com.example.practicaeventos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView textoC;
    private MainActivity thisRef;
    /**
     * Creamos un constructor del mainActivity donde le pasamos un TextView donde el cual al pulsar haremos algo con el evento
     * onClick
     */
    public MainActivity(){
        thisRef=this;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.textoC=this.findViewById(R.id.textoCentral);//nos devuelve un objeto de tipo al que le llames de la id en este caso TextView


        //si hacemos el Toast en el activiti main nos lanzala el msj al iniciar la app
        Toast.makeText(this,R.string.actividad_iniciada,Toast.LENGTH_LONG).show();

        //para poder compliar bien esta funcion debemos crear un private de nuestra clase principal y luego luego instanciarlo dentro
        //del constructor por ultimo a donce vayamos a hacer la funcion del click en este caso es TextoC y debemos tambien de crearlo dentro
        //de un private pero instanciarlo dentro del main con el findViewv
      textoC.setOnClickListener(new View.OnClickListener() {//Al pulsar el texto se ejecuta el onclick
          @Override
          public void onClick(View v) {
              //Operaciones
              Toast.makeText(thisRef,"Se ha pulsado el TextView", Toast.LENGTH_LONG).show();
          }
      });
    }

    public void cambiar_estilo(View view) {

        Toast.makeText(this, R.string.estilo_cambiado, Toast.LENGTH_LONG).show();//al hacele click al boton mostramos un msj

    //   TextView textoC= this.findViewById(R.id.textoCentral);//nos devuelve un objeto de tipo al que le llames de la id en este caso TextView
        ConstraintLayout contenedorPrincipal =this.findViewById(R.id.layoutPrincipal);

        contenedorPrincipal.setBackgroundColor(ContextCompat.getColor(this,R.color.feo));//ponemos el color del layout fondo a marron
        textoC.setTextSize(30f);//podemos cambiar el tamaño de testo gracias al objeto de tivo TextView

        Log.e("Error ficticio","Simulamos que ha ocurrido un error, pero no ha ocurrido de verdad.");
        Log.d("Informacion ficticia","Simulamos que mostramos una informacion util por el log. Por ejemplo el texto del TextView"+textoC.getText());
    }
}