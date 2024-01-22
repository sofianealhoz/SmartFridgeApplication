package Back;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DatabaseAccessTest {

    // Assuming you have a test database with the following credentials
    private static String TEST_DB_URL = "jdbc:mysql://127.0.0.1/testdb";
    private static String TEST_DB_USER = "root";
    private static String TEST_DB_PASSWORD = "root";
    private Recipe recipe;

    @Before
    public void setUp() {
        DatabaseAccess.setPASSWORD(TEST_DB_PASSWORD);
        DatabaseAccess.setURL(TEST_DB_URL);
        DatabaseAccess.setUSER(TEST_DB_USER);
        // Create a test Recipe object

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

        recipe = new Recipe(name, imageUrl, ingredients, instructions, nutritionInfo, allergens);
    }

    @After
    public void tearDown() {
        // Clean up test data from the database after each test
        DatabaseAccess.resetDatabaseRecipe();
        String deleteIngredients = "DELETE FROM ingredients";
        try (Connection connection = DriverManager.getConnection(TEST_DB_URL, TEST_DB_USER, TEST_DB_PASSWORD)) {
        	try (PreparedStatement deleteIngredientsStatement = connection.prepareStatement(deleteIngredients)) {
        		deleteIngredientsStatement.executeUpdate();
        	}
        	System.out.println("Ingredients reset successful.");
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
    }

    @Test
    public void testGetIngredientsList() {
        // Initially, the database should be empty
        List<Ingredient> ingredients = DatabaseAccess.getIngredientsList();
        assertNotNull(ingredients);
        assertEquals(0, ingredients.size());

        // Insert a test ingredient into the database
        DatabaseAccess.callInsertIngredient("Eggs",  "12", LocalDate.of(2024, 12, 31).toString(), "Dairy", "");

        // Retrieve the ingredients again
        ingredients = DatabaseAccess.getIngredientsList();
        assertNotNull(ingredients);
        assertEquals(1, ingredients.size());
        assertEquals("Eggs", ingredients.get(0).getName());
    }

    @Test
    public void testReturnRecipeList() {
        // Initially, the database should be empty
        List<Recipe> recipes = DatabaseAccess.returnRecipeList();
        assertNotNull(recipes);
        assertEquals(0, recipes.size());

        // Insert a test recipe into the database
        DatabaseAccess.insertRecipe(recipe, 1);

        // Retrieve the recipes again
        recipes = DatabaseAccess.returnRecipeList();
        assertNotNull(recipes);
        assertEquals(1, recipes.size());
        assertEquals("Test Recipe", recipes.get(0).getName());
    }

    @Test
    public void testInsertRecipe() {

        // Insert the test Recipe into the database
        DatabaseAccess.insertRecipe(recipe, 1);

        // Retrieve the inserted Recipe from the database
        List<Recipe> recipes = DatabaseAccess.returnRecipeList();
        assertNotNull(recipes);
        assertEquals(1, recipes.size());
        assertEquals("Test Recipe", recipes.get(0).getName());
    }

    @Test
    public void testGetExpiredIngredients() {
        // Initially, there should be no expired ingredients
        List<Ingredient> expiredIngredients = DatabaseAccess.getExpiredIngredients();
        assertNotNull(expiredIngredients);
        assertEquals(0, expiredIngredients.size());

        // Insert an expired ingredient into the database
        DatabaseAccess.callInsertIngredient("Expired Milk", "500", LocalDate.of(2002, 1, 1).toString(), "Dairy", "");

        // Retrieve expired ingredients
        expiredIngredients = DatabaseAccess.getExpiredIngredients();
        assertNotNull(expiredIngredients);
        assertEquals(1, expiredIngredients.size());
        assertEquals("Expired Milk", expiredIngredients.get(0).getName());
    }

    @Test
    public void testGetSoonExpiredIngredients() {
        // Initially, there should be no soon-to-expire ingredients
        List<Ingredient> soonExpiredIngredients = DatabaseAccess.getSoonExpiredIngredients();
        assertNotNull(soonExpiredIngredients);
        assertEquals(0, soonExpiredIngredients.size());

        // Insert a soon-to-expire ingredient into the database
        DatabaseAccess.callInsertIngredient("Soon Expire Cheese",  "200", LocalDate.now().plusDays(5).toString(), "Dairy", "");

        // Retrieve soon-to-expire ingredients
        soonExpiredIngredients = DatabaseAccess.getSoonExpiredIngredients();
        assertNotNull(soonExpiredIngredients);
        assertEquals(1, soonExpiredIngredients.size());
        assertEquals("Soon Expire Cheese", soonExpiredIngredients.get(0).getName());
    }
}
