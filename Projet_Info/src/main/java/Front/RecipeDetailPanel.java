package Front;

import Back.Recipe;
import Back.Ingredient;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class RecipeDetailPanel extends JPopupMenu {
    private JTextArea ingredientsTextArea;
    private JTextArea instructionsTextArea;

    public RecipeDetailPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel ingredientsHeader = new JLabel("Ingredients:");
        customizeLabel(ingredientsHeader, Color.BLUE, new Font("Verdana", Font.ITALIC, 20));
        add(ingredientsHeader);

        ingredientsTextArea = new JTextArea(5, 20); // Set size to limit the popup size
        customizeTextArea(ingredientsTextArea, Color.BLACK, new Font("Arial", Font.PLAIN, 16));
        add(new JScrollPane(ingredientsTextArea)); // Add scrolling

        add(Box.createRigidArea(new Dimension(0, 20)));

        JLabel instructionsHeader = new JLabel("Instructions:");
        customizeLabel(instructionsHeader, Color.BLUE, new Font("Verdana", Font.ITALIC, 20));
        add(instructionsHeader);

        instructionsTextArea = new JTextArea(5, 20); // Set size
        customizeTextArea(instructionsTextArea, Color.DARK_GRAY, new Font("Arial", Font.PLAIN, 16));
        add(new JScrollPane(instructionsTextArea)); // Add scrolling
    }

    public void displayRecipe(Recipe recipe) {
        if (recipe != null) {
            // Display ingredients in the ingredients text area
            List<Ingredient> ingredients = recipe.getIngredients();
            StringBuilder ingredientsText = new StringBuilder();
            for (Ingredient ingredient : ingredients) {
                ingredientsText.append("\u2022 ").append(ingredient.getName()).append("\n");
            }
            ingredientsTextArea.setText(ingredientsText.toString());

            // Display instructions in the instructions text area
            List<String> instructions = recipe.getInstructions();
            StringBuilder instructionsText = new StringBuilder();
            for (String instruction : instructions) {
                instructionsText.append("\u2022 ").append(instruction).append("\n");
            }
            instructionsTextArea.setText(instructionsText.toString());
        } else {
            // Reset the text areas if no recipe is selected
            ingredientsTextArea.setText("");
            instructionsTextArea.setText("");
        }
    }

    private void customizeLabel(JLabel label, Color color, Font font) {
        label.setForeground(color);
        label.setFont(font);
    }

    private void customizeTextArea(JTextArea textArea, Color color, Font font) {
        textArea.setEditable(false);
        textArea.setWrapStyleWord(true);
        textArea.setLineWrap(true);
        textArea.setForeground(color);
        textArea.setFont(font);
        textArea.setMargin(new Insets(10, 10, 10, 10));
    }
}
