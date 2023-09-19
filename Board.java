//Written by Sean Cheng (chen7424) and Lily Li (li002398)
public class Board {
    private int numBoats; //number of boats
    private Battleboat[] boats; //stores all the boats
    private Cell[][] board; //where cells containing information of each boath is stored
    private int[] boatSizes; //list of the sizes of boats
    private String strBoard = ""; //a board that is displayed and printed
    private int sunkCount = 0; //how many boats have been sunk

    public Board(int mode) {
        // class constructor
        /*
        creates a board, sets the sizes of the boats, how many boats, fills the board with
        empty spaces. Amount of each setting is based on the mode that is chosen.
         */
        board = new Cell[mode][mode];
        if (mode == 3) { //Beginner, 3x3
            numBoats = 1;
            boatSizes = new int[]{2};
        }
        if (mode == 6) { //Intermediate, 6x6
            numBoats = 3;
            boatSizes = new int[]{2,3,4};
        }
        if (mode == 9) { //Advanced, 9x9
            numBoats = 5;
            boatSizes = new int[]{2, 3, 3, 4, 5};
        }

        boats = new Battleboat[numBoats]; //creates empty list of boats

        //this for loop sets the board to empty spaces
        for (int i = 0; i < mode; i++){
            for (int j = 0; j < mode; j++) {
                board[i][j] = new Cell(i, j, '-');
            }
        }
    }

    public void sunk() {
        //the sunk method exists to check if a boat has been or not
        for (Battleboat boat : boats) { //loops through the boats
            int checkHit = 0;
            for (Cell chunk: boat.getSpaces()) { //loops through the cells of the boat
                if(chunk.getStatus() == 'H') { //if the boat has been hit, add a point to the checkHit counter
                    checkHit++;
                }
            }
            if (checkHit == boat.getSize()) { //if all the cells of the boat have been hit, the boat sinks
                if (boat.getSunk() == false) {
                    boat.setSunk(true);
                    System.out.println("Sunk a ship!");
                    sunkCount += 1; //count a ship as sunk
                }

            }
        }
    }

    public void placeBoats() {
        // creates a boat object
        // adds boat object to boat[]
        // places boats randomly on the board

        int i;
        int rand_x, rand_y;
        for (i = 0; i < numBoats; i++) {
            boolean switcher = true; //monitoring the while loop as a switch
            while (switcher) { //iterates through until all boats have been places
                Battleboat btl = new Battleboat(boatSizes[i]);
                boats[i] = btl;

                int pass_count = 0; //counter to see if all spots are okay to have a boat

                if (btl.getOrientation()) { //vertical is true, checking to see if there is space for a vertical ship
                    rand_x = (int) Math.floor(Math.random() * (board.length));//creates a random x value
                    rand_y = (int) Math.floor((board.length - boatSizes[i] +1) * Math.random()); //creates a random y value

                    for (int j = rand_y; j < rand_y + boatSizes[i]; j++) { //loops through each spot to see if it hasn't been fired at
                        if (board[j][rand_x].getStatus() == '-') {
                            pass_count++; //increases the pass_count
                        }
                    }
                } else { //horizontal is true, checking to see if there is space for a horizontal ship
                    rand_y = (int) Math.floor(Math.random() * (board.length)); //creates a random y value
                    rand_x = (int) (int) Math.floor((board.length - boatSizes[i] +1) * Math.random()); //creates a random x value
                    for (int k = rand_x; k < rand_x + boatSizes[i]; k++) { //loops through each spot to see if it hasn't been fired at
                        if (board[rand_y][k].getStatus() == '-') {
                            pass_count++;
                        }
                    }
                }
                Cell[] cells = new Cell[boatSizes[i]]; //creates a list of cells, with space for each cell of the boat
                if (boatSizes[i] == pass_count) { //if there were passes for each cell of the boat
                    if (btl.getOrientation()) { //vertical is true
                        for (int j = rand_y; j < rand_y + boatSizes[i]; j++) { //loops through initial y-coordinate to y-coordinate of end of boat
                            Cell cell = new Cell(j, rand_x, 'B'); //sets the cell with the y,x, and then status
                            board[j][rand_x].setStatus('B'); //sets status of board to be a battleship
                            cells[j-rand_y] = cell; //sets corresponding to the new cell with a battleeship
                            switcher = false; //turns the while loop off
                        }
                    } else if (!btl.getOrientation()) { //loops through initial x-coordinate to x-coordinate of end of boat
                        for (int j = rand_x; j < rand_x + boatSizes[i]; j++) { //sets the cell with the y,x, and then status
                            Cell cell = new Cell(rand_y, j, 'B'); //sets status of board to be a battleship
                            board[rand_y][j].setStatus('B'); //sets corresponding to the new cell with a battleeship
                            cells[j-rand_x] = cell;
                            switcher = false; //turns the while loop off
                        }
                    }
                    btl.setSpaces(cells); //sets the spaces of the battleboat to coordinates with boats
                }
            }
        }
    }

