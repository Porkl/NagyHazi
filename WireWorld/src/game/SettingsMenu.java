package game;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class SettingsMenu {
    
    private JLabel headerLabel = new JLabel("Settings");
    private JLabel rowLabel = new JLabel("Row Count: ");
    private JLabel columnLabel = new JLabel("Column Count: ");

    private JPanel settingsPanel = new JPanel(new GridLayout(3, 2));

}
