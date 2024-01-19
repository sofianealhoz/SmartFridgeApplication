package Front;

import Back.Frigo;
import Back.Ingredient;
import Back.RecipeFinder;
import Back.Recipe;
import Back.DatabaseAccess;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

import java.util.Timer;

public class Interface extends JFrame {
	private static final long serialVersionUID = 1L;
	private CardLayout cardLayout;
	private JPanel cardPanel;
	private FrigoPanel frigoPanel;
	private RecipesPanel recipesPanel;
	private SelectedRecipePanel selectedRecipePanel;
	private WelcomePanel welcomePanel;
	private List<JButton> menuButtons;
	private Frigo frigo;
	private ShoppingCartPanel shoppingCartPanel;

	public Frigo getFrigo() {
        return frigo;
}
	
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
		selectedRecipePanel = new SelectedRecipePanel(recipesPanel);
        shoppingCartPanel = new ShoppingCartPanel(this, selectedRecipePanel);


		// Welcome Panel
		WelcomePanel welcomePanel = new WelcomePanel();
		cardPanel.add(welcomePanel, "Welcome");

		// Adding other panels to the card layout
		cardPanel.add(frigoPanel, "Fridge");
		cardPanel.add(recipesPanel, "Recipe Search");
		cardPanel.add(selectedRecipePanel, "SelectedRecipe");

		// Create the orange stripe panel
		JPanel orangeStripe = new JPanel();
		orangeStripe.setBackground(Color.ORANGE);
		int stripeWidth = getWidth() / 6; // Adjust the width fraction as needed
		orangeStripe.setPreferredSize(new Dimension(stripeWidth, getHeight()));

		// Use BoxLayout for vertical arrangement of buttons
		orangeStripe.setLayout(new BoxLayout(orangeStripe, BoxLayout.Y_AXIS));

		// Create and add menu buttons to the orange stripe
		String[] menuItems = { "", "My Fridge App", "", "", "", "", "", "", "", "", "", "", "Fridge", ".",
				"Recipe Search", ".", "Selected Recipes", ".", "Shopping List", ".", "Favorites" };
		for (int i = 0; i < menuItems.length; i++) {
			String item = menuItems[i];
			JButton button = new JButton(item);
			button.setFont(new Font("Segoe UI", Font.BOLD, 24));
			button.setForeground(Color.WHITE);
			button.setOpaque(false); // Make the button transparent
			button.setContentAreaFilled(false); // Remove the default background
			button.setBorderPainted(false); // Remove the button border
			button.setFocusPainted(false);
			button.setCursor(new Cursor(Cursor.HAND_CURSOR));
			button.setAlignmentX(Component.CENTER_ALIGNMENT); // Center text horizontally

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

		// Add the orange stripe to the left (WEST) and content to the center (CENTER)
		add(orangeStripe, BorderLayout.WEST);
		add(cardPanel, BorderLayout.CENTER);

		setTitle("What's in my fridge");
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

		// Add a component listener to handle resizing events
		addComponentListener(new java.awt.event.ComponentAdapter() {
			public void componentResized(java.awt.event.ComponentEvent evt) {
				int stripeWidth = getWidth() / 5; // Adjust the width fraction as needed
				orangeStripe.setPreferredSize(new Dimension(stripeWidth, getHeight()));
				orangeStripe.revalidate();
			}
		});

		// Ajoutez l'appel à startExpirationCheckTimer() ici
		startExpirationCheckTimer();

	}

	// Handles menu item clicks to switch between panels
	private void handleMenuItemClick(String itemName) {
		switch (itemName) {
		case "My Fridge App":
            cardLayout.show(cardPanel, "Welcome");
            break;
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

		// Additional handling for other menu items we need to implement
		}
	}

	public void startExpirationCheckTimer() {
        Timer timer = new Timer();
        
        // Scheduled task to check expiration dates every 24 hours
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                checkAndDisplayExpiredIngredients();
            }
        }, 10000, 10000); // Start immediately and repeat every
    }

    // Method to check and display expired ingredients
    private void checkAndDisplayExpiredIngredients() {
        List<Ingredient> expiredIngredients = DatabaseAccess.getExpiredIngredients();

        if (!expiredIngredients.isEmpty()) {
            // Build the message to display in the dialog box
            StringBuilder message = new StringBuilder("The following ingredients have expired :\n");

            for (Ingredient ingredient : expiredIngredients) {
                message.append("- ").append(ingredient.getName()).append("\n");
            }

            // Display the dialog box
			SwingUtilities.invokeLater(() -> {
				JOptionPane.showMessageDialog(null, message.toString(), "Expired ingredients", JOptionPane.WARNING_MESSAGE);
			});
			
			
		}
    }

	public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Interface app = new Interface();
            app.handleMenuItemClick("My Fridge App"); // Display the WelcomePanel initially
        });
    }

}