    public int fire(int x, int y) {
        // handles attacking a coordinate
        //if user attacks a coordinate and there is a boat there, sets it to hit
        int penalty = 0; //a pentaly in case the user fires at the wrong place
        if (board[y][x].getStatus() == 'B') { //if there is a boat
            board[y][x].setStatus('H'); //sets the boat to hit
            for (Battleboat boat : boats) { //for all the boats in list boats
                for (Cell chunk : boat.getSpaces()) { //for all the cells in the boat
                    if (chunk.getRow() == y && chunk.getCol() == x) { //if the "chunk" cell is equals to the coordinate being fired at
                        chunk.setStatus('H'); //set the status of the cell to be hit
                        System.out.println();
                        System.out.println("Hit");
                        System.out.println();
                    }
                }
                //need a way to tell when boat is sunk
            }
        }
        else if (board[y][x].getStatus() == '-') { //if there no boat
            System.out.println();
            board[y][x].setStatus('M');
            System.out.println("Miss");
            System.out.println();
        }
        else if (board[y][x].getStatus() != '-') { //if user fires at an already fired at cell, they recieve a penalty
            penalty = 1;
        }
        return penalty; //return the penalty, which is either 0 or 1
    }

    public void display() { //prints out the board with the location of the boats hidden
        strBoard = "";
        // prints out the final board state every turn
        for (int i = 0; i < board.length; i++) { //for each cell in the board
            for (int j = 0; j < board[i].length; j++) {
                if (getCell(i,j) == 'B') { //if the cell has an unhit boat, hide it with a "-"
                    strBoard += "-";
                    strBoard += " ";
                }
                else { //otherwise, print the cell
                    strBoard += getCell(i, j);
                    strBoard += " ";
                }
            }
            strBoard += "\n";
        }
        System.out.println(strBoard);
    }

    public void print() { // prints out the fully revealed board for debugging purposes
        strBoard = "";
        for (int i = 0; i < board.length; i++) { //for each cell on the board
            for (int j = 0; j < board[i].length; j++) {
                strBoard += getCell(i, j); //give a cell
                strBoard += " ";
            }
            strBoard += "\n";
        }
            System.out.println(strBoard);

        //debugging area
            int boatNum = 0; //indicates which boat this is
            for (Battleboat boat : boats) {
                    boatNum += 1; //numbers the boats
                for (Cell chunk : boat.getSpaces()) { //for each cell, get the cell information for each boat
                    System.out.println("x,y: (" + chunk.getCol() + "," + chunk.getRow() + ") | Status: " +
                            chunk.getStatus()+ " | Boat #" + boatNum); //prints out for the cell the boat is in, the x,y coordinates, the status, and what boat it is
                }
                System.out.println();
            }
    }

    //getter methods
    public char getCell(int x, int y) {
        return board[x][y].getStatus();
    } //gets the status of the Cell
    public int getNumBoats() {
        return this.numBoats;
    } //gets the number of boats orignally in play

    public int getSunkCount() {return this.sunkCount;} //gets the number of boats that have been sunk
}
