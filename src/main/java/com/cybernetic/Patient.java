package com.cybernetic;

import java.time.LocalDate;

public class Patient {
    private String id;
    private String name;
    private int age;
    private String bloodType;
    private String organNeeded;
    private int urgencyLevel;
    private LocalDate registrationDate;
    private String status;


    public Patient(String id, String name, int age, String bloodType, String organNeeded,
                   int urgencyLevel, LocalDate registrationDate, String status){
       validateId(id);
       validateAge(age);
       validateBloodType(bloodType);
       validateOrganNeeded(organNeeded);
       validateUrgencyLevel(urgencyLevel);

        this.id = id;
        this.name = name;
        this.age = age;
        this.bloodType = bloodType;
        this.organNeeded = organNeeded;
        this.urgencyLevel = urgencyLevel;
        this.registrationDate = registrationDate;
        this.status = status;
    }

    private void validateId(String id){
        if(!id.matches("PAT-\\d{4}")){
            throw new IllegalArgumentException("Invalid patient ID format.");
        }
    }

    private void validateAge(int age){
        if(age < 1 || age > 120){
            throw new IllegalArgumentException("Age must be between 1 and 120.");
        }
    }

    private void validateBloodType(String bloodType) {
        if(!bloodType.matches("(A|B|AB|O)[+-]")) {
            throw new IllegalArgumentException("Invalid Blood Type.");
        }
    }

    private void validateOrganNeeded(String organNeeded) {
        if(!organNeeded.matches("HEART|LUNG|KIDNEY|LIVER")) {
            throw new IllegalArgumentException("Invalid Organ Type.");
        }
    }

    private void validateUrgencyLevel(int urgencyLevel) {
        if(urgencyLevel < 1 || urgencyLevel > 10){
            throw new IllegalArgumentException("Urgency Level must be between 1 and 10.");
        }
    }

//Applicable Getters:

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getBloodType() {
        return bloodType;
    }

    public String getOrganNeeded() {
        return organNeeded;
    }

}
