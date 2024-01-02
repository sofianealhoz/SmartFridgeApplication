 package front;

import back.Frigo;
import back.Ingredient;
import back.RecipeFinder;
import back.Recipe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Interface extends JFrame {
    private static final long serialVersionUID = 1L;
    private JTabbedPane tabbedPane;
    private JButton addButton;
    private JPanel ingredientPanel;
    private JPanel recipesPanel;
    private List<Ingredient> ingredients;

    private List<JButton> menuButtons;
    private JTextArea ingredientsTextArea;
    private Frigo frigo;

    public Interface() {
        frigo = new Frigo();
        menuButtons = new ArrayList<>();
        ingredientsTextArea = new JTextArea();
        ingredientsTextArea.setPreferredSize(new Dimension(200, 200)); // Example size

        frameInit();
        JPanel menuPanel = createMenuPanel();
        ingredientPanel = createIngredientPanel();
        recipesPanel = new JPanel(new BorderLayout());
        tabbedPane.addTab("Recherche Recettes", recipesPanel); // Add it to the tabbed pane

        ingredientPanel.setVisible(false);
        JScrollPane frigoScrollPane = new JScrollPane(ingredientsTextArea);

        tabbedPane.addTab("Frigo", frigoScrollPane);
        add(menuPanel, BorderLayout.WEST);
        add(ingredientPanel, BorderLayout.CENTER);

        addButton = new JButton("+");
        addButton.setPreferredSize(menuButtons.get(0).getPreferredSize());
        addButton.setFont(new Font("Arial", Font.BOLD, 24));
        addButton.setForeground(Color.WHITE);
        addButton.setBackground(Color.BLUE);
        addButton.setOpaque(false);
        addButton.setBorderPainted(false);
        addButton.setFocusPainted(false);
        addButton.addActionListener(e -> displayAddIngredientDialog());
        JPanel addButtonPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                int diameter = Math.min(getWidth(), getHeight());
                g.setColor(Color.BLUE);
                g.fillOval(getWidth() - diameter, getHeight() - diameter, diameter, diameter);
                g.setColor(Color.WHITE);
                g.setFont(new Font("Arial", Font.BOLD, 24));
                g.drawString("+", getWidth() - diameter / 2 - 10, getHeight() - diameter / 2 + 10);
            }
        };
        addButtonPanel.setPreferredSize(new Dimension(70, 70));
        addButtonPanel.setOpaque(false);
        addButtonPanel.setLayout(new BorderLayout());
        addButtonPanel.add(addButton, BorderLayout.CENTER);
        add(addButtonPanel, BorderLayout.SOUTH);

        setTitle("What's in my fridge");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private JPanel createMenuPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1));

        String[] menuItems = {"Frigo", "Recherche Recettes", "Recettes Selectionnées", "Liste de courses", "Favoris"};
        for (String item : menuItems) {
            addMenuItem(panel, item);
        }

        return panel;
    }

    private JPanel createRecipesPanel(List<Recipe> recipes) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        DefaultListModel<String> listModel = new DefaultListModel<>();
        JList<String> recipeList = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(recipeList);

        if (recipes.isEmpty()) {
            listModel.addElement("No recipes found with the specified ingredients.");
        } else {
            for (Recipe recipe : recipes) {
                String recipeText = recipe.getName() + "\nIngredients:\n";
                for (Ingredient ingredient : recipe.getIngredients()) {
                    recipeText += "- " + ingredient.getName() + "\n";
                }
                recipeText += "Instructions:\n" + recipe.getInstructions();
                listModel.addElement(recipeText);
            }
        }

        panel.add(scrollPane, BorderLayout.CENTER);
        return panel;
    }


	private JPanel createIngredientPanel() {
	    JPanel panel = new JPanel(new BorderLayout());
	
	    ingredientsTextArea.setEditable(false);
	
	    if (frigo.getIngredients().isEmpty()) {
	        appendIngredientText("There is nothing in your fridge");
	    } else {
	        for (Ingredient ingredient : frigo.getIngredients()) {
	            appendIngredientText("- " + ingredient.getQuantity() + " unit(s) of " + ingredient.getName() +
	                    " (Expiration date: " + ingredient.getExpirationDate() + ")\n");
	        }
	    }
	
	    JScrollPane scrollPane = new JScrollPane(ingredientsTextArea);
	
	    panel.add(scrollPane, BorderLayout.CENTER);
	
	    return panel;
	}

	private void addMenuItem(JPanel panel, String itemName) {
        JButton button = new JButton(itemName);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBackground(Color.BLUE);
        button.setForeground(Color.WHITE);
        panel.add(button);
        if (itemName.equals("Frigo")) {
            FrigoButtonHandler frigoHandler = new FrigoButtonHandler(frigo, this);
            button.addActionListener(frigoHandler);
        } else {
            button.addActionListener(e -> handleMenuItemClick(itemName));
        }
        menuButtons.add(button);
    }

    void handleMenuItemClick(String itemName) {
        switch (itemName) {
            case "Frigo":
                displayFridgeIngredients();
                break;
            case "Recherche Recettes":
                displayRecipes();
                break;
            default:
                JOptionPane.showMessageDialog(this, "Feature '" + itemName + "' not yet implemented.", "Info", JOptionPane.INFORMATION_MESSAGE);
                break;
        }
    }

    
    
    
    
    
    
    private void displayFridgeIngredients() {
	    updateIngredientsTextArea();
	    frigo.sortIngredients();
	    refreshIngredientsTextArea();
	}

    private void displayRecipes() {
        // Call RecipeFinder to search for recipes based on fridge ingredients
        List<Recipe> recipes = RecipeFinder.searchRecipes(frigo.getIngredients());

        // Clear the previous search results
        recipesPanel.removeAll();

        // Use the createRecipesPanel method to create a panel for displaying recipes
        JPanel recipesDisplayPanel = createRecipesPanel(recipes);

        // Add the recipesDisplayPanel to the recipesPanel
        recipesPanel.add(recipesDisplayPanel);

        // Refresh the panel to display new components
        recipesPanel.revalidate();
        recipesPanel.repaint();
    }




    
    
    
    private void updateIngredientsTextArea() {
        clearIngredientsTextArea();
        if (frigo.getIngredients().isEmpty()) {
            appendIngredientText("There is nothing in your fridge");
        } else {
            for (Ingredient ingredient : frigo.getIngredients()) {
                appendIngredientText("- " + ingredient.getQuantity() + " unit(s) of " + ingredient.getName() +
                        " (Expiration date: " + ingredient.getExpirationDate() + ")\n");
            }
        }
    }

    private void refreshIngredientsTextArea() {
        ingredientsTextArea.revalidate();
    }

    private void clearIngredientsTextArea() {
        ingredientsTextArea.setText("");
    }

    private void appendIngredientText(String text) {
        ingredientsTextArea.append(text);
    }

    private void setIngredientPanel(JPanel newPanel) {
        getContentPane().remove(ingredientPanel);
        ingredientPanel = newPanel;
        getContentPane().add(ingredientPanel, BorderLayout.CENTER);
        revalidate();
        repaint();
    }


    private void displayAddIngredientDialog() {
        JDialog dialog = new JDialog(this, "Add Ingredient");
        dialog.setLayout(new GridLayout(0, 2));

        JLabel nameLabel = new JLabel("Ingredient Name:");
        JTextField nameField = new JTextField();
        JLabel quantityLabel = new JLabel("Quantity:");
        JTextField quantityField = new JTextField();
        JLabel expirationLabel = new JLabel("Expiration Date (yyyy-mm-dd):");
        JTextField expirationField = new JTextField();
        JLabel categoryLabel = new JLabel("Category:");
        JComboBox<String> categoryComboBox = new JComboBox<>(new String[]{
                "Meat, sausages and fish",
                "Grain, nuts and baking products",
                "Eggs, milk and dairy",
                "Fats and oils",
                "Herbs and spices",
                "Fruits",
                "Vegetables",
                "Others"
        });

        dialog.add(nameLabel);
        dialog.add(nameField);
        dialog.add(quantityLabel);
        dialog.add(quantityField);
        dialog.add(expirationLabel);
        dialog.add(expirationField);
        dialog.add(categoryLabel);
        dialog.add(categoryComboBox);

        JButton okButton = new JButton("OK");
        okButton.addActionListener(e -> {
            String name = nameField.getText();
            String quantityStr = quantityField.getText();
            String expirationStr = expirationField.getText();

            try {
                int quantity = Integer.parseInt(quantityStr);
                LocalDate expirationDate = LocalDate.parse(expirationStr);

                frigo.addIngredient(new Ingredient(name, expirationDate, quantity));
                frigo.sortIngredients();
                updateIngredientsTextArea();

                dialog.dispose();
            } catch (NumberFormatException | java.time.format.DateTimeParseException ex) {
                JOptionPane.showMessageDialog(this, "Invalid input. Please enter valid values.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        dialog.add(okButton);

        dialog.pack();
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }

    public List<JButton> getMenuButtons() {
        return menuButtons;
    }

    public JPanel getIngredientPanel() {
        return ingredientPanel;
    }

    public class FrigoButtonHandler implements ActionListener {
        private Frigo frigo;
        private Interface mainInterface;

        public FrigoButtonHandler(Frigo frigo, Interface mainInterface) {
            this.frigo = frigo;
            this.mainInterface = mainInterface;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Frigo button clicked.");
            updateIngredientsTextArea();
            frigo.sortIngredients();
            mainInterface.refreshIngredientsTextArea();

            // Create the ingredientPanel with the list of ingredients
            JPanel ingredientPanel = mainInterface.createIngredientPanel();

            // Set the ingredientPanel as the content of the Interface
            mainInterface.setIngredientPanel(ingredientPanel);
        }

        private void updateIngredientsTextArea() {
            mainInterface.clearIngredientsTextArea();
            for (Ingredient ingredient : frigo.getIngredients()) {
                    mainInterface.appendIngredientText("- " + ingredient.getQuantity() + " unit(s) of " + ingredient.getName() +
                            " (Expiration date: " + ingredient.getExpirationDate() + ")\n");
                }
            
        }

        public void setIngredientPanel(JPanel ingredientPanel) {
            mainInterface.remove(mainInterface.ingredientPanel); // Remove the old ingredientPanel
            mainInterface.ingredientPanel = ingredientPanel; // Set the new ingredientPanel
            mainInterface.add(ingredientPanel, BorderLayout.CENTER); // Add it to the Interface
            mainInterface.validate(); // Revalidate the Interface to update the UI
        }
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

    protected void frameInit() {
        super.frameInit();
        tabbedPane = new JTabbedPane();
        add(tabbedPane);
        tabbedPane.addTab("Onglet par défaut", new JLabel("Hello world!"));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Interface());
    }
}