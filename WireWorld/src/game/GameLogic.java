package game;

import java.util.ArrayList;

public class GameLogic {

    private int rowCount;
    private int columnCount;
    private ArrayList<ArrayList<CellType>> matrix;
    private boolean currentlyPlaying;
    

    public GameLogic(GameFieldMatrix matrix) {

        this.rowCount = matrix.getRowCount();
        this.columnCount = matrix.getColumnCount();
        this.matrix = matrix.getMatrix();
        currentlyPlaying = false;

    }

    public void step() {

        ArrayList<ArrayList<CellType>> newMatrix = new ArrayList<>();
        for (int row = 0; row < rowCount; row++) {
            ArrayList<CellType> r = new ArrayList<>();
            matrix.add(r);
        }


        CellType cell;

        for (int row = 0; row < matrix.size(); row++) {
            for (int col = 0; col < matrix.get(row).size(); col++) {
                cell = matrix.get(row).get(col);
                if (cell.equals(CellType.EMPTY)) {
                    newMatrix.get(row).set(col, CellType.EMPTY);
                } else if (cell.equals(CellType.HEAD)) {
                    newMatrix.get(row).set(col, CellType.TAIL);
                } else if (cell.equals(CellType.TAIL)) {
                    newMatrix.get(row).set(col, CellType.CONDUCTOR);
                } else {
                    newMatrix.get(row).set(col, fromConductorToHeadOrConductor(matrix, row, col));
                }
            }
        }

        matrix = newMatrix;
    }

    private CellType fromConductorToHeadOrConductor(ArrayList<ArrayList<CellType>> matrix, int row, int col) {
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
            if (col == lastColIndex) {

                for(int i = row; i < 2; i++) {
                    for (int j = col; j > lastColIndex - 2; j--) {
                        if (matrix.get(i).get(j).equals(CellType.HEAD)) {
                            headCount++;
                        }
                    }
                }

            }

        }

        if (headCount == 1 || headCount == 2) {
            return CellType.HEAD;
        } else {
            return CellType.CONDUCTOR;
        }
    }

    public void updateMatrixTitle(int row, int col) {

    }
}
