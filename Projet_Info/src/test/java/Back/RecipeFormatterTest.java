package Back;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class RecipeFormatterTest {

    @Test
    public void testStandardizeFromHTML() {
        RecipeFormatter formatter = new RecipeFormatter();
        String htmlInstructions = "<ul><li>Step 1</li><li>Step 2</li></ul>";
        String expected = "• Step 1\n• Step 2";

        String result = formatter.standardizeFromHTML(htmlInstructions);

        assertEquals(expected, result);
    }

    @Test
    public void testStandardizeFromPlainText() {
        RecipeFormatter formatter = new RecipeFormatter();
        String plainTextInstructions = "Step 1\nStep 2";
        String expected = "• Step 1\n• Step 2";

        String result = formatter.standardizeFromPlainText(plainTextInstructions);

        assertEquals(expected, result);
    }
}


