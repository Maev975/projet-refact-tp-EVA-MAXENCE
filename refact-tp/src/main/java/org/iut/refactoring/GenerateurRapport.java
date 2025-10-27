package org.iut.refactoring;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GenerateurRapport {
    private final LogService logService;

    public GenerateurRapport(LogService logService) {
        this.logService = logService;
    }

    public void genererRapportSalaires(List<Employe> employes, String equipeFiltre) {
        System.out.println("=== RAPPORT: SALAIRE ===");

        employes.stream()
                .filter(e -> equipeFiltre == null || equipeFiltre.isEmpty()
                        || e.getEquipe().equals(equipeFiltre))
                .forEach(e -> System.out.printf("%s: %.2f €%n",
                        e.getNom(),
                        e.calculerSalaire()));

        logService.log("Rapport salaires généré");
    }

    public void genererRapportExperience(List<Employe> employes, String equipeFiltre) {
        System.out.println("=== RAPPORT: EXPERIENCE ===");

        employes.stream()
                .filter(e -> equipeFiltre == null || equipeFiltre.isEmpty()
                        || e.getEquipe().equals(equipeFiltre))
                .forEach(e -> System.out.printf("%s: %d années%n",
                        e.getNom(),
                        e.getExperience()));

        logService.log("Rapport expérience généré");
    }

    public void genererRapportEquipes(List<Employe> employes) {
        System.out.println("=== RAPPORT: EQUIPES ===");

        Map<String, Long> compteur = new HashMap<>();
        employes.forEach(e ->
                compteur.put(e.getEquipe(),
                        compteur.getOrDefault(e.getEquipe(), 0L) + 1)
        );

        compteur.forEach((equipe, count) ->
                System.out.printf("%s: %d employés%n", equipe, count)
        );

        logService.log("Rapport équipes généré");
    }
}