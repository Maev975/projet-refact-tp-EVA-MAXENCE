package org.iut.refactoring;

public class SalaireChefProjet implements StrategieSalaire {
    private static final double COEFFICIENT_BASE = 1.5;
    private static final double BONUS_3_ANS = 1.1;
    private static final double PRIME_FIXE = 5000.0;
    private static final double TAUX_BONUS_ANNUEL = 0.2;
    private static final double MULTIPLICATEUR_BONUS_3_ANS = 1.3;

    @Override
    public double calculerSalaire(Employe employe) {
        double salaire = employe.getSalaireDeBase() * COEFFICIENT_BASE;

        if (employe.getExperience() > 3) {
            salaire *= BONUS_3_ANS;
        }

        return salaire + PRIME_FIXE;
    }

    @Override
    public double calculerBonusAnnuel(Employe employe) {
        double bonus = employe.getSalaireDeBase() * TAUX_BONUS_ANNUEL;

        if (employe.getExperience() > 3) {
            bonus *= MULTIPLICATEUR_BONUS_3_ANS;
        }

        return bonus;
    }
}