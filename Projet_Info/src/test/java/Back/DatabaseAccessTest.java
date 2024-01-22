package Back;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DatabaseAccessTest {

    // Assuming you have a test database with the following credentials
    private static String TEST_DB_URL = "jdbc:mysql://127.0.0.1/testdb";
    private static String TEST_DB_USER = "root";
    private static String TEST_DB_PASSWORD = "root";

    @Before
    public void setUp() {
        DatabaseAccess.setPASSWORD(TEST_DB_PASSWORD);
        DatabaseAccess.setURL(TEST_DB_URL);
        DatabaseAccess.setUSER(TEST_DB_USER);
    }

    @After
    public void tearDown() {
        // Clean up test data from the database after each test
        DatabaseAccess.resetDatabaseRecipe();
    }

    @Test
    public void testGetIngredientsList() {
        // Initially, the database should be empty
        List<Ingredient> ingredients = DatabaseAccess.getIngredientsList();
        assertNotNull(ingredients);
        assertEquals(0, ingredients.size());

        // Insert a test ingredient into the database
        DatabaseAccess.callInsertIngredient("Eggs", LocalDate.of(2024, 12, 31).toString(), "12", "Dairy");

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
        Recipe testRecipe = new Recipe("Test Recipe", "test-image.jpg", null, null, null, null);
        DatabaseAccess.insertRecipe(testRecipe, 1);

        // Retrieve the recipes again
        recipes = DatabaseAccess.returnRecipeList();
        assertNotNull(recipes);
        assertEquals(1, recipes.size());
        assertEquals("Test Recipe", recipes.get(0).getName());
    }

    @Test
    public void testInsertRecipe() {
        // Create a test Recipe object
        Recipe testRecipe = new Recipe("Test Recipe", "test-image.jpg", null, null, null, null);

        // Insert the test Recipe into the database
        DatabaseAccess.insertRecipe(testRecipe, 1);

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
        DatabaseAccess.callInsertIngredient("Expired Milk", LocalDate.of(2022, 1, 1).toString(), "500", "Dairy");

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
        DatabaseAccess.callInsertIngredient("Soon Expire Cheese", LocalDate.now().plusDays(5).toString(), "200", "Dairy");

        // Retrieve soon-to-expire ingredients
        soonExpiredIngredients = DatabaseAccess.getSoonExpiredIngredients();
        assertNotNull(soonExpiredIngredients);
        assertEquals(1, soonExpiredIngredients.size());
        assertEquals("Soon Expire Cheese", soonExpiredIngredients.get(0).getName());
    }
}
