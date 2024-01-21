package Back;

import static org.junit.Assert.*;

import org.json.JSONObject;
import org.junit.Test;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class RecipeFinderTest {

    @Test
    public void testSearchRecipes_NoIngredients() {
        // Test when no ingredients are specified
        List<Recipe> recipes = RecipeFinder.searchRecipes(Arrays.asList());
        assertNotNull(recipes);
        assertEquals(2, recipes.size()); // 2 --> the default recipes  that are included
    }

    @Test
    public void testSearchRecipes_WithIngredients() {
        // Test searching for recipes with specific ingredients
        List<Ingredient> searchIngredients = Arrays.asList(
                new Ingredient("Flour", LocalDate.of(2024, 6, 30), 500, "Bakery"),
                new Ingredient("Sugar", LocalDate.of(2024, 12, 31), 200, "Bakery")
        );

        List<Recipe> recipes = RecipeFinder.searchRecipes(searchIngredients);
        assertNotNull(recipes);
        assertTrue(recipes.size() > 0);

        // Ensure that the recipes contain the expected information
        for (Recipe recipe : recipes) {
            assertNotNull(recipe.getName());
            assertNotNull(recipe.getImageUrl());
            assertNotNull(recipe.getIngredients());
            assertNotNull(recipe.getInstructions());
            assertNotNull(recipe.getNutritionInfo());
            assertNotNull(recipe.getAllergens());
        }
    }

    @Test
    public void testFetchRecipeDetails() {
        // Test fetching detailed information for a specific recipe
        int recipeId = 12345; // Replace with a valid recipe ID for testing
        Recipe detailedRecipe = RecipeFinder.fetchRecipeDetails(recipeId);
        assertNotNull(detailedRecipe);
        assertNotNull(detailedRecipe.getName());
        assertNotNull(detailedRecipe.getImageUrl());
        assertNotNull(detailedRecipe.getIngredients());
        assertNotNull(detailedRecipe.getInstructions());
        assertNotNull(detailedRecipe.getNutritionInfo());
        assertNotNull(detailedRecipe.getAllergens());
    }

    @Test
    public void testExtractNutritionInfo() {
        // Test extracting nutrition information from a JSON object
        JSONObject nutritionObject = new JSONObject("{\"calories\": 200, \"fat\": 10, \"protein\": 5, \"carbohydrates\": 20, \"fiber\": 2, \"sugar\": 8, \"sodium\": 300}");
        NutritionInfo nutritionInfo = RecipeFinder.extractNutritionInfo(nutritionObject);

        assertNotNull(nutritionInfo);
        assertEquals(200, nutritionInfo.getCalories(), 0.01);
        assertEquals(10, nutritionInfo.getFat(), 0.01);
        assertEquals(5, nutritionInfo.getProtein(), 0.01);
        assertEquals(20, nutritionInfo.getCarbs(), 0.01);
        assertEquals(2, nutritionInfo.getFiber(), 0.01);
        assertEquals(8, nutritionInfo.getSugar(), 0.01);
        assertEquals(300, nutritionInfo.getSodium(), 0.01);
    }

    @Test
    public void testExtractAllergens() {
        // Test extracting allergen information from a JSON object
        JSONObject recipeDetail = new JSONObject("{\"allergens\": [\"Gluten\", \"Eggs\", \"Dairy\"]}");
        List<String> allergens = RecipeFinder.extractAllergens(recipeDetail);

        assertNotNull(allergens);
        assertEquals(3, allergens.size());
        assertTrue(allergens.contains("Gluten"));
        assertTrue(allergens.contains("Eggs"));
        assertTrue(allergens.contains("Dairy"));
    }
}

