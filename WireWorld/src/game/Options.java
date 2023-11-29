package game;

public class Options {
    
    private int rowCount;
    private int columnCount;

    
    public Options() {
        rowCount = 10;
        columnCount = 10;
    }

    public Options(int rowCount, int columnCount) {
        this.rowCount = rowCount;
        this.columnCount = columnCount;
    }


    public int getRowCount() {
        return rowCount;
    }

    public int getColumnCount() {
        return columnCount;
    }

    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
    }

    public void setColumnCount(int columnCount) {
        this.columnCount = columnCount;
    }
}
