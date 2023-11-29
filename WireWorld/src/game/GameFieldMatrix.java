package game;

import java.util.ArrayList;

public class GameFieldMatrix {

    private Options options;
    private ArrayList<ArrayList<CellType>> matrix;

    public GameFieldMatrix(Options options) {

        this.options = options;

        structMatrix(this.options);
    }

    public void structMatrix(Options options) {
        this.matrix = new ArrayList<>();

        for (int row = 0; row < options.getRowCount(); row++) {
            ArrayList<CellType> r = new ArrayList<>();
            for (int col = 0; col < options.getColumnCount(); col++) {
                r.add(CellType.EMPTY);
            }
            matrix.add(r);
        }
    }

    public Options getOptions() {
        return options;
    }

    public void setOptions(Options options) {
        this.options = options;
    }

    public ArrayList<ArrayList<CellType>> getMatrix() {
        return matrix;
    }

    public void setMatrix(ArrayList<ArrayList<CellType>> matrix) {
        this.matrix = matrix;
    }
}
