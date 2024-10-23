package com.cybernetic;

public class WaitingListNode {
    Patient patient;
    int priority;
    WaitingListNode next;

    public WaitingListNode(Patient patient, int priority) {
        this.patient = patient;
        this.priority = priority;
        this.next = null;
    }

}
