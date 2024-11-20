package com.cybernetic;

import java.util.ArrayList;
import java.util.List;

public class TransplantHistory {
    private TransplantRecord head;

    public void addTransplantRecordAtBeginning(TransplantRecord record) {
        record.next = head;
        head = record;
    }

    public List<TransplantRecord> getRecentTransplants(int count) {
        List<TransplantRecord> recent = new ArrayList<>();
        TransplantRecord current = head;
        int counter = 0;

        while (current != null && counter < count) {
            recent.add(current);
            current = current.next;
            counter++;
        }
        return recent;
    }

}
