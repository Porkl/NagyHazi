package junittest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import game.GameFieldMatrix;
import game.CellType;
import game.Options;

public class GameFieldMatrixTest {
	GameFieldMatrix matrix;

	@Before
	public void setUp() {
		matrix = new GameFieldMatrix(new Options(2, 2));
	}

	@Test
	public void reBuildMatrixTest() {
		matrix.getMatrix().get(1).set(1, CellType.CONDUCTOR);
		matrix.reBuildMatrix(matrix.getOptions());
		assertEquals(matrix.getMatrix().get(1).get(1), CellType.EMPTY);
	}

}
