package model;

import java.util.Vector;

public class Script extends Vector {

    private int col = 0;
    private int row = 0;

    public Script(int col, int row) {
        super();
        this.row = row;
        this.col = col;
    }

    public void addString(String cmd) {
        // Îª½Å±¾Ìí¼ÓÓï¾ä
        addElement(cmd);
    }

    public void delString(int num) {
        removeElementAt(num);
    }

    public String getString(int num) {
        return (String) elementAt(num);
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }
}
