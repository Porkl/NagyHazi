package game;

import javax.swing.JFrame;

public class Window extends JFrame{
    private Options options;

    private MainMenu mainMenu;
    private SettingsMenu settingsMenu;
    private GameUI gameUI;

    public Window(Options options) {
        this.options = options;
        mainMenu = new MainMenu(this);
        settingsMenu = new SettingsMenu(this, options);
        gameUI = new GameUI(this, options);

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

    public GameUI getGameUI() {
        return gameUI;
    }
}
