package Front;

import Back.Recipe;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FavoritePanel extends JPanel {
    private JLabel recipeDetails;
    private RecipesPanel recipesPanel; // Add a reference to RecipesPanel
    private List<Recipe> favoriteRecipes;

    public FavoritePanel(RecipesPanel recipesPanel) {
        this.recipesPanel = recipesPanel; // Initialize the reference to RecipesPanel
        recipeDetails = new JLabel();
        this.add(recipeDetails);
    }

    public void updateFavoriteRecipes(List<Recipe> favoriteRecipes) {
        this.favoriteRecipes = favoriteRecipes;
    }

    public List<Recipe> getFavoriteRecipe(){
        favoriteRecipes = recipesPanel.getFavoriteRecipes();
        return favoriteRecipes;
    }

    public void displayFavoriteRecipes() {
        JPanel innerPanel = new JPanel();
        innerPanel.setLayout(new BoxLayout(innerPanel, BoxLayout.Y_AXIS));
        favoriteRecipes = this.getFavoriteRecipe();
        
        for (Recipe recipe : favoriteRecipes) {
            JPanel recipePanel = new JPanel();
            recipePanel.setLayout(new BoxLayout(recipePanel, BoxLayout.Y_AXIS));
            recipePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
            recipePanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1, true),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)));
            innerPanel.setBackground(new Color(245, 246, 250));

            recipePanel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    showRecipeDetails(recipe, e); // Méthode existante
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

            JButton selectButton = new JButton("✘") {
                @Override
                protected void paintComponent(Graphics g) {
                    if (getModel().isArmed()) {
                        g.setColor(Color.ORANGE); // Couleur plus foncée lors du clic
                    } else {
                        g.setColor(Color.RED); // Couleur normale du bouton
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
                if (favoriteRecipes.contains(recipe)) {
                    favoriteRecipes.remove(recipe);
                    displayFavoriteRecipes();
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

        JLabel pageLabel = new JLabel("Favorite Recipes");
        pageLabel.setFont(new Font("Segoe UI", Font.BOLD, 40));
        pageLabel.setForeground(Color.ORANGE);
        pageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        pageLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        scrollPane.setColumnHeaderView(pageLabel);

        setLayout(new BorderLayout()); // Set layout for this panel

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
