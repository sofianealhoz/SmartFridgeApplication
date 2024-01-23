package Back;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.List;

public class FrigoTest {

    private Frigo fridge;
    private Ingredient ingredient_1 = new Ingredient("Eggs", LocalDate.of(2024, 12, 31), 12, "Dairy", null);
    private Ingredient ingredient_2 = new Ingredient("Apple", LocalDate.of(2022, 10, 15), 3, "Fruit", null);
    
    @Before
    public void setUp() {
    	DatabaseAccess databaseAccess = new DatabaseAccess();
        User user= new User("user", databaseAccess);
        user.addAllergy("Peanuts");
        fridge = new Frigo(user, databaseAccess);
    }
    
    @After
    public void tearDown() {
	        fridge.removeIngredient(ingredient_1);
	        fridge.removeIngredient(ingredient_2);
    }

    @Test
    public void testAddIngredient() {
        fridge.addIngredient(ingredient_2);
        assertTrue(fridge.getIngredients().contains(ingredient_2));
    }

    @Test
    public void testRemoveIngredient() {
        Ingredient ingredientToRemove = new Ingredient("Flour", LocalDate.of(2024, 6, 30), 200, "Bakery", null);
        fridge.removeIngredient(ingredientToRemove);
        assertFalse(fridge.getIngredients().contains(ingredientToRemove));
    }

    @Test
    public void testRemoveNonexistentIngredient() {
        // Redirect System.out to capture console output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        
        Ingredient nonExistentIngredient = new Ingredient("Nonexistent", LocalDate.of(2024, 6, 30), 200, "Unknown", null);
        fridge.removeIngredient(nonExistentIngredient);
        // Make sure no exception is thrown, and it's considered as not found
    }

    @Test
    public void testSortIngredients() {
    	fridge.addIngredient(ingredient_1);
    	fridge.addIngredient(ingredient_2);
    	    	
        // Ensure ingredients are initially unsorted
        assertFalse(isSortedByExpirationDate(fridge.getIngredients()));

        // Ensure ingredients are now sorted
        assertTrue(isSortedByExpirationDate(fridge.sortIngredients()));
    }
    
    @Test
    public void testDisplayContents() {
        // Redirect System.out to capture console output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        
        fridge.addIngredient(ingredient_1);
        fridge.addIngredient(ingredient_2);
        fridge.displayContents();

        // Reset System.out
        System.setOut(System.out);

        // Verify the output contains expected content
        assertTrue(outputStream.toString().contains("Fridge contents"));
        assertTrue(outputStream.toString().contains("Eggs"));
        assertTrue(outputStream.toString().contains("Apple"));
    }

    @Test
    public void testDisplayAllergies() {
        // Redirect System.out to capture console output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        fridge.displayAllergies();

        // Reset System.out
        System.setOut(System.out);

        // Verify the output contains expected content
        assertTrue(outputStream.toString().contains("List of allergies"));
        assertTrue(outputStream.toString().contains("Peanuts"));
    }
    
    private boolean isSortedByExpirationDate(List<Ingredient> ingredients) {
        for (int i = 0; i < ingredients.size() - 1; i++) {
            LocalDate currentExpiration = ingredients.get(i).getExpirationDate();
            LocalDate nextExpiration = ingredients.get(i + 1).getExpirationDate();
            if (currentExpiration != null && nextExpiration != null && currentExpiration.isAfter(nextExpiration) && !(currentExpiration.isEqual(nextExpiration))) {
                return false;
            }
        }
        return true;
    }

}
