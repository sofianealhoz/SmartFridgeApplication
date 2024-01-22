package Front;

import Back.Frigo;
import Back.Ingredient;
import Back.RecipeFinder;
import Back.User;
import Back.Recipe;
import Back.AccountManager;
import Back.DatabaseAccess;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;
import java.util.Timer;

import Back.Frigo;
import Back.Ingredient;
import Back.Recipe;
import Back.RecipeFinder;

public class Interface extends JFrame {
	private static final long serialVersionUID = 1L;
	private CardLayout cardLayout;
	private JPanel cardPanel;
	private FrigoPanel frigoPanel;
	private RecipesPanel recipesPanel;
	private SelectedRecipePanel selectedRecipePanel;
	private FavoritePanel favoriteRecipePanel;
	private WelcomePanel welcomePanel;
	private List<JButton> menuButtons;
	private Frigo frigo;
	private ShoppingCartPanel shoppingCartPanel;
	private boolean alertDisplayed = false;
	private boolean alertDisplayed2 = false;

	private boolean Bobmode;
	DatabaseAccess databaseAccess = new DatabaseAccess();
	AccountManager accountManager = new AccountManager(databaseAccess);
	User currentUser = new User("default", databaseAccess);
	JPanel orangeStripe = new JPanel();
	String[] menuItems = { "", "My Fridge App", "", "", "","", "", "", "", "", "", "", "Change Mode", ".", "Fridge", ".",
		        "Recipe Search", ".", "Selected Recipes", ".", "Shopping List", ".", "Favorites" };

	public Frigo getFrigo() {
        return frigo;
	}

	public boolean getMode(){
		return Bobmode;
	}
	
	private void setmenuItems(String[] menuitems){
		this.menuItems = menuitems;
	}

	private void switchMode() {
		orangeStripe.removeAll(); // Remove all components from orangeStripe
		menuButtons.clear(); // Clear the existing buttons list
	
		// Update the menuItems array based on the mode
		if (Bobmode) {
			Bobmode = false;
			setmenuItems(new String[] { "", "My Fridge App", "", "", "","", "", "", "", "", "", "", "Change Mode", ".", "Fridge", ".",
            "Recipe Search", ".", "Selected Recipes",  ".", "Shopping List"});
		} else {
			Bobmode = true;
			setmenuItems(new String[] { "", "My Fridge App", "", "", "","", "", "", "", "", "", "", "Change Mode", ".", "Fridge", ".",
            "Recipe Search", ".", "Selected Recipes", ".", "Shopping List", ".", "Favorites" });
		}

		// Recreate the buttons
		createMenuButtons();
		
		// Refresh the orangeStripe panel to show the new buttons
		orangeStripe.revalidate();
		orangeStripe.repaint();
	}

