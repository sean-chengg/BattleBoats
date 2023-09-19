//Written by Sean Cheng (chen7424) and Lily Li (li002398)
public class Cell {
    private int row;
    private int col;
    private char status; //represents the status of the cell

    /*
    '-': hasn't been guessed, no boat present
    'B': hasn't been guessed, boat present
    'H': has been guessed, boat present
    'M': has been guessed, no boat present
     */

    // getter method for status
    public char getStatus() {
        return this.status;
    }
    // getter method for row
    public int getRow() {
        return this.row;
    }
    // getter method for column
    public int getCol() {
        return this.col;
    }
    // getter method for status
    public void setStatus(char c) {
        this.status = c;
    }
    //constuctor method for Cell
    public Cell(int row, int col, char status) {
        this.row = row;
        this.col = col;
        this.status = status;

    }
}
