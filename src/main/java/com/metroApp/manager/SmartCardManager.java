package com.metroApp.manager;

import com.metroApp.model.Journey;
import com.metroApp.model.SmartCard;
import com.metroApp.service.SmartCardService;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

public class SmartCardManager {


    SmartCardService smartCardService;

    public SmartCardManager(SmartCardService smartCardService) {
        this.smartCardService = smartCardService;
    }

    public UUID createSmartCard(SmartCard smartCard) throws Exception {

        smartCard = smartCardService.createSmartCard(smartCard);
        return smartCard.getId();
    }


    public SmartCard getSmartCard(UUID id, List<SmartCard> smartCards) throws Exception {
        SmartCard smartCard = smartCardService.getSmartCard(id, smartCards);
        return smartCard;
    }

    public void deductBalance(SmartCard smartCard, Journey journey) throws IOException {
        smartCardService.deductBalance(smartCard,journey);
    }
}
