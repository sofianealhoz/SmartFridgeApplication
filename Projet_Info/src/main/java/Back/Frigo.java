package Back;

import java.time.LocalDate;
import java.util.*;

public class Frigo {
	// List to store Ingredients in the fridge
    private final List<Ingredient> ingredients;
    // List to store allergies
    private final List<String> allergies;
    
    // Constructor : initializes empty lists for ingredients and allergies
    public Frigo() {
        this.ingredients = new ArrayList<>(DatabaseAccess.getIngredientsList());
        this.allergies = new ArrayList<>();
    
        // Initialize the ingredients list with default ingredients
        ingredients.add(new Ingredient("Flour", LocalDate.of(2024, 6, 30), 500, "Bakery"));
        ingredients.add(new Ingredient("Sugar", LocalDate.of(2024, 12, 31), 200, "Bakery"));
    }
    
    public List<String> getAllergies() {
		return allergies;
	}

	// Getter for the list of ingredients 
    public List<Ingredient> getIngredientsFrigo() {
        return ingredients;
    }
    
    public List<Ingredient> getIngredients() {
        return DatabaseAccess.getIngredientsList();
    }
    
    // Adds a new ingredient to the fridge and prints a message
    public void addIngredient(final Ingredient ingredient) {
        if (ingredient != null && ingredient.getExpirationDate() != null) {
            // Check if the ingredient is already present in the fridge
            for (final Ingredient ingr : ingredients) {
                if (ingr.getName().equals(ingredient.getName())) {
                    // Increment the quantity by adding the new quantity
                    ingr.setQuantity(ingr.getQuantity() + ingredient.getQuantity());
                    System.out.println(ingredient.getQuantity() + " unit(s) of " + ingredient.getName() + " has(have) been added to the fridge.");
                    return;
                }
            }
            // If the ingredient is not already present, add it to the fridge
            ingredients.add(ingredient);
            System.out.println(ingredient.getQuantity() + " unit(s) of " + ingredient.getName() + " has(have) been added to the fridge.");
        }
    }
    
    // Checks if an ingredient is present in the fridge
    public boolean hasIngredient(String name) {
    	for (Ingredient ingredient : ingredients) {
            if (ingredient.getName().equals(name)) {
                return true;
            }
        }
    	return false;
    }
    
    // Removes a specified ingredient from the fridge and prints a message
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
        // If the ingredient is not found, print a message
        System.out.println(ingredient.getQuantity() + " unit(s) of " + ingredient.getName() + " not found in the fridge.");
    }
    
    // Removes a specified ingredient by name. 
    public void removeIngredientByName(String name) {
    	DatabaseAccess.callDeleteIngredientByName(name);
    }
    
    // Display the contents of the fridge (list of ingredients with quantities and expiration dates)
    public void displayContents() {
        System.out.println("Fridge contents:");
        for (final Ingredient ingredient : ingredients) {
            System.out.println("- " + ingredient.getQuantity() + " unit(s) of " + ingredient.getName() +
                    " (Expiration date: " + ingredient.getExpirationDate() + ")");
        }
    }
    
    // Adds a new allergy to the list and prints a message
    public void addAllergy(final String allergy) {
        allergies.add(allergy);
        System.out.println("Allergy added: " + allergy);
    }
    
    // Removes a specified allergy from the list and prints a message
    public void removeAllergy(final String allergy) {
        if (allergies.remove(allergy)) {
            System.out.println("Allergy removed: " + allergy);
        } else {
            System.out.println("Allergy not found: " + allergy);
        }
    }
    
    // Displays the list of allergies
    public void displayAllergies() {
        System.out.println("List of allergies:");
        for (final String allergy : allergies) {
            System.out.println("- " + allergy);
        }
    }

    // Sorts all the ingredients based on their expiration dates
    public void sortIngredients() {
    	// Comparator for sorting ingredients by expiration date (nulls first)
        final Comparator<Ingredient> ingredientComparator = Comparator.comparing(Ingredient::getExpirationDate, Comparator.nullsFirst(Comparator.naturalOrder()));
        ingredients.sort(ingredientComparator);
    }
}
