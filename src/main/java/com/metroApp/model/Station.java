package com.metroApp.model;

import com.metroApp.constants.StationName;


import java.util.UUID;


public class Station {

    private UUID id;
    private StationName station;
    private int cost;

    public Station() {
    }

    public Station(StationName station, int cost) {
        this.station = station;
        this.cost = cost;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public StationName getStation() {
        return station;
    }

    public void setStation(StationName station) {
        this.station = station;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}
