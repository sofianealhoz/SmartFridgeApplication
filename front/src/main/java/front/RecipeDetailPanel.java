package front;

import javax.swing.*;
import java.awt.*;

import back.Ingredient;
import back.Recipe;

public class RecipeDetailPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    

    public RecipeDetailPanel(Recipe recipe, CardLayout cardLayout,  JPanel cardContainer) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Adding a back button
        JButton backButton = new JButton("Back to Recipes");
        backButton.addActionListener(e -> {
            System.out.println("Back button clicked");
            cardLayout.show(cardContainer, "Recipe List");
        });
        add(backButton);

        // Ingredient section header
        JLabel ingredientsHeader = new JLabel("Ingredients:");
        customizeLabel(ingredientsHeader, Color.BLUE, new Font("Verdana", Font.ITALIC, 20));
        add(ingredientsHeader);

        // Displaying the ingredients of the recipe 
        for (Ingredient ingredient : recipe.getIngredients()) {
            JLabel ingredientLabel = new JLabel("\u2022 " + ingredient.getName()); 
            customizeLabel(ingredientLabel, Color.BLACK, new Font("Arial", Font.PLAIN, 16));
            add(ingredientLabel);
        }

        // Separator between ingredients and instructions
        add(Box.createRigidArea(new Dimension(0, 20)));

        JLabel instructionsHeader = new JLabel("Instructions:");
        customizeLabel(instructionsHeader, Color.BLUE, new Font("Verdana", Font.ITALIC, 20));
        add(instructionsHeader);

        // Displaying the cooking instructions of the recipe
        JPanel instructionsPanel = new JPanel();
        instructionsPanel.setLayout(new BoxLayout(instructionsPanel, BoxLayout.Y_AXIS));
        for (String instruction : recipe.getInstructions()) {
            JLabel instructionLabel = new JLabel("\u2022 " + instruction); 
            instructionLabel.setMaximumSize(new Dimension(Integer.MAX_VALUE, instructionLabel.getMinimumSize().height));
            customizeLabel(instructionLabel, Color.DARK_GRAY, new Font("Arial", Font.PLAIN, 16));
            instructionsPanel.add(instructionLabel);
        }

        JScrollPane instructionsScrollPane = new JScrollPane(instructionsPanel);
        add(instructionsScrollPane);
    }

 
    private void customizeLabel(JLabel label, Color color, Font font) {
        label.setForeground(color);
        label.setFont(font);
        label.setAlignmentX(Component.LEFT_ALIGNMENT); 
    }
}
