package Projet;

import java.time.LocalDate; // Add this import for LocalDate

public class Main {
    public static void main(String[] args) {
        // Exemple d'utilisation de la classe Frigo
        Frigo monFrigo = new Frigo();

        Ingredient lait = new Ingredient("Lait", LocalDate.of(2023, 12, 1), 2);
        Ingredient oeufs = new Ingredient("Oeufs", LocalDate.of(2023, 11, 30), 12);
        Ingredient yaourt = new Ingredient("Yaourt", LocalDate.of(2023, 12, 5), 4);

        monFrigo.ajouterIngredient(lait);
        monFrigo.ajouterIngredient(oeufs);
        monFrigo.ajouterIngredient(yaourt);

        monFrigo.afficherContenu();

        monFrigo.retirerIngredient(oeufs);
        monFrigo.afficherContenu();

        monFrigo.ajouterAllergie("Noix");
        monFrigo.ajouterAllergie("Gluten");

        monFrigo.afficherAllergies();
    }
}
