package game;

import java.io.Serializable;
import java.util.ArrayList;

public class GameFieldMatrix implements Serializable{

    private Options options;
    private ArrayList<ArrayList<CellType>> matrix;

    /**
     * A GameFieldMatrix konstruktora
     * 
     * @param options : A játéktér opcióit tartalmazó Options class
     */
    public GameFieldMatrix(Options options) {

        this.options = options;

        reBuildMatrix(this.options);
    }

    /**
     * A GameFieldMatrix újjáépítésére szolgáló függvény.
     * Segítségével új Option-öket kapva lehet építeni egy új mátrixot.
     * Az új mátrix felülírja az előzőt.
     * 
     * @param options : A játéktér opcióit tartalmazó Options class
     */
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

    /**
     * Az Options getter függvénye
     * 
     * @return Az Options értéke
     */
    public Options getOptions() {
        return options;
    }

    /**
     * Az Options setter függvénye
     * 
     * @param options : A játéktér opcióit tartalmazó Options class
     */
    public void setOptions(Options options) {
        this.options = options;
    }

    /**
     * A mátrixunk getter függvénye
     * 
     * @return A mátrix struktúra
     */
    public ArrayList<ArrayList<CellType>> getMatrix() {
        return matrix;
    }

    /**
     * A mátrixunk setter függvénye
     * 
     * @param matrix : A mátrixunk értékeit tartalmazó mátrix
     */
    public void setMatrix(ArrayList<ArrayList<CellType>> matrix) {
        this.matrix = matrix;
    }
}
