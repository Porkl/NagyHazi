package game;

import java.util.ArrayList;

public class GameLogic {

    private GameFieldMatrix gameFieldMatrix;
    private boolean currentlyPlaying;
    

    /**
     * A GameLogic konstruktora
     * 
     * @param matrix : A mátrixot és az opciókat tartalmazó elem
     */
    public GameLogic(GameFieldMatrix matrix) {

        this.gameFieldMatrix = matrix;
        currentlyPlaying = false;

    }

    /**
     * Függvény, amely megmondja hogy kattintás során milyen tipusú cellából milyen típusú cella lesz.
     * Üres -> Vezető -> Elektron Fej -> Elektron Farok -> Üres -> ...
     * 
     * @param prewiousCellType : A cella típusa, amit módosítani szeretnénk
     * @return : A cella típusa, amit a módosítás után kapunk
     */
    private CellType nextCellType(CellType prewiousCellType) {

        if (prewiousCellType == CellType.EMPTY) {
            return CellType.CONDUCTOR;
        } else if (prewiousCellType == CellType.CONDUCTOR) {
            return CellType.HEAD;
        } else if (prewiousCellType == CellType.HEAD) {
            return CellType.TAIL;
        } else {
            return CellType.EMPTY;
        }

    }

    /**
     *  A tábla alaphelyzetbe állítása, azaz csak üres mezők szerepelnek rajta
     */
    public void resetTable() {

        GameFieldMatrix newFieldMatrix = new GameFieldMatrix(gameFieldMatrix.getOptions());
        gameFieldMatrix = newFieldMatrix;

    }

    /**
     * A függvény segítségével kattintás hatására megváltozik 
     * a paraméterben megadott cella a "nextCellType" függvény segítségével.
     * 
     * @param row : A vátoztatandó sor száma
     * @param col : A vátoztatandó oszlop száma
     */
    public void clickStep(int row, int col) {

        // Stazsa a gamaefiledmatrix-on, de a button matrixon nem
        CellType prewiousCellType = gameFieldMatrix.getMatrix().get(row + 1).get(col + 1);
        gameFieldMatrix.getMatrix().get(row + 1).set(col + 1, nextCellType(prewiousCellType));

    }

    /**
     *  A függvény gondskodik az elindítást követően hogy az egész tábla változzon a szabályoknak megfelelően.
     */
    public void step() {

        GameFieldMatrix newFieldMatrix = new GameFieldMatrix(gameFieldMatrix.getOptions());
        ArrayList<ArrayList<CellType>> newMatrix = newFieldMatrix.getMatrix();

        CellType cell;

        for (int row = 1; row < gameFieldMatrix.getMatrix().size() - 1; row++) {
            for (int col = 1; col < gameFieldMatrix.getMatrix().get(row).size() - 1; col++) {
                cell = gameFieldMatrix.getMatrix().get(row).get(col);
                if (cell.equals(CellType.EMPTY)) {
                    newMatrix.get(row).set(col, CellType.EMPTY);
                } else if (cell.equals(CellType.HEAD)) {
                    newMatrix.get(row).set(col, CellType.TAIL);
                } else if (cell.equals(CellType.TAIL)) {
                    newMatrix.get(row).set(col, CellType.CONDUCTOR);
                } else {
                    newMatrix.get(row).set(col, fromConductorToHeadOrConductor(gameFieldMatrix.getMatrix(), row, col));
                }
            }
        }

        gameFieldMatrix.setMatrix(newMatrix);
    }

    /**
     * A step() függvény segédfüggvénye, megmondja hogy egy CONDUCTOR cellából milyen cella lesz a következő
     * 
     * @param matrix : A mátrix, amin a műveletet el szeretnénk végezni
     * @param row : A vátoztatandó sor száma
     * @param col : A vátoztatandó oszlop száma
     * @return
     */
    private CellType fromConductorToHeadOrConductor(ArrayList<ArrayList<CellType>> matrix, int row, int col) {

        int headCount = 0;

        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = col - 1; j <= col + 1; j++) {
                if (matrix.get(i).get(j).equals(CellType.HEAD)) {
                    headCount++;
                }
            }
        }

        if (headCount == 1 || headCount == 2) {
            return CellType.HEAD;
        } else {
            return CellType.CONDUCTOR;
        }

    }

    /**
     * Az GameFieldMatrix getter függvénye
     * 
     * @return A GameFieldMatrix értéke
     */
    public GameFieldMatrix getGameFieldMatrix() {
        return gameFieldMatrix;
    }

    /**
     * Az GameFieldMatrix setter függvénye
     * 
     * @param gameFieldMatrix A GameFieldMatrix értéke
     */
    public void setGameFieldMatrix(GameFieldMatrix gameFieldMatrix) {
        this.gameFieldMatrix = gameFieldMatrix;
    }
    
    /**
     * Visszaadja, hogy a jelenlegi játék folyik-e
     * 
     * @return A jelenlegi játék folyik-e
     */
    public boolean isCurrentlyPlaying() {
        return currentlyPlaying;
    }

    /**
     * Beállítható, hogy a jelenlegi játék follyon-e
     * 
     * @param currentlyPlaying A currentlyPlaying értéke
     */
    public void setCurrentlyPlaying(boolean currentlyPlaying) {
        this.currentlyPlaying = currentlyPlaying;
    }
}
