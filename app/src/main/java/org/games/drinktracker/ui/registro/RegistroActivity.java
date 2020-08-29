package org.games.drinktracker.ui.registro;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.games.drinktracker.R;
import org.games.drinktracker.controllers.RegistroController;
import org.games.drinktracker.model.Registro;

public class RegistroActivity extends AppCompatActivity {
    private EditText etId;
    private EditText etTipo;
    private EditText etCantidad;
    private EditText etFecha;

    private Button boton1;
    private Button boton2;

    private int tipo;
    private Registro registro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        etTipo=findViewById(R.id.etTipo);
        etCantidad=findViewById(R.id.etCantidad);
        boton1=findViewById(R.id.button1);
        boton2=findViewById(R.id.button2);

        tipo=getIntent().getIntExtra("tipo",2);
        if(tipo==1) {
            registro = (Registro)getIntent().getSerializableExtra("registros");
            etTipo.setText(registro.getTipo());
            etCantidad.setText(""+registro.getCantidad());
            boton1.setText(getString(R.string.btModificar));
            boton2.setText(getString(R.string.btEliminar));
        } else {
            etTipo.setText("");
            etCantidad.setText("");
            boton1.setText(getString(R.string.btSalvar));
            boton2.setText(getString(R.string.btCancelar));
        }

        boton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                proceso1();
            }
        });

        boton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                proceso2();
            }
        });
    }

    private void proceso1() {
        String tiporeg=etTipo.getText().toString();
        int cantidad=Integer.parseInt(etCantidad.getText().toString());
        registro=new Registro(tiporeg,cantidad);
        boolean accion = (tipo==1) ? RegistroController.modifica(registro) : RegistroController.inserta(registro);
        if (accion) {
            Toast.makeText(getApplicationContext(), "Realizado!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "ERROR!", Toast.LENGTH_SHORT).show();
        }
        finish();
    }

    private void proceso2() {
        if(tipo==1) {
            int id=Integer.parseInt(etId.getText().toString());
            if (RegistroController.elimina(id)) {
                Toast.makeText(getApplicationContext(), "Eliminado!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), "Error!", Toast.LENGTH_SHORT).show();
            }
        }
        finish();
    }

}