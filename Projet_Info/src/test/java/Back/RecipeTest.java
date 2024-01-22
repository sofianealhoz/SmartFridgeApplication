package Back;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.Arrays;
import java.util.List;

public class RecipeTest {

    @Test
    public void testGetterAndSetter() {
        // Sample data for testing
        String name = "Test Recipe";
        String imageUrl = "http://example.com/image.jpg";
        
        // Creating Ingredient objects
        Ingredient ingredient1 = new Ingredient("Ingredient1", null, 0, null);
        Ingredient ingredient2 = new Ingredient("Ingredient2", null, 0, null);

        List<Ingredient> ingredients = Arrays.asList(ingredient1, ingredient2);
        List<String> instructions = Arrays.asList("Step 1", "Step 2", "Step 3");
        NutritionInfo nutritionInfo = new NutritionInfo(300, 15, 25, 35, 8, 20, 600);
        List<String> allergens = Arrays.asList("Allergen1", "Allergen2");

        Recipe recipe = new Recipe(name, imageUrl, ingredients, instructions, nutritionInfo, allergens);

        // Test getters
        assertEquals(name, recipe.getName());
        assertEquals(imageUrl, recipe.getImageUrl());
        assertEquals(ingredients, recipe.getIngredients());
        assertEquals(instructions, recipe.getInstructions());
        assertEquals(nutritionInfo, recipe.getNutritionInfo());
        assertEquals(allergens, recipe.getAllergens());

        // Test setters
        List<String> newInstructions = Arrays.asList("Step A", "Step B", "Step C");
        recipe.setInstructions(newInstructions);
        assertEquals(newInstructions, recipe.getInstructions());

        NutritionInfo newNutritionInfo = new NutritionInfo(400, 20, 30, 40, 10, 25, 700);
        recipe.setNutritionInfo(newNutritionInfo);
        assertEquals(newNutritionInfo, recipe.getNutritionInfo());

        List<String> newAllergens = Arrays.asList("AllergenX", "AllergenY");
        recipe.setAllergens(newAllergens);
        assertEquals(newAllergens, recipe.getAllergens());
    }
    
    @Test
    public void testGetImagePath() {
        // Replace "validImageUrl" with an actual valid image URL
        String validImageUrl = "https://via.placeholder.com/150";
        
        // Create a testable Recipe instance
        Recipe recipe = new Recipe("Test Recipe", validImageUrl, null, null, null, null);

        // Call the getImagePath method
        String imagePath = recipe.getImagePath();

        // Assert that the returned path is not null or empty
        assertNotNull("Image path should not be null", imagePath);
        assertFalse("Image path should not be empty", imagePath.isEmpty());
    }
}


