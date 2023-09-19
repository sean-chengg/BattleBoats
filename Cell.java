public class Cell {
    private int row;
    private int col;
    private char status;

    /*
    '-': hasn't been guessed, no boat present
    'B': hasn't been guessed, boat present
    'H': has been guessed, boat present
    'M': has been guessed, no boat present
     */

    public char getStatus() {
        // getter method for status
        return this.status;
    }

    public int getRow() {
        return this.row;
    }

    public int getCol() {
        return this.col;
    }

    public void setStatus(char c) {
        this.status = c;
    }

    public Cell(int row, int col, char status) {
        this.row = row;
        this.col = col;
        this.status = status;

    }
}
