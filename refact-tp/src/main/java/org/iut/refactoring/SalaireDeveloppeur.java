package org.iut.refactoring;

public class SalaireDeveloppeur implements StrategieSalaire {
    private static final double COEFFICIENT_BASE = 1.2;
    private static final double BONUS_5_ANS = 1.15;
    private static final double BONUS_10_ANS = 1.05;
    private static final double TAUX_BONUS_ANNUEL = 0.1;
    private static final double MULTIPLICATEUR_BONUS_5_ANS = 1.5;

    @Override
    public double calculerSalaire(Employe employe) {
        double salaire = employe.getSalaireDeBase() * COEFFICIENT_BASE;

        if (employe.getExperience() > 5) {
            salaire *= BONUS_5_ANS;
        }

        if (employe.getExperience() > 10) {
            salaire *= BONUS_10_ANS;
        }

        return salaire;
    }

    @Override
    public double calculerBonusAnnuel(Employe employe) {
        double bonus = employe.getSalaireDeBase() * TAUX_BONUS_ANNUEL;

        if (employe.getExperience() > 5) {
            bonus *= MULTIPLICATEUR_BONUS_5_ANS;
        }

        return bonus;
    }
}