package org.iut.refactoring;

public class SalaireStagiaire implements StrategieSalaire {
    private static final double COEFFICIENT_BASE = 0.6;

    @Override
    public double calculerSalaire(Employe employe) {
        return employe.getSalaireDeBase() * COEFFICIENT_BASE;
    }

    @Override
    public double calculerBonusAnnuel(Employe employe) {
        return 0.0;
    }
}