package com.example.epicapi.activities.Models;

import java.io.Serializable;

public class ObjActivitiesString implements Serializable {
    private String nome;
    private String texto;

    public ObjActivitiesString(String nome, String texto) {
        this.nome = nome;
        this.texto = texto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String email) {
        this.texto = texto;
    }
}


