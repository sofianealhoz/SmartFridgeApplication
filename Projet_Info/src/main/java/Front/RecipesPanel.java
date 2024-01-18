package Front;

import Back.Recipe;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class RecipesPanel extends JPanel {
    private JPanel cardPanel;
    private CardLayout cardLayout;
    private List<Recipe> selectedRecipes = new ArrayList<>();
    private RecipeDetailPanel recipeDetailPanel; // Assuming this is defined elsewhere

    public RecipesPanel(JPanel cardPanel, CardLayout cardLayout) {
        this.cardPanel = cardPanel;
        this.cardLayout = cardLayout;
        recipeDetailPanel = new RecipeDetailPanel(); // Initialize it if necessary
        recipeDetailPanel.setVisible(false); // Hide the RecipeDetailPanel by default
        setLayout(new BorderLayout());
    }

    public List<Recipe> getSelectedRecipes() {
        return selectedRecipes;
    }

    public void displayRecipes(List<Recipe> recipes) {
        JPanel innerPanel = new JPanel();
        innerPanel.setLayout(new BoxLayout(innerPanel, BoxLayout.Y_AXIS));
        innerPanel.setBackground(new Color(245, 246, 250)); // Set background color outside the loop

        for (Recipe recipe : recipes) {
            JPanel recipePanel = new JPanel();
            recipePanel.setLayout(new BoxLayout(recipePanel, BoxLayout.Y_AXIS));
            recipePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
            recipePanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1, true),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));

            recipePanel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    SwingUtilities.invokeLater(() -> {
                        showRecipeDetails(recipe, e);
                        System.out.println("Selected Recipes: " + selectedRecipes);
                    });
                }
            });

            JLabel titleLabel = new JLabel("<html><u>" + recipe.getName() + "</u></html>");
            titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
            titleLabel.setForeground(Color.ORANGE);
            titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
            recipePanel.add(titleLabel);

            ImageIcon recipeImage = loadImageIcon(recipe.getImageUrl(), 200, 200);
            JLabel imageLabel = new JLabel(recipeImage);
            imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            recipePanel.add(imageLabel);

            JButton selectButton = new JButton("✔") {
                @Override
                protected void paintComponent(Graphics g) {
                    if (getModel().isArmed()) {
                        g.setColor(Color.GREEN); // Couleur plus foncée lors du clic
                    } else {
                        g.setColor(Color.BLUE); // Couleur normale du bouton
                    }
                    g.fillOval(6, 0, getSize().width - 14, getSize().height -1);
                    super.paintComponent(g);
                }

                @Override
                protected void paintBorder(Graphics g) {
                    g.setColor(getForeground()); // Couleur de la bordure
                    g.drawOval(6, 0, getSize().width - 14, getSize().height -1);
                }
            };

            selectButton.setPreferredSize(new Dimension(40, 40)); // Augmenter la taille pour un aspect plus rond
            selectButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            selectButton.setFocusPainted(false);
            selectButton.setBorderPainted(false);
            selectButton.setContentAreaFilled(false);
            selectButton.setForeground(Color.WHITE); // Texte blanc
            selectButton.setFont(new Font("Segoe UI Symbol", Font.BOLD, 16)); // Police plus grande pour le texte

            selectButton.addActionListener(e -> {
                if (!selectedRecipes.contains(recipe)) {
                    selectedRecipes.add(recipe);
                    // Mettez à jour ici les composants de l'interface utilisateur si nécessaire
                }
            });

            recipePanel.add(selectButton);

            innerPanel.add(recipePanel);
            innerPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        }

        JScrollPane scrollPane = new JScrollPane(innerPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(null);

        JLabel pageLabel = new JLabel("Recipes for you");
        pageLabel.setFont(new Font("Segoe UI", Font.BOLD, 40));
        pageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        pageLabel.setForeground(Color.ORANGE);

        pageLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        scrollPane.setColumnHeaderView(pageLabel);

        removeAll();
        add(scrollPane, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    private ImageIcon loadImageIcon(String imageUrl, int width, int height) {
        try {
            URL url = new URL(imageUrl);
            Image image = ImageIO.read(url).getScaledInstance(width, height, Image.SCALE_SMOOTH);
            return new ImageIcon(image);
        } catch (IOException e) {
            e.printStackTrace();
            return new ImageIcon();
        }
    }

    private void showRecipeDetails(Recipe recipe, MouseEvent e) {
        RecipeDetailPanel detailPanel = new RecipeDetailPanel();
        detailPanel.displayRecipe(recipe);
        detailPanel.show(e.getComponent(), e.getX(), e.getY()); // Show as a popup at the click position
    }
}
