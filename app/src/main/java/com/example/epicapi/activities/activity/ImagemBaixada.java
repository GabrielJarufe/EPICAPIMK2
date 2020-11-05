package com.example.epicapi.activities.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.epicapi.R;
import com.example.epicapi.activities.Models.ObjActivitiesString;

import static android.content.Intent.ACTION_CALL;

public class ImagemBaixada extends AppCompatActivity {

    private TextView NomeAct3;
    private TextView TextoAct3;
    private ImageView Image_View;
    private Button btnPrincipal3;
    private Button btnLigar;
    private TextView txtNumeroNasa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imagem_baixada);

        NomeAct3 = findViewById(R.id.NomeAct3);
        TextoAct3 = findViewById(R.id.TextAct3);
        Image_View = findViewById(R.id.imgAct3);
        btnPrincipal3 = findViewById(R.id.btnVoltaPrincipal3);
        btnLigar = findViewById(R.id.btnLigar);
        txtNumeroNasa = findViewById(R.id.txtNumero);
        Bundle dados = getIntent().getExtras();
        ObjActivitiesString objActivitiesString = (ObjActivitiesString) dados.getSerializable("objeto2");

        NomeAct3.setText(objActivitiesString.getNome());
        TextoAct3.setText(objActivitiesString.getTexto());

        btnLigar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String numero = txtNumeroNasa.getText().toString();
                Uri uri = Uri.parse("tel: "+numero);
                Intent intent = new Intent(ACTION_CALL,
                        uri);
                if(ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(ImagemBaixada.this,new String[]{(Manifest.permission.CALL_PHONE)},1);
                }else{
                    startActivity(intent);
                }

            }
        });


        btnPrincipal3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
            }
        });
    }



}