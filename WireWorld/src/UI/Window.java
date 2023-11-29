package UI;

import javax.swing.JFrame;

import game.GameFieldMatrix;

public class Window extends JFrame{
    private GameFieldMatrix matrix;
    private MainMenu mainMenu;
    private SettingsMenu settingsMenu;
    private GameUI gameUI;

    public Window(GameFieldMatrix matrix) {
        this.matrix = matrix;

        mainMenu = new MainMenu(this);
        settingsMenu = new SettingsMenu(this);
        gameUI = new GameUI(this);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setTitle("WireWorld");

        add(mainMenu.getMainPanel());
    }

    public GameFieldMatrix getMatrix() {
        return matrix;
    }

    public void setMatrix(GameFieldMatrix matrix) {
        this.matrix = matrix;
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
