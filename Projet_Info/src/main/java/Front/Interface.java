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
    private SelectedRecipePanel selectedRecipePanel; // Panel pour afficher une recette sélectionnée
    private WelcomePanel welcomePanel; // Added WelcomePanel
    private List<JButton> menuButtons;
    private Frigo frigo;
    private ShoppingCartPanel shoppingCartPanel; // Add a reference to the ShoppingCartPanel

    public Frigo getFrigo() {
            return frigo;
    }
        
    public Interface() {
        frigo = new Frigo();
        menuButtons = new ArrayList<>();

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        // Initialize WelcomePanel and add it to cardPanel
        welcomePanel = new WelcomePanel();
        cardPanel.add(welcomePanel, "Welcome"); // Add the WelcomePanel to the cardLayout with the name "Welcome"

        frigoPanel = new FrigoPanel(frigo);
        recipesPanel = new RecipesPanel(cardPanel, cardLayout);
        selectedRecipePanel = new SelectedRecipePanel(recipesPanel);
        shoppingCartPanel = new ShoppingCartPanel(this, selectedRecipePanel);

        cardPanel.add(frigoPanel, "Fridge");
        cardPanel.add(recipesPanel, "Recipe Search");
        cardPanel.add(selectedRecipePanel, "SelectedRecipe"); // Ajout du panel au CardLayout

        // Configuration du panel orange avec les boutons du menu
        JPanel orangeStripe = new JPanel();
        orangeStripe.setBackground(Color.ORANGE);
        orangeStripe.setLayout(new BoxLayout(orangeStripe, BoxLayout.Y_AXIS));
        int stripeWidth = getWidth() / 6; 
        orangeStripe.setPreferredSize(new Dimension(stripeWidth, getHeight()));

        String[] menuItems = {"", "My Fridge App", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "Fridge", ".", "Recipe Search", ".", "Selected Recipes", ".", "Shopping List", ".", "Favorites"};
        for (String item : menuItems) {
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
            orangeStripe.add(button);
            menuButtons.add(button);
        }

        setLayout(new BorderLayout());
        add(orangeStripe, BorderLayout.WEST);
        add(cardPanel, BorderLayout.CENTER);

        setTitle("What's in my fridge");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                int newStripeWidth = getWidth() / 5; 
                orangeStripe.setPreferredSize(new Dimension(newStripeWidth, getHeight()));
                orangeStripe.revalidate();
            }
        });
    }

    private void handleMenuItemClick(String itemName) {
        switch (itemName) {
            case "My Fridge App":
                cardLayout.show(cardPanel, "Welcome"); // Display the WelcomePanel
                break;
            case "Fridge":
                cardLayout.show(cardPanel, "Fridge");
                break;
            case "Recipe Search":
                List<Recipe> recipes = RecipeFinder.searchRecipes(frigo.getIngredients());
                recipesPanel.displayRecipes(recipes);
                cardLayout.show(cardPanel, "Recipe Search");
                break;
            case "Selected Recipes":
                selectedRecipePanel.displaySelectedRecipes();
                cardLayout.show(cardPanel, "SelectedRecipe");
                break;
            case "Shopping List":
                // Check if the ShoppingCartPanel already exists, and show it if it does
                Component[] components = cardPanel.getComponents();
                for (Component component : components) {
                    if (component instanceof ShoppingCartPanel) {
                        cardLayout.show(cardPanel, "ShoppingCart"); // Use the correct card name here
                        // Trigger a refresh of the ShoppingCartPanel
                        ((ShoppingCartPanel) component).refreshShoppingCart();
                        return; // Exit the method to prevent creating multiple instances
                    }
                }

                // If ShoppingCartPanel doesn't exist, create and add it
                ShoppingCartPanel shoppingCartPanel = new ShoppingCartPanel(this, selectedRecipePanel);
                cardPanel.add(shoppingCartPanel, "ShoppingCart"); // Use the correct card name here
                cardLayout.show(cardPanel, "ShoppingCart"); // Use the correct card name here
                break;
                        // Autres cas...
                    }
                }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Interface app = new Interface();
            app.handleMenuItemClick("My Fridge App"); // Display the WelcomePanel initially
        });
    }
}
