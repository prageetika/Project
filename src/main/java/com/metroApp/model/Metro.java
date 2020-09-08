package com.metroApp.model;

import com.metroApp.config.Configurations;
import com.metroApp.constants.StationName;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.concurrent.atomic.AtomicInteger;


public class Metro {

    private Deque<Station> stations;
    private StationName currentStation;
    private Configurations configurations;
    private static Metro metro;

    private Metro() throws IOException {
        this.configurations = new Configurations();
        this.stations = getStationList();
    }

    public static Metro getMetroInstance() throws IOException {
        if(metro==null){
            synchronized (Metro.class){
                if (metro==null){
                    metro=new Metro();
                }
            }
        }
        return  metro;
    }

    public Deque<Station> getStations() {
        return stations;
    }


    public StationName getCurrentStation() {
        return currentStation;
    }

    private Deque<Station> getStationList() {
        Deque<Station> stations = new ArrayDeque<>();
        AtomicInteger atomicInteger = new AtomicInteger(0);
        Arrays.asList(StationName.values()).forEach((name) -> {
            stations.add(new Station(name, atomicInteger.get()));
            try {
                atomicInteger.set(atomicInteger.get() + Integer.parseInt(configurations.getPropValue("fareIncrementStrategy")));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        return stations;
    }

}
