package Projet;

import java.time.LocalDate;

class Ingredient {
    private String nom;
    private LocalDate datePeremption;
    private int quantite;

    public Ingredient(String nom, LocalDate datePeremption, int quantite) {
        this.nom = nom;
        this.datePeremption = datePeremption;
        this.quantite = quantite;
    }

    public String getNom() {
        return nom;
    }

    public LocalDate getDatePeremption() {
        return datePeremption;
    }

    public int getQuantite() {
        return quantite;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Ingredient ingredient = (Ingredient) obj;
        return nom.equals(ingredient.nom);
    }

    @Override
    public int hashCode() {
        return nom.hashCode();
    }
}
