package com.metroApp.manager;

import com.metroApp.model.Journey;
import com.metroApp.model.SmartCard;
import com.metroApp.service.MetroService;
import com.metroApp.service.SmartCardService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;


public class MetroManager {


    MetroService metroService;
    SmartCardManager smartCardManager;

    public MetroManager(MetroService metroService, SmartCardManager smartCardManager) {
        this.metroService = metroService;
        this.smartCardManager = smartCardManager;
    }


    public ResponseEntity<Object> validEntry(Journey journey,List<SmartCard> smartCards) {
        try {
            boolean result = metroService.validEntry(journey,smartCards);
            return ResponseEntity.ok(result);
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
        }
    }


    public ResponseEntity<Object> travel(Journey journey, List<SmartCard> smartCards) {
        SmartCard smartCard = null;
        try {
           if(metroService.validEntry(journey,smartCards))
           {
            smartCard=smartCardManager.getSmartCard(journey.getSmartCardId(),smartCards);
               smartCardManager.deductBalance(smartCard,journey);
           }
            return ResponseEntity.ok(smartCard);
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
        }
    }

}