	private void createMenuButtons() {
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

			button.addActionListener(e -> handleMenuItemClick(item,false));

			// Add rigid area for vertical spacing between buttons
			orangeStripe.add(Box.createRigidArea(new Dimension(0, 10)));
			orangeStripe.add(button);
			menuButtons.add(button);

			// Apply a condition for the title
			if (i == 1) {
				button.setFont(new Font("Tahoma", Font.PLAIN, 40));
			}
		}
	
	}
	
	private void updateFridgePanel() {
        // Update your UI components that depend on the current fridge
        // For example, update the frigoPanel to display the contents of the current fridge
        if (frigo != null) {
            frigoPanel.setFrigo(frigo);
            frigoPanel.refreshFrigoDisplay();
        }
    }

	// Method to handle switching accounts
	 public void switchAccount(String username) {
        accountManager.switchAccount(username);
        currentUser = accountManager.getCurrentUser();
        Frigo currentFridge = accountManager.getFridge(currentUser);
        // Optionally, select a default fridge or let the user choose
        this.frigo = currentFridge;
        // Update the interface to reflect the new user's fridge
        updateFridgePanel();
    }
    

	// Constructor to set up the main interface of the application.
	public Interface() {
		Bobmode = true;
		accountManager.createAccount("default");
		frigo = currentUser.getFridge();
		System.out.println("Account : " + currentUser + " " + currentUser.getFridge().getId());

		menuButtons = new ArrayList<>();

		// Setting up card layout to switch between different panels
		cardLayout = new CardLayout();
		cardPanel = new JPanel(cardLayout);

		// Initializing the panels for the fridge and recipe search
		frigoPanel = new FrigoPanel(frigo, Bobmode);
		recipesPanel = new RecipesPanel(cardPanel, cardLayout);
		selectedRecipePanel = new SelectedRecipePanel(recipesPanel);
		favoriteRecipePanel = new FavoritePanel(recipesPanel);


		// Welcome Panel
		WelcomePanel welcomePanel = new WelcomePanel(frigo, this, accountManager, Bobmode);
		cardPanel.add(welcomePanel, "Welcome");
		
		// Loading Panel
		LoadingPanel loadingPanel = new LoadingPanel();
		cardPanel.add(loadingPanel, "Loading");

		// Adding other panels to the card layout
		cardPanel.add(frigoPanel, "Fridge");
		cardPanel.add(recipesPanel, "Recipe Search");
		cardPanel.add(selectedRecipePanel, "SelectedRecipe");
		cardPanel.add(favoriteRecipePanel, "Favorites");

		// Create the orange stripe panel
		orangeStripe.setBackground(Color.ORANGE);
		int stripeWidth = getWidth() / 6; // Adjust the width fraction as needed
		orangeStripe.setPreferredSize(new Dimension(stripeWidth, getHeight()));

		// Use BoxLayout for vertical arrangement of buttons
		orangeStripe.setLayout(new BoxLayout(orangeStripe, BoxLayout.Y_AXIS));

		this.switchMode();
		
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

		// Call to startExpirationCheckTimer
		startExpirationCheckTimer();

	}

	public void setOwner(User user){
		this.currentUser=user;
	}

	// Handles menu item clicks to switch between panels
	private void handleMenuItemClick(String itemName, boolean initial) {
		switch (itemName) {
			case "My Fridge App":
			if (!initial) {
				// Check if welcomePanel is not null before trying to remove it
				if (welcomePanel != null) {
					cardPanel.remove(welcomePanel);
				}
				// Reinitialize welcomePanel and add it back
				welcomePanel = new WelcomePanel(currentUser.getFridge(), this, accountManager, Bobmode);
				cardPanel.add(welcomePanel, "Welcome");
			}
			cardLayout.show(cardPanel, "Welcome");
			break;
		case "Fridge":
			currentUser=accountManager.getCurrentUser();
			// Update the content of the FrigoPanel
			cardPanel.remove(frigoPanel);
			frigoPanel = new FrigoPanel(currentUser.getFridge(),getMode());
			cardPanel.add(frigoPanel, "Fridge");

			cardLayout.show(cardPanel, "Fridge"); // Display the updated FrigoPanel
			System.out.println("Account : " + currentUser + " " + currentUser.getFridge().getId());
			break;
		case "Recipe Search":
            Object[] options = {"New Search", "Show last research"};
            int choice = JOptionPane.showOptionDialog(this, "Choose an option:", "Recipe Search", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

            if (choice == 0) {
                // Show the loading panel while processing the new search
                cardLayout.show(cardPanel, "Loading");

                // Perform the search in a separate thread
                new Thread(() -> {
                    List<Ingredient> selectedIngredients = frigoPanel.getSelectedIngredients();
                    List<Recipe> recipes = RecipeFinder.searchRecipes(selectedIngredients);
                    SwingUtilities.invokeLater(() -> {
                        System.out.println("List of found recipes:");
                        for (Recipe recipe : recipes) {
                            System.out.println("Recipe: " + recipe.getName());
                            System.out.println("Image URL: " + recipe.getImageUrl());
                            System.out.println();
                        }
                        recipesPanel.displayRecipes(recipes);
                        cardLayout.show(cardPanel, "Recipe Search");
                    });
                }).start();
            } else if (choice == 1) {
                // Show the loading panel while fetching the last research
                cardLayout.show(cardPanel, "Loading");

                // Perform the fetching in a separate thread
                new Thread(() -> {
                    List<Recipe> recipes = DatabaseAccess.returnRecipeList();
                    SwingUtilities.invokeLater(() -> {
                        System.out.println("List of last researched recipes:");
                        for (Recipe recipe : recipes) {
                            System.out.println("Recipe: " + recipe.getName());
                            System.out.println("Image URL: " + recipe.getImageUrl());
                            System.out.println();
                        }
                        recipesPanel.displayRecipes(recipes);
                        cardLayout.show(cardPanel, "Recipe Search");
                    });
                }).start();
            }
            break;
		case "Selected Recipes":
            selectedRecipePanel.displaySelectedRecipes();
            cardLayout.show(cardPanel, "SelectedRecipe");
            break;
		case "Shopping List":
            ShoppingCartPanel shoppingCartPanel = new ShoppingCartPanel(this, selectedRecipePanel);
            cardPanel.add(shoppingCartPanel, "ShoppingCart"); 
            cardLayout.show(cardPanel, "ShoppingCart"); 
            break;
		case "Favorites":
			favoriteRecipePanel.displayFavoriteRecipes();
			cardLayout.show(cardPanel, "Favorites");
			break;
		case "Change Mode":
			this.switchMode();
		}
	}

	public void startExpirationCheckTimer() {
        Timer timer = new Timer();
        
        // Scheduled task to check expiration dates every 1 minute
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                checkAndDisplayExpiredIngredients();
				checkAndDisplaySoonExpiredIngredients();
            }
        }, 1000*60, 1000*60); // Start after 1 minute and repeat every minute
    }

    // Method to check and display expired ingredients
    private void checkAndDisplayExpiredIngredients() {
		if (alertDisplayed) {
			// An alert is already displayed, do nothing
			return;
		}
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
			alertDisplayed = true; // Update alert status
			
			
		}
    }

	// Method to check and display soon expired ingredients
    private void checkAndDisplaySoonExpiredIngredients() {
		if (alertDisplayed2) {
			// An alert is already displayed, do nothing
			return;
		}
        List<Ingredient> soonexpiredIngredients = DatabaseAccess.getSoonExpiredIngredients();
        if (!soonexpiredIngredients.isEmpty()) {
            // Build the message to display in the dialog box
            StringBuilder message = new StringBuilder("The following ingredients will soon expire :\n");

            for (Ingredient ingredient : soonexpiredIngredients) {
                message.append("- ").append(ingredient.getName()).append("\n");
            }

            // Display the dialog box
			SwingUtilities.invokeLater(() -> {
				JOptionPane.showMessageDialog(null, message.toString(), "Soon expired ingredients", JOptionPane.WARNING_MESSAGE);
			});
			alertDisplayed2 = true; // Update alert status
			
			
		}
    }

	public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Interface app = new Interface();
            app.handleMenuItemClick("My Fridge App",true); // Display the WelcomePanel initially
        });
    }

}
