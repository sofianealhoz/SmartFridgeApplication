package Front;

import Back.Recipe;



import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.util.List;

public class RecipesPanel extends JPanel {
	private JPanel cardPanel;
    private CardLayout cardLayout;
    
    private static final long serialVersionUID = 1L;

    public RecipesPanel(JPanel cardPanel, CardLayout cardLayout) {
        this.cardPanel = cardPanel;
        this.cardLayout = cardLayout;
        setLayout(new BorderLayout());
    }
    
    public void displayRecipes(List<Recipe> recipes) {
    	JPanel innerPanel = new JPanel();
        innerPanel.setLayout(new BoxLayout(innerPanel, BoxLayout.Y_AXIS));

        // Loop to display each recipe
        for (Recipe recipe : recipes) {
            JPanel recipePanel = new JPanel();
            recipePanel.setLayout(new BoxLayout(recipePanel, BoxLayout.Y_AXIS));
            recipePanel.setAlignmentX(Component.CENTER_ALIGNMENT); 
            recipePanel.setBorder(BorderFactory.createCompoundBorder(
            	    BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1, true), 
            	    BorderFactory.createEmptyBorder(10, 10, 10, 10) 
            	));
            innerPanel.setBackground(new Color(245, 246, 250));

            // Show details of recipe instructions
            recipePanel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    showRecipeDetails(recipe);
                }
            });
                        
            // Recipe title
            JLabel titleLabel = new JLabel("<html><u>" + recipe.getName() + "</u></html>");
            titleLabel.setFont(new Font("Serif", Font.BOLD, 24));
            titleLabel.setForeground(new Color(44, 62, 80));
            titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT); 
            recipePanel.add(titleLabel);


            // Recipe image 
            ImageIcon recipeImage = loadImageIcon(recipe.getImageUrl(), 200, 200);
            JLabel imageLabel = new JLabel(recipeImage);
            imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT); 
            recipePanel.add(imageLabel);

            innerPanel.add(recipePanel);
            innerPanel.add(Box.createRigidArea(new Dimension(0, 20))); 
      
        }
        // Scroll pane for scrolling through the recipe cards
        JScrollPane scrollPane = new JScrollPane(innerPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(null); 
        
        
        
        // Title for the page
        JLabel pageLabel = new JLabel("Recipes for you");
        pageLabel.setFont(new Font("Serif", Font.BOLD, 40));
        pageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        pageLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0)); 
        scrollPane.setColumnHeaderView(pageLabel);
        
        removeAll(); 
        add(scrollPane, BorderLayout.CENTER); 
        
        revalidate();
        repaint();
 
        System.out.println("displayRecipes called with " + recipes.size() + " recipes.");
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
  
    private void showRecipeDetails(Recipe recipe) {
        RecipeDetailPanel detailPanel = new RecipeDetailPanel(recipe, cardLayout, cardPanel);
        cardPanel.add(detailPanel, "RecipeDetails");
        cardLayout.show(cardPanel, "RecipeDetails");

    }
    
    
}
