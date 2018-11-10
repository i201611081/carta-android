package pe.edu.cibertec.app_demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    private EditText Name;
    private EditText Password;
    private TextView info;
    //botones
    private Button login;
    private Button registrar;
    private int counter = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Name = (EditText)findViewById(R.id.etName);
        Password= (EditText)findViewById(R.id.etPassword);
        info = (TextView)findViewById(R.id.tvInfo);
        login = (Button)findViewById(R.id.btnLogin);
        registrar =(Button)findViewById(R.id.btnRegistro);

        info.setText("no de intentos restantes es: 5");

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate(Name.getText().toString(), Password.getText().toString());
            }
        });


        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentreg = new Intent(MainActivity.this, RegistroActivity.class);
                MainActivity.this.startActivity(intentreg);

            }
        });


    }

    private  void validate(String userName, String userPassword ) {
        if ((userName.equals( "admin")) && (userPassword.equals("123"))) {

            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            startActivity(intent);
        }else{
            counter--;
            info.setText("no de intentos son: " + String.valueOf(counter));

            if(counter==0){
                login.setEnabled(false);

            }
        }

    }


}
