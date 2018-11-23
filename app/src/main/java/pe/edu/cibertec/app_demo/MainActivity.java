package pe.edu.cibertec.app_demo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    RequestQueue request;
    private static final String TAG = "main";
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
                RequestFace(Name.getText().toString(), Password.getText().toString());
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

    public void RequestFace(String user,String pass){

        String URL = "https://ff4f1d8a.ngrok.io/login";
        Map<String, Object> object= new HashMap<>();
        object.put("user",user);
        object.put("pass",pass);

        request = Volley.newRequestQueue(getApplicationContext());
        JsonObjectRequest o = new JsonObjectRequest(Request.Method.POST, URL, new JSONObject(object), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    if(response.getString("estado").equals("ok")){
                        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                        startActivity(intent);
                        finish();
                    }
                    else{
                        Toast.makeText(MainActivity.this,"Usuario o contraseña incorrecta",Toast.LENGTH_LONG).show();
                        counter--;
                        info.setText("no de intentos son: " + String.valueOf(counter));
                        if(counter==0){
                            login.setEnabled(false);
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(MainActivity.this,e.getMessage(),Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this,"Error en la Solicitud : " +error.getMessage(),Toast.LENGTH_LONG).show();
            }
        });

        o.setTag(TAG);
        request.add(o);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(request != null){
            request.cancelAll(TAG);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(request != null){
            request.cancelAll(TAG);
        }
    }
}
