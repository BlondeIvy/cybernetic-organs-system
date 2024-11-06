package com.cybernetic;

public class DiagnosticNode {
    String measurementType;
    double thresholdValue;
    String diagnosis;  // null for non-leaf nodes
    DiagnosticNode left;
    DiagnosticNode right;

    public DiagnosticNode(String measurementType, double thresholdValue) {
        this.measurementType = measurementType;
        this.thresholdValue = thresholdValue;
        this.diagnosis = null;
        this.left = null;
        this.right = null;
    }
}