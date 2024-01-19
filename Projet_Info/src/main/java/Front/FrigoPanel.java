package Front;

import javax.swing.*;

import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import Back.DatabaseAccess;
import Back.Frigo;
import Back.Ingredient;

public class FrigoPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    private JTable ingredientsTable;
    private Frigo fridge;

    public FrigoPanel(Frigo fridge) {
        this.fridge = fridge;
        setLayout(new BorderLayout());

        // Create a table 
        String[] columnNames = {"Name", "Quantity", "Expiration Date", "Category", "Select"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0) {
            /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
            public Class<?> getColumnClass(int columnIndex) {
                return columnIndex == 4 ? Boolean.class : String.class;
            }
        };

        ingredientsTable = new JTable(tableModel);
        ingredientsTable.setFillsViewportHeight(true);
        ingredientsTable.setAutoCreateRowSorter(true);
        styleTable();

        // Scroll pane for text area
        JScrollPane scrollPane = new JScrollPane(ingredientsTable);
        add(scrollPane, BorderLayout.CENTER);
        
     // Button to delete selected ingredient
     		JButton deleteIngredientButton = new JButton("Delete Ingredient");
     		deleteIngredientButton.setFont(new Font("SansSerif", Font.BOLD, 18));
     		deleteIngredientButton.setForeground(new Color(60, 60, 60));
     		deleteIngredientButton.setBackground(new Color(200, 200, 200));
     		deleteIngredientButton.setBorder(BorderFactory.createRaisedBevelBorder());
     		deleteIngredientButton.setFocusPainted(false);
     		deleteIngredientButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

     		deleteIngredientButton.addActionListener(new ActionListener() {
     			@Override
     			public void actionPerformed(ActionEvent e) {
     				int selectedRow = ingredientsTable.getSelectedRow();

     				if (selectedRow != -1) {
     					// Get the name of the ingredient in the selected row
     					String ingredientName = (String) ingredientsTable.getValueAt(selectedRow, 0);

     					// Call a method to delete the ingredient from the fridge and database
     					fridge.removeIngredientByName(ingredientName);

     					// Refresh the ingredients table
     					refreshIngredientsTable();
     				} else {
     					JOptionPane.showMessageDialog(FrigoPanel.this, "Please select an ingredient to delete.", "Error",
     							JOptionPane.ERROR_MESSAGE);
     				}
     			}
     		});

        

    		// Button to add new ingredients
    		JButton addIngredientButton = new JButton("Add Ingredient");
    		addIngredientButton.setFont(new Font("SansSerif", Font.BOLD, 18)); // Set font
    		addIngredientButton.setForeground(new Color(60, 60, 60)); // Set a dark gray text color
    		addIngredientButton.setBackground(new Color(200, 200, 200)); // Set a light gray background color
    		addIngredientButton.setBorder(BorderFactory.createRaisedBevelBorder()); // Set border for a 3D effect
    		addIngredientButton.setFocusPainted(false); // Remove focus ring around text
    		addIngredientButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

    		addIngredientButton.addActionListener(new ActionListener() {
    			@Override
    			public void actionPerformed(ActionEvent e) {
    				displayAddIngredientDialog();
    			}
    		});
        
    		// Button to update ingredient quantity
    		JButton updateQuantityButton = new JButton("Update Quantity");
    		updateQuantityButton.setFont(new Font("SansSerif", Font.BOLD, 18));
    		updateQuantityButton.setForeground(new Color(60, 60, 60));
    		updateQuantityButton.setBackground(new Color(200, 200, 200));
    		updateQuantityButton.setBorder(BorderFactory.createRaisedBevelBorder());
    		updateQuantityButton.setFocusPainted(false);
    		updateQuantityButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

    		updateQuantityButton.addActionListener(new ActionListener() {
    			@Override
    			public void actionPerformed(ActionEvent e) {
    				int selectedRow = ingredientsTable.getSelectedRow();

    				if (selectedRow != -1) {
    					// Get the name of the ingredient in the selected row
    					String ingredientName = (String) ingredientsTable.getValueAt(selectedRow, 0);
    					String quantityText = JOptionPane.showInputDialog(FrigoPanel.this, "Enter new quantity for " + ingredientName + ":");
    					
    					if (quantityText != null) {
    						try {
    							int quantity = Integer.parseInt(quantityText);
    							
    							// Call a method to update the ingredient quantity in the database
    							DatabaseAccess.callUpdateIngredientQuantity(ingredientName, quantity);
    							
    							// Refresh the ingredients table
    							refreshIngredientsTable();
    						} catch (NumberFormatException ex) {
    							JOptionPane.showMessageDialog(FrigoPanel.this, "Invalid quantity format.", "Error",
    									JOptionPane.ERROR_MESSAGE);
    						}
    					}
    				} else {
    					JOptionPane.showMessageDialog(FrigoPanel.this, "Please select an ingredient to update.", "Error",
    							JOptionPane.ERROR_MESSAGE);
    				}
    			}
    		});
        

        // Add "What's in my fridge" title
        JLabel titleLabel = new JLabel("What's in my fridge", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 40));
        titleLabel.setForeground(new Color(44, 62, 80));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        add(titleLabel, BorderLayout.NORTH);

     // Create a panel for the buttons
     		JPanel buttonPanel = new JPanel();
     		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
     		buttonPanel.add(deleteIngredientButton);
     		buttonPanel.add(addIngredientButton);
     		buttonPanel.add(updateQuantityButton);
     		add(buttonPanel, BorderLayout.SOUTH);

     		refreshIngredientsTable();
     	}


    // Style of the table
    private void styleTable() {
        // Style for the table header
        ingredientsTable.getTableHeader().setFont(new Font("SansSerif", Font.ITALIC, 16));
        ingredientsTable.getTableHeader().setBackground(new Color(153, 204, 255));
        ingredientsTable.getTableHeader().setForeground(Color.BLACK);
        // Style for the table cells
        ingredientsTable.setFont(new Font("SansSerif", Font.PLAIN, 20));
        ingredientsTable.setBackground(Color.WHITE);
        ingredientsTable.setForeground(Color.BLACK);
        ingredientsTable.setRowHeight(25);
    }

    // Shows a dialog to add a new ingredient to the fridge
    void displayAddIngredientDialog() {
        JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(this);

        JDialog dialog = new JDialog(parentFrame, "Add an Ingredient", true);
        JPanel panel = new JPanel(new GridLayout(0, 2));

        // Form fields for ingredient details
        JLabel nameLabel = new JLabel("Ingredient Name:");
        JTextField nameField = new JTextField();
        JLabel quantityLabel = new JLabel("Quantity:");
        JTextField quantityField = new JTextField();
        JLabel expirationLabel = new JLabel("Expiration Date (yyyy-MM-dd):");
        JTextField expirationField = new JTextField();
        JLabel categoryLabel = new JLabel("Category:");
        String[] categories = {"Meat", "Vegetable", "Fruit", "Dairy", "Grain", "Spice", "Other"};
        JComboBox<String> categoryComboBox = new JComboBox<>(categories);

        JButton addButton = new JButton("Add");

        // Action listener for adding the ingredient
        addButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String name = nameField.getText();
				String quantityText = quantityField.getText();
				String expirationText = expirationField.getText();
				String selectedCategory = (String) categoryComboBox.getSelectedItem();
				// Validation of input fields
				if (name.isEmpty() || quantityText.isEmpty() || expirationText.isEmpty()) {
					JOptionPane.showMessageDialog(dialog, "Please fill in all fields.", "Error",
							JOptionPane.ERROR_MESSAGE);
				} else {
					try {
						int quantity = Integer.parseInt(quantityText);
						LocalDate expirationDate = LocalDate.parse(expirationText);

						// Check if the ingredient name already exists
						if (fridge.hasIngredient(name)) {
							throw new IllegalArgumentException("Ingredient already exists.");
						}

						// Adding the new ingredient to the fridge
						fridge.addIngredient(new Ingredient(name, expirationDate, quantity, selectedCategory));
						DatabaseAccess.callInsertIngredient(name, quantityText, expirationText, selectedCategory);

						// Refreshing the ingredients display
						refreshIngredientsTable();

						dialog.dispose();
					} catch (NumberFormatException | DateTimeParseException ex) {
						JOptionPane.showMessageDialog(dialog, "Invalid date or quantity format.", "Error",
								JOptionPane.ERROR_MESSAGE);
					} catch (IllegalArgumentException ex) {
						JOptionPane.showMessageDialog(dialog, "Ingredient already exists.", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});



        // Adding form fields to the panel
        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(quantityLabel);
        panel.add(quantityField);
        panel.add(expirationLabel);
        panel.add(expirationField);
        panel.add(categoryLabel);
        panel.add(categoryComboBox);

        // Setting up the dialog layout
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);

        dialog.add(panel, BorderLayout.CENTER);
        dialog.add(buttonPanel, BorderLayout.SOUTH);
        dialog.pack();
        dialog.setLocationRelativeTo(parentFrame);
        dialog.setVisible(true);
    }

    // Refreshes the display of ingredients in the text area
    private void refreshIngredientsTable() {
        DefaultTableModel model = (DefaultTableModel) ingredientsTable.getModel();
        model.setRowCount(0); // Clear table

        fridge.getIngredients().sort(Comparator.comparing(Ingredient::getCategory));
        for (Ingredient ingredient : fridge.getIngredients()) {
            model.addRow(new Object[]{
                    ingredient.getName(),
                    ingredient.getQuantity() + " unit(s)",
                    ingredient.getExpirationDate().toString(),
                    ingredient.getCategory(),
                    false
            });
        }
    }

    // Method to get the ingredients we want to use for recipe search
    public List<Ingredient> getSelectedIngredients() {
        List<Ingredient> selectedIngredients = new ArrayList<>();
        DefaultTableModel model = (DefaultTableModel) ingredientsTable.getModel();
        for (int i = 0; i < model.getRowCount(); i++) {
            Object checkboxValue = model.getValueAt(i, 4);
            if (checkboxValue instanceof Boolean && (Boolean) checkboxValue) {
                String name = (String) model.getValueAt(i, 0);
                double quantity = Double.parseDouble(((String) model.getValueAt(i, 1)).split(" ")[0]);
                int quantityInt = (int) quantity;
                LocalDate expirationDate = LocalDate.parse((String) model.getValueAt(i, 2));
                String category = (String) model.getValueAt(i, 3);

                selectedIngredients.add(new Ingredient(name, expirationDate, quantityInt, category));
            }
        }
        System.out.println("Selected Ingredients: " + selectedIngredients.size());
        return selectedIngredients;
    }



}
