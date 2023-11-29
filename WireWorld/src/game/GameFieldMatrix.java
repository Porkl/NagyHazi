package game;

import java.util.ArrayList;

public class GameFieldMatrix {

    private int rowCount;
    private int columnCount;
    private ArrayList<ArrayList<CellType>> matrix;

    public GameFieldMatrix(Options options) {

        this.rowCount = options.getRowCount();
        this.columnCount = options.getColumnCount();

        structMatrix(options);
    }

    public void structMatrix(Options options) {
        this.matrix = new ArrayList<>();

        for (int row = 0; row < rowCount; row++) {
            ArrayList<CellType> r = new ArrayList<>();
            for (int col = 0; col < columnCount; col++) {
                r.add(CellType.EMPTY);
            }
            matrix.add(r);
        }
    }

    public int getRowCount() {
        return rowCount;
    }

    public int getColumnCount() {
        return columnCount;
    }

    public ArrayList<ArrayList<CellType>> getMatrix() {
        return matrix;
    }

    public void setMatrix(ArrayList<ArrayList<CellType>> matrix) {
        this.matrix = matrix;
    }

    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
    }

    public void setColumnCount(int columnCount) {
        this.columnCount = columnCount;
    }
}
