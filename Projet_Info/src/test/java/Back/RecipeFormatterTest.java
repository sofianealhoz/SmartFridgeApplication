package Back;

import static org.junit.Assert.*;

import org.junit.Test;

public class RecipeFormatterTest {

    @Test
    public void testStandardizeInstructionsWithHtml() {
        RecipeFormatter formatter = new RecipeFormatter();
        String rawInstructions = "<ol><li>Preheat the oven</li><li>Mix ingredients</li></ol>";

        String standardizedInstructions = formatter.standardizeInstructions(rawInstructions);

        assertEquals("1. Preheat the oven\n2. Mix ingredients", standardizedInstructions);
    }

    @Test
    public void testStandardizeInstructionsWithoutHtml() {
        RecipeFormatter formatter = new RecipeFormatter();
        String rawInstructions = "Preheat the oven\nMix ingredients";

        String standardizedInstructions = formatter.standardizeInstructions(rawInstructions);

        assertEquals("1. Preheat the oven\n2. Mix ingredients", standardizedInstructions);
    }

    @Test
    public void testStandardizeInstructionsWithMixedHtmlAndText() {
        RecipeFormatter formatter = new RecipeFormatter();
        String rawInstructions = "<ol><li>Preheat the oven</li>Line break\n<li>Mix ingredients</li></ol>";

        String standardizedInstructions = formatter.standardizeInstructions(rawInstructions);

        System.out.println("Standardized Instructions:");
        System.out.println(standardizedInstructions);

        assertTrue(standardizedInstructions.contains("1. Preheat the oven"));
        assertTrue(standardizedInstructions.contains("2. Line break"));
        assertTrue(standardizedInstructions.contains("3. Mix ingredients"));
    }

    @Test
    public void testStandardizeInstructionsWithRedundantNumbering() {
        RecipeFormatter formatter = new RecipeFormatter();
        String rawInstructions = "1. Preheat the oven\n2. Mix ingredients\n1. Bake in the oven";

        String standardizedInstructions = formatter.standardizeInstructions(rawInstructions);

        assertEquals("1. Preheat the oven\n2. Mix ingredients\n3. Bake in the oven", standardizedInstructions);
    }

    @Test
    public void testStandardizeInstructionsWithEmptyInput() {
        RecipeFormatter formatter = new RecipeFormatter();
        String rawInstructions = "";

        String standardizedInstructions = formatter.standardizeInstructions(rawInstructions);

        assertEquals("", standardizedInstructions);
    }

    @Test
    public void testStandardizeInstructionsWithNullInput() {
        RecipeFormatter formatter = new RecipeFormatter();
        String standardizedInstructions = formatter.standardizeInstructions(null);

        assertEquals("", standardizedInstructions);
    }
}

