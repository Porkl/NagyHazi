package junittest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import game.CellType;
import game.GameFieldMatrix;
import game.GameLogic;
import game.Options;

public class GameLogicTest {
	GameLogic logic;
	GameFieldMatrix gameFieldMatrix1;
	boolean currentlyPlaying;

	@Before
	public void setUp() {
		gameFieldMatrix1 = new GameFieldMatrix(new Options(2, 2));
		logic = new GameLogic(gameFieldMatrix1);
		currentlyPlaying = false;
	}

	
	@Test
	public void resetTableTest() {
		GameFieldMatrix gameFieldMatrix2 = new GameFieldMatrix(new Options(2, 2));
		logic.getGameFieldMatrix().getMatrix().get(0).set(0, CellType.CONDUCTOR);
		logic.resetTable();
		assertEquals(gameFieldMatrix2.getMatrix(), logic.getGameFieldMatrix().getMatrix()); 
	}

	@Test
	public void stepTest1() {
		logic.getGameFieldMatrix().getMatrix().get(1).set(1, CellType.CONDUCTOR);
		logic.step();
		assertEquals(logic.getGameFieldMatrix().getMatrix().get(1).get(1), CellType.CONDUCTOR);
	}

	@Test
	public void stepTest2() {
		logic.getGameFieldMatrix().getMatrix().get(1).set(1, CellType.HEAD);
		logic.step();
		assertEquals(logic.getGameFieldMatrix().getMatrix().get(1).get(1), CellType.TAIL);
	}

	@Test
	public void stepTest3() {
		logic.getGameFieldMatrix().getMatrix().get(1).set(1, CellType.TAIL);
		logic.step();
		assertEquals(logic.getGameFieldMatrix().getMatrix().get(1).get(1), CellType.CONDUCTOR);
	}
}
