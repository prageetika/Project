package com.metroApp.service;

import com.metroApp.config.Configurations;
import com.metroApp.constants.StationName;
import com.metroApp.model.Journey;
import com.metroApp.model.Metro;
import com.metroApp.model.SmartCard;
import com.metroApp.model.Station;
import com.metroApp.utils.MetroUtility;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Deque;
import java.util.List;

@Service
public class MetroService {


    private SmartCardService smartCardService;
    private Metro metro;
    private MetroUtility metroUtility;
    private Configurations configurations;

    public MetroService(SmartCardService smartCardService, MetroUtility metroUtility, Configurations configurations) throws IOException {
        this.smartCardService = smartCardService;
        this.metroUtility = metroUtility;
        this.configurations = configurations;
        this.metro=Metro.getMetroInstance();
    }

    public StationName getCurrentStation() throws Exception {
        return metro.getCurrentStation();
    }


    public Deque<Station> getStations() throws Exception {
        return metro.getStations();
    }


    public boolean validEntry(Journey journey,List<SmartCard>  smartCards) throws Exception {
        if (isStartEndValid(journey) && isBalanceSufficient(journey,smartCards))
            return true;
        else return false;
    }

    private boolean isStartEndValid(Journey journey) {
        if (metroUtility.checkStationName(metro.getStations(),journey.getStartStation()) && metroUtility.checkStationName(metro.getStations(),journey.getEndStation())) {
            return true;
        } else
            return false;
    }

    private boolean isBalanceSufficient(Journey journey, List<SmartCard> smartCards) throws IOException {
        SmartCard smartCard = smartCardService.getSmartCard(journey.getSmartCardId(),smartCards);
        if (smartCardService.isBalanceSufficientForJourney(smartCard,journey)) {
            return true;
        } else
            return false;
    }
}
