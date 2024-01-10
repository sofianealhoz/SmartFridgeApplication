package Front;

import Back.Frigo;
import Back.RecipeFinder;
import Back.Recipe;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Interface extends JFrame {
    private static final long serialVersionUID = 1L;
    private CardLayout cardLayout;
    private JPanel cardPanel;
    private FrigoPanel frigoPanel;
    private RecipesPanel recipesPanel;
    private List<JButton> menuButtons;
    private Frigo frigo;
    private JButton addButton;

    // Constructor to set up the main interface of the application.
    public Interface() {
        frigo = new Frigo();
        menuButtons = new ArrayList<>();

        // Setting up card layout to switch between different panels
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        // Initializing the panels for the fridge and recipe search
        frigoPanel = new FrigoPanel(frigo);
        recipesPanel = new RecipesPanel(cardPanel, cardLayout);

        // Welcome Panel 
        WelcomePanel welcomePanel = new WelcomePanel();
        cardPanel.add(welcomePanel, "Welcome");

        // Adding other panels to the card layout
        cardPanel.add(frigoPanel, "Fridge");
        cardPanel.add(recipesPanel, "Recipe Search");

        // Creating and adding menu panel to the frame
        JPanel menuPanel = createMenuPanel();
        add(menuPanel, BorderLayout.WEST);
        add(cardPanel, BorderLayout.CENTER);

        
        setTitle("What's in my fridge");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        }

    // Creates the menu panel with buttons for different functionalities
    private JPanel createMenuPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1));
        String[] menuItems = {"Fridge", "Recipe Search", "Selected Recipes", "Shopping List", "Favorites"};
        for (String item : menuItems) {
            addMenuItem(panel, item);
        }
        return panel;
    }

    // Adds the buttons to the menu panel
    private void addMenuItem(JPanel panel, String itemName) {
    	JButton button = new JButton(itemName);
        button.setFont(new Font("Segoe UI", Font.BOLD, 18)); 
        Color buttonColor = new Color(169, 204, 177); 
        button.setBackground(buttonColor);
        button.setForeground(Color.DARK_GRAY); 
        button.setBorder(BorderFactory.createLineBorder(new Color(105, 130, 110))); 
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(buttonColor.darker()); 
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(buttonColor); 
            }
        });
        button.addActionListener(e -> handleMenuItemClick(itemName));
        panel.add(button);
        menuButtons.add(button);
    }

    // Handles menu item clicks to switch between panels
    private void handleMenuItemClick(String itemName) {
        switch (itemName) {
            case "Fridge":
                cardLayout.show(cardPanel, "Fridge");
                break;
            case "Recipe Search":
                List<Recipe> recipes = RecipeFinder.searchRecipes(frigo.getIngredients());
                System.out.println("List of found recipes:");
                for (Recipe recipe : recipes) {
                    System.out.println("Recipe: " + recipe.getName());
                    System.out.println("Image URL: " + recipe.getImageUrl());
                    System.out.println();
                }
                recipesPanel.displayRecipes(recipes);
                cardLayout.show(cardPanel, "Recipe Search");
                break;
            // Additional handling for other menu items we need to implement
        }
    }
    

    // Main method to run the application
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Interface());
    }
}
