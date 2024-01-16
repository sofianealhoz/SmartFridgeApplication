package Front;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import java.net.URL;

public class WelcomePanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private Image backgroundImage; 

    public WelcomePanel() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(400, 300));
        setBackground(new Color(245, 245, 245));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(Box.createVerticalStrut(20), BorderLayout.NORTH);
        
        // Load the background image
        URL resourceUrl = getClass().getResource("/welcome.png");
        if (resourceUrl != null) {
    System.out.println("Resource URL: " + resourceUrl);
    backgroundImage = new ImageIcon(resourceUrl).getImage();
        } else {
        System.err.println("Resource not found!");
        }
        // Welcome message
        JLabel welcomeLabel = new JLabel("Welcome to your Fridge App!", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Segoe UI", Font.BOLD, 60)); 
        welcomeLabel.setForeground(new Color(78, 52, 46, 255)); 
        welcomeLabel.setBorder(new EmptyBorder(10, 10, 10, 10));
        add(welcomeLabel, BorderLayout.NORTH);

        // Instructional text
        JLabel instructionLabel = new JLabel("<html><div style='text-align: center;'>" +
            "To get started, go to 'Fridge' then add ingredients to your fridge using the 'Add Ingredient' button.<br/>" +
            "If you've already added ingredients, feel free to explore the other options !</div></html>",
            SwingConstants.CENTER);
        instructionLabel.setFont(new Font("Segoe UI", Font.ITALIC, 28)); 
        instructionLabel.setForeground(Color.BLACK); 
        add(instructionLabel, BorderLayout.CENTER);

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        BufferedImage scaledImage = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics2D = scaledImage.createGraphics();
        graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        graphics2D.drawImage(backgroundImage, 0, 0, getWidth() / 2, getHeight() / 2, null);
        g.drawImage(backgroundImage, 0, 0, this);
    }
}
