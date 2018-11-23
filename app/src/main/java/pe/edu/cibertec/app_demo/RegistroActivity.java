package pe.edu.cibertec.app_demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegistroActivity extends AppCompatActivity  {

    EditText nombreReg;
    EditText contraseñaReg;
    EditText correoReg;
    Button registrar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        nombreReg = (EditText)findViewById(R.id.etUsuarioR);
        contraseñaReg =(EditText)findViewById(R.id.etContraseña);
        correoReg = (EditText)findViewById(R.id.etCorreo);
        registrar =(Button)findViewById(R.id.btnRegistrar);


        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!validacionemail(correoReg.getText().toString())){

                correoReg.setError("email invalido");

                }else{
                    Toast.makeText(RegistroActivity.this, "validation succes", Toast.LENGTH_LONG).show();

                }

            }
        });
    }

    private boolean validacionemail(String correoReg) {

        if(!correoReg.contains("@")){
            return false;
        }else{

            return true;
        }
    }

}
