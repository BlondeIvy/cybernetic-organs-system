package com.cybernetic;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class EmergencyCase implements Comparable<EmergencyCase> {
    private String caseId;
    private int severityLevel;
    private LocalDateTime registrationTime;

    public EmergencyCase(String caseId, Patient patient, int severityLevel,
                         LocalDateTime registrationTime) {
        this.caseId = caseId;
        this.severityLevel = severityLevel;
        this.registrationTime = registrationTime;
    }

    @Override
    public int compareTo(EmergencyCase other) {
        if (this.severityLevel != other.severityLevel) {
            return other.severityLevel - this.severityLevel;
        }
        return (int) this.registrationTime.until(other.registrationTime, ChronoUnit.MINUTES);
    }

    @Override
    public String toString() {
        long waitTime = registrationTime.until(LocalDateTime.now(), ChronoUnit.MINUTES);
        return String.format("%s (Severity %d, Wait time: %d min)",
                caseId, severityLevel, waitTime);
    }

    public String getCaseId() { return caseId; }
    public void setSeverityLevel(int severityLevel) { this.severityLevel = severityLevel; }
}
