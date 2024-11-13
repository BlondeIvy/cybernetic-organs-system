package com.cybernetic;

import java.time.LocalDate;

public class CyberneticOrgan {
    private String id;
    private String type;
    private String model;
    private int powerLevel;
    private double compatibilityScore;
    private LocalDate manufactureDate;
    private String status;
    private String manufacturer;

public CyberneticOrgan(String id, String type, String model, int powerLevel, double compatibilityScore,
                       LocalDate manufactureDate, String status, String manufacturer) {

    validateId(id);
    validateType(type);
    validatePowerLevel(powerLevel);
    validateCompatibilityScore(compatibilityScore);
    validateManufactureDate(manufactureDate);

    this.id = id;
    this.type = type;
    this.model = model;
    this.powerLevel = powerLevel;
    this.compatibilityScore = compatibilityScore;
    this.manufactureDate = manufactureDate;
    this.status = status;
    this.manufacturer = manufacturer;
}

    private void validateId(String id) {
        if(!id.matches("ORG-\\d{4}")){
            throw new IllegalArgumentException("Invalid Organ Id");
        }
    }

    private void validateType(String type) {
        if(!type.matches("HEART|LUNG|KIDNEY|LIVER")){
            throw new IllegalArgumentException("Invalid Organ Type");
        }
    }

    private void validatePowerLevel(int powerLevel) {
        if(powerLevel < 1 || powerLevel > 100){
            throw new IllegalArgumentException("Power Level must be between 1 and 100.");
        }
    }

    private void validateCompatibilityScore(double score) {
        if(score < 0.0 || score > 1.0){
            throw new IllegalArgumentException("Compatibility Score must be between 0.0 and 1.0");
        }
    }

    private void validateManufactureDate(LocalDate manufactureDate) {
         if(manufactureDate.isAfter(LocalDate.now())){
            throw new IllegalArgumentException("Manufacture Date cannot be a future date.");
         }
    }

//Applicable Getter:

    public String getId() {
        return id;
    }

    public int getPowerLevel() {
        return powerLevel;
    }

    public String getType() {
        return type;
    }

    public LocalDate getManufactureDate() {
        return manufactureDate;
    }

    public double getCompatibilityScore() {
        return compatibilityScore;
    }
}