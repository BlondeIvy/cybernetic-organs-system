package com.cybernetic;

public class OrganCompatibilityAnalyzer {

    public Patient findCompatiblePatient(Organ organ, WaitingList waitingList) {
        WaitingListNode current = waitingList.getHead();
        while (current != null) {
            if (isCompatible(organ, current.patient)) {
                return current.patient;
            }
            current = current.next;
        }
        return null;
    }


    private boolean isCompatible(Organ organ, Patient patient) {
        int bloodTypeScore = calculateBloodTypeCompatibility(organ.getBloodType(), patient.getBloodType());
        int weightScore = calculateWeightCompatibility(organ.getWeight(), patient.getWeight());
        int hlaScore = calculateHlaCompatibility(organ.getHlaType(), patient.getHlaType());

        return bloodTypeScore > 0 && weightScore > 0 && hlaScore > 0;
    }


    private int calculateBloodTypeCompatibility(String donorType, String recipientType) {
        String donorTypeLetters = donorType.replaceAll("[+-]", "");
        String recipientTypeLetters = recipientType.replaceAll("[+-]", "");

        if (donorTypeLetters.equals(recipientTypeLetters)) {
            return 100;
        }
        if (donorTypeLetters.equals("O")) {
            return 100;
        }
        if (recipientTypeLetters.equals("AB")) {
            return 80;
        }
        if (donorTypeLetters.startsWith("A") && recipientTypeLetters.startsWith("AB")) {
            return 80;
        }
        if (donorTypeLetters.startsWith("B") && recipientTypeLetters.endsWith("AB")) {
            return 80;
        }
        return 0;
    }

    private int calculateWeightCompatibility(int organWeight, int patientWeight) {
        double patientWeightInG = patientWeight * 1000;
        double calculatedWeightRatio = (double) organWeight / patientWeightInG;
        if(calculatedWeightRatio >= 0.8 && calculatedWeightRatio<= 1.2){
            return 100;
        }
        if(calculatedWeightRatio >= 0.6 && calculatedWeightRatio < 0.8 || calculatedWeightRatio > 1.2 && calculatedWeightRatio <= 1.4){
            return 50;
        }
        return 0;
    }


    private int calculateHlaCompatibility(String organHla, String patientHla) {
        String[] organHlaParts = organHla.split("-");
        String[] patientHlaParts = patientHla.split("-");
        int matches = 0;
        for(String organPart : organHlaParts){
            for(String patientPart : patientHlaParts){
                if(organPart.equals(patientPart)){
                    matches++;
                    break;
                }
            }
        }
        return (int) (((double) matches / organHlaParts.length) * 100);
    }

    public double calculateCompatibilityScore(Organ organ, Patient patient) {
        double bloodTypeScore = calculateBloodTypeCompatibility(organ.getBloodType(), patient.getBloodType());
        double weightScore = calculateWeightCompatibility(organ.getWeight(), patient.getWeight());
        double hlaScore = calculateHlaCompatibility(organ.getHlaType(), patient.getHlaType());
        return (bloodTypeScore * 0.4) + (weightScore * 0.3) + (hlaScore * 0.3);
    }

}
