package com.example.epicapi.activities.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.epicapi.R;
import com.example.epicapi.activities.Models.CarregaData;
import com.example.epicapi.activities.Models.ObjActivitiesDouble;
import com.example.epicapi.activities.Models.ObjActivitiesString;
import com.example.epicapi.activities.Models.Posicao;
import com.example.epicapi.activities.helper.PosicaoDAO;


import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity
        implements DatePickerDialog.OnDateSetListener,
        LoaderManager.LoaderCallbacks<String> {

    private EditText nmData;
    private TextView psX;
    private TextView psY;
    private TextView psZ;
    private Button btnSaberMais;
    private Button btnMaps;
    private Button btnSharedData;
    private Button btnLimpar;
    private Button btnBanco;
    private Button btnBusca;
    private SharedPreferences mData;
    private String dateshared;
    private Button btnSalvar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nmData = findViewById(R.id.dataInput);
        psX = findViewById(R.id.textX);
        psY = findViewById(R.id.textY);
        psZ = findViewById(R.id.textZ);
        btnSaberMais = findViewById(R.id.btnSaberMais);
        btnMaps = findViewById(R.id.btnMaps);
        btnSharedData = findViewById(R.id.btnSharedData);
        btnLimpar = findViewById(R.id.btnLimpar);
        btnSalvar = findViewById(R.id.btnSalvar);
        btnBanco = findViewById(R.id.btnBanco);
        btnBusca = findViewById(R.id.btnBusca);
        mData = getSharedPreferences("datafile", 0);


        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                armazenar();
                Toast.makeText(MainActivity.this, "Data Salva!", Toast.LENGTH_SHORT).show();
            }
        });

        btnLimpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nmData.setText("");
                Toast.makeText(MainActivity.this, "Data deletada", Toast.LENGTH_SHORT).show();
            }
        });

        nmData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(
                        nmData.getWindowToken(), 0);
                showDatePickerDialog();
            }
        });

        btnSharedData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recuperar();
            }
        });

        btnBanco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ActivityBanco.class);
                startActivity(intent);
            }
        });

        btnSaberMais.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ObjActivitiesString objActivitiesString = new ObjActivitiesString("Observatório Solar & Heliosférico(SOHO)", "O projeto Do Observatório Solar & Heliosférico (SOHO) é um esforço cooperativo entre a Agência Espacial Europeia (ESA) e a NASA. O SOHO foi projetado para estudar a estrutura interna do Sol, sua extensa atmosfera externa e a origem do vento solar, o fluxo de gás altamente ionizado que sopra continuamente para fora através do Sistema Solar.\n SOHO foi lançado em 2 de dezembro de 1995. A espaçonave SOHO foi construída na Europa por uma equipe da indústria liderada por Matra, e os instrumentos foram fornecidos por cientistas europeus e americanos. A NASA foi responsável pelo lançamento e agora é responsável pelas operações da missão. Grandes antenas de rádio ao redor do mundo que formam a Rede Espacial Profunda da NASA são usadas para rastrear a espaçonave além da órbita da Terra. O controle da missão está baseado no Goddard Space Flight Center da NASA em Maryland.\n O SOHO deveria operar até 1998, mas foi tão bem sucedido que a ESA e a NASA decidiram prolongar sua vida várias vezes e endossaram várias extensões da missão.\n ");

                Intent intent = new Intent(getApplicationContext(), ActivitySoho.class);
                intent.putExtra("nome", "Observatório Solar & Heliosférico(SOHO)");
                intent.putExtra("text", "O projeto Do Observatório Solar & Heliosférico (SOHO) é um esforço cooperativo entre a Agência Espacial Europeia (ESA) e a NASA. O SOHO foi projetado para estudar a estrutura interna do Sol, sua extensa atmosfera externa e a origem do vento solar, o fluxo de gás altamente ionizado que sopra continuamente para fora através do Sistema Solar.\n" +
                        "\n" +
                        "SOHO foi lançado em 2 de dezembro de 1995. A espaçonave SOHO foi construída na Europa por uma equipe da indústria liderada por Matra, e os instrumentos foram fornecidos por cientistas europeus e americanos. A NASA foi responsável pelo lançamento e agora é responsável pelas operações da missão. Grandes antenas de rádio ao redor do mundo que formam a Rede Espacial Profunda da NASA são usadas para rastrear a espaçonave além da órbita da Terra. O controle da missão está baseado no Goddard Space Flight Center da NASA em Maryland.\n" +
                        "\n" +
                        "O SOHO deveria operar até 1998, mas foi tão bem sucedido que a ESA e a NASA decidiram prolongar sua vida várias vezes e endossaram várias extensões da missão.\n" +
                        " ");
                intent.putExtra("objeto", objActivitiesString);

                startActivity(intent);
            }
        });

        btnMaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ObjActivitiesDouble objActivitiesDouble = new ObjActivitiesDouble(28.573967, -80.648898);
                Intent intent = new Intent(getApplicationContext(), LocalizaSohoeUsuario.class);
                intent.putExtra("objeto_nasa", objActivitiesDouble);
                startActivity(intent);
            }
        });

        btnBusca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buscaData(nmData);

            }
        });
    }



    private void showDatePickerDialog() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
    }


    public void buscaData(View view) {
        // Recupera a string de busca.
        String queryString = nmData.getText().toString();
        // esconde o teclado qdo o botão é clicado
        InputMethodManager inputManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputManager != null) {
            inputManager.hideSoftInputFromWindow(view.getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }

        // Verifica o status da conexão de rede
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = null;
        if (connMgr != null) {
            networkInfo = connMgr.getActiveNetworkInfo();
        }
        /* Se a rede estiver disponivel e o campo de busca não estiver vazio
         iniciar o Loader CarregaLivros */
        if (networkInfo != null && networkInfo.isConnected()
                && queryString.length() != 0) {
            Bundle queryBundle = new Bundle();
            queryBundle.putString("queryString", queryString);
            getSupportLoaderManager().restartLoader(0, queryBundle, this);
            psX.setText(R.string.loading);
            psY.setText(R.string.loading);
            psZ.setText(R.string.loading);
        }
        // atualiza a textview para informar que não há conexão ou termo de busca
        else {
            if (queryString.length() == 0) {
                psX.setText(R.string.no_search_term);
                psY.setText(R.string.no_search_term);
                psZ.setText(R.string.no_search_term);
            } else {
                psX.setText(R.string.no_network);
                psY.setText(R.string.no_network);
                psZ.setText(R.string.no_network);
            }
        }
    }


    @NonNull
    @Override
    public Loader<String> onCreateLoader(int id, @Nullable Bundle args) {
        String queryString = "";
        if (args != null) {
            queryString = args.getString("queryString");
        }
        return new CarregaData(this, queryString);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String data) {
        try {

            String x, y, z;


            JSONArray jsonArray = new JSONArray(data);
            //  for(int i=0;i<jsonArray.length();i++){


            JSONObject jsonObject = jsonArray.getJSONObject(0);
            JSONObject jsonObjectSol = jsonObject.getJSONObject("sun_j2000_position");
            x = jsonObjectSol.getString("x");
            y = jsonObjectSol.getString("y");
            z = jsonObjectSol.getString("z");

            psX.setText("posição X: " + x);
            psY.setText("posição Y: " + y);
            psZ.setText("posição Z: " + z);

            PosicaoDAO posicaoDAO = new PosicaoDAO(getApplicationContext());
            Posicao posicao = new Posicao();
            posicao.setPx(x);
            posicao.setPy(y);
            posicao.setPz(z);
            posicao.setDatapesq( nmData.getText().toString());
            posicaoDAO.salvar(posicao);
            //}

        } catch (Exception e) {

            e.printStackTrace();
        }

    }


    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {

    }

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        String date = i + "-0" + i1 + "-0" + i2;
        nmData.setText(date);
    }


    private void armazenar() {

        dateshared = nmData.getText().toString();

        SharedPreferences.Editor preferencesEditor = mData.edit();
        preferencesEditor.putString("data", dateshared);
        preferencesEditor.apply();

    }

    private void recuperar() {

        SharedPreferences sharedPreferences = getSharedPreferences("datafile", 0);

        String lastDateShared = sharedPreferences.getString("data", null);
        nmData.setText(lastDateShared);


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}

