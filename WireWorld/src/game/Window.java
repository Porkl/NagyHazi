package game;

import javax.swing.JFrame;

public class Window extends JFrame{

    private MainMenu mainMenu = new MainMenu(this);
    private SettingsMenu settingsMenu = new SettingsMenu(this);

    public Window() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setTitle("WireWorld");

        add(mainMenu.getMainPanel());
    }

    public MainMenu getMainMenu() {
        return mainMenu;
    }

    public SettingsMenu getSettingsMenu() {
        return settingsMenu;
    }
}
