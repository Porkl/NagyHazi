package game;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JFrame{

    private JPanel mainPanel = new JPanel(new GridLayout(5,1));
    private JPanel settingsPanel = new JPanel(new GridLayout(4, 2));
    private ActionListener buttonListener = new ButtonListener();

    private void initMainMenu() {
        JLabel headerLabel = new JLabel("WireWorld", JLabel.CENTER);
        JButton startButton = new JButton("New WireWorld Map");
        JButton loadButton = new JButton("Load Previous Map");
        JButton settingsButton = new JButton("Settings");
        JButton exitButton = new JButton("Exit the Game");

        startButton.addActionListener(buttonListener);
        loadButton.addActionListener(buttonListener);
        settingsButton.addActionListener(buttonListener);
        exitButton.addActionListener(buttonListener);

        mainPanel.add(headerLabel);
        mainPanel.add(startButton);
        mainPanel.add(loadButton);
        mainPanel.add(settingsButton);
        mainPanel.add(exitButton);
    }

    private void initSettingsMenu() {
        JLabel headerLabel = new JLabel("Settings");
        JLabel emptyLabel = new JLabel();
        JLabel rowLabel = new JLabel("Row Count: ");
        JLabel columnLabel = new JLabel("Column Count: ");
        JTextField rowField = new JTextField();
        JTextField columnField = new JTextField();
        JButton saveButton = new JButton("Save");
        JButton backButton = new JButton("Back to Main Menu");

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

    public MainMenu() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setTitle("WireWorld");

        initMainMenu();
        initSettingsMenu();

        add(mainPanel);
    }

    
    /**
     * ButtonListener
     */
    public class ButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            switch (e.getActionCommand()) {
                case "New WireWorld Map":
                    // startNewGame();
                    break;

                case "Load Previous Map":
                    // startPreviousGame();
                    break;

                case "Settings":
                    mainPanel.setVisible(false);
                    remove(mainPanel);
                    add(settingsPanel);
                    settingsPanel.setVisible(true);
                    break;

                case "Exit the Game":
                    System.exit(0);
                    break;

                case "Save":
                    // saveOptions();
                    break;

                case "Back to Main Menu":
                    settingsPanel.setVisible(false);
                    remove(settingsPanel);
                    add(mainPanel);
                    mainPanel.setVisible(true);
                    break;

                default:
                    break;
            }
        }
    
    }
}
