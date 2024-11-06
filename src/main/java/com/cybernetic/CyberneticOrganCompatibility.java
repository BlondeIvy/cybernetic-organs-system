package com.cybernetic;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

// Main compatibility checker class
public class CyberneticOrganCompatibility {
    private List<String> incompatibilityReasons;

    public CyberneticOrganCompatibility() {
        this.incompatibilityReasons = new ArrayList<>();
    }

    public boolean isCompatible(Patient patient, CyberneticOrgan organ, DiagnosticDecisionTree diagnosticTree) {

        incompatibilityReasons.clear();
        boolean isCompatible = true;

        Map<String, Double> measurements = patient.getAllMeasurements();
        Map<String, CyberneticOrgan.Range> requirements = organ.getRequirements();

        String diagnosis = diagnosticTree.diagnosePatient(measurements);
        if (!"Compatible".equals(diagnosis)) {
            incompatibilityReasons.add("Diagnostic Tree Result: " + diagnosis);
            isCompatible = false;
        }

        for (Map.Entry<String, CyberneticOrgan.Range> requirement : requirements.entrySet()) {
            String measurementType = requirement.getKey();
            Double value = measurements.get(measurementType);
            CyberneticOrgan.Range range = requirement.getValue();

            if (value == null || value < range.min || value > range.max) {
                incompatibilityReasons.add(String.format("%s out of range: %.2f (required: %.2f - %.2f)",
                        measurementType, value != null ? value : 0.0, range.min, range.max));
                isCompatible = false;
            }
        }
        return isCompatible;
    }

    public List<String> getIncompatibilityReasons() {
        return new ArrayList<>(incompatibilityReasons);
    }
}