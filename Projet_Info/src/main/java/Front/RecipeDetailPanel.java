package Front;

import Back.Recipe;
import Back.Ingredient;
import Back.NutritionInfo;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class RecipeDetailPanel extends JPopupMenu {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextArea ingredientsTextArea;
    private JTextArea instructionsTextArea;
    private JTextArea nutritionTextArea;

    public RecipeDetailPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel ingredientsHeader = new JLabel("Ingredients:");
        customizeLabel(ingredientsHeader, Color.ORANGE, new Font("Segoe UI", Font.BOLD, 20));
        add(ingredientsHeader);

        ingredientsTextArea = new JTextArea(5, 20);
        customizeTextArea(ingredientsTextArea, Color.BLACK, new Font("Segoe UI", Font.PLAIN, 16));
        add(new JScrollPane(ingredientsTextArea));

        add(Box.createRigidArea(new Dimension(0, 20)));

        JLabel instructionsHeader = new JLabel("Instructions:");
        customizeLabel(instructionsHeader, Color.ORANGE, new Font("Segoe UI", Font.BOLD, 20));
        add(instructionsHeader);

        instructionsTextArea = new JTextArea(5, 20);
        customizeTextArea(instructionsTextArea, Color.DARK_GRAY, new Font("Segoe UI", Font.PLAIN, 16));
        add(new JScrollPane(instructionsTextArea));

        add(Box.createRigidArea(new Dimension(0, 20)));

        JLabel nutritionHeader = new JLabel("Nutrition:");
        customizeLabel(nutritionHeader, Color.ORANGE, new Font("Segoe UI", Font.BOLD, 20));
        add(nutritionHeader);

        nutritionTextArea = new JTextArea(5, 20);
        customizeTextArea(nutritionTextArea, Color.DARK_GRAY, new Font("Segoe UI", Font.PLAIN, 16));
        add(new JScrollPane(nutritionTextArea));
    }

    public void displayRecipe(Recipe recipe) {
        if (recipe != null) {
            // Affichez les ingrédients dans la zone de texte des ingrédients
            List<Ingredient> ingredients = recipe.getIngredients();
            StringBuilder ingredientsText = new StringBuilder();
            for (Ingredient ingredient : ingredients) {
                ingredientsText.append("\u2022 ").append(ingredient.getName()).append("\n");
            }
            ingredientsTextArea.setText(ingredientsText.toString());

            // Affichez les instructions dans la zone de texte des instructions
            List<String> instructions = recipe.getInstructions();
            StringBuilder instructionsText = new StringBuilder();
            for (String instruction : instructions) {
                instructionsText.append("\u2022 ").append(instruction).append("\n");
            }
            instructionsTextArea.setText(instructionsText.toString());

            // Affichez les informations nutritionnelles
            NutritionInfo nutritionInfo = recipe.getNutritionInfo();
            StringBuilder nutritionText = new StringBuilder();
            nutritionText.append("Calories: ").append(nutritionInfo.getCalories()).append(" ");
            nutritionText.append("Protéines: ").append(nutritionInfo.getProtein()).append("g ");
            nutritionText.append("Glucides: ").append(nutritionInfo.getCarbs()).append("g ");
            nutritionText.append("Lipides: ").append(nutritionInfo.getFat()).append("g ");
            nutritionText.append("Fibres: ").append(nutritionInfo.getFiber()).append("g");
            nutritionTextArea.setText(nutritionText.toString());
        } else {
            // Réinitialisez les zones de texte si aucune recette n'est sélectionnée
            ingredientsTextArea.setText("");
            instructionsTextArea.setText("");
            nutritionTextArea.setText("");
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