package com.example.epicapi.activities.Models;

import java.io.Serializable;

public class Posicao implements Serializable {

    private Long id;
    private String px;
    private String py;
    private String pz;
    private String datapesq;
    private String datephone;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPx() {
        return px;
    }

    public void setPx(String px) {
        this.px = px;
    }

    public String getPy() {
        return py;
    }

    public void setPy(String py) {
        this.py = py;
    }

    public String getPz() {
        return pz;
    }

    public void setPz(String pz) {
        this.pz = pz;
    }

    public String getDatephone() {
        return datephone;
    }

    public void setDatephone(String datephone) {
        this.datephone = datephone;
    }

    public String getDatapesq() {
        return datapesq;
    }

    public void setDatapesq(String datapesq) {
        this.datapesq = datapesq;
    }
}
