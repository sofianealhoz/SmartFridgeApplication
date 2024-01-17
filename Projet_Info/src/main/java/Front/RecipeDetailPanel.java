package Front;

import javax.swing.*;
import java.awt.*;
import Back.Ingredient;
import Back.Recipe;

public class RecipeDetailPanel extends JPanel {
    private static final long serialVersionUID = 1L;

    public RecipeDetailPanel(Recipe recipe, CardLayout cardLayout, JPanel cardContainer) {
        setLayout(new BorderLayout());
        
     // Add a "Back to Recipes" button
        JButton backButton = new JButton("Back to Recipes");
        backButton.addActionListener(e -> cardLayout.show(cardContainer, "Recipe Search"));
        add(backButton, BorderLayout.SOUTH);
        backButton.setFont(new Font("Arial", Font.BOLD, 16));
        backButton.setForeground(Color.WHITE);
        backButton.setBackground(new Color(70, 130, 180));
        backButton.setOpaque(true);
        backButton.setBorderPainted(false);

        // Container for ingredients and instructions
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        JScrollPane contentScrollPane = new JScrollPane(contentPanel);
        add(contentScrollPane, BorderLayout.CENTER);

        // "Ingredients" header
        JLabel ingredientsHeader = new JLabel("Ingredients:");
        customizeLabel(ingredientsHeader, Color.BLUE, new Font("Verdana", Font.ITALIC, 20));
        contentPanel.add(ingredientsHeader);

        // Display recipe ingredients
        for (Ingredient ingredient : recipe.getIngredients()) {
            JLabel ingredientLabel = new JLabel("\u2022 " + ingredient.getName());
            customizeLabel(ingredientLabel, Color.BLACK, new Font("Arial", Font.PLAIN, 16));
            contentPanel.add(ingredientLabel);
        }

        contentPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        // "Instructions" header
        JLabel instructionsHeader = new JLabel("Instructions:");
        customizeLabel(instructionsHeader, Color.BLUE, new Font("Verdana", Font.ITALIC, 20));
        contentPanel.add(instructionsHeader);

        // Display cooking instructions for the recipe
        for (String instruction : recipe.getInstructions()) {
            JLabel instructionLabel = new JLabel("\u2022 " + instruction);
            customizeLabel(instructionLabel, Color.DARK_GRAY, new Font("Arial", Font.PLAIN, 16));
            contentPanel.add(instructionLabel);
        }
    }

    private void customizeLabel(JLabel label, Color color, Font font) {
        label.setForeground(color);
        label.setFont(font);
    }
}
