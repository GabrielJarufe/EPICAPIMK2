package com.example.epicapi.activities.helper;

import com.example.epicapi.activities.Models.Posicao;

import java.util.List;

public interface IPosicaoDAO {

    public boolean salvar(Posicao posicao);
    public List<Posicao> listar();

}
