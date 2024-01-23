package Back;

import static org.junit.Assert.*;
import org.junit.Test;
import java.time.LocalDate;

public class IngredientTest {

    @Test
    public void testIngredientInitialization() {
        // Arrange
        String name = "Carrot";
        LocalDate expirationDate = LocalDate.of(2022, 12, 31);
        double quantity = 5.0;
        String category = "Vegetable";

        // Act
        Ingredient ingredient = new Ingredient(name, expirationDate, quantity, category, null);

        // Assert
        assertEquals(name, ingredient.getName());
        assertEquals(expirationDate, ingredient.getExpirationDate());
        assertEquals(quantity, ingredient.getQuantity(), 0.001); // Use delta for double comparison
        assertEquals(category, ingredient.getCategory());
        assertEquals("", ingredient.getUnit());
    }

    @Test
    public void testSetCategory() {
        // Arrange
        Ingredient ingredient = new Ingredient("Apple", LocalDate.of(2022, 10, 15), 3, "Fruit", null);

        // Act
        ingredient.setCategory("Snack");

        // Assert
        assertEquals("Snack", ingredient.getCategory());
    }

    @Test
    public void testEqualsMethod() {
        // Arrange
        Ingredient ingredient1 = new Ingredient("Milk", LocalDate.of(2022, 12, 15), 1.0, "Dairy", null);
        Ingredient ingredient2 = new Ingredient("Milk", LocalDate.of(2022, 12, 15), 1.5, "Dairy", null);
        Ingredient ingredient3 = new Ingredient("Egg", LocalDate.of(2022, 12, 31), 6.0, "Protein", null);

        // Act & Assert
        assertEquals(ingredient1, ingredient2); // Ingredients with the same name should be equal
        assertNotEquals(ingredient1, ingredient3); // Ingredients with different names should not be equal
    }
}


