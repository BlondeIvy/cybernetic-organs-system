package com.cybernetic;

import java.util.ArrayList;
import java.util.List;

public class OrganCompatibilityAnalyzer {
    private List<Organ> organs;
    private List<Patient> patients;

    public OrganCompatibilityAnalyzer() {
        organs = new ArrayList<>();
        patients = new ArrayList<>();
    }

    public void addOrgan(Organ organ) {
        organs.add(organ);
    }

    public void addPatient(Patient patient) {
        patients.add(patient);
    }

    public int[][] createCompatibilityMatrix() {
        int[][] matrix = new int[organs.size()][patients.size() * 3];

        for(int i = 0; i < organs.size(); i++){
            for(int j = 0; j < patients.size(); j++){
                Organ organ = organs.get(i);
                Patient patient = patients.get(j);

                int bloodTypeCom = calculateBloodTypeCompatibility(organ.getBloodType(), patient.getBloodType());
                int weightCom = calculateWeightCompatibility(organ.getWeight(), patient.getWeight());
                int hlaCom = calculateHlaCompatibility(organ.getHlaType(), patient.getHlaType());

                matrix[i][j * 3] = bloodTypeCom;
                matrix[i][j * 3 + 1] = weightCom;
                matrix[i][j * 3 + 2] = hlaCom;
            }
        }
        return matrix;
    }

    private int calculateBloodTypeCompatibility(String donorType, String recipientType) {
        String donorTypeLetters = donorType.replaceAll("[+-]", "");
        String recipientTypeLetters = recipientType.replaceAll("[+-]", "");

        if(donorTypeLetters.equals(recipientTypeLetters)){
            return 100;
        }
        if(donorTypeLetters.equals("O")){
            return 100;
        }
        if(recipientTypeLetters.equals("AB")){
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

    private int calculateWeightCompatibility (int organWeight, int patientWeight){
        double calcuatedWeightRatio = (double) organWeight / (patientWeight * 1000);
        if(calcuatedWeightRatio >= 0.8 && calcuatedWeightRatio<= 1.2){
            return 100;
        }
        if(calcuatedWeightRatio >= 0.6 && calcuatedWeightRatio < 0.8 || calcuatedWeightRatio > 1.2 && calcuatedWeightRatio <= 1.4){
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

    public double[][] calculateWeightedCompatibility (double[] weights){
        int[][] compatibilityMatrix = createCompatibilityMatrix();
        double[][] resultMatrix = new double[organs.size()][patients.size()];
        for(int i = 0; i < organs.size(); i++){
            for(int j = 0; j < patients.size(); j++){
                double weightedSum = 0;
                for(int k = 0; k < 3; k++){
                    weightedSum += compatibilityMatrix[i][j * 3 + k] * weights[k];
                }
                resultMatrix[i][j] = weightedSum;
            }
        }
        return resultMatrix;
    }
    public void displayMatrix (int[][] matrix){
        System.out.println("Initial Compatibility Matrix:");
        System.out.print("    ");
        for(int i = 0; i < patients.size(); i++){
            System.out.printf("P%d  P%d  P%d  ", i + 1, i + 1, i + 1);
        }
        System.out.println();
        for(int i = 0; i < organs.size(); i++){
            System.out.printf("O%d  ", i + 1);
            for(int j = 0; j < patients.size() * 3; j++){
                System.out.printf("%-3d ", matrix[i][j]);
            }
            System.out.println();
        }
    }
    public void displayWeightMatrix (double[] weights){
        System.out.println("\nWeight Matrix:");
        for(double weight : weights){
            System.out.printf("%.1f ", weight);
        }
        System.out.println();
    }

    public void displayWeightedMatrix (double[][] matrix){
        System.out.println("\nFinal Weighted Compatibility Matrix:");
        System.out.print("    ");
        for(int i = 0; i < patients.size(); i++){
            System.out.printf("P%-4d", i + 1);
        }
        System.out.println();
        for(int i = 0; i < organs.size(); i++){
            System.out.printf("O%d  ", i + 1);
            for(int j = 0; j < patients.size(); j++){
                System.out.printf("%.1f  ", matrix[i][j]);
            }
            System.out.println();
        }
    }
}
