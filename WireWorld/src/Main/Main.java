package Main;

import UI.Window;
import game.GameFieldMatrix;
import game.GameLogic;
import game.Options;

public class Main {
    public static void main (String[] args) {
        Options options = new Options();
        GameFieldMatrix gameFieldMatrix = new GameFieldMatrix(options);
        GameLogic gameLogic = new GameLogic(gameFieldMatrix);
        Window gameWindow = new Window(gameLogic);
        gameWindow.setVisible(true);
    }
}
