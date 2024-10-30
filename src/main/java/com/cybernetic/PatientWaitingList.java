package com.cybernetic;

import java.util.LinkedList;
import java.util.Queue;

public class PatientWaitingList {
    private Queue<Patient> waitingList;

    public PatientWaitingList(){
        this.waitingList = new LinkedList<>();
    }

    public void addPatient(Patient patient) {
        waitingList.offer(patient);
    }

    public Patient removeNextPatient() {
        return waitingList.poll();
    }

    public boolean isEmpty() {
        return waitingList.isEmpty();
    }


    public void printWaitingList() {
        System.out.println("Current Waiting Queue: ");
        int position = 1;
        for(Patient patient : waitingList){
            System.out.print(position + ". ( " + patient.getName() + " )");
            if(position < waitingList.size()){
                System.out.print(" <------ ");
            }
            position++;
        }
        System.out.println();
    }

}