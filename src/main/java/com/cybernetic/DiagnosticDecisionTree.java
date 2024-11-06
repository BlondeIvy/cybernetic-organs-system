package com.cybernetic;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DiagnosticDecisionTree {
    private DiagnosticNode root;
    private List<String> diagnosticPath;

    public DiagnosticDecisionTree() {
        this.diagnosticPath = new ArrayList<>();
    }

    // Add this getter method that was missing
    public List<String> getDiagnosticPath() {
        return new ArrayList<>(diagnosticPath);  // Return a copy for encapsulation
    }

    public void addDiagnosticCriteria(String measurementType, double threshold, String diagnosis) {
       if (root == null){
           root = new DiagnosticNode(measurementType, threshold);
           root.diagnosis = diagnosis;
           return;
       }
       DiagnosticNode current = root;
       while(true) {
           if ( threshold < current.thresholdValue) {
               if (current.left == null) {
                   current.left = new DiagnosticNode(measurementType, threshold);
                   current.left.diagnosis = diagnosis;
                   break;
               }
               current = current.left;
           } else {
               if (current.right == null) {
                   current.right = new DiagnosticNode(measurementType, threshold);
                   current.right.diagnosis = diagnosis;
                   break;
               }
               current = current.right;
           }
       }
    }

    // Method to diagnose patient
    public String diagnosePatient(Map<String, Double> measurements) {
        diagnosticPath.clear();  // Clear previous diagnostic path
        return diagnosePatientRecursive(root, measurements, 1);
    }

    private String diagnosePatientRecursive(DiagnosticNode node, Map<String, Double> measurements, int level) {
        if (node == null){
            return "Inconclusive";
        }

        Double measurement = measurements.get(node.measurementType);
        if (measurement == null) {
            return "Missing measurement: " + node.measurementType;
        }

        diagnosticPath.add(String.format("Level %d: %s = %.1f %s %.1f",
                level, node.measurementType, measurement,
                measurement >= node.thresholdValue ? "≥" : "<", node.thresholdValue));

        if (measurement < node.thresholdValue) {
            return node.left == null ? node.diagnosis : diagnosePatientRecursive(node.left, measurements, level + 1);
        } else {
            return node.right == null ? node.diagnosis : diagnosePatientRecursive(node.right, measurements, level + 1);
        }
    }

    // Utility method to print tree structure
    public void printTree() {
        System.out.println("\nDiagnostic Tree Structure:");
        printTreeRec(root, "", true);
    }

    private void printTreeRec(DiagnosticNode node, String prefix, boolean isLeft) {
        if (node == null) return;

        System.out.print(prefix);
        System.out.print(isLeft ? "└── " : "├── ");
        System.out.println(node.measurementType + " (" + node.thresholdValue + ")" + (node.diagnosis != null ? " -> " + node.diagnosis : ""));

        printTreeRec(node.left, prefix + (isLeft ? "    " : "│   "), false);
        printTreeRec(node.right, prefix + (isLeft ? "    " : "│   "), true);
    }
}