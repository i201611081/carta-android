package pe.edu.cibertec.app_demo;


import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.blikoon.qrcodescanner.QrCodeActivity;
import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class SecondActivity extends AppCompatActivity {
TextView a;

    private static final int REQUEST_CODE_QR_SCAN = 101;
    //private ZXingScannerView mScannerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

a= (TextView)findViewById(R.id.a);



    }



    public void btnQr(View v){

        Intent i = new Intent(SecondActivity.this, QrCodeActivity.class);
        startActivityForResult(i, REQUEST_CODE_QR_SCAN);
    }


    //public void handleResult(Result result) {

      //  Log.v("HandleResult", result.getText());
       // AlertDialog.Builder builder = new AlertDialog.Builder(this);
       // builder.setTitle("resultado de scan");
       // builder.setMessage(result.getText());
        //AlertDialog alertDialog = builder.create();
        //alertDialog.show();

       // mScannerView.resumeCameraPreview(this);


   //}


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode != Activity.RESULT_OK) {
            Toast.makeText(getApplicationContext(), "No se pudo obtener una respuesta", Toast.LENGTH_SHORT).show();
            String resultado = data.getStringExtra("com.blikoon.qrcodescanner.error_decoding_image");
            if (resultado != null) {
                Toast.makeText(getApplicationContext(), "No se pudo escanear el código QR", Toast.LENGTH_SHORT).show();
            }
            return;
        }
        if (requestCode == REQUEST_CODE_QR_SCAN) {
            if (data != null) {
                String lectura = data.getStringExtra("com.blikoon.qrcodescanner.got_qr_scan_relult");
                Toast.makeText(getApplicationContext(), "Leído: " + lectura, Toast.LENGTH_SHORT).show();


                if(lectura != null){

                    a.setVisibility(View.VISIBLE);
            }




            }

        }


    }



    public void a(View view) {
Intent siguiente = new Intent(SecondActivity.this,CartaActivity.class);
 startActivity(siguiente);



    }
}
