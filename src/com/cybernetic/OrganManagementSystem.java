package com.cybernetic;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class OrganManagementSystem {
    private List<Organ> organs;
    private List<Patient> patients;
    private OrganCompatibilityAnalyzer analyzer;

    public OrganManagementSystem(List<Organ> organs, List<Patient> patients) {
        this.organs = organs;
        this.patients = patients;
        this.analyzer = new OrganCompatibilityAnalyzer();
        organs.forEach(analyzer::addOrgan);
        patients.forEach(analyzer::addPatient);
    }

    public Set<String> getUniqueBloodTypes() {
        return Stream.concat(
                organs.stream().map(Organ::getBloodType),
                patients.stream().map(Patient::getBloodType)
        ). collect(Collectors.toSet());
    }

    public Map<String, List<Patient>> groupPatientsByBloodType() {
       return patients.stream()
               .collect(Collectors.groupingBy(Patient::getBloodType));
    }

    public List<Organ> sortOrgansByWeight() {
        return organs.stream()
                .sorted(Comparator.comparingInt(Organ::getWeight))
                .collect(Collectors.toList());
    }

    public List<Organ> getTopCompatibleOrgans(Patient patient, int n){
        List<Organ> compatibleOrgans = organs.stream()
                    .sorted(Comparator.comparingDouble(organ -> analyzer.calculateCompatibilityScore((Organ) organ, patient)).reversed())
                    .limit(n)
                    .collect(Collectors.toList());
            System.out.println( compatibleOrgans.size()+ " compatible organs found.");
            return compatibleOrgans;
    }


}
