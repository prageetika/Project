package com.metroApp.model;

import com.metroApp.constants.StationName;

import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


public class Journey {

    private UUID id;
    private StationName startStation;
    private StationName endStation;
    private UUID smartCardId;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public StationName getStartStation() {
        return startStation;
    }

    public void setStartStation(StationName startStation) {
        this.startStation = startStation;
    }

    public StationName getEndStation() {
        return endStation;
    }

    public void setEndStation(StationName endStation) {
        this.endStation = endStation;
    }

    public UUID getSmartCardId() {
        return smartCardId;
    }

    public void setSmartCardId(UUID smartCardId) {
        this.smartCardId = smartCardId;
    }

    @Override
    public String toString() {
        return "{"+startStation +" To "+ endStation+"}" ;
    }
}
