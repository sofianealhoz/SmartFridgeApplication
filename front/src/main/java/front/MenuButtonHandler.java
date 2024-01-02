package front;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuButtonHandler implements ActionListener {
    private Interface mainInterface;
    private String itemName;

    public MenuButtonHandler(Interface mainInterface, String itemName) {
        this.mainInterface = mainInterface;
        this.itemName = itemName;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        mainInterface.handleMenuItemClick(itemName);
    }
}

