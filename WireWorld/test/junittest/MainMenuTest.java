package junittest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import UI.MainMenu;
import UI.Window;
import game.GameFieldMatrix;
import game.GameLogic;
import game.Options;

public class MainMenuTest {
    Window window;
    MainMenu mainMenu;

    @Before
	public void setUp() {
        window = new Window(new GameLogic(new GameFieldMatrix(new Options())));
        mainMenu = new MainMenu(window);
    }

    @Test
	public void saveIsWorking() {
        mainMenu.save("TestSave.txt");
        File f = new File("TestSave.txt");
        assertTrue(f.exists());
    }

    @Test
	public void loadIsWorking() {
        Window window2 = new Window(new GameLogic(new GameFieldMatrix(new Options())));
        mainMenu.loadPreviousGame("TestSave.txt");
        assertEquals(window2.getLogic().getGameFieldMatrix().getOptions().getColumnCount(), window.getLogic().getGameFieldMatrix().getOptions().getColumnCount());
        assertEquals(window2.getLogic().getGameFieldMatrix().getOptions().getRowCount(), window.getLogic().getGameFieldMatrix().getOptions().getRowCount());
    }

    @Test
	public void loadIsWorking2() {
        Window window2 = new Window(new GameLogic(new GameFieldMatrix(new Options())));
        mainMenu.loadPreviousGame("TestSave.txt");
        assertEquals(window2.getLogic().getGameFieldMatrix().getMatrix(), window.getLogic().getGameFieldMatrix().getMatrix());
    }

}
