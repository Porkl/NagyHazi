package game;

import java.util.ArrayList;

public class GameLogic {

    private GameFieldMatrix gameFieldMatrix;
    private boolean currentlyPlaying;
    

    public GameLogic(GameFieldMatrix matrix) {

        this.gameFieldMatrix = gameFieldMatrix;
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

    public void clickStep(int row, int col) {

        // Stazsa a gamaefiledmatrix-on, de a button matrixon nem
        CellType prewiousCellType = gameFieldMatrix.getMatrix().get(row + 1).get(col + 1);
        gameFieldMatrix.getMatrix().get(row + 1).set(col + 1, nextCellType(prewiousCellType));

    }

    public void step() {

        ArrayList<ArrayList<CellType>> newMatrix = new ArrayList<>();
        for (int row = 0; row < gameFieldMatrix.getMatrix().size(); row++) {
            ArrayList<CellType> r = new ArrayList<>();
            newMatrix.add(r);
        }


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

        for (int i = row - 1; i <= row + 1; row++) {
            for (int j = col - 1; j < col + 1; col++) {
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
        
        /*
        int lastRowIndex = matrix.size();
        int lastColIndex = matrix.get(0).size();

        int headCount = 0;
        if (row == 0) {
            // Upper left:
            if (col == 0) {

                for (int i = row; i < 2; i++) {
                    for (int j = col; j < 2; j++) {
                        if (matrix.get(i).get(j).equals(CellType.HEAD)) {
                            headCount++;
                        }
                    }
                }

            }

            // Upper right:
            else if (col == lastColIndex) {

                for(int i = row; i < 2; i++) {
                    for (int j = col; j > lastColIndex - 2; j--) {
                        if (matrix.get(i).get(j).equals(CellType.HEAD)) {
                            headCount++;
                        }
                    }
                }

            }

            // Upper row middle:
            else {

                for (int i = row; i < 2; i++) {
                    for (int j = col - 1; j < 2; j++) {

                    }
                }
            }

        }

        if (headCount == 1 || headCount == 2) {
            return CellType.HEAD;
        } else {
            return CellType.CONDUCTOR;
        }
        */
    }

    public boolean isCurrentlyPlaying() {
        return currentlyPlaying;
    }

    public void setCurrentlyPlaying(boolean currentlyPlaying) {
        this.currentlyPlaying = currentlyPlaying;
    }
}
