package game;

import java.util.ArrayList;

public class GameLogic {

    private GameFieldMatrix gameFieldMatrix;
    private boolean currentlyPlaying;
    

    public GameLogic(GameFieldMatrix matrix) {

        this.gameFieldMatrix = matrix;
        currentlyPlaying = false;

    }

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

    public void resetTable() {

        GameFieldMatrix newFieldMatrix = new GameFieldMatrix(gameFieldMatrix.getOptions());
        gameFieldMatrix = newFieldMatrix;

    }

    public void clickStep(int row, int col) {

        // Stazsa a gamaefiledmatrix-on, de a button matrixon nem
        CellType prewiousCellType = gameFieldMatrix.getMatrix().get(row + 1).get(col + 1);
        gameFieldMatrix.getMatrix().get(row + 1).set(col + 1, nextCellType(prewiousCellType));

    }

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

    public GameFieldMatrix getGameFieldMatrix() {
        return gameFieldMatrix;
    }

    public void setGameFieldMatrix(GameFieldMatrix gameFieldMatrix) {
        this.gameFieldMatrix = gameFieldMatrix;
    }

    public boolean isCurrentlyPlaying() {
        return currentlyPlaying;
    }

    public void setCurrentlyPlaying(boolean currentlyPlaying) {
        this.currentlyPlaying = currentlyPlaying;
    }
}
