package com.metroApp.model;



import java.util.List;
import java.util.UUID;



public class SmartCard {


    private UUID id;

    private String customerName;
    private float customerAge;
    private String customerAddress;
    private int balance;
    private List<Journey> travelHistory;
    private Journey currentJourney;


    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName( String customerName) {
        this.customerName = customerName;
    }

    public float getCustomerAge() {
        return customerAge;
    }

    public void setCustomerAge(float customerAge) {
        this.customerAge = customerAge;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public Journey getCurrentJourney() {
        return currentJourney;
    }

    public void setCurrentJourney(Journey currentJourney) {
        this.currentJourney = currentJourney;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public List<Journey> getTravelHistory() {
        return travelHistory;
    }

    public void setTravelHistory(List<Journey> travelHistory) {
        this.travelHistory = travelHistory;
    }

    @Override
    public String toString() {
        return "SmartCard: {" +
                "\n id=" + id +
                ",\n customerName='" + customerName + '\'' +
                ",\n customerAge=" + customerAge +
                ",\n customerAddress='" + customerAddress + '\'' +
                ",\n balance=" + balance +
                ",\n travelHistory=" + travelHistory.toString() +
                ",\n currentJourney=" + currentJourney.toString() +
                "\n}";
    }
}
