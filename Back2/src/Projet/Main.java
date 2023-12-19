package Projet;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Frigo monFrigo = new Frigo();
        monFrigo.ajouterIngredient(new Ingredient("Lait", LocalDate.of(2023, 12, 1), 2));
        monFrigo.ajouterIngredient(new Ingredient("Oeufs", LocalDate.of(2023, 11, 30), 12));
        monFrigo.ajouterIngredient(new Ingredient("Yaourt", LocalDate.of(2023, 12, 5), 4));

        // Fetch and display the current ingredients
        monFrigo.afficherContenu();

        // Use the RecipeFinder to find recipes based on the ingredients
        RecipeFinder.searchRecipes(monFrigo.getCurrentIngredients());

        // Additional logic as needed...
    }
}

