package com.hamma.tp6.exercice2;

public class Medicament {
    private String description;
    private MoisEnum moisPreemption;
    private int anneePreemption;
    private int quantite;

    public String getDescription() {
        return description;
    }

    public MoisEnum getMoisPreemption() {
        return moisPreemption;
    }

    public int getAnneePreemption() {
        return anneePreemption;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setMoisPreemption(MoisEnum moisPreemption) {
        this.moisPreemption = moisPreemption;
    }

    public void setAnneePreemption(int anneePreemption) {
        this.anneePreemption = anneePreemption;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    @Override
    public String toString() {
        return "Medicament{" +
                "description='" + description + '\'' +
                ", moisPreemption=" + moisPreemption +
                ", anneePreemption=" + anneePreemption +
                ", quantite=" + quantite +
                '}';
    }
}
