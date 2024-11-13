package com.cybernetic;

import java.util.ArrayList;
import java.util.Collections;

public class OrganInventory {

    private ArrayList<CyberneticOrgan> organs;
    private final int maxCapacity = 1000;

    public OrganInventory() {
        this.organs = new ArrayList<>();
    }

    public void addOrgan(CyberneticOrgan organ){
        if(organs.size() >= maxCapacity){
            throw new IllegalArgumentException("Inventory is at Maximum Capacity.");
        }
        organs.add(organ);
    }

    public ArrayList<CyberneticOrgan> sortByPowerLevel() {
        ArrayList<CyberneticOrgan> sorted = new ArrayList<>(organs);
        quickSort(sorted, 0, sorted.size()-1);
        return sorted;
    }

    public ArrayList<CyberneticOrgan> sortByManufactureDate() {
        ArrayList<CyberneticOrgan> sorted = new ArrayList<>(organs);
        mergeSort(sorted, 0, sorted.size()-1);
        return sorted;
    }

    public ArrayList<CyberneticOrgan> sortByCompatibilityScore() {
        ArrayList<CyberneticOrgan> sorted = new ArrayList<>(organs);
        bubbleSort(sorted);
        return sorted;
    }

    private void quickSort(ArrayList<CyberneticOrgan> list, int lo, int hi){
        if(lo < hi){
            int pi = partition(list, lo, hi);
            quickSort(list, lo, pi - 1);
            quickSort(list, pi+1, hi);
        }
    }
    private int partition(ArrayList<CyberneticOrgan> list, int lo, int hi){
        int pivot = list.get(hi).getPowerLevel();
        int i = lo - 1;

        for(int j = lo; j < hi; j++){
            if(list.get(j).getPowerLevel() >= pivot){
                i++;
                Collections.swap(list, i, j);
            }
        }
    Collections.swap(list, i + 1, hi);
    return i +1;
    }

    private void mergeSort(ArrayList<CyberneticOrgan> list, int left, int right){
        if(left < right) {
            int mid = (left + right) / 2;
            mergeSort(list, left, mid);
            mergeSort(list, mid + 1, right);
            merge(list, left, mid, right);
        }
    }
    private void merge(ArrayList<CyberneticOrgan> list, int left, int mid, int right){
        ArrayList<CyberneticOrgan> temp = new ArrayList<>();
        int i = left;
        int j = mid + 1;

        while(i <= mid && j <= right){
            if(list.get(i).getManufactureDate().isAfter(list.get(j).getManufactureDate())){
                temp.add(list.get(i++));
            } else {
                temp.add(list.get(j++));
        }
    }
        while(i <= mid){
            temp.add(list.get(i++));
        }
        while(j <= right){
            temp.add(list.get(j++));
        }

        for(i = 0; i < temp.size(); i ++){
            list.set(left + i, temp.get(i));
        }
    }

    private void bubbleSort(ArrayList<CyberneticOrgan> list){
        int size = list.size();
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size - 1; j++){
                if(list.get(j).getCompatibilityScore() < list.get(j + 1).getCompatibilityScore()){
                    Collections.swap(list, j, j + 1);
                }
            }
        }
    }
}
