package UI;

import javax.swing.JFrame;

import game.GameLogic;

public class Window extends JFrame{
    private GameLogic logic;

    private MainMenu mainMenu;
    private SettingsMenu settingsMenu;
    private GameUI gameUI;

    public Window(GameLogic logic) {
        this.logic = logic;

        mainMenu = new MainMenu(this);
        settingsMenu = new SettingsMenu(this);
        gameUI = new GameUI(this);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setTitle("WireWorld");

        add(mainMenu.getMainPanel());
    }
    
    public GameLogic getLogic() {
        return logic;
    }

    public void setLogic(GameLogic logic) {
        this.logic = logic;
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
