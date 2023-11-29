package UI;

import javax.swing.JFrame;

import game.GameFieldMatrix;
import game.Options;

public class Window extends JFrame{
    private MainMenu mainMenu;
    private SettingsMenu settingsMenu;
    private GameUI gameUI;

    public Window(Options options, GameFieldMatrix matrix) {
        mainMenu = new MainMenu(this);
        settingsMenu = new SettingsMenu(this, options);
        gameUI = new GameUI(this, matrix);

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
