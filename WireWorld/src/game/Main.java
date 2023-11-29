package game;

public class Main {
    public static void main (String[] args) {
        Options options = new Options();
        GameFieldMatrix gameFieldMatrix = new GameFieldMatrix(options);
        // GameLogic gameLogic = new GameLogic(options);
        Window gameWindow = new Window(options, gameFieldMatrix);
        gameWindow.setVisible(true);
    }
}
