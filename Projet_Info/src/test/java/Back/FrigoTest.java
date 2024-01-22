package Back;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.List;

public class FrigoTest {

    private Frigo fridge;

    @Before
    public void setUp() {
        fridge = new Frigo();
    }

    @Test
    public void testAddIngredient() {
        Ingredient newIngredient = new Ingredient("Eggs", LocalDate.of(2024, 12, 31), 12, "Dairy", null);
        fridge.addIngredient(newIngredient);
        assertTrue(fridge.hasIngredient("Eggs"));
    }

    @Test
    public void testAddExistingIngredient() {
        Ingredient existingIngredient = new Ingredient("Sugar", LocalDate.of(2024, 12, 31), 100, "Bakery", null);
        Double initialQuantity = fridge.getIngredientsFrigo().stream()
                .filter(ingredient -> ingredient.getName().equals("Sugar"))
                .findFirst()
                .map(Ingredient::getQuantity)
                .orElse(0.0);
        fridge.addIngredient(existingIngredient);
        Double finalQuantity = fridge.getIngredientsFrigo().stream()
                .filter(ingredient -> ingredient.getName().equals("Sugar"))
                .findFirst()
                .map(Ingredient::getQuantity)
                .orElse(0.0);
        assertEquals(initialQuantity + existingIngredient.getQuantity(), finalQuantity, 0);
    }

    @Test
    public void testRemoveIngredient() {
        Ingredient ingredientToRemove = new Ingredient("Flour", LocalDate.of(2024, 6, 30), 200, "Bakery", null);
        fridge.removeIngredient(ingredientToRemove);
        assertFalse(fridge.hasIngredient("Flour"));
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
    	Ingredient newIngredient_1 = new Ingredient("Eggs", LocalDate.of(2024, 12, 31), 12, "Dairy", null);
    	Ingredient newIngredient_2 = new Ingredient("Apple", LocalDate.of(2022, 10, 15), 3, "Fruit", null);
    	fridge.addIngredient(newIngredient_1);
    	fridge.addIngredient(newIngredient_2);
    	    	
        // Ensure ingredients are initially unsorted
        assertFalse(isSortedByExpirationDate(fridge.getIngredientsFrigo()));

        fridge.sortIngredients();
        
        // Ensure ingredients are now sorted
        assertTrue(isSortedByExpirationDate(fridge.getIngredientsFrigo()));
    }
    
    @Test
    public void testDisplayContents() {
        // Redirect System.out to capture console output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        fridge.displayContents();

        // Reset System.out
        System.setOut(System.out);

        // Verify the output contains expected content
        assertTrue(outputStream.toString().contains("Fridge contents"));
        assertTrue(outputStream.toString().contains("Flour"));
        assertTrue(outputStream.toString().contains("Sugar"));
    }

    @Test
    public void testAddAllergy() {
        fridge.addAllergy("Peanuts");
        assertTrue(fridge.getAllergies().contains("Peanuts"));
    }

    @Test
    public void testRemoveAllergy() {
        fridge.addAllergy("Peanuts");
        assertTrue(fridge.getAllergies().contains("Peanuts"));

        fridge.removeAllergy("Peanuts");
        assertFalse(fridge.getAllergies().contains("Peanuts"));
    }

    @Test
    public void testRemoveNonexistentAllergy() {
        assertFalse(fridge.getAllergies().contains("Nonexistent"));
        fridge.removeAllergy("Nonexistent");
        // Ensure no exception is thrown, and it's considered as not found
    }

    @Test
    public void testDisplayAllergies() {
        // Redirect System.out to capture console output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        fridge.addAllergy("Peanuts");
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
            if (currentExpiration != null && nextExpiration != null && currentExpiration.isAfter(nextExpiration)) {
                return false;
            }
        }
        return true;
    }

}
