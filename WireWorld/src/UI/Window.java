package UI;

import javax.swing.JFrame;

import game.GameLogic;

public class Window extends JFrame{
    private GameLogic logic;

    private MainMenu mainMenu;
    private SettingsMenu settingsMenu;
    private GameUI gameUI;

    /**
     * A játék ablakát létrehozó, és fenntartó Window konstruktora. Ő hozza létre a többi menüt is.
     * 
     * @param logic : A játék logikája
     */
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
    
    /**
     * A játék logikáját visszaadó getter függvény
     * 
     * @return A játék logikája
     */
    public GameLogic getLogic() {
        return logic;
    }

    /**
     * A játék logikáját lehet vele állítani, setter függvény
     * 
     * @param logic : A játék logikája
     */
    public void setLogic(GameLogic logic) {
        this.logic = logic;
    }
    
    /**
     * A játék főmenüjét visszaadó getter függvény
     * 
     * @return A játék főmenüje
     */
    public MainMenu getMainMenu() {
        return mainMenu;
    }

    /**
     * A játék Settings menüjét visszaadó getter függvény
     * 
     * @return A játék Settings menüje
     */
    public SettingsMenu getSettingsMenu() {
        return settingsMenu;
    }

    /**
     * A játék GameUI-át visszaadó getter függvény
     * 
     * @return A játék GameUI-a
     */
    public GameUI getGameUI() {
        return gameUI;
    }
}
