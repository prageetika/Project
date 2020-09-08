package com.metroApp.service;

import com.metroApp.config.Configurations;
import com.metroApp.model.Journey;
import com.metroApp.model.Metro;
import com.metroApp.model.SmartCard;
import com.metroApp.utils.MetroUtility;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;


public class SmartCardService {


    private MetroUtility metroUtility;


    Configurations configurations;


    Metro metro;

    public SmartCardService(Configurations configurations,MetroUtility metroUtility) throws IOException {
        this.metroUtility = metroUtility;
        this.configurations = configurations;
        this.metro=Metro.getMetroInstance();
    }

    public void deductBalance(SmartCard smartCard, Journey journey) throws IOException{


        AtomicInteger totalFare = new AtomicInteger(0);
        journey.setId(metroUtility.getRandomId());

        getTotalFare(journey, totalFare);
        smartCard.setBalance(smartCard.getBalance() - totalFare.get());
        smartCard.setCurrentJourney(journey);
        smartCard.getTravelHistory().add(journey);

    }


    public boolean isBalanceSufficient(SmartCard smartCard) throws IOException{

        if (smartCard.getBalance() <= Integer.parseInt(configurations.getPropValue("minBalance"))) {
            return false;
        } else {
            return true;
        }

    }


    public boolean isBalanceSufficientForJourney(SmartCard smartCard, Journey journey) throws IOException{
        if (!isBalanceSufficient(smartCard)) {
            return false;
        } else {
            AtomicInteger totalFare = new AtomicInteger(0);
            getTotalFare(journey, totalFare);
            if ((smartCard.getBalance() - totalFare.get()) <= 0) {
                return false;
            } else
                return true;
        }

    }

    private void getTotalFare(Journey journey, AtomicInteger totalFare) {
        AtomicBoolean found = new AtomicBoolean(false);
        metro.getStations().forEach(station -> {
            int newValue = 0;
            try {
                newValue = found.get() ? totalFare.get() + Integer.parseInt(configurations.getPropValue("fareIncrementStrategy")) : totalFare.get();
            } catch (IOException e) {
                e.printStackTrace();
            }
            totalFare.set(newValue);
            if (station.getStation().equals(journey.getStartStation())) {
                found.set(true);
            }
            if (found.get() && station.getStation().equals(journey.getEndStation())) {
                found.set(false);
            }


        });
    }


    public void addBalance(SmartCard smartCard, int money) {

    }


    public SmartCard createSmartCard(SmartCard smartCard) throws Exception {
        if (smartCard.getCustomerName() != null && smartCard.getCustomerAddress() != null) {
            smartCard.setId(metroUtility.getRandomId());
            smartCard.setBalance(Integer.parseInt(configurations.getPropValue("defaultBalance")));
            smartCard.setTravelHistory(new ArrayList<>());
            return smartCard;
        } else {
            throw new Exception("Please Enter valid customer name or address");
        }
    }


    public SmartCard getSmartCard(UUID id, List<SmartCard> smartCards) {
        Optional<SmartCard> smartCard = smartCards.stream().filter(smartCardValue ->
                smartCardValue.getId().equals(id)
        ).findFirst();

        return smartCard.get();

    }
}
