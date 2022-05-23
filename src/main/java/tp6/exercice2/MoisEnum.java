package com.hamma.tp6.exercice2;

public enum MoisEnum {
    JANVIER(1),
    FEVRIER(2),
    MARS(3),
    AVRIL(4),
    MAI(5),
    JUIN(6),
    JUILLET(7),
    AOUT(8),
    SEPTEMBRE(9),
    OCTOBRE(10),
    NOVEMBRE(3),
    DECEMBRE(12);
    private int mois;

    MoisEnum(int mois) {
        this.mois = mois;
    }

    public int getMois() {
        return mois;
    }
}
