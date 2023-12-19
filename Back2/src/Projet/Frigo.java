package Projet;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Frigo {
    private List<Ingredient> ingredients;
    private List<String> allergies;

    public Frigo() {
        this.ingredients = new ArrayList<>();
        this.allergies = new ArrayList<>();
    }

    public void ajouterIngredient(Ingredient ingredient) {
        ingredients.add(ingredient);
        System.out.println(ingredient.getQuantite() + " unité(s) de " + ingredient.getNom() + " a(ont) été ajouté(s) au frigo.");
    }

    public void retirerIngredient(Ingredient ingredient) {
        Iterator<Ingredient> iterator = ingredients.iterator();
        while (iterator.hasNext()) {
            Ingredient ingr = iterator.next();
            if (ingr.equals(ingredient)) {
                iterator.remove();
                System.out.println(ingredient.getQuantite() + " unité(s) de " + ingredient.getNom() + " a(ont) été retiré(s) du frigo.");
                return;
            }
        }
        System.out.println(ingredient.getQuantite() + " unité(s) de " + ingredient.getNom() + " n'a(ont) pas été trouvé(s) dans le frigo.");
    }

    public void afficherContenu() {
        System.out.println("Contenu du frigo :");
        for (Ingredient ingredient : ingredients) {
            System.out.println("- " + ingredient.getQuantite() + " unité(s) de " + ingredient.getNom() +
                    " (Péremption le " + ingredient.getDatePeremption() + ")");
        }
    }

    public void ajouterAllergie(String allergie) {
        allergies.add(allergie);
        System.out.println("Allergie ajoutée : " + allergie);
    }

    public void retirerAllergie(String allergie) {
        if (allergies.remove(allergie)) {
            System.out.println("Allergie retirée : " + allergie);
        } else {
            System.out.println("Allergie non trouvée : " + allergie);
        }
    }

    public void afficherAllergies() {
        System.out.println("Liste d'allergies :");
        for (String allergie : allergies) {
            System.out.println("- " + allergie);
        }
    }
}