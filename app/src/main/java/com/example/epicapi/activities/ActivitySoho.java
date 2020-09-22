package com.example.epicapi.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.epicapi.R;
import com.example.epicapi.activities.Models.MainActivity;
import com.example.epicapi.activities.Models.ObjActivitiesString;

import java.io.File;
import java.io.OutputStream;

public class ActivitySoho extends AppCompatActivity {

    private Button btnPrincipal;
    private Button btnImagemSalvar;
    private  Button btnWhatsApp;
    private TextView txtNome;
    private TextView txtText;
    ImageView ImagemSoho;

    OutputStream outputStream;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soho);

        ImagemSoho = findViewById(R.id.imagemSoho);
        txtNome = findViewById(R.id.NomeAct2);
        txtText = findViewById(R.id.TextoAct2);
        btnPrincipal=findViewById(R.id.btnVoltaPrincipal);
        btnImagemSalvar=findViewById(R.id.btnDownload);
        btnWhatsApp = findViewById(R.id.btnWhatsApp);

        Bundle dados = getIntent().getExtras();
        ObjActivitiesString objActivitiesString = (ObjActivitiesString) dados.getSerializable("objeto");

        txtNome.setText(objActivitiesString.getNome());
        txtText.setText(objActivitiesString.getTexto());


        btnPrincipal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });



        btnImagemSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityCompat.requestPermissions(ActivitySoho.this, new String[]
                        {Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        1);
                File direct = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
                        .getAbsolutePath() + "/" + System.currentTimeMillis());

                if (!direct.exists()) direct.mkdir();

                DownloadManager dm = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
                Uri downloadUri = Uri.parse("https://solarsystem.nasa.gov/system/content_pages/main_images/1390_SOHO_20151202_1600.jpg");
                DownloadManager.Request request = new DownloadManager.Request(downloadUri);
                request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE)
                        .setAllowedOverRoaming(false)
                        .setTitle("" + System.currentTimeMillis())
                        .setMimeType("image/jpeg")
                        .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                        .setDestinationInExternalPublicDir(Environment.DIRECTORY_PICTURES,
                                File.separator + "/Soho/" + System.currentTimeMillis());

                assert dm != null;
                dm.enqueue(request);

                ObjActivitiesString objActivitiesString2 = new ObjActivitiesString("Imagem foi baixada!","Sua imagem foi baixado com sucesso!");

                Intent intent = new Intent(getApplicationContext(),ImagemBaixada.class);
                intent.putExtra("objeto2", objActivitiesString2);
                startActivity(intent);

                // Toast.makeText(ActivitySoho.this, "Imagem salva!", Toast.LENGTH_SHORT).show();

            }
        });

        btnWhatsApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendWhatsapp("Desbubra mais sobre a SOHO neste link! \n https://www.nasa.gov/mission_pages/soho/overview/index.html");
            }
        });


    }
    private void sendWhatsapp(String message){
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, message);
        sendIntent.setType("text/plain");
        sendIntent.setPackage("com.whatsapp");
        if (sendIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(sendIntent);
        }
    }



}