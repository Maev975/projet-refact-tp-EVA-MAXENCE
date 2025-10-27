package org.iut.refactoring;

public class SalaireStandard implements StrategieSalaire {
    @Override
    public double calculerSalaire(Employe employe) {
        return employe.getSalaireDeBase();
    }

    @Override
    public double calculerBonusAnnuel(Employe employe) {
        return 0.0;
    }
}