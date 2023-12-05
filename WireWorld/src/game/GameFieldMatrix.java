package game;

import java.io.Serializable;
import java.util.ArrayList;

public class GameFieldMatrix implements Serializable{

    private Options options;
    private ArrayList<ArrayList<CellType>> matrix;

    public GameFieldMatrix(Options options) {

        this.options = options;

        reBuildMatrix(this.options);
    }

    public void reBuildMatrix(Options options) {
        this.matrix = new ArrayList<>();

        // Stazsa a konnyebb kezelhetoseg miatt (+2)
        for (int row = 0; row < options.getRowCount() + 2; row++) {
            ArrayList<CellType> r = new ArrayList<>();
            for (int col = 0; col < options.getColumnCount() + 2; col++) {
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
