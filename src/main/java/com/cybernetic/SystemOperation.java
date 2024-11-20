package com.cybernetic;

import java.time.LocalDateTime;

public class SystemOperation {
    private String operationId;
    private String operationType;
    private LocalDateTime timestamp;
    private String description;
    private boolean isReversible;

    public SystemOperation(String operationId, String operationType,
                           String description, boolean isReversible) {
        this.operationId = operationId;
        this.operationType = operationType;
        this.timestamp = LocalDateTime.now();
        this.description = description;
        this.isReversible = isReversible;
    }

    @Override
    public String toString() {
        return String.format("%s: %s", operationId, operationType);
    }

    public String getOperationId() { return operationId; }
}