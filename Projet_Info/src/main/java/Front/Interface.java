package Front;

import Back.Frigo;
import Back.Ingredient;
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

        // Create the orange stripe panel
        JPanel orangeStripe = new JPanel();
        orangeStripe.setBackground(new Color(255, 165, 79)); 
        int stripeWidth = getWidth() / 6;
        orangeStripe.setPreferredSize(new Dimension(stripeWidth, getHeight()));
        orangeStripe.setLayout(new BoxLayout(orangeStripe, BoxLayout.Y_AXIS));

        // Create and add menu buttons to the orange stripe
        String[] menuItems = {"","My Fridge App","","","","","","","","","","","Fridge",".", "Recipe Search",".", "Selected Recipes",".", "Shopping List",".", "Favorites"};
        for (int i = 0; i < menuItems.length; i++) {
            String item = menuItems[i];
            JButton button = new JButton(item);
            button.setFont(new Font("Segoe UI", Font.BOLD, 24));
            button.setForeground(Color.WHITE);
            button.setOpaque(false); 
            button.setContentAreaFilled(false); 
            button.setBorderPainted(false); 
            button.setFocusPainted(false);
            button.setCursor(new Cursor(Cursor.HAND_CURSOR));
            button.setAlignmentX(Component.CENTER_ALIGNMENT); 
        
            button.addActionListener(e -> handleMenuItemClick(item));
        
            // Add rigid area for vertical spacing between buttons
            orangeStripe.add(Box.createRigidArea(new Dimension(0, 10)));
            orangeStripe.add(button);
            menuButtons.add(button);
        
            // Apply a condition for the title
            if (i == 1) {
                button.setFont(new Font("Tahoma", Font.PLAIN, 40));
            }
        }

        // Set the main layout to BorderLayout
        setLayout(new BorderLayout());
        add(orangeStripe, BorderLayout.WEST);
        add(cardPanel, BorderLayout.CENTER);

        setTitle("What's in my fridge");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        // Add a component listener to handle resizing events
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                int stripeWidth = getWidth() / 5; 
                orangeStripe.setPreferredSize(new Dimension(stripeWidth, getHeight()));
                orangeStripe.revalidate();
            }
        });

    }

    // Handles menu item clicks to switch between panels
    private void handleMenuItemClick(String itemName) {
        switch (itemName) {
            case "Fridge":
                cardLayout.show(cardPanel, "Fridge");
                break;
            case "Recipe Search":
            	List<Ingredient> selectedIngredients = frigoPanel.getSelectedIngredients();
                List<Recipe> recipes = RecipeFinder.searchRecipes(selectedIngredients);
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

