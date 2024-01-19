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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.format.DateTimeFormatter;

import Back.DatabaseConnection;
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

        // Add "What's in my fridge" title
        JLabel titleLabel = new JLabel("What's in my fridge", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 40));
        titleLabel.setForeground(new Color(44, 62, 80));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        add(titleLabel, BorderLayout.NORTH);

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
        add(addIngredientButton, BorderLayout.SOUTH);
        refreshIngredientsTable();
        List<Ingredient> ingredientsFromDb = getIngredientsFromDatabase();
        for (Ingredient ingredient : ingredientsFromDb) {
            fridge.addIngredient(ingredient);
            // Ajoutez également l'ingrédient à tableModel
            ((DefaultTableModel) ingredientsTable.getModel()).addRow(new Object[]{
                ingredient.getName(),
                ingredient.getQuantity() + " unit(s)",
                ingredient.getExpirationDate().toString(),
                ingredient.getCategory(),
                false
            });
        }

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
                    JOptionPane.showMessageDialog(dialog, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    try {
                        int quantity = Integer.parseInt(quantityText);
                        LocalDate expirationDate = LocalDate.parse(expirationText);

                        // Adding the new ingredient to the fridge
                        fridge.addIngredient(new Ingredient(name, expirationDate, quantity, selectedCategory));

                        // Adding the new ingredient to the database
                        addIngredientToDatabase(new Ingredient(name, expirationDate, quantity, selectedCategory));

                        // Refreshing the ingredients display
                        refreshIngredientsTable();

                        dialog.dispose();
                    } catch (NumberFormatException | DateTimeParseException ex) {
                        JOptionPane.showMessageDialog(dialog, "Invalid date or quantity format.", "Error", JOptionPane.ERROR_MESSAGE);
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

    // Method to add an ingredient to the database
    public void addIngredientToDatabase(Ingredient ingredient) {
        Connection connection = DatabaseConnection.getConnection();
        if (connection != null) {
            try {
                String insertSQL = "INSERT INTO ingredients (name, expiration_date, quantity, category) VALUES (?, ?, ?, ?)";
                PreparedStatement preparedStatement = connection.prepareStatement(insertSQL);
                preparedStatement.setString(1, ingredient.getName());
                preparedStatement.setString(2, ingredient.getExpirationDate().format(DateTimeFormatter.ISO_LOCAL_DATE));
                preparedStatement.setDouble(3, ingredient.getQuantity()); // Assuming quantity is a double
                preparedStatement.setString(4, ingredient.getCategory());
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Method to retrieve ingredients from the database
    public List<Ingredient> getIngredientsFromDatabase() {
        List<Ingredient> ingredients = new ArrayList<>();
        Connection connection = DatabaseConnection.getConnection();
        if (connection != null) {
            try {
                String selectSQL = "SELECT * FROM ingredients";
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(selectSQL);
                while (resultSet.next()) {
                    String name = resultSet.getString("name");
                    LocalDate expirationDate = LocalDate.parse(resultSet.getString("expiration_date"), DateTimeFormatter.ISO_LOCAL_DATE);
                    double quantity = resultSet.getDouble("quantity");
                    String category = resultSet.getString("category");
                    ingredients.add(new Ingredient(name, expirationDate, (int) quantity, category));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return ingredients;
    }
}
