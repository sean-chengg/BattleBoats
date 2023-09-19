import java.util.Random;
public class Battleboat {
    private int size;
    private boolean orientation;
    private Cell[] spaces;
    private boolean sunk;


    public Battleboat(int length) {
        this.size = length;
        Random r = new Random();
        orientation = r.nextBoolean();
        sunk = false;
    }

    public boolean getOrientation() {
        return this.orientation;
    }
    //true for vertical boat, false for horizontal boat


    public Cell[] getSpaces() {
        return this.spaces;
    }

    public void setSpaces(Cell[] cells) {
        this.spaces = cells;
    }

    public int getSize() {
        return this.size;
    }

    public boolean getSunk() {return sunk;}

    public void setSunk(boolean sunk) {this.sunk = sunk;}

}
