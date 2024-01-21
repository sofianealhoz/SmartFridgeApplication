package Front;
import javax.swing.*;
import java.awt.*;

public class LoadingPanel extends JPanel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel loadingLabel;
    private Timer timer;
    private final String loadingText = "Loading";
    private int dotCount = 0;

    public LoadingPanel() {
        setLayout(new GridBagLayout()); 
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.CENTER;

        loadingLabel = new JLabel(loadingText, JLabel.CENTER);
        loadingLabel.setFont(new Font("Serif", Font.BOLD, 36)); 
        add(loadingLabel, gbc); 
        loadingLabel.setHorizontalAlignment(JLabel.CENTER);
        loadingLabel.setVerticalAlignment(JLabel.CENTER);

        // Updating the label every 300ms
        timer = new Timer(300, e -> {
            if (dotCount < 3) {
                dotCount++;
            } else {
                dotCount = 0;
            }

            StringBuilder text = new StringBuilder(loadingText);
            for (int i = 0; i < dotCount; i++) {
                text.append(".");
            }
            loadingLabel.setText(text.toString());
        });

        // Start the timer
        timer.start();
    }

    public void stopLoadingAnimation() {
        if (timer != null) {
            timer.stop();
            loadingLabel.setText(loadingText);
        }
    }
}
