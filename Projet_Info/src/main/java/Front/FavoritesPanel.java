package Front;

import Back.Recipe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class FavoritesPanel extends JPanel {

    private JPanel cardPanel;
    private CardLayout cardLayout;
    private List<Recipe> favorites;

    public FavoritesPanel(JPanel cardPanel, CardLayout cardLayout, List<Recipe> favorites) {
        this.cardPanel = cardPanel;
        this.cardLayout = cardLayout;
        this.favorites = favorites;

        setLayout(new BorderLayout());
        displayFavorites(favorites);
    }

    public void displayFavorites(List<Recipe> favorites) {
        JPanel innerPanel = new JPanel();
        innerPanel.setLayout(new BoxLayout(innerPanel, BoxLayout.Y_AXIS));

        // Loop pour afficher chaque recette
        for (Recipe recipe : favorites) {
            JPanel recipePanel = createRecipePanel(recipe);
            innerPanel.add(recipePanel);
            innerPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        }

        // Scroll pane pour faire défiler les cartes de recettes
        JScrollPane scrollPane = new JScrollPane(innerPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(null);

        // Titre pour la page
        JLabel pageLabel = new JLabel("Recettes favorites");
        pageLabel.setFont(new Font("Serif", Font.BOLD, 40));
        pageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        pageLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        scrollPane.setColumnHeaderView(pageLabel);

        removeAll();
        add(scrollPane, BorderLayout.CENTER);

        revalidate();
        repaint();
    }

    private JPanel createRecipePanel(Recipe recipe) {
        JPanel recipePanel = new JPanel();
        recipePanel.setLayout(new BoxLayout(recipePanel, BoxLayout.Y_AXIS));
        recipePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        recipePanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1, true),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));

        // Afficher les détails de la recette
        recipePanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                showRecipeDetails(recipe);
            }
        });

        // Titre de la recette
        JLabel titleLabel = new JLabel("<html><u>" + recipe.getName() + "</u></html>");
        titleLabel.setFont(new Font("Serif", Font.BOLD, 24));
        titleLabel.setForeground(new Color(44, 62, 80));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        recipePanel.add(titleLabel);

        // Image de la recette
        ImageIcon recipeImage = loadImageIcon(recipe.getImageUrl(), 200, 200);
        JLabel imageLabel = new JLabel(recipeImage);
        imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        recipePanel.add(imageLabel);

        return recipePanel;
    }

    private ImageIcon loadImageIcon(String imageUrl, int width, int height) {
        // Implémentez le chargement de l'image à partir de l'URL comme dans RecipesPanel
        // Retournez l'ImageIcon résultant
        return new ImageIcon(); // À remplacer
    }

    private void showRecipeDetails(Recipe recipe) {
        RecipeDetailPanel detailPanel = new RecipeDetailPanel(recipe, cardLayout, cardPanel);
        cardPanel.add(detailPanel, "RecipeDetails");
        cardLayout.show(cardPanel, "RecipeDetails");
    }
}
