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

    public Patient(String id, String name, int age, String bloodType,
                   String organNeeded, int urgencyLevel,
                   LocalDate registrationDate, String status) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.bloodType = bloodType;
        this.organNeeded = organNeeded;
        this.urgencyLevel = urgencyLevel;
        this.registrationDate = registrationDate;
        this.status = status;
    }
}