package game;

public class Main {
    public static void main (String[] args) {
        Options options = new Options();
        // GameLogic gameLogic = new GameLogic(options);
        Window gameWindow = new Window(options);
        gameWindow.setVisible(true);
    }
}
