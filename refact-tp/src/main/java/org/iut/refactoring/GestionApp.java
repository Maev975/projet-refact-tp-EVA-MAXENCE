package org.iut.refactoring;

class GestionApp {
    public static void main(String[] args) {
        GestionPersonnel app = new GestionPersonnel();

        Employe alice = app.ajouterDeveloppeur("Alice", 50000, 6, "IT");
        app.ajouterChefProjet("Bob", 60000, 4, "RH");
        app.ajouterStagiaire("Charlie", 20000, 0, "IT");
        app.ajouterDeveloppeur("Dan", 55000, 12, "IT");
        
        String aliceId = alice.getId();
        
        System.out.println("Salaire de Alice: " + app.calculerSalaire(aliceId) + " €");
        System.out.println("Bonus de Alice: " + app.calculerBonusAnnuel(aliceId) + " €");
        
        app.genererRapportSalaires("IT");
        app.genererRapportEquipes();
        
        app.promouvoirEnChefProjet(aliceId);
        
        app.afficherLogs();
    }
}
