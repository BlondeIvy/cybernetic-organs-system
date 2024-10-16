package com.cybernetic;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OrganCompatibilityAnalyzer {
    private List<Organ> organs;
    private List<Patient> patients;

    public OrganCompatibilityAnalyzer() {
        this.organs = new ArrayList<>();
        this.patients = new ArrayList<>();
    }

    public void addOrgan(Organ organ) {
        organs.add(organ);
    }

    public void addPatient(Patient patient) {
        patients.add(patient);
    }

    public List<Organ> getCompatibleOrgans(Patient patient){
        return organs.stream()
                .filter(organ -> isCompatible(organ, patient))
                .collect(Collectors.toList());
    }

    public boolean isCompatible(Organ organ, Patient patient) {
        double bloodCompatibility = calculateBloodTypeCompatibility(organ.getBloodType(), patient.getBloodType());
        double weightCompatibility = calculateWeightCompatibility(organ.getWeight(), patient.getWeight());
        return bloodCompatibility > 0 && weightCompatibility > 0;
    }

    public Map<Patient, List<Double>> calculateCompatibilityScores() {
        return patients.stream()
                .collect(Collectors.toMap(
                        patient -> patient,
                        patient -> organs.stream()
                                .map(organ -> calculateCompatibilityScore(organ, patient))
                                .collect(Collectors.toList())
                ));
    }

    public double calculateCompatibilityScore(Organ organ, Patient patient) {
        double bloodTypeScore = calculateBloodTypeCompatibility(organ.getBloodType(), patient.getBloodType());
        double weightScore = calculateWeightCompatibility(organ.getWeight(), patient.getWeight());
        double hlaScore = calculateHlaCompatibility(organ.getHlaType(), patient.getHlaType());
        return (bloodTypeScore * 0.4) + (weightScore * 0.3) + (hlaScore * 0.3);
    }

    private int calculateBloodTypeCompatibility(String donorType, String recipientType) {
        String donorTypeLetters = donorType.replaceAll("[+-]", "").trim();
        String recipientTypeLetters = recipientType.replaceAll("[+-]", "").trim();

        if (donorType.equals(recipientType)){
            return 100;
        }
        if (donorTypeLetters.equals("O ")){
            return 100;
        }
        if (recipientTypeLetters.equals("AB ")){
            return 80;
        }
        if (donorTypeLetters.equals("A ") && (recipientTypeLetters.equals("A ")|| recipientTypeLetters.equals("AB"))){
            return 80;
        }
        if (donorTypeLetters.equals("B ") && (recipientTypeLetters.equals("B ") || recipientTypeLetters.equals("AB"))){
            return 80;
        }
        return 0;
    }

    private int calculateWeightCompatibility (int organWeight, int patientWeight){
        double calucatedWeightRatio = (double) organWeight / (patientWeight * 1000);
        if (calucatedWeightRatio >= 0.8 && calucatedWeightRatio<= 1.2){
            return 100;
        }
        if (calucatedWeightRatio >= 0.6 && calucatedWeightRatio < 0.8 || calucatedWeightRatio > 1.2 && calucatedWeightRatio <= 1.4){
            return 50;
        }
        return 0;
    }

    private int calculateHlaCompatibility (String organHla, String patientHla){
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

}