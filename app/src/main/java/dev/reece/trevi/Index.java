package dev.reece.trevi;

/**
 * Created by reececheng on 2019/1/9.
 */

public class Index {
    private int column;
    private int row;

    public Index(int column, int row) {
        this.column = column;
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }
}
