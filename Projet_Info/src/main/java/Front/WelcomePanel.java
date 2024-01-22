package Front;

import Back.AccountManager;
import Back.Frigo;
import Back.User;

import javax.swing.border.EmptyBorder;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class WelcomePanel extends JPanel {
    private static final long serialVersionUID = 1L;

    private JTextField usernameField;
    private JButton createAccountButton;
    private JButton switchAccountButton;
    private JComboBox<String> accountComboBox; // Dropdown for existing accounts
    private Interface mainInterface;  // Reference to the main interface
    private AccountManager accountManager; // Account manager for handling user accounts
    private JComboBox<String> allergyComboBox;
    private JTextField allergyField;
    private JButton addAllergyButton;
    private JButton removeAllergyButton;

    public WelcomePanel(Frigo frigo, Interface mainInterface, AccountManager accountManager, boolean Bobmode) {
        this.mainInterface = mainInterface;
        this.accountManager = accountManager;
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(400, 300));
        setBackground(new Color(245, 245, 245));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    
        // Welcome message
        JLabel welcomeLabel = new JLabel("Welcome to your Fridge App!", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Segoe UI", Font.BOLD, 40)); 
        welcomeLabel.setForeground(Color.ORANGE); 
        welcomeLabel.setBorder(new EmptyBorder(10, 10, 10, 10));
        add(welcomeLabel, BorderLayout.NORTH);
    
        // Container panel for user account and allergy panels
        JPanel containerPanel = new JPanel();
        containerPanel.setLayout(new BoxLayout(containerPanel, BoxLayout.Y_AXIS));
        containerPanel.setBackground(new Color(245, 245, 245));

        // User Account Panel
        JPanel userAccountPanel = createUserAccountPanel();
        userAccountPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, userAccountPanel.getPreferredSize().height));
        containerPanel.add(userAccountPanel);
    
        if (Bobmode){
            // Allergy Management Panel
            JPanel allergyPanel = createAllergyPanel(frigo);
            allergyPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, allergyPanel.getPreferredSize().height));
            containerPanel.add(allergyPanel);
        }
        
    
        add(containerPanel, BorderLayout.SOUTH);
    
        // Instructional text
        JLabel instructionLabel = new JLabel("<html><div style='text-align: center;'>" +
            "To get started, go to 'Fridge' then add ingredients to your fridge using the 'Add Ingredient' button.<br/>" +
            "If you've already added ingredients, feel free to explore the other options!</div></html>",
            SwingConstants.CENTER);
        instructionLabel.setFont(new Font("Segoe UI", Font.PLAIN, 22)); 
        instructionLabel.setForeground(Color.ORANGE); 
        add(instructionLabel, BorderLayout.CENTER);
    }

    private JPanel createUserAccountPanel() {
        JPanel userAccountPanel = new JPanel();
        userAccountPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        userAccountPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
    
        usernameField = new JTextField();
        usernameField.setPreferredSize(new Dimension(150, 25)); // Réduire la largeur
        createAccountButton = new JButton("Log Account");
        switchAccountButton = new JButton("Switch Account");
        accountComboBox = new JComboBox<>(); // Populate this with existing usernames
        accountComboBox.setPreferredSize(new Dimension(150, 25)); // Réduire la largeur
    
        userAccountPanel.add(new JLabel("Username:"));
        userAccountPanel.add(usernameField);
        userAccountPanel.add(createAccountButton);
        userAccountPanel.add(new JLabel("Select Account:"));
        userAccountPanel.add(accountComboBox);
        userAccountPanel.add(switchAccountButton);

        createAccountButton.addActionListener(e -> createAccount());
        switchAccountButton.addActionListener(e -> switchAccount());

        return userAccountPanel;
    }

    private JPanel createAllergyPanel(Frigo frigo) {
        JPanel allergyPanel = new JPanel();
        allergyPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        allergyPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
    
        allergyField = new JTextField();
        allergyField.setPreferredSize(new Dimension(150, 25)); // Réduire la largeur
        addAllergyButton = new JButton("Add Allergy");
        allergyComboBox = new JComboBox<>(); // Populate with existing allergies
        allergyComboBox.setPreferredSize(new Dimension(150, 25)); // Réduire la largeur
        removeAllergyButton = new JButton("Remove Allergy");
    

        allergyPanel.add(new JLabel("Allergy:"));
        allergyPanel.add(allergyField);
        allergyPanel.add(addAllergyButton);
        allergyPanel.add(new JLabel("Existing Allergies:"));
        allergyPanel.add(allergyComboBox);
        allergyPanel.add(removeAllergyButton);

        
        User currentUser = accountManager.getCurrentUser();

        addAllergyButton.addActionListener(e -> addAllergy(currentUser));
        removeAllergyButton.addActionListener(e -> removeAllergy(currentUser));

        return allergyPanel;
    }


    private void createAccount() {
        String username = usernameField.getText();

        if (username.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Username cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (accountManager.createAccount(username)) {
            JOptionPane.showMessageDialog(this, "Account loged successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
            accountComboBox.addItem(username);
            accountManager.createAccount(username);
            mainInterface.switchAccount(username); 
            
              // Refresh UI components to reflect the new user's data
            User currentUser = accountManager.getCurrentUser();
            refreshAllergyComboBox(currentUser);
             
        } else {
            JOptionPane.showMessageDialog(this, "Account login failed.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void switchAccount() {
        String selectedUser = (String) accountComboBox.getSelectedItem();
        if (selectedUser == null) {
            JOptionPane.showMessageDialog(this, "No account selected.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
    
        accountManager.switchAccount(selectedUser);
        User selectedUserObject = accountManager.switchAccount(selectedUser); // Get the selected user
        if (selectedUserObject != null) {
            // Display allergies for the selected user
            refreshAllergyComboBox(selectedUserObject);
            JOptionPane.showMessageDialog(this, "Switched to account: " + selectedUser, "Success", JOptionPane.INFORMATION_MESSAGE);
            System.out.println( "Switched to account: " + accountManager.getCurrentUser());
            System.out.println("Allergies de l'utilisateur '" + selectedUser + "': " + selectedUserObject.getAllergies());
        } else {
            JOptionPane.showMessageDialog(this, "Account switch failed.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void addAllergy(User currentUser) {
        if (accountManager.getCurrentUser() != null) { // Check if currentUser is not null
            String allergy = allergyField.getText().trim();
            if (!allergy.isEmpty()) {
                accountManager.getCurrentUser().addAllergy(allergy); // Add the allergy to the current user
                refreshAllergyComboBox(accountManager.getCurrentUser());
                System.out.println("Allergy added: " + allergy);
                System.out.println("Allergies de l'utilisateur '" + accountManager.getCurrentUser() + "': " + accountManager.getCurrentUser().getAllergies() + "fridge :" + accountManager.getCurrentUser().getFridge().getId());
            }
        }
    }
    
    
    private void removeAllergy(User currentUser) {
        String selectedAllergy = (String) allergyComboBox.getSelectedItem();
        if (selectedAllergy != null) {
            accountManager.getCurrentUser().removeAllergy(selectedAllergy); // Remove the allergy from the current user
            refreshAllergyComboBox(accountManager.getCurrentUser());
            System.out.println("Allergy removed: " + selectedAllergy);
            System.out.println("Allergies de l'utilisateur '" + accountManager.getCurrentUser() + "': " + accountManager.getCurrentUser().getAllergies());
        }
    }
    
    private void refreshAllergyComboBox(User currentUser) {
        allergyComboBox.removeAllItems(); // Remove all items
        for (String allergy : accountManager.getCurrentUser().getAllergies()) {
            allergyComboBox.addItem(allergy); // Add each allergy to the combo box
        }
    }

}