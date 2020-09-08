package com.metroApp.utils;


import com.metroApp.constants.StationName;
import com.metroApp.model.Station;

import java.util.Deque;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;


public class MetroUtility {
    public UUID getRandomId() {
        return UUID.randomUUID();
    }

    public boolean checkStationName(Deque<Station> stations, StationName stationName) {
        AtomicReference<Boolean> found = new AtomicReference<>(false);
        stations.forEach(station -> {
            if (station.getStation().equals(stationName)) {
                found.set(true);
            }
        });
        return found.get();
    }
}
