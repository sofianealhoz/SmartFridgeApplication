package Front;

import Back.Frigo;
import Back.Ingredient;
import Back.Recipe;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class ShoppingCartPanel extends JPanel {
    private Frigo fridge;
    private SelectedRecipePanel selectedRecipePanel;

    // Create a JTable to display the necessary ingredients
    private JTable ingredientsTable;

    public ShoppingCartPanel(Interface mainInterface, SelectedRecipePanel selectedRecipePanel) {
        this.fridge = mainInterface.getFrigo();
        this.selectedRecipePanel = selectedRecipePanel;
        setLayout(new BorderLayout());

        // Initialize the ingredientsTable
        ingredientsTable = new JTable();
        ingredientsTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        ingredientsTable.setFillsViewportHeight(true);

        JScrollPane scrollPane = new JScrollPane(ingredientsTable);
        add(scrollPane, BorderLayout.CENTER);

        refreshShoppingCart();
    }

    public void refreshShoppingCart() {
        List<Recipe> selectedRecipes = selectedRecipePanel.getSelectedRecipe();

        if (selectedRecipes.isEmpty()) {
            // Handle the case when no recipes are selected
            JLabel emptyLabel = new JLabel("No recipes selected.");
            emptyLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
            emptyLabel.setHorizontalAlignment(SwingConstants.CENTER);
            removeAll();
            add(emptyLabel, BorderLayout.CENTER);
        } else {
            List<Ingredient> necessaryIngredients = new ArrayList<>();

            // Calculate necessary ingredients from the selected recipes and fridge contents
            for (Recipe recipe : selectedRecipes) {
                necessaryIngredients.addAll(calculateNecessaryIngredients(recipe, fridge.getIngredients()));
            }

            // Create a table model to display the necessary ingredients
            DefaultTableModel tableModel = new DefaultTableModel(
                new String[]{"Ingredient", "Quantity", "Category"}, 0);

            for (Ingredient ingredient : necessaryIngredients) {
                tableModel.addRow(new Object[]{
                    ingredient.getName(),
                    ingredient.getQuantity(),
                    ingredient.getCategory()
                });
            }

            ingredientsTable.setModel(tableModel);
        }

        revalidate();
        repaint();
    }

    private List<Ingredient> calculateNecessaryIngredients(Recipe recipe, List<Ingredient> fridgeIngredients) {
        List<Ingredient> necessaryIngredients = new ArrayList<>();

        // Iterate through the ingredients in the recipe
        for (Ingredient recipeIngredient : recipe.getIngredients()) {
            boolean foundInFridge = false;

            // Check if the ingredient is already in the fridge
            for (Ingredient fridgeIngredient : fridgeIngredients) {
                if (fridgeIngredient.getName().equalsIgnoreCase(recipeIngredient.getName())) {
                    foundInFridge = true;

                    // Check if there is enough quantity in the fridge
                    if (fridgeIngredient.getQuantity() < recipeIngredient.getQuantity()) {
                        double neededQuantity = recipeIngredient.getQuantity() - fridgeIngredient.getQuantity();
                        necessaryIngredients.add(new Ingredient(
                            recipeIngredient.getName(),
                            recipeIngredient.getExpirationDate(),
                            neededQuantity,
                            recipeIngredient.getCategory()
                        ));
                    }
                    break;
                }
            }

            // If the ingredient is not found in the fridge, add it to the necessaryIngredients list
            if (!foundInFridge) {
                necessaryIngredients.add(recipeIngredient);
            }
        }

        return necessaryIngredients;
    }
    
}
