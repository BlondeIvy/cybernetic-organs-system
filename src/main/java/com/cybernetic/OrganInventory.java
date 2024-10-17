package main.java.com.cybernetic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class OrganInventory {
    private final List<Organ> inventory;

    public OrganInventory() {
        this.inventory = new ArrayList<>();
    }

    public void addOrgan(Organ organ) {
        inventory.add(organ);
    }

    public List<Organ> getOrganList() {
        return Collections.unmodifiableList(inventory);
    }

    //ability to sort by multiple properties in order. name, model, compatibility using built-in sort
    public List<Organ> sortOrganByNameModelAndCompatibilityUsingBuiltInSort() {
        List <Organ> sortedList = new ArrayList<>(inventory);
        Collections.sort(sortedList, new Comparator<Organ>() {
            @Override
            public int compare(Organ o1, Organ o2) {
                int nameCompare = o1.getName().compareTo(o2.getName());
                if(nameCompare != 0){
                    return nameCompare;
                }
                int modelCompare = o1.getModel().compareTo(o2.getModel());
                if(modelCompare != 0){
                    return modelCompare;
                }
                return o1.getCompatibility().compareTo(o2.getCompatibility());
            }
        });
        return sortedList;
    }

    //ability to sort by multiple properties in order. name, model, compatibility using quicksort
    public List<Organ> quickSortOrganByNameModelAndCompatibility(List<Organ> unmodifiableOrganList) {
        List<Organ> organList = new ArrayList<>(unmodifiableOrganList);
        quickSort(organList, 0, organList.size()-1);
        return organList;
    }

    private void quickSort(List<Organ> list, int lo, int hi){
        if(lo < hi){
            int pi = partition(list, lo, hi);
            quickSort(list, lo, pi - 1);
            quickSort(list, pi + 1, hi);
        }
    }

    private int partition(List<Organ> list, int lo, int hi) {
        Organ pivot = list.get(hi);
        int i = lo - 1;
        for (int j = lo; j < hi; j++) {
            if (compareOrgans(list.get(j), pivot) <= 0) {
                i++;
                Collections.swap(list, i, j);
            }
        }
        Collections.swap(list, i + 1, hi);
        return i + 1;
    }

    private int compareOrgans(Organ o1, Organ o2){
        int nameCompare = o1.getName().compareTo(o2.getName());
        if(nameCompare != 0){
            return nameCompare;
        }
        int modelCompare = o1.getModel().compareTo(o2.getModel());
        if(modelCompare != 0){
            return modelCompare;
        }
        return o1.getCompatibility().compareTo(o2.getCompatibility());
    }

}