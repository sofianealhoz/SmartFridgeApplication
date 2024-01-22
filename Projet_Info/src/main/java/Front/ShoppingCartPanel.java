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

    public void setFridge(Frigo fridge){
        this.fridge = fridge;
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
            removeAll(); // Clear the panel before updating
    
            // Create a JPanel with a vertical BoxLayout to contain the blocks of ingredients
            JPanel ingredientsPanel = new JPanel();
            ingredientsPanel.setLayout(new BoxLayout(ingredientsPanel, BoxLayout.Y_AXIS));
    
            for (Recipe recipe : selectedRecipes) {
                JPanel recipeBlock = new JPanel();
                recipeBlock.setLayout(new BorderLayout());
                recipeBlock.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1, true));
                recipeBlock.setBackground(new Color(245, 246, 250));
    
                // Add recipe name as a label at the top of each block
                JLabel recipeLabel = new JLabel(recipe.getName());
                recipeLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
                recipeLabel.setForeground(Color.ORANGE);
                recipeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                recipeLabel.setHorizontalAlignment(SwingConstants.CENTER);
    
                recipeBlock.add(recipeLabel, BorderLayout.NORTH);
    
                // Create a table model to display the necessary ingredients for the recipe
                DefaultTableModel tableModel = new DefaultTableModel(
                    new String[]{"Ingredient", "Quantity"}, 0);
    
                List<Ingredient> necessaryIngredients = calculateNecessaryIngredients(recipe, fridge.getIngredients());
    
                for (Ingredient ingredient : necessaryIngredients) {
                    tableModel.addRow(new Object[]{
                        ingredient.getName(),
                        ingredient.getQuantity(),
                    });
                }
    
                // Create a JTable to display the ingredients
                JTable ingredientsTable = new JTable(tableModel);
                ingredientsTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
                ingredientsTable.setFillsViewportHeight(true);
    
                JScrollPane scrollPane = new JScrollPane(ingredientsTable);
                recipeBlock.add(scrollPane, BorderLayout.CENTER);
    
                // Add the recipe block to the ingredients panel
                ingredientsPanel.add(recipeBlock);
                ingredientsPanel.add(Box.createRigidArea(new Dimension(0, 20))); // Add spacing between blocks
            }
    
            JScrollPane ingredientsScrollPane = new JScrollPane(ingredientsPanel);
            add(ingredientsScrollPane, BorderLayout.CENTER);
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
                            "", // Vous pouvez laisser cette chaîne vide si la catégorie n'est pas utilisée
                            recipeIngredient.getUnit()
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
