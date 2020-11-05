package com.example.epicapi.activities.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.LinearLayout;

import com.example.epicapi.R;
import com.example.epicapi.activities.Adapter.PosicaoAdapter;
import com.example.epicapi.activities.Models.Posicao;
import com.example.epicapi.activities.helper.DbHelper;
import com.example.epicapi.activities.helper.PosicaoDAO;

import java.util.ArrayList;
import java.util.List;

public class ActivityBanco extends AppCompatActivity {

    private RecyclerView recyclerView;
    private PosicaoAdapter posicaoAdapter;
    private List<Posicao> listaPosicoes = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banco);
        //Configurando RecyclerView
        recyclerView = findViewById(R.id.recyclerView);
    }

    public void carregarListaTarefas(){

        //Listar Tarefas
        PosicaoDAO tarefaDAO = new PosicaoDAO(getApplicationContext());
        listaPosicoes = tarefaDAO.listar();
         /*
            Exibe lista de tarefas no RecyclerView
         */

        //configurar um adapter
        posicaoAdapter = new PosicaoAdapter(listaPosicoes);
        //configurar RecyclerView
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), LinearLayout.VERTICAL));
        recyclerView.setAdapter(posicaoAdapter);
    }

    @Override
    protected void onStart() {
        carregarListaTarefas();
        super.onStart();
    }
}