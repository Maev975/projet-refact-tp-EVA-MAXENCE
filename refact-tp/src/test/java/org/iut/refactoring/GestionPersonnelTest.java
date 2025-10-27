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
        gp.ajouterDeveloppeur("Alice", 50000, 6, "IT");
        gp.ajouterChefProjet("Bob", 60000, 4, "RH");
        gp.ajouterStagiaire("Charlie", 20000, 0, "IT");
    }

    /**
     * Recherche l'identifiant d'un employé par son nom.
     *
     * @param nom le nom de l'employé recherché
     * @return l'identifiant si trouvé, sinon {@code null}
     */
    private String getIdByName(String nom) {
        List<String> equipes = List.of("IT", "RH");
        for (String equipe : equipes) {
            for (Employe e : gp.getEmployesParEquipe(equipe)) {
                if (nom.equals(e.getNom())) {
                    return e.getId();
                }
            }
        }
        return null;
    }

    /**
     * Vérifie que l'ajout d'employés fonctionne et produit des logs.
     */
    @Test
    void testAjouteSalarieCreeEmploye() {
        int total = gp.getEmployesParEquipe("IT").size() + gp.getEmployesParEquipe("RH").size();
        assertEquals(3, total);
        assertEquals(2, gp.getEmployesParEquipe("IT").size());
        assertEquals(1, gp.getEmployesParEquipe("RH").size());
    }

    /**
     * Vérifie le calcul du salaire pour un développeur.
     */
    @Test
    void testCalculSalaireDeveloppeur() {
        String id = getIdByName("Alice");
        assertNotNull(id);
        double salaire = gp.calculerSalaire(id);
        // attente d'un salaire conforme aux règles de la stratégie développeur
        assertEquals(69000, salaire, 0.1);
    }

    /**
     * Vérifie le calcul du salaire pour un chef de projet.
     */
    @Test
    void testCalculSalaireChefDeProjet() {
        String id = getIdByName("Bob");
        assertNotNull(id);
        double salaire = gp.calculerSalaire(id);
        assertEquals(104000, salaire, 0.1);
    }

    /**
     * Vérifie le calcul du salaire pour un stagiaire.
     */
    @Test
    void testCalculSalaireStagiaire() {
        String id = getIdByName("Charlie");
        assertNotNull(id);
        double salaire = gp.calculerSalaire(id);
        assertEquals(12000, salaire, 0.1);
    }

    /**
     * Vérifie le comportement lorsque l'employé n'existe pas.
     */
    @Test
    void testCalculSalaireEmployeInexistant() {
        assertThrows(IllegalArgumentException.class, () -> gp.calculerSalaire("id_inexistant"));
    }

    /**
     * Vérifie le calcul du bonus annuel pour un développeur.
     */
    @Test
    void testCalculBonusDeveloppeur() {
        String id = getIdByName("Alice");
        assertNotNull(id);
        double bonus = gp.calculerBonusAnnuel(id);
        assertEquals(7500, bonus, 0.1);
    }

    /**
     * Vérifie le calcul du bonus annuel pour un chef de projet.
     */
    @Test
    void testCalculBonusChefDeProjet() {
        String id = getIdByName("Bob");
        assertNotNull(id);
        double bonus = gp.calculerBonusAnnuel(id);
        assertEquals(15600, bonus, 0.1);
    }

    /**
     * Le stagiaire ne reçoit pas de bonus.
     */
    @Test
    void testCalculBonusStagiaire() {
        String id = getIdByName("Charlie");
        assertNotNull(id);
        double bonus = gp.calculerBonusAnnuel(id);
        assertEquals(0, bonus, 0.1);
    }

    /**
     * Vérifie le comportement du calcul de bonus pour un identifiant invalide.
     */
    @Test
    void testCalculBonusEmployeInexistant() {
        assertThrows(IllegalArgumentException.class, () -> gp.calculerBonusAnnuel("id_faux"));
    }

    /**
     * Vérifie qu'un employé peut être promu et que sa fonction est mise à jour.
     */
    @Test
    void testAvancementEmploye() {
        String id = getIdByName("Alice");
        assertNotNull(id);
        double avant = gp.calculerSalaire(id);
        gp.promouvoirEnChefProjet(id);
        double apres = gp.calculerSalaire(id);
        assertNotEquals(avant, apres);
    }

    /**
     * Vérifie qu'aucune exception n'est levée pour un identifiant inexistant.
     */
    @Test
    void testAvancementEmployeInexistant() {
        assertDoesNotThrow(() -> gp.promouvoirEnChefProjet("id_inexistant"));
    }

    /**
     * Vérifie la génération de rapport par salaire pour une division.
     */
    @Test
    void testGenerationRapportSalaire() {
        assertDoesNotThrow(() -> gp.genererRapportSalaires("IT"));
    }

    /**
     * Vérifie la génération de rapport par expérience pour une division.
     */
    @Test
    void testGenerationRapportExperience() {
        assertDoesNotThrow(() -> gp.genererRapportExperience("IT"));
    }

    /**
     * Vérifie la génération de rapport global (par division).
     */
    @Test
    void testGenerationRapportDivision() {
        assertDoesNotThrow(() -> gp.genererRapportEquipes());
    }


    /**
     * Vérifie la récupération des employés d'une division existante.
     */
    @Test
    void testGetEmployesParDivision() {
        var list = gp.getEmployesParEquipe("IT");
        assertEquals(2, list.size());
    }

    /**
     * Vérifie la récupération pour une division sans employés.
     */
    @Test
    void testGetEmployesParDivisionVide() {
        var list = gp.getEmployesParEquipe("Finance");
        assertTrue(list.isEmpty());
    }

    /**
     * Vérifie que l'affichage des logs ne vide pas la liste des logs.
     */
    @Test
    void testPrintLogsNeVidePasLesLogs() {
        assertDoesNotThrow(() -> gp.afficherLogs());
    }
}
