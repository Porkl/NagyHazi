package game;

import java.io.Serializable;

public class Options implements Serializable{
    
    private int rowCount;
    private int columnCount;

    
    /**
     *  Az Options osztály konstruktora
     */
    public Options() {
        rowCount = 10;
        columnCount = 10;
    }

    /**
     * Az Options osztály konstruktora
     * 
     * @param rowCount : A sorok száma
     * @param columnCount : Az oszlopok száma
     */
    public Options(int rowCount, int columnCount) {
        this.rowCount = rowCount;
        this.columnCount = columnCount;
    }


    /**
     * A sorok getter függvénye
     * 
     * @return A sorok száma
     */
    public int getRowCount() {
        return rowCount;
    }
    
    /**
     * Az oszlopok getter függvénye
     * 
     * @return Az oszlopk száma
     */
    public int getColumnCount() {
        return columnCount;
    }

    /**
     * A sorok setter függvénye
     * 
     * @param rowCount : A sorok száma
     */
    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
    }
    
    /**
     * Az oszlopok setter függvénye
     * 
     * @param rowCount : Az oszlopok száma
     */
    public void setColumnCount(int columnCount) {
        this.columnCount = columnCount;
    }
}
