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

public class SelectedRecipePanel extends JPanel {
    private JLabel recipeDetails;
    private RecipesPanel recipesPanel; // Add a reference to RecipesPanel
    private List<Recipe> selectedRecipes;

    public SelectedRecipePanel(RecipesPanel recipesPanel) {
        this.recipesPanel = recipesPanel; // Initialize the reference to RecipesPanel
        recipeDetails = new JLabel();
        this.add(recipeDetails);
    }
    
    public void displaySelectedRecipes() {
        JPanel innerPanel = new JPanel();
        innerPanel.setLayout(new BoxLayout(innerPanel, BoxLayout.Y_AXIS));
        selectedRecipes = recipesPanel.getSelectedRecipes();
        
        for (Recipe recipe : selectedRecipes) {
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
            titleLabel.setFont(new Font("Serif", Font.BOLD, 24));
            titleLabel.setForeground(new Color(44, 62, 80));
            titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            recipePanel.add(titleLabel);

            ImageIcon recipeImage = loadImageIcon(recipe.getImageUrl(), 200, 200);
            JLabel imageLabel = new JLabel(recipeImage);
            imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            recipePanel.add(imageLabel);

            innerPanel.add(recipePanel);
            innerPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        }

        JScrollPane scrollPane = new JScrollPane(innerPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(null);

        JLabel pageLabel = new JLabel("Recipes Selected");
        pageLabel.setFont(new Font("Serif", Font.BOLD, 40));
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
