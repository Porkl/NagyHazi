package junittest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import UI.Window;
import game.GameFieldMatrix;
import game.GameLogic;
import game.Options;

public class WindowTest {
    Window window;

	@Before
	public void setUp() {
        window = new Window(new GameLogic(new GameFieldMatrix(new Options(2,2))));
	}

    @Test
	public void WindowExistTest() {
        assertNotNull(window);
	}
}
