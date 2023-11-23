package fise2.info3;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class testSwing {

	public static void main(String[] args) {
		JFrame window = new JFrame("Première appli");
	    window.add(new JLabel("Hello world!"));
	    window.setSize(700,500);
	    window.setLocationRelativeTo(null);
	    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    window.setVisible(true);
	}

}
