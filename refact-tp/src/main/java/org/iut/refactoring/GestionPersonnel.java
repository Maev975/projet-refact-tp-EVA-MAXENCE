package org.iut.refactoring;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GestionPersonnel {

    private final Map<String, Employe> employes;
    private final LogService logService;
    private final GenerateurRapport generateurRapport;

    public GestionPersonnel() {
        this.employes = new HashMap<>();
        this.logService = new LogService();
        this.generateurRapport = new GenerateurRapport(logService);
    }

    public Employe ajouterDeveloppeur(String nom, double salaireBase,
                                      int experience, String equipe) {
        Employe employe = new Employe(nom, salaireBase, experience, equipe,
                new SalaireDeveloppeur());
        employes.put(employe.getId(), employe);
        logService.log("Ajout de l'employe: " + nom);
        return employe;
    }

    public Employe ajouterChefProjet(String nom, double salaireBase,
                                     int experience, String equipe) {
        Employe employe = new Employe(nom, salaireBase, experience, equipe,
                new SalaireChefProjet());
        employes.put(employe.getId(), employe);
        logService.log("Ajout de l'employe: " + nom);
        return employe;
    }

    public Employe ajouterStagiaire(String nom, double salaireBase,
                                    int experience, String equipe) {
        Employe employe = new Employe(nom, salaireBase, experience, equipe,
                new SalaireStagiaire());
        employes.put(employe.getId(), employe);
        logService.log("Ajout de l'employe: " + nom);
        return employe;
    }

    public double calculerSalaire(String employeId) {
        Employe employe = employes.get(employeId);
        if (employe == null) {
            throw new IllegalArgumentException("Employé introuvable");
        }
        return employe.calculerSalaire();
    }

    public double calculerBonusAnnuel(String employeId) {
        Employe employe = employes.get(employeId);
        if (employe == null) {
            throw new IllegalArgumentException("Employe introuvable");
        }
        return employe.calculerBonusAnnuel();
    }

    public void promouvoirEnChefProjet(String employeId) {
        Employe employe = employes.get(employeId);
        if (employe == null) {
            System.err.println("ERREUR: Employe introuvable");
            return;
        }

        employe.setStrategieSalaire(new SalaireChefProjet());
        logService.log("Employe promu: " + employe.getNom());
        System.out.println("Employe promu avec succee!");
    }

    public void promouvoirEnDeveloppeur(String employeId) {
        Employe employe = employes.get(employeId);
        if (employe == null) {
            System.err.println("ERREUR: Employe introuvable");
            return;
        }

        employe.setStrategieSalaire(new SalaireDeveloppeur());
        logService.log("Employe promu: " + employe.getNom());
        System.out.println("Employe promu avec succes!");
    }

    public void genererRapportSalaires(String equipeFiltre) {
        generateurRapport.genererRapportSalaires(
                new ArrayList<>(employes.values()),
                equipeFiltre
        );
    }

    public void genererRapportExperience(String equipeFiltre) {
        generateurRapport.genererRapportExperience(
                new ArrayList<>(employes.values()),
                equipeFiltre
        );
    }

    public void genererRapportEquipes() {
        generateurRapport.genererRapportEquipes(
                new ArrayList<>(employes.values())
        );
    }

    public List<Employe> getEmployesParEquipe(String equipe) {
        return employes.values().stream()
                .filter(e -> e.getEquipe().equals(equipe))
                .toList();
    }

    public void afficherLogs() {
        logService.afficherLogs();
    }
}