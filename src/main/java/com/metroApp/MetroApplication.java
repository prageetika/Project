package com.metroApp;

import com.metroApp.config.Configurations;
import com.metroApp.constants.StationName;
import com.metroApp.manager.MetroManager;
import com.metroApp.manager.SmartCardManager;
import com.metroApp.model.Journey;
import com.metroApp.model.SmartCard;
import com.metroApp.service.MetroService;
import com.metroApp.service.SmartCardService;
import com.metroApp.utils.MetroUtility;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;


public class MetroApplication {

    private MetroManager metroManager;
    private SmartCardManager smartCardManager;
    private SmartCardService smartCardService;
    private MetroService metroService;
    private Configurations configurations;
    private MetroUtility metroUtility;

    private List<SmartCard> smartCards;

    MetroApplication() throws IOException {
        this.configurations = new Configurations();
        this.metroUtility = new MetroUtility();

        this.smartCardService = new SmartCardService(configurations, metroUtility);
        this.metroService = new MetroService(smartCardService, metroUtility, configurations);

        this.smartCardManager = new SmartCardManager(smartCardService);
        this.metroManager = new MetroManager(metroService, smartCardManager);
    }

    private void init(int count) throws Exception {
        smartCards = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            SmartCard smartCard = new SmartCard();
            smartCard.setCustomerName("Prageetika_" + i);
            smartCard.setCustomerAddress("U.P, Noida");
            this.smartCardService.createSmartCard(smartCard);
            smartCards.add(smartCard);
        }
    }

    public static void main(String[] args) throws Exception {
        MetroApplication metroApplication = new MetroApplication();
        metroApplication.init(10);
        boolean exit=false;

        while (!exit) {
            Scanner scanner = new Scanner(System.in);

            System.out.println("############## CHOOSE THE SMART CARD TO TRAVEL #############");
            metroApplication.smartCards.stream().forEach(smartCard -> {
                System.out.println("ID : " + smartCard.getId());
            });
            System.out.println("############################################################");

            System.out.println("\n############################################################");
            System.out.println("Enter the Smart Card Id to travel");

            String id = scanner.nextLine();
            System.out.println("\n############################################################");

            System.out.println("Enter the Start Station travel");

            String startStation = scanner.nextLine();

            System.out.println("\n############################################################");

            System.out.println("Enter the End Station travel");

            String endStation = scanner.nextLine();

            System.out.println("\n############################################################");

            Journey journey = new Journey();
            journey.setSmartCardId(UUID.fromString(id));
            journey.setEndStation(StationName.valueOf(endStation));
            journey.setStartStation(StationName.valueOf(startStation));
            SmartCard smartCard = metroApplication.smartCardManager.getSmartCard(journey.getSmartCardId(), metroApplication.smartCards);
            metroApplication.smartCardService.deductBalance(smartCard, journey);
            System.out.println(smartCard);

            System.out.println("\n############################################################");

            System.out.println("Do you want to continue(y/n)?");

            exit =scanner.nextLine().equalsIgnoreCase("y")?false:true;

            System.out.println("\n############################################################");
        }
    }

}
