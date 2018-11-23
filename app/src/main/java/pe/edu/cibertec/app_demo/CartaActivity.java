package pe.edu.cibertec.app_demo;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pe.edu.cibertec.app_demo.adapter.RecicleView_Plato;
import pe.edu.cibertec.app_demo.bean.PlatosBean;

public class CartaActivity extends AppCompatActivity {

    RequestQueue request;
    private static final String TAG = "main";
    private Context context;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carta);
        context = this;
        recyclerView = this.findViewById(R.id.recicleView_carta);
        RequestList();
    }

    public void RequestList(){

        String URL = "https://ff4f1d8a.ngrok.io/listarMenuDia";
        Map<String, Object> object= new HashMap<>();

        request = Volley.newRequestQueue(getApplicationContext());
        JsonObjectRequest o = new JsonObjectRequest(Request.Method.POST, URL, new JSONObject(object), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    if(response.getString("estado").equals("ok")){
                        JSONArray arrayPlatos = response.getJSONArray("platos");
                        List<PlatosBean> lista = new ArrayList<>();
                        for(int i = 0;i<arrayPlatos.length();i++){
                            JSONObject plato = arrayPlatos.getJSONObject(i);
                            lista.add(new PlatosBean(plato.getString("url_plato"),plato.getString("nombre_plato"),plato.getString("descripcion_tiempo_comida"),plato.getString("precio_plato")));
                        }
                        LinearLayoutManager linear = new LinearLayoutManager(context);
                        linear.setOrientation(LinearLayout.VERTICAL);
                        recyclerView.setLayoutManager(linear);
                        recyclerView.setAdapter(new RecicleView_Plato(lista, R.layout.cardview_plato,context));

                    }
                    else{
                        Toast.makeText(context,"Error al parsear resultado",Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(context,e.getMessage(),Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context,"Error en la Solicitud : " +error.getMessage(),Toast.LENGTH_LONG).show();
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
