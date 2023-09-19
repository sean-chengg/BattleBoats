//Written by Sean Cheng (chen7424) and Lily Li (li002398)
import java.util.Random;
public class Battleboat {
    //initalizes variables
    private int size;
    private boolean orientation;
    private Cell[] spaces;
    private boolean sunk;

    public Battleboat(int length) {  //constructor method of the boat
        this.size = length;
        Random r = new Random(); //gives a random boolean to determine if the boat is vertical or horizontal
        orientation = r.nextBoolean();
        sunk = false;
    }

    public boolean getOrientation() {
        return this.orientation;
    }
    //true for vertical boat, false for horizontal boat

    //getter methods
    public Cell[] getSpaces() {
        return this.spaces;
    } //get the cells the boat is in

    public int getSize() {
        return this.size;
    } //get the size of the boat

    public boolean getSunk() {return sunk;} //get if the boat is sunk or not

    //setter methods
    public void setSunk(boolean sunk) {this.sunk = sunk;} //set if the board is sunk

    public void setSpaces(Cell[] cells) {
        this.spaces = cells;
    } //set the cells the boat is in

}
