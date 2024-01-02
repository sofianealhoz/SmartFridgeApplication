package back;

import java.util.*;

public class Frigo{
    private List<Ingredient> ingredients;
    private List<String> allergies;

    public Frigo() {
        this.ingredients = new ArrayList<>();
        this.allergies = new ArrayList<>();
    }

    public List<Ingredient> getIngredients() {
        return new ArrayList<>(ingredients);
    }

    public void addIngredient(Ingredient ingredient) {
        ingredients.add(ingredient);
        System.out.println(ingredient.getQuantity() + " unit(s) of " + ingredient.getName() + " has(have) been added to the fridge.");
    }

    public void removeIngredient(Ingredient ingredient) {
        Iterator<Ingredient> iterator = ingredients.iterator();
        while (iterator.hasNext()) {
            Ingredient ingr = iterator.next();
            if (ingr.equals(ingredient)) {
                iterator.remove();
                System.out.println(ingredient.getQuantity() + " unit(s) of " + ingredient.getName() + " has(have) been removed from the fridge.");
                return;
            }
        }
        System.out.println(ingredient.getQuantity() + " unit(s) of " + ingredient.getName() + " not found in the fridge.");
    }

    public void displayContents() {
        System.out.println("Fridge contents:");
        for (Ingredient ingredient : ingredients) {
            System.out.println("- " + ingredient.getQuantity() + " unit(s) of " + ingredient.getName() +
                    " (Expiration date: " + ingredient.getExpirationDate() + ")");
        }
    }

    public void addAllergy(String allergy) {
        allergies.add(allergy);
        System.out.println("Allergy added: " + allergy);
    }

    public void removeAllergy(String allergy) {
        if (allergies.remove(allergy)) {
            System.out.println("Allergy removed: " + allergy);
        } else {
            System.out.println("Allergy not found: " + allergy);
        }
    }

    public void displayAllergies() {
        System.out.println("List of allergies:");
        for (String allergy : allergies) {
            System.out.println("- " + allergy);
        }
    }
    public void sortIngredients() {
        ingredients.sort(Comparator.comparing(Ingredient::getCategory)
                .thenComparing(Ingredient::getName)
                .thenComparing(Ingredient::getExpirationDate));
    }
    
}
