package fise2.info3;

import javax.swing.*;
import java.awt.*;

public class TestSwing extends JFrame{
	
	private JTabbedPane tabbedPane;
	
	public TestSwing() {
        frameInit();
        JPanel menuPanel = createMenuPanel();
        add(menuPanel, BorderLayout.WEST);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
	
	private JPanel createMenuPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0,1));

        addMenuItem(panel, "Frigo");
        addMenuItem(panel, "Recherche Recettes");
        addMenuItem(panel, "Recettes Selectionnées");
        addMenuItem(panel, "Liste de courses");
        addMenuItem(panel, "Favoris");

        return panel;
    }
	
	private void addMenuItem(JPanel panel, String itemName) {
        JButton button = new JButton(itemName);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBackground(Color.BLUE);
        button.setForeground(Color.WHITE);
        button.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(button);
    }
	
	@Override
	protected void frameInit() {
		super.frameInit();
        tabbedPane = new JTabbedPane();
        add(tabbedPane);
        tabbedPane.addTab("Onglet par défaut", new JLabel("Hello world!"));
    }

	public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TestSwing());
    }
}
