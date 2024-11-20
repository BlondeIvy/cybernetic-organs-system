package com.cybernetic;

import java.time.LocalDateTime;

public class TransplantRecord {
    private String operationId;
    private String patientId;
    private String organId;
    private LocalDateTime timestamp;
    private String surgeon;
    private String outcome;
    TransplantRecord next;

    public TransplantRecord(String operationId, String patientId, String organId,
                            String surgeon, String outcome) {
        this.operationId = operationId;
        this.patientId = patientId;
        this.organId = organId;
        this.timestamp = LocalDateTime.now();
        this.surgeon = surgeon;
        this.outcome = outcome;
    }

    @Override
    public String toString() {
        return String.format("%s: %s (%s)", operationId, patientId, outcome);
    }

}