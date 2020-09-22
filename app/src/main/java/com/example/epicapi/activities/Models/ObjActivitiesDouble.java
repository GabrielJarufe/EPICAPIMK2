package com.example.epicapi.activities.Models;

import java.io.Serializable;

public class ObjActivitiesDouble implements Serializable {
    private double lat;
    private double log;

    public ObjActivitiesDouble(double lat, double log) {
        this.lat = lat;
        this.log = log;
    }
    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLog() {
        return log;
    }

    public void setLog(double log) {
        this.log = log;
    }
}
