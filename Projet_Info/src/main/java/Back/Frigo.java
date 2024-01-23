package Back;
import java.time.LocalDate;
import java.util.*;

public class Frigo {
    // List to store Ingredients in the fridge
    private List<Ingredient> ingredients;
    // List to store allergies
    private final List<String> allergies;
    private User owner; // Reference to the User who owns this fridge
    private int fridgeId; // Unique ID for this fridge
    private DatabaseAccess databaseAccess;

     // Constructor: initializes empty lists for ingredients and allergies and adds the fridge to the database
    public Frigo(User owner, DatabaseAccess databaseAccess) {
	    this.ingredients = new ArrayList<>(DatabaseAccess.getIngredientsForFridge(this.fridgeId));
	    if (owner != null) {
	        this.allergies = owner.getAllergies();
	    } else {
	        this.allergies = new ArrayList<>(); // Initialize with an empty list
	    }
	    this.owner = owner;
	    this.databaseAccess = databaseAccess;
	    
	    // Add the fridge to the database
	    if (owner != null) {
	        boolean addedToFridgeDB = DatabaseAccess.addFridge(this);
	        if (!addedToFridgeDB) {
	            System.out.println("Failed to add fridge to the database.");
	        }
	    }
    }

    public void setFridgeId(int fridgeId) {
		this.fridgeId = fridgeId;
	}

	public int getId(){
        return databaseAccess.getFridgeIdFromDatabase(this.owner);
    }

    // Getter for the list of ingredients (returns a copy to prevent external modifications)
    public List<Ingredient> getIngredients() {
        return DatabaseAccess.getIngredientsForFridge(this.getId());
    }

    public User getOwner() {
        return owner;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients.clear(); // Clear the existing ingredients
        this.ingredients.addAll(ingredients); // Add the new ingredients
    }


    public void addIngredient(final Ingredient ingredient) {
            this.getIngredients();
            databaseAccess.addIngredient(ingredient.getName(), ingredient.getQuantity(), ingredient.getExpirationDate(), ingredient.getCategory(), ingredient.getUnit(), this.getId()); // Provide fridgeId
            this.setIngredients(this.getIngredients());
            System.out.println(ingredient.getQuantity() + " unit(s) of " + ingredient.getName() + " has(have) been added to the fridge."+ this.getId() + this.getIngredients());
    }
    
    public void removeIngredient(final Ingredient ingredient) {
        this.getIngredients();
        if (DatabaseAccess.hasIngredientInFridge(this.getId(), ingredient.getName())) {
            databaseAccess.deleteIngredientByName(ingredient.getName(), this.getId()); // Provide fridgeId
            this.setIngredients(this.getIngredients());
            ingredients.removeIf(ingr -> ingr.getName().equals(ingredient.getName()));
            System.out.println(ingredient.getQuantity() + " unit(s) of " + ingredient.getName() + " has(have) been removed from the fridge.");
        } else {
            // If the ingredient is not found, print a message
            System.out.println(ingredient.getQuantity() + " unit(s) of " + ingredient.getName() + " not found in the fridge.");
        }
    }
    
    public void updateIngredient() {
    	this.ingredients = DatabaseAccess.getIngredientsForFridge(fridgeId);
    }
    
    // Display the contents of the fridge (list of ingredients with quantities and expiration dates)
    public void displayContents() {
        this.getIngredients();
        System.out.println("Fridge contents:");
        for (final Ingredient ingredient : ingredients) {
            System.out.println("- " + ingredient.getQuantity() + " unit(s) of " + ingredient.getName() +
                    " (Expiration date: " + ingredient.getExpirationDate() + ")");
        }
    }
    
    public List<String> getAllergies() {
        return this.allergies; // Assuming 'allergies' is a List<String>
    }
    // Displays the list of allergies
    public void displayAllergies() {
        System.out.println("List of allergies:");
        for (final String allergy : allergies) {
            System.out.println("- " + allergy);
        }
    }

    // Sorts all the ingredients based on their expiration dates
    public List<Ingredient> sortIngredients() {
        this.getIngredients();
    	// Comparator for sorting ingredients by expiration date (nulls first)
        final Comparator<Ingredient> ingredientComparator = Comparator.comparing(Ingredient::getExpirationDate, Comparator.nullsFirst(Comparator.naturalOrder()));
        ingredients.sort(ingredientComparator);
        return ingredients;
    }
}
