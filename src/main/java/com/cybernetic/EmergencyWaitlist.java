package com.cybernetic;

import java.util.PriorityQueue;
import java.util.TreeMap;

public class EmergencyWaitlist {
    private PriorityQueue<EmergencyCase> priorityQueue;
    private TreeMap<String, EmergencyCase> bstIndex;

    public EmergencyWaitlist() {
        this.priorityQueue = new PriorityQueue<>();
        this.bstIndex = new TreeMap<>();
    }

    public void addEmergencyCase(EmergencyCase emergencyCase) {
        priorityQueue.offer(emergencyCase);
        bstIndex.put(emergencyCase.getCaseId(), emergencyCase);
    }

    public EmergencyCase getNextUrgentCase() {
        EmergencyCase nextCase = priorityQueue.poll();
        if (nextCase != null) {
            bstIndex.remove(nextCase.getCaseId());
        }
        return nextCase;
    }

    public void updateCaseSeverity(String caseId, int newLevel) {
        EmergencyCase existingCase = bstIndex.get(caseId);
        if (existingCase != null) {
            priorityQueue.remove(existingCase);
            existingCase.setSeverityLevel(newLevel);
            priorityQueue.offer(existingCase);
        }
    }

}