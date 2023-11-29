package game;

import java.util.ArrayList;

public class GameLogic {
    private int rowCount;
    private int columnCount;

    private ArrayList<ArrayList<CellType>> matrix;

    private enum CellType {
        EMPTY,
        HEAD,
        TAIL,
        CONDUCTOR
    }

    public GameLogic(Options options) {
        this.rowCount = options.getRowCount();
        this.columnCount = options.getColumnCount();
        matrix = new ArrayList<>();

        for (int row = 0; row < rowCount; row++) {
            ArrayList<CellType> r = new ArrayList<>();
            for (int col = 0; col < columnCount; col++) {
                r.add(CellType.EMPTY);
            }
            matrix.add(r);
        }
    }

    public GameLogic(ArrayList<ArrayList<CellType>> matrix, Options options) {
        
    }
}
