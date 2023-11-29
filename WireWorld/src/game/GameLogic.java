package game;

import java.util.ArrayList;

public class GameLogic {

    private int rowCount;
    private int columnCount;
    private ArrayList<ArrayList<CellType>> matrix;
    private boolean currentlyPlaying;
    private enum CellType {
        EMPTY,      // Color: BLACK
        HEAD,       // Color: BLUE
        TAIL,       // Color: RED
        CONDUCTOR   // Color: YELLOW
    }


    public GameLogic(Options options) {

        this.rowCount = options.getRowCount();
        this.columnCount = options.getColumnCount();
        matrix = new ArrayList<>();
        currentlyPlaying = false;

        for (int row = 0; row < rowCount; row++) {
            ArrayList<CellType> r = new ArrayList<>();
            for (int col = 0; col < columnCount; col++) {
                r.add(CellType.EMPTY);
            }
            matrix.add(r);
        }

    }

    public GameLogic(ArrayList<ArrayList<CellType>> matrix, Options options) {
        
        this.rowCount = options.getRowCount();
        this.columnCount = options.getColumnCount();
        this.matrix = matrix;
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
}
