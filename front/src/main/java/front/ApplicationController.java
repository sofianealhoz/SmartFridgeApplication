package front;

import javax.swing.JButton;

import back.Frigo;
import back.RecipeFinder;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ApplicationController {
    private Frigo frigo;
    
    public ApplicationController() {
        frigo = new Frigo();
        initGUI();
    }

    private void initGUI() {
        Interface testSwing = new Interface();
        List<JButton> buttons = testSwing.getMenuButtons();

        // Ajouter des écouteurs d'événements aux boutons du menu
        buttons.get(0).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Logique à exécuter lors du clic sur le bouton "Frigo"
                frigo.displayContents();
            }
        });

        buttons.get(1).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Logique à exécuter lors du clic sur le bouton "Recherche Recettes"
                RecipeFinder.searchRecipes(frigo.getIngredients());
            }
        });

        // Ajouter d'autres écouteurs d'événements pour les autres boutons du menu
    }
}
