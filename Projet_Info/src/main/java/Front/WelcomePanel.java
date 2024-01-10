package Front;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.BorderFactory;
import javax.swing.Box;

public class WelcomePanel extends JPanel {

    private static final long serialVersionUID = 1L;

    public WelcomePanel() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(400, 300)); 
        setBackground(new Color(245, 245, 245)); 
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); 
        add(Box.createVerticalStrut(20), BorderLayout.NORTH);
        
        // Welcome message
        JLabel welcomeLabel = new JLabel("Welcome to your Fridge App!", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 50));
        welcomeLabel.setForeground(new Color(25, 25, 112)); 
        add(welcomeLabel, BorderLayout.NORTH);

        // Instructional text
        JLabel instructionLabel = new JLabel("<html><div style='text-align: center;'>" +
            "To get started, go to 'Fridge' then add ingredients to your fridge using the 'Add Ingredient' button.<br/>" +
            "If you've already added ingredients, feel free to explore the other options !</div></html>",
            SwingConstants.CENTER);
        instructionLabel.setFont(new Font("Sher", Font.ITALIC, 25));
        instructionLabel.setForeground(Color.BLACK); 
        add(instructionLabel, BorderLayout.CENTER);
    }
}
