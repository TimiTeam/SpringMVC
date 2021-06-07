package com.gmail.timatiblackstar666.SpringMVC.models;

public enum DocumentType {
    TC_PASSPORT("PASSPORT"),
    TC_VEHICLE_LICENCE("VEHICLE LICENCE"),
    ;

    DocumentType(String name) {
        this.name = name;
    }

    private final String name;

    public String getName() {
        return name;
    }
}
