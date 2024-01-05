package back;

import java.util.*;

public class Frigo {
    private final List<Ingredient> ingredients;
    private final List<String> allergies;

    public Frigo() {
        this.ingredients = new ArrayList<>();
        this.allergies = new ArrayList<>();
    }

    public List<Ingredient> getIngredients() {
        return new ArrayList<>(ingredients);
    }

    public void addIngredient(final Ingredient ingredient) {
        if (ingredient != null && ingredient.getExpirationDate() != null) {
            ingredients.add(ingredient);
            System.out.println(ingredient.getQuantity() + " unit(s) of " + ingredient.getName() + " has(have) been added to the fridge.");
        }
    }

    public void removeIngredient(final Ingredient ingredient) {
        final Iterator<Ingredient> iterator = ingredients.iterator();
        while (iterator.hasNext()) {
            final Ingredient ingr = iterator.next();
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
        for (final Ingredient ingredient : ingredients) {
            System.out.println("- " + ingredient.getQuantity() + " unit(s) of " + ingredient.getName() +
                    " (Expiration date: " + ingredient.getExpirationDate() + ")");
        }
    }

    public void addAllergy(final String allergy) {
        allergies.add(allergy);
        System.out.println("Allergy added: " + allergy);
    }

    public void removeAllergy(final String allergy) {
        if (allergies.remove(allergy)) {
            System.out.println("Allergy removed: " + allergy);
        } else {
            System.out.println("Allergy not found: " + allergy);
        }
    }

    public void displayAllergies() {
        System.out.println("List of allergies:");
        for (final String allergy : allergies) {
            System.out.println("- " + allergy);
        }
    }

    public void sortIngredients() {
        final Comparator<Ingredient> ingredientComparator = Comparator.comparing(Ingredient::getExpirationDate, Comparator.nullsFirst(Comparator.naturalOrder()));
        ingredients.sort(ingredientComparator);
    }
}
