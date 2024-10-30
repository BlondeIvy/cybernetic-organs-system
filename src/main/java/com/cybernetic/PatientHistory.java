package com.cybernetic;

import java.util.Stack;

public class PatientHistory {
    private Stack<String> medicalHistory;

    public PatientHistory() {
        this.medicalHistory = new Stack<>();
    }

    public void addMedicalEvent(String event) {
        medicalHistory.push(event);
    }

    public String viewLatestEvent() {
        if(!medicalHistory.isEmpty()){
            return medicalHistory.peek();
        }
        return null;
    }

    public String removeMostRecentEvent() {
        if (!medicalHistory.isEmpty()) {
            return medicalHistory.pop();
        }
        return null;
    }

    public boolean isEmpty() {
        return medicalHistory.isEmpty();
    }

}