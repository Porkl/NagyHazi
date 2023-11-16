package game;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SettingsMenu {

    private Window window;
    private Options options;
    private JPanel settingsPanel;

    private JTextField rowField;
    private JTextField columnField;
    
    public SettingsMenu(Window window, Options options) {
        this.window = window;
        this.options = options;
        settingsPanel = new JPanel(new GridLayout(4, 2));
        rowField = new JTextField();
        columnField = new JTextField();

        JLabel headerLabel = new JLabel("Settings");
        JLabel emptyLabel = new JLabel();
        JLabel rowLabel = new JLabel("Row Count: ");
        JLabel columnLabel = new JLabel("Column Count: ");
        JButton saveButton = new JButton("Save");
        JButton backButton = new JButton("Back to Main Menu");

        ActionListener buttonListener = new ButtonListener();

        saveButton.addActionListener(buttonListener);
        backButton.addActionListener(buttonListener);

        settingsPanel.add(headerLabel);
        settingsPanel.add(emptyLabel);
        settingsPanel.add(rowLabel);
        settingsPanel.add(rowField);
        settingsPanel.add(columnLabel);
        settingsPanel.add(columnField);
        settingsPanel.add(saveButton);
        settingsPanel.add(backButton);
    }

    private class ButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            switch (e.getActionCommand()) {
                case "Save":
                    options.setRowCount(Integer.parseInt(rowField.getText()));
                    options.setColumnCount(Integer.parseInt(columnField.getText()));
                    window.getGameUI().reDrawBoard(options);
                    break;

                case "Back to Main Menu":
                    settingsPanel.setVisible(false);
                    window.remove(settingsPanel);
                    window.add(window.getMainMenu().getMainPanel());
                    window.getMainMenu().getMainPanel().setVisible(true);
                    break;

                default:
                    break;
            }        
        }
    }

    public JPanel getSettingsPanel() {
        return settingsPanel;
    }
}
