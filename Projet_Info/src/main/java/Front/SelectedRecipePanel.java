package Front;

import Back.Recipe;

import javax.swing.*;

public class SelectedRecipePanel extends JPanel {
    private JLabel recipeDetails;

    public SelectedRecipePanel() {
        recipeDetails = new JLabel();
        this.add(recipeDetails);
    }

    public void displayRecipe(Recipe recipe) {
        recipeDetails.setText("<html>" + recipe.getName() + "<br>" + recipe.getInstructions() + "</html>");
        // Afficher d'autres détails de la recette si nécessaire
    }
}
