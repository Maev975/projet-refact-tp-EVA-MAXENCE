package org.iut.refactoring;

public class Employe {
    private final String id;
    private String nom;
    private double salaireDeBase;
    private int experience;
    private String equipe;
    private StrategieSalaire strategieSalaire;

    public Employe(String nom, double salaireDeBase, int experience, String equipe, StrategieSalaire strategieSalaire) {
        this.id = java.util.UUID.randomUUID().toString();
        this.nom = nom;
        this.salaireDeBase = salaireDeBase;
        this.experience = experience;
        this.equipe = equipe;
        this.strategieSalaire = strategieSalaire;
    }

    public String getId() { return id; }
    public String getNom() { return nom; }
    public double getSalaireDeBase() { return salaireDeBase; }
    public int getExperience() { return experience; }
    public String getEquipe() { return equipe; }

    public double calculerSalaire() {
        return strategieSalaire.calculerSalaire(this);
    }

    public double calculerBonusAnnuel() {
        return strategieSalaire.calculerBonusAnnuel(this);
    }
    public void setStrategieSalaire(StrategieSalaire strategieSalaire) {
        this.strategieSalaire = strategieSalaire;
    }
}
