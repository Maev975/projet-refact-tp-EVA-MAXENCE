package org.iut.refactoring;

import org.junit.jupiter.api.*;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests unitaires pour la classe GestionPersonnel.
 */
public class GestionPersonnelTest {

    GestionPersonnel gp;

    /**
     * Prépare un contexte de test avant chaque méthode :
     * crée une instance de GestionPersonnel et ajoute trois employés de test.
     */
    @BeforeEach
    void setup() {
        gp = new GestionPersonnel();
        gp.ajouteSalarie("DEVELOPPEUR", "Alice", 50000, 6, "IT");
        gp.ajouteSalarie("CHEF DE PROJET", "Bob", 60000, 4, "RH");
        gp.ajouteSalarie("STAGIAIRE", "Charlie", 20000, 0, "IT");
    }

    /**
     * Recherche l'identifiant d'un employé par son nom.
     *
     * @param nom le nom de l'employé recherché
     * @return l'identifiant si trouvé, sinon {@code null}
     */
    private String getIdByName(String nom) {
        for (Object[] e : gp.employes) {
            if (nom.equals(e[2])) {
                return (String) e[0];
            }
        }
        return null;
    }

    /**
     * Vérifie que l'ajout d'employés fonctionne et produit des logs.
     */
    @Test
    void testAjouteSalarieCreeEmploye() {
        assertEquals(3, gp.employes.size());
        assertEquals(3, gp.salairesEmployes.size());
        assertTrue(gp.logs.stream().anyMatch(log -> log.contains("Ajout de l'employé")));
    }

    /**
     * Vérifie le calcul du salaire pour un développeur.
     */
    @Test
    void testCalculSalaireDeveloppeur() {
        String id = getIdByName("Alice");
        double salaire = gp.calculSalaire(id);
        // 50000 * 1.2 * 1.15 = 69000
        assertEquals(69000, salaire, 0.1);
    }

    /**
     * Vérifie le calcul du salaire pour un chef de projet.
     */
    @Test
    void testCalculSalaireChefDeProjet() {
        String id = getIdByName("Bob");
        double salaire = gp.calculSalaire(id);
        // 60000 * 1.5 * 1.1 + 5000 = 99500
        assertEquals(104000, salaire, 0.1);
    }

    /**
     * Vérifie le calcul du salaire pour un stagiaire.
     */
    @Test
    void testCalculSalaireStagiaire() {
        String id = getIdByName("Charlie");
        double salaire = gp.calculSalaire(id);
        // 20000 * 0.6 = 12000
        assertEquals(12000, salaire, 0.1);
    }

    /**
     * Vérifie le comportement lorsque l'employé n'existe pas.
     */
    @Test
    void testCalculSalaireEmployeInexistant() {
        double salaire = gp.calculSalaire("id_inexistant");
        assertEquals(0, salaire);
    }

    /**
     * Vérifie le calcul du bonus annuel pour un développeur.
     */
    @Test
    void testCalculBonusDeveloppeur() {
        String id = getIdByName("Alice");
        double bonus = gp.calculBonusAnnuel(id);
        // 50000 * 0.1 * 1.5 = 7500
        assertEquals(7500, bonus, 0.1);
    }

    /**
     * Vérifie le calcul du bonus annuel pour un chef de projet.
     */
    @Test
    void testCalculBonusChefDeProjet() {
        String id = getIdByName("Bob");
        double bonus = gp.calculBonusAnnuel(id);
        // 60000 * 0.2 * 1.3 = 15600
        assertEquals(15600, bonus, 0.1);
    }

    /**
     * Le stagiaire ne reçoit pas de bonus.
     */
    @Test
    void testCalculBonusStagiaire() {
        String id = getIdByName("Charlie");
        double bonus = gp.calculBonusAnnuel(id);
        assertEquals(0, bonus);
    }

    /**
     * Vérifie le comportement du calcul de bonus pour un identifiant invalide.
     */
    @Test
    void testCalculBonusEmployeInexistant() {
        double bonus = gp.calculBonusAnnuel("id_faux");
        assertEquals(0, bonus);
    }

    /**
     * Vérifie qu'un employé peut être promu et que sa fonction est mise à jour.
     */
    @Test
    void testAvancementEmploye() {
        String id = getIdByName("Alice");
        gp.avancementEmploye(id, "CHEF DE PROJET");
        Object[] emp = gp.employes.stream()
                .filter(e -> e[0].equals(id))
                .findFirst()
                .orElse(null);
        assertNotNull(emp);
        assertEquals("CHEF DE PROJET", emp[1]);
    }

    /**
     * Vérifie qu'aucune exception n'est levée pour un identifiant inexistant.
     */
    @Test
    void testAvancementEmployeInexistant() {
        gp.avancementEmploye("id_inexistant", "CHEF DE PROJET");
        // pas d'exception levée, juste un message d'erreur
        assertTrue(true);
    }

    /**
     * Vérifie la génération de rapport par salaire pour une division.
     */
    @Test
    void testGenerationRapportSalaire() {
        gp.generationRapport("SALAIRE", "IT");
        assertTrue(gp.logs.get(gp.logs.size() - 1).contains("Rapport généré"));
    }

    /**
     * Vérifie la génération de rapport par expérience pour une division.
     */
    @Test
    void testGenerationRapportExperience() {
        gp.generationRapport("EXPERIENCE", "IT");
        assertTrue(gp.logs.get(gp.logs.size() - 1).contains("Rapport généré"));
    }

    /**
     * Vérifie la génération de rapport global (par division).
     */
    @Test
    void testGenerationRapportDivision() {
        gp.generationRapport("DIVISION", null);
        assertTrue(gp.logs.get(gp.logs.size() - 1).contains("Rapport généré"));
    }


    /**
     * Vérifie la récupération des employés d'une division existante.
     */
    @Test
    void testGetEmployesParDivision() {
        var list = gp.getEmployesParDivision("IT");
        assertEquals(2, list.size());
    }

    /**
     * Vérifie la récupération pour une division sans employés.
     */
    @Test
    void testGetEmployesParDivisionVide() {
        var list = gp.getEmployesParDivision("Finance");
        assertTrue(list.isEmpty());
    }

    /**
     * Vérifie que l'affichage des logs ne vide pas la liste des logs.
     */
    @Test
    void testPrintLogsNeVidePasLesLogs() {
        int before = gp.logs.size();
        gp.printLogs();
        assertEquals(before, gp.logs.size());
    }
}
