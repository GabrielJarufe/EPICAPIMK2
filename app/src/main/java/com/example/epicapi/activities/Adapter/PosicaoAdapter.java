package com.example.epicapi.activities.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.epicapi.R;
import com.example.epicapi.activities.Models.Posicao;

import java.util.List;

public class PosicaoAdapter extends RecyclerView.Adapter<PosicaoAdapter.MyViewHolder> {


    private List<Posicao> listaPosicoes;

    public PosicaoAdapter(List<Posicao>lista) {
        this.listaPosicoes = lista;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemLista = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.lista_posicao_adapter,parent,false);

        return new MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            Posicao posicao = listaPosicoes.get(position);
            holder.px.setText(posicao.getPx());
            holder.py.setText(posicao.getPx());
            holder.pz.setText(posicao.getPx());
            holder.data.setText(posicao.getDatapesq());
    }

    @Override
    public int getItemCount() {
        return this.listaPosicoes.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView px;
        TextView py;
        TextView pz;
        TextView data;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            px = itemView.findViewById(R.id.textPsXa);
            py = itemView.findViewById(R.id.textPsYa);
            pz = itemView.findViewById(R.id.textPsZa);
            data = itemView.findViewById(R.id.textDataPesq);
        }
    }

}
