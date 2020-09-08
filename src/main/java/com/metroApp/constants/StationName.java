package com.metroApp.constants;

public enum StationName {

    A1("A1"), A2("A2"), A3("A3"), A4("A4"), A5("A5"), A6("A6"), A7("A7"), A8("A8"), A9("A9"), A10("A10");

    private String name;

    StationName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
