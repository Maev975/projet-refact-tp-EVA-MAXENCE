package org.iut.refactoring;

public class Employe {
    private final String id;
    private String type;
    private String nom;
    private double salaireDeBase;
    private int experience;
    private String equipe;

    public Employe(String type, String nom, double salaireDeBase, int experience, String equipe) {
        this.id = java.util.UUID.randomUUID().toString();
        this.type = type;
        this.nom = nom;
        this.salaireDeBase = salaireDeBase;
        this.experience = experience;
        this.equipe = equipe;
    }

    public String getId() { return id; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public String getNom() { return nom; }
    public double getSalaireDeBase() { return salaireDeBase; }
    public int getExperience() { return experience; }
    public String getEquipe() { return equipe; }


}
