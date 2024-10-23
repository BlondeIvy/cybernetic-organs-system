package com.cybernetic;

import lombok.Data;

@Data
public class WaitingList {
    private WaitingListNode head;

    public void addPatient(Patient patient, int priority) {
        WaitingListNode newNode = new WaitingListNode(patient, priority);
        if (head == null || priority > head.priority) {
            newNode.next = head;
            head = newNode;
        } else {
            WaitingListNode current = head;
            while (current.next != null && current.next.priority >= priority) {
                current = current.next;
            }
            newNode.next = current.next;
            current.next = newNode;
        }
    }

    public Patient removeHighestPriority() {
        if(head == null){
            return null;
        }
        Patient highestPriorityPatient = head.patient;
        head = head.next;
        return highestPriorityPatient;
    }

    public void updatePriority(String patientId, int newPriority) {
        WaitingListNode current = head;
        WaitingListNode prev = null;
        while(current != null && !current.patient.getId().equals(patientId)){
            prev = current;
            current = current.next;
        }
        if(current != null){
            if(prev != null){
                prev.next = current.next;
            } else {
                head = current.next;
            }
            addPatient(current.patient, newPriority);
        }
    }

    public void displayWaitingList() {
        WaitingListNode current = head;
        int position = 1;
        while(current != null){
            System.out.println(position + ". " + current.patient.getName() + " (Priority " + current.priority + ") ");
            current = current.next;
            position++;
        }
    }

    public int getPosition(String patientId) {
        WaitingListNode current = head;
        int position = 1;
        while(current != null){
            if(current.patient.getId().equals(patientId)){
                return position;
            }
            current = current.next;
            position++;
        }
        return -1;
    }

    public void removePatient(String patientId) {
        if(head == null){
            return;
        }
        if(head.patient.getId().equals(patientId)){
            head = head.next;
            return;
        }
        WaitingListNode current = head;
        WaitingListNode prev = null;
        while(current != null && !current.patient.getId().equals(patientId)){
            prev = current;
            current = current.next;
        }
        if(current != null){
            prev.next = current.next;
        }
    }

    public WaitingListNode getHead() {
        return head;
    }
}
